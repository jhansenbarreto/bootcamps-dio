<?php

require_once '../database/config-bd.php';

$descricao = $_POST["nome"];
$user = $_POST["id_user"];

$con = conexaoBD();
$sql = "INSERT INTO conta (nome, usuario) VALUES (:nome, :user)";
$stmt = $con->prepare($sql);

$stmt->bindParam(":nome", $descricao);
$stmt->bindParam(":user", $user);

session_start();
if ($stmt->execute()) {
    $_SESSION["msg"] = "Dados inseridos com sucesso!";
    header("Location:./../contas.php");
} else {
    $_SESSION["msg"] = "Erro! Dados não inseridos!";
    header("Location:./../contas.php");
}