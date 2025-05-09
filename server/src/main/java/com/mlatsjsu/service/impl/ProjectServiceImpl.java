package com.mlatsjsu.service.impl;

import com.mlatsjsu.model.Project;
import com.mlatsjsu.model.ProjectProposalDTO;
import com.mlatsjsu.model.Membership;
import com.mlatsjsu.repository.ProjectRepository;
import com.mlatsjsu.repository.MembershipRepository;
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

    @Autowired
    private MembershipRepository membershipRepository;

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
    public Project proposeProject(ProjectProposalDTO proposal) {
        // Create project with is_approved = false
        Project project = new Project();
        project.setTitle(proposal.getTitle());
        project.setDescription(proposal.getDescription());
        project.setTopic(proposal.getTopic());
        project.setSemesterId(proposal.getSemesterId());
        project.setGithubLink(proposal.getGithubLink());
        project.setSpotsAvailable(proposal.getSpotsAvailable());
        project.setApproved(false);

        Project savedProject = projectRepository.save(project);

        // Create pending lead membership
        Membership leadMembership = new Membership();
        leadMembership.setUserId(proposal.getProposerId());
        leadMembership.setProjectId(savedProject.getProjectId());
        leadMembership.setRole(Membership.Role.LEAD);
        leadMembership.setStatus(Membership.Status.PENDING);
        membershipRepository.save(leadMembership);

        return savedProject;
    }

    @Override
    @Transactional
    public Project approveProject(Long projectId) {
        // Get project
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new RuntimeException("Project not found"));

        // Update project approval status
        project.setApproved(true);
        projectRepository.update(project);

        // Find and approve lead membership
        List<Membership> memberships = membershipRepository.findByProjectId(projectId);
        memberships.stream()
                .filter(m -> m.getRole() == Membership.Role.LEAD)
                .findFirst()
                .ifPresent(lead -> {
                    lead.setStatus(Membership.Status.APPROVED);
                    membershipRepository.update(lead);
                });

        return project;
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