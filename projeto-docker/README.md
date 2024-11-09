<p align="center">
  <img width="300px" src="https://github.com/jhansenbarreto/bootcamps-dio/assets/13790608/a6abfa60-b98a-46c6-8f21-7d3e121bf098">
</p>
<h1 align=center>Criando um Container de</br>uma Aplicação WEB</h1>

> *Neste projeto o expert utilizou o Docker Compose para executar uma aplicação HTML em um Container Apache. Você poderá ir além e fazer alterações mais robustas ao seu projeto, estilizando sua página e utilizando seus conhecimentos em (HTML, CSS e JS). Você também pode buscar outras formas para executar seu arquivo HTML em outras Linguagens de Programação.*

Diferente do que o instrutor da DIO, <a href=https://github.com/denilsonbonatti>Denilson Bonatti</a>, fez em seu projeto <a href=https://github.com/denilsonbonatti/docker-projeto1-dio>
docker-projeto1-dio</a>, utilizei uma antiga aplicação de uma atividade da época da faculdade.

Um sisteminha bem simples de lançamentos financeiros (receitas, despesas, transferências), implementado em **PHP**, tendo o **MySQL** como SGBD. Por ser uma atividade simples onde o foco era demonstrar algumas coisas no *front-end*, o *back-end* é bem simplório, pois, o código nem faz os tratamentos necessários antes de uma transação, como por exemplo, verificar se o valor de uma retirada é maior que o saldo disponível. Foram implementadas algumas funcionalidades apenas para dar alguma vida à aplicação, para não ser apenas telas **HTML** estáticas.

Sendo assim, ignore esses detalhes, esse projeto foi postado apenas para testar os conhecimentos em **Docker**. Dentro do diretório é possível encontrar o projeto feito em **PHP**, nomeado como ``ControleFinanceiro``, uma pasta ``data`` utilizada como <a herf=https://docs.docker.com/engine/storage/volumes/>*volume*</a> para que haja a persistência e o compartilhamento de dados entre o *host* e o *container*, recuperando os dados do banco sempre que precisar subir um novo *container*, e o arquivo ``docker-compose.yml``, para subir a aplicação.

O *volume* existente na pasta ``data`` já contém dados persistidos, ou seja, quando a aplicação subir, já existirá um banco de dados criado e populado.

Explore o projeto à vontade :+1: