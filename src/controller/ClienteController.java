package controller;

import model.Cliente;

import java.io.*;
import java.util.*;

public class ClienteController {
    private static final String ARQUIVO = "clientes.txt";
    private List<Cliente> lista = new ArrayList<>();

    /** Carrega todos os clientes de clientes.txt */
    public void carregar() {
        File f = new File(ARQUIVO);
        if (!f.exists()) return;
        lista.clear();
        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                Cliente c = Cliente.fromText(linha);
                if (c != null) lista.add(c);
            }
        } catch (IOException e) {
            System.err.println("Erro ao carregar clientes: " + e.getMessage());
        }
    }

    /** Salva um cliente novo ao fim do arquivo */
    public void salvar(Cliente c) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(ARQUIVO, true))) {
            pw.println(c.toText());
        } catch (IOException e) {
            System.err.println("Erro ao salvar cliente: " + e.getMessage());
        }
    }

    /**
     * Adiciona o cliente na lista em memória e persiste em arquivo,
     * mas antes valida CPF e checa duplicatas.
     */
    public void adicionar(Cliente c) {
        // Validação de formato: exatamente 11 dígitos
        if (!c.getCpf().matches("\\d{11}")) {
            System.out.println("CPF inválido (deve conter 11 dígitos): " + c.getCpf());
            return;
        }
        // Duplicata?
        if (buscarPorCpf(c.getCpf()) != null) {
            System.out.println("CPF já cadastrado: " + c.getCpf());
            return;
        }
        lista.add(c);
        salvar(c);
        System.out.println("Cliente cadastrado com sucesso!");
    }


    public List<Cliente> getLista() {
        return Collections.unmodifiableList(lista);
    }

  
    public Cliente buscarPorCpf(String cpf) {
        return lista.stream()
                    .filter(c -> c.getCpf().equals(cpf))
                    .findFirst()
                    .orElse(null);
    }
}
