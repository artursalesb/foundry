package com.foundry.backend.domain.project;

import java.time.Instant;
import java.util.Objects;
import java.util.UUID;

public class Project {

    private static final int NAME_MAX_LENGTH = 100;
    private static final int DESCRIPTION_MAX_LENGTH = 500;

    private final UUID id;
    private final String name;
    private final String description;
    private final GithubRepository githubRepository;
    private final Instant createdAt;

    private Project(UUID id, String name, String description,
                     GithubRepository githubRepository, Instant createdAt) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.githubRepository = githubRepository;
        this.createdAt = createdAt;
    }

    public static Project create(String name, String description, String githubRepositoryUrl) {
        validateName(name);
        validateDescription(description);
        GithubRepository githubRepository = GithubRepository.fromUrl(githubRepositoryUrl);

        return new Project(UUID.randomUUID(), name.trim(), description, githubRepository, Instant.now());
    }

    private static void validateName(String name) {
        if (name == null || name.isBlank()) {
            throw new InvalidProjectException("O nome do projeto não pode ser vazio.");
        }
        if (name.trim().length() > NAME_MAX_LENGTH) {
            throw new InvalidProjectException(
                    "O nome do projeto não pode ter mais de %d caracteres.".formatted(NAME_MAX_LENGTH));
        }
    }

    private static void validateDescription(String description) {
        if (description != null && description.length() > DESCRIPTION_MAX_LENGTH) {
            throw new InvalidProjectException(
                    "A descrição não pode ter mais de %d caracteres.".formatted(DESCRIPTION_MAX_LENGTH));
        }
    }

    public UUID getId() { return id; }
    public String getName() { return name; }
    public String getDescription() { return description; }
    public GithubRepository getGithubRepository() { return githubRepository; }
    public Instant getCreatedAt() { return createdAt; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Project other)) return false;
        return id.equals(other.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}