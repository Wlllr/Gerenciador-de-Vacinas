package com.springdemo.routes;


import com.springdemo.service.ServiceVacina;
import spark.Spark;

public class Rotas {
    public static void processarRotas() throws Exception {

        //rotas das vacinas
        Spark.get("/vacinas/consultar", ServiceVacina.consultarVacinas());
        Spark.get("/vacinas/consultar/faixa_etaria/:faixa", ServiceVacina.consultarVacinaPFaixa());
        Spark.get("/vacinas/consultar/idade_maior/:meses", ServiceVacina.consultarVacinaPIdadeMaior());
        Spark.get("/vacinas/consultar/nao_aplicaveis/paciente/:id", ServiceVacina.consultarVacinaNaoAplicaveis());

        //rotas das doses

    }
}
