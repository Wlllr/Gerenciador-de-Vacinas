# 💉 Gerenciador de Vacinas

O **Gerenciador de Vacinas** é uma aplicação **backend** desenvolvida em **Java** com o framework **Spark** e banco de dados **MySQL**.  
O sistema tem como objetivo **gerenciar o cadastro e controle de vacinas e pessoas vacinadas**, oferecendo operações completas de **CRUD (Create, Read, Update, Delete)** por meio de uma **API RESTful**.

---

## ⚙️ Tecnologias Utilizadas

- **Java 17+**
- **Spark Java** (framework web)
- **MySQL** (banco de dados relacional)
- **Gson** (para serialização/deserialização JSON)
- **DAO Pattern (Data Access Object)** para separação da lógica de negócio e persistência
- **Postman** (para testes de requisições)

---

## 🚀 Funcionalidades

- Cadastro, listagem, atualização e exclusão de **pessoas**
- Cadastro, listagem, atualização e exclusão de **vacinas**
- Associação de vacinas a pessoas
- Retorno de dados em formato **JSON**
- Conexão persistente com banco de dados **MySQL**
- Rotas RESTful simples e organizadas

---

## 🧩 Estrutura do Projeto

src/
├── config/ # Configuração de conexão e utilitários
├── dao/ # Classes responsáveis pela comunicação com o banco
├── model/ # Classes de domínio (Pessoa, Vacina)
├── dto/ # data transfer object
├── service/ # Camada de regras de negócio
├── routes/ # endpoints
└── Main.java # Ponto de entrada da aplicação


---

## ⚡ Como Rodar o Projeto

1. **Clonar o repositório:**
   ```bash
   git clone https://github.com/wellerpereira/gerenciador-de-vacinas.git
2. Importar o projeto em sua IDE (ex: IntelliJ IDEA, Eclipse)

3. Configurar o banco de dados MySQL:
CREATE DATABASE gerenciador_vacinas;
USE gerenciador_vacinas;

-- Tabelas exemplo:
CREATE TABLE pessoa (
  id INT AUTO_INCREMENT PRIMARY KEY,
  nome VARCHAR(100),
  cpf VARCHAR(11)
);

CREATE TABLE vacina (
  id INT AUTO_INCREMENT PRIMARY KEY,
  nome VARCHAR(100),
  fabricante VARCHAR(100)
);

4 Atualizar as credenciais do banco (host, usuário e senha) no arquivo de configuração da conexão:
URL = "jdbc:mysql://localhost:3306/vacinacao";
USUARIO = "root";
SENHA = "1234567";

5.Executar a aplicação pela

6. Testar os endpoints com Postman, Insomnia ou navegador.

🔗 Endpoints Principais


👤 Paciente
| Método   | Endpoint                  | Descrição                        |
| -------- | ------------------------- | -------------------------------- |
| `POST`   | `/paciente/inserir`       | Cadastra um novo paciente        |
| `GET`    | `/paciente/consultar/:id` | Consulta paciente por ID         |
| `GET`    | `/paciente/consultar`     | Lista todos os pacientes         |
| `PUT`    | `/paciente/alterar/:id`   | Atualiza os dados de um paciente |
| `DELETE` | `/paciente/excluir/:id`   | Exclui um paciente pelo ID       |

💉 Imunização
| Método   | Endpoint                                                       | Descrição                                                    |
| -------- | -------------------------------------------------------------- | ------------------------------------------------------------ |
| `POST`   | `/imunizacao/inserir`                                          | Cadastra uma nova imunização                                 |
| `GET`    | `/imunizacao/consultar/:id`                                    | Consulta uma imunização pelo ID                              |
| `GET`    | `/imunizacao/consultar`                                        | Lista todas as imunizações                                   |
| `GET`    | `/imunizacao/consultar/paciente/:id`                           | Lista imunizações de um paciente específico                  |
| `GET`    | `/imunizacao/consultar/paciente/:id/aplicacao/:dt_ini/:dt_fim` | Consulta imunizações de um paciente em um intervalo de datas |
| `PUT`    | `/imunizacao/alterar/:id`                                      | Atualiza uma imunização                                      |
| `DELETE` | `/imunizacao/excluir/:id`                                      | Exclui uma imunização pelo ID                                |
| `DELETE` | `/imunizacao/excluir/paciente/:id`                             | Exclui todas as imunizações de um paciente                   |


💊 Vacinas
| Método | Endpoint                                         | Descrição                                             |
| ------ | ------------------------------------------------ | ----------------------------------------------------- |
| `GET`  | `/vacinas/consultar`                             | Lista todas as vacinas                                |
| `GET`  | `/vacinas/consultar/faixa_etaria/:faixa`         | Consulta vacinas por faixa etária                     |
| `GET`  | `/vacinas/consultar/idade_maior/:meses`          | Consulta vacinas aplicáveis a idades acima de X meses |
| `GET`  | `/vacinas/consultar/nao_aplicaveis/paciente/:id` | Lista vacinas não aplicáveis a um paciente específico |

📊 Estatísticas
| Método | Endpoint                                            | Descrição                                                            |
| ------ | --------------------------------------------------- | -------------------------------------------------------------------- |
| `GET`  | `/estatisticas/imunizacoes/paciente/:id`            | Mostra o total de imunizações de um paciente                         |
| `GET`  | `/estatisticas/proximas_imunizacoes/paciente/:id`   | Retorna vacinas programadas para o próximo mês                       |
| `GET`  | `/estatisticas/imunizacoes_atrasadas/paciente/:id`  | Lista vacinas atrasadas de um paciente                               |
| `GET`  | `/estatisticas/imunizacoes/idade_maior/:meses`      | Consulta vacinas aplicadas em pacientes com idade superior a X meses |
| `GET`  | `/estatisticas/vacinas/nao_aplicaveis/paciente/:id` | Retorna a quantidade de vacinas não aplicáveis a um paciente         |


🧠 Conceitos Aplicados

- Padrão DAO (Data Access Object)
- Arquitetura em camadas (Model, DAO, Service)
- Boas práticas de organização e modularização
- Manipulação de JSON com Gson
- Uso de rotas RESTful e tratamento de requisições HTTP
- Consultas personalizadas com múltiplos parâmetros
