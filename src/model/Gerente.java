package model;

public class Gerente extends Funcionario implements Trabalhavel {

    private double bonusAnual;

    public Gerente(String nome, String matricula, double salarioBase, double bonusAnual) {
        super(nome, matricula, salarioBase);
        this.bonusAnual = bonusAnual;
    }

    public double getBonusAnual() { return bonusAnual; }
    public void setBonusAnual(double bonusAnual) { this.bonusAnual = bonusAnual; }

    @Override
    public double calcularSalario() {
        return getSalarioBase() + bonusAnual;
    }

    @Override
    public void trabalhar() {
        System.out.println(getNome() + " está coordenando a equipe e definindo metas.");
    }

    @Override
    public void relatarProgresso() {
        System.out.println(getNome() + " gerou relatório de resultados.");
    }

    @Override
    public String toString() {
        return "[GERENTE] " + super.toString() + 
               "\\nBônus Anual: R$" + bonusAnual;
    }

    @Override
    public String toText() {
        return "GERENTE;" + getNome() + ";" + getMatricula() +
               ";" + getSalarioBase() + ";" + bonusAnual;
    }
}
