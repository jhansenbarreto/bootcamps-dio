<?php

require_once './../database/config-bd.php';
require("./../phplot-6.2.0/phplot.php");
session_start();
$nome_user = $_SESSION['user'];

date_default_timezone_set("America/Bahia");
$P_Dia = date("Y-m-01");
$U_Dia = date("Y-m-t");

$con = conexaoBD();
$sql = "SELECT soma_receitas_por_mes('$nome_user', '$P_Dia', '$U_Dia') AS soma_receitas";
$stmt = $con->prepare($sql);
$stmt->execute();
$resultado = $stmt->fetch(PDO::FETCH_ASSOC);

$con2 = conexaoBD();
$sql2 = "SELECT soma_despesas_por_mes('$nome_user', '$P_Dia', '$U_Dia') AS soma_despesas";
$stmt2 = $con2->prepare($sql2);
$stmt2->execute();
$resultado2 = $stmt2->fetch(PDO::FETCH_ASSOC);

$grafico = new PHPlot(600, 260);

$grafico->SetFileFormat('png');
$grafico->SetDataType("text-data");
$grafico->SetPlotType('bars');

$grafico->SetYTitle("Valor em Reais");
$grafico->SetXTitle("Despesas / Receitas");

$dados = array(array('', $resultado2["soma_despesas"], $resultado["soma_receitas"]));
$grafico->SetTitle('Saldo: R$ '.number_format($resultado["soma_receitas"]-$resultado2["soma_despesas"], 2, ',', '.'));

$grafico->SetLegend(array('R$ '.number_format($resultado2["soma_despesas"], 2, ',', '.'), 'R$ '.number_format($resultado["soma_receitas"], 2, ',', '.')));
$grafico->SetDataColors(array(array(238, 106, 80), array(84, 163, 38)));
$grafico->SetDataValues($dados);
$grafico->DrawGraph();