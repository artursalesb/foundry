package com.foundry.backend.application.project;

import com.foundry.backend.domain.project.InvalidGithubRepositoryException;
import com.foundry.backend.domain.project.InvalidProjectException;
import com.foundry.backend.domain.project.Project;
import com.foundry.backend.infrastructure.persistence.InMemoryProjectRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class CreateProjectUseCaseTest {

    private static final String VALID_REPOSITORY = "foundry-org/foundry";

    private CreateProjectUseCase useCase;

    @BeforeEach
    void setUp() {
        useCase = new CreateProjectUseCase(new InMemoryProjectRepository());
    }

    @Test
    void shouldCreateAndPersistProject() {
        CreateProjectCommand command =
                new CreateProjectCommand("Foundry", "Backlog de evolução", VALID_REPOSITORY);

        Project created = useCase.execute(command);

        assertThat(created.getId()).isNotNull();
        assertThat(created.getName()).isEqualTo("Foundry");
        assertThat(created.getGithubRepository().fullName()).isEqualTo(VALID_REPOSITORY);
    }

    @Test
    void shouldPropagateProjectValidationErrors() {
        CreateProjectCommand invalidCommand =
                new CreateProjectCommand("", "desc", VALID_REPOSITORY);

        assertThatThrownBy(() -> useCase.execute(invalidCommand))
                .isInstanceOf(InvalidProjectException.class);
    }

    @Test
    void shouldPropagateGithubRepositoryValidationErrors() {
        CreateProjectCommand invalidCommand =
                new CreateProjectCommand("Foundry", "desc", "not-a-valid-repo");

        assertThatThrownBy(() -> useCase.execute(invalidCommand))
                .isInstanceOf(InvalidGithubRepositoryException.class);
    }
}