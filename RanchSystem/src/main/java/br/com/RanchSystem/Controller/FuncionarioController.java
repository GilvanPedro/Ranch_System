package br.com.RanchSystem.Controller;

import br.com.RanchSystem.Entity.Funcionario;
import br.com.RanchSystem.Logicas.ValidarCpf;
import br.com.RanchSystem.Logicas.ValidarTelefone;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class FuncionarioController {

    private String ARQUIVO = "funcionarios.json";

    List<Funcionario> funcionariosLista = new ArrayList<>();
    Funcionario funcionario;
    ValidarTelefone validarTelefone = new ValidarTelefone();
    ValidarCpf validarCpf = new ValidarCpf();
    File file;
    FileWriter escrita;

    Gson gson = new GsonBuilder()
            .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
            .setPrettyPrinting()
            .create();

    // LER O ARQUIVO
    public void lerArquivo() {
        try {
            file = new File(ARQUIVO);

            if (!file.exists()) {
                funcionariosLista = new ArrayList<>();
                return;
            }

            FileReader reader = new FileReader(file);
            Type tipoLista = new TypeToken<List<Funcionario>>() {}.getType();
            funcionariosLista = gson.fromJson(reader, tipoLista);

            if (funcionariosLista == null) {
                funcionariosLista = new ArrayList<>();
            }

            reader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // VERIFICAR CPF
    public boolean cpfExiste(String cpf) {
        for (Funcionario f : funcionariosLista) {
            if (f.getCpf().equals(cpf)) {
                return true;
            }

        }
        return false;
    }

    // ADICIONAR SOMENTE SE CPF NÃO EXISTIR
    public void adicionarFuncionario(String nome, String cpf, double salario, String telefone) {

        lerArquivo();

        if (!validarCpf.cpfValido(cpf)) {
            System.out.println("CPF inválido.");
            return;
        }

        if (!validarTelefone.telefoneValido(telefone)) {
            System.out.println("Telefone inválido.");
            return;
        }

        if (cpfExiste(cpf)) {
            System.out.println("CPF já cadastrado.");
            return;
        }

        funcionario = new Funcionario(
                nome,
                cpf,
                salario,
                telefone.replaceAll("\\D", "")
        );

        funcionariosLista.add(funcionario);
    }



    // MOSTRA OS FUNCIONARIOS SALVOS NO JSON
    public void mostrarFuncionarios() {

        lerArquivo();

        System.out.println("Lista de Funcionarios:");

        if (funcionariosLista.isEmpty()) {
            System.out.println("Nenhum funcionário cadastrado.");
            return;
        }
        for (Funcionario f : funcionariosLista) {
            System.out.println(f);
        }
    }


    public void escreverArquivo() {
        try {
            escrita = new FileWriter(ARQUIVO);
            escrita.write(gson.toJson(funcionariosLista));
            escrita.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void removerFuncionario(String cpf) {
        lerArquivo();

        boolean removido = false;

        for (int i = 0; i < funcionariosLista.size(); i++) {
            if (funcionariosLista.get(i).getCpf().equals(cpf)) {
                funcionariosLista.remove(i);
                removido = true;
                break;
            }
        }

        if (removido) {
            escreverArquivo(); // Salva o JSON atualizado
            System.out.println("Funcionário removido com sucesso.");
        } else {
            System.out.println("Funcionário não encontrado.");
        }
    }

}
