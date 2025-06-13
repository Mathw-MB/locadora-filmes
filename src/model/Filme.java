package model;

public class Filme {

    private String titulo;
    private String diretor;
    private int ano;
    private Genero genero;

    public Filme(String titulo, String diretor, int ano, Genero genero) {
        this.titulo = titulo;
        this.diretor = diretor;
        this.ano = ano;
        this.genero = genero;
    }

    public String getTitulo()  { return titulo; }
    public void setTitulo(String t) { this.titulo = t; }
    public String getDiretor() { return diretor; }
    public void setDiretor(String d) { this.diretor = d; }
    public int getAno()        { return ano; }
    public void setAno(int a)  { this.ano = a; }
    public Genero getGenero() { return genero; }
    public void setGenero(Genero g) { this.genero = g; }

    @Override
    public String toString() {
        return "Título: " + titulo  +
               "\\nDiretor: " + diretor +
               "\\nAno: " + ano +
               "\\nGênero: " + genero.getNome();
    }

    public String toText() {
        return titulo + ";" + diretor + ";" + ano + ";" + genero.getNome();
    }

    public static Filme fromText(String linha) {
        String[] p = linha.split(";");
        return new Filme(p[0], p[1], Integer.parseInt(p[2]), new Genero(p[3], ""));
    }
}
