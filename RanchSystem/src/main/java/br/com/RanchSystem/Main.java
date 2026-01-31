package br.com.RanchSystem;

import br.com.RanchSystem.Controller.FuncionarioController;
import br.com.RanchSystem.Entity.Funcionario;

public class Main {
    public static void main(String[] args) {
        FuncionarioController funcionarioController =  new FuncionarioController();

        funcionarioController.adicionarFuncionario("Marcos", 4385784, 3434, 454345);
        funcionarioController.mostrarFuncionarios();

    }
}
