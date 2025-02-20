package com.springdemo.dto;

import com.springdemo.model.Dose;

public class DoseDTO {
    private int id;
    private int id_vacina;
    private String dose;
    private int idadeRecomAplic;

    public DoseDTO (int id, int id_vacina, String dose, int idadeRecomAplic) {
        this.id = id;
        this.id_vacina = id_vacina;
        this.dose = dose;
        this.idadeRecomAplic = idadeRecomAplic;
    }

    public static DoseDTO fromDose(Dose dose) {
        return new DoseDTO(
                dose.getId(),
                dose.getId_vacina(),
                dose.getDose(),
                dose.getIdadeRecomAplic()
        );
    }

    public int getId() {
        return id;
    }

    public int getId_vacina() {
        return id_vacina;
    }

    public String getDose() {
        return dose;
    }

    public int getIdadeRecomAplic() {
        return idadeRecomAplic;
    }
}
