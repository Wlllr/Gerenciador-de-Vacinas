package com.springdemo.model;

public class Dose {
    private int id;
    private int id_vacina;
    private String dose;
    private int idadeRecomAplic;


    public Dose(int id, int id_vacina, String dose, int idadeRecomAplic) {
        this.id = id;
        this.id_vacina = id_vacina;
        this.dose = dose;
        this.idadeRecomAplic = idadeRecomAplic;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_vacina() {
        return id_vacina;
    }

    public void setId_vacina(int id_vacina) {
        this.id_vacina = id_vacina;
    }

    public String getDose() {
        return dose;
    }

    public void setDose(String dose) {
        this.dose = dose;
    }

    public int getIdadeRecomAplic() {
        return idadeRecomAplic;
    }

    public void setIdadeRecomAplic(int idadeRecomAplic) {
        this.idadeRecomAplic = idadeRecomAplic;
    }
}
