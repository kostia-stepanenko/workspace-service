package com.github.kostia.workspace_service.project;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ProjectRepo extends CrudRepository<Project, Integer> {

    Optional<Project> findByCompartmentId(int compartment_id);

}