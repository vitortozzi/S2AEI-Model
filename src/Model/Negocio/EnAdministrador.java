
package Model.Negocio;

import Model.Database.AdministradorDAO;
import Model.Tabelas.Administrador;
import java.util.ArrayList;

public class EnAdministrador {
    
    private AdministradorDAO admDAO;
    
    public EnAdministrador() {
        admDAO = new AdministradorDAO();
    }
    
    public String checkEmailExists(String email) {
        return admDAO.checkEmailExists(email);
    }
    
    public boolean addAdministrador(Administrador newAdm) {
        return admDAO.addAdministrador(newAdm);
    }
    
    public boolean editAdministrador(Administrador editAdm) {
        return admDAO.editAdministrador(editAdm);
    }
    
    public Administrador getAdministrador(String email) {
        return admDAO.getAdministrador(email);
    }
    
    public ArrayList<Administrador> getAdministradores() {
        return admDAO.getAdministradores();
    }
    
    public boolean deleteAdministrador(String email) {
        return admDAO.deleteAdministrador(email);
    }

}
