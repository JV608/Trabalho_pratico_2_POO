package org.teiacoltec.poo.tp1;

public class Main {
    public static void main(String[] args) throws PessoaJaParticipanteException, PessoaNaoEncontradaException {
        
    //CRIANDO OBJETOS 

        Pessoa pessoa1 = new Pessoa("Carlos","10/10/2000","111.222.333-44","Rua C, 789","carlos@gmail.com");

        Turma turma1 = new Turma(1,"Turma de Matemática","Ensino médio","01/02/2025","30/11/2025",null,null,null);
        Turma turma2 = new Turma(2,"Turma de Português","Ensino médio","01/02/2025","30/11/2025",null,null,null);    
        Turma turma3 = new Turma(3,"Turma de História","Ensino médio","01/02/2025","30/11/2025",null,null,null);
        
        Aluno aluno1 = new Aluno("João","joao@gmail.com","Rua A, 123","123.456.789-00","01/01/2007","123456","Matemática");
        Aluno aluno2 = new Aluno("Lucas","321.654.987-00","02/02/2008","Rua E, 202","lucas@gmail.com","654321","Português");
        

        Professores professor1 = new Professores("Maria","15/03/1980","987.654.321-00","Rua B, 456","maria@gmail.com","PROF123","Matemática");
        Professores professor2 = new Professores("Pedro","20/07/1975","888.777.666-55","Rua F, 303","pedro@gmail.com","PROF456","Português");


        Atividade atividade1 = new Atividade(1, "Trabalho de Matemática", "Trabalho sobre funções", "01/03/2025", "15/03/2025");


        
        imprimirInformacoes(pessoa1);
        imprimirInformacoes(aluno1);
        imprimirInformacoes(professor1);
        imprimirInformacoes(turma1);
        imprimirInformacoes(atividade1);
        imprimirInformacoesParticipantes(turma1, turma1.getParticipantes());
        

        turma1.adicionarParticipante(professor1);
        turma3.adicionarParticipante(professor2);


        //Teste PessoaJaParticipanteException
        try {
            turma1.adicionarParticipante(aluno1);
            turma1.adicionarParticipante(aluno1);
        } catch (PessoaJaParticipanteException e) {
            System.out.println(e.getMessage());
        }


        //Teste PessoaNaoEncontradaException
        try {
            turma1.removerParticipante(aluno2);
        } catch (PessoaNaoEncontradaException e) {
            System.out.println(e.getMessage());
        }



       // Testando participa
        System.out.println("\n-------------------\n"+aluno2.getNome()+" participa da turma? " + turma1.participa(aluno2)+"\n-------------------\n"); 
        turma1.adicionarParticipante(aluno2);
        System.out.println("\n-------------------\n"+aluno2.getNome()+" participa da turma? " + turma1.participa(aluno2)+"\n-------------------\n"); 

        
        // Testando associaSubturma
        turma1.associaSubturma(turma2);

        //Teste Turmas filhas
        imprimirTurmasFilhas(turma1);
        
    }



    private static void imprimirInformacoes(Pessoa pessoa) {
        System.out.println("Informações Pessoais\n\n"+pessoa.obterInformacoes() 
        +"\n-------------------\n");
    }
    public static void imprimirInformacoes(Turma turma) {
        System.out.println("Informações das Turmas\n\n"+turma.obterInformacoes()
        + "\n-------------------\n");
    }
    public static void imprimirInformacoes(Atividade atividade) {
        System.out.println("Informações das Atividades\n\n"+atividade.obterInformacoes()
        +"\n-------------------\n");
    }

    public static void imprimirInformacoesParticipantes(Turma turma, Pessoa[] participantes) {
        System.out.println("\n-------------------\n"+"Participantes na turma\n"+turma.obtemListaParticipantes()
        +"\n-------------------\n");
    }

    public static void imprimirTurmasFilhas(Turma turma) {
    System.out.println("\n-------------------\n"+"Turmas filhas de " + turma.getNome() + ":");
    Turma[] filhas = turma.getTurmasFilhas();
    if (filhas != null && filhas.length > 0) {
        for (Turma t : filhas) {
            if (t != null) {
                System.out.println("- " + t.getNome()+"\n-------------------\n");
            }
        }
    } else {
        System.out.println("Nenhuma subturma associada.");
    }
}
}