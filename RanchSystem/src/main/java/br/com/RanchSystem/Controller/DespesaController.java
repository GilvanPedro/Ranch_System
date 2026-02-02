package br.com.RanchSystem.Controller;

import br.com.RanchSystem.Entity.Despesa;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DespesaController {

    private String ARQUIVO = "Arquivos/despesas.json";

    List<Despesa> despesasLista = new ArrayList<>();
    File file;
    FileWriter escrita;

    Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .create();

    // LER ARQUIVO
    public void lerArquivo() {
        try {
            file = new File(ARQUIVO);

            if (!file.exists()) {
                despesasLista = new ArrayList<>();
                return;
            }

            FileReader reader = new FileReader(file);
            Type tipoLista = new TypeToken<List<Despesa>>() {}.getType();
            despesasLista = gson.fromJson(reader, tipoLista);

            if (despesasLista == null) {
                despesasLista = new ArrayList<>();
            }

            reader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // GERAR ID AUTOMÁTICO
    private int gerarProximoId() {

        int maiorId = 0;

        for (Despesa d : despesasLista) {
            if (d.getId() > maiorId) {
                maiorId = d.getId();
            }
        }

        return maiorId + 1;
    }

    // ADICIONAR DESPESA / GANHO
    public String adicionarDespesa(
            String descricao,
            String tipo,
            String categoria,
            double valor
    ) {

        lerArquivo();

        if (descricao == null || descricao.trim().isEmpty()) return "A descrição não pode estar vazia.";

        tipo = tipo.toUpperCase();
        categoria = categoria.toUpperCase();

        if (!tipoValido(tipo)) {
            return "Tipo inválido. Use GASTO ou GANHO.";
        }

        if (!categoriaValida(categoria)) {
            return "Categoria inválida.";
        }

        if (valor <= 0) {
            return "Valor deve ser maior que zero.";
        }

        int id = gerarProximoId();

        Despesa despesa = new Despesa(
                id,
                descricao,
                tipo,
                categoria,
                valor,
                new Date()
        );

        despesasLista.add(despesa);
        escreverArquivo();

        return "Sucesso";
    }

    // EDITAR DESPESA
    public String editarDespesa(int id, String descricao, String tipo, String categoria, double valor) {
        lerArquivo();
        
        if (descricao == null || descricao.trim().isEmpty()) return "A descrição não pode estar vazia.";
        tipo = tipo.toUpperCase();
        categoria = categoria.toUpperCase();
        if (!tipoValido(tipo)) return "Tipo inválido.";
        if (!categoriaValida(categoria)) return "Categoria inválida.";
        if (valor <= 0) return "Valor deve ser maior que zero.";

        for (int i = 0; i < despesasLista.size(); i++) {
            if (despesasLista.get(i).getId() == id) {
                Despesa antiga = despesasLista.get(i);
                Despesa nova = new Despesa(id, descricao, tipo, categoria, valor, antiga.getData());
                despesasLista.set(i, nova);
                escreverArquivo();
                return "Sucesso";
            }
        }
        return "Registro não encontrado.";
    }

    // LISTAR
    public void mostrarDespesas() {

        lerArquivo();

        if (despesasLista.isEmpty()) {
            System.out.println("Nenhuma despesa cadastrada.");
            return;
        }

        for (Despesa d : despesasLista) {
            System.out.println(d);
        }
    }

    // REMOVER POR ID
    public void removerDespesa(int id) {

        lerArquivo();

        boolean removido = false;

        for (int i = 0; i < despesasLista.size(); i++) {
            if (despesasLista.get(i).getId() == id) {
                despesasLista.remove(i);
                removido = true;
                break;
            }
        }

        if (removido) {
            escreverArquivo();
            System.out.println("Registro removido com sucesso.");
        } else {
            System.out.println("ID não encontrado.");
        }
    }

    // SALDO TOTAL
    public double calcularSaldo() {

        lerArquivo();

        double saldo = 0;

        for (Despesa d : despesasLista) {
            if (d.getTipo().equals("GANHO")) {
                saldo += d.getValor();
            } else {
                saldo -= d.getValor();
            }
        }

        return saldo;
    }

    // VALIDADORES
    private boolean tipoValido(String tipo) {
        return tipo.equals("GASTO") || tipo.equals("GANHO");
    }

    private boolean categoriaValida(String categoria) {
        return categoria.equals("GADO") ||
                categoria.equals("FUNCIONARIO") ||
                categoria.equals("PLANTACAO") ||
                categoria.equals("CONSERTOS") ||
                categoria.equals("CONTRATACAO") ||
                categoria.equals("OUTROS");
    }

    public List<Despesa> getDespesasLista() {
        lerArquivo();
        return despesasLista;
    }

    // ESCREVER JSON
    public void escreverArquivo() {
        try {
            File dir = new File("Arquivos");
            if (!dir.exists()) dir.mkdirs();
            escrita = new FileWriter(ARQUIVO);
            escrita.write(gson.toJson(despesasLista));
            escrita.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
