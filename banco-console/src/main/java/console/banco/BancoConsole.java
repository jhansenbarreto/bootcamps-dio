package console.banco;

import console.banco.model.Cliente;
import console.banco.model.Conta;
import console.banco.utils.CorConsole;

import java.io.IOException;
import java.io.PrintStream;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Jhansen Barreto
 */
public class BancoConsole {

    private static final String FUNDO_LETRA_PADRAO = CorConsole.LETRA_AZUL + CorConsole.BACKGROUND_BRANCO;
    private static final String FUNDO_LETRA_ERRO = CorConsole.LETRA_BRANCA + CorConsole.BACKGROUND_VERMELHO;
    private static final String FUNDO_LETRA_ATENCAO = CorConsole.LETRA_PRETA + CorConsole.BACKGROUND_AMARELO;
    private static final String FUNDO_LETRA_SUCESSO = CorConsole.LETRA_BRANCA + CorConsole.BACKGROUND_VERDE;

    //Para imprimir extrato
    private static final String FUNDO_LETRA_ENTRADA = CorConsole.LETRA_VERDE + CorConsole.BACKGROUND_BRANCO;
    private static final String FUNDO_LETRA_SAIDA = CorConsole.LETRA_VERMELHA + CorConsole.BACKGROUND_BRANCO;

    //Exemplo simples, aceita apenas letras maiúsculas e minúsculas sem acento e sem cedilha
    private static final String REGEX_NOME = "([a-zA-Z ]+)+";
    //Aceita apenas um grupo de 11 algarismos
    private static final String REGEX_CPF = "[0-9]{11}";

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        PrintStream out = System.out;
        List<Conta> contas = new ArrayList<>();

        menu(scan, out, contas);
    }

    private static void opcoesMenu(PrintStream out) {
        limparConsole();

        out.println(FUNDO_LETRA_PADRAO + "\n # MENU: " + CorConsole.RESET);
        out.println(FUNDO_LETRA_PADRAO + "\t 1 - ABRIR CONTA " + CorConsole.RESET);
        out.println(FUNDO_LETRA_PADRAO + "\t 2 - CONSULTAR SALDO " + CorConsole.RESET);
        out.println(FUNDO_LETRA_PADRAO + "\t 3 - EFETUAR DEPÓSITO " + CorConsole.RESET);
        out.println(FUNDO_LETRA_PADRAO + "\t 4 - EFETUAR SAQUE " + CorConsole.RESET);
        out.println(FUNDO_LETRA_PADRAO + "\t 5 - FAZER TRANSFERÊNCIA " + CorConsole.RESET);
        out.println(FUNDO_LETRA_PADRAO + "\t 6 - IMPRIMIR EXTRATO " + CorConsole.RESET);
        out.println(FUNDO_LETRA_PADRAO + "\t 0 - SAIR " + CorConsole.RESET);
    }

    private static void menu(Scanner scan, PrintStream out, List contas) {
        opcoesMenu(out);

        String opcao = "-1";

        while (opcao.equals("-1")) {
            out.print(FUNDO_LETRA_PADRAO + "\n Digite o número da opção desejada:" + CorConsole.RESET);
            opcao = scan.nextLine();

            switch (opcao) {
                case "0" -> {
                    out.println(FUNDO_LETRA_SUCESSO + "\n Encerrando o sistema. " + CorConsole.RESET);
                    System.exit(0);
                }
                case "1" -> {
                    abrirConta(scan, out, contas);
                    opcao = "-1";
                }
                case "2" -> {
                    consultarSaldo(scan, out, contas);
                    opcao = "-1";
                }
                case "3" -> {
                    efetuarDeposito(scan, out, contas);
                    opcao = "-1";
                }
                case "4" -> {
                    efetuarSaque(scan, out, contas);
                    opcao = "-1";
                }
                case "5" -> {
                    fazerTransferencia(scan, out, contas);
                    opcao = "-1";
                }
                case "6" -> {
                    imprimirExtrato(scan, out, contas);
                    opcao = "-1";
                }
                default -> {
                    out.println(FUNDO_LETRA_ERRO + " ERRO: Digite uma opção válida. " + CorConsole.RESET);
                    out.println(FUNDO_LETRA_ATENCAO + " Um número entre 0 e 6 " + CorConsole.RESET);
                    opcao = "-1";
                }
            }
        }
    }

    //OPÇÃO 1
    private static void abrirConta(Scanner scan, PrintStream out, List contas) {
        limparConsole();

        out.println(FUNDO_LETRA_PADRAO + "\n # ABRIR CONTA: " + CorConsole.RESET);
        out.println(FUNDO_LETRA_PADRAO + " Seja bem-vindo(a)! " + CorConsole.RESET);

        String nome = getNomeCliente(scan, out, "-1");
        String cpf = getCpfCliente(scan, out, "-1");
        String senha = getSenhaConta(scan, out, "-1");

        Conta conta = new Conta(new Cliente(nome, cpf), senha);
        contas.add(conta);

        System.out.println(
                String.format(
                        "\n%s CONTA CRIADA!\n Obrigado pela preferência, %s %s",
                        FUNDO_LETRA_SUCESSO,
                        conta.getTitular(),
                        CorConsole.RESET)
        );
        System.out.println(
                String.format(
                        "\n%s Sua conta tem o número: %s %s",
                        FUNDO_LETRA_SUCESSO,
                        conta.getNumero(),
                        CorConsole.RESET)
        );

        voltarAoMenu(scan, out);
    }

    private static String getNomeCliente(Scanner scan, PrintStream out, String nome) {
        while (nome.equals("-1")) {
            out.print(FUNDO_LETRA_PADRAO + "\n Digite seu nome completo:" + CorConsole.RESET);
            nome = scan.nextLine();

            if (nome.equals("-1") || !nome.matches(REGEX_NOME)) {
                out.println(FUNDO_LETRA_ERRO + " ERRO: Informe um nome válido. " + CorConsole.RESET);
                out.println(FUNDO_LETRA_ATENCAO + " Apenas letras maiúsculas e minúsculas sem acento e sem cedilha! " + CorConsole.RESET);
                nome = "-1";
            }
        }
        return nome;
    }

    private static String getCpfCliente(Scanner scan, PrintStream out, String cpf) {
        while (cpf.equals("-1")) {
            out.print(FUNDO_LETRA_PADRAO + "\n Digite seu CPF (apenas números):" + CorConsole.RESET);
            cpf = scan.nextLine();

            if (cpf.equals("-1") || !cpf.matches(REGEX_CPF)) {
                out.println(FUNDO_LETRA_ERRO + " ERRO: Informe um CPF válido. " + CorConsole.RESET);
                out.println(FUNDO_LETRA_ATENCAO + " Digite os 11 números, sem pontos (.) ou traços (-) " + CorConsole.RESET);
                cpf = "-1";
            }
        }
        return cpf;
    }

    private static String getSenhaConta(Scanner scan, PrintStream out, String senha) {
        while (senha.equals("-1")) {
            out.print(FUNDO_LETRA_PADRAO + "\n Escolha uma senha (no mínimo 6 caracteres):" + CorConsole.RESET);
            senha = scan.nextLine();

            if (senha.equals("-1") || (senha.length() < 6)) {
                out.println(FUNDO_LETRA_ERRO + " ERRO: Informe uma senha válida. " + CorConsole.RESET);
                out.println(FUNDO_LETRA_ATENCAO + " No mínimo 6 caracteres! " + CorConsole.RESET);
                senha = "-1";
            }
        }

        String repetirSenha = "-1";
        while (!repetirSenha.equals(senha)) {
            out.print(FUNDO_LETRA_PADRAO + "\n Digite novamente a senha:" + CorConsole.RESET);
            repetirSenha = scan.nextLine();

            if (!repetirSenha.equals(senha)) {
                out.println(FUNDO_LETRA_ERRO + " ERRO: As senhas não coincidem. " + CorConsole.RESET);
                out.println(FUNDO_LETRA_ATENCAO + " Informe a senha correta! " + CorConsole.RESET);
            }
        }
        return repetirSenha;
    }

    //OPÇÃO 2
    private static void consultarSaldo(Scanner scan, PrintStream out, List contas) {
        limparConsole();

        out.println(FUNDO_LETRA_PADRAO + "\n # CONSULTAR SALDO: " + CorConsole.RESET);

        var conta = buscarConta(scan, out, contas);
        if (consultarSenha(scan, out, conta, "-1")) {
            mostrarSaldo(out, conta);
        }
        voltarAoMenu(scan, out);
    }

    private static void mostrarSaldo(PrintStream out, Conta conta) {
        out.println(
                String.format("%s\n Saldo da sua conta: R$ %.2f %s",
                        FUNDO_LETRA_SUCESSO,
                        conta.getSaldo(),
                        CorConsole.RESET)
        );
    }

    private static Conta buscarConta(Scanner scan, PrintStream out, List<Conta> contas) {
        out.print(FUNDO_LETRA_PADRAO + "\n Digite o número da conta:" + CorConsole.RESET);
        String numero = scan.nextLine();

        var optConta = contas.stream().filter(c -> c.getNumero().equals(numero)).findFirst();
        if (!optConta.isEmpty()) {
            out.println(FUNDO_LETRA_SUCESSO + "\n CONTA ENCONTRADA! " + CorConsole.RESET);
            out.println(
                    String.format("%s Titular: %s %s",
                            FUNDO_LETRA_PADRAO,
                            optConta.get().getTitular(),
                            CorConsole.RESET)
            );
            return optConta.get();
        } else {
            out.println(FUNDO_LETRA_ERRO + " ERRO: Conta não encontrada. " + CorConsole.RESET);
            out.println(FUNDO_LETRA_ATENCAO + " Informe um número de conta válido " + CorConsole.RESET);
            return buscarConta(scan, out, contas);
        }
    }

    private static boolean consultarSenha(Scanner scan, PrintStream out, Conta conta, String senha) {
        while (!conta.getSenha().equals(senha)) {
            out.print(FUNDO_LETRA_PADRAO + "\n Digite sua senha:" + CorConsole.RESET);
            senha = scan.nextLine();
            if (!conta.getSenha().equals(senha)) {
                out.println(FUNDO_LETRA_ERRO + " ERRO: Senha incorreta. " + CorConsole.RESET);
            }
        }
        return true;
    }

    //OPÇÃO 3
    private static void efetuarDeposito(Scanner scan, PrintStream out, List contas) {
        limparConsole();

        out.println(FUNDO_LETRA_PADRAO + "\n # EFETUAR DEPÓSITO: " + CorConsole.RESET);

        var conta = buscarConta(scan, out, contas);
        double valor = 0.0;

        while (valor <= 0) {
            out.print(FUNDO_LETRA_PADRAO + "\n Informe o valor: R$" + CorConsole.RESET);
            try {
                //não usei nextDouble para evitar o erro de quebra de linha
                valor = Double.parseDouble(scan.nextLine());
                conta.deposito(conta, valor);
                out.println(FUNDO_LETRA_SUCESSO + "\n MOVIMENTAÇÃO BANCÁRIA EFETUADA! " + CorConsole.RESET);

            } catch (NumberFormatException ex) {
                out.println(FUNDO_LETRA_ERRO + " ERRO: digite uma quantia válida. " + CorConsole.RESET);
                out.println(FUNDO_LETRA_ATENCAO + " Informe um número positivo inteiro ou real separando os centavos por ponto (.) " + CorConsole.RESET);
                valor = 0.0;
            }
        }
        voltarAoMenu(scan, out);
    }

    //OPÇÃO 4 
    private static void efetuarSaque(Scanner scan, PrintStream out, List contas) {
        limparConsole();

        out.println(FUNDO_LETRA_PADRAO + "\n # EFETUAR SAQUE: " + CorConsole.RESET);

        var conta = buscarConta(scan, out, contas);
        if (consultarSenha(scan, out, conta, "-1")) {
            if (verificaSaldo(scan, out, conta)) {
                saqueTransferencia(scan, out, conta, null);
            }
        }
        voltarAoMenu(scan, out);
    }

    public static boolean verificaSaldo(Scanner scan, PrintStream out, Conta conta) {
        mostrarSaldo(out, conta);
        if (conta.getSaldo() == 0) {
            out.println(FUNDO_LETRA_ATENCAO + " Seu saldo está zerado. " + CorConsole.RESET);
            return false;
        }
        return true;
    }

    private static void fazerTransferencia(Scanner scan, PrintStream out, List contas) {
        limparConsole();

        out.println(FUNDO_LETRA_PADRAO + "\n # FAZER TRANSFERÊNCIA: " + CorConsole.RESET);

        var origem = buscarConta(scan, out, contas);

        if (consultarSenha(scan, out, origem, "-1")) {
            if (verificaSaldo(scan, out, origem)) {
                out.print(FUNDO_LETRA_PADRAO + "\n Escolher conta de destino." + CorConsole.RESET);
                var destino = buscarConta(scan, out, contas);
                saqueTransferencia(scan, out, origem, destino);
            }
        }
        voltarAoMenu(scan, out);
    }

    private static void saqueTransferencia(Scanner scan, PrintStream out, Conta origem, Conta destino) {
        double valor = 0.0;
        while (valor <= 0) {
            out.print(FUNDO_LETRA_PADRAO + "\n Informe o valor: R$" + CorConsole.RESET);
            try {
                //não usei nextDouble para evitar o erro de quebra de linha
                valor = Double.parseDouble(scan.nextLine());

                if (destino == null) {
                    origem.saque(origem, valor); //Se destino == null é um saque
                } else {
                    origem.transferencia(origem, destino, valor); //Senão é transferência
                }
                out.println(FUNDO_LETRA_SUCESSO + "\n MOVIMENTAÇÃO BANCÁRIA EFETUADA! " + CorConsole.RESET);

            } catch (NumberFormatException ex) {
                out.println(FUNDO_LETRA_ERRO + " ERRO: digite uma quantia válida. " + CorConsole.RESET);
                out.println(FUNDO_LETRA_ATENCAO + " Informe um número positivo inteiro ou real separando os centavos por ponto (.) " + CorConsole.RESET);
                valor = 0.0;

            } catch (IllegalArgumentException ex) {
                out.println(FUNDO_LETRA_ERRO + ex.getMessage() + CorConsole.RESET);
                out.println(FUNDO_LETRA_ATENCAO + " Informe um valor menor ou igual ao saldo. " + CorConsole.RESET);
                valor = 0.0;
            }
        }
    }

    //OPÇÃO 6
    private static void imprimirExtrato(Scanner scan, PrintStream out, List contas) {
        limparConsole();

        out.println(FUNDO_LETRA_PADRAO + "\n # IMPRIMIR EXTRATO: " + CorConsole.RESET);

        var conta = buscarConta(scan, out, contas);

        if (consultarSenha(scan, out, conta, "-1")) {
            out.println(FUNDO_LETRA_PADRAO + "\n ------------------EXTRATO------------------ \n" + CorConsole.RESET);

            out.println(
                    String.format(
                            "%s Titular: %s \n Saldo: R$ %.2f %s",
                            FUNDO_LETRA_PADRAO,
                            conta.getTitular(),
                            conta.getSaldo(),
                            CorConsole.RESET));

            conta.getExtrato().forEach(item -> {
                if (item.isEntrada()) {
                    out.println(FUNDO_LETRA_ENTRADA + item + CorConsole.RESET);
                } else {
                    out.println(FUNDO_LETRA_SAIDA + item + CorConsole.RESET);
                }
            });
            out.println(FUNDO_LETRA_PADRAO + "\n ------------------------------------------- " + CorConsole.RESET);
        }
        voltarAoMenu(scan, out);
    }

    private static void voltarAoMenu(Scanner scan, PrintStream out) {
        String voltar = "-1";
        while (!voltar.equals("0")) {
            out.print(FUNDO_LETRA_PADRAO + "\n Digite 0 para voltar ao MENU:" + CorConsole.RESET);
            voltar = scan.nextLine();

            if (voltar.equals("0")) {
                opcoesMenu(out);
                return;
            } else {
                out.println(FUNDO_LETRA_ERRO + " ERRO: opção inválida. " + CorConsole.RESET);
            }
        }
    }

    private static void limparConsole() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                Runtime.getRuntime().exec("clear");
            }
        } catch (IOException | InterruptedException ex) {
            System.out.println("ERRO: " + ex.getMessage());
        }
    }
}
