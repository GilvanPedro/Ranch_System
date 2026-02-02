package br.com.RanchSystem.Controller;

import br.com.RanchSystem.Entity.Gado;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class GadoController {

    private String ARQUIVO = "Arquivos/gados.json";

    List<Gado> gadosLista = new ArrayList<>();
    Gado gado;
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
                gadosLista = new ArrayList<>();
                return;
            }

            FileReader reader = new FileReader(file);
            Type tipoLista = new TypeToken<List<Gado>>() {}.getType();
            gadosLista = gson.fromJson(reader, tipoLista);

            if (gadosLista == null) {
                gadosLista = new ArrayList<>();
            }

            reader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // VERIFICAR RGN
    public boolean rgnExiste(long rgn) {
        for (Gado g : gadosLista) {
            if (g.getRgn() == rgn) {
                return true;
            }

        }
        return false;
    }

    // ADICIONAR GADO
    public String adicionarGado(String nome, String nascimento, String raca, String proprietario, String serie, long rgn, String categoria, double iABCZ, boolean sexo) {

        lerArquivo();

        if (nome == null || nome.trim().isEmpty()) return "O nome do gado não pode estar vazio.";
        if (rgn <= 0) return "O RGN deve ser um número positivo.";
        if (rgnExiste(rgn)) return "RGN já cadastrado.";

        gado = new Gado(
                nome,
                nascimento,
                raca,
                proprietario,
                serie,
                rgn,
                categoria,
                iABCZ,
                sexo
        );

        gadosLista.add(gado);
        return "Sucesso";
    }

    // EDITAR GADO
    public String editarGado(long rgnAntigo, String nome, String nascimento, String raca, String proprietario, String serie, long rgnNovo, String categoria, double iABCZ, boolean sexo) {
        lerArquivo();
        
        if (nome == null || nome.trim().isEmpty()) return "O nome do gado não pode estar vazio.";
        if (rgnNovo <= 0) return "O RGN deve ser um número positivo.";
        
        // Se mudou o RGN, verifica se o novo já existe
        if (rgnAntigo != rgnNovo && rgnExiste(rgnNovo)) return "Novo RGN já cadastrado.";

        for (int i = 0; i < gadosLista.size(); i++) {
            if (gadosLista.get(i).getRgn() == rgnAntigo) {
                Gado g = new Gado(nome, nascimento, raca, proprietario, serie, rgnNovo, categoria, iABCZ, sexo);
                gadosLista.set(i, g);
                escreverArquivo();
                return "Sucesso";
            }
        }
        return "Gado não encontrado.";
    }

    // MOSTRA OS GADOS SALVOS NO JSON
    public void mostrarGados() {

        lerArquivo();

        System.out.println("Lista de Gado:");

        if (gadosLista.isEmpty()) {
            System.out.println("Nenhum gado cadastrado.");
            return;
        }
        for (Gado g : gadosLista) {
            System.out.println(g);
        }
    }


    public void escreverArquivo() {
        try {
            File dir = new File("Arquivos");
            if (!dir.exists()) dir.mkdirs();
            escrita = new FileWriter(ARQUIVO);
            escrita.write(gson.toJson(gadosLista));
            escrita.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Gado> getGadosLista() {
        lerArquivo();
        return gadosLista;
    }

    public void removerGado(long rgn) {
        lerArquivo();

        boolean removido = false;

        for (int i = 0; i < gadosLista.size(); i++) {
            if (gadosLista.get(i).getRgn() == rgn) {
                gadosLista.remove(i);
                removido = true;
                break;
            }
        }

        if (removido) {
            escreverArquivo(); // Salva o JSON atualizado
            System.out.println("Gado removido com sucesso.");
        } else {
            System.out.println("Gado não encontrado.");
        }
    }

}
