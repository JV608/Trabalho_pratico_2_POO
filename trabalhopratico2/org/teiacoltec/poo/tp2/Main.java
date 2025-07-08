//codigo igual ao do gabriel a principio 
package org.teiacoltec.poo.tp2;

import java.time.LocalDate;

public class Main {

    public static void main(String[] args) {
    
    Pessoa[] Participantes;

     Professor Junior = new Professor("17270131232", "Junior", LocalDate.of(1978,7,1), "juninhoGamer@hotmail.com", "Rua Peralta N401", "2031041", "Físico");
     Aluno Gabriel = new Aluno("42002941431", "Gabriel", LocalDate.of(2008,2,11), "a2024952008@teiacoltec.org", "Rua Matheus Pereira N10", "2024952008", "Desenvolvimento de Sistemas");
     Aluno Marco = new Aluno("62342412155", "Marco", LocalDate.of(2007,6,1), "marco0810@gmail.com", "Rua Peralta N21", "9841000", "Desenvolvimento de Sistemas");
     Atividade TP1 = new Atividade(01, "Livro Didático", "Páginas 20 a 25", LocalDate.of(2025,5,12), LocalDate.of(2025,5,18));
     Turma A203 = new Turma(03, "203", "Turma de Desenvolvimento de Sistemas - COLTEC-UFMG", LocalDate.of(2025,1,1), LocalDate.of(2025,12,25), null, null, null);
     Turma subturmaA = new Turma(A203);
     Turma subturmaB = new Turma(A203);
     
     subturmaA.setNome("SubturmaA");
     subturmaA.setDescricao("Subturma A da 203");
     subturmaB.setNome("SubturmaB");
     subturmaA.setDescricao("Subturma B da 203");

     Turma[] TurmasFilhas = A203.getTurmasFilhas();
     System.out.println("As turmas filhas associadas:\n");
     System.out.print("[");
     for(int i=0; i<TurmasFilhas.length; i++){

     System.out.print(TurmasFilhas[i].getNome());
     if(i != TurmasFilhas.length - 1){
     System.out.print(", ");
     }     
     }
     System.out.print("]\n");

     System.out.println("\nObtendo informações da pessoa, por meio do método obter informações:");
     String informações = Gabriel.obterInformacoes();
     System.out.println(informações + "\n");

     System.out.println("Informações do Aluno:");
     imprimirInformacoes(Gabriel);
     System.out.println("\nInformações do Professor:");
     imprimirInformacoes(Junior);
     System.out.println("\nInformações da Atividade:");
     imprimirInformacoes(TP1);
     System.out.println();

     try {
      A203.adicionarParticipante(Gabriel);
     } catch (PessoaJaParticipanteException e) {
        System.out.println("Erro: A pessoa já está participando da turma.");
       }

        try {
      A203.adicionarParticipante(Junior);
     } catch (PessoaJaParticipanteException e) {
        System.out.println("Erro: A pessoa já está participando da turma.");
       }

       boolean participa;
       participa = A203.participa(Gabriel);
       System.out.println("Gabriel faz parte dos participantes após adição? : " + participa);

       participa = A203.participa(Junior);
       System.out.println("Junior faz parte dos participantes após adição? : " + participa);

       Participantes = A203.obtemListaParticipantes();
       System.out.println("\nParticipantes após as adições de pessoas: \n");
       
       System.out.print("[");
       for(int i=0; i<Participantes.length; i++){

       System.out.print(Participantes[i].getNome());
       if(i != Participantes.length - 1){
        System.out.print(", ");
       }
       }
       System.out.print("]\n");

       try {
      A203.removerParticipante(Junior);
     } catch (PessoaNaoEncontradaException e) {
        System.out.println("Erro: A pessoa não foi encontrada na Turma.");
       }

       Participantes = A203.obtemListaParticipantes();
       System.out.println("\nParticipantes após a remoção: \n");

       System.out.print("[");
       for(int i=0; i<Participantes.length; i++){

       System.out.print(Participantes[i].getNome());
       if(i != Participantes.length - 1){
        System.out.print(", ");
       }
       }
       System.out.print("]");

       participa = A203.participa(Gabriel);
       System.out.println("\n\nGabriel faz parte dos participantes após remoção? : " + participa);

       participa = A203.participa(Junior);
       System.out.println("Junior faz parte dos participantes após remoção? : " + participa);

       System.out.println("\nTeste do erro PessoaJaParticipanteException:");

       try {
       A203.adicionarParticipante(Gabriel);
       } catch (PessoaJaParticipanteException e) {
        System.out.println("Erro: A pessoa já está participando da turma.");
       }

       System.out.println("\nTeste do erro PessoaNaoEncontradaException:");

       try {
       A203.removerParticipante(Marco);
       } catch (PessoaNaoEncontradaException e) {
        System.out.println("Erro: A pessoa não foi encontrada na Turma.");
       }

    }

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

}
