package console.banco.model;

import java.util.Calendar;
import lombok.Getter;

/**
 *
 * @author Jhansen Barreto
 */
@Getter
public class ContaPoupanca extends Conta {

    private final double taxaJuros;

    public ContaPoupanca(Cliente titular, String senha) {
        super(titular, senha);
        this.taxaJuros = 0.005;
    }

    public void creditarJuros() {
        double valor = getSaldo() * taxaJuros;
        
        if (valor > 0) {
            this.setSaldo(this.getSaldo() + valor);
            this.salvarMovimentacao(valor, true, "JUROS 0.5%");
        }
    }

    @Override
    public String toString() {
        return String.format("CP: %s", super.toString());
    }
    
    @Override
    public void run() {
        int dia;
        int hora;
        int min;
        boolean creditou = false;
        Calendar calendar;

        /*
            Essa thread é "ligada" quando a conta nasce e verifica sempre a data
            e horário para poder creditar os juros da conta. Sempre no primeiro
            dia do mês, após as 23h30min.
        */
        while (true) {
            calendar = Calendar.getInstance();

            dia = calendar.get(Calendar.DAY_OF_MONTH);
            hora = calendar.get(Calendar.HOUR_OF_DAY);
            min = calendar.get(Calendar.MINUTE);

            //Primeiro dia do mês, à partir das 23:30, credita os juros e marca como creditado
            if (dia == 1 && hora == 23 && min >= 30 && !creditou) {
                this.creditarJuros();
                creditou = true;
            }
            //Primeiro dia do mês, à partir das 00:01, desmarca como creditado para poder creditar no fim da noite
            if (dia == 1 && hora == 0 && min >= 1 && creditou) {
                creditou = false;
            }
        }
    }
}
