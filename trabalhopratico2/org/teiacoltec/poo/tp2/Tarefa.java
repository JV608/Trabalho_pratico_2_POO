//copia de atividade
package org.teiacoltec.poo.tp2;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class Tarefa{
    private int id;
    private String nome;
    private String descricao;
    private float nota;
    private LocalDate inicio;
    private LocalDate fim;
    

    public Tarefa(int id, String nome, String descricao, String inicio, String fim){
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.inicio = LocalDate.parse(inicio, modelo);
        this.fim = LocalDate.parse(fim, modelo);
    }
    public int getId(){
        return id;
    }
    public String getnome(){
        return nome;
    }
    public String getdescricao(){
        return descricao;
    }
    public LocalDate getinicio(){
        return inicio;
    }
    public LocalDate getfim(){
        return fim;
    }
    public String obterInformacoes(){
        String informacoes = "Informacoes da atividade "+getnome()+":\nID: "+ getId() + "\nDescrição: "+getdescricao()+"\nInicio da atividade: "+getinicio()+"\nFim do Prazo: "+getfim();
        return informacoes;
    }
    public int obterTarefaPorID(){
        return getId();
    }
}
