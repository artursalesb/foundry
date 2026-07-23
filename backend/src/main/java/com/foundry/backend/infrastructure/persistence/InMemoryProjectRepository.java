package com.foundry.backend.infrastructure.persistence;

import com.foundry.backend.domain.project.Project;
import com.foundry.backend.domain.project.ProjectRepository;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.List;

/**
 * Implementação em memória de ProjectRepository, usada enquanto a
 * persistência definitiva (PostgreSQL) não é implementada.
 * Ver ADR-0003 e ADR-0004 (pendente) para contexto dessa decisão.
 */
@Repository
public class InMemoryProjectRepository implements ProjectRepository {

    private final Map<UUID, Project> storage = new ConcurrentHashMap<>();

    @Override
    public Project save(Project project) {
        storage.put(project.getId(), project);
        return project;
    }

    @Override
    public Optional<Project> findById(UUID id) {
        return Optional.ofNullable(storage.get(id));
    }
    @Override
    public List<Project> findAll() {
        return List.copyOf(storage.values());
    }
}