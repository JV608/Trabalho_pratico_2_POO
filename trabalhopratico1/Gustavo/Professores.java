package org.teiacoltec.poo.tp1;

public class Professores extends Pessoa {
    private String matricula;
    private String formacao;

     public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getFormacao() {
        return formacao;
    }

    public void setFormacao(String formacao) {
        this.formacao = formacao;
    }

    public Professores(String nome, String cpf, String nascimento, String endereco, String email, String matricula, String formacao) {
    super(nome, cpf, nascimento, endereco, email);
    this.matricula = matricula;
    this.formacao = formacao;
}
}

