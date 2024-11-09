<?php

require_once '../database/config-bd.php';

$descricao = $_POST["desc"];
$valor = $_POST["valor"];
$data_lancamento = $_POST["data"];
$conta_id = $_POST["conta"];


$con = conexaoBD();
$sql = "INSERT INTO receita (descricao, valor, data_lancamento, conta_id) VALUES (:desc, :valor, :data, :conta)";
$stmt = $con->prepare($sql);

$stmt->bindParam(":desc", $descricao);
$stmt->bindParam(":valor", $valor);
$stmt->bindParam(":data", $data_lancamento);
$stmt->bindParam(":conta", $conta_id);

session_start();
if ($stmt->execute()) {
    $_SESSION["msg"] = "Dados inseridos com sucesso!";
    header("Location:./../receitas.php");
} else {
    $_SESSION["msg"] = "Erro! Dados não inseridos!";
    header("Location:./../receitas.php");
}