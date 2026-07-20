package com.foundry.backend.interfaces.web.project;

import com.foundry.backend.application.project.CreateProjectCommand;
import com.foundry.backend.application.project.CreateProjectUseCase;
import com.foundry.backend.domain.project.InvalidProjectException;
import com.foundry.backend.domain.project.Project;
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

@WebMvcTest(ProjectController.class)
class ProjectControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CreateProjectUseCase createProjectUseCase;

    @Test
    void shouldReturn201WhenProjectIsCreatedSuccessfully() throws Exception {
        Project project = Project.create("Foundry", "Backlog de evolução");
        when(createProjectUseCase.execute(any(CreateProjectCommand.class))).thenReturn(project);

        mockMvc.perform(post("/api/v1/projects")
                        .contentType("application/json")
                        .content("""
                                {"name": "Foundry", "description": "Backlog de evolução"}
                                """))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Foundry"));
    }

    @Test
    void shouldReturn400WhenDomainValidationFails() throws Exception {
        when(createProjectUseCase.execute(any(CreateProjectCommand.class)))
                .thenThrow(new InvalidProjectException("O nome do projeto não pode ser vazio."));

        mockMvc.perform(post("/api/v1/projects")
                        .contentType("application/json")
                        .content("""
                                {"name": "", "description": "desc"}
                                """))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("O nome do projeto não pode ser vazio."));
    }
}