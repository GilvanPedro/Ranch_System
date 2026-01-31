package br.com.RanchSystem.Entity;

public class Gado {
    private long id;
    private String nome;
    private String nascimento;
    private String raca;
    private String RGN;
    private boolean sexo;

    public Gado(long id, String nome, String nascimento, String raca, String RGN,  boolean sexo) {
        this.id = id;
        this.nome = nome;
        this.nascimento = nascimento;
        this.raca = raca;
        this.RGN = RGN;
        this.sexo = sexo;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNascimento() {
        return nascimento;
    }

    public void setNascimento(String nascimento) {
        this.nascimento = nascimento;
    }

    public String getRaca() {
        return raca;
    }

    public void setRaca(String raca) {
        this.raca = raca;
    }

    public String getRGN() {
        return RGN;
    }

    public void setRGN(String RGN) {
        this.RGN = RGN;
    }

    public boolean isSexo() {
        return sexo;
    }

    public void setSexo(boolean sexo) {
        this.sexo = sexo;
    }
}
