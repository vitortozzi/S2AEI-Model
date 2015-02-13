/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Negocio;

import Model.Database.AlunoDAO;
import Model.Tabelas.Aluno;
import java.util.ArrayList;

/**
 *
 * @author Vítor
 */
public class EnAluno {
    
    AlunoDAO daoAluno;

    public EnAluno() {
        this.daoAluno = new AlunoDAO();
    }
    
    public boolean adicionaAluno(Aluno aluno){       
        return daoAluno.addAluno(aluno);       
    }
    
    public ArrayList<Aluno> getAlunosAtivos(){        
        return daoAluno.getAlunosAtivos();
    }
    
    public boolean deletaAluno(String email){
        return daoAluno.deleteAluno(email);
    }
    
    public boolean atualizaAluno(Aluno aluno){
        return daoAluno.updateAluno(aluno);
    }
    
    public ArrayList<Aluno> getAlunos(){
        return daoAluno.getAlunos();
    }
    
    public Aluno getAluno(String email){
        return daoAluno.getAluno(email);
    }
}
