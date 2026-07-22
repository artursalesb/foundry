package com.foundry.backend.domain.project;

import com.foundry.backend.domain.DomainException;

public class InvalidProjectException extends DomainException {

    public InvalidProjectException(String message) {
        super(message);
    }
}