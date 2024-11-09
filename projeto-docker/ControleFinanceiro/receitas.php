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
            <h2>Receitas</h2>
            <br/>

            Olá <label id="destaque"><?php echo $nome_user; ?></label>, 
            na área de <label id="destaque">Receitas</label> você pode lançar suas
            entradas.

            <br/>
            <br/>

            <div class="conteudo">
                <h2>Lançar Receita</h2>
                <br/>

                <?php
                $conta = conexaoBD();
                $sqlconta = "SELECT c.id, c.nome FROM conta c INNER JOIN usuario u ON c.usuario = u.id WHERE u.user = '$nome_user'";
                $stmtconta = $conta->prepare($sqlconta);
                $stmtconta->execute();
                ?>

                <table id="tbl">
                    <tr>
                        <td style="width: 30%">
                            <form method="POST" action="insert_sql/insert_receita.php">
                                <table>
                                    <tr>
                                        <td>
                                            Descrição: *
                                        </td>

                                        <td>
                                            <input type="text" placeholder="Descrição" name="desc" required="required"/>
                                        </td>
                                    </tr>

                                    <tr>
                                        <td>
                                            Valor: *
                                        </td>

                                        <td>
                                            <input type="number" placeholder="R$ 0,00" name="valor" step="0.01" min="0" required="required"/>
                                        </td>
                                    </tr>

                                    <tr>
                                        <td>
                                            Data: *
                                        </td>

                                        <td>
                                            <input type="date" placeholder="01/01/0000" name="data" required="required"/>
                                        </td>
                                    </tr>

                                    <tr>
                                        <td>
                                            Conta: *
                                        </td>

                                        <td>
                                            <select name="conta" required="required">
                                                <?php while ($contas = $stmtconta->fetch(PDO::FETCH_ASSOC)) { ?>
                                                    <option value="<?php echo $contas["id"] ?>"><?php echo $contas["nome"]; ?></option>
                                                <?php } ?>
                                            </select>
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
                            <?php
                            $con = conexaoBD();
                            $sql = "CALL receitas_por_user ('$nome_user')";
                            $stmt = $con->prepare($sql);
                            $stmt->execute();
                            ?>

                            <table id="table" style="margin-top: 5px;">
                                <thead>
                                    <tr>
                                        <th class="th-table">DESCRIÇÃO</th>
                                        <th class="th-table">VALOR</th>
                                        <th class="th-table">DATA</th>
                                        <th class="th-table">CONTA</th>
                                    </tr>
                                </thead>

                                <tbody>
                                    <?php while ($resultado = $stmt->fetch(PDO::FETCH_ASSOC)) { ?>
                                        <tr>
                                            <td class="td-table">
                                                <?php echo $resultado["descricao"]; ?>
                                            </td>

                                            <td class="td-table">
                                                <?php echo 'R$ ' . number_format($resultado["valor"], 2, ',', '.'); ?>
                                            </td>

                                            <td class="td-table">
                                                <?php echo $resultado["data_lancamento"]; ?>
                                            </td>

                                            <td class="td-table">
                                                <?php echo $resultado["contas"]; ?>
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