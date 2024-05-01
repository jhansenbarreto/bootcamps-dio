package desafio.iphone;

import desafio.iphone.model.IPhone;

/**
 *
 * @author Jhansen Barreto
 */
public class DesafioIPhone {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        var iPhone = new IPhone();
        
        System.out.println("\n# Funções do Aparelho Telefônico");
        iPhone.ligar();
        iPhone.atender();
        iPhone.iniciarCorreioDeVoz();

        System.out.println("\n# Funções do Navegador de Internet");
        iPhone.selecionarMusica();
        iPhone.tocar();
        iPhone.pausar();

        System.out.println("\n# Funções do Reprodutor Musical");
        iPhone.adicionarNovaAba();
        iPhone.exibirPagina();
        iPhone.atualizarPagina();
    }
}
