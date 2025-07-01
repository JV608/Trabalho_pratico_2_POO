package  org.teiacoltec.poo.tp1;

public class Aluno extends Pessoa{
    private String matricula;
    private String curso;

    public Aluno(String CPF, String nome, String nascimento, String email, String endereco, String matricula, String curso){
        super(CPF, nome, nascimento, email, endereco);
        this.matricula = matricula;
        this.curso = curso;

        
    }
   
}