
package Model.Database;

import Model.Tabelas.Avaliador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AvaliadorDAO {

    Avaliador av;

    private Connection connection;
    private ResultSet rs;
    private PreparedStatement pstm;

    public AvaliadorDAO() {
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
    
    public Avaliador getAvaliador(String email) {

        av = new Avaliador();

        String sql = "SELECT u.email, u.nome, u.senha, u.papel, u.status, a.area, a.formacao FROM usuario u, avaliador a WHERE"
                + " u.email = (?) and u.email = a.email";

        try {
            pstm = connection.prepareStatement(sql);
            pstm.setString(1, email);

            pstm.execute();
            rs = pstm.getResultSet();

            while (rs.next()) {
                av.setEmail(rs.getString(1));
                av.setNome(rs.getString(2));
                av.setSenha(rs.getString(3));
                av.setPapel(rs.getString(4));
                av.setStatus(rs.getString(5));
                av.setArea(rs.getString(6));
                av.setFormacao(rs.getString(7));
            }

            }catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return av;
    }

    public ArrayList<Avaliador> getAvaliadores() {

        ArrayList<Avaliador> avaliadores = new ArrayList<>();

        String sql = "SELECT u.email, u.nome, u.senha, u.papel, u.status, a.area, a.formacao "
                + "FROM usuario u, avaliador a WHERE u.papel = 'Avaliador' and u.email = a.email";

        try {
            pstm = connection.prepareStatement(sql);
            pstm.execute();
            rs = pstm.getResultSet();

            while (rs.next()) {
                av = new Avaliador();
                av.setEmail(rs.getString(1));
                av.setNome(rs.getString(2));
                av.setSenha(rs.getString(3));
                av.setPapel(rs.getString(4));
                av.setStatus(rs.getString(5));
                av.setArea(rs.getString(6));
                av.setFormacao(rs.getString(7));
                avaliadores.add(av);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return avaliadores;
    }

    public ArrayList<Avaliador> getAvaliadoresAtivos() {

        ArrayList<Avaliador> avaliadores = new ArrayList<>();

        String sql = "SELECT u.email, u.nome, u.senha, u.papel, u.status, a.area, a.formacao "
                + "FROM usuario u, avaliador a WHERE u.email = a.email AND u.status = 'Ativo'";

        try {
            pstm = connection.prepareStatement(sql);
            pstm.execute();
            rs = pstm.getResultSet();

            while (rs.next()) {
                av = new Avaliador();
                av.setEmail(rs.getString(1));
                av.setNome(rs.getString(2));
                av.setSenha(rs.getString(3));
                av.setPapel(rs.getString(4));
                av.setStatus(rs.getString(5));
                av.setArea(rs.getString(6));
                av.setFormacao(rs.getString(7));
                avaliadores.add(av);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return avaliadores;
    }

    public boolean addAvaliador(Avaliador av) {

        new UsuarioDAO().addUsuario(av);

        String sql = "INSERT INTO avaliador (email, area, formacao)"
                + " VALUES (?, ?, ?)";

        try {
            pstm = connection.prepareStatement(sql);
            pstm.setString(1, av.getEmail());
            pstm.setString(2, av.getArea());
            pstm.setString(3, av.getFormacao());

            pstm.execute();
            pstm.close();

        } catch (SQLException e) {
//            throw new RuntimeException(e);
            return false;
        }

        return true;
    }

    public boolean editAvaliador(Avaliador av) {

        boolean check1 = false, check2 = false;
        
        // update na tabela usuario
        String sql = "UPDATE usuario SET nome = (?), senha = (?), ultima_modificacao = curdate(), status = (?) WHERE usuario.email = (?)";

        try {
            pstm = connection.prepareStatement(sql);
            pstm.setString(1, av.getNome());
            pstm.setString(2, av.getSenha());
            pstm.setString(3, av.getStatus());
            pstm.setString(4, av.getEmail());
            
            pstm.execute();
            pstm.close();

            check1 = true;
        } catch (SQLException e) {
            check1 = false;
//            throw new RuntimeException(e);
        }
        
        // update na tabela avaliador
        sql = "UPDATE avaliador SET area = (?), formacao = (?) WHERE avaliador.email = (?)";
        
        try {
            pstm = connection.prepareStatement(sql);
            pstm.setString(1, av.getArea());
            pstm.setString(2, av.getFormacao());
            pstm.setString(3, av.getEmail());
            
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

    public boolean deleteAvaliador(String email) {

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
