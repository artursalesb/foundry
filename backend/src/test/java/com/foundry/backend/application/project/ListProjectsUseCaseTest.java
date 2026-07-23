package com.foundry.backend.application.project;

import com.foundry.backend.infrastructure.persistence.InMemoryProjectRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ListProjectsUseCaseTest {

    private InMemoryProjectRepository repository;
    private ListProjectsUseCase useCase;

    @BeforeEach
    void setUp() {
        repository = new InMemoryProjectRepository();
        useCase = new ListProjectsUseCase(repository);
    }

    @Test
    void shouldReturnEmptyListWhenNoProjectsExist() {
        assertThat(useCase.execute()).isEmpty();
    }

    @Test
    void shouldReturnAllPersistedProjects() {
        new CreateProjectUseCase(repository).execute(
                new CreateProjectCommand("Foundry", "desc", "foundry-org/foundry"));
        new CreateProjectUseCase(repository).execute(
                new CreateProjectCommand("Outro Projeto", null, "outro-org/outro"));

        assertThat(useCase.execute()).hasSize(2);
    }
}