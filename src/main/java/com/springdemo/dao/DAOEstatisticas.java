package com.springdemo.dao;

import com.springdemo.config.Conexao;

import java.sql.Connection;

public class DAOEstatisticas {
    public static Connection conexao;

    static {
        try {
            conexao = Conexao.getConexao();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao conectar ao banco de dados: " + e.getMessage());
        }
    }


    /*

    1 - qtde vacinas por paciente

    String sql = "SELECT COUNT(*) FROM vacinacao.imunizacoes WHERE id_paciente = 3";

    2 - qtde de vacinas aplicaveis no proximo mes por paciente

    SELECT COUNT(*) AS total_vacinas_aplicaveis
FROM dose d
JOIN vacina v ON d.id_vacina = v.id
JOIN paciente p ON p.id = 5
LEFT JOIN imunizacoes i ON d.id = i.id_dose AND i.id_paciente = p.id
WHERE i.id IS NULL  -- O paciente ainda não tomou esta dose
AND TIMESTAMPDIFF(MONTH, p.data_nascimento, CURDATE() + INTERVAL 1 MONTH) = d.idade_recomendada_aplicacao
AND (v.limite_aplicacao IS NULL OR TIMESTAMPDIFF(MONTH, p.data_nascimento, CURDATE() + INTERVAL 1 MONTH) <= v.limite_aplicacao);


    3 - 

    5 - qtde nao aplicadeveis

    String sql = """
            SELECT COUNT(*)
            FROM vacina v
            WHERE v.limite_aplicacao < (
                SELECT TIMESTAMPDIFF(MONTH, p.data_nascimento, CURDATE())
                FROM paciente p
                WHERE p.id = ?
            )
        """;
    * */
}
