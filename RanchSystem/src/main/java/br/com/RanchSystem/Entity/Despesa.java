package br.com.RanchSystem.Entity;

import java.util.Date;

public class Despesa {

    private int id;
    private String descricao;
    private String tipo;       // GASTO ou GANHO
    private String categoria;  // GADO, FUNCIONARIO, PLANTACAO, etc
    private double valor;
    private Date data;

    public Despesa(int id, String descricao, String tipo, String categoria, double valor, Date data) {
        this.id = id;
        this.descricao = descricao;
        this.tipo = tipo;
        this.categoria = categoria;
        this.valor = valor;
        this.data = data;
    }

    public int getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getTipo() {
        return tipo;
    }

    public String getCategoria() {
        return categoria;
    }

    public double getValor() {
        return valor;
    }

    public Date getData() {
        return data;
    }

    @Override
    public String toString() {
        return "ID: " + id +
                " | Descrição: " + descricao +
                " | Tipo: " + tipo +
                " | Categoria: " + categoria +
                " | Valor: R$ " + valor +
                " | Data: " + data;
    }
}