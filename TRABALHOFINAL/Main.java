package org.teiacoltec.poo.tp2;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList; 
import java.util.List;
import java.util.Scanner; 

public class Main {

    private static List<Turma> listaDeTurmas = new ArrayList<>();
    private static List<Pessoa> listaDePessoas = new ArrayList<>();
    private static List<Atividade> listaDeAtividades = new ArrayList<>();
    private static List<Tarefa> listaDeTarefas = new ArrayList<>();
    private static int proximoIdPessoa = 1;
    private static int proximoIdTarefa = 1;
    private static int proximoIdAtividade = 1;
    private static int proximoIdTurma = 1;
    private static DateTimeFormatter formatadorDeData = DateTimeFormatter.ofPattern("dd/MM/yyyy");

     private static final String DADOS_FILE = "dados_sistema.ser";

    public static void main(String[] args) {
        carregarDados();

        Scanner scanner = new Scanner(System.in);
        int opcaoEscolhida = -1; 

        while (opcaoEscolhida != 0) {
            exibirMenuPrincipal(); 
            try {
                opcaoEscolhida = Integer.parseInt(scanner.nextLine());

                switch (opcaoEscolhida) {
                    case 1:
                        criarTarefaParaTurma(scanner);
                        break;
                    case 2:
                        adicionarPessoaNaTurma(scanner);
                        break;
                    case 3:
                        listarParticipantesDaTurma(scanner);
                        break;
                     case 4:
                        registrarNotaEmTarefa(scanner);
                        break;
                    case 5:
                        consultarTarefasDoAluno(scanner);
                        break;
                    case 6:
                        gerenciarAtividadesDaTurma(scanner);
                        break;
                    case 0:
                        System.out.println("Obrigado! Volte sempre!");
                        break;
                    default:
                        System.out.println("Opção inválida. Escolha um número do menu.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Erro: Por favor, digite apenas números.");
            } catch (Exception e) {    
                System.out.println("Algo deu errado: " + e.getMessage());
            } finally {
                salvarDados();
            }
        }
       
        scanner.close(); 
    }

    //Salva o estado atual das listas e contadores em um arquivo.

     private static void salvarDados() {
        try (ObjectOutputStream sal = new ObjectOutputStream(new FileOutputStream(DADOS_FILE))) {
            sal.writeObject(listaDeTurmas);
            sal.writeObject(listaDePessoas);
            sal.writeObject(listaDeAtividades);
            sal.writeObject(listaDeTarefas);
            sal.writeInt(proximoIdPessoa);
            sal.writeInt(proximoIdAtividade);
            sal.writeInt(proximoIdTurma);
            sal.writeInt(proximoIdTarefa);
            System.out.println("Dados salvos com sucesso!");
        } catch (IOException e) {
            System.out.println("Erro ao salvar dados: " + e.getMessage());
        }
    }


    //Carrega o estado das listas e contadores de um arquivo.
    //Se o arquivo não existir ou houver erro, gera dados iniciais.

    private static void carregarDados() {
        try (ObjectInputStream car = new ObjectInputStream(new FileInputStream(DADOS_FILE))) {
            listaDeTurmas = (List<Turma>) car.readObject();
            listaDePessoas = (List<Pessoa>) car.readObject();
            listaDeAtividades = (List<Atividade>) car.readObject();
            listaDeTarefas = (List<Tarefa>) car.readObject();
            proximoIdPessoa = car.readInt();
            proximoIdAtividade = car.readInt();
            proximoIdTurma = car.readInt();
            proximoIdTarefa = car.readInt();
            System.out.println("Dados carregados com sucesso!");
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo de dados não encontrado. Iniciando com dados iniciais.");
            GeraDadosIniciais();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Erro ao carregar dados: " + e.getMessage());
            e.printStackTrace(); 
            System.out.println("Carregando dados iniciais.");
            GeraDadosIniciais();
        }
    }


    private static void exibirMenuPrincipal() {
        System.out.println("\n===== BEM-VINDO AO SISTEMA DA ESCOLA =====");
        System.out.println("1. Criar e associar uma nova tarefa para uma turma");
        System.out.println("2. Adicionar uma pessoa a uma turma");
        System.out.println("3. Ver quem está em uma turma");
        System.out.println("4. Registrar nota em uma tarefa");
        System.out.println("5. Consultar tarefas e notas de um aluno");
        System.out.println("6. Gerenciar atividades da turma");
        System.out.println("0. Sair do Sistema");
        System.out.print("Digite sua opção: ");
    }

    private static void gerenciarAtividadesDaTurma(Scanner scanner) throws AtividadeJaAssociadaATurmaException, AtividadeNaoAssociadaATurmaException { // Adicionado throws
        System.out.println("\n--- Gerenciar Atividades da Turma ---");
        System.out.print("Qual o ID da turma? ");
        int idTurma = Integer.parseInt(scanner.nextLine());

        Turma turma = buscarTurmaPorId(idTurma);
        if (turma == null) {
            System.out.println("Erro: Turma não encontrada.");
            return;
        }

        System.out.println("\n--- Opções de Atividades para Turma " + turma.getNome() + " ---");
        System.out.println("1. Listar atividades da turma");
        System.out.println("2. Associar atividade existente à turma");
        System.out.println("3. Desassociar atividade da turma");
        System.out.print("Escolha uma opção: ");
        int opcao = Integer.parseInt(scanner.nextLine());

        switch (opcao) {
            case 1:
                listarAtividadesDaTurma(turma, scanner);
                break;
            case 2:
                associarAtividadeExistente(turma, scanner);
                break;
            case 3:
                desassociarAtividade(turma, scanner);
                break;
            default:
                System.out.println("Opção inválida.");
        }
    }


    private static void criarTarefaParaTurma(Scanner scanner) throws AtividadeJaAssociadaATurmaException, AtividadeNaoPertenceATurmaException {
        System.out.println("\n--- Criar Nova Tarefa ---");
        System.out.print("ID da turma? ");
        int idTurma = Integer.parseInt(scanner.nextLine());

        Turma turma = buscarTurmaPorId(idTurma);
        if (turma == null) {
            System.out.println("Erro: Não há nenhuma turma com o ID :" + idTurma);
            return;
        }

        System.out.print("Nome da Atividade: ");
        String nomeAtividade = scanner.nextLine();
        System.out.print("Descrição: ");
        String descAtividade = scanner.nextLine();
        System.out.print("Data de Início: ");
        String inicioStr = scanner.nextLine();
        LocalDate inicioAtividade = LocalDate.parse(inicioStr, formatadorDeData);
        System.out.print("Data de Fim: ");
        String fimStr = scanner.nextLine();
        LocalDate fimAtividade = LocalDate.parse(fimStr, formatadorDeData);
        System.out.print("Valor (pontos): ");
        float valorAtividade = Float.parseFloat(scanner.nextLine());

        // Verifica se a atividade já existe.
        Atividade novaAtividade = new Atividade(listaDeAtividades.size() + 1, nomeAtividade, descAtividade, inicioAtividade, fimAtividade, valorAtividade); 
        listaDeAtividades.add(novaAtividade);

        // Adicionando verificação para AtividadeJaAssociadaATurmaException
        if (turma.getAtividades().contains(novaAtividade)) {
            throw new AtividadeJaAssociadaATurmaException(); 
        }
        turma.associaAtividade(novaAtividade);
        System.out.println("Atividade " + nomeAtividade + " foi associada à turma.");

        ArrayList<Aluno> alunosDaTurma = turma.obtemListaAlunos(true);
        if (alunosDaTurma.isEmpty()) {
            System.out.println("Aviso: Esta turma não tem alunos. Nenhuma tarefa foi criada.");
            return;
        }

        for (Aluno aluno : alunosDaTurma) {
            // Verificação para TarefaJaCriadaException.
            boolean tarefaJaExiste = false;
            for (Tarefa tarefa : listaDeTarefas) { 
                if (tarefa.getAluno().getCPF().equals(aluno.getCPF()) && tarefa.getAtividade().getID() == novaAtividade.getID()) { //,,
                    tarefaJaExiste = true;
                    System.out.println("Aviso: Tarefa para o aluno " + aluno.getNome() + " e atividade '" + novaAtividade.getNome() + "' já existe. Pulando."); //,
                    break;
                }
            }
            if (!tarefaJaExiste) {
                Tarefa novaTarefa = new Tarefa(proximoIdTarefa++, aluno, turma, novaAtividade, 0); // Nota inicial 0
                listaDeTarefas.add(novaTarefa); //
            }
        }

        System.out.println(alunosDaTurma.size() + " tarefas foram criadas para os alunos.");
    }


////////////


    private static void listarAtividadesDaTurma(Turma turma, Scanner scanner) {
        try {
            System.out.print("Deseja incluir atividades de subturmas? (S/N): ");
            String resposta = scanner.nextLine();
            boolean completa;

            if (resposta.equals("S")) {
                completa = true;
            } else {
                completa = false;
            }

            ArrayList<Atividade> atividadesDaTurma;

            atividadesDaTurma = turma.obtemAtividadesDaTurma(completa);

            if (atividadesDaTurma.isEmpty()) {
                System.out.println("Nenhuma atividade encontrada para esta turma " + (completa ? "e suas subturmas" : "") + ".");
                return;
            }

            System.out.println("\n----- Atividades da Turma " + turma.getNome() + " -----");
            for (Atividade atividade : atividadesDaTurma) {
                imprimirInformacoes(atividade);
                System.out.println("---");
            }
        } catch (Exception e) {
            System.out.println("Ocorreu um erro ao listar as atividades da turma: " + e.getMessage());
        }
    }



////////////

    
    private static void associarAtividadeExistente(Turma turma, Scanner scanner) throws AtividadeJaAssociadaATurmaException {
        System.out.println("\n--- Associar Atividade Existente ---");
        if (listaDeAtividades.isEmpty()) {
            System.out.println("Não há atividades cadastradas no sistema para associar.");
            return;
        }

        System.out.println("\nAtividades disponíveis:");
        for (Atividade atividade : listaDeAtividades) {
            System.out.println("ID: " + atividade.getID() + " , Nome: " + atividade.getNome());
        }

        System.out.print("Digite o ID da atividade que deseja associar: ");
        int idAtividade = Integer.parseInt(scanner.nextLine());

        Atividade atividadeParaAssociar = Atividade.obtemAtividadePorID(idAtividade, (ArrayList<Atividade>) listaDeAtividades);

        if (atividadeParaAssociar == null) {
            System.out.println("Atividade com ID " + idAtividade + " não encontrada.");
            return;
        }
        // lança AtividadeJaAssociadaATurmaException se a atividade já estiver associada.
        turma.associaAtividade(atividadeParaAssociar);
        System.out.println("Atividade " + atividadeParaAssociar.getNome() + " associada à turma " + turma.getNome() + " com sucesso!");
    }


////////////


    private static void registrarNotaEmTarefa(Scanner scanner) {
        System.out.println("\n--- Registrar Nota em Tarefa ---");
        System.out.print("Qual o CPF do aluno? ");
        String cpfAluno = scanner.nextLine();
        Pessoa pessoa = buscarPessoaPorCpf(cpfAluno);

        if (!(pessoa instanceof Aluno)) {
            System.out.println("Erro: Pessoa não encontrada ou não é um aluno.");
            return;
        }
        Aluno aluno = (Aluno) pessoa;

        ArrayList<Tarefa> tarefasDoAluno = Tarefa.obtemTarefasDaPessoa(aluno, (ArrayList<Tarefa>) listaDeTarefas);
        if (tarefasDoAluno.isEmpty()) {
            System.out.println("Este aluno não possui tarefas registradas.");
            return;
        }

        System.out.println("\nTarefas de " + aluno.getNome() + ":");
        for (int i = 0; i < tarefasDoAluno.size(); i++) {
            Tarefa tarefa = tarefasDoAluno.get(i);
            System.out.println("ID da Tarefa: " + tarefa.getID() +
            "\nAtividade: " + tarefa.getAtividade().getNome() +
            "\nTurma: " + tarefa.getTurma().getNome() +
            "\nNota Atual: " + tarefa.getNota() + " / " + tarefa.getAtividade().getValor());
        }

        System.out.print("Digite o ID da tarefa para registrar a nota: ");
        int idTarefa = Integer.parseInt(scanner.nextLine());

        Tarefa tarefaSelecionada = Tarefa.obtemTarefaPorID(idTarefa, tarefasDoAluno);

        if (tarefaSelecionada == null) {
            System.out.println("Tarefa não encontrada para este aluno.");
            return;
        }

        System.out.print("Digite a nova nota para a tarefa " + tarefaSelecionada.getAtividade().getNome() + " (máx.: " + tarefaSelecionada.getAtividade().getValor() + "): ");
        float novaNota = Float.parseFloat(scanner.nextLine());

        if (novaNota < 0 || novaNota > tarefaSelecionada.getAtividade().getValor()) {
            System.out.println("Erro: A nota deve estar entre 0 e o valor máximo.");
            return;
        }

        tarefaSelecionada.setNota(novaNota);
        System.out.println("Nota " + novaNota + " registrada com sucesso para a tarefa ID " + tarefaSelecionada.getID() + ".");
    }


////////////


    private static void consultarTarefasDoAluno(Scanner scanner) {
        System.out.println("\n--- Receita: Consultar Tarefas de um Aluno ---");
        System.out.print("Qual o CPF do aluno? ");
        String cpfAluno = scanner.nextLine();

        Pessoa pessoa = buscarPessoaPorCpf(cpfAluno);
        if (!(pessoa instanceof Aluno)) {
            System.out.println("Erro: Pessoa não é um aluno ou não encontrada.");
            return;
        }
       
        Aluno aluno = (Aluno) pessoa;
        ArrayList<Tarefa> tarefasDoAluno;
        tarefasDoAluno = Tarefa.obtemTarefasDaPessoa(aluno, (ArrayList<Tarefa>) listaDeTarefas);

        if (tarefasDoAluno.isEmpty()) {
            System.out.println("Nenhuma tarefa encontrada para este aluno.");
            return;
        }

        System.out.println("\n----- Tarefas de " + aluno.getNome() + " -----");
        for (Tarefa tarefa : tarefasDoAluno) {
            imprimirInformacoes(tarefa);
            System.out.println("---");
        }
    }



////////////


    private static void adicionarPessoaNaTurma(Scanner scanner) {
         System.out.println("\n--- Matricular Pessoa na Turma ---");
         System.out.print("Qual o CPF da pessoa? ");
         String cpf = scanner.nextLine();
         System.out.print("Qual o ID da turma? ");
         int idTurma = Integer.parseInt(scanner.nextLine());

         try {
             Pessoa pessoa = buscarPessoaPorCpf(cpf);
             Turma turma = buscarTurmaPorId(idTurma);

             if (pessoa == null || turma == null) {
                 System.out.println("Erro: Pessoa ou Turma não encontrada. Verifique o CPF e o ID.");
                 return;
             }
            
             turma.adicionarParticipante(pessoa);
             System.out.println(pessoa.getNome() + " foi adicionado(a) à turma " + turma.getNome() + ".");

         } catch (PessoaJaParticipanteException e) {
              System.out.println("Erro: Essa pessoa já está nessa turma!");
         } catch (Exception e) {
             System.out.println("Erro ao adicionar: " + e.getMessage());
         }
    }


////////////

    private static void listarParticipantesDaTurma(Scanner scanner) {
        System.out.println("\n--- Ver a lista de participantes da Turma ---");
        System.out.print("Qual o ID da Turma? ");
        int idTurma = Integer.parseInt(scanner.nextLine());
        
        Turma turma = buscarTurmaPorId(idTurma);/////////////
        if (turma == null) {
            System.out.println("Erro: Turma não encontrada.");
            return;
        }

        ArrayList<Pessoa> participantes = turma.obtemListaParticipantes();
        if (participantes.isEmpty()) {
            System.out.println("A turma " + turma.getNome() + " está vazia.");
            return;
        }
        
        System.out.println("----- Participantes da Turma: " + turma.getNome() + " -----");
        String tipo = "Desconhecido";
        for(Pessoa pessoa : participantes){
           if (pessoa instanceof Aluno) {
                 tipo = "Aluno";
            } else if (pessoa instanceof Professor) {
                 tipo = "Professor";
            } else if (pessoa instanceof Monitor) { // Adicionado para cobrir o Monitor
                 tipo = "Monitor";
            }
            System.out.println("- " + pessoa.getNome() + " (Cargo: " + tipo + ")");
        }
    }
    
////////////

      private static void desassociarAtividade(Turma turma, Scanner scanner) throws AtividadeNaoAssociadaATurmaException {
        System.out.println("\n--- Desassociar Atividade ---");
        ArrayList<Atividade> atividadesAtuais = turma.getAtividades();
        if (atividadesAtuais.isEmpty()) {
            System.out.println("Esta turma não possui atividades associadas.");
            return;
        }

        System.out.println("\nAtividades associadas à turma " + turma.getNome() + ":");
        for (Atividade atividade : atividadesAtuais) {
            System.out.println("ID: " + atividade.getID() + " , Nome: " + atividade.getNome());
        }

        System.out.print("Digite o ID da atividade que você deseja desassociar: ");
        int idAtividade = Integer.parseInt(scanner.nextLine());

        Atividade atividadeParaDesassociar = Atividade.obtemAtividadePorID(idAtividade, atividadesAtuais); //

        if (atividadeParaDesassociar == null) {
            throw new AtividadeNaoAssociadaATurmaException();
        }

        turma.desassociaAtividade(atividadeParaDesassociar);
        System.out.println("Atividade " + atividadeParaDesassociar.getNome() + " desassociada da turma " + turma.getNome() + " com sucesso!");
    }

////////////// MÉTODOS ADICIONAIS

    private static Turma buscarTurmaPorId(int id) {
        for (Turma turma : listaDeTurmas) { //
            if (turma.getID() == id) { //
                return turma;
            }
        }
        return null;
    }

    private static Pessoa buscarPessoaPorCpf(String cpf) {
        for (Pessoa pessoa : listaDePessoas) { //
            if (pessoa.getCPF().equals(cpf)) { //
                return pessoa;
            }
        }
        return null;
    }

////////////// MÉTODOS IMPRIMIR INFORMAÇÕES



public static void imprimirInformacoes(Pessoa pessoa) {
        
        System.out.println("CPF: " + pessoa.getCPF()); 
        System.out.println("nome: " + pessoa.getNome());
        System.out.println("nascimento: " + pessoa.getNascimento());
        System.out.println("email: " + pessoa.getEmail());
        System.out.println("endereço: " + pessoa.getEndereco());
        
    }

    public static void imprimirInformacoes(Turma turma) {
        
        System.out.println("ID: " + turma.getID()); 
        System.out.println("nome: " + turma.getNome());
        System.out.println("descrição: " + turma.getDescricao());
        System.out.println("início: " + turma.getInicio());
        System.out.println("fim: " + turma.getFim());
        
    }

    public static void imprimirInformacoes(Atividade atividade) {
        
        System.out.println("ID: " + atividade.getID()); 
        System.out.println("nome: " + atividade.getNome());
        System.out.println("descrição: " + atividade.getDescricao());
        System.out.println("início: " + atividade.getInicio());
        System.out.println("fim: " + atividade.getFim());
        
    }

    public static void imprimirInformacoes(Tarefa tarefa) {
        
        System.out.println("ID: " + tarefa.getID()); 
        System.out.println("nota: " + tarefa.getNota());
        System.out.println("Matricula do aluno: " + tarefa.getAluno().getMatricula());
        System.out.println("turma: " + tarefa.getTurma().getNome());
        System.out.println("ID da atividade: " + tarefa.getAtividade().getID());
        
    }



////////////// DADOS INICIAIS


     //Apenas cria um conjunto mínimo de dados para que o programa não comece vazio

    private static void GeraDadosIniciais() {

        // --- Pessoas ---
        Aluno aluno1 = new Aluno("111", "João Silva", LocalDate.parse("10/05/2007", formatadorDeData), "joao@email.com", "Rua A, 10", "A01", "DS");
        listaDePessoas.add(aluno1);
        Professor prof1 = new Professor("333", "Carlos Souza", LocalDate.parse("20/08/1985", formatadorDeData), "carlos@email.com", "Rua C, 30", "P01", "POO");
        listaDePessoas.add(prof1);

        // --- Turmas ---
        Turma turma1 = new Turma(proximoIdTurma++, "203-A", "Desenvolvimento de Sistemas", LocalDate.now(), LocalDate.now().plusMonths(6), null, null, null, null);
        listaDeTurmas.add(turma1);

        try {
            turma1.adicionarParticipante(aluno1);
            turma1.adicionarParticipante(prof1);
        } catch (PessoaJaParticipanteException e) {
            System.out.println("Erro ao adicionar participantes iniciais: " + e.getMessage());
        }


        // --- Atividades ---
        Atividade ativ1 = new Atividade(proximoIdAtividade++, "Prova POO", "Avaliação final", LocalDate.parse("01/07/2025", formatadorDeData), LocalDate.parse("01/07/2025", formatadorDeData), 100);
        listaDeAtividades.add(ativ1);

        // --- Associa atividades às turmas e cria tarefas iniciais.
        try {
            turma1.associaAtividade(ativ1);

            // Cria tarefa para o Aluno1 na Turma1 com Atividade1
            if (turma1.obtemListaAlunos(false).contains(aluno1)) {
                Tarefa t1 = new Tarefa(proximoIdTarefa++, aluno1, turma1, ativ1, 4);
                listaDeTarefas.add(t1);
            }
        } catch (AtividadeJaAssociadaATurmaException e) {
            System.out.println("Erro ao associar atividades iniciais: " + e.getMessage());
        }
    }

}