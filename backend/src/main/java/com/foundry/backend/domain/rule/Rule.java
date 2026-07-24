package com.foundry.backend.domain.rule;

import java.nio.file.Path;

/**
 * Representa uma verificação objetiva sobre a estrutura de um repositório.
 * Opera sobre um caminho de sistema de arquivos já disponível localmente —
 * a forma como esse conteúdo chegou até aqui (clone, API remota, etc.) é
 * responsabilidade de outra camada. Ver ADR-0006.
 */
public interface Rule {

    String id();

    RuleResult evaluate(Path repositoryRoot);
}