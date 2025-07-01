package org.teiacoltec.poo.tp1;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Turma{
    private int ID;
    private String nome;
    private String descricao;
    private LocalDate inicio;
    private LocalDate fim;
    private Pessoa[] participantes = new Pessoa[100];
    private Turma turma_pai;
    private Turma[] turmas_filhas = new Turma[50];
    private  int countpart;
    private int countturmaf;
    private static DateTimeFormatter modelo = DateTimeFormatter.ofPattern("dd/MM/yyyy");


    public Turma(int ID, String nome, String descricao, String inicio, String fim, Pessoa[] participantes, Turma turma_pai){
        this(turma_pai);
        this.ID = ID;
        this.nome = nome;
        this.descricao = descricao;
        this.inicio = LocalDate.parse(inicio, modelo);
        this.fim = LocalDate.parse(fim, modelo);
        this.participantes = participantes;
        this.countpart = 0;
        this.countturmaf = 0;
    
    }
    private Turma(Turma turma_pai){
        this.turma_pai = turma_pai;
        if (turma_pai != null) {
            turma_pai.associaSubturma(this);
        }
        
    }
    public Pessoa[] getParticipantes(){
        return participantes;
    }
    public Pessoa[] obtemListaParticipantes(){
        //Pessoa[] listadeparticipantes =new Pessoa[countpart]; 
        //for(int i = 0; i < countpart; i++) {
         //   listadeparticipantes[i] = participantes[i];
        //}
        Pessoa[] listadeparticipantes = this.getParticipantes();
        return listadeparticipantes;
    }

    public boolean participa(Pessoa pessoa){
        boolean existe = false;
        for (int i=0; i < countpart; i++){
            if (participantes[i].getCPF().compareToIgnoreCase(pessoa.getCPF()) == 0){
                existe = true;
                break;
            }    
        }
        return existe;
    }
    public void adicionarParticipante(Pessoa pessoa)throws PessoaJaParticipanteException{
        boolean prov = false;
        prov = participa(pessoa);
        if (prov){
            throw new PessoaJaParticipanteException();
        }
        else{
            participantes[countpart] = pessoa;     
            countpart++; 
        }
    }
    public void removerParticipante(Pessoa pessoa)throws PessoaNaoEncontradaException{
        boolean prov = false;
        int indexeliminado = 0;
        prov = participa(pessoa);
        if(prov){
            for (int i=0; i < countpart; i++){
                if (participantes[i].getCPF().compareToIgnoreCase(pessoa.getCPF()) == 0){
                    indexeliminado = i;
                }
            }
            for (int i=indexeliminado; i  < countpart; i++){
                participantes[i] = participantes[i + 1];
            }
            countpart--;
        }
        else{
            throw new PessoaNaoEncontradaException();
        }
    }
    
   
    public void associaSubturma(Turma turma){
        if(countturmaf < turmas_filhas.length){
        turmas_filhas[countturmaf++] = turma;
        }
    }
    
    public int getID(){
        return ID;
    }
    public String getnome(){
        return nome;
    }
    public String getdescricao(){
        return descricao;
    }
    public LocalDate getinicio(){
        return inicio;
    }
    public LocalDate getfim(){
        return fim;
    }
    public Pessoa[] getparticipantes(){
        return participantes;
    }

    
    
    public String obterInformacoes(){
        String chamada = "";
        for(int i = 0; i < participantes.length; i++){
            chamada += "\n"+participantes[i].getnome() ;
        }
        String informacoes = "Informacoes da turma "+getnome()+":\nID: "+ getID() + "\nDescrição: "+getdescricao()+"\nInicio: "+getinicio()+"\nFim: "+getfim()+"\nParticipantes: "+chamada;
        return informacoes;
    }
}
