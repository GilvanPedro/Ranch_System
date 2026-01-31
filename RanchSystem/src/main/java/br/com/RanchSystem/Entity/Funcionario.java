package br.com.RanchSystem.Entity;

public class Funcionario {
    private String nome;
    private long cpf;
    private double salario;
    private long telefone;

    public Funcionario(String nome, long cpf, double salario, long telefone) {
        this.nome = nome;
        this.cpf = cpf;
        this.salario = salario;
        this.telefone = telefone;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public long getCpf() {
        return cpf;
    }

    public void setCpf(long cpf) {
        this.cpf = cpf;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public long getTelefone() {
        return telefone;
    }

    public void setTelefone(long telefone) {
        this.telefone = telefone;
    }

    @Override
    public String toString() {
        return "Funcionario{" +
                "nome='" + nome + '\'' +
                ", cpf='" + cpf + '\'' +
                ", salario=" + salario +
                ", telefone=" + telefone +
                '}';
    }
}
