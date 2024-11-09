<?php

$entrar = $_POST['entrar'];
$user = $_POST['user'];
$senha = md5($_POST['senha']);

require_once './database/config-bd.php';
$con = conexaoBD();

if (isset($entrar)) {
    $sql = "SELECT user FROM usuario WHERE user = '$user' AND senha = '$senha'";
    $stmt = $con->prepare($sql);
    $stmt->execute();
    $resultado = ($stmt->fetchAll(PDO::FETCH_ASSOC));

    if (sizeof($resultado) == 1) {
        session_start();
        $_SESSION['user'] = $user;
        header("Location:home.php");
    } else {
        header("Location:errorlogin.php");
    }
}