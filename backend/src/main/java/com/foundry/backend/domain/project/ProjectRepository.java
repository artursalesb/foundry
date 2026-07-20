package com.foundry.backend.domain.project;

import java.util.Optional;
import java.util.UUID;

/**
 * Porta de saída para persistência de Project.
 * A camada de domínio/aplicação depende apenas desta interface;
 * a implementação concreta vive em infrastructure.
 */
public interface ProjectRepository {

    Project save(Project project);

    Optional<Project> findById(UUID id);
}