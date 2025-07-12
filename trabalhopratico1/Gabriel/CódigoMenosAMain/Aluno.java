package org.teiacoltec.poo.tp2;

import java.time.LocalDate;

public class Aluno extends Pessoa {
    
    private String Matricula;
    private String Curso;
    
    Aluno(String CPF, String nome, LocalDate nascimento, String email, String endereco, String Matricula, String Curso){

        super(CPF, nome, nascimento, email, endereco);
        this.Matricula = Matricula;
        this.Curso = Curso;

    }

    public String getMatricula() {
        return Matricula;
    }

    public void setMatricula(String Matricula) {
        this.Matricula = Matricula;
    }

    public String getCurso() {
        return Curso;
    }

    public void setCurso(String Curso) {
        this.Curso = Curso;
    }

}