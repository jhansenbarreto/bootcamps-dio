package console.banco.model;

/**
 *
 * @author Jhansen Barreto
 */
public record Cliente(String nome, String cpf) {

    @Override
    public String toString() {
        return this.nome.toUpperCase();
    }
}
