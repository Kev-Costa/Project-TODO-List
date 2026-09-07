package org.zgsolucoes.model;

import java.time.LocalDate;

public class Tarefa implements Comparable<Tarefa> {
    private String nome;
    private String descricao;
    private LocalDate dataTermino;
    private int prioridade;
    private String categoria;
    private Status status;


    public Tarefa(String nome, String descricao, LocalDate dataTermino, int prioridade, String categoria, Status status) {
        this.nome = nome;
        this.descricao = descricao;
        this.dataTermino = dataTermino;
        this.prioridade = prioridade;
        this.categoria = categoria;
        this.status = status;
    }

    public String getNome(){
        return this.nome;
    }
    public String getDescricao(){
        return this.descricao;
    }
    public LocalDate getDataTermino(){
        return this.dataTermino;
    }
    public int getPrioridade(){
        return this.prioridade;
    }
    public String getCategoria(){
        return this.categoria;
    }
    public Status getStatus(){
        return this.status;
    }

    public void setNome(String nome){
        this.nome = nome;
    }
    public void setDescricao(String descricao){
        this.descricao = descricao;
    }
    public void setDataTermino(LocalDate dataTermino){
        this.dataTermino = dataTermino;
    }
    public void setPrioridade(int prioridade){
        this.prioridade = prioridade;
    }
    public void setCategoria(String categoria){
        this.categoria = categoria;
    }
    public void setStatus(Status status){
        this.status = status;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s | Prioridade: %d | Categoria: %s | Termino: %s | Descriçao: %s",
                status, nome, prioridade, categoria, dataTermino, descricao);
    }

    @Override
    public int compareTo(Tarefa outraTarefa) {
        return Integer.compare(outraTarefa.getPrioridade(), this.prioridade);
    }
}



