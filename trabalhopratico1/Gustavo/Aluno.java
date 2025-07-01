package org.teiacoltec.poo.tp1;

public class Aluno extends Pessoa {
    private String Matricula;
    private String Curso;

    public String getMatricula() {
        return Matricula;
    }
    public void setMatricula(String matricula) {
        this.Matricula = matricula;
    }
    public String getCurso() {
        return Curso;
    }
    public void setCurso(String curso) {
        this.Curso = curso;
    }

    public Aluno(String nome, String email, String endereco, String cpf, String nascimento, String matricula, String curso) {
    super(nome, nascimento, cpf, endereco, email);
    setMatricula(matricula);
    setCurso(curso);
}
}
