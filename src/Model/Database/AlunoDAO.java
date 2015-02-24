
package Model.Database;

import Model.Entidades.Aluno;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AlunoDAO {

    Aluno a;

    private Connection connection;
    private ResultSet rs;
    private PreparedStatement pstm;

    public AlunoDAO() {
        connection = new ConnectionFactory().getConnection();
    }

    public Aluno getAluno(String email) {

        a = new Aluno();

        String sql = "SELECT u.email, u.nome, u.senha, u.papel, u .status, a.matricula, a.curso, a.periodo FROM usuario u, aluno a WHERE"
                + " u.email = (?) and u.email = a.email";

        try {
            pstm = connection.prepareStatement(sql);
            pstm.setString(1, email);

            pstm.execute();
            rs = pstm.getResultSet();

            while (rs.next()) {
                a.setEmail(rs.getString(1));
                a.setNome(rs.getString(2));
                a.setSenha(rs.getString(3));
                a.setPapel(rs.getString(4));
                a.setStatus(rs.getString(5));
                a.setMatricula(rs.getString(6));
                a.setCurso(rs.getString(7));
                a.setPeriodo(rs.getInt(8));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return a;
    }

    public ArrayList<Aluno> getAlunos() {

        ArrayList<Aluno> alunos = new ArrayList<>();

        String sql = "SELECT a.email, u.nome, u.senha, u.papel, u.status, a.matricula, a.curso, a.periodo FROM aluno a, usuario u"
                + " WHERE a.email = u.email";

        try {
            pstm = connection.prepareStatement(sql);
            pstm.execute();
            rs = pstm.getResultSet();

            while (rs.next()) {
                a = new Aluno();
                a.setEmail(rs.getString(1));
                a.setNome(rs.getString(2));
                a.setSenha(rs.getString(3));
                a.setPapel(rs.getString(4));
                a.setStatus(rs.getString(5));
                a.setMatricula(rs.getString(6));
                a.setCurso(rs.getString(7));
                a.setPeriodo(rs.getInt(8));
                alunos.add(a);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return alunos;
    }

    public ArrayList<Aluno> getAlunosAtivos() {

        ArrayList<Aluno> alunos = new ArrayList<>();

        String sql = "SELECT a.email, u.nome, u.senha, u.papel, a.matricula, a.curso, a.periodo FROM aluno a, usuario u"
                + " WHERE a.email = u.email and u.status = 'Ativo'";

        try {
            pstm = connection.prepareStatement(sql);
            pstm.execute();
            rs = pstm.getResultSet();

            while (rs.next()) {
                a = new Aluno();
                a.setEmail(rs.getString(1));
                a.setNome(rs.getString(2));
                a.setSenha(rs.getString(3));
                a.setPapel(rs.getString(4));
                a.setMatricula(rs.getString(5));
                a.setCurso(rs.getString(6));
                a.setPeriodo(rs.getInt(7));
                alunos.add(a);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return alunos;
    }

    public boolean addAluno(Aluno a) {

        new UsuarioDAO().addUsuario(a);

        String sql = "INSERT INTO aluno (email, matricula, curso, periodo)"
                + " VALUES (?, ?, ?, ?)";

        try {
            pstm = connection.prepareStatement(sql);
            pstm.setString(1, a.getEmail());
            pstm.setString(2, a.getMatricula());
            pstm.setString(3, a.getCurso());
            pstm.setInt(4, a.getPeriodo());

            pstm.execute();
            pstm.close();

            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public boolean deleteAluno(String email) {

        String sql = "DELETE FROM participa WHERE email_aluno = (?)";

        try {
            pstm = connection.prepareStatement(sql);
            pstm.setString(1, email);

            pstm.execute();
            pstm.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        sql = "UPDATE usuario SET status = 'Inativo' WHERE usuario.email = (?)";

        try {
            pstm = connection.prepareStatement(sql);
            pstm.setString(1, email);

            pstm.execute();
            pstm.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return true;

    }

    public boolean updateAluno(Aluno a) {
        String sql = "UPDATE aluno a, usuario u SET u.nome = (?), a.curso = (?), a.periodo = (?), u.status = (?), u.ultima_modificacao = curdate() "
                + " WHERE a.email = (?) and u.email = (?)";

        try {
            pstm = connection.prepareStatement(sql);
            pstm.setString(1, a.getNome());
            pstm.setString(2, a.getCurso());
            pstm.setInt(3, a.getPeriodo());
            pstm.setString(4, a.getStatus());
            pstm.setString(5, a.getEmail());
            pstm.setString(6, a.getEmail());

            pstm.execute();
            pstm.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return true;
    }

    public boolean AlunosIsLeader(String nome) {

        String sql = "SELECT id FROM projeto WHERE (SELECT email FROM usuario WHERE (?) = nome) = email_lider "
                + "and (status = 'Novo' or status = 'Em preenchimento' or status = 'Em avaliação')";

        try {
            pstm = connection.prepareStatement(sql);
            pstm.setString(1, nome);
            pstm.execute();
            rs = pstm.getResultSet();

            while (rs.next()) {
                return true;
            }
            return false;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
