package com.foundry.backend.domain.project;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ProjectTest {

    @Test
    void shouldCreateProjectWithValidNameAndDescription() {
        Project project = Project.create("Foundry", "Plataforma de evolução de engenharia");

        assertThat(project.getId()).isNotNull();
        assertThat(project.getName()).isEqualTo("Foundry");
        assertThat(project.getDescription()).isEqualTo("Plataforma de evolução de engenharia");
        assertThat(project.getCreatedAt()).isNotNull();
    }

    @Test
    void shouldTrimNameWhenCreatingProject() {
        Project project = Project.create("  Foundry  ", null);

        assertThat(project.getName()).isEqualTo("Foundry");
    }

    @Test
    void shouldAllowNullDescription() {
        Project project = Project.create("Foundry", null);

        assertThat(project.getDescription()).isNull();
    }

    @Test
    void shouldRejectBlankName() {
        assertThatThrownBy(() -> Project.create("   ", "desc"))
                .isInstanceOf(InvalidProjectException.class)
                .hasMessageContaining("nome");
    }

    @Test
    void shouldRejectNameLongerThan100Characters() {
        String tooLongName = "a".repeat(101);

        assertThatThrownBy(() -> Project.create(tooLongName, null))
                .isInstanceOf(InvalidProjectException.class)
                .hasMessageContaining("100");
    }

    @Test
    void shouldRejectDescriptionLongerThan500Characters() {
        String tooLongDescription = "a".repeat(501);

        assertThatThrownBy(() -> Project.create("Foundry", tooLongDescription))
                .isInstanceOf(InvalidProjectException.class)
                .hasMessageContaining("500");
    }
}