package com.springdemo.dto;

import com.springdemo.model.Vacina;

import java.util.List;

public class VacinaDTO {
    private int id;
    private String nome;
    private String descricao;
    private int limiteAplicacao;
    private String publicoAlvo;

    public VacinaDTO(int id, String nome, String descricao, int limiteAplicacao, String publicoAlvo) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.limiteAplicacao = limiteAplicacao;
        this.publicoAlvo = publicoAlvo;
    }

    //converte Vacina para VacinaDTO
    public static VacinaDTO fromVacina(Vacina vacina) {
        return new VacinaDTO(
                vacina.getId(),
                vacina.getVacina(),
                vacina.getDescricao(),
                vacina.getLimiteAplic(),
                vacina.getPublicoALvo().name() //converte Enum para String
        );
    }

    //Faz conversao do tipo Vacina para VacinaDTO e tambem ja usa o stream
    //E praticamente um generics usa vez que pega o codigo e padroniza ele em unico local
    public static List<VacinaDTO> toDTOList(List<Vacina> vacinas) {
        return vacinas.stream().map(VacinaDTO::fromVacina).toList();
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public int getLimiteAplicacao() {
        return limiteAplicacao;
    }

    public String getPublicoAlvo() {
        return publicoAlvo;
    }
}
