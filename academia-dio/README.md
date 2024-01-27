<p align="center">
  <img width="200px" src="https://github.com/jhansenbarreto/bootcamps-dio/assets/13790608/a6abfa60-b98a-46c6-8f21-7d3e121bf098">
</p>
<h1 align=center>Conhecendo Spring Data JPA na prática com Java</h1>

>Ao final deste projeto, o Dev irá conhecer os principais conceitos de mapeamento objeto relacional (ORM) usando o Spring Data JPA. Para isso, uma API REST será desenvolvida com ênfase na modelagem de suas entidades, no domínio de uma academia de ginástica.

<p align="center">
  <img width="80%" src="https://user-images.githubusercontent.com/13790608/230746753-8878c246-94c9-4f5a-8e77-303566d40be9.png">
</p>

A imagem acima apresenta o Diagrama Entidade Relacionamento (DER) do projeto implementado, tendo como referência o projeto <a href=https://github.com/cami-la/academia-digital>academia-digital</a> da instrutora Camila Cavalcante da DIO. O projeto consiste na criação de uma API REST que simula o cadastro de alunos em uma academia. O DER apresentado exibe não só as entidades como a relação e cardinalidade entre elas:

- ``1 Matrícula tem 1 Aluno``
- ``1 Aluno pode ter várias Avaliações Físicas``

## :heavy_check_mark: Técnicas e Tecnologias Utilizadas

- Java (JDK 17.0.4.1)
- Spring Framework:
  - Spring Boot 3.0.5
  - Spring Data JPA
  - Spring Web
  - Spring Validation
- Apache NetBeans IDE 15
- PostgreSQL 15.2 (SGBD)
- Lombok
- <a href=https://modelmapper.org/>ModelMapper</a>
- Postman 10 (para testes práticos)
- Padrão de Projeto DTO (Data Transfer Object)
- Modelagem com DDD (Domain-Driven Design)

## :hammer: Funcionalidades

O projeto conta com o CRUD completo da entidade Aluno, fornecendo as seguintes funcionalidades:
- Listar todos os alunos cadastrados
- Buscar um aluno por ID
- Buscar alunos por nome
- Cadastrar um aluno
- Atualizar um aluno
- Excluir um aluno

Em relação à entidade Matrícula, a API fornece:
- Listar todas as matrículas (ativas e inativas)
- Listar matrículas entre datas (ativas e inativas)
- Listar matrículas de um aluno (ativas e inativas)
- Buscar uma matrícula por ID
- Criar uma matrícula por ID do aluno
- Desativar matrícula por ID do aluno

Já para Avaliações Físicas:
- Listar todas as avaliações de um aluno por ID
- Buscar uma avaliação por ID
- Salvar uma avaliação para um aluno
- Excluir uma avaliação de um aluno

## :rotating_light: Observações

- ``Decisões de Projeto:`` A modelagem e implementação da API conta com alguns detalhes escolhidos por decisão de projeto, por exemplo:
  - Não é possível matricular um aluno que já tem uma matrícula ATIVA;
  - Não é possível desmatricular um aluno sem matrícula ATIVA;
  - Não é possível salvar uma Avaliação Física para um aluno sem matrícula ATIVA;
  - Não é possível salvar aluno com CPF duplicado;
- ``Spring Data JPA:`` Com a utilização da dependência, foi possível configurar facilmente a comunicação entre a API e o SGBD (PostgreSQL) além de modelar tranquilamente as relações entre as entidades através das anotações fornecidas pela dependência. Uma outra observação é: o projeto está configurado para gerar o DDL para criação e atualização das tabelas automaticamente, utilizando a opção de atualização (UPDATE) do schema do banco de dados.
- ``Tratamento de Erros:`` A API conta com tratamento de erros e fornece ao consumidor um modelo de representação de erros baseado na especificação <a href="https://www.rfc-editor.org/rfc/rfc7807">RFC 7807 (Problem Details for HTTP APIs)</a>, utilizando-a apenas como referência, não implementando totalmente à risca.
- ``Propriedades da Aplicação:`` Antes de rodar a aplicação, verifique o arquivo ``application.properties`` e confirme se as configurações relacionadas ao banco de dados conferem com a instalação do seu SGBD.

Explore o projeto à vontade :+1: