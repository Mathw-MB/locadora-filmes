package model;

public class Genero {
    private String nome;
    private String descricao;

    public Genero(String nome, String descricao) {
        this.nome = nome;
        this.descricao = descricao;
    }

    public String getNome()      { return nome; }
    public void setNome(String n){ this.nome = n; }
    public String getDescricao(){ return descricao; }
    public void setDescricao(String d){ this.descricao = d; }

    @Override
    public String toString() {
        return nome + " — " + descricao;
    }
}
