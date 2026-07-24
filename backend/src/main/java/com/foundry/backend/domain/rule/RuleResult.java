package com.foundry.backend.domain.rule;

public record RuleResult(String ruleId, boolean passed, String message) {

    public static RuleResult passed(String ruleId, String message) {
        return new RuleResult(ruleId, true, message);
    }

    public static RuleResult failed(String ruleId, String message) {
        return new RuleResult(ruleId, false, message);
    }
}