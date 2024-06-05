package console.banco.model;

import java.util.Calendar;
import lombok.Getter;

/**
 *
 * @author Jhansen Barreto
 */
@Getter
public class ContaCorrente extends Conta {

    private final double taxaManutencao;

    public ContaCorrente(Cliente titular, String senha) {
        super(titular, senha);
        this.taxaManutencao = 12.50;
    }

    public void cobrarTaxa() {
        this.setSaldo(this.getSaldo() - taxaManutencao);
        this.salvarMovimentacao(taxaManutencao, false, "MANUTENÇÃO");
    }

    @Override
    public String toString() {
        return String.format("CC: %s", super.toString());
    }

    @Override
    public void run() {
        int dia;
        int hora;
        int min;
        boolean debitou = false;
        Calendar calendar;

        /*
            Essa thread é "ligada" quando a conta nasce e verifica sempre a data
            e horário para poder debitar a taxa de manutenção. Sempre no primeiro
            dia do mês, após as 23h30min.
        */
        while (true) {
            calendar = Calendar.getInstance();

            dia = calendar.get(Calendar.DAY_OF_MONTH);
            hora = calendar.get(Calendar.HOUR_OF_DAY);
            min = calendar.get(Calendar.MINUTE);

            //Primeiro dia do mês, à partir das 23:30, desconta a taxa e marca como debitado
            if (dia == 1 && hora == 23 && min >= 30 && !debitou) {
                this.cobrarTaxa();
                debitou = true;
            }
            //Primeiro dia do mês, à partir das 00:01, desmarca como debitado para poder taxar no fim da noite
            if (dia == 1 && hora == 0 && min >= 1 && debitou) {
                debitou = false;
            }
        }
    }
}
