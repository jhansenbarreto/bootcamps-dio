package com.dio.application;

import com.dio.comportamental.ObraCondominio;
import com.dio.comportamental.ProprietarioCasa;
import com.dio.criacional.FerrariBuilder;
import com.dio.criacional.FordBuilder;
import com.dio.criacional.Veiculo;
import com.dio.estrutural.Cafe;
import com.dio.estrutural.CafeComCanela;
import com.dio.estrutural.CafeComChocolate;
import com.dio.estrutural.CafeComLeite;
import com.dio.estrutural.CafeSimples;

/**
 *
 * @author Jhansen Barreto
 */
public class DesignPatterns {

    public static void main(String[] args) {
        //Para maiores detalhes sobre os padrões exemplificados ver Javadoc das classes e comentários dos métodos abaixo.

        padraoDecorator();
        padraoObserver();
        padraoBuilder();
    }

    private static void padraoDecorator() {
        System.out.println("\n[*************************EXEMPLO DECORATOR (ESTRUTURAL)*************************]");

        //Instanciando objeto
        Cafe cafe = new CafeSimples();
        cafe.imprimir();

        //Decorando objeto
        cafe = new CafeComLeite(cafe);
        cafe.imprimir();

        //Decorando objeto
        cafe = new CafeComCanela(cafe);
        cafe.imprimir();

        //Decorando objeto
        cafe = new CafeComChocolate(cafe);
        cafe.imprimir();
    }

    private static void padraoObserver() {
        System.out.println("\n[*************************EXEMPLO OBSERVER (COMPORTAMENTAL)*************************]");

        //Instanciando objetos do tipo Observer
        var pessoa1 = new ProprietarioCasa("João");
        var pessoa2 = new ProprietarioCasa("Maria");
        var pessoa3 = new ProprietarioCasa("José");

        //Instanciando objeto do tipo Observable
        var condominio = new ObraCondominio(300);

        //Registrando observers na coleção do objeto observable
        condominio.registerObserver(pessoa1);
        condominio.registerObserver(pessoa2);
        condominio.registerObserver(pessoa3);

        //Atualizando objeto observable para notificar os observers
        condominio.setConstruidas(100);

        //Removendo observer da coleção do objeto observable
        condominio.removeObserver(pessoa2);

        //Atualizando objeto observable para notificar os observers
        condominio.setConstruidas(150);
        condominio.setConstruidas(50);
        condominio.setConstruidas(1);
    }

    private static void padraoBuilder() {
        System.out.println("\n[*************************EXEMPLO BUILDER (CRIACIONAL)*************************]");

        construirFerrari(FerrariBuilder.Modelo.GTB, "Amarela", "Automática");
        construirFerrari(FerrariBuilder.Modelo.F8_TRIBUTO, "Vermelha", "Automática");
        construirFerrari(FerrariBuilder.Modelo.SP51, "Preta", "Automática");

        construirFord(FordBuilder.Modelo.RANGER, "Prata", "Automática");
        construirFord(FordBuilder.Modelo.MAVERICK, "Verde", "Manual");
        construirFord(FordBuilder.Modelo.MUSTANG, "Laranja", "Automática");
    }

    private static void construirFerrari(FerrariBuilder.Modelo modelo, String cor, String transmissao) {
        //Através do modelo, o Builder já sabe como construir o restante do objeto. 
        //Cor e transmissão também poderiam entrar na construção automática,
        //não foram colocados apenas por exemplificação

        Veiculo ferrari
                = new FerrariBuilder()
                        .modelo(modelo)
                        .cor(cor)
                        .transmissao(transmissao)
                        .builder();

        System.out.println("\n***");
        System.out.println(ferrari);
    }

    private static void construirFord(FordBuilder.Modelo modelo, String cor, String transmissao) {
        //Através do modelo, o Builder já sabe como construir o restante do objeto. 
        //Cor e transmissão também poderiam entrar na construção automática,
        //não foram colocados apenas por exemplificação

        Veiculo ford
                = new FordBuilder()
                        .modelo(modelo)
                        .cor(cor)
                        .transmissao(transmissao)
                        .builder();

        System.out.println("\n***");
        System.out.println(ford);
    }
}
