package com.dio.comportamental;

/**
 * Interface que será implementada por classes que descrevem objetos
 * Observáveis, interesse dos objetos Observadores que serão notificados sempre
 * que o Observável de seu interesse sofrer uma mudança/atualização.
 *
 * @author Jhansen Barreto
 */
public interface Observable {

    public void registerObserver(Observer observer);

    public void removeObserver(Observer observer);

    public void notifyObservers();
}
