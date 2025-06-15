package model;

import java.time.LocalDate;
import java.util.List;

public class Locacao {
    private String cpf;              
    private Filme filme;
    private LocalDate dataLocacao;
    private LocalDate dataDevolucao;

    public Locacao(String cpf, Filme filme, LocalDate dtLoc, LocalDate dtDev) {
        this.cpf             = cpf;
        this.filme           = filme;
        this.dataLocacao     = dtLoc;
        this.dataDevolucao   = dtDev;
    }

    public String getCpf() { 
        return cpf; 
    }
    public Filme getFilme() { 
        return filme; 
    }
    public LocalDate getDataLocacao() { 
        return dataLocacao; 
    }
    public LocalDate getDataDevolucao() { 
        return dataDevolucao; 
    }

    @Override
    public String toString() {
        return  "CPF Cliente: "  + cpf            + "\n"
              + "Filme: "        + filme.getTitulo() + "\n"
              + "Locado em: "    + dataLocacao       + "\n"
              + "Devolver em: "  + dataDevolucao     + "\n"
              + "-----------------------------";
    }

    public String toText() {
        return cpf + ";"
             + filme.getTitulo() + ";"
             + dataLocacao      + ";"
             + dataDevolucao;
    }

    /**
     * Constrói uma Locacao a partir de uma linha de texto no formato:
     * CPF;Título;YYYY-MM-DD;YYYY-MM-DD
     */
    public static Locacao fromText(String linha, List<Filme> filmes, List<Cliente> clientes) {
        String[] p = linha.split(";");
        if (p.length != 4) return null;

        String cpf    = p[0];
        String titulo = p[1];
        LocalDate dtL = LocalDate.parse(p[2]);
        LocalDate dtD = LocalDate.parse(p[3]);

        Filme filme = filmes.stream()
            .filter(f -> f.getTitulo().equalsIgnoreCase(titulo))
            .findFirst()
            .orElse(null);

        Cliente cliente = clientes.stream()
            .filter(c -> c.getCpf().equals(cpf))
            .findFirst()
            .orElse(null);

        if (filme != null && cliente != null) {
            return new Locacao(cpf, filme, dtL, dtD);
        }
        return null;
    }
}
