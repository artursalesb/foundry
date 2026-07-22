package com.foundry.backend.domain.project;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Representa a referência a um repositório GitHub (owner/name).
 * Nesta etapa, valida apenas o formato — não confirma que o repositório
 * existe de fato. Ver ADR-0004 para o motivo dessa decisão.
 */
public record GithubRepository(String owner, String name) {

    private static final Pattern OWNER_REPO_PATTERN = Pattern.compile("^([\\w.-]+)/([\\w.-]+)$");

    public static GithubRepository fromUrl(String rawValue) {
        if (rawValue == null || rawValue.isBlank()) {
            throw new InvalidGithubRepositoryException("A referência ao repositório GitHub não pode ser vazia.");
        }

        String normalized = normalize(rawValue.trim());
        Matcher matcher = OWNER_REPO_PATTERN.matcher(normalized);

        if (!matcher.matches()) {
            throw new InvalidGithubRepositoryException(
                    "Formato de repositório inválido: '%s'. Use 'owner/repo' ou a URL completa do GitHub."
                            .formatted(rawValue));
        }

        return new GithubRepository(matcher.group(1), matcher.group(2));
    }

    private static String normalize(String value) {
        String withoutProtocol = value.replaceFirst("^https?://", "");
        String withoutHost = withoutProtocol.replaceFirst("^(www\\.)?github\\.com/", "");
        String withoutGitSuffix = withoutHost.replaceFirst("\\.git$", "");
        return withoutGitSuffix.replaceFirst("/$", "");
    }

    public String fullName() {
        return owner + "/" + name;
    }
}