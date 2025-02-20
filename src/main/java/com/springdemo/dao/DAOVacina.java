package com.springdemo.dao;

import com.springdemo.config.Conexao;
import com.springdemo.model.Vacina;

import java.sql.*;
import java.util.ArrayList;

public class DAOVacina {
    public static Connection conexao;

    static {
        try {
            conexao = Conexao.getConexao();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao conectar ao banco de dados: " + e.getMessage());
        }
    }

    public static ArrayList<Vacina> consultarVacinas() throws SQLException {
        ArrayList<Vacina> listaVacinas = new ArrayList<>();

        String sql = "SELECT * FROM vacinacao.vacina";

        try (   Statement comando = conexao.createStatement();
                ResultSet resultado = comando.executeQuery(sql);
        ) {
          while (resultado.next()) {
              Vacina novaVacina = new Vacina(
                      resultado.getInt("id"),
                      resultado.getString("vacina"),
                      resultado.getString("descricao"),
                      resultado.getInt("limite_aplicacao"),
                      Vacina.PublicoALvo.valueOf(resultado.getString("publico_alvo"))
              );

              listaVacinas.add(novaVacina);
          }
        }
        return listaVacinas;
    }

    public static ArrayList<Vacina> consultarVacinaPFaixa(String publicoALvo) throws SQLException {
        ArrayList<Vacina> listaVacinas = new ArrayList<>();

        String sql = "SELECT * FROM vacinacao.vacina WHERE publico_alvo = ?";

        try (PreparedStatement comando = conexao.prepareStatement(sql)) {
            comando.setString(1, publicoALvo);


            ResultSet resultado = comando.executeQuery();

            while (resultado.next()) {
                Vacina novaVacina = new Vacina(
                    resultado.getInt("id"),
                    resultado.getString("vacina"),
                    resultado.getString("descricao"),
                    resultado.getInt("limite_aplicacao"),
                    Vacina.PublicoALvo.valueOf(resultado.getString("publico_alvo").toUpperCase().trim())
                );

                listaVacinas.add(novaVacina);
            }

            return listaVacinas;
        }
    }

    public static ArrayList<Vacina> consultarVacinaPIdadeMaior(int meses) throws SQLException {
        ArrayList<Vacina> listaVacinas = new ArrayList<>();

        String sql = " SELECT * FROM vacinacao.vacina WHERE limite_aplicacao > ?";

        try (PreparedStatement comando = conexao.prepareStatement(sql)) {
            comando.setInt(1, meses);

            ResultSet resultado = comando.executeQuery();

            while (resultado.next()) {
                Vacina novaVacina = new Vacina(
                        resultado.getInt("id"),
                        resultado.getString("vacina"),
                        resultado.getString("descricao"),
                        resultado.getInt("limite_aplicacao"),
                        Vacina.PublicoALvo.valueOf(resultado.getString("publico_alvo").toUpperCase().trim())
                );

                listaVacinas.add(novaVacina);
            }

            return listaVacinas;
        }
    }

    public static ArrayList<Vacina> consultarVacinaNaoAplicaveis(int id) throws SQLException{
        ArrayList<Vacina> listaVacinas = new ArrayList<>();

        String sql = """
            SELECT * 
            FROM vacina v
            WHERE v.limite_aplicacao < (
                SELECT TIMESTAMPDIFF(MONTH, p.data_nascimento, CURDATE()) 
                FROM paciente p 
                WHERE p.id = ?
            )
        """;

        try (PreparedStatement comando = conexao.prepareStatement(sql)) {
            comando.setInt(1, id);

            ResultSet resultado = comando.executeQuery();

            while (resultado.next()) {
                Vacina novaVacina = new Vacina(
                        resultado.getInt("id"),
                        resultado.getString("vacina"),
                        resultado.getString("descricao"),
                        resultado.getInt("limite_aplicacao"),
                        Vacina.PublicoALvo.valueOf(resultado.getString("publico_alvo"))
                );

                listaVacinas.add(novaVacina);
            }

            return listaVacinas;
        }
    }
}
