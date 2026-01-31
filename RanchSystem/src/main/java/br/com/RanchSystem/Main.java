package br.com.RanchSystem;

import br.com.RanchSystem.Controller.FuncionarioController;

public class Main {
    public static void main(String[] args) {
        FuncionarioController funcionarioController =  new FuncionarioController();

        funcionarioController.adicionarFuncionario("Fernanda Macedo", "40893885037", 1300.08, "62934583490");
        funcionarioController.escreverArquivo();

        funcionarioController.mostrarFuncionarios();
    }
}
