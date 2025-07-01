package org.teiacoltec.poo.tp1;


public class Pessoa {
    private String nome;
    private String nascimento;
    private String cpf;
    private String endereco;
    private String email;

    public String getNome() {
        return nome;
    }
    public String  getNascimento() {
        return nascimento;
    }

    public String getCpf() {
        return cpf;
    }

    public String getEndereco() {
        return endereco;
    }

    public String getEmail() {
        return email;
    }

    
    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setNascimento(String nascimento) {
        this.nascimento = nascimento;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Pessoa(String nome, String nascimento, String cpf, String endereco, String email) {
    this.nome = nome;
    this.nascimento = nascimento;
    this.cpf = cpf;
    this.endereco = endereco;
    this.email = email;
}

    public String obterInformacoes() {
        return "Nome: " + nome + "\n" +
               "Data de Nascimento: " + nascimento + "\n" +
               "CPF: " + cpf + "\n" +
               "Endereço: " + endereco + "\n" +
               "Email: " + email;
    }
}