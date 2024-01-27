package com.dio.desafiopoo.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashSet;
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
public class Bootcamp {

    private String nome;
    private String descricao;
    private LocalDate dataInicial;
    private LocalDate dataFinal;
    private Set<Dev> inscritos;
    private Set<Conteudo> conteudos;

    public Bootcamp(String nome, String descricao, LocalDate dataInicial, LocalDate dataFinal) {
        this.nome = nome;
        this.descricao = descricao;
        this.dataInicial = dataInicial;
        this.dataFinal = dataFinal;

        this.inscritos = new LinkedHashSet<>();
        this.conteudos = new LinkedHashSet<>();
    }

    private Set<Curso> getCursos() {
        return this.conteudos
                .stream()
                .filter(Curso.class::isInstance)
                .map(Curso.class::cast)
                .collect(Collectors.toSet());
    }

    public int getCargaHoraria() {
        return getCursos().stream().mapToInt(Curso::getCargaHoraria).sum();
    }

    @Override
    public String toString() {
        return String
                .format("\n***\nBOOTCAMP:\n\tNome: %s\n\tDescrição: %s\n\tInício: %s\n\tTérmino: %s\n\tNº de Inscritos: %d\n\tCarga Horária: %d horas",
                        nome,
                        descricao,
                        dataInicial.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                        dataFinal.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                        inscritos.size(),
                        getCargaHoraria());
    }
}
