package br.com.RanchSystem.Controller;

import br.com.RanchSystem.Entity.Funcionario;

import java.util.ArrayList;
import java.util.List;

public class FuncionarioController {
    List<Funcionario> funcionariosLista =  new ArrayList<>();
    Funcionario funcionario;

    public void adicionarFuncionario(String nome, long cpf, double salario, long telefone){
         funcionario = new Funcionario(nome,cpf,salario,telefone);
         funcionariosLista.add(funcionario);
    }

    public void mostrarFuncionarios(){
        System.out.println("Lista de Funcionarios:");
        System.out.println("Nome: "+funcionario.getNome()+" | CPF: "+funcionario.getCpf()+" | Salario: "+funcionario.getSalario()+" | Telefone: "+funcionario.getTelefone());
    }
}
