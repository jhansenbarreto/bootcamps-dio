-- --------------------------------------------------------
-- Servidor:                     127.0.0.1
-- Versão do servidor:           10.4.6-MariaDB - mariadb.org binary distribution
-- OS do Servidor:               Win64
-- HeidiSQL Versão:              10.2.0.5599
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- Copiando estrutura do banco de dados para controle_financeiro
CREATE DATABASE IF NOT EXISTS `controle_financeiro` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `controle_financeiro`;

-- Copiando estrutura para tabela controle_financeiro.conta
CREATE TABLE IF NOT EXISTS `conta` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(40) NOT NULL,
  `saldo` double NOT NULL DEFAULT 0,
  `criado_em` datetime NOT NULL DEFAULT current_timestamp(),
  `usuario` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `usuario` (`usuario`),
  CONSTRAINT `FK_conta_usuario` FOREIGN KEY (`usuario`) REFERENCES `usuario` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;

-- Copiando dados para a tabela controle_financeiro.conta: ~4 rows (aproximadamente)
/*!40000 ALTER TABLE `conta` DISABLE KEYS */;
INSERT INTO `conta` (`id`, `nome`, `saldo`, `criado_em`, `usuario`) VALUES
	(1, 'POUPANÇA BRADESCO', 770, '2021-05-02 18:39:20', 1),
	(2, 'CONTA SALÁRIO CAIXA', 28, '2021-05-02 18:40:04', 1),
	(4, 'CONTA CORRENTE BANCO DO BRASIL', 169, '2021-05-03 00:04:46', 1),
	(6, 'POUPANÇA SANTANDER', 1900, '2021-05-05 22:57:16', 2),
	(7, 'CONTA CORRENTE BANCO DO NORDESTE', 210, '2021-05-05 22:57:55', 2);
/*!40000 ALTER TABLE `conta` ENABLE KEYS */;

-- Copiando estrutura para procedure controle_financeiro.contas_por_user
DELIMITER //
CREATE DEFINER=`root`@`localhost` PROCEDURE `contas_por_user`(
	IN `nome_user` VARCHAR(50)
)
BEGIN
	SELECT c.* FROM conta c INNER JOIN usuario u ON c.usuario = u.id WHERE u.user = nome_user ORDER BY c.nome;
END//
DELIMITER ;

-- Copiando estrutura para tabela controle_financeiro.despesa
CREATE TABLE IF NOT EXISTS `despesa` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `descricao` varchar(50) NOT NULL,
  `valor` double NOT NULL,
  `data_lancamento` date NOT NULL,
  `conta_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `conta` (`conta_id`),
  CONSTRAINT `FK_despesa_conta` FOREIGN KEY (`conta_id`) REFERENCES `conta` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

-- Copiando dados para a tabela controle_financeiro.despesa: ~3 rows (aproximadamente)
/*!40000 ALTER TABLE `despesa` DISABLE KEYS */;
INSERT INTO `despesa` (`id`, `descricao`, `valor`, `data_lancamento`, `conta_id`) VALUES
	(1, 'GASOLINA', 220, '2021-05-02', 1),
	(2, 'ÁLCOOL', 93, '2021-05-03', 4),
	(3, 'TROCA DE ÓLEO', 120, '2021-05-04', 2),
	(4, 'SEGURO', 90, '2021-05-05', 6);
/*!40000 ALTER TABLE `despesa` ENABLE KEYS */;

-- Copiando estrutura para procedure controle_financeiro.despesas_por_user
DELIMITER //
CREATE DEFINER=`root`@`localhost` PROCEDURE `despesas_por_user`(
	IN `nome_user` VARCHAR(50)
)
BEGIN
	SELECT d.*, c.nome AS contas FROM despesa d 
		INNER JOIN conta c ON d.conta_id = c.id
		INNER JOIN usuario u ON c.usuario = u.id
	WHERE u.user = nome_user ORDER BY d.data_lancamento DESC;
END//
DELIMITER ;

-- Copiando estrutura para tabela controle_financeiro.entrada
CREATE TABLE IF NOT EXISTS `entrada` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tipo` enum('RECEITA','TRANSF') NOT NULL,
  `valor` double NOT NULL,
  `data_entrada` date NOT NULL,
  `conta_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `conta_id` (`conta_id`),
  CONSTRAINT `FK_entrada_conta` FOREIGN KEY (`conta_id`) REFERENCES `conta` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;

-- Copiando dados para a tabela controle_financeiro.entrada: ~8 rows (aproximadamente)
/*!40000 ALTER TABLE `entrada` DISABLE KEYS */;
INSERT INTO `entrada` (`id`, `tipo`, `valor`, `data_entrada`, `conta_id`) VALUES
	(1, 'RECEITA', 1100, '2021-05-02', 1),
	(2, 'RECEITA', 100, '2021-05-02', 1),
	(3, 'TRANSF', 85, '2021-05-02', 2),
	(4, 'RECEITA', 112, '2021-05-01', 4),
	(5, 'RECEITA', 50, '2021-05-03', 2),
	(6, 'RECEITA', 25, '2021-05-03', 4),
	(7, 'TRANSF', 125, '2021-05-04', 4),
	(8, 'RECEITA', 13, '2021-04-30', 2),
	(9, 'RECEITA', 2200, '2021-05-03', 6),
	(10, 'TRANSF', 210, '2021-05-05', 7);
/*!40000 ALTER TABLE `entrada` ENABLE KEYS */;

-- Copiando estrutura para procedure controle_financeiro.entradas_por_user
DELIMITER //
CREATE DEFINER=`root`@`localhost` PROCEDURE `entradas_por_user`(
	IN `nome_user` VARCHAR(50)



)
BEGIN
	SELECT e.tipo, e.valor, e.data_entrada, c.nome AS contas FROM entrada e 
		INNER JOIN conta c ON e.conta_id = c.id
		INNER JOIN usuario u ON c.usuario = u.id
	WHERE u.user = nome_user ORDER BY e.data_entrada DESC;
END//
DELIMITER ;

-- Copiando estrutura para tabela controle_financeiro.receita
CREATE TABLE IF NOT EXISTS `receita` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `descricao` varchar(50) NOT NULL,
  `valor` double NOT NULL,
  `data_lancamento` date NOT NULL,
  `conta_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `conta` (`conta_id`),
  CONSTRAINT `FK_receita_conta` FOREIGN KEY (`conta_id`) REFERENCES `conta` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;

-- Copiando dados para a tabela controle_financeiro.receita: ~5 rows (aproximadamente)
/*!40000 ALTER TABLE `receita` DISABLE KEYS */;
INSERT INTO `receita` (`id`, `descricao`, `valor`, `data_lancamento`, `conta_id`) VALUES
	(1, 'SALARIO', 1100, '2021-05-02', 1),
	(2, 'RIFA', 100, '2021-05-02', 1),
	(3, 'COMISSÃO', 112, '2021-05-01', 4),
	(4, 'MESADA', 50, '2021-05-03', 2),
	(5, 'DEPÓSITO', 25, '2021-05-03', 4),
	(6, 'AJUSTE MÊS ANTERIOR', 13, '2021-04-30', 2),
	(7, 'SALDO INICIAL', 2200, '2021-05-03', 6);
/*!40000 ALTER TABLE `receita` ENABLE KEYS */;

-- Copiando estrutura para procedure controle_financeiro.receitas_por_user
DELIMITER //
CREATE DEFINER=`root`@`localhost` PROCEDURE `receitas_por_user`(
	IN `nome_user` VARCHAR(50)
)
BEGIN
	SELECT r.*, c.nome AS contas FROM receita r 
		INNER JOIN conta c ON r.conta_id = c.id
		INNER JOIN usuario u ON c.usuario = u.id
	WHERE u.user = nome_user ORDER BY r.data_lancamento DESC;
END//
DELIMITER ;

-- Copiando estrutura para tabela controle_financeiro.saida
CREATE TABLE IF NOT EXISTS `saida` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tipo` enum('DESPESA','TRANSF') NOT NULL,
  `valor` double NOT NULL,
  `data_saida` date NOT NULL,
  `conta_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `conta_id` (`conta_id`),
  CONSTRAINT `FK_saida_conta` FOREIGN KEY (`conta_id`) REFERENCES `conta` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;

-- Copiando dados para a tabela controle_financeiro.saida: ~7 rows (aproximadamente)
/*!40000 ALTER TABLE `saida` DISABLE KEYS */;
INSERT INTO `saida` (`id`, `tipo`, `valor`, `data_saida`, `conta_id`) VALUES
	(1, 'DESPESA', 220, '2021-05-02', 1),
	(2, 'TRANSF', 85, '2021-05-02', 1),
	(3, 'DESPESA', 93, '2021-05-03', 4),
	(4, 'DESPESA', 120, '2021-05-04', 2),
	(5, 'TRANSF', 125, '2021-05-04', 1),
	(6, 'DESPESA', 90, '2021-05-05', 6),
	(7, 'TRANSF', 210, '2021-05-05', 6);
/*!40000 ALTER TABLE `saida` ENABLE KEYS */;

-- Copiando estrutura para procedure controle_financeiro.saidas_por_user
DELIMITER //
CREATE DEFINER=`root`@`localhost` PROCEDURE `saidas_por_user`(
	IN `nome_user` VARCHAR(50)

)
BEGIN
	SELECT s.tipo, s.valor, s.data_saida, c.nome AS contas FROM saida s 
		INNER JOIN conta c ON s.conta_id = c.id
		INNER JOIN usuario u ON c.usuario = u.id
	WHERE u.user = nome_user ORDER BY s.data_saida DESC;
END//
DELIMITER ;

-- Copiando estrutura para função controle_financeiro.saldo_geral
DELIMITER //
CREATE DEFINER=`root`@`localhost` FUNCTION `saldo_geral`(`nome_user` VARCHAR(50)

) RETURNS double
BEGIN
	DECLARE soma DOUBLE;
	SELECT SUM(c.saldo) INTO soma FROM conta c INNER JOIN usuario u ON c.usuario = u.id WHERE u.user = nome_user;
	RETURN soma;
END//
DELIMITER ;

-- Copiando estrutura para função controle_financeiro.soma_despesas_por_mes
DELIMITER //
CREATE DEFINER=`root`@`localhost` FUNCTION `soma_despesas_por_mes`(nome_user VARCHAR(50), inicio DATE, final DATE) RETURNS double
BEGIN
	DECLARE soma DOUBLE;
	SELECT SUM(d.valor) INTO soma FROM despesa d
		INNER JOIN conta c ON d.conta_id = c.id
		INNER JOIN usuario u ON c.usuario = u.id
	WHERE u.user = nome_user AND d.data_lancamento BETWEEN inicio AND final;
	RETURN soma;
END//
DELIMITER ;

-- Copiando estrutura para função controle_financeiro.soma_receitas_por_mes
DELIMITER //
CREATE DEFINER=`root`@`localhost` FUNCTION `soma_receitas_por_mes`(`nome_user` VARCHAR(50),
	`inicio` DATE,
	`final` DATE
) RETURNS double
BEGIN
	DECLARE soma DOUBLE;
	SELECT SUM(r.valor) INTO soma FROM receita r
		INNER JOIN conta c ON r.conta_id = c.id
		INNER JOIN usuario u ON c.usuario = u.id
	WHERE u.user = nome_user AND r.data_lancamento BETWEEN inicio AND final;
	RETURN soma;
END//
DELIMITER ;

-- Copiando estrutura para tabela controle_financeiro.transferencia
CREATE TABLE IF NOT EXISTS `transferencia` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `descricao` varchar(50) NOT NULL,
  `valor` double NOT NULL DEFAULT 0,
  `data_lancamento` date NOT NULL,
  `origem` int(11) NOT NULL,
  `destino` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `origem` (`origem`),
  KEY `destino` (`destino`),
  CONSTRAINT `FK_transferencia_conta` FOREIGN KEY (`origem`) REFERENCES `conta` (`id`),
  CONSTRAINT `FK_transferencia_conta_2` FOREIGN KEY (`destino`) REFERENCES `conta` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

-- Copiando dados para a tabela controle_financeiro.transferencia: ~0 rows (aproximadamente)
/*!40000 ALTER TABLE `transferencia` DISABLE KEYS */;
INSERT INTO `transferencia` (`id`, `descricao`, `valor`, `data_lancamento`, `origem`, `destino`) VALUES
	(1, 'EMPRESTIMO', 85, '2021-05-02', 1, 2),
	(2, 'INVESTIMENTO', 125, '2021-05-04', 1, 4),
	(3, 'EMPRÉSTIMO', 210, '2021-05-05', 6, 7);
/*!40000 ALTER TABLE `transferencia` ENABLE KEYS */;

-- Copiando estrutura para procedure controle_financeiro.transf_por_user
DELIMITER //
CREATE DEFINER=`root`@`localhost` PROCEDURE `transf_por_user`(
	IN `nome_user` VARCHAR(50)
)
BEGIN
	SELECT t.*, c.nome AS org, cc.nome AS dest FROM transferencia t
		INNER JOIN conta c ON t.origem = c.id
		INNER JOIN conta cc ON t.destino = cc.id
		INNER JOIN usuario u ON c.usuario = u.id AND cc.usuario = u.id
	WHERE u.user = nome_user ORDER BY t.data_lancamento DESC;
END//
DELIMITER ;

-- Copiando estrutura para tabela controle_financeiro.usuario
CREATE TABLE IF NOT EXISTS `usuario` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user` varchar(20) NOT NULL,
  `senha` text NOT NULL,
  `criado_em` datetime NOT NULL DEFAULT current_timestamp(),
  PRIMARY KEY (`id`),
  UNIQUE KEY `usuario` (`user`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

-- Copiando dados para a tabela controle_financeiro.usuario: ~1 rows (aproximadamente)
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` (`id`, `user`, `senha`, `criado_em`) VALUES
	(1, 'joker', '202cb962ac59075b964b07152d234b70', '2021-04-29 20:34:15'),
	(2, 'bruce_wayne', '202cb962ac59075b964b07152d234b70', '2021-05-05 19:24:51');
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;

-- Copiando estrutura para trigger controle_financeiro.depositos
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_ZERO_IN_DATE,NO_ZERO_DATE,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `depositos` AFTER INSERT ON `receita` FOR EACH ROW BEGIN
	UPDATE conta SET saldo = (saldo + NEW.valor) WHERE NEW.conta_id = conta.id;
	INSERT INTO entrada (tipo, valor, data_entrada, conta_id) VALUES ('RECEITA', NEW.valor, NEW.data_lancamento, NEW.conta_id);
END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Copiando estrutura para trigger controle_financeiro.saques
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_ZERO_IN_DATE,NO_ZERO_DATE,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `saques` AFTER INSERT ON `despesa` FOR EACH ROW BEGIN
	UPDATE conta SET saldo = (saldo - NEW.valor) WHERE NEW.conta_id = conta.id;
	INSERT INTO saida (tipo, valor, data_saida, conta_id) VALUES ('DESPESA', NEW.valor, NEW.data_lancamento, NEW.conta_id);
END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Copiando estrutura para trigger controle_financeiro.transferencias
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_ZERO_IN_DATE,NO_ZERO_DATE,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `transferencias` AFTER INSERT ON `transferencia` FOR EACH ROW BEGIN
	UPDATE conta SET saldo = (saldo - NEW.valor) WHERE NEW.origem = conta.id;
	UPDATE conta SET saldo = (saldo + NEW.valor) WHERE NEW.destino = conta.id;
	INSERT INTO saida (tipo, valor, data_saida, conta_id) VALUES ('TRANSF', NEW.valor, NEW.data_lancamento, NEW.origem);
	INSERT INTO entrada (tipo, valor, data_entrada, conta_id) VALUES ('TRANSF', NEW.valor, NEW.data_lancamento, NEW.destino);
END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
