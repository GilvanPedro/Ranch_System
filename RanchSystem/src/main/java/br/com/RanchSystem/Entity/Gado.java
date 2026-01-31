package br.com.RanchSystem.Entity;

import java.util.Date;

public class Gado {
    private String nome;
    private Date nascimento;
    private String raca;
    private String proprietario;
    private String serie;
    private long rgn;
    private String categoria;
    private byte totalFilhos;
    private boolean sexo;

    public Gado(String nome, Date nascimento, String raca, String proprietario, String serie, long rgn, String categoria, byte totalFilhos,  boolean sexo) {
        this.nome = nome;
        this.nascimento = nascimento;
        this.raca = raca;
        this.proprietario = proprietario;
        this.serie = serie;
        this.rgn = rgn;
        this.categoria = categoria;
        this.totalFilhos = totalFilhos;
        this.sexo = sexo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getNascimento() {
        return nascimento;
    }

    public void setNascimento(Date nascimento) {
        this.nascimento = nascimento;
    }

    public String getRaca() {
        return raca;
    }

    public void setRaca(String raca) {
        this.raca = raca;
    }

    public String getProprietario() {
        return proprietario;
    }

    public void setProprietario(String proprietario) {
        this.proprietario = proprietario;
    }

    public long getRgn() {
        return rgn;
    }

    public void setRgn(long rgn) {
        this.rgn = rgn;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public boolean isSexo() {
        return sexo;
    }

    public void setSexo(boolean sexo) {
        this.sexo = sexo;
    }

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public byte getTotalFilhos() {
        return totalFilhos;
    }

    public void setTotalFilhos(byte totalFilhos) {
        this.totalFilhos = totalFilhos;
    }
}
