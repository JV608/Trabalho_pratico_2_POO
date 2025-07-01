package org.teiacoltec.poo.tp1;

import java.time.LocalDate;

public class Atividade {

    private int ID;
    private String nome;
    private String descricao;
    private LocalDate inicio;
    private LocalDate fim;

    Atividade(int ID, String nome, String descricao, LocalDate inicio, LocalDate fim){

        this.ID = ID;
        this.nome = nome;
        this.descricao = descricao;
        this.inicio = inicio;
        this.fim = fim;

    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDate getInicio() {
        return inicio;
    }

    public void setInicio(LocalDate inicio) {
        this.inicio = inicio;
    }

    public LocalDate getFim() {
        return fim;
    }

    public void setFim(LocalDate fim) {
        this.fim = fim;
    }

}