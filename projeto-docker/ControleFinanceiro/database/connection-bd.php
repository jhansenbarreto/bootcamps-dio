<?php

function conexaoBD() {
	try {
		$conexao = new PDO(DSN . ":host=" . DB_SERVER . ";dbname=" . DB_NAME, DB_USERNAME, DB_PASSWORD);
		$conexao->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
		return $conexao;
	} catch(PDOException $e) {
		echo 'DEU RUIM AQUI: ' . $e->getMessage();
	}
}
