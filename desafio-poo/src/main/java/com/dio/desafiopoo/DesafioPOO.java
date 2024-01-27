package com.dio.desafiopoo;

import com.dio.desafiopoo.model.Bootcamp;
import com.dio.desafiopoo.model.Curso;
import com.dio.desafiopoo.model.Dev;
import com.dio.desafiopoo.model.Mentoria;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 *
 * @author Jhansen Barreto
 */
public class DesafioPOO {

    public static void main(String[] args) {
        System.out.println("MANIPULANDO OBJETO DESENVOLVEDOR 1: ");
        dev1();
        
        System.out.println("\n\nMANIPULANDO OBJETO DESENVOLVEDOR 2: ");
        dev2();
    }

    public static Bootcamp getBootcamp1() {
        var bootcamp = new Bootcamp(
                "Banco PAN",
                "Java Developer",
                LocalDate.now(),
                LocalDate.now().plusDays(30));

        var set = bootcamp.getConteudos();

        set.add(new Curso("Introdução", "Apresentação inicial", 3));
        set.add(new Curso("Iniciando no Java", "Aula Básica", 5));
        set.add(new Curso("Estruturas de Dados", "Lista, Pilha, Fila e Árvore", 7));

        set.add(new Mentoria("TDD", "Test Driven Development", LocalDateTime.now()));
        set.add(new Mentoria("DDD", "Domain Driven Design", LocalDateTime.now()));

        return bootcamp;
    }

    public static Bootcamp getBootcamp2() {
        var bootcamp = new Bootcamp(
                "Spring Framework",
                "Nível especialista",
                LocalDate.now(),
                LocalDate.now().plusDays(60));

        var set = bootcamp.getConteudos();

        set.add(new Curso("Conhecendo o Spring", "Aula Básica", 8));
        set.add(new Curso("JPA", "Persistindo Dados", 15));

        set.add(new Mentoria("Tratamento de Erros", "RFC 7807 (Problem Details for HTTP APIs)", LocalDateTime.now()));

        return bootcamp;
    }

    public static void dev1() {
        //INSTANCIANDO BOOTCAMPS
        var bootcamp1 = getBootcamp1();
        var bootcamp2 = getBootcamp2();

        //INSTANCIANDO DEV E MATRICULANDO NOS BOOTCAMPS
        var dev = new Dev("Steve Jobs");
        dev.inscreverBootcamp(bootcamp1);
        dev.inscreverBootcamp(bootcamp2);

        //IMPRIMINDO DEV
        System.out.println("\nINÍCIO\n************************************************************************\n");
        System.out.println(dev);
        System.out.println("------------------------------------------------------------------------");

        //IMPRIMINDO BOOTCAMPS E CONTEÚDOS
        dev.imprimirBootcampsConteudos();

        //PROGREDIR NO BOOTCAMP 1 (concluir todos os conteúdos)
        dev.progredir(bootcamp1);
        dev.progredir(bootcamp1);
        dev.progredir(bootcamp1);
        dev.progredir(bootcamp1);
        dev.progredir(bootcamp1);

        //PROGREDIR NO BOOTCAMP 2 (concluir só os cursos)
        dev.progredir(bootcamp2);
        dev.progredir(bootcamp2);

        System.out.println("------------------------------------------------------------------------");

        //IMPRIMIR PROGRESSO (OBS: Progresso só considera Curso. Mentoria não tem carga horária.)
        System.out.println("\n***\nMEU PROGRESSO:\n");
        dev.verProgresso(bootcamp1);
        dev.verProgresso(bootcamp2);

        //IMPRIMIR XP TOTAL (OBS: XP é calculado com todos os conteúdos. Curso e Mentoria.)
        System.out.println("\n***\nMEU XP TOTAL: " + dev.calcularTotalXP());

        System.out.println("------------------------------------------------------------------------");

        //IMPRIMIR CONTEÚDO QUE NÃO FOI CONCLUÍDO
        System.out.println("\n***\nCONTEÚDO RESTANTE:");
        System.out.println(dev.getConteudosInscritos().keySet());

        System.out.println("\n************************************************************************\nFIM");
    }

    public static void dev2() {
        //INSTANCIANDO BOOTCAMPS
        var bootcamp1 = getBootcamp1();
        var bootcamp2 = getBootcamp2();

        //INSTANCIANDO DEV E MATRICULANDO NOS BOOTCAMPS
        var dev = new Dev("Bill Gates");
        dev.inscreverBootcamp(bootcamp1);
        dev.inscreverBootcamp(bootcamp2);

        //IMPRIMINDO DEV
        System.out.println("\nINÍCIO\n************************************************************************\n");
        System.out.println(dev);
        System.out.println("------------------------------------------------------------------------");

        //IMPRIMINDO BOOTCAMPS E CONTEÚDOS
        dev.imprimirBootcampsConteudos();

        //PROGREDIR NO BOOTCAMP 1 (concluir 2 cursos)
        dev.progredir(bootcamp1);
        dev.progredir(bootcamp1);

        //PROGREDIR NO BOOTCAMP 2 (concluir 1 curso)
        dev.progredir(bootcamp2);

        System.out.println("------------------------------------------------------------------------");

        //IMPRIMIR PROGRESSO (OBS: Progresso só considera Curso. Mentoria não tem carga horária.)
        System.out.println("\n***\nMEU PROGRESSO:\n");
        dev.verProgresso(bootcamp1);
        dev.verProgresso(bootcamp2);

        //IMPRIMIR XP TOTAL (OBS: XP é calculado com todos os conteúdos. Curso e Mentoria.)
        System.out.println("\n***\nMEU XP TOTAL: " + dev.calcularTotalXP());

        System.out.println("------------------------------------------------------------------------");

        //IMPRIMIR CONTEÚDO QUE NÃO FOI CONCLUÍDO
        System.out.println("\n***\nCONTEÚDO RESTANTE:");
        System.out.println(dev.getConteudosInscritos().keySet());

        System.out.println("\n************************************************************************\nFIM");
    }
}
