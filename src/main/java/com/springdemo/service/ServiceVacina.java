package com.springdemo.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springdemo.dao.DAOVacina;
import com.springdemo.dto.VacinaDTO;
import com.springdemo.model.Vacina;
import spark.Request;
import spark.Response;
import spark.Route;

import java.util.ArrayList;
import java.util.List;

public class ServiceVacina {
    public static Route consultarVacinas(){
        return new Route() {
            @Override
            public Object handle(Request request, Response response) throws Exception {
                ObjectMapper converteJson = new ObjectMapper();

                ArrayList<Vacina> vacinas = DAOVacina.consultarVacinas();

                if (!vacinas.isEmpty()) {
                    response.status(200);
                    return converteJson.writeValueAsString(VacinaDTO.toDTOList(vacinas));
                } else {
                    response.status(404);
                    return "{\"message\": \"Nenhuma vacina encontrada no banco de dados.\"}";
                }
            }
        };
    }

    public static Route consultarVacinaPFaixa() {
        return new Route() {
            @Override
            public Object handle(Request request, Response response) throws Exception {
                ObjectMapper converteJson = new ObjectMapper();

                try {
                    String publicoALvo = request.params(":faixa");
                    ArrayList<Vacina> vacinasPFaixaEtaria = DAOVacina.consultarVacinaPFaixa(publicoALvo);

                    if (!vacinasPFaixaEtaria.isEmpty()) {
                        response.status(200);
                        return converteJson.writeValueAsString(VacinaDTO.toDTOList(vacinasPFaixaEtaria));
                    } else {
                        response.status(204);
                        return "{\"message\": \"Nenhum registro dessas vacinas por faixa foi encontrado.\"}" ;
                    }

                } catch (IllegalArgumentException e) {
                    response.status(400);
                    return "{\"message\": \"Faixa etária inválida. Use 'CRIANÇA' ou 'ADULTO'.\"}";
                } catch (Exception e) {
                    response.status(500);
                    return "{\"message\": \"Erro interno no servidor.\"}";
                }

            }
        };
    }

    public static Route consultarVacinaPIdadeMaior() throws Exception{
        return new Route() {
            @Override
            public Object handle(Request request, Response response) throws Exception {
                ObjectMapper converteJson = new ObjectMapper();

                try {
                    int idade = Integer.parseInt(request.params(":meses"));
                    ArrayList<Vacina> vacinasIdade = DAOVacina.consultarVacinaPIdadeMaior(idade);

                    if (!vacinasIdade.isEmpty()) {

                        response.status(200);
                        return converteJson.writeValueAsString(VacinaDTO.toDTOList(vacinasIdade));
                    } else {
                        response.status(204);
                        return "{\"message\": \"Nenhum registro dessas vacinas por idade foi encontrado.\"}" ;
                    }
                } catch (Exception e) {
                    response.status(500);
                    return "{\"message\": \"Erro interno no servidor.\"}";
                }
            }
        };
    }

    public static Route consultarVacinaNaoAplicaveis() throws Exception {
        return new Route() {
            @Override
            public Object handle(Request request, Response response) throws Exception {
                ObjectMapper converteJson = new ObjectMapper();

                try {
                    int id = Integer.parseInt(request.params(":id"));
                    ArrayList<Vacina> vacinas = DAOVacina.consultarVacinaNaoAplicaveis(id);

                    if (!vacinas.isEmpty()) {
                        response.status(200);
                        return converteJson.writeValueAsString(VacinaDTO.toDTOList(vacinas));
                    } else {
                        response.status(204);
                        return "{\"message\": \"Nenhuma vacina disponível para este paciente.\"}";
                    }
                } catch (Exception e) {
                    response.status(400);
                    return "{\"message\": \"Erro ao buscar vacinas para o paciente.\"}";
                }
            }
        };
    }


}
