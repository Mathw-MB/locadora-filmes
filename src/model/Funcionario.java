package model;

public abstract class Funcionario {
    private String nome;
    private String matricula;
    private double salarioBase;

    public Funcionario(String nome, String matricula, double salarioBase) {
        this.nome = nome;
        this.matricula = matricula;
        this.salarioBase = salarioBase;
    }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getMatricula() { return matricula; }
    public void setMatricula(String matricula) { this.matricula = matricula; }
    public double getSalarioBase() { return salarioBase; }
    public void setSalarioBase(double salarioBase) { this.salarioBase = salarioBase; }

    public abstract double calcularSalario();
    public abstract String toText();

    @Override
    public String toString() {
        return  "Nome: "       + nome +
              "\nMatrícula: " + matricula +
              "\nSalário: R$" + salarioBase;
    }

    public static Funcionario fromText(String linha) {
        String[] partes = linha.split(";");
        String tipo      = partes[0];
        String nome      = partes[1];
        String mat       = partes[2];
        double base      = Double.parseDouble(partes[3]);

        if ("GERENTE".equalsIgnoreCase(tipo)) {
            double bonus = Double.parseDouble(partes[4]);
            return new Gerente(nome, mat, base, bonus);
        } else if ("ATENDENTE".equalsIgnoreCase(tipo)) {
            String turno = partes[4];
            return new Atendente(nome, mat, base, turno);
        }
        return null;
    }
}
