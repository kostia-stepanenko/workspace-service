package com.github.kostia.workspace_service.project;

import org.springframework.data.repository.CrudRepository;

public interface ProjectRepo extends CrudRepository<Project, Integer> {
    /*
   Optional<Project> findByCompartmentId(int compartment_id);
   */
}