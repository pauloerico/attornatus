# Attornatus
Avaliação técnica para desenvolvedor Backend Java na Attornatus

## Desafio
Criar uma API Java com as seguintes características:

### Objetos
Uma Pessoa deve ter os seguintes campos:
* Nome
* Data de nascimento
* Endereço
  * Logradouro
  * CEP
  * Número
  * Cidade

### Requisitos
 - Todas as respostas da API devem ser em JSON
 - Banco de dados em H2

### Questões
1. Durante a implementação de uma nova funcionalidade de software solicitada, quais critérios você avalia e implementa 
para garantia de qualidade de software?

* R: Avalio quais são os requisitos do cliente, o que essa funcionalidade se propõe a resolver e se a forma proposta é realmente a 
melhor maneira de solucionar o problema. Facilidade de uso da funcionalidade. Simplicidade na implementação, clareza no 
código e modularidade da funcionalidade vislumbrando futuras evoluções e facilidade de manutenção.

2. Em qual etapa da implementação você considera a qualidade de software?

* R: Desde a análise dos requisitos, concepção, desenvolvimento e manutenção. Procuro levar a qualidade de software em 
consideração em todos os momentos.

## Aplicação
### Stack
* Java 17
### Database
* H2
* A interface de usuário do banco de dados pode ser acessada em http://localhost:8080/h2
* O respositório foi definido através de interface, o que permite utilizar o banco de dados que for mais conveniente e 
intercambiabilidade, sem prejudicar o funcionamento da API.
### Features
* Foram criadas as classes Contact e Address com uma relação unidirecional de muitos endereços para um contato. 
* A relação foi definida na tabela de endereços porque manter a relação no objeto contido permite paginação e ordenação, 
evita inconsistências no banco de dados, melhorando performance e facilitando futuras evoluções da API. Também permite 
que um contato exista sem um endereço, mas um endereço não existe sem um contato.
* Os identificadores dos objetos são do tipo UUID o que faz com que sejam únicos mesmo entre as classes contatos e
endereços. Isso permite que os gerenciadores de cada classe possam ser separados em microserviços independentes, caso 
necessário.
* Foi desenvolvido um CRUD para Contact e um para Address.
* É possível definir um endereço primário durante a criação ou atualização de um endereço.
* É possível listar o endereço primário ou todos os endereços de uma pessoa, mas por segurança, não é possível listar todos
os endereços de todas as pessoas.
* Pela pequena quantidade e simplicidade, os errors identificados foram tratados no próprio controller.
* Foram realizados testes de integração em ContactController usando JUnit e RestAssured.

## Como executar o projeto
* Clone este repositório.
```shell
git clone https://github.com/pauloerico/attornatus
```
* Abra e execute na sua IDE de preferência.
* Use sua plataforma de preferência para testar os endpoints.

### Endpoints
#### Contact
* Busca todos os contatos.
```http request
GET http://localhost:8080/contacts
```
* Salva um novo contato
```http request
POST http://localhost:8080/contacts
```
* Busca contato pelo seu ID
```http request
GET http://localhost:8080/contacts/{contactId}
```
* Edita um contato pelo seu ID
```http request
PUT http://localhost:8080/contacts/{contactId}
```
* Deleta um contato pelo seu ID
```http request
DELETE http://localhost:8080/contacts/{contactId}
```
#### Address
* Busca todos os endereços de um contato
```http request
GET http://localhost:8080/contacts/{contactId}/addresses
```
* Adiciona novo endereço à um contato
```http request
POST http://localhost:8080/contacts/{contactId}/addresses
```
* Busca os endereço(s) principal(ais) de um contato
```http request
GET http://localhost:8080/contacts/{contactId}/primary-address
```
* Edita o endereço pelo seu ID
```http request
PUT http://localhost:8080/addresses/{addressId}
```
* Deleta um endereço pelo seu ID
```http request
DELETE http://localhost:8080/addresses/{addressId}
```
### DTOs
#### ContactDto
```json
{
    "name":"John Doe",
    "dateOfBirth":"1979-12-30"
}
```
#### AddressDto
```json
{
    "street":"Rua dos Bobos",
    "number":0,
    "city":"São Paulo",
    "state":"SP",
    "cep":12345678,
    "primaryAddress":true
}
```