package com.dio.comportamental;

/**
 * Interface que será implementada por classes que descrevem objetos
 * Observadores, interessados na mudança/atualização de objetos Observáveis.
 *
 * @author Jhansen Barreto
 */
public interface Observer {

    public void update(double porcentagem);
}
