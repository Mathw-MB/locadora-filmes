package view;

import controller.console.Console;
import controller.FuncionarioController;
import controller.FilmeController;
import controller.ClienteController;
import controller.LocacaoController;
import model.*;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Map;

public class Principal {

    public static void main(String[] args) {
        // Carregamento inicial
        FuncionarioController fc = new FuncionarioController();
        fc.carregar();

        FilmeController fmc = new FilmeController();
        fmc.carregar();

        ClienteController cc = new ClienteController();
        cc.carregar();

        LocacaoController lc = new LocacaoController();
        lc.carregar(fmc.getLista(), cc.getLista());

        // Seleção de perfil (simples controle de acesso)
        System.out.println("Selecione seu perfil:\n1) Gerente\n2) Atendente");
        int perfil = Console.lerInt();
        boolean isGerente = (perfil == 1);

        int op;
        do {
            System.out.println("\n=== LOCADORA DE FILMES ===");
            System.out.println("1) Cadastrar Gerente");
            System.out.println("2) Cadastrar Atendente");
            System.out.println("3) Listar Funcionários");
            System.out.println("4) Cadastrar Filme");
            System.out.println("5) Listar Filmes");
            System.out.println("6) Buscar Filmes por Gênero");
            System.out.println("7) Cadastrar Cliente");
            System.out.println("8) Listar Clientes");
            System.out.println("9) Realizar Locação");
            System.out.println("10) Listar Locações");
            System.out.println("11) Relatório: Locações por Filme");
            System.out.println("12) Relatório: Locações por Gênero");
            System.out.println("0) Sair");
            System.out.print("Opção: ");
            op = Console.lerInt();

            switch (op) {
                case 1 -> {
                    System.out.print("Nome do Gerente: ");
                    String ng = Console.lerString();
                    System.out.print("Matrícula: ");
                    String mg = Console.lerString();
                    System.out.print("Salário Base: ");
                    double sbg = Double.parseDouble(Console.lerString());
                    System.out.print("Bônus Anual: ");
                    double bg = Double.parseDouble(Console.lerString());
                    fc.adicionar(new Gerente(ng, mg, sbg, bg));
                }
                case 2 -> {
                    System.out.print("Nome do Atendente: ");
                    String na = Console.lerString();
                    System.out.print("Matrícula: ");
                    String ma = Console.lerString();
                    System.out.print("Salário Base: ");
                    double sba = Double.parseDouble(Console.lerString());
                    System.out.print("Turno: ");
                    String ta = Console.lerString();
                    fc.adicionar(new Atendente(na, ma, sba, ta));
                }
                case 3 -> {
                    List<Funcionario> lf = fc.getLista();
                    if (lf.isEmpty()) {
                        System.out.println("Nenhum funcionário cadastrado.");
                    } else {
                        lf.forEach(System.out::println);
                    }
                }
                case 4 -> {
                    if (!isGerente) {
                        System.out.println("Apenas gerente pode cadastrar filmes.");
                        break;
                    }
                    System.out.print("Título: ");
                    String t = Console.lerString();
                    System.out.print("Diretor: ");
                    String d = Console.lerString();
                    System.out.print("Ano: ");
                    int a = Console.lerInt();
                    System.out.print("Gênero: ");
                    String g = Console.lerString();
                    fmc.adicionar(new Filme(t, d, a, new Genero(g, "")));
                }
                case 5 -> {
                    List<Filme> listaFilmes = fmc.getLista();
                    if (listaFilmes.isEmpty()) {
                      System.out.println("Nenhum filme cadastrado.");
                    } else {
                      System.out.println("\n=== Lista de Filmes ===");
                      for (int i = 0; i < listaFilmes.size(); i++) {
                    Filme fil = listaFilmes.get(i);
                      System.out.println("\nFilme " + (i + 1) + ":");
                     System.out.println(fil);  // invoca o toString() corrigido
                          }
                        }
                    }

                case 6 -> {
                    System.out.print("Gênero: ");
                    String gr = Console.lerString();
                    List<Filme> encontrados = fmc.buscarPorGenero(gr);
                    if (encontrados.isEmpty()) {
                        System.out.println("Nenhum filme deste gênero.");
                    } else {
                        encontrados.forEach(System.out::println);
                    }
                }
                case 7 -> {
                    System.out.print("Nome do Cliente: ");
                    String nc = Console.lerString();
                    System.out.print("CPF (11 dígitos): ");
                    String cpf = Console.lerString();
                    cc.adicionar(new Cliente(nc, cpf));
                }
                case 8 -> {
                    List<Cliente> clientes = cc.getLista();
                    if (clientes.isEmpty()) {
                        System.out.println("Nenhum cliente cadastrado.");
                    } else {
                        clientes.forEach(System.out::println);
                    }
                }
                case 9 -> {
                    System.out.print("CPF do cliente (11 dígitos): ");
                    String cpfLoc = Console.lerString();
                    Cliente c = cc.buscarPorCpf(cpfLoc);
                    if (c == null) {
                        System.out.println("Cliente não encontrado ou CPF inválido.");
                        break;
                    }

                    System.out.print("Título do Filme: ");
                    String tf = Console.lerString();
                    Filme f = fmc.getLista().stream()
                                 .filter(x -> x.getTitulo().equalsIgnoreCase(tf))
                                 .findFirst().orElse(null);
                    if (f == null) {
                        System.out.println("Filme não encontrado.");
                        break;
                    }

                    LocalDate dtL, dtD;
                    try {
                        System.out.print("Data Locação (YYYY-MM-DD): ");
                        dtL = LocalDate.parse(Console.lerString());
                        System.out.print("Data Devolução (YYYY-MM-DD): ");
                        dtD = LocalDate.parse(Console.lerString());
                    } catch (DateTimeParseException e) {
                        System.out.println("Formato de data inválido! Use YYYY-MM-DD.");
                        break;
                    }
                    if (dtD.isBefore(dtL)) {
                        System.out.println("Data de devolução não pode ser anterior à locação.");
                        break;
                    }

                    lc.adicionar(new Locacao(c.getNome(), f, dtL, dtD));
                    System.out.println("Locação registrada com sucesso!");
                }
                case 10 -> {
                    List<Locacao> locs = lc.getLista();
                    if (locs.isEmpty()) {
                        System.out.println("Sem locações.");
                    } else {
                        System.out.println("\n=== Lista de Locações ===");
                        for (int i = 0; i < locs.size(); i++) {
                            System.out.println("\nLocação " + (i + 1) + ":");
                            System.out.println(locs.get(i));  // usa o toString() atualizado
                        }
                    }
                }

                case 11 -> {
                    Map<String, Long> rp = lc.locacoesPorFilme();
                    rp.forEach((filme, count) ->
                        System.out.println(filme + ": " + count + " locações"));
                }
                case 12 -> {
                    Map<String, Long> rg = lc.locacoesPorGenero();
                    rg.forEach((gen, count) ->
                        System.out.println(gen + ": " + count + " locações"));
                }
                case 0 -> System.out.println("Encerrando sistema...");
                default -> System.out.println("Opção inválida!");
            }
        } while (op != 0);
    }
}
