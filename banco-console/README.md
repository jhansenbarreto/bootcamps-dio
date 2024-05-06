<p align="center">
    <img width="200px" src="https://github.com/jhansenbarreto/bootcamps-dio/assets/13790608/a6abfa60-b98a-46c6-8f21-7d3e121bf098">
</p>
<h1 align=center>Simulando Uma Conta Bancária</br>Através do Terminal/Console</h1>

> Ponha em prática os fundamentos da linguagem e noções de regras de estrutura de sintaxe.

O usuário interege com a aplicação através do terminal/console. Programa um pouco mais elaborado que o solicitado pelo desafio. Pequeno sistema que simula movimentações bancárias, com as seguintes opções:

- Abrir Conta;
- Consultar saldo;
- Depositar;
- Sacar;
- Fazer transferência entre contas;
- Imprimir extrato;
- Sair (parar execução do programa).

A implementação conta com o uso de ``records``, uma funcionalidade ainda *jovem* do ``Java``. Então, atenção com a versão do seu **JDK** ao rodar o programa. Foi utilizado o ``Java 17``.

Para facilitar a implementação do projeto foi utilizado o ``Maven`` para *build* e gerenciamento de dependências, e a biblioteca ``Lombok`` para diminuir a escrita de código. A imagem abaixo exemplifica o programa já compilado, sendo executado no terminal do *Windows*.

<p align="center">
    <img width="80%" src="https://github.com/jhansenbarreto/bootcamps-dio/assets/13790608/000dc1e4-35a7-4ed6-aa4b-6184c6a9acbb">
</p>

A cor de fundo e das letras foi modificada de acordo com a necessidade das informações exibidas. A imagem abaixo exemplifica essa situação, com mensagens de erro, alerta, sucesso, etc...

<p align="center">
    <img width="80%" src="https://github.com/jhansenbarreto/bootcamps-dio/assets/13790608/94211739-f112-4c7b-b192-1981b99e1083">
</p>

Conforme o usuário navega, o terminal é limpo e apresenta novas informações da função escolhida. Finalizando a função, volta para o menu inicial.

:rotating_light:**OBS:** Esses detalhes de limpar a tela do terminal e cor das letras foram testados apenas no ``Windows``, não há certeza do comportamento no terminal de outros Sistemas Operacionais ``(Linux, MacOS, etc...)``. 

Explore o projeto à vontade 👍