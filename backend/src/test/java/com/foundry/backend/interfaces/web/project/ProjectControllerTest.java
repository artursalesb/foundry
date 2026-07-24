package com.foundry.backend.interfaces.web.project;

import com.foundry.backend.application.project.CreateProjectCommand;
import com.foundry.backend.application.project.CreateProjectUseCase;
import com.foundry.backend.application.project.ListProjectsUseCase;
import com.foundry.backend.domain.project.InvalidProjectException;
import com.foundry.backend.domain.project.Project;
import com.foundry.backend.interfaces.web.GlobalExceptionHandler;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@WebMvcTest(ProjectController.class)
@org.springframework.context.annotation.Import(GlobalExceptionHandler.class)
 

class ProjectControllerTest {
    @MockBean
    private ListProjectsUseCase listProjectsUseCase;

    private static final String VALID_REPOSITORY = "foundry-org/foundry";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CreateProjectUseCase createProjectUseCase;

    @Test
    void shouldReturn201WhenProjectIsCreatedSuccessfully() throws Exception {
        Project project = Project.create("Foundry", "Backlog de evolução", VALID_REPOSITORY);
        when(createProjectUseCase.execute(any(CreateProjectCommand.class))).thenReturn(project);

        mockMvc.perform(post("/api/v1/projects")
                        .contentType("application/json")
                        .content("""
                                {"name": "Foundry", "description": "Backlog de evolução", "githubRepositoryUrl": "foundry-org/foundry"}
                                """))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Foundry"))
                .andExpect(jsonPath("$.githubRepository").value("foundry-org/foundry"));
    }

    @Test
    void shouldReturn400WhenDomainValidationFails() throws Exception {
        when(createProjectUseCase.execute(any(CreateProjectCommand.class)))
                .thenThrow(new InvalidProjectException("O nome do projeto não pode ser vazio."));

        mockMvc.perform(post("/api/v1/projects")
                        .contentType("application/json")
                        .content("""
                                {"name": "", "description": "desc", "githubRepositoryUrl": "foundry-org/foundry"}
                                """))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("O nome do projeto não pode ser vazio."));
    }
    @Test
   
    void shouldReturnAllProjectsOnList() throws Exception {
        Project project = Project.create("Foundry", "desc", VALID_REPOSITORY);
        when(listProjectsUseCase.execute()).thenReturn(java.util.List.of(project));

        mockMvc.perform(get("/api/v1/projects"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Foundry"));
    }
}