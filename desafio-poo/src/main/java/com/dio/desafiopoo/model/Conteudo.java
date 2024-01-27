package com.dio.desafiopoo.model;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Jhansen Barreto
 */
@Getter
@Setter
public abstract class Conteudo {

    private String titulo;
    private String descricao;
    private int xp;

    public Conteudo(String titulo, String descricao) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.xp = 0;
    }

    public abstract void calcularXp();
}
