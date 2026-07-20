package com.foundry.backend.application.project;

import com.foundry.backend.domain.project.InvalidProjectException;
import com.foundry.backend.domain.project.Project;
import com.foundry.backend.infrastructure.persistence.InMemoryProjectRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class CreateProjectUseCaseTest {

    private CreateProjectUseCase useCase;

    @BeforeEach
    void setUp() {
        useCase = new CreateProjectUseCase(new InMemoryProjectRepository());
    }

    @Test
    void shouldCreateAndPersistProject() {
        CreateProjectCommand command = new CreateProjectCommand("Foundry", "Backlog de evolução");

        Project created = useCase.execute(command);

        assertThat(created.getId()).isNotNull();
        assertThat(created.getName()).isEqualTo("Foundry");
    }

    @Test
    void shouldPropagateDomainValidationErrors() {
        CreateProjectCommand invalidCommand = new CreateProjectCommand("", "desc");

        assertThatThrownBy(() -> useCase.execute(invalidCommand))
                .isInstanceOf(InvalidProjectException.class);
    }
}