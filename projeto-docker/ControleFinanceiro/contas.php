<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">

        <link rel="stylesheet" type="text/css" href="css/index.css">
        <link rel="stylesheet" type="text/css" href="css/table.css">
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">

        <script
            src="https://code.jquery.com/jquery-3.5.1.js"
            integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc="
            crossorigin="anonymous">
        </script>

        <script src="https://cdn.datatables.net/1.10.16/css/jquery.dataTables.min.css"></script>
        <script src="https://cdn.datatables.net/1.10.16/js/jquery.dataTables.min.js"></script>

        <title>ContFin</title>
    </head>

    <body>
        <?php
        require_once './database/config-bd.php';
        session_start();
        $nome_user = $_SESSION['user'];

        if (!isset($_SESSION['user'])) {
            session_destroy();
            header("Location:login.php");
            exit();
        }
        ?>

        <div id="cabecalho">
            <h1>₵ontFin - Controle Financeiro</h1>
        </div>

        <div class="div-dashboard">
            <h2>Contas</h2>
            <br/>

            Olá <label id="destaque"><?php echo $nome_user; ?></label>, 
            aqui na área de <label id="destaque">Contas</label> você pode cadastrar uma nova conta
            e ver suas contas já cadastradas.

            <br/>
            <br/>

            <div class="conteudo">
                <?php
                $con2 = conexaoBD();
                $sql2 = "SELECT saldo_geral('$nome_user') AS total";
                $stmt2 = $con2->prepare($sql2);
                $stmt2->execute();
                $saldo = $stmt2->fetch(PDO::FETCH_ASSOC);

                $con = conexaoBD();
                $sql = "CALL contas_por_user('$nome_user')";
                $stmt = $con->prepare($sql);
                $stmt->execute();
                
                $conUSER = conexaoBD();
                $sqlUSER = "SELECT id FROM usuario WHERE user = '$nome_user'";
                $stmtUSER = $conUSER->prepare($sqlUSER);
                $stmtUSER->execute();
                $idUSER = $stmtUSER->fetch(PDO::FETCH_ASSOC);
                ?>
                
                <table id="tbl">
                    <tr>
                        <td style="width: 30%">
                            <form method="POST" action="insert_sql/insert_contas.php">
                                <input type="hidden" name="id_user" value="<?php echo $idUSER['id']; ?>">
                                <table>
                                    <tr>
                                        <td>
                                            Nome da Conta: *
                                        </td>

                                        <td>
                                            <input type="text" placeholder="Descrição" name="nome" required="required"/>
                                        </td>
                                    </tr>

                                    <tr>
                                        <td>
                                            <button type="submit" name="enviar" value="Salvar">Salvar</button>
                                        </td>

                                        <td id="feedback">
                                            <?php
                                            if (isset($_SESSION["msg"])):
                                                print $_SESSION["msg"];
                                                unset($_SESSION["msg"]);
                                            endif;
                                            ?>
                                        </td>
                                    </tr>
                                </table>
                            </form>
                        </td>

                        <td>
                            <?php echo 'Saldo Geral: R$ ' . number_format($saldo["total"], 2, ',', '.'); ?>
                            <table id="table" style="margin-top: 5px;">
                                <thead>
                                    <tr>
                                        <th class="th-table">CONTA</th>
                                        <th class="th-table">SALDO</th>
                                    </tr>
                                </thead>

                                <tbody>
                                    <?php while ($resultado = $stmt->fetch(PDO::FETCH_ASSOC)) { ?>
                                        <tr>
                                            <td class="td-table">
                                                <?php echo $resultado["nome"]; ?>
                                            </td>

                                            <td class="td-table">
                                                <?php echo 'R$ ' . number_format($resultado["saldo"], 2, ',', '.'); ?>
                                            </td>
                                        </tr>
                                    <?php } ?>
                                </tbody>
                                <tfoot><tr></tr></tfoot>
                            </table>
                            <script type="text/javascript" src="javascript/script-table.js"></script>
                        </td>
                    </tr>
                </table>
            </div>

            <form method="POST" action="menu.php">
                <table id="menu" style="width: 100%">
                    <tr>
                        <td>
                            <button title="Início" name="home" class="menu-buttons" style="color: #FFF"><span class="material-icons">home</span></button>
                        </td>

                        <td>
                            <button title="Contas" name="contas" class="menu-buttons" style="color: #FFF"><span class="material-icons">account_balance</span></button>
                        </td>

                        <td>
                            <button title="Gráficos" name="grafico" class="menu-buttons" style="color: #FFF"><span class="material-icons">leaderboard</span></button>
                        </td>

                        <td>
                            <button title="Receitas" name="receitas" class="menu-buttons" style="color: #54A326"><span class="material-icons">add_circle</span></button>
                        </td>

                        <td>
                            <button title="Despesas" name="despesas" class="menu-buttons" style="color: #EE6A50"><span class="material-icons">remove_circle</span></button>
                        </td>

                        <td>
                            <button title="Transferências" name="transfer" class="menu-buttons" style="color: #EEEE00"><span class="material-icons">swap_horizontal_circle</span></button>
                        </td>

                        <td>
                            <button title="Histórico" name="hist" class="menu-buttons" style="color: #FFF"><span class="material-icons">history</span></button>
                        </td>

                        <td>
                            <button title="Sair" name="sair" class="menu-buttons" style="color: #FFF"><span class="material-icons">logout</span></button>
                        </td>
                    </tr>
                </table>
            </form>
        </div>

        <div id="footer">
            Copyright © 2021 | Jhansen Barreto
        </div>
    </body>
</html>