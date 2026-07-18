package com.foundry.backend.domain.project;

/**
 * Lançada quando uma tentativa de criar ou modificar um Project
 * viola as invariantes da entidade.
 */
public class InvalidProjectException extends RuntimeException {

    public InvalidProjectException(String message) {
        super(message);
    }
}