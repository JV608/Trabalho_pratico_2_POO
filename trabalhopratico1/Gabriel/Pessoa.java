package org.teiacoltec.poo.tp1;
import java.time.LocalDate;

abstract public class Pessoa {

    private String CPF;
    private String nome;
    private LocalDate nascimento;
    private String email;
    private String endereco;
    
    Pessoa(String CPF, String nome, LocalDate nascimento, String email, String endereco){
    this.CPF = CPF;
    this.nome = nome;
    this.nascimento = nascimento;
    this.email = email;
    this.endereco = endereco;
    }
    
    String obterInformacoes(){
  
     return "CPF: " + CPF + "\n" +
            "Nome: " + nome + "\n" +
            "Nascimento: " + nascimento + "\n" +
            "Email: " + email + "\n" +
            "Endereço: " + endereco;

    }

    public String getCPF() {
        return CPF;
    }

    public void setCPF(String CPF) {
        this.CPF = CPF;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getNascimento() {
        return nascimento;
    }

    public void setNascimento(LocalDate nascimento) {
        this.nascimento = nascimento;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

}