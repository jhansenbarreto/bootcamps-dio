package console.banco.model;

/**
 *
 * @author Jhansen Barreto
 */
public interface Operacao {

    default double saque(Conta conta, double valor) throws IllegalArgumentException {
        double saldo = conta.getSaldo();

        if (valor <= saldo) {
            saldo -= valor;
            conta.setSaldo(saldo);
            conta.salvarMovimentacao(valor, false);
            return valor;
        }
        throw new IllegalArgumentException(" ERRO: Saldo insuficiente. ");
    }

    default void deposito(Conta conta, double valor) {
        double saldo = conta.getSaldo();
        saldo += valor;
        conta.setSaldo(saldo);
        conta.salvarMovimentacao(valor, true);
    }

    default void transferencia(Conta origem, Conta destino, double valor) throws IllegalArgumentException {
        deposito(destino, saque(origem, valor));
    }
}
