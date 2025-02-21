package com.springdemo.routes;


import com.springdemo.service.ServiceVacina;
import com.springdemo.service.ServiceEstatistica;
import spark.Spark;

public class Rotas {
    public static void processarRotas() throws Exception {

        //rotas das vacinas
        Spark.get("/vacinas/consultar", ServiceVacina.consultarVacinas());
        Spark.get("/vacinas/consultar/faixa_etaria/:faixa", ServiceVacina.consultarVacinaPFaixa());
        Spark.get("/vacinas/consultar/idade_maior/:meses", ServiceVacina.consultarVacinaPIdadeMaior());
        Spark.get("/vacinas/consultar/nao_aplicaveis/paciente/:id", ServiceVacina.consultarVacinaNaoAplicaveis());

        //rotas das estatisticas
        Spark.get("/estatisticas/imunizacoes/paciente/:id", ServiceEstatistica.vacinaPorPaciente());
        Spark.get("/estatisticas/proximas_imunizacoes/paciente/:id", ServiceEstatistica.vacinasProximoMes());
        Spark.get("/estatisticas/imunizacoes_atrasadas/paciente/:id", ServiceEstatistica.vacinasAtrasadas());
        Spark.get("/estatisticas/imunizacoes/idade_maior/:meses", ServiceEstatistica.vacinaPorIdade());
        Spark.get("/estatisticas/vacinas/nao_aplicaveis/paciente/:id", ServiceEstatistica.qtdeVacinasNaoAplicaveis());



    }
}
