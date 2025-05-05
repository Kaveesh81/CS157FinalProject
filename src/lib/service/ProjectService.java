package lib.service;

import java.sql.SQLException;
import java.util.List;
import lib.dao.ProjectDAO;
import lib.model.Project;

/**
 * Service class for Project-related business logic
 */
public class ProjectService {
    private ProjectDAO projectDAO;
    
    public ProjectService() {
        this.projectDAO = new ProjectDAO();
    }
    
    /**
     * Get all projects
     * 
     * @return List of all projects
     * @throws ServiceException if an error occurs
     */
    public List<Project> getAllProjects() throws ServiceException {
        try {
            return projectDAO.getAllProjects();
        } catch (SQLException e) {
            throw new ServiceException("Error retrieving projects", e);
        }
    }
    
    /**
     * Get a project by ID
     * 
     * @param projectId the ID of the project to retrieve
     * @return the Project object, or null if not found
     * @throws ServiceException if an error occurs
     */
    public Project getProjectById(int projectId) throws ServiceException {
        try {
            return projectDAO.getProjectById(projectId);
        } catch (SQLException e) {
            throw new ServiceException("Error retrieving project with ID: " + projectId, e);
        }
    }
    
    /**
     * Get projects by semester
     * 
     * @param semesterId the ID of the semester
     * @return List of projects for the specified semester
     * @throws ServiceException if an error occurs
     */
    public List<Project> getProjectsBySemester(int semesterId) throws ServiceException {
        try {
            return projectDAO.getProjectsBySemester(semesterId);
        } catch (SQLException e) {
            throw new ServiceException("Error retrieving projects for semester: " + semesterId, e);
        }
    }
    
    /**
     * Get projects by lead
     * 
     * @param leadId the ID of the project lead
     * @return List of projects led by the specified member
     * @throws ServiceException if an error occurs
     */
    public List<Project> getProjectsByLead(int leadId) throws ServiceException {
        try {
            return projectDAO.getProjectsByLead(leadId);
        } catch (SQLException e) {
            throw new ServiceException("Error retrieving projects for lead: " + leadId, e);
        }
    }
    
    /**
     * Create a new project
     * 
     * @param project the Project object to create
     * @return the ID of the newly created project
     * @throws ServiceException if an error occurs
     */
    public int createProject(Project project) throws ServiceException {
        try {
            return projectDAO.addProject(project);
        } catch (SQLException e) {
            throw new ServiceException("Error creating project", e);
        }
    }
    
    /**
     * Update a project
     * 
     * @param project the Project object with updated information
     * @return true if the update was successful, false otherwise
     * @throws ServiceException if an error occurs
     */
    public boolean updateProject(Project project) throws ServiceException {
        try {
            return projectDAO.updateProject(project);
        } catch (SQLException e) {
            throw new ServiceException("Error updating project", e);
        }
    }
    
    /**
     * Delete a project
     * 
     * @param projectId the ID of the project to delete
     * @return true if the deletion was successful, false otherwise
     * @throws ServiceException if an error occurs
     */
    public boolean deleteProject(int projectId) throws ServiceException {
        try {
            return projectDAO.deleteProject(projectId);
        } catch (SQLException e) {
            throw new ServiceException("Error deleting project", e);
        }
    }
    
    /**
     * Get the number of members in a project
     * 
     * @param projectId the ID of the project
     * @return the number of members in the project
     * @throws ServiceException if an error occurs
     */
    public int getProjectMemberCount(int projectId) throws ServiceException {
        try {
            return projectDAO.getProjectMemberCount(projectId);
        } catch (SQLException e) {
            throw new ServiceException("Error getting member count for project: " + projectId, e);
        }
    }
}
