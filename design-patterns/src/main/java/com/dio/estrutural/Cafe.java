package com.dio.estrutural;

/**
 * Interface a ser implementada pela classe base do objeto que será "decorado",
 * além da classe base "decoradora" que será extendida por cada "decorador".
 *
 * @author Jhansen Barreto
 */
public interface Cafe {

    public double getPreco();

    public String getComplementos();

    default void imprimir() {
        System.out.println(String.format("\nValor Total: R$ %.2f\n%s", this.getPreco(), this.getComplementos()));
    }
}
