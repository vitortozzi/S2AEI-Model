/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Entidades;

import java.util.List;

/**
 *
 * @author Vítor
 */
public class Aluno extends Usuario {
    
    private String matricula;
    private String curso;
    private int periodo;
    private List<Projeto> projetosParticipa;
    private Projeto projetoLidera;

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public int getPeriodo() {
        return periodo;
    }

    public void setPeriodo(int periodo) {
        this.periodo = periodo;
    }

    public List<Projeto> getProjetosParticipa() {
        return projetosParticipa;
    }

    public void setProjetosParticipa(List<Projeto> projetosParticipa) {
        this.projetosParticipa = projetosParticipa;
    }

    public Projeto getProjetoLidera() {
        return projetoLidera;
    }

    public void setProjetoLidera(Projeto projetoLidera) {
        this.projetoLidera = projetoLidera;
    }
    
    
    
}
