package com.academia.dio.domain.exception;

/**
 *
 * @author Jhansen Barreto
 */
public class EntidadeNaoEncontradaException extends RuntimeException {

    public EntidadeNaoEncontradaException() {
        super();
    }

    public EntidadeNaoEncontradaException(String msg) {
        super(msg);
    }
}
