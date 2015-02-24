
package Model.Negocio;

import Model.Database.AlunoDAO;
import Model.Database.ProfessorDAO;
import Model.Database.ProjetoDAO;
import Model.Entidades.Avaliador;
import Model.Entidades.Projeto;
import java.util.ArrayList;

public class EnProjeto {

    private AlunoDAO daoAluno;
    private ProjetoDAO daoProjeto;
    private ProfessorDAO daoProfessor;

    public EnProjeto() {
        this.daoAluno = new AlunoDAO();
        this.daoProjeto = new ProjetoDAO();
        this.daoProfessor = new ProfessorDAO();
    }

    public boolean alteraStatusProjetoAprovado(int idProjeto, String novoStatus) {
        return daoProjeto.alteraStatusProjetoAprovado(idProjeto, novoStatus);
    }
    
    public boolean addComentario(int idProjeto, int idPergunta, String comentario) {
        return daoProjeto.addComentario(idProjeto, idPergunta, comentario);
    }
    
    public ArrayList<String> getComentarios(int idProjeto) {
        return daoProjeto.getComentarios(idProjeto);
    }
    
    public boolean jaLideraProjeto(String nomeLider) {
        if (daoAluno.AlunosIsLeader(nomeLider)) {
            return true;
        }
        return false;
    }

    public boolean setAvaliadorProjeto(Avaliador ava, Projeto p) {
        return daoProjeto.setAvaliadorProjeto(ava, p);
    }
    
    public boolean deleteAvaliadorProjeto(Projeto p, String nomeAva) {
        return daoProjeto.deleteAvaliadorProjeto(p, nomeAva);
    }
    
    public boolean criaProjeto(Projeto projeto) {
        return daoProjeto.insertProjeto(projeto);
    }

    public ArrayList<Projeto> getProjetos() {
        return daoProjeto.getProjetos();
    }
    
    public Projeto getProjetoPorLider(String nome) {
        return daoProjeto.getProjetoPorLider(nome);
    }

    public ArrayList<Projeto> getProjetosAvaliador(String email) {
        return daoProjeto.getProjetosAvaliador(email);
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
    
    public boolean setNotas(int idPergunta, String emailAvaliador, int idProjeto, double nota) {
        return daoProjeto.setNotas(idPergunta, emailAvaliador, idProjeto, nota);
    }
    
    public ArrayList<Double> getNotasProjetoPorAvaliador(int idProjeto, String emailAvaliador) {
        return daoProjeto.getNotasProjetoPorAvaliador(idProjeto, emailAvaliador);
    }
    
    public boolean checkFinalizarProjeto(int idProjeto) {
        return daoProjeto.checkFinalizarProjeto(idProjeto);
    }
    
    public int countAvaliadoresProjeto(int idProjeto) {
        return daoProjeto.countAvaliadoresProjeto(idProjeto);
    }

    public boolean finalizaAndCalculaNota(int idProjeto) {
        return daoProjeto.FinalizaAndCalculaNota(idProjeto);
    }
    
}
