/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Negocio;

import Utils.XMLParser;
import Model.Database.AlunoDAO;
import Model.Database.ProfessorDAO;
import Model.Database.ProjetoDAO;
import Model.Tabelas.Projeto;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jdom2.JDOMException;

/**
 *
 * @author Vítor
 */
public class EnProjeto {

    AlunoDAO daoAluno;
    ProjetoDAO daoProjeto;
    ProfessorDAO daoProfessor;

    public EnProjeto() {
        this.daoAluno = new AlunoDAO();
        this.daoProjeto = new ProjetoDAO();
        this.daoProfessor = new ProfessorDAO();
    }

    public boolean jaLideraProjeto(String nomeLider) {
        if (daoAluno.AlunosIsLeader(nomeLider)) {
            return true;
        }
        return false;
    }

    public boolean criaProjeto(Projeto projeto) {
        return daoProjeto.insertProjeto(projeto);
    }

    public Projeto getProjetoPorLider(String nome) {
        return daoProjeto.getProjetoPorLider(nome);
    }

    public boolean finalizaProjeto(int id) {
        return daoProjeto.finalizaProjeto(id);
    }

    public ArrayList<String> getRespostas(int id) {
        return daoProjeto.getRespostas(id);
    }
    
    public boolean checkProjetoStatus(Projeto projeto){
        return projeto.getStatus().equals("Novo") || projeto.getStatus().equals("Em preenchimento");
    }

    public boolean atualizaRespostas(Projeto projeto) {
        return daoProjeto.updateRespostas(projeto);
    }

    public ArrayList<Projeto> getProjetoPorEntidade(String tipoEntidade, String nome) {
        if (tipoEntidade.equals("Aluno")) {
            return daoProjeto.getProjetosParticipante(nome);
        } else if (tipoEntidade.equals("Professor")) {
            return daoProjeto.getProjetosOrientador(nome);
        }
        return null;
    }

    public Projeto getProjetoPorID(int id) {
        return daoProjeto.getProjetoPorID(id);
    }
}
