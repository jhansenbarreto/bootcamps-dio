package com.academia.dio.api.dto;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Jhansen Barreto
 */
@Getter
@Setter
public class AvaliacaoFisicaOutput {

    private Long id;
    private LocalDateTime dataAvaliacao;
    private Double peso;
    private Double altura;
    private String imc;
    private AlunoOutput aluno;
}
