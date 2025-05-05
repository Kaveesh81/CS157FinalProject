package lib.model;

import java.sql.Timestamp;

/**
 * Model class representing a Member entity
 */
public class Member {
    private int memberId;
    private String name;
    private String email;
    private String password;
    private String linkedin;
    private String gradDate;
    private String role;
    private String startDate;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    
    // Default constructor
    public Member() {
    }
    
    // Constructor with fields
    public Member(String name, String email, String password, String linkedin, 
                  String gradDate, String role, String startDate) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.linkedin = linkedin;
        this.gradDate = gradDate;
        this.role = role;
        this.startDate = startDate;
    }
    
    // Getters and setters
    public int getMemberId() {
        return memberId;
    }
    
    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getLinkedIn() {
        return linkedin;
    }
    
    public void setLinkedIn(String linkedin) {
        this.linkedin = linkedin;
    }
    
    public String getGradDate() {
        return gradDate;
    }
    
    public void setGradDate(String gradDate) {
        this.gradDate = gradDate;
    }
    
    public String getRole() {
        return role;
    }
    
    public void setRole(String role) {
        this.role = role;
    }
    
    public String getStartDate() {
        return startDate;
    }
    
    public void setStartDate(String startDate) {
        this.startDate = startDate;
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
    
    @Override
    public String toString() {
        return "Member{" +
                "memberId=" + memberId +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", role='" + role + '\'' +
                ", gradDate='" + gradDate + '\'' +
                '}';
    }
}
