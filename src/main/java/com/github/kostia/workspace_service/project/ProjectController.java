package com.github.kostia.workspace_service.project;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {
    private final ProjectRepo projectRepo;

    public ProjectController(ProjectRepo projectRepo) {
        this.projectRepo = projectRepo;
    }

    // READ by ID
    @GetMapping("/{id}")
    public Optional<Project> getProjectById(@PathVariable Integer id) {
        return projectRepo.findById(id);
    }

    // READ all
    @GetMapping
    public ResponseEntity<?> getProjects() {
        return ResponseEntity.ok(projectRepo.findAll());
    }
}