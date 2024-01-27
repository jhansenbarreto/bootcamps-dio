package com.dio.estrutural;

/**
 * Classe decoradora, incrementa o objeto concreto.
 *
 * @author Jhansen Barreto
 */
public class CafeComLeite extends CafeDecorator {

    public CafeComLeite(Cafe cafe) {
        super(cafe);
    }

    @Override
    public double getPreco() {
        return super.getPreco() + 1;
    }

    @Override
    public String getComplementos() {
        return super.getComplementos() + "\n\t+ Leite";
    }
}
