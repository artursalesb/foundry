package com.foundry.backend.domain.project;

import com.foundry.backend.domain.DomainException;

public class InvalidGithubRepositoryException extends DomainException {

    public InvalidGithubRepositoryException(String message) {
        super(message);
    }
}