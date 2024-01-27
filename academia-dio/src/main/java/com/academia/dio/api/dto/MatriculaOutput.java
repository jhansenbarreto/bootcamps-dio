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
public class MatriculaOutput {

    private Long id;
    private Boolean ativa;
    private LocalDateTime dataMatricula;
    private LocalDateTime dataDesmatricula;
    private AlunoOutput aluno;
}
