package com.springdemo.model;

public class Vacina {
    public enum PublicoALvo {
        CRIANÇA, ADOLESCENTE,ADULTO, GESTANTE;
    }

    private int id;
    private String vacina;
    private String descricao;
    private int limiteAplic;
    private PublicoALvo publicoALvo;

    public Vacina(int id, String vacina, String descricao, int limiteAplic, PublicoALvo publicoALvo) {
        this.id = id;
        this.vacina = vacina;
        this.descricao = descricao;
        this.limiteAplic = limiteAplic;
        this.publicoALvo = publicoALvo;
    }

    public PublicoALvo getPublicoALvo() {
        return publicoALvo;
    }

    public void setPublicoALvo(PublicoALvo publicoALvo) {
        this.publicoALvo = publicoALvo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVacina() {
        return vacina;
    }

    public void setVacina(String vacina) {
        this.vacina = vacina;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getLimiteAplic() {
        return limiteAplic;
    }

    public void setLimiteAplic(int limiteAplic) {
        this.limiteAplic = limiteAplic;
    }

}
