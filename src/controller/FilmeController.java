package controller;

import model.Filme;

import java.io.*;
import java.util.*;

public class FilmeController {
    private static final String ARQUIVO = "filmes.txt";
    private List<Filme> lista = new ArrayList<>();

    public void carregar() {
        File f = new File(ARQUIVO);
        if (!f.exists()) return;
        lista.clear();
        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                Filme fl = Filme.fromText(linha);
                if (fl != null) lista.add(fl);
            }
        } catch (IOException e) {
            System.err.println("Erro ao carregar filmes: " + e.getMessage());
        }
    }

    /** Salva um filme ao fim do arquivo */
    public void salvar(Filme f) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(ARQUIVO, true))) {
            pw.println(f.toText());
        } catch (IOException e) {
            System.err.println("Erro ao salvar filme: " + e.getMessage());
        }
    }

    /** Sobrescreve o arquivo com a lista atual */
    public void salvarTodos() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(ARQUIVO))) {
            for (Filme f : lista) pw.println(f.toText());
        } catch (IOException e) {
            System.err.println("Erro ao salvar todos os filmes: " + e.getMessage());
        }
    }

    /**
     * Adiciona um filme, mas antes checa duplicata por título (case-insensitive).
     */
    public void adicionar(Filme f) {
        boolean existe = lista.stream()
                              .anyMatch(x -> x.getTitulo().equalsIgnoreCase(f.getTitulo()));
        if (existe) {
            System.out.println("Filme já cadastrado: " + f.getTitulo());
            return;
        }
        lista.add(f);
        salvar(f);
        System.out.println("Filme cadastrado com sucesso!");
    }

 
    public List<Filme> getLista() {
        return Collections.unmodifiableList(lista);
    }

  
    public List<Filme> buscarPorGenero(String genero) {
        List<Filme> out = new ArrayList<>();
        for (Filme f : lista) {
            if (f.getGenero().getNome().equalsIgnoreCase(genero)) {
                out.add(f);
            }
        }
        return out;
    }
}
