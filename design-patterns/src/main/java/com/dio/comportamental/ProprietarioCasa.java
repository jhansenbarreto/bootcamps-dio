package com.dio.comportamental;

import lombok.Getter;

/**
 * Classe que descreve um objeto Observador. Sempre será notificado quando o
 * Observável sofrer mudança/atualização.
 *
 * @author Jhansen Barreto
 */
@Getter
public class ProprietarioCasa implements Observer {

    private final String nome;

    public ProprietarioCasa(String nome) {
        this.nome = nome.toUpperCase();
    }

    @Override
    public void update(double porcentagem) {
        if (porcentagem == 100) {
            System.out.println(
                    String.format("\tPARABÉNS, %s! A OBRA ACABOU!!!", nome)
            );
        } else {
            System.out.println(
                    String.format(
                            "\tMais um pouquinho de paciência, %s... A obra do condomínio está em %.2f%%",
                            nome, porcentagem
                    )
            );
        }
    }
}
