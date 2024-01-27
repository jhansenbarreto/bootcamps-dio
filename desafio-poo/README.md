<p align="center">
  <img width="200px" src="https://github.com/jhansenbarreto/bootcamps-dio/assets/13790608/a6abfa60-b98a-46c6-8f21-7d3e121bf098">
</p>
<h1 align=center>Abstraindo um Bootcamp (Desafio POO)</h1>

>Desmistifique a Programação Orientada a Objetos (POO) com Java e pratique esse conceito fundamental nesse desafio. Para isso, os pilares da OO são devidamente formalizados: Abstração, Encapsulamento, Herança e Polimorfismo. Com isso, você poderá desenvolver sua capacidade de abstração com um problema real e implementar as evoluções que achar interessantes :wink:

<p align=center>
  <img width="80%" src="https://user-images.githubusercontent.com/13790608/230703266-64e2f693-de64-458a-b56b-cbf4f75b796b.png"/>
</p>

A imagem acima representa o projeto implementado, tendo como referência o projeto <a href=https://github.com/cami-la/desafio-poo-dio>desafio-poo-dio<a> da instrutora Camila Cavalcante da DIO. Após algumas mudanças implementadas, este projeto conta com as funcionalidades listadas abaixo.

## :hammer: Funcionalidades

:package: ``Bootcamp``
- Cria um Bootcamp;
- Aceita matrícula de Devs;
- Aceita cadastro de Conteúdos:
  - Cursos;
  - Mentorias;
  
:package: ``Dev``
- Cria um Dev;
- Inscreve em um Bootcamp;
- Progride em um Bootcamp (consumindo conteúdos);
- Calcula o XP total adquirido;
- Imprime Bootcamp inscrito e seus respectivos Conteúdos;
- Imprime progresso:
  - Porcentagem concluída de cada Bootcamp;
  - Conteúdos restantes;

:package: ``Conteúdo (classe abstrata)``
- Curso (herda de Conteúdo):
  - Cria um Curso;
  - Calcula XP de acordo com a carga horária;
- Mentoria (herda de Conteúdo):
  - Cria uma Mentoria;
  - Tem XP fixo (não tem carga horária);
  
Para maiores esclarecimentos, o código está comentado  em alguns pontos. Explore o projeto à vontade 👍