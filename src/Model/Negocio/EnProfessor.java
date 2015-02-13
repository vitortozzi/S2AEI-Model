/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Negocio;

import Model.Database.ProfessorDAO;
import Model.Tabelas.Professor;
import java.util.ArrayList;

/**
 *
 * @author Vítor
 */
public class EnProfessor {
    
    ProfessorDAO daoProfessor;
    
    public EnProfessor(){
        daoProfessor = new ProfessorDAO();
    }
    
    public ArrayList<Professor> getProfessoresAtivos(){
        return daoProfessor.getProfessoresAtivos();
    }
    
}
