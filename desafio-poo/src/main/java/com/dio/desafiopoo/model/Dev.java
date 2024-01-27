package com.dio.desafiopoo.model;

import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Jhansen Barreto
 */
@Getter
@Setter
public class Dev {

    private String nome;
    private Set<Bootcamp> bootcamps;
    private Map<Conteudo, Bootcamp> conteudosInscritos;
    private Map<Conteudo, Bootcamp> conteudosConcluidos;

    public Dev(String nome) {
        this.nome = nome;

        this.bootcamps = new LinkedHashSet<>();
        this.conteudosInscritos = new LinkedHashMap<>();
        this.conteudosConcluidos = new LinkedHashMap<>();
    }

    public void inscreverBootcamp(Bootcamp bootcamp) {
        bootcamp.getInscritos().add(this);
        bootcamps.add(bootcamp);

        for (var conteudo : bootcamp.getConteudos()) {
            conteudosInscritos.put(conteudo, bootcamp);
        }
    }

    private List<Conteudo> conteudoPorBootcamp(Bootcamp bootcamp) {
        return this.conteudosInscritos
                .entrySet()
                .stream()
                .filter(item -> item.getValue().equals(bootcamp))
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

    public void progredir(Bootcamp bootcamp) {
        if (bootcamps.contains(bootcamp)) {
            var conteudo = conteudoPorBootcamp(bootcamp).stream().findFirst();

            if (conteudo.isPresent()) {
                conteudosConcluidos.put(conteudo.get(), bootcamp);
                conteudosInscritos.remove(conteudo.get(), bootcamp);
            } else {
                System.out.println("ERRO! Sem mais conteúdos.");
            }
        } else {
            System.out.println("ATENÇÃO! Você não está matriculado neste Bootcamp.");
        }
    }

    public int calcularTotalXP() {
        return conteudosConcluidos.keySet().stream().mapToInt(Conteudo::getXp).sum();
    }

    public void verProgresso(Bootcamp bootcamp) {
        int cargaHorariaConcluida = cursosConcluidosPorBootcamp(bootcamp).stream().mapToInt(Curso::getCargaHoraria).sum();

        int chTotal = bootcamp.getCargaHoraria();
        double conclusao = (cargaHorariaConcluida * 100) / chTotal;

        System.out.println(
                String.format("BOOTCAMP:\n\t%s | %s%s\n\tCarga Horária Total: %d horas\n\tCarga Horária Concluída: %d horas",
                        bootcamp.getNome(),
                        String.format("%.1f", conclusao),
                        "% concluído",
                        chTotal,
                        cargaHorariaConcluida)
        );
    }

    private Set<Curso> cursosConcluidosPorBootcamp(Bootcamp bootcamp) {
        return this.conteudosConcluidos
                .entrySet()
                .stream()
                .filter(item -> item.getValue().equals(bootcamp))
                .map(Map.Entry::getKey)
                .filter(Curso.class::isInstance)
                .map(Curso.class::cast)
                .collect(Collectors.toSet());
    }

    public void imprimirBootcampsConteudos() {
        System.out.println("***\nESTOU MATRICULADO EM:");

        bootcamps.forEach(bootcamp -> {
            System.out.println(bootcamp);
            System.out.println("---\nCONTEÚDOS:" + bootcamp.getConteudos());
        });
    }

    @Override
    public String toString() {
        return String.format("PERFIL DO DEV:\n\tNome: %s\n\tNº Bootcamps Inscritos: %d", nome, bootcamps.size());
    }
}
