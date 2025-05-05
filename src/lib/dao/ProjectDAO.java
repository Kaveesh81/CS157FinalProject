package lib.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import lib.model.Project;
import lib.util.DBUtil;

/**
 * Data Access Object for Project entity
 * Handles database operations related to projects
 */
public class ProjectDAO {
    
    /**
     * Get all projects from the database
     * 
     * @return List of all projects
     * @throws SQLException if a database error occurs
     */
    public List<Project> getAllProjects() throws SQLException {
        List<Project> projects = new ArrayList<>();
        String sql = "SELECT p.*, m.Name as LeadName, s.Term, s.Year " +
                     "FROM Projects p " +
                     "JOIN Members m ON p.ProjectLeadID = m.MemberID " +
                     "JOIN Semesters s ON p.SemesterID = s.SemesterID " +
                     "ORDER BY p.CreatedAt DESC";
        
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            
            while (rs.next()) {
                Project project = mapResultSetToProject(rs);
                projects.add(project);
            }
        }
        
        return projects;
    }
    
    /**
     * Get a project by ID
     * 
     * @param projectId the ID of the project to retrieve
     * @return the Project object, or null if not found
     * @throws SQLException if a database error occurs
     */
    public Project getProjectById(int projectId) throws SQLException {
        String sql = "SELECT p.*, m.Name as LeadName, s.Term, s.Year " +
                     "FROM Projects p " +
                     "JOIN Members m ON p.ProjectLeadID = m.MemberID " +
                     "JOIN Semesters s ON p.SemesterID = s.SemesterID " +
                     "WHERE p.ProjectID = ?";
        
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, projectId);
            
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToProject(rs);
                }
            }
        }
        
        return null;
    }
    
    /**
     * Get projects by semester
     * 
     * @param semesterId the ID of the semester
     * @return List of projects for the specified semester
     * @throws SQLException if a database error occurs
     */
    public List<Project> getProjectsBySemester(int semesterId) throws SQLException {
        List<Project> projects = new ArrayList<>();
        String sql = "SELECT p.*, m.Name as LeadName, s.Term, s.Year " +
                     "FROM Projects p " +
                     "JOIN Members m ON p.ProjectLeadID = m.MemberID " +
                     "JOIN Semesters s ON p.SemesterID = s.SemesterID " +
                     "WHERE p.SemesterID = ? " +
                     "ORDER BY p.Title";
        
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, semesterId);
            
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Project project = mapResultSetToProject(rs);
                    projects.add(project);
                }
            }
        }
        
        return projects;
    }
    
    /**
     * Get projects by lead
     * 
     * @param leadId the ID of the project lead
     * @return List of projects led by the specified member
     * @throws SQLException if a database error occurs
     */
    public List<Project> getProjectsByLead(int leadId) throws SQLException {
        List<Project> projects = new ArrayList<>();
        String sql = "SELECT p.*, m.Name as LeadName, s.Term, s.Year " +
                     "FROM Projects p " +
                     "JOIN Members m ON p.ProjectLeadID = m.MemberID " +
                     "JOIN Semesters s ON p.SemesterID = s.SemesterID " +
                     "WHERE p.ProjectLeadID = ? " +
                     "ORDER BY p.CreatedAt DESC";
        
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, leadId);
            
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Project project = mapResultSetToProject(rs);
                    projects.add(project);
                }
            }
        }
        
        return projects;
    }
    
    /**
     * Add a new project to the database
     * 
     * @param project the Project object to add
     * @return the ID of the newly created project
     * @throws SQLException if a database error occurs
     */
    public int addProject(Project project) throws SQLException {
        String sql = "INSERT INTO Projects (Title, Description, Topic, SemesterID, ProjectLeadID, " +
                     "GitHubLink, SpotsAvailable, Status) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            stmt.setString(1, project.getTitle());
            stmt.setString(2, project.getDescription());
            stmt.setString(3, project.getTopic());
            stmt.setInt(4, project.getSemesterId());
            stmt.setInt(5, project.getProjectLeadId());
            stmt.setString(6, project.getGitHubLink());
            stmt.setInt(7, project.getSpotsAvailable());
            stmt.setString(8, project.getStatus());
            
            int affectedRows = stmt.executeUpdate();
            
            if (affectedRows == 0) {
                throw new SQLException("Creating project failed, no rows affected.");
            }
            
            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1);
                } else {
                    throw new SQLException("Creating project failed, no ID obtained.");
                }
            }
        }
    }
    
    /**
     * Update an existing project in the database
     * 
     * @param project the Project object with updated information
     * @return true if the update was successful, false otherwise
     * @throws SQLException if a database error occurs
     */
    public boolean updateProject(Project project) throws SQLException {
        String sql = "UPDATE Projects SET Title = ?, Description = ?, Topic = ?, SemesterID = ?, " +
                     "ProjectLeadID = ?, GitHubLink = ?, SpotsAvailable = ?, Status = ? " +
                     "WHERE ProjectID = ?";
        
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, project.getTitle());
            stmt.setString(2, project.getDescription());
            stmt.setString(3, project.getTopic());
            stmt.setInt(4, project.getSemesterId());
            stmt.setInt(5, project.getProjectLeadId());
            stmt.setString(6, project.getGitHubLink());
            stmt.setInt(7, project.getSpotsAvailable());
            stmt.setString(8, project.getStatus());
            stmt.setInt(9, project.getProjectId());
            
            int affectedRows = stmt.executeUpdate();
            return affectedRows > 0;
        }
    }
    
    /**
     * Delete a project from the database
     * 
     * @param projectId the ID of the project to delete
     * @return true if the deletion was successful, false otherwise
     * @throws SQLException if a database error occurs
     */
    public boolean deleteProject(int projectId) throws SQLException {
        String sql = "DELETE FROM Projects WHERE ProjectID = ?";
        
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, projectId);
            
            int affectedRows = stmt.executeUpdate();
            return affectedRows > 0;
        }
    }
    
    /**
     * Get the number of members in a project
     * 
     * @param projectId the ID of the project
     * @return the number of members in the project
     * @throws SQLException if a database error occurs
     */
    public int getProjectMemberCount(int projectId) throws SQLException {
        String sql = "SELECT COUNT(*) FROM ProjectMembers WHERE ProjectID = ? AND Status = 'Approved'";
        
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, projectId);
            
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        }
        
        return 0;
    }
    
    /**
     * Map a ResultSet row to a Project object
     * 
     * @param rs the ResultSet containing project data
     * @return a Project object populated with data from the ResultSet
     * @throws SQLException if a database error occurs
     */
    private Project mapResultSetToProject(ResultSet rs) throws SQLException {
        Project project = new Project();
        project.setProjectId(rs.getInt("ProjectID"));
        project.setTitle(rs.getString("Title"));
        project.setDescription(rs.getString("Description"));
        project.setTopic(rs.getString("Topic"));
        project.setSemesterId(rs.getInt("SemesterID"));
        project.setProjectLeadId(rs.getInt("ProjectLeadID"));
        project.setGitHubLink(rs.getString("GitHubLink"));
        project.setSpotsAvailable(rs.getInt("SpotsAvailable"));
        project.setStatus(rs.getString("Status"));
        project.setCreatedAt(rs.getTimestamp("CreatedAt"));
        project.setUpdatedAt(rs.getTimestamp("UpdatedAt"));
        
        // Additional fields from joins
        project.setLeadName(rs.getString("LeadName"));
        project.setSemesterTerm(rs.getString("Term"));
        project.setSemesterYear(rs.getInt("Year"));
        
        return project;
    }
}
