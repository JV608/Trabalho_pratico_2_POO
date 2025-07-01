package org.teiacoltec.poo.tp1;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

abstract class Pessoa{
    private String CPF;
    private String nome;
    private LocalDate nascimento;
    private String email;
    private String endereco;
    private static DateTimeFormatter modelo = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    Pessoa( String CPF, String nome, String nascimento, String email, String endereco){
        this.CPF = CPF;
        this.nome = nome;
        this.nascimento = LocalDate.parse(nascimento, modelo);
        this.email = email;
        this.endereco = endereco;
    }
    
    public String getCPF(){
        return CPF;
    }
    public String getnome(){
        return nome;
    }
    public LocalDate getnascimento(){
        return nascimento;
    }
    public String getemail(){
        return email;
    }
    public String getendereco(){
        return endereco;
    }

    public String obterInformacoes(){
        String informacoes = "Informacoes de "+getnome()+":\nCPF: "+ getCPF() + "\nNascimento: "+getnascimento()+"\nE-mail: "+getemail()+"\nEndereco: "+getendereco();
        return informacoes;
    }
}