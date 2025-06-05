package com.github.kostia.workspace_service.ProjectTests;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.kostia.workspace_service.project.Project;
import com.github.kostia.workspace_service.project.ProjectController;
import com.github.kostia.workspace_service.project.ProjectRepo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProjectController.class)
class ProjectControllerTest {

    @Autowired
    private ProjectRepo projectRepo;

    @TestConfiguration
    static class MockConfig {
        @Bean
        @Primary
        public ProjectRepo mockProjectRepo() {
            return Mockito.mock(ProjectRepo.class);
        }
    }


    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("GET /api/projects should return all projects")
    void getAllProjects() throws Exception {
        Project project = new Project(
                1, 12, 345,
                "display name 21", "short name 21",
                "description21", "ACTIVE", "MISSED",
                "stores21", "groups21",
                Boolean.TRUE, Boolean.FALSE, 1234L
        );

        Mockito.when(projectRepo.findAll()).thenReturn(List.of(project));

        mockMvc.perform(get("/api/projects"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].compartmentId").value(12))
                .andExpect(jsonPath("$[0].tenantId").value(345))
                .andExpect(jsonPath("$[0].displayName").value("display name 21"))
                .andExpect(jsonPath("$[0].shortName").value("short name 21"))
                .andExpect(jsonPath("$[0].description").value("description21"))
                .andExpect(jsonPath("$[0].lifecycleState").value("ACTIVE"))
                .andExpect(jsonPath("$[0].configStatus").value("MISSED"))
                .andExpect(jsonPath("$[0].stores").value("stores21"))
                .andExpect(jsonPath("$[0].groups").value("groups21"))
                .andExpect(jsonPath("$[0].isProtected").value(true))
                .andExpect(jsonPath("$[0].vetted").value(false))
                .andExpect(jsonPath("$[0].version").value(1234));
    }

    @Test
    @DisplayName("GET /api/projects/1 should return project by ID")
    void getProjectById() throws Exception {
        Project project = new Project(
                1, 12, 345,
                "display name 21", "short name 21",
                "description21", "ACTIVE", "MISSED",
                "stores21", "groups21",
                Boolean.TRUE, Boolean.FALSE, 1234L
        );

        Mockito.when(projectRepo.findById(1)).thenReturn(Optional.of(project));

        mockMvc.perform(get("/api/projects/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.compartmentId").value(12))
                .andExpect(jsonPath("$.tenantId").value(345))
                .andExpect(jsonPath("$.displayName").value("display name 21"))
                .andExpect(jsonPath("$.shortName").value("short name 21"))
                .andExpect(jsonPath("$.description").value("description21"))
                .andExpect(jsonPath("$.lifecycleState").value("ACTIVE"))
                .andExpect(jsonPath("$.configStatus").value("MISSED"))
                .andExpect(jsonPath("$.stores").value("stores21"))
                .andExpect(jsonPath("$.groups").value("groups21"))
                .andExpect(jsonPath("$.isProtected").value(true))
                .andExpect(jsonPath("$.vetted").value(false))
                .andExpect(jsonPath("$.version").value(1234));
    }
}
