package com.mlatsjsu.service;

import com.mlatsjsu.model.Project;
import java.util.List;
import java.util.Optional;

public interface ProjectService {
    List<Project> findAll();

    List<Project> findBySemesterId(Long semesterId);

    List<Project> findByActive(boolean active);

    Optional<Project> findById(Long id);

    Project create(Project project);

    Project update(Long id, Project project);

    void delete(Long id);
}