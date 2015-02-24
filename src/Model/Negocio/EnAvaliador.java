
package Model.Negocio;

import Model.Database.AvaliadorDAO;
import Model.Entidades.Avaliador;
import java.util.ArrayList;

public class EnAvaliador {
    
    private AvaliadorDAO avaDAO;
    
    public EnAvaliador() {
        avaDAO = new AvaliadorDAO();
    }

    public String checkEmailExists(String email) {
        return avaDAO.checkEmailExists(email);
    }

    public boolean editAvaliador(Avaliador ava) {
        return avaDAO.editAvaliador(ava);
    }
    
    public boolean addAvaliador(Avaliador ava) {
        return avaDAO.addAvaliador(ava);
    }
    
    public Avaliador getAvaliador(String email) {
        return avaDAO.getAvaliador(email);
    }
    
    public ArrayList<Avaliador> getAvaliadores() {
        return avaDAO.getAvaliadores();
    }
    
    public ArrayList<Avaliador> getAvaliadoresAtivos() {
        return avaDAO.getAvaliadoresAtivos();
    }
    
    public ArrayList<Avaliador> getAvaliadoresProjeto(String projeto) {
        return avaDAO.getAvaliadoresProjeto(projeto);
    }
    
    public boolean deleteAvaliador(String email) {
        return avaDAO.deleteAvaliador(email);
    }
}
