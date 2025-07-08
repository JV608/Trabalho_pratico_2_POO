package org.teiacoltec.poo.tp2;

public class Aluno extends Pessoa {
    
    private String matricula;
    private String curso;
    
    public Aluno(String CPF, String nome, String nascimento, String email, String endereco, String matricula, String curso){
        super(CPF, nome, nascimento, email, endereco);
        this.matricula = matricula;
        this.curso = curso;

        
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String Matricula) {
        this.matricula = Matricula;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String Curso) {
        this.curso = Curso;
    }

}
