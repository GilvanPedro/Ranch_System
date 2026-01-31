package br.com.RanchSystem.Entity;

import java.util.Date;

public class Plantacao {
    private String tipo;
    private Date dataPlantacao;
    private Date dataColheita;
    private double hectares;
    private boolean agrotoxicos;

    public Plantacao(String tipo, Date dataPlantacao, Date dataColheita, double hectares, boolean agrotoxicos) {
        this.tipo = tipo;
        this.dataPlantacao = dataPlantacao;
        this.dataColheita = dataColheita;
        this.hectares = hectares;
        this.agrotoxicos = agrotoxicos;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Date getDataPlantacao() {
        return dataPlantacao;
    }

    public void setDataPlantacao(Date dataPlantacao) {
        this.dataPlantacao = dataPlantacao;
    }

    public Date getDataColheita() {
        return dataColheita;
    }

    public void setDataColheita(Date dataColheita) {
        this.dataColheita = dataColheita;
    }

    public double getHectares() {
        return hectares;
    }

    public void setHectares(double hectares) {
        this.hectares = hectares;
    }

    public boolean isAgrotoxicos() {
        return agrotoxicos;
    }

    public void setAgrotoxicos(boolean agrotoxicos) {
        this.agrotoxicos = agrotoxicos;
    }
}
