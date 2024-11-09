<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<html>
    <head>
        <meta charset="UTF-8">
        <link rel="stylesheet" type="text/css" href="css/login.css">
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
        <title>ContFin</title>
    </head>
    <body>
        <div id="login">
            <form method="POST" action="log-in.php" id="form">
                <div id="cabecalho">
                    <h1>₵ontFin</h1>
                    <h3>Controle Financeiro</h3>
                </div>

                <p> 
                    <label for="user">Usuário</label>
                    <input id="user" name="user" required="required" type="text" placeholder="User"/>
                </p>

                <p> 
                    <label for="senha">Senha</label>
                    <input id="senha" name="senha" required="required" type="password" placeholder="Password"/> 
                </p>

                <p> 
                    <button type="submit" name="entrar" value="Login">Entrar<span class="material-icons">login</span></button>
                </p>
            </form>
        </div>
    </body>
</html>
