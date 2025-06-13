package controller;

import model.Locacao;
import model.Filme;
import model.Cliente;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Controlador de locações: carrega, salva e fornece relatórios sobre locações.
 */
public class LocacaoController {
    private static final String ARQUIVO = "locacoes.txt";
    private final List<Locacao> lista = new ArrayList<>();

    /**
     * Carrega do arquivo todas as locações já registradas,
     * reconstrói objetos só se CPF e título validarem contra listas.
     */
    public void carregar(List<Filme> filmes, List<Cliente> clientes) {
        File f = new File(ARQUIVO);
        if (!f.exists()) return;
        lista.clear();

        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                Locacao loc = Locacao.fromText(linha, filmes, clientes);
                if (loc != null) {
                    lista.add(loc);
                }
            }
        } catch (IOException e) {
            System.err.println("Erro ao carregar locações: " + e.getMessage());
        }
    }

    /** Persiste uma nova locação no final do arquivo. */
    public void salvar(Locacao loc) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(ARQUIVO, true))) {
            pw.println(loc.toText());
        } catch (IOException e) {
            System.err.println("Erro ao salvar locação: " + e.getMessage());
        }
    }

    /** Adiciona a locação em memória e grava em arquivo. */
    public void adicionar(Locacao loc) {
        lista.add(loc);
        salvar(loc);
    }

    /** Retorna lista não modificável de todas as locações registradas. */
    public List<Locacao> getLista() {
        return Collections.unmodifiableList(lista);
    }

    /** Relatório: conta quantas vezes cada filme foi locado. */
    public Map<String, Long> locacoesPorFilme() {
        return lista.stream()
            .collect(Collectors.groupingBy(l -> l.getFilme().getTitulo(), Collectors.counting()));
    }

    /** Relatório: conta quantas locações de cada gênero foram feitas. */
    public Map<String, Long> locacoesPorGenero() {
        return lista.stream()
            .collect(Collectors.groupingBy(l -> l.getFilme().getGenero().getNome(), Collectors.counting()));
    }
}
