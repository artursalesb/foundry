package com.foundry.backend.application.project;

import com.foundry.backend.domain.project.Project;
import com.foundry.backend.domain.project.ProjectRepository;
import org.springframework.stereotype.Service;

@Service
public class CreateProjectUseCase {

    private final ProjectRepository projectRepository;

    public CreateProjectUseCase(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public Project execute(CreateProjectCommand command) {
        Project project = Project.create(command.name(), command.description());

        return projectRepository.save(project);
    }
}