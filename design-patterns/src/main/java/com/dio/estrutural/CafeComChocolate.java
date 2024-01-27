package com.dio.estrutural;

/**
 * Classe decoradora, incrementa o objeto concreto.
 *
 * @author Jhansen Barreto
 */
public class CafeComChocolate extends CafeDecorator {

    public CafeComChocolate(Cafe cafe) {
        super(cafe);
    }

    @Override
    public double getPreco() {
        return super.getPreco() + 1.25;
    }

    @Override
    public String getComplementos() {
        return super.getComplementos() + "\n\t+ Calda de Chocolate";
    }
}
