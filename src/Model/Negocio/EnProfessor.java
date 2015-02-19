
package Model.Negocio;

import Model.Database.ProfessorDAO;
import Model.Tabelas.Professor;
import java.util.ArrayList;

public class EnProfessor {
    
    ProfessorDAO daoProfessor;
    
    public EnProfessor(){
        daoProfessor = new ProfessorDAO();
    }
    
    public String checkEmailExists(String mail) {
        return daoProfessor.checkEmailExists(mail);
    }
    
    public boolean addProfessor(Professor p) {
        return daoProfessor.addProfessor(p);
    } 
    
    public boolean editProfessor(Professor p) {
        return daoProfessor.editProfessor(p);
    }
    
    public Professor getProfessor(String email) {
        return daoProfessor.getProfessor(email);
    }
     
    public ArrayList<Professor> getProfessores() {
        return daoProfessor.getProfessores();
    }
    
    public ArrayList<Professor> getProfessoresAtivos(){
        return daoProfessor.getProfessoresAtivos();
    }

    public boolean deleteProfessor(String email) {
        return daoProfessor.deleteProfessor(email);
    }
    
}
