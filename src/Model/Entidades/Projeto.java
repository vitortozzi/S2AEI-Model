/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Entidades;

import java.util.ArrayList;

/**
 *
 * @author Vítor
 */
public class Projeto {
    
    private int id;
    private String titulo;
    private String descricao;
    private String lider;
    private String dataCriacao;
    private String ultimaModificacao;
    private String Status;
    private String[] membros;
    private String area;
    private String orientador;
    private ArrayList<String> perguntas;
    private ArrayList<String> respostas;
    private ArrayList<Double> notas;

    public ArrayList<Double> getNotas() {
        return notas;
    }

    public void setNotas(ArrayList<Double> notas) {
        this.notas = notas;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getLider() {
        return lider;
    }

    public void setLider(String lider) {
        this.lider = lider;
    }

    public String getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(String dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public String getUltimaModificacao() {
        return ultimaModificacao;
    }

    public void setUltimaModificacao(String ultimaModificacao) {
        this.ultimaModificacao = ultimaModificacao;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String Status) {
        this.Status = Status;
    }

    public String[] getMembros() {
        return membros;
    }

    public void setMembros(String[] membros) {
        this.membros = membros;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getOrientador() {
        return orientador;
    }

    public void setOrientador(String orientador) {
        this.orientador = orientador;
    }

    public ArrayList<String> getPerguntas() {
        return perguntas;
    }

    public void setPerguntas(ArrayList<String> perguntas) {
        this.perguntas = perguntas;
    }

    public ArrayList<String> getRespostas() {
        return respostas;
    }

    public void setRespostas(ArrayList<String> respostas) {
        this.respostas = respostas;
    }
}
