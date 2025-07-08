//copia de atividade
package org.teiacoltec.poo.tp2;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class Tarefa{
    private int ID;
    private String nome;
    private String descricao;
    private LocalDate inicio;
    private LocalDate fim;
    private static DateTimeFormatter modelo = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public Tarefa(int ID, String nome, String descricao, String inicio, String fim){
        this.ID = ID;
        this.nome = nome;
        this.descricao = descricao;
        this.inicio = LocalDate.parse(inicio, modelo);
        this.fim = LocalDate.parse(fim, modelo);
    }
    public int getID(){
        return ID;
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
        String informacoes = "Informacoes da atividade "+getnome()+":\nID: "+ getID() + "\nDescrição: "+getdescricao()+"\nInicio da atividade: "+getinicio()+"\nFim do Prazo: "+getfim();
        return informacoes;
    }
}
