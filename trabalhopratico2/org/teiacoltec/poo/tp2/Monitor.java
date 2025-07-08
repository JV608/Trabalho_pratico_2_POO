package org.teiacoltec.poo.tp2;

public class Monitor extends Pessoa {
    
    private String matricula;
    private String curso;
    
    public Monitor(String CPF, String nome, String nascimento, String email, String endereco, String matricula, String curso){
        super(CPF, nome, nascimento, email, endereco);
        this.matricula = matricula;
        this.curso = curso;

        
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String Curso) {
        this.curso = Curso;
    }

}
