package com.example.poo_projeto_ac2.model;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Escola {
    private int id;
    private String nome;
    private String diretor;
    private String endereco;
    
    @JsonIgnore
    private boolean temCurso;

    @JsonIgnore
    private ArrayList<Curso> cursos = new ArrayList<Curso>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDiretor() {
        return diretor;
    }

    public void setDiretor(String diretor) {
        this.diretor = diretor;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public ArrayList<Curso> getCursos() {
        return cursos;
    }

    public void setCursos(ArrayList<Curso> cursos) {
        this.cursos = cursos;
    }

    public boolean addCurso(Curso curso){
        return cursos.add(curso);
    }

    public boolean removeCurso(Curso curso){
        return cursos.remove(curso);
    }

    public boolean isTemCurso() {
        return temCurso;
    }

    public void setTemCurso(boolean temCurso) {
        this.temCurso = temCurso;
    }
    
}