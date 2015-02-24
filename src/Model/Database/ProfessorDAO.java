
package Model.Database;

import Model.Entidades.Professor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProfessorDAO {

    private Connection connection;
    private ResultSet rs;
    private PreparedStatement pstm;

    Professor professor;
    ArrayList<Professor> professores;

    public ProfessorDAO() {
        connection = new ConnectionFactory().getConnection();
    }
    
    public String checkEmailExists(String email) {
        
        String emailResult = "";
        String sql = "SELECT u.email FROM usuario u WHERE u.email = (?)";

        try {
            pstm = connection.prepareStatement(sql);
            pstm.setString(1, email);

            pstm.execute();
            rs = pstm.getResultSet();
            
            while (rs.next()) {
                emailResult = rs.getString(1);
            }
            
            pstm.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return emailResult;
        
    }

    public Professor getProfessor(String email) {

        professor = new Professor();

        String sql = "SELECT u.email, u.nome, u.senha, u.papel, u .status, p.departamento, p.disciplina_principal FROM usuario u, professor p WHERE"
                + " u.email = (?) and u.email = p.email";

        try {
            pstm = connection.prepareStatement(sql);
            pstm.setString(1, email);

            pstm.execute();
            rs = pstm.getResultSet();

            while (rs.next()) {
                professor.setEmail(rs.getString(1));
                professor.setNome(rs.getString(2));
                professor.setSenha(rs.getString(3));
                professor.setPapel(rs.getString(4));
                professor.setStatus(rs.getString(5));
                professor.setDepartamento(rs.getString(6));
                professor.setDisciplinaPrincipal(rs.getString(7));
            }

            }catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return professor;
    }
    
    public ArrayList<Professor> getProfessores() {

        String sql = "SELECT p.email, p.disciplina_principal, p.departamento, u.nome, u.senha, u.status, u.papel FROM professor p, usuario u"
                + " WHERE p.email = u.email";

        professores = new ArrayList<>();

        try {
            pstm = connection.prepareStatement(sql);
            pstm.execute();
            rs = pstm.getResultSet();

            while (rs.next()) {
                professor = new Professor();
                professor.setEmail(rs.getString(1));
                professor.setDisciplinaPrincipal(rs.getString(2));
                professor.setDepartamento(rs.getString(3));
                professor.setNome(rs.getString(4));
                professor.setSenha(rs.getString(5));
                professor.setStatus(rs.getString(6));
                professor.setPapel(rs.getString(7));
                professores.add(professor);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return professores;
    }
    
    public ArrayList<Professor> getProfessoresAtivos() {

        String sql = "SELECT p.email, p.disciplina_principal, p.departamento, u.nome, u.senha, u.status, u.papel FROM professor p, usuario u"
                + " WHERE p.email = u.email and u.status = 'Ativo'";

        professores = new ArrayList<>();

        try {
            pstm = connection.prepareStatement(sql);
            pstm.execute();
            rs = pstm.getResultSet();

            while (rs.next()) {
                professor = new Professor();
                professor.setEmail(rs.getString(1));
                professor.setDisciplinaPrincipal(rs.getString(2));
                professor.setDepartamento(rs.getString(3));
                professor.setNome(rs.getString(4));
                professor.setSenha(rs.getString(5));
                professor.setStatus(rs.getString(6));
                professor.setPapel(rs.getString(7));
                professores.add(professor);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return professores;
    }
    
    public boolean addProfessor(Professor p) {

        new UsuarioDAO().addUsuario(p);

        String sql = "INSERT INTO professor (email, departamento, disciplina_principal)"
                + " VALUES (?, ?, ?)";

        try {
            pstm = connection.prepareStatement(sql);
            pstm.setString(1, p.getEmail());
            pstm.setString(2, p.getDepartamento());
            pstm.setString(3, p.getDisciplinaPrincipal());

            pstm.execute();
            pstm.close();

        } catch (SQLException e) {
//            throw new RuntimeException(e);
            return false;
        }

        return true;
    }

    public boolean editProfessor(Professor p) {

        boolean check1 = false, check2 = false;
        
        // update na tabela usuario
        String sql = "UPDATE usuario SET nome = (?), senha = (?), ultima_modificacao = curdate(), status = (?) WHERE usuario.email = (?)";

        try {
            pstm = connection.prepareStatement(sql);
            pstm.setString(1, p.getNome());
            pstm.setString(2, p.getSenha());
            pstm.setString(3, p.getStatus());
            pstm.setString(4, p.getEmail());
            
            pstm.execute();
            pstm.close();

            check1 = true;
        } catch (SQLException e) {
            check1 = false;
//            throw new RuntimeException(e);
        }
        
        // update na tabela professor
        sql = "UPDATE professor SET departamento = (?), disciplina_principal = (?) WHERE professor.email = (?)";
        
        try {
            pstm = connection.prepareStatement(sql);
            pstm.setString(1, p.getDepartamento());
            pstm.setString(2, p.getDisciplinaPrincipal());
            pstm.setString(3, p.getEmail());
            
            pstm.execute();
            pstm.close();

            check2 = true;
        } catch (SQLException e) {
            check2 = false;
//            throw new RuntimeException(e);
        }
        
        
        if (!check1 || !check2) return false;
        else return true;
    }

    public boolean deleteProfessor(String email) {
        
        // verificar como fica a questao de o Professor estar em um Projeto

        String sql = "UPDATE usuario SET status = 'Inativo' WHERE usuario.email = (?)";

        try {
            pstm = connection.prepareStatement(sql);
            pstm.setString(1, email);

            pstm.execute();
            pstm.close();

        } catch (SQLException e) {
            return false;
//            throw new RuntimeException(e);
        }

        return true;

    }

}
