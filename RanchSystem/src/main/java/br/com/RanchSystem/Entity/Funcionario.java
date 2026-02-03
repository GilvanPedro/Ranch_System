package br.com.RanchSystem.Entity;

public class Funcionario {
    private String nome;
    private String cpf;
    private double salario;
    private String telefone;

    public Funcionario(String nome, String cpf, double salario, String telefone) {
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

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    @Override
    public String toString() {
        return "Nome: "+nome+" | CPF: "+cpf+" | Salario: "+salario+" | Telefone: "+telefone;
    }
}
