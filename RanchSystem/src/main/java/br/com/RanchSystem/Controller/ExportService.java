package br.com.RanchSystem.Controller;

import br.com.RanchSystem.Entity.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

public class ExportService {

    private static final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public static String exportToTxt(String title, List<?> data, String filePath) {
        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write("=== RELATÓRIO: " + title.toUpperCase() + " ===\n");
            writer.write("Gerado em: " + sdf.format(new java.util.Date()) + "\n");
            writer.write("------------------------------------------\n\n");
            
            for (Object obj : data) {
                writer.write(obj.toString() + "\n");
            }
            
            return "Sucesso";
        } catch (IOException e) {
            return "Erro ao exportar TXT: " + e.getMessage();
        }
    }

    public static String exportToJson(List<?> data, String filePath) {
        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write(gson.toJson(data));
            return "Sucesso";
        } catch (IOException e) {
            return "Erro ao exportar JSON: " + e.getMessage();
        }
    }

    public static String exportToCsv(String type, List<?> data, String filePath) {
        try (FileWriter writer = new FileWriter(filePath)) {
            switch (type.toLowerCase()) {
                case "funcionarios":
                    writer.write("Nome;CPF;Salario;Telefone\n");
                    for (Object obj : data) {
                        Funcionario f = (Funcionario) obj;
                        writer.write(f.getNome() + ";" + f.getCpf() + ";" + f.getSalario() + ";" + f.getTelefone() + "\n");
                    }
                    break;
                case "gado":
                    writer.write("Nome;RGN;Raca;Proprietario;Serie;Sexo;Categoria;Filhos\n");
                    for (Object obj : data) {
                        Gado g = (Gado) obj;
                        writer.write(g.getNome() + ";" + g.getRgn() + ";" + g.getRaca() + ";" + g.getProprietario() + ";" + 
                                     g.getSerie() + ";" + (g.isSexo() ? "Macho" : "Femea") + ";" + g.getCategoria() + ";" + g.getIABCZ() + "\n");
                    }
                    break;
                case "plantacoes":
                    writer.write("Tipo;Hectares;DataPlantio;DataColheita;Agrotoxicos\n");
                    SimpleDateFormat dsf = new SimpleDateFormat("dd/MM/yyyy");
                    for (Object obj : data) {
                        Plantacao p = (Plantacao) obj;
                        writer.write(p.getTipo() + ";" + p.getHectares() + ";" + dsf.format(p.getDataPlantacao()) + ";" + 
                                     dsf.format(p.getDataColheita()) + ";" + (p.isAgrotoxicos() ? "Sim" : "Nao") + "\n");
                    }
                    break;
                case "financeiro":
                    writer.write("ID;Descricao;Tipo;Categoria;Valor;Data\n");
                    for (Object obj : data) {
                        Despesa d = (Despesa) obj;
                        writer.write(d.getId() + ";" + d.getDescricao() + ";" + d.getTipo() + ";" + d.getCategoria() + ";" + 
                                     d.getValor() + ";" + sdf.format(d.getData()) + "\n");
                    }
                    break;
            }
            return "Sucesso";
        } catch (IOException e) {
            return "Erro ao exportar CSV: " + e.getMessage();
        }
    }
}
