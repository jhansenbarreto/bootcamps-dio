package console.banco.model;

/**
 *
 * @author Jhansen Barreto
 */
public interface Operacao {

    default double saque(Conta conta, double valor, String operacao) throws IllegalArgumentException {
        double saldo = conta.getSaldo();

        if (valor <= saldo) {
            saldo -= valor;
            conta.setSaldo(saldo);
            conta.salvarMovimentacao(valor, false, operacao);
            return valor;
        }
        throw new IllegalArgumentException(" ERRO: Saldo insuficiente. ");
    }

    default void deposito(Conta conta, double valor, String operacao) {
        double saldo = conta.getSaldo();
        saldo += valor;
        conta.setSaldo(saldo);
        conta.salvarMovimentacao(valor, true, operacao);
    }

    default void transferencia(Conta origem, Conta destino, double valor, String operacao) throws IllegalArgumentException {
        deposito(destino, saque(origem, valor, operacao), operacao);
    }
}
