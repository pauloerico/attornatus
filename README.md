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

* Foram criadas as classes Contact e Address com uma relação unidirecional de muitos endereços para um contato. 
* A relação foi definida na tabela de endereços porque manter a relação no objeto contido permite paginação e ordenação, 
evita inconsistências no banco de dados, melhorando performance e facilitando futuras evoluções da API.
* A interface de usuário do banco de dados pode ser acessada em http://localhost:8080/h2
* Foi desenvolvido um CRUD para Contact e um para Address.
* É possível definir um endereço primário durante a criação ou atualização de um endereço.
* É possível listar o endereço primário ou todos os endereços de uma pessoa, mas por segurança, não é possível listar todos
os endereços de todas as pessoas.
* Pela simplicidade, principais errors foram tratados no próprio controller.
* Foram realizados testes de integração em ContactController usando JUnit e RestAssured.
