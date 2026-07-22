package com.foundry.backend.domain;

/**
 * Classe base para exceções que representam violação de regras de negócio.
 * Toda subclasse é traduzida para HTTP 400 pela camada de interface.
 */
public abstract class DomainException extends RuntimeException {

    protected DomainException(String message) {
        super(message);
    }
}