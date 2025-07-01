package org.teiacoltec.poo.tp1;


public class Turma {
    private int id;
    private String nome;
    private String descricao;
    private String inicio;
    private String fim;
    private Pessoa[] participantes;
    private Turma turma_pai;
    private Turma[] turmas_filhas;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getInicio() {
        return inicio;
    }

    public void setInicio(String inicio) {
        this.inicio = inicio;
    }

    public String getFim() {
        return fim;
    }

    public void setFim(String fim) {
        this.fim = fim;
    }

    public Pessoa[] getParticipantes() {
        return participantes;
    }

    public void setParticipantes(Pessoa[] participantes) {
        this.participantes = participantes;
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

    public Turma(int id, String nome, String descricao, String inicio, String fim, Pessoa[] participantes, Turma turma_pai, Turma[] turmas_filhas) {
    this.id = id;
    this.nome = nome;
    this.descricao = descricao;
    this.inicio = inicio;
    this.fim = fim;
    this.participantes = participantes;
    this.turma_pai = turma_pai;
    this.turmas_filhas = turmas_filhas;
}

    public Pessoa[] obtemListaParticipantes() {
        return participantes;
    }
    
    public String obterInformacoes() {
    return "Turma: " + nome + "\n" +
           "Descrição: " + descricao + "\n" +
           "Início: " + inicio + "\n" +
           "Fim: " + fim + "\n" +
           "ID: " + id;
    }

    public void adicionarParticipante(Pessoa pessoa) throws PessoaJaParticipanteException {
        if (participantes != null) {
            for (Pessoa p : participantes) {
                if (p != null && p.getCpf().equals(pessoa.getCpf())) {
                    throw new PessoaJaParticipanteException("\n-------------------\n"+pessoa.getNome()+" já é participante da turma."+"\n-------------------\n");
                }
            }
        }
        if (participantes == null) {
            participantes = new Pessoa[1];
            participantes[0] = pessoa;
        } else {
            Pessoa[] novoArray = new Pessoa[participantes.length + 1];
            System.arraycopy(participantes, 0, novoArray, 0, participantes.length);
            novoArray[participantes.length] = pessoa;
            participantes = novoArray;
        }
    }


public void removerParticipante(Pessoa pessoa) throws PessoaNaoEncontradaException {
    if (participantes == null || participantes.length == 0) {
        throw new PessoaNaoEncontradaException("\n-------------------\n" + pessoa.getNome() + " não encontrada na turma.\n-------------------\n");
    }
    int idxRemover = -1;
    for (int i = 0; i < participantes.length; i++) {
        if (participantes[i] != null && participantes[i].getCpf().equals(pessoa.getCpf())) {
            idxRemover = i;
            break;
        }
    }
    if (idxRemover == -1) {
        throw new PessoaNaoEncontradaException("\n-------------------\n"+pessoa.getNome() + " não foi encontrado(a) na turma.\n-------------------\n");
    }
    Pessoa[] novoArray = new Pessoa[participantes.length - 1];
    for (int i = 0, j = 0; i < participantes.length; i++) {
        if (i != idxRemover) {
            novoArray[j++] = participantes[i];
        }
    }
    participantes = novoArray;
    }
    

    public boolean participa(Pessoa pessoa) {
    if (participantes == null) return false;
    for (Pessoa p : participantes) {
        if (p != null && p.getCpf().equals(pessoa.getCpf())) {
            return true;
        }
    }
    return false;
    }


  public void associaSubturma(Turma subturma) {
    if (turmas_filhas == null) {
        turmas_filhas = new Turma[1];
        turmas_filhas[0] = subturma;
    } else {
        Turma[] novoArray = new Turma[turmas_filhas.length + 1];
        System.arraycopy(turmas_filhas, 0, novoArray, 0, turmas_filhas.length);
        novoArray[turmas_filhas.length] = subturma;
        turmas_filhas = novoArray;
    }
    subturma.setTurmaPai(this);
    System.out.println("\n-------------------\n"+"Subturma '" + subturma.getNome() + "' associada com sucesso à turma '" + this.getNome() + "'."+"\n-------------------\n");
}


    public Turma(Turma turmaPai) {
    this.turma_pai = turmaPai;
    
    }

}