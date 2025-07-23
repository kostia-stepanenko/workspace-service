package com.github.kostia.workspace_service.project;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    // CREATE
    @PostMapping
    public ResponseEntity<Project> createProject(@RequestBody Project project) {
        Project saved = projectRepo.save(project);
        return ResponseEntity.ok(saved);
    }

    // PUT A.K.A UPDATE
    @PutMapping("/update/{id}")
    public ResponseEntity<Project> updateProject(
            @PathVariable Integer id,
            @RequestBody Project updatedProject) {

        return projectRepo.findById(id)
                .map(existing -> {
                    updatedProject.setId(id); // ensure the correct ID is set
                    Project saved = projectRepo.save(updatedProject);
                    return ResponseEntity.ok(saved);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProject(@PathVariable Integer id) {
        if (projectRepo.existsById(id)) {
            projectRepo.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}