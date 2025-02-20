package com.springdemo;

import spark.Spark;

import java.sql.Connection;

import com.springdemo.config.Conexao;

import com.springdemo.dao.DAOVacina;
import com.springdemo.routes.Rotas;

import spark.Request;
import spark.Response;
import spark.Route;

public class Main {
    public static void main(String[] args) {

        try {
            Spark.port(8080);

            Spark.options("/*", new Route() {
                @Override   
                public Object handle(Request requisicaoHttp, Response respostaHttp) throws Exception {
                    String accessControlRequestHeaders = requisicaoHttp.headers("Access-Control-Request-Headers");

                    if (accessControlRequestHeaders != null)
                        respostaHttp.header("Access-Control-Allow-Headers", accessControlRequestHeaders);

                    String accessControlRequestMethod = requisicaoHttp.headers("Access-Control-Request-Method");

                    if (accessControlRequestMethod != null)
                        respostaHttp.header("Access-Control-Allow-Methods", accessControlRequestMethod);

                    return "OK";
                }
            });

            Spark.before(new spark.Filter() {
                @Override
                public void handle(Request requisicaoHttp, Response respostaHttp) throws Exception {
                    respostaHttp.header("Access-Control-Allow-Origin", "*"); // Permite todas as origens
                    respostaHttp.header("Access-Control-Allow-Methods", "OPTIONS, GET, POST, PUT, DELETE");
                    respostaHttp.header("Access-Control-Allow-Headers", "Content-Type, Authorization");
                }
            });

            Rotas.processarRotas();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}