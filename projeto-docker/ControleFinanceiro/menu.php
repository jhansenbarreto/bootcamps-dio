<?php

$home = $_POST['home'];
$contas = $_POST['contas'];
$grafico = $_POST['grafico'];
$receitas = $_POST['receitas'];
$despesas = $_POST['despesas'];
$transfer = $_POST['transfer'];
$hist = $_POST['hist'];
$sair = $_POST['sair'];

if (isset($home)) {
    header("Location:home.php");
} elseif (isset($contas)) {
    header("Location:contas.php");
} elseif (isset($grafico)) {
    header("Location:grafico.php");
} elseif (isset($receitas)) {
    header("Location:receitas.php");
} elseif (isset($despesas)) {
    header("Location:despesas.php");
} elseif (isset($transfer)) {
    header("Location:transf.php");
} elseif (isset($hist)) {
    header("Location:historico.php");
} elseif (isset($sair)) {
    header("Location:log-out.php");
}