package com.foundry.backend.application.project;

/**
 * Representa a intenção de criar um novo projeto,
 * antes de qualquer validação de domínio ser aplicada.
 */
public record CreateProjectCommand(String name, String description) {
}