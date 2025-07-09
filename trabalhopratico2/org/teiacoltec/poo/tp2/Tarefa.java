//copia de atividade
package org.teiacoltec.poo.tp2;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;

public class Tarefa{
    
    private int ID;
    private Aluno aluno;
    private Turma turma;
    private Atividade atividade;
    private float nota;
    

    public Tarefa(int ID, String nome, String descricao, String inicio, String fim){
        public Tarefa(int ID, Aluno aluno, Turma turma, Atividade atividade) {
        this.ID = ID;
        this.aluno = aluno;
        this.turma = turma;
        this.atividade = atividade;
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

    public Tarefa obtemTarefaPorID(int id) {
        if (this.ID == id) {
            return this;
        }
        return null;
    }

}
