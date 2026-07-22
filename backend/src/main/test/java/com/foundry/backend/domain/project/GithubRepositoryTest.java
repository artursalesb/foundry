package com.foundry.backend.domain.project;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class GithubRepositoryTest {

    @ParameterizedTest
    @ValueSource(strings = {
            "foundry-org/foundry",
            "https://github.com/foundry-org/foundry",
            "https://github.com/foundry-org/foundry.git",
            "github.com/foundry-org/foundry"
    })
    void shouldParseValidRepositoryFormats(String input) {
        GithubRepository repository = GithubRepository.fromUrl(input);

        assertThat(repository.owner()).isEqualTo("foundry-org");
        assertThat(repository.name()).isEqualTo("foundry");
        assertThat(repository.fullName()).isEqualTo("foundry-org/foundry");
    }

    @Test
    void shouldRejectBlankValue() {
        assertThatThrownBy(() -> GithubRepository.fromUrl(" "))
                .isInstanceOf(InvalidGithubRepositoryException.class);
    }

    @Test
    void shouldRejectValueWithoutOwnerAndRepo() {
        assertThatThrownBy(() -> GithubRepository.fromUrl("foundry"))
                .isInstanceOf(InvalidGithubRepositoryException.class);
    }
}