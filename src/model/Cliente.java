package model;

public class Cliente {
    private String nome;
    private String cpf; // identificador único

    public Cliente(String nome, String cpf) {
        this.nome = nome;
        this.cpf = cpf;
    }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getCpf() { return cpf; }
    public void setCpf(String cpf) { this.cpf = cpf; }

    @Override
    public String toString() {
        return "Cliente: " + nome + " (CPF: " + cpf + ")";
    }

    public String toText() {
        return nome + ";" + cpf;
    }

    public static Cliente fromText(String linha) {
        String[] parts = linha.split(";");
        return new Cliente(parts[0], parts[1]);
    }
}
