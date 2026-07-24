package com.foundry.backend.domain.rule;

import java.nio.file.Files;
import java.nio.file.Path;

public class HasReadmeRule implements Rule {

    private static final String RULE_ID = "has-readme";

    @Override
    public String id() {
        return RULE_ID;
    }

    @Override
    public RuleResult evaluate(Path repositoryRoot) {
        boolean exists = Files.exists(repositoryRoot.resolve("README.md"));

        if (exists) {
            return RuleResult.passed(RULE_ID, "O projeto possui um arquivo README.md.");
        }

        return RuleResult.failed(RULE_ID,
                "O projeto não possui um arquivo README.md na raiz do repositório.");
    }
}