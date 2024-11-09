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
            <h2>Histórico</h2>
            <br/>

            Olá <label id="destaque"><?php echo $nome_user; ?></label>, 
            aqui em <label id="destaque">Histórico</label> você acompanha todas as
            entradas e saídas lançadas.

            <br/>
            <br/>

            <div class="conteudo">
                <h2>Resumo</h2>
                <br/>

                <?php
                $con = conexaoBD();
                $sql = "CALL entradas_por_user('$nome_user')";
                $stmt = $con->prepare($sql);
                $stmt->execute();
                
                $con2 = conexaoBD();
                $sql2 = "CALL saidas_por_user('$nome_user')";
                $stmt2 = $con2->prepare($sql2);
                $stmt2->execute();
                ?>

                <table id="tbl">
                    <tr>
                        <td style="padding-right: 20px">
                            Entradas <br/>
                            <table id="table" style="margin-top: 5px;">
                                <thead>
                                    <tr>
                                        <th class="th-table">MOVIMENTAÇÃO</th>
                                        <th class="th-table">VALOR</th>
                                        <th class="th-table">DATA</th>
                                        <th class="th-table">CONTA</th>
                                    </tr>
                                </thead>

                                <tbody>
                                    <?php while ($resultado = $stmt->fetch(PDO::FETCH_ASSOC)) { ?>
                                        <tr>
                                            <td class="td-table">
                                                <?php echo $resultado["tipo"]; ?>
                                            </td>

                                            <td class="td-table">
                                                <?php echo 'R$ ' . number_format($resultado["valor"], 2, ',', '.'); ?>
                                            </td>

                                            <td class="td-table">
                                                <?php echo $resultado["data_entrada"]; ?>
                                            </td>

                                            <td class="td-table">
                                                <?php echo $resultado["contas"]; ?>
                                            </td>
                                        </tr>
                                    <?php } ?>
                                </tbody>
                                <tfoot><tr></tr></tfoot>
                            </table>
                        </td>

                        <td>
                            Saídas <br/>
                            <table id="table2" style="margin-top: 5px;">
                                <thead>
                                    <tr>
                                        <th class="th-table2">MOVIMENTAÇÃO</th>
                                        <th class="th-table2">VALOR</th>
                                        <th class="th-table2">DATA</th>
                                        <th class="th-table2">CONTA</th>
                                    </tr>
                                </thead>

                                <tbody>
                                    <?php while ($resultado2 = $stmt2->fetch(PDO::FETCH_ASSOC)) { ?>
                                        <tr>
                                            <td class="td-table2">
                                                <?php echo $resultado2["tipo"]; ?>
                                            </td>

                                            <td class="td-table2">
                                                <?php echo 'R$ ' . number_format($resultado2["valor"], 2, ',', '.'); ?>
                                            </td>

                                            <td class="td-table2">
                                                <?php echo $resultado2["data_saida"]; ?>
                                            </td>

                                            <td class="td-table2">
                                                <?php echo $resultado2["contas"]; ?>
                                            </td>
                                        </tr>
                                    <?php } ?>
                                </tbody>
                                <tfoot><tr></tr></tfoot>
                            </table>
                        </td>
                    </tr>
                </table>
                <script type="text/javascript" src="javascript/script-table.js"></script>
                <script type="text/javascript" src="javascript/script-table2.js"></script>
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