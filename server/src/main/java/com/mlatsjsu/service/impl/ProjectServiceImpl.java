package com.mlatsjsu.service.impl;

import com.mlatsjsu.model.Project;
import com.mlatsjsu.repository.ProjectRepository;
import com.mlatsjsu.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    @Override
    public List<Project> findAll() {
        return projectRepository.findAll();
    }

    @Override
    public List<Project> findBySemesterId(Long semesterId) {
        return projectRepository.findBySemesterId(semesterId);
    }

    @Override
    public List<Project> findByActive(boolean active) {
        return projectRepository.findByActive(active);
    }

    @Override
    public Optional<Project> findById(Long id) {
        return projectRepository.findById(id);
    }

    @Override
    @Transactional
    public Project create(Project project) {
        return projectRepository.save(project);
    }

    @Override
    @Transactional
    public Project update(Long id, Project project) {
        project.setProjectId(id);
        projectRepository.update(project);
        return projectRepository.findById(id).orElseThrow();
    }

    @Override
    @Transactional
    public void delete(Long id) {
        projectRepository.deleteById(id);
    }
}