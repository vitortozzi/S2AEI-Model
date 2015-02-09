/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Tabelas;

import java.util.List;

/**
 *
 * @author Vítor
 */
public class Professor extends Usuario{
    
    private String departamento;
    private String disciplinaPrincipal;
    private List<Projeto> projetosOrientados;

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getDisciplinaPrincipal() {
        return disciplinaPrincipal;
    }

    public void setDisciplinaPrincipal(String disciplinaPrincipal) {
        this.disciplinaPrincipal = disciplinaPrincipal;
    }

    public List<Projeto> getProjetosOrientados() {
        return projetosOrientados;
    }

    public void setProjetosOrientados(List<Projeto> projetosOrientados) {
        this.projetosOrientados = projetosOrientados;
    }
    
    
    
}
