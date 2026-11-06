# API de Funcionários

Este projeto foi desenvolvido como atividade da disciplina de Laboratório de Programação FullStack. O objetivo foi criar uma API utilizando Spring Boot para trabalhar com departamentos e funcionários.

## Tecnologias utilizadas

- Java 21
- Spring Boot 3.5.16
- Maven
- Spring Web
- Spring Boot Validation

## Como executar o projeto

Para executar a aplicação, é necessário ter o Java 21 instalado.

1. Abra o projeto no IntelliJ IDEA.
2. Aguarde o Maven carregar as dependências.
3. Abra a classe `FuncionariosApiApplication`.
4. Execute o método `main`.

Também é possível executar pelo terminal, dentro da pasta do projeto, utilizando o comando:

```bash
mvn spring-boot:run
```

Depois de iniciar, a API ficará disponível em:

`http://localhost:8080`

## Funcionamento da API

A API permite realizar operações de cadastro, consulta, atualização e exclusão de funcionários, além de consultar os departamentos.

Foram criadas as seguintes rotas:

| Método | Rota | Função |
|---|---|---|
| GET | /departamento | Listar departamentos |
| GET | /departamento?nome=tec | Buscar departamento pelo nome |
| GET | /departamento/{id}/funcionario | Listar funcionários de um departamento |
| GET | /funcionario | Listar funcionários |
| GET | /funcionario/{id} | Buscar funcionário pelo ID |
| POST | /funcionario | Cadastrar funcionário |
| PUT | /funcionario/{id} | Atualizar funcionário |
| DELETE | /funcionario/{id} | Excluir funcionário |

## Exemplo de cadastro

Para cadastrar um funcionário, deve ser enviado um JSON com nome, salário e departamento:

```json
{
  "nome": "Victor Lopes",
  "salario": 5000.00,
  "departamentoId": 1
}
```

## Organização do projeto

O projeto foi separado em pacotes para organizar melhor o código:

- **controller:** responsável pelas rotas e requisições.
- **model:** contém as interfaces e implementações das operações.
- **entity:** contém as classes de Departamento e Funcionário.
- **dto:** utilizado para receber e retornar os dados da API.
- **mapper:** responsável pela conversão entre DTOs e entidades.
- **exception:** contém as exceções e o tratamento dos erros.

Também foram utilizados injeção de dependências, validação dos dados recebidos e tratamento de erros para situações como dados inválidos ou funcionários não encontrados.

## Armazenamento dos dados

Os dados são armazenados em memória utilizando `ArrayList`, sem conexão com banco de dados. Foram adicionados alguns departamentos e funcionários iniciais para facilitar os testes.

Como os dados ficam apenas na memória, eles são reiniciados quando a aplicação é encerrada.

## Considerações finais

Com o desenvolvimento deste projeto, foi possível praticar a criação de uma API REST com Spring Boot, trabalhar com requisições HTTP, validação de dados, DTOs, interfaces e injeção de dependências.