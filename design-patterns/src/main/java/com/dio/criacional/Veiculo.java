package com.dio.criacional;

import lombok.Setter;

/**
 * Classe que descreve o objeto concreto.
 *
 * @author Jhansen Barreto
 */
@Setter
public class Veiculo {

    private String montadora;
    private String modelo;
    private String motor;
    private String cor;
    private String transmissao;
    private String velocidadeMaxima;

    @Override
    public String toString() {
        return String.format(
                "CARRO:\n\tMontadora: %s\n\tModelo: %s\n\tMotor: %s\n\tCor: %s\n\tTransmissão: %s\n\tVelocidade Máxima: %s",
                montadora, modelo, motor, cor, transmissao, velocidadeMaxima);
    }
}
