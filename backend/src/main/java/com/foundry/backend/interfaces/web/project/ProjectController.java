package com.foundry.backend.interfaces.web.project;

import com.foundry.backend.application.project.CreateProjectCommand;
import com.foundry.backend.application.project.CreateProjectUseCase;
import com.foundry.backend.application.project.ListProjectsUseCase;
import com.foundry.backend.domain.project.Project;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/projects")
public class ProjectController {

    private final CreateProjectUseCase createProjectUseCase;
    private final ListProjectsUseCase listProjectsUseCase;

    public ProjectController(CreateProjectUseCase createProjectUseCase,
                              ListProjectsUseCase listProjectsUseCase) {
        this.createProjectUseCase = createProjectUseCase;
        this.listProjectsUseCase = listProjectsUseCase;
    }

    @PostMapping
    public ResponseEntity<ProjectResponse> create(@RequestBody CreateProjectRequest request) {
        CreateProjectCommand command = new CreateProjectCommand(request.name(), request.description(), request.githubRepositoryUrl());

        Project created = createProjectUseCase.execute(command);

        return ResponseEntity.status(HttpStatus.CREATED).body(ProjectResponse.from(created));
    }

    @GetMapping
    public ResponseEntity<List<ProjectResponse>> list() {
        List<ProjectResponse> response = listProjectsUseCase.execute().stream()
                .map(ProjectResponse::from)
                .toList();

        return ResponseEntity.ok(response);
    }
}