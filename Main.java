package  org.teiacoltec.poo.tp1;

public class Main {
    public static void main(String[] args) {
        Professor P = new Professor("111.222.333-44", "Einsteim", "14/03/1879", "albert@teiacoltec.org", "rua 1,washington, DC", "a2025950001", "Física aplicada");
        imprimirInformacoes(P);

        Atividade At1 = new Atividade(111,"Liçao de filosofia","O aluno deve descobrir o sentido da vida","18/05/2025","19/05/2025");
        imprimirInformacoes(At1);

        Aluno[] alunos = new Aluno[5];
        alunos[0] = new Aluno("123.456.789-00", "joao", "01/02/2008", "0000@teiacoltec.org", "Rua 1, Belo Horizonte", "2025950000", "Desenvolvimento de sistemas");
        alunos[1] = new Aluno("234.567.890-01", "julia", "02/02/2008", "0001@teiacoltec.org", "Rua 2, Belo Horizonte", "2025950001", "Desenvolvimento de sistemas");
        alunos[2] = new Aluno("345.678.900-12", "jonas", "03/02/2008", "0002@teiacoltec.org", "Rua 3, Belo Horizonte", "2025950002", "Desenvolvimento de sistemas");
        alunos[3] = new Aluno("456.789.001-23", "joana", "04/02/2008", "0003@teiacoltec.org", "Rua 4, Belo Horizonte", "2025950003", "Desenvolvimento de sistemas");
        alunos[4] = new Aluno("567.890.012-34", "junior", "05/02/2008", "0004@teiacoltec.org", "Rua 5, Belo Horizonte", "2025950004", "Desenvolvimento de sistemas");
        
        Aluno[] alunosA = new Aluno[3];
        for(int i = 0; i < 3; i++) {
            alunosA[i] = alunos[i];
        }

        Aluno[] alunosB = new Aluno[2];
        for (int i = 0; i < 2; i++) {
            alunosB[i] = alunos[i + 3];
        }

        Turma t = new Turma(111, "terceirão", "terceiro ano de informatica", "01/02/2025", "01/12/2025", alunos, null);
        Turma subA = new Turma(222, "terceiro A", "Apoiam a china", "01/02/2024", "01/12/2025", alunosA, t);
        Turma subB = new Turma(333, "terceiro B", "Apoiam Taiwan", "01/02/2024", "01/12/2025" , alunosB, t);

        imprimirInformacoes(t);
        Pessoa[] chamadasubA = subA.obtemListaParticipantes();
        System.out.println("Chamada Sub A:");
        System.out.println("Total de alunos: " + chamadasubA.length);
        for(int i = 0; i < chamadasubA.length; i++){
            if(chamadasubA[i] != null){
                System.out.println("Aluno " + i +": " + chamadasubA[i].getnome());
            }
}
        Aluno aluno1 = new Aluno("000.456.777-00", "lucas", "01/02/2008", "0000@teiacoltec.org", "Rua 1, Belo Horizonte", "2025950000", "Desenvolvimento de sistemas");
        Aluno naoaluno = new Aluno("000.111.999-88", "ivone", "01/02/2008", "0000@teiacoltec.org", "Rua 1, Belo Horizonte", "2025950000", "Desenvolvimento de sistemas");
        
        try {
            t.adicionarParticipante(aluno1);
            
            if (t.participa(aluno1)) {
                System.out.println(aluno1.getnome() + " esta na turma");
            }else{
                System.out.println(aluno1.getnome() + " não esta na turma");
            }
            if (t.participa(naoaluno)) {
                System.out.println(naoaluno.getnome() + " esta na turma");
            }else{
                System.out.println(naoaluno.getnome() + " não esta na turma");
            }
            
            t.removerParticipante(alunos[0]);
            if (t.participa(alunos[0])) {
                System.out.println(alunos[0].getnome() + " esta na turma");
            }else{
                System.out.println( "aluno não esta na turma");
            }
            t.removerParticipante(aluno1);
            t.adicionarParticipante(alunos[0]);
        } catch (PessoaJaParticipanteException | PessoaNaoEncontradaException e) {
            throw new RuntimeException(e);
        }
    
    }   
   public static void imprimirInformacoes(Pessoa pessoa){
        String info = pessoa.obterInformacoes();
        System.out.println(info);
    }
    public static void imprimirInformacoes(Turma turma){
        String info = turma.obterInformacoes();
        System.out.println(info);
    }
    public static void imprimirInformacoes(Atividade atividade){
        String info = atividade.obterInformacoes();
        System.out.println(info);
    }
}
    
   
