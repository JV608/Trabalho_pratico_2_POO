package org.teiacoltec.poo.tp1;

import java.time.LocalDate;
import java.util.Arrays;

public class Turma {
    
    private int ID;
    private String nome;
    private String descricao;
    private LocalDate inicio;
    private LocalDate fim;
    private Pessoa[] participantes;
    private Turma turma_pai;
    private Turma[] turmas_filhas;

    Turma(int ID, String nome, String descricao, LocalDate inicio, LocalDate fim, Pessoa[] participantes, Turma turma_pai, Turma[] turmas_filhas){

        this.ID = ID;
        this.nome = nome;
        this.descricao = descricao;
        this.inicio = inicio;
        this.fim = fim;
        if (participantes != null) {
        this.participantes = participantes;
        } else {
        this.participantes = new Pessoa[0];
          }
        if (turmas_filhas != null) {
        this.turmas_filhas = turmas_filhas;
        } else {
            this.turmas_filhas = new Turma[0];
          }
        this.turma_pai = turma_pai;

    }
 
    Turma(Turma turmaPai){

    this.turma_pai = turmaPai;
    if (turmaPai != null){
    turmaPai.associaSubturma(this);
    }

    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDate getInicio() {
        return inicio;
    }

    public void setInicio(LocalDate inicio) {
        this.inicio = inicio;
    }

    public LocalDate getFim() {
        return fim;
    }

    public void setFim(LocalDate fim) {
        this.fim = fim;
    }

    public Turma getTurmaPai() {
    return turma_pai;
    }

    public void setTurmaPai(Turma turma_pai) {
        this.turma_pai = turma_pai;
    }

    public Turma[] getTurmasFilhas() {
        return turmas_filhas;
    }

    public void setTurmasFilhas(Turma[] turmas_filhas) {
        this.turmas_filhas = turmas_filhas;
    }

    public Pessoa[] obtemListaParticipantes(){
        return participantes;
    }
   
    void adicionarParticipante(Pessoa pessoa) throws PessoaJaParticipanteException{

        for (int i = 0; i<participantes.length; i++) {
            if(participantes[i].getCPF().equals(pessoa.getCPF())){
                throw new PessoaJaParticipanteException();
            }
        }
       
        Pessoa[] novoArray = Arrays.copyOf(participantes, participantes.length + 1);
        novoArray[novoArray.length - 1] = pessoa;
        participantes = novoArray;

    }
   
    void removerParticipante(Pessoa pessoa) throws PessoaNaoEncontradaException{

        boolean encontrado = false;

         for (int i = 0; i<participantes.length; i++) {
            if(participantes[i] != null && participantes[i].getCPF().equals(pessoa.getCPF())){

             for (int j = i; j < participantes.length - 1; j++) {
                participantes[j] = participantes[j + 1];
            }

            participantes = Arrays.copyOf(participantes, participantes.length - 1);
            encontrado = true;
            break;
            }
        
        }

        if(encontrado != true)
        throw new PessoaNaoEncontradaException();

    }
   
    boolean participa(Pessoa pessoa){
        
        for(int i=0; i < participantes.length; i++){

            if(participantes[i].getCPF().equals(pessoa.getCPF())) 
            return true;
        }

            return false;

    }
   
    void associaSubturma (Turma subturma){

    Turma[] novasFilhas = Arrays.copyOf(turmas_filhas, turmas_filhas.length + 1);
    novasFilhas[novasFilhas.length - 1] = subturma;
    turmas_filhas = novasFilhas;

    subturma.setTurmaPai(this);

    }

}