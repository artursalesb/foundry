package com.foundry.backend.application.project;

public record CreateProjectCommand(String name, String description, String githubRepositoryUrl) {
}