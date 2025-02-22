package com.springdemo.routes;


import com.springdemo.service.ServiceVacina;
import com.springdemo.service.ServiceEstatistica;
import com.springdemo.service.ServicoImunizacao;
import com.springdemo.service.ServicoPaciente;
import spark.Spark;

public class Rotas {
    public static void processarRotas() throws Exception {

        //rotas paciente
        Spark.post("/paciente/inserir", ServicoPaciente.cadastrarPaciente());
        Spark.get("/paciente/consultar/:id", ServicoPaciente.consultarPacientePorId());
        Spark.get("/paciente/consultar", ServicoPaciente.consultarTodosPacientes());
        Spark.put("/paciente/alterar/:id", ServicoPaciente.alterarPaciente());
        Spark.delete("/paciente/excluir/:id", ServicoPaciente.excluirPaciente());

        //rotas imunizacoes
        Spark.post("/imunizacao/inserir", ServicoImunizacao.cadastrarImunizacao());
        Spark.get("/imunizacao/consultar/:id", ServicoImunizacao.consultarImunizacaoPorId());
        Spark.get("/imunizacao/consultar", ServicoImunizacao.consultarTodasImunizacoes());
        Spark.get("/imunizacao/consultar/paciente/:id", ServicoImunizacao.consultarImunizacoesPorIdPaciente());
        Spark.get("/imunizacao/consultar/paciente/:id/aplicacao/:dt_ini/:dt_fim", ServicoImunizacao.consultarImunizacoesPorIdPacienteEIntervalo());
        Spark.put("/imunizacao/alterar/:id", ServicoImunizacao.alterarImunizacao());
        Spark.delete("/imunizacao/excluir/:id", ServicoImunizacao.excluirImunizacao());
        Spark.delete("/imunizacao/excluir/paciente/:id", ServicoImunizacao.excluirImunizacoesPorIdPaciente());


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
