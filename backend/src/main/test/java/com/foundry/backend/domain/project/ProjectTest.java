package com.foundry.backend.domain.project;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ProjectTest {

    private static final String VALID_REPOSITORY = "foundry-org/foundry";

    @Test
    void shouldCreateProjectWithValidNameAndDescription() {
        Project project = Project.create("Foundry", "Plataforma de evolução de engenharia", VALID_REPOSITORY);

        assertThat(project.getId()).isNotNull();
        assertThat(project.getName()).isEqualTo("Foundry");
        assertThat(project.getDescription()).isEqualTo("Plataforma de evolução de engenharia");
        assertThat(project.getGithubRepository().fullName()).isEqualTo(VALID_REPOSITORY);
        assertThat(project.getCreatedAt()).isNotNull();
    }

    @Test
    void shouldTrimNameWhenCreatingProject() {
        Project project = Project.create("  Foundry  ", null, VALID_REPOSITORY);

        assertThat(project.getName()).isEqualTo("Foundry");
    }

    @Test
    void shouldAllowNullDescription() {
        Project project = Project.create("Foundry", null, VALID_REPOSITORY);

        assertThat(project.getDescription()).isNull();
    }

    @Test
    void shouldRejectBlankName() {
        assertThatThrownBy(() -> Project.create("   ", "desc", VALID_REPOSITORY))
                .isInstanceOf(InvalidProjectException.class)
                .hasMessageContaining("nome");
    }

    @Test
    void shouldRejectNameLongerThan100Characters() {
        String tooLongName = "a".repeat(101);

        assertThatThrownBy(() -> Project.create(tooLongName, null, VALID_REPOSITORY))
                .isInstanceOf(InvalidProjectException.class)
                .hasMessageContaining("100");
    }

    @Test
    void shouldRejectDescriptionLongerThan500Characters() {
        String tooLongDescription = "a".repeat(501);

        assertThatThrownBy(() -> Project.create("Foundry", tooLongDescription, VALID_REPOSITORY))
                .isInstanceOf(InvalidProjectException.class)
                .hasMessageContaining("500");
    }

    @Test
    void shouldRejectInvalidGithubRepository() {
        assertThatThrownBy(() -> Project.create("Foundry", "desc", "not-a-valid-repo"))
                .isInstanceOf(InvalidGithubRepositoryException.class);
    }
}