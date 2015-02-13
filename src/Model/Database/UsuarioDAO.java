/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Database;

import Model.Tabelas.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Vítor
 */
public class UsuarioDAO {

    private Connection connection;
    private ResultSet rs;
    private PreparedStatement pstm;

    public UsuarioDAO() {
        connection = new ConnectionFactory().getConnection();
    }

    public void addUsuario(Usuario u) {

        String sql = "INSERT INTO usuario (email, nome, senha, data_cadastro, ultima_modificacao, papel, status)"
                + " VALUES (?, ?, ?, curdate(), curdate(), ?, ?)";

        try {
            pstm = connection.prepareStatement(sql);

            pstm.setString(1, u.getEmail());
            pstm.setString(2, u.getNome());
            pstm.setString(3, u.getSenha());
            pstm.setString(4, u.getPapel());
            pstm.setString(5, u.getStatus());

            pstm.execute();
            pstm.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public String checkLogin(String email, String senha) {

        String sql = "SELECT u.papel FROM usuario u WHERE u.email = (?) AND "
                + "u.senha = (?) AND u.status = 'Ativo'";
        
        try {
            pstm = connection.prepareStatement(sql);
            pstm.setString(1, email);
            pstm.setString(2, senha);

            pstm.execute();
            rs = pstm.getResultSet();
            
            if (rs.next()) {
                return rs.getString(1);
            } else {
                return "";
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
