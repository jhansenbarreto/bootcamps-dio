<p align="center">
    <img width="200px" src="https://github.com/jhansenbarreto/bootcamps-dio/assets/13790608/a6abfa60-b98a-46c6-8f21-7d3e121bf098">
</p>
<h1 align=center>Simulando Uma Conta Bancária</br>Através do Terminal/Console</h1>

> Ponha em prática os fundamentos da linguagem e noções de regras de estrutura de sintaxe.

<h1 align=center>Criando um Banco Digital com</br>Java e Orientação a Objetos</h1>

> Reforce seu conhecimento em Programação Orientada a Objetos (POO) em Java com um desafio de projeto totalmente prático. Para isso, os pilares da orientação a objetos são devidamente explorados no contexto bancário, onde o expert implementa um projeto de referência (disponibilizado no GitHub) de forma prática e interativa. Sendo assim, você poderá desenvolver sua capacidade de abstração reproduzindo essa solução. Além disso, caso queira ir além, implemente suas próprias evoluções e melhorias ;-)

Este projeto atende a dois **Desafios de Projeto** propostos pela DIO. O usuário interege com a aplicação através do ``terminal/console``. Pequeno sistema que simula movimentações bancárias, com as seguintes opções:

- Abrir Conta;
- Consultar saldo;
- Depositar;
- Sacar;
- Fazer transferência entre contas;
- Imprimir extrato;
- Sair (parar execução do programa).

O usuário pode escolher entre abrir uma **Conta Corrente (CC)** ou **Conta Poupança (CP)**. O programa gera automaticamente o número das contas. Foi definido programaticamente que a **CC** tem uma taxa de manutenção de **R$ 12,50** cobrada sempre no primeiro dia de todo mês, após as 23:30h, já a **CP** recebe **0,5%** de juros em cima do saldo também na mesma data e horário.

A implementação do programa foi feita com a versão ``17`` do ``Java`` e conta com o uso de ``records``, uma funcionalidade ainda *jovem* da linguagem. Então, atenção com a versão instalada na sua máquina antes de rodar o programa.

Para facilitar a implementação do projeto foi utilizado o ``Maven`` para *build* e gerenciamento de dependências e a biblioteca ``Lombok`` para diminuir a escrita de código. A imagem abaixo exemplifica o programa já compilado, sendo executado no terminal do ``Windows``.

<p align="center">
    <img width="80%" src="https://github.com/jhansenbarreto/bootcamps-dio/assets/13790608/000dc1e4-35a7-4ed6-aa4b-6184c6a9acbb">
</p>

A cor de fundo e das letras foi modificada de acordo com a necessidade das informações exibidas. A imagem abaixo exemplifica essa situação, com mensagens de erro, alerta, sucesso, etc...

<p align="center">
    <img width="80%" src="https://github.com/jhansenbarreto/bootcamps-dio/assets/13790608/94211739-f112-4c7b-b192-1981b99e1083">
</p>

Conforme o usuário navega, o terminal é limpo e apresenta novas informações da função escolhida. Finalizando a função, volta para o menu inicial.

:rotating_light:**OBS:** Esses detalhes de limpar a tela do terminal e cor das letras foram testados apenas no ``Windows``, não há certeza do comportamento no terminal de outros Sistemas Operacionais ``(Linux, MacOS, etc...)``. 

:warning: **Melhorias:** 
- Mudar a forma de debitar a taxa de manutenção da **CC** e creditar os juros da **CP**. Nesta versão uma ``thread`` é *"ligada"* na criação da conta, que verifica sempre quando deve efetuar a operação.
- Salvar os dados, pois o programa salva tudo em memória. Os dados *"morrem"* quando o programa é finalizado.

Explore o projeto à vontade 👍