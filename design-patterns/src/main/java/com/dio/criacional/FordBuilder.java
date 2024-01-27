package com.dio.criacional;

import lombok.Getter;

/**
 * Classe construtora do objeto concreto. Através da informação do valor de um
 * atributo, a classe já conhece os passos para construir o restante do objeto.
 *
 * @author Jhansen Barreto
 */
public class FordBuilder implements VeiculoBuilder {

    private final Veiculo veiculo;

    public FordBuilder() {
        this.veiculo = new Veiculo();
        this.veiculo.setMontadora("FORD");
    }

    @Override
    public VeiculoBuilder modelo(Enum modelo) {
        Modelo m = (Modelo) modelo;

        veiculo.setModelo(m.getModelo());
        veiculo.setMotor(m.getMotor().getMotor());
        veiculo.setVelocidadeMaxima(m.getMotor().getVelocidade());

        return this;
    }

    @Override
    public VeiculoBuilder transmissao(String transmissao) {
        veiculo.setTransmissao(transmissao);
        return this;
    }

    @Override
    public Veiculo builder() {
        return veiculo;
    }

    @Override
    public VeiculoBuilder cor(String cor) {
        veiculo.setCor(cor);
        return this;
    }

    @Getter
    public enum Modelo {

        RANGER("Ford Ranger Storm", Motor.DIESEL_32),
        MAVERICK("Ford Maverick", Motor.V8_ANTIGO),
        MUSTANG("Ford Mustang Mach 1", Motor.COYOTE);

        public String modelo;
        public Motor motor;

        Modelo(String modelo, Motor motor) {
            this.modelo = modelo;
            this.motor = motor;
        }
    }

    @Getter
    public enum Motor {

        DIESEL_32("Turbo Diesel | 3.2 | 200cv", "180km/h"),
        V8_ANTIGO("V8 | 5.0 | 197cv", "182km/h"),
        COYOTE("V8 | 5.0 Aspirado | 483cv", "250km/h");

        private final String motor;
        private final String velocidade;

        Motor(String motor, String velocidade) {
            this.motor = motor;
            this.velocidade = velocidade;
        }
    }
}
