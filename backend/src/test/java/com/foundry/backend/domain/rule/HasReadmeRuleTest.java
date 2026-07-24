package com.foundry.backend.domain.rule;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.assertj.core.api.Assertions.assertThat;

class HasReadmeRuleTest {

    private final HasReadmeRule rule = new HasReadmeRule();

    @Test
    void shouldPassWhenReadmeExists(@TempDir Path repositoryRoot) throws IOException {
        Files.createFile(repositoryRoot.resolve("README.md"));

        RuleResult result = rule.evaluate(repositoryRoot);

        assertThat(result.passed()).isTrue();
        assertThat(result.ruleId()).isEqualTo("has-readme");
    }

    @Test
    void shouldFailWhenReadmeIsMissing(@TempDir Path repositoryRoot) {
        RuleResult result = rule.evaluate(repositoryRoot);

        assertThat(result.passed()).isFalse();
        assertThat(result.message()).contains("não possui");
    }
}