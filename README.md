# API REST com Spring Boot

Este projeto é uma API REST desenvolvida utilizando Spring Boot, com integração de tecnologias como JPA, Spring Security, JWT, MySQL e Docker.

## Tecnologias Utilizadas

- **Java**: Linguagem de programação principal.
- **Spring Boot**: Framework para criação de aplicações Java.
- **Spring Data JPA**: Abstração para persistência de dados.
- **Spring Security**: Framework de segurança para aplicações Java.
- **JWT (JSON Web Token)**: Método para autenticação e troca segura de informações.
- **MySQL**: Sistema de gerenciamento de banco de dados relacional.
- **Docker**: Plataforma para desenvolvimento, envio e execução de aplicações em containers.

## Funcionalidades

- **CRUD de Entidades**: Implementação de operações de criação, leitura, atualização e exclusão.
- **Autenticação e Autorização**: Utilização de Spring Security com JWT para controle de acesso.
- **Persistência de Dados**: Integração com banco de dados MySQL utilizando Spring Data JPA.
- **Containerização**: Configuração de Docker para facilitar a implantação da aplicação.

## Estrutura do Projeto

O projeto está organizado da seguinte forma:

- **src/main/java**: Código-fonte principal da aplicação.
  - **controller**: Classes responsáveis por manipular as requisições HTTP.
  - **service**: Classes que contêm a lógica de negócio.
  - **repository**: Interfaces para acesso aos dados no banco de dados.
  - **model**: Definição das entidades e mapeamentos JPA.
  - **security**: Configurações e implementações relacionadas à segurança da aplicação.

- **src/main/resources**: Recursos e configurações da aplicação.
  - **application.properties**: Configurações de ambiente, como parâmetros de conexão com o banco de dados.

- **Dockerfile**: Script para criação da imagem Docker da aplicação.

## Pré-requisitos

- **Java 11** ou superior.
- **Maven** para gerenciamento de dependências.
- **MySQL** instalado e configurado.
- **Docker** (opcional, caso deseje utilizar a containerização).

## Configuração e Execução

1. **Clonar o Repositório**:
   ```bash
   git clone https://github.com/williammian/app-api.git
   ```

2. **Configurar o Banco de Dados**:
   - Criar um banco de dados no MySQL.
   - Atualizar o arquivo `application.properties` com as credenciais e URL do banco de dados.

3. **Executar a Aplicação**:
   - Navegar até o diretório do projeto:
     ```bash
     cd app-api
     ```
   - Compilar e executar a aplicação:
     ```bash
     mvn spring-boot:run
     ```

   A aplicação estará disponível em `http://localhost:8080`.

4. **Executar com Docker** (opcional):
   - Construir a imagem Docker:
     ```bash
     docker build -t app-api .
     ```
   - Executar o container:
     ```bash
     docker run -p 8080:8080 app-api
     ```

## Observações

- Certifique-se de que o MySQL está em execução e acessível conforme as configurações definidas.
- As rotas protegidas requerem autenticação via JWT. Para acessar essas rotas, é necessário obter um token válido através do processo de login.
- O projeto inclui um `Dockerfile` para facilitar a criação de um ambiente containerizado.

## Licença

Este projeto está licenciado sob a licença MIT.

