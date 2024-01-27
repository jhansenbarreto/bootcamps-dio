package com.dio.estrutural;

/**
 * Classe abstrata que define a implementação base de cada "decorador", que irá
 * incrementar responsabilidades ao objeto "decorado".
 *
 * @author Jhansen Barreto
 */
public abstract class CafeDecorator implements Cafe {

    private final Cafe cafe;

    public CafeDecorator(Cafe cafe) {
        this.cafe = cafe;
    }

    @Override
    public double getPreco() {
        return cafe.getPreco();
    }

    @Override
    public String getComplementos() {
        return cafe.getComplementos();
    }
}
