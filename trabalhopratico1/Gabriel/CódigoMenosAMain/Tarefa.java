//copia de atividade
package org.teiacoltec.poo.tp2;

import java.time.LocalDate;
import java.util.ArrayList;

public class Tarefa{
    
    private int ID;
    private Aluno aluno;
    private Turma turma;
    private Atividade atividade;
    private float nota;

    public Tarefa(int ID, Aluno aluno, Turma turma, Atividade atividade, float nota) {
    this.ID = ID;
    this.aluno = aluno;
    this.turma = turma;
    this.atividade = atividade;
    this.nota = nota;    
    }
        
    public int getID(){
        return ID;
    }
        
    public void setID(int ID) {
        this.ID = ID;
    }
    
    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public Turma getTurma() {
        return turma;
    }

    public void setTurma(Turma turma) {
        this.turma = turma;
    }

    public Atividade getAtividade() {
        return atividade;
    }

    public void setAtividade(Atividade atividade) {
        this.atividade = atividade;
    }

    public float getNota() {
        return nota;
    }

    public void setNota(float nota) {
        this.nota = nota;
    }

    public static Tarefa obtemTarefaPorID(int id, ArrayList<Tarefa> tarefas) {
    for (Tarefa tarefa : tarefas) {
        if (tarefa.getID() == id) {
            return tarefa;
        }
    }
    return null;
    }

    public static ArrayList<Tarefa> obtemTarefasDaPessoa(Pessoa pessoa, ArrayList<Tarefa> todasAsTarefas) {
        ArrayList<Tarefa> tarefasDaPessoa = new ArrayList<>();
        for (Tarefa tarefa : todasAsTarefas) {
            if (tarefa.getAluno().getCPF().equals(pessoa.getCPF())) {
                tarefasDaPessoa.add(tarefa);
            }
        }
        return tarefasDaPessoa;
    }

    public static ArrayList<Tarefa> obtemTarefasDaPessoa(Pessoa pessoa, LocalDate inicio, LocalDate fim, ArrayList<Tarefa> todasAsTarefas) {
    ArrayList<Tarefa> tarefasDaPessoa = new ArrayList<>();

    for (Tarefa tarefa : todasAsTarefas) {
        if (tarefa.getAluno().getCPF().equals(pessoa.getCPF())) {
            LocalDate dataAtividade = tarefa.getAtividade().getInicio();

            if ((dataAtividade.isEqual(inicio) || dataAtividade.isAfter(inicio)) &&
                (dataAtividade.isEqual(fim) || dataAtividade.isBefore(fim))) {
                tarefasDaPessoa.add(tarefa);
            }
        }
    }

    return tarefasDaPessoa;
    }
}