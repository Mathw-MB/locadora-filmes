package controller;

import model.Funcionario;
import java.io.*;
import java.util.*;

public class FuncionarioController {
    private static final String ARQUIVO = "funcionarios.txt";
    private List<Funcionario> lista = new ArrayList<>();

    public void carregar() {
        File f = new File(ARQUIVO);
        if (!f.exists()) return;
        lista.clear();
        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                Funcionario func = Funcionario.fromText(linha);
                if (func != null) lista.add(func);
            }
        } catch (IOException e) {
            System.err.println("Erro ao carregar funcionários: " + e.getMessage());
        }
    }

    public void salvar(Funcionario f) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(ARQUIVO, true))) {
            pw.println(f.toText());
        } catch (IOException e) {
            System.err.println("Erro ao salvar funcionário: " + e.getMessage());
        }
    }

    public void salvarTodos() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(ARQUIVO))) {
            for (Funcionario f : lista) pw.println(f.toText());
        } catch (IOException e) {
            System.err.println("Erro ao salvar todos: " + e.getMessage());
        }
    }

    public void adicionar(Funcionario f) {
        lista.add(f);
        salvar(f);
    }

    public List<Funcionario> getLista() {
        return Collections.unmodifiableList(lista);
    }
}
