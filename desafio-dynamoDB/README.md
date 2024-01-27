<p align="center">
  <img width="200px" src="https://github.com/jhansenbarreto/bootcamps-dio/assets/13790608/a6abfa60-b98a-46c6-8f21-7d3e121bf098">
</p>
<h1 align=center>Boas práticas com DynamoDB</h1>

>Características Relacionais (SQL) e Não Relacionais (NoSQL) usando o mesmo banco de dados? Isso é possível? Com o DynamoDB sim! Entenda um pouco das possibilidades desse banco de dados totalmente gerenciado da AWS. Apresentação de uma série de boas práticas para o DynamoDB em um desafio totalmente prático. Sendo assim, você poderá reproduzir essa solução e, caso queira ir além, implementar suas próprias evoluções e melhorias :wink:

<p align=center>
  <img width=80% src="https://user-images.githubusercontent.com/13790608/230742699-44aa9cfd-a0db-4d8f-99dd-a5f84d4ed866.png"/>
</p>

A imagem acima representa a arquitetura da proposta de construção desse banco de dados, tendo como referência o projeto <a href=https://github.com/cassianobrexbit/dio-live-dynamodb>dio-live-dynamodb</a> do instrutor Cassiano Peres da DIO. O projeto toma como exemplo o mundo da música, onde temos as entidades Artista, Álbum e Música.

Além das entidades, o diagrama apresenta as relações e cardinalidade entre elas:
- 1 Artista pode ter muitos Álbuns
- 1 Álbum pode ter muitas Músicas
- 1 Música pode pertencer a mais de 1 Álbum

O diretório conta com todos os scripts utilizados para criação de tabela, inserção de dados, criação de índices e consultas, todos numerados. Os serviços utilizados foram:
- Amazon DynamoDB
- Amazon CLI (para executar os scripts por linha de comando)

Além dos scripts, o diretório conta também com alguns arquivos JSON para facilitar a inserção de dados e consulta. Explore o projeto à vontade :+1: