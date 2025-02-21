package com.springdemo.dao;

import com.mysql.cj.protocol.Resultset;
import com.springdemo.config.Conexao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DAOEstatisticas {
    public static Connection conexao;

    static {
        try {
            conexao = Conexao.getConexao();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao conectar ao banco de dados: " + e.getMessage());
        }
    }

    public static int vacinasPorPaciente(int id) throws SQLException {
        String sql = "SELECT COUNT(*) AS total_vacinas_aplicadas FROM imunizacoes WHERE id_paciente = ?";

        try (PreparedStatement comando = conexao.prepareStatement(sql)) {
            comando.setInt(1, id);

            ResultSet resultado = comando.executeQuery();


            if (resultado.next()) {
                return resultado.getInt("total_vacinas_aplicadas");
            }
        }
        return 0;
    }

    public static int vacinasProxMes(int id) throws SQLException {
        String sql = """
        SELECT COUNT(*) AS total_vacinas_aplicaveis
        FROM dose d
        JOIN vacina v ON d.id_vacina = v.id
        JOIN paciente p ON p.id = ?
        LEFT JOIN imunizacoes i ON d.id = i.id_dose AND i.id_paciente = p.id
        WHERE i.id IS NULL
        AND TIMESTAMPDIFF(MONTH, p.data_nascimento, CURDATE()) + 1 >= d.idade_recomendada_aplicacao
        AND (v.limite_aplicacao IS NULL OR TIMESTAMPDIFF(MONTH, p.data_nascimento, CURDATE()) + 1 <= v.limite_aplicacao);
    """;

        try (PreparedStatement comando = conexao.prepareStatement(sql)) {
            comando.setInt(1, id);

            ResultSet resultado = comando.executeQuery();

            if(resultado.next()) {
                return resultado.getInt("total_vacinas_aplicaveis");
            }
        }
        return 0;
    }

    public static int vacinasAtrasadas(int id) throws SQLException{
        String sql = """
                SELECT COUNT(*) AS total_atrasadas
                FROM dose d
                JOIN vacina v ON d.id_vacina = v.id
                JOIN paciente p ON p.id = ?
                LEFT JOIN imunizacoes i ON d.id = i.id_dose AND i.id_paciente = p.id
                WHERE i.id IS NULL  -- Dose ainda não foi aplicada
                AND TIMESTAMPDIFF(MONTH, p.data_nascimento, CURDATE()) >= d.idade_recomendada_aplicacao;
                
                """;

        try (PreparedStatement comando = conexao.prepareStatement(sql)) {
            comando.setInt(1, id);

            ResultSet resultado = comando.executeQuery();

            if (resultado.next()) {
                return resultado.getInt("total_atrasadas");
            }
        }

        return 0;
    }

    public static int vacinaDeterminadaPorIdade(int idade) throws SQLException{
        String sql = "SELECT COUNT(*) AS total_vacinas FROM dose WHERE idade_recomendada_aplicacao > ?";

        try (PreparedStatement comando = conexao.prepareStatement(sql)) {
            comando.setInt(1, idade);

            ResultSet resultado = comando.executeQuery();

            if (resultado.next()) {
                return resultado.getInt("total_vacinas");
            }
        }
        return 0;
    }

    public static int vacinasNaoAplicaveis(int id) throws SQLException {
        String sql = """
            SELECT COUNT(*) AS vacinas_nao_aplicaveis
            FROM vacina v
            WHERE v.limite_aplicacao < (
                SELECT TIMESTAMPDIFF(MONTH, p.data_nascimento, CURDATE())
                FROM paciente p
                WHERE p.id = ?)
        """;

        try (PreparedStatement comando = conexao.prepareStatement(sql)) {
            comando.setInt(1, id);

            ResultSet resultado = comando.executeQuery();

            if (resultado.next()) {
                return resultado.getInt("vacinas_nao_aplicaveis");
            }
        }
        return 0;

    }
}
