package console.banco.model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Jhansen Barreto
 */
@Getter
public abstract class Conta extends Thread implements Operacao {

    @Setter
    private Double saldo;
    private final Cliente titular;
    private final String senha;
    private final String numero;
    private final List<Movimentacao> extrato;

    public Conta(Cliente titular, String senha) {
        this.titular = titular;
        this.senha = senha;
        this.saldo = 0.0;
        this.numero = String.format("%d", Calendar.getInstance().getTimeInMillis());
        this.extrato = new ArrayList<>();
    }

    public void salvarMovimentacao(double valor, boolean isEntrada, String operacao) {
        this.extrato.add(new Movimentacao(valor, isEntrada, operacao));
    }

    @Override
    public String toString() {
        return String.format("%s | %s ", numero, titular);
    }

    public class Movimentacao {

        @Getter
        private final boolean entrada;
        private final double valor;
        private final String data;
        private final String operacao;

        public Movimentacao(double valor, boolean entrada, String operacao) {
            this.valor = valor;
            this.entrada = entrada;
            this.data = Calendar.getInstance().getTime().toString();
            this.operacao = operacao;
        }

        @Override
        public String toString() {
            return String.format("\n > %s \n\t %s no valor de R$ %.2f ", data, operacao, valor);
        }
    }
}
