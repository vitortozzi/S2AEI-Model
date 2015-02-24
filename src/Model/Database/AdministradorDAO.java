
package Model.Database;

import Model.Entidades.Administrador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AdministradorDAO {

    Administrador adm;

    private Connection connection;
    private ResultSet rs;
    private PreparedStatement pstm;

    public AdministradorDAO() {
        connection = new ConnectionFactory().getConnection();
    }
    
    public boolean checkLogin(String email, String senha) {

        String sql = "SELECT u.email, u.senha FROM usuario u WHERE u.email = (?) AND "
                + "u.senha = (?) AND u.papel = 'Administrador' and u.status = 'Ativo'";
        
        adm = new Administrador();

        try {
            pstm = connection.prepareStatement(sql);
            pstm.setString(1, email);
            pstm.setString(2, senha);

            pstm.execute();
            rs = pstm.getResultSet();
            
            if (rs.next()) {
                return true;
            } else {
                return false;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
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
    
    public Administrador getAdministrador(String email) {

        adm = new Administrador();

        String sql = "SELECT u.email, u.nome, u.senha, u.papel, u .status FROM usuario u "
                + "WHERE u.email = (?)";

        try {
            pstm = connection.prepareStatement(sql);
            pstm.setString(1, email);

            pstm.execute();
            rs = pstm.getResultSet();

            while (rs.next()) {
                adm.setEmail(rs.getString(1));
                adm.setNome(rs.getString(2));
                adm.setSenha(rs.getString(3));
                adm.setPapel(rs.getString(4));
                adm.setStatus(rs.getString(5));
            }

            }catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return adm;
    }

    public ArrayList<Administrador> getAdministradores() {

        ArrayList<Administrador> administradores = new ArrayList<>();

        String sql = "SELECT u.email, u.nome, u.senha, u.papel, u .status "
                + "FROM usuario u WHERE u.papel = 'Administrador'";

        try {
            pstm = connection.prepareStatement(sql);
            pstm.execute();
            rs = pstm.getResultSet();

            while (rs.next()) {
                adm = new Administrador();
                adm.setEmail(rs.getString(1));
                adm.setNome(rs.getString(2));
                adm.setSenha(rs.getString(3));
                adm.setPapel(rs.getString(4));
                adm.setStatus(rs.getString(5));
                
                administradores.add(adm);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return administradores;
    }

    public ArrayList<Administrador> getAdministradorAtivos() {

        ArrayList<Administrador> administradores = new ArrayList<>();

        String sql = "SELECT u.email, u.nome, u.senha, u.papel, u .status"
                + " FROM usuario u WHERE u.status = 'Ativo'";

        try {
            pstm = connection.prepareStatement(sql);
            pstm.execute();
            rs = pstm.getResultSet();

            while (rs.next()) {
                adm = new Administrador();
                adm.setEmail(rs.getString(1));
                adm.setNome(rs.getString(2));
                adm.setSenha(rs.getString(3));
                adm.setPapel(rs.getString(4));
                adm.setStatus(rs.getString(5));

                administradores.add(adm);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return administradores;
    }

    public boolean addAdministrador(Administrador newAdm) {

        new UsuarioDAO().addUsuario(newAdm);

        return true;
    }

    public boolean editAdministrador(Administrador editAdm) {

        // update na tabela usuario
        String sql = "UPDATE usuario SET nome = (?), senha = (?), ultima_modificacao = curdate(), status = (?) WHERE usuario.email = (?)";

        try {
            pstm = connection.prepareStatement(sql);
            pstm.setString(1, editAdm.getNome());
            pstm.setString(2, editAdm.getSenha());
            pstm.setString(3, editAdm.getStatus());
            pstm.setString(4, editAdm.getEmail());
            
            pstm.execute();
            pstm.close();

        } catch (SQLException e) {
            return false;
//            throw new RuntimeException(e);
        }

        return true;
    }

    public boolean deleteAdministrador(String email) {
        
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
