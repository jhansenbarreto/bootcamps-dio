package com.academia.dio.api.exceptionhandler;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.time.OffsetDateTime;
import java.util.List;
import lombok.Builder;
import lombok.Getter;

/**
 *
 * @author Jhansen Barreto
 */
@Getter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Erro {

    private final Integer status;
    private final String title;
    private final String detail;
    private final OffsetDateTime timestamp;
    private final List<ErroOrigem> errors;

    @Builder
    @Getter
    public static class ErroOrigem {

        private final String name;
        private final String message;
    }
}
