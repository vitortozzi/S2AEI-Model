
package Model.Negocio;

import Model.Database.UsuarioDAO;

public class EnUsuario {
    
    private UsuarioDAO userDAO;
    
    public EnUsuario() {
        userDAO = new UsuarioDAO();
    }
    
    public String checkLogin(String userEmail, String userPass) {
        return userDAO.checkLogin(userEmail, userPass);
    }
}
