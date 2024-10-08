<p align="center">
    <img src="https://www.svgrepo.com/show/184143/java.svg" width="130" />
    <img src="https://www.edureka.co/blog/wp-content/uploads/2019/08/recyclebin-data-1.png" width="220" />
</p>

# Beverage Stock Control - v1.0.0

## Objetivo

Este projeto tem como objetivo desenvolver uma API RESTful para gerir o armazenamento e estoque de um depósito de bebidas. O sistema gerencia tanto bebidas alcoólicas quanto não alcoólicas em diferentes seções, respeitando regras de negócio específicas, como capacidade de armazenamento e tipos de bebida permitidos por seção. A API também mantém um histórico de entradas e saídas de bebidas no estoque, facilitando a consulta de volumes e registros de movimentações.

## Funcionalidades Implementadas

- [x] **Cadastro de bebidas armazenadas** (RF01)
- [x] **Registro de histórico de entrada e saída de bebidas** (RF08)

## Funcionalidades a Implementar

- [ ] **Consulta do volume total no estoque por tipo de bebida**
- [ ] **Consulta dos locais disponíveis de armazenamento para um determinado volume de bebida**
- [ ] **Consulta das seções disponíveis para venda de determinado tipo de bebida**
- [ ] **Consulta de histórico de entradas e saídas por tipo de bebida e seção**

## Regras de Negócio

- Seções do estoque não podem armazenar bebidas alcoólicas e não alcoólicas simultaneamente.
- Uma seção possui capacidade de armazenamento fixa (500 litros para bebidas alcoólicas e 400 para não alcoólicas).
- O sistema deve calcular o local disponível de armazenamento com base na capacidade livre.
- Todo registro de entrada e saída deve ser acompanhado de um histórico que contenha: horário, tipo, volume, seção e responsável.
- Não é permitido o recebimento de bebidas não alcoólicas no mesmo dia em que bebidas alcoólicas foram registradas na seção.

## Pré-requisitos

Para a execução do serviço, é necessário ter instalado no ambiente os softwares abaixo:

- **Java SDK 21**
- **Maven v3.9.6**
- **Docker** (Opcional)

## Principais dependências

- **Spring Boot v3.2.5**
- **Spring Data JPA**
- **Spring Validation**
- **Mysql Driver**
- **JUnit/Spock Framework** (Testes)
- **Lombok**
- **Modelmapper**

### Instalação de dependências

``` bash
   mvn install
```

## Environment Variables

O projeto se utiliza de variáveis de ambiente que podem ser definidas nos arquivos **application.yaml**

## Testes

``` bash
   mvn test
```
## Execução local

Para executar o projeto de forma local, faça a configuração do arquivo application.yml e execute os comandos abaixo.

``` bash
   mvn spring-boot:run
```

## Instalação e execução com Docker

Para facilitar instalação e execução da aplicação foi implementado containers Docker, onde é realizado o processo de build, testes e execução.
O profile configurado para utilizanção no Docker é o **application-docker.yml**

Estrutura dos arquivos Docker:

- **Dockerfile:** responsável por realizar o build da imagem da aplicação
- **docker-compose.yml:** responsável por realizar o build e execução do servicos

Os passos abaixo devem ser executados na raiz do projeto.

### Build

``` bash
    docker-compose -p beveragestockcontrol -f .docker/docker-compose.yml build
```

### Up

``` bash
    docker-compose -p beveragestockcontrol -f .docker/docker-compose.yml up -d
```

### Down

``` bash
    docker-compose -p beveragestockcontrol -f .docker/docker-compose.yml down
```

## Swagger

Para facilitar a documentação e interação com a API deste projeto, utilizamos o Swagger, uma ferramenta que
permite visualizar, testar e entender melhor os endpoints disponíveis. Abaixo estão as principais informações
relacionadas ao Swagger neste projeto:


### Acessando o Swagger

Após iniciar o serviço localmente ou através do Docker, você pode acessar a interface do Swagger no seguinte endereço: **http://localhost:8080/swagger-ui-custom.html**


### Explorando a API

Ao acessar o Swagger, você terá uma visão completa dos endpoints disponíveis, suas descrições, tipos de requisições
suportadas (GET, POST, etc.), parâmetros necessários e exemplos de resposta.


### Testando Endpoints

O Swagger permite que você teste os endpoints diretamente na interface, fornecendo entradas de dados e visualizando as
respostas. Isso facilita o processo de desenvolvimento e depuração.


## Desenvolvedor

- Bruno F Godoi - brunofgodoi@outlook.com.br