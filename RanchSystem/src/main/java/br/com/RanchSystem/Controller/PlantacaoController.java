package br.com.RanchSystem.Controller;

import br.com.RanchSystem.Entity.Plantacao;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class PlantacaoController {

    private String ARQUIVO = "Arquivos/plantacoes.json";

    List<Plantacao> plantacoesLista = new ArrayList<>();
    Plantacao plantacao;
    File file;
    FileWriter escrita;

    Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .create();

    // LER O ARQUIVO
    public void lerArquivo() {
        try {
            file = new File(ARQUIVO);

            if (!file.exists()) {
                plantacoesLista = new ArrayList<>();
                return;
            }

            FileReader reader = new FileReader(file);
            Type tipoLista = new TypeToken<List<Plantacao>>() {}.getType();
            plantacoesLista = gson.fromJson(reader, tipoLista);

            if (plantacoesLista == null) {
                plantacoesLista = new ArrayList<>();
            }

            reader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // ADICIONAR PLANTAÇÃO
    public String adicionarPlantacao(
            String tipo,
            Date dataPlantacao,
            double hectares,
            boolean agrotoxicos
    ) {

        lerArquivo();

        if (tipo == null || tipo.trim().isEmpty()) return "O tipo de plantação não pode estar vazio.";
        if (hectares <= 0) return "A área em hectares deve ser maior que zero.";

        int diasCrescimento = diasPorTipo(tipo);

        if (diasCrescimento == -1) {
            return "Tipo de plantação inválido. Use: Café, Milho, Cana, Trigo ou Árvore.";
        }

        Date dataColheita = calcularDataColheita(dataPlantacao, diasCrescimento);

        plantacao = new Plantacao(
                tipo,
                dataPlantacao,
                dataColheita,
                hectares,
                agrotoxicos
        );

        plantacoesLista.add(plantacao);
        escreverArquivo();

        return "Sucesso";
    }

    // EDITAR PLANTAÇÃO
    public String editarPlantacao(String tipoOriginal, Date dataOriginal, String novoTipo, double hectares, boolean agrotoxicos) {
        lerArquivo();
        
        if (novoTipo == null || novoTipo.trim().isEmpty()) return "O tipo de plantação não pode estar vazio.";
        if (hectares <= 0) return "A área em hectares deve ser maior que zero.";

        int diasCrescimento = diasPorTipo(novoTipo);
        if (diasCrescimento == -1) return "Tipo de plantação inválido.";

        for (int i = 0; i < plantacoesLista.size(); i++) {
            Plantacao p = plantacoesLista.get(i);
            if (p.getTipo().equalsIgnoreCase(tipoOriginal) && p.getDataPlantacao().equals(dataOriginal)) {
                Date dataColheita = calcularDataColheita(p.getDataPlantacao(), diasCrescimento);
                Plantacao nova = new Plantacao(novoTipo, p.getDataPlantacao(), dataColheita, hectares, agrotoxicos);
                plantacoesLista.set(i, nova);
                escreverArquivo();
                return "Sucesso";
            }
        }
        return "Plantação não encontrada.";
    }

    // CALCULAR DATA DA COLHEITA
    private Date calcularDataColheita(Date dataPlantacao, int dias) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dataPlantacao);
        calendar.add(Calendar.DAY_OF_MONTH, dias);
        return calendar.getTime();
    }

    // DIAS DE CRESCIMENTO POR TIPO
    private int diasPorTipo(String tipo) {
        switch (tipo.toLowerCase()) {
            case "café":
            case "cafe":
                return 730;

            case "milho":
                return 120;

            case "cana":
            case "cana-de-açucar":
                return 365;

            case "trigo":
                return 150;

            case "árvore":
            case "arvore":
                return 1825;

            default:
                return -1;
        }
    }

    // LISTAR PLANTAÇÕES
    public void listarPlantacoes() {

        lerArquivo();

        System.out.println("Lista de Plantações:");

        if (plantacoesLista.isEmpty()) {
            System.out.println("Nenhuma plantação cadastrada.");
            return;
        }

        for (Plantacao p : plantacoesLista) {
            System.out.println(
                    "Tipo: " + p.getTipo() +
                            " | Plantio: " + p.getDataPlantacao() +
                            " | Colheita: " + p.getDataColheita() +
                            " | Hectares: " + p.getHectares() +
                            " | Agrotóxicos: " + (p.isAgrotoxicos() ? "Sim" : "Não")
            );
        }
    }

    // REMOVER PLANTAÇÃO (por tipo + data de plantio)
    public List<Plantacao> getPlantacoesLista() {
        lerArquivo();
        return plantacoesLista;
    }

    public void removerPlantacao(String tipo, Date dataPlantacao) {

        lerArquivo();

        boolean removido = false;

        for (int i = 0; i < plantacoesLista.size(); i++) {
            Plantacao p = plantacoesLista.get(i);

            if (p.getTipo().equalsIgnoreCase(tipo)
                    && p.getDataPlantacao().equals(dataPlantacao)) {

                plantacoesLista.remove(i);
                removido = true;
                break;
            }
        }

        if (removido) {
            escreverArquivo();
            System.out.println("Plantação removida com sucesso.");
        } else {
            System.out.println("Plantação não encontrada.");
        }
    }

    // ESCREVER NO JSON
    public void escreverArquivo() {
        try {
            File dir = new File("Arquivos");
            if (!dir.exists()) dir.mkdirs();
            escrita = new FileWriter(ARQUIVO);
            escrita.write(gson.toJson(plantacoesLista));
            escrita.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
