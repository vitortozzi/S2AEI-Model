
package Model.Negocio;

import Model.Database.AlunoDAO;
import Model.Entidades.Aluno;
import java.util.ArrayList;

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
