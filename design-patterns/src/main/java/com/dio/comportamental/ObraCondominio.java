package com.dio.comportamental;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Classe que descreve um objeto Observável. Sempre que a quantidade de casas
 * construídas aumentar, os Observadores serão notificados da nova porcentagem
 * do andamento da obra.
 *
 * @author Jhansen Barreto
 */
public class ObraCondominio implements Observable {

    private final int qtdCasas;
    private int construidas;
    private double construcao;
    private final Set<Observer> observers;

    public ObraCondominio(int qtdCasas) {
        this.qtdCasas = qtdCasas;
        this.construidas = 0;
        this.construcao = 0.0;
        this.observers = new LinkedHashSet<>();

        System.out.println("\n---\nOBRA ENCOMENDADA:");
        System.out.println(String.format("\tCondomínio com %d casas", this.qtdCasas));
        System.out.println("\tIniciando obras...\n");
    }

    public void setConstruidas(int novas) {
        if (verificaObra(novas)) {
            construidas += novas;
            construcao = (((construidas * 1d) / (qtdCasas * 1d)) * 100.0);

            System.out.println(
                    String.format("\tConstruindo mais %d casas.\n\tAgora são %d casas prontas.\n\tFaltam %d casas.\n",
                            novas, construidas, (qtdCasas - construidas))
            );
            //notificar observadores sempre que a quantidades de casas construídas aumentar
            this.notifyObservers();
        }
    }

    private boolean verificaObra(int novas) {
        System.out.println(String.format("Você informou a construção de %d novas casas.", novas));
        if (construidas == qtdCasas) {
            System.out.println(String.format("\tTodas as %d casas já foram construídas.", qtdCasas));
            return false;
        }
        if (novas < 1) {
            System.out.println(String.format("\tNão é possível construir %d casas, informe um número válido.", novas));
            return false;
        }
        if (qtdCasas < (construidas + novas)) {
            System.out.println(
                    String.format("\tFaltam apenas %d casas, corrija sua informação.", (qtdCasas - construidas))
            );
            return false;
        }
        return true;
    }

    @Override
    public void registerObserver(Observer observer) {
        if (observer instanceof ProprietarioCasa proprietario) {
            System.out.println(String.format("\tAdicionando %s na lista de proprietários.\n", proprietario.getNome()));
            observers.add(proprietario);
        } else {
            System.out.println("[**********ERROR CAST REGISTER**********]");
        }
    }

    @Override
    public void removeObserver(Observer observer) {
        if (observer instanceof ProprietarioCasa proprietario) {
            System.out.println(String.format("\tRemovendo %s da lista de proprietários.\n", proprietario.getNome()));
            observers.remove(proprietario);
        } else {
            System.out.println("[**********ERROR CAST REMOVE**********]");
        }
    }

    @Override
    public void notifyObservers() {
        observers.forEach(item -> {
            if (item instanceof ProprietarioCasa proprietario) {
                System.out.println(String.format("Notificando %s sobre andamento das obras.", proprietario.getNome()));
                proprietario.update(construcao);
                System.out.println("---\n");
            } else {
                System.out.println("[**********ERROR CAST NOTIFY**********]");
            }
        });
    }
}
