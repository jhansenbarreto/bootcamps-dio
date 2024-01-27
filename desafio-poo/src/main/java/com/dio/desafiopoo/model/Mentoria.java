package com.dio.desafiopoo.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Jhansen Barreto
 */
@Getter
@Setter
public final class Mentoria extends Conteudo {

    private LocalDateTime dataMentoria;

    public Mentoria(String titulo, String descricao, LocalDateTime dataMentoria) {
        super(titulo, descricao);
        this.dataMentoria = dataMentoria;

        this.calcularXp();
    }

    @Override
    public void calcularXp() {
        super.setXp(20);
    }

    @Override
    public String toString() {
        return String.format("\n\tMENTORIA:\n\t\tTítulo: %s\n\t\tDescrição: %s\n\t\tData/Hora: %s\n\t\tXP: %d",
                super.getTitulo(),
                super.getDescricao(),
                dataMentoria.format(DateTimeFormatter.ofPattern("dd/MM/yyyy - HH:mm")),
                super.getXp());
    }
}
