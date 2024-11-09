<?php

require_once '../database/config-bd.php';

$descricao = $_POST["desc"];
$valor = $_POST["valor"];
$data_lancamento = $_POST["data"];
$origem = $_POST["origem"];
$destino = $_POST["destino"];


$con = conexaoBD();
$sql = "INSERT INTO transferencia (descricao, valor, data_lancamento, origem, destino) VALUES (:desc, :valor, :data, :origem, :destino)";
$stmt = $con->prepare($sql);

$stmt->bindParam(":desc", $descricao);
$stmt->bindParam(":valor", $valor);
$stmt->bindParam(":data", $data_lancamento);
$stmt->bindParam(":origem", $origem);
$stmt->bindParam(":destino", $destino);

session_start();
if ($stmt->execute()) {
    $_SESSION["msg"] = "Dados inseridos com sucesso!";
    header("Location:./../transf.php");
} else {
    $_SESSION["msg"] = "Erro! Dados não inseridos!";
    header("Location:./../transf.php");
}