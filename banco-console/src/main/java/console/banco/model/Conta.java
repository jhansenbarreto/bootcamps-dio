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
public class Conta implements Operacao {

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

    public void salvarMovimentacao(double valor, boolean isEntrada) {
        this.extrato.add(new Movimentacao(valor, isEntrada));
    }

    public class Movimentacao {

        @Getter
        private final boolean entrada;
        private final double valor;
        private final String data;

        public Movimentacao(double valor, boolean entrada) {
            this.valor = valor;
            this.entrada = entrada;
            this.data = Calendar.getInstance().getTime().toString();
        }

        @Override
        public String toString() {
            return String.format("\n > %s \n\t %s no valor de R$ %.2f ", data, (entrada ? "ENTRADA" : "SAÍDA"), valor);
        }
    }
}
