package com.dio.estrutural;

/**
 * Classe do objeto concreto que será decorado (terá responsabilidades
 * incrementadas).
 *
 * @author Jhansen Barreto
 */
public class CafeSimples implements Cafe {

    @Override
    public double getPreco() {
        return 0.5;
    }

    @Override
    public String getComplementos() {
        return "Café";
    }
}
