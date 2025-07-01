package  org.teiacoltec.poo.tp1;

public class Professor extends Pessoa{
    private String matricula;
    private String formacao;

    public Professor(String CPF, String nome, String nascimento, String email, String endereco, String matricula, String formacao){
        super(CPF, nome, nascimento, email, endereco);
        this.matricula = matricula;
        this.formacao = formacao;

       
    }

}