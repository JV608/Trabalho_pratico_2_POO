package org.teiacoltec.poo.tp1;

public class Atividade {
    private int id;
    private String nome;
    private String descricao;
    private String inicio;
    private String fim;
    
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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getInicio() {
        return inicio;
    }

    public void setInicio(String inicio) {
        this.inicio = inicio;
    }

    public String getFim() {
        return fim;
    }

    public void setFim(String fim) {
        this.fim = fim;
    }

     public Atividade(int id, String titulo, String descricao, String inicio, String fim) {
        this.id = id;
        this.nome = titulo;
        this.descricao = descricao;
        this.inicio = inicio;
        this.fim = fim;
    }
    
    public String obterInformacoes() {
    return "Atividade: " + nome + "\n" +
           "Descrição: " + descricao + "\n" +
           "Início: " + inicio + "\n" +
           "Fim: " + fim + "\n" +
           "ID: " + id;
    }

}

