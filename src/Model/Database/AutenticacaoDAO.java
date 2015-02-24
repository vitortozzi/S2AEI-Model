
package Model.Database;

import Model.Entidades.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AutenticacaoDAO {

    private Connection connection;
    private ResultSet rs;
    private PreparedStatement pstm;

    public AutenticacaoDAO() {
        connection = new ConnectionFactory().getConnection();
    }

    
    
    public Usuario selectUser(String email) {

        Usuario u = new Usuario();
        String sql = "SELECT * FROM usuario WHERE login = (?)";

        try {
            PreparedStatement stmt = (PreparedStatement) connection.prepareStatement(sql);
            stmt.setString(1, email);
            stmt.executeQuery();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return u;

    }

    public Usuario checkPassword(String email, String senha) {

        Usuario u = new Usuario();

        String sql = "SELECT nome, papel, status FROM usuario WHERE usuario.email = (?) and usuario.senha = (?)";

        try {
            pstm = connection.prepareStatement(sql);
            pstm.setString(1, email);
            pstm.setString(2, senha);

            pstm.execute();
            rs = pstm.getResultSet();
            
            while(rs.next()){
            u.setNome(rs.getString(1));
            u.setPapel(rs.getString(2));
            u.setStatus(rs.getString(3));
            }
            
            pstm.close();
            
            return u;
            
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

    }

}
