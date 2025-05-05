package lib.model;

import java.sql.Timestamp;

/**
 * Model class representing a Project entity
 */
public class Project {
    private int projectId;
    private String title;
    private String description;
    private String topic;
    private int semesterId;
    private int projectLeadId;
    private String gitHubLink;
    private int spotsAvailable;
    private String status;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    
    // Additional fields from joins
    private String leadName;
    private String semesterTerm;
    private int semesterYear;
    
    // Default constructor
    public Project() {
    }
    
    // Constructor with fields
    public Project(String title, String description, String topic, int semesterId, 
                   int projectLeadId, String gitHubLink, int spotsAvailable, String status) {
        this.title = title;
        this.description = description;
        this.topic = topic;
        this.semesterId = semesterId;
        this.projectLeadId = projectLeadId;
        this.gitHubLink = gitHubLink;
        this.spotsAvailable = spotsAvailable;
        this.status = status;
    }
    
    // Getters and setters
    public int getProjectId() {
        return projectId;
    }
    
    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }
    
    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public String getTopic() {
        return topic;
    }
    
    public void setTopic(String topic) {
        this.topic = topic;
    }
    
    public int getSemesterId() {
        return semesterId;
    }
    
    public void setSemesterId(int semesterId) {
        this.semesterId = semesterId;
    }
    
    public int getProjectLeadId() {
        return projectLeadId;
    }
    
    public void setProjectLeadId(int projectLeadId) {
        this.projectLeadId = projectLeadId;
    }
    
    public String getGitHubLink() {
        return gitHubLink;
    }
    
    public void setGitHubLink(String gitHubLink) {
        this.gitHubLink = gitHubLink;
    }
    
    public int getSpotsAvailable() {
        return spotsAvailable;
    }
    
    public void setSpotsAvailable(int spotsAvailable) {
        this.spotsAvailable = spotsAvailable;
    }
    
    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    public Timestamp getCreatedAt() {
        return createdAt;
    }
    
    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
    
    public Timestamp getUpdatedAt() {
        return updatedAt;
    }
    
    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }
    
    public String getLeadName() {
        return leadName;
    }
    
    public void setLeadName(String leadName) {
        this.leadName = leadName;
    }
    
    public String getSemesterTerm() {
        return semesterTerm;
    }
    
    public void setSemesterTerm(String semesterTerm) {
        this.semesterTerm = semesterTerm;
    }
    
    public int getSemesterYear() {
        return semesterYear;
    }
    
    public void setSemesterYear(int semesterYear) {
        this.semesterYear = semesterYear;
    }
    
    public String getSemesterDisplay() {
        return semesterTerm + " " + semesterYear;
    }
    
    @Override
    public String toString() {
        return "Project{" +
                "projectId=" + projectId +
                ", title='" + title + '\'' +
                ", topic='" + topic + '\'' +
                ", status='" + status + '\'' +
                ", leadName='" + leadName + '\'' +
                ", semester='" + getSemesterDisplay() + '\'' +
                '}';
    }
}
