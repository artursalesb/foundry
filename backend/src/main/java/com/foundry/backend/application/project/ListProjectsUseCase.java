package com.foundry.backend.application.project;

import com.foundry.backend.domain.project.Project;
import com.foundry.backend.domain.project.ProjectRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListProjectsUseCase {

    private final ProjectRepository projectRepository;

    public ListProjectsUseCase(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public List<Project> execute() {
        return projectRepository.findAll();
    }
}