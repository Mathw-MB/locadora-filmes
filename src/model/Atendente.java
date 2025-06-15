package model;

public class Atendente extends Funcionario implements Trabalhavel {

    private String turno;

    public Atendente(String nome, String matricula, double salarioBase, String turno) {
        super(nome, matricula, salarioBase);
        this.turno = turno;
    }

    public String getTurno() { return turno; }
    public void setTurno(String turno) { this.turno = turno; }

    @Override
    public double calcularSalario() {
        return getSalarioBase();
    }

    @Override
    public void trabalhar() {
        System.out.println(getNome() + " está atendendo no turno da " + turno + ".");
    }

    @Override
    public void relatarProgresso() {
        System.out.println(getNome() + " concluiu os atendimentos planejados.");
    }

    @Override
    public String toString() {
        // super.toString() já traz Nome, Matrícula e Salário Base
        return "[ATENDENTE]\n"
            + super.toString()
            + "\nTurno: " + turno;
    }

    @Override
    public String toText() {
        return "ATENDENTE;" 
            + getNome() + ";" 
            + getMatricula() + ";" 
            + getSalarioBase() + ";" 
            + turno;
    }
}
