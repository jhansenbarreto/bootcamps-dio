package com.dio.desafiopoo.model;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Jhansen Barreto
 */
@Getter
@Setter
public final class Curso extends Conteudo {

    private int cargaHoraria;

    public Curso(String titulo, String descricao, int cargaHoraria) {
        super(titulo, descricao);
        this.cargaHoraria = cargaHoraria;

        this.calcularXp();
    }

    @Override
    public void calcularXp() {
        super.setXp(cargaHoraria * 10);
    }

    @Override
    public String toString() {
        return String.format("\n\tCURSO:\n\t\tTítulo: %s\n\t\tDescrição: %s\n\t\tCarga Horária: %d horas\n\t\tXP: %d",
                super.getTitulo(),
                super.getDescricao(),
                cargaHoraria,
                super.getXp());
    }
}
