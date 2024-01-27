package com.dio.criacional;

/**
 * Interface que será implementada por classes que descrevem objetos
 * Construtores, aqueles que conhecem os passos para construção do objeto
 * concreto, minimizando os esforços de quem deseja instanciá-lo.
 *
 * OBS: A própria dependência Lombok utilizada no projeto para Getters e
 * Setters, fornece uma anotação que implementa o padrão Builder, porém, para
 * fins demonstrativos, foi implementado o padrão programaticamente.
 *
 * @author Jhansen Barreto
 */
public interface VeiculoBuilder {

    public VeiculoBuilder modelo(Enum modelo);

    public VeiculoBuilder cor(String cor);

    public VeiculoBuilder transmissao(String transmissao);

    public Veiculo builder();
}
