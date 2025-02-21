package com.springdemo.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.springdemo.dao.DAOEstatisticas;
import com.springdemo.dto.VacinaDTO;
import spark.Request;
import spark.Response;
import spark.Route;

import java.sql.SQLException;

public class ServiceEstatistica {
    public static Route vacinaPorPaciente() throws SQLException{
        return new Route() {
            @Override
            public Object handle(Request request, Response response) throws Exception {
                ObjectMapper converteJson = new ObjectMapper();

                try {
                    int idPaciente = Integer.parseInt(request.params(":id"));
                    int totalVacinas = DAOEstatisticas.vacinasPorPaciente(idPaciente);

                    if (totalVacinas >= 1) {
                        response.status(200);
                        return converteJson.writeValueAsString(totalVacinas);
                    } else {
                        response.status(204);
                        return "{\"message\": \"Nenhuma vacina aplicada foi encontrada para este paciente.\"}";
                    }

                } catch (NumberFormatException e) {
                    response.status(400);
                    return "{\"error\": \"ID do paciente inválido.\"}";
                }

            }
        };
    }

    public static Route vacinasProximoMes() {
        return (request, response) -> {
            ObjectMapper converteJson = new ObjectMapper();

            try {
                int idPaciente = Integer.parseInt(request.params(":id"));

                int qtdeVacinasProxMes = DAOEstatisticas.vacinasProxMes(idPaciente);

                if (qtdeVacinasProxMes > 0) {
                    response.status(200);
                    return converteJson.writeValueAsString(qtdeVacinasProxMes);
                } else {
                    response.status(204);
                    return "{\"message\": \"Nenhuma vacina programada para o próximo mês.\"}";
                }
            } catch (NumberFormatException e) {
                response.status(400);
                return "{\"error\": \"ID do paciente inválido.\"}";
            } catch (SQLException e) {
                response.status(500);
                return "{\"error\": \"Erro no banco de dados ao buscar as vacinas.\"}";
            }
        };
    }

    public static Route vacinasAtrasadas() {
        return new Route() {
            @Override
            public Object handle(Request request, Response response) throws Exception {
                ObjectMapper converteJson = new ObjectMapper();

                try {
                    int idPaciente = Integer.parseInt(request.params(":id"));
                    int qtdeVacinasAtrasadas = DAOEstatisticas.vacinasAtrasadas(idPaciente);

                    if (qtdeVacinasAtrasadas > 0) {
                        response.status(200);
                        return converteJson.writeValueAsString(qtdeVacinasAtrasadas);
                    } else {
                        response.status(204);
                        return "{\"message\": \"Nenhuma vacina atrasada.\"}";
                    }
                } catch (NumberFormatException e) {
                    response.status(400);
                    return "{\"error\": \"ID do paciente inválido.\"}";
                } catch (SQLException e) {
                    response.status(500);
                    return "{\"error\": \"Erro no banco de dados ao buscar as vacinas.\"}";
                }
            }
        };
    }

    public static Route vacinaPorIdade() {
        return new Route() {
            @Override
            public Object handle(Request request, Response response) throws Exception {
                ObjectMapper converteJson = new ObjectMapper();

                try {
                    int idadeMesPaciente = Integer.parseInt(request.params(":meses"));
                    int qtdePorIdade = DAOEstatisticas.vacinaDeterminadaPorIdade(idadeMesPaciente);

                    if (qtdePorIdade > 0) {
                        response.status(200);
                        return converteJson.writeValueAsString(qtdePorIdade);
                    } else {
                        response.status(204);
                        return "{\"message\": \"Nenhuma vacina por idade encontrada.\"}";
                    }
                } catch (NumberFormatException e) {
                    response.status(400);
                    return "{\"error\": \"ID do paciente inválido.\"}";
                } catch (SQLException e) {
                    response.status(500);
                    return "{\"error\": \"Erro no banco de dados ao buscar as vacinas.\"}";
                }

            }
        };
    }

    public static Route qtdeVacinasNaoAplicaveis() {
        return new Route() {
            @Override
            public Object handle(Request request, Response response) throws Exception {
                ObjectMapper converteJson = new ObjectMapper();

                try {
                    int idPaciente = Integer.parseInt(request.params(":id"));
                    int qtdeVacinas = DAOEstatisticas.vacinasNaoAplicaveis(idPaciente);

                    if (qtdeVacinas > 0) {
                        response.status(200);
                        return converteJson.writeValueAsString(qtdeVacinas);
                    } else {
                        response.status(204);
                        return "{\"message\": \"Voce esta em dia com suas vacinas. Nenhuma a ser aplicada.\"}";
                    }
                } catch (NumberFormatException e) {
                    response.status(400);
                    return "{\"error\": \"ID do paciente inválido.\"}";
                } catch (SQLException e) {
                    response.status(500);
                    return "{\"error\": \"Erro no banco de dados ao buscar as vacinas.\"}";
                }

            }
        };
    }
}