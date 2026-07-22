package com.foundry.backend.interfaces.web.project;

import com.foundry.backend.domain.project.Project;

import java.time.Instant;
import java.util.UUID;

public record ProjectResponse(UUID id, String name, String description,
                               String githubRepository, Instant createdAt) {

    public static ProjectResponse from(Project project) {
        return new ProjectResponse(
                project.getId(),
                project.getName(),
                project.getDescription(),
                project.getGithubRepository().fullName(),
                project.getCreatedAt()
        );
    }
}