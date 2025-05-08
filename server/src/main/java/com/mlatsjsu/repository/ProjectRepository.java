package com.mlatsjsu.repository;

import com.mlatsjsu.model.Project;
import java.util.List;
import java.util.Optional;

public interface ProjectRepository {
    List<Project> findAll();

    List<Project> findBySemesterId(Long semesterId);

    List<Project> findByActive(boolean active);

    Optional<Project> findById(Long id);

    Project save(Project project);

    int update(Project project);

    int deleteById(Long id);
}