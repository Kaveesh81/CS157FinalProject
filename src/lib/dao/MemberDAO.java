package lib.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import lib.model.Member;
import lib.util.DBUtil;

/**
 * Data Access Object for Member entity
 * Handles database operations related to members
 */
public class MemberDAO {
    
    /**
     * Get all members from the database
     * 
     * @return List of all members
     * @throws SQLException if a database error occurs
     */
    public List<Member> getAllMembers() throws SQLException {
        List<Member> members = new ArrayList<>();
        String sql = "SELECT * FROM Members ORDER BY Name";
        
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            
            while (rs.next()) {
                Member member = mapResultSetToMember(rs);
                members.add(member);
            }
        }
        
        return members;
    }
    
    /**
     * Get a member by ID
     * 
     * @param memberId the ID of the member to retrieve
     * @return the Member object, or null if not found
     * @throws SQLException if a database error occurs
     */
    public Member getMemberById(int memberId) throws SQLException {
        String sql = "SELECT * FROM Members WHERE MemberID = ?";
        
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, memberId);
            
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToMember(rs);
                }
            }
        }
        
        return null;
    }
    
    /**
     * Get a member by email
     * 
     * @param email the email of the member to retrieve
     * @return the Member object, or null if not found
     * @throws SQLException if a database error occurs
     */
    public Member getMemberByEmail(String email) throws SQLException {
        String sql = "SELECT * FROM Members WHERE Email = ?";
        
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, email);
            
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToMember(rs);
                }
            }
        }
        
        return null;
    }
    
    /**
     * Add a new member to the database
     * 
     * @param member the Member object to add
     * @return the ID of the newly created member
     * @throws SQLException if a database error occurs
     */
    public int addMember(Member member) throws SQLException {
        String sql = "INSERT INTO Members (Name, Email, Password, LinkedIn, GradDate, Role, StartDate) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            stmt.setString(1, member.getName());
            stmt.setString(2, member.getEmail());
            stmt.setString(3, member.getPassword());
            stmt.setString(4, member.getLinkedIn());
            stmt.setString(5, member.getGradDate());
            stmt.setString(6, member.getRole());
            stmt.setString(7, member.getStartDate());
            
            int affectedRows = stmt.executeUpdate();
            
            if (affectedRows == 0) {
                throw new SQLException("Creating member failed, no rows affected.");
            }
            
            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1);
                } else {
                    throw new SQLException("Creating member failed, no ID obtained.");
                }
            }
        }
    }
    
    /**
     * Update an existing member in the database
     * 
     * @param member the Member object with updated information
     * @return true if the update was successful, false otherwise
     * @throws SQLException if a database error occurs
     */
    public boolean updateMember(Member member) throws SQLException {
        String sql = "UPDATE Members SET Name = ?, Email = ?, LinkedIn = ?, GradDate = ?, " +
                     "Role = ?, StartDate = ? WHERE MemberID = ?";
        
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, member.getName());
            stmt.setString(2, member.getEmail());
            stmt.setString(3, member.getLinkedIn());
            stmt.setString(4, member.getGradDate());
            stmt.setString(5, member.getRole());
            stmt.setString(6, member.getStartDate());
            stmt.setInt(7, member.getMemberId());
            
            int affectedRows = stmt.executeUpdate();
            return affectedRows > 0;
        }
    }
    
    /**
     * Delete a member from the database
     * 
     * @param memberId the ID of the member to delete
     * @return true if the deletion was successful, false otherwise
     * @throws SQLException if a database error occurs
     */
    public boolean deleteMember(int memberId) throws SQLException {
        String sql = "DELETE FROM Members WHERE MemberID = ?";
        
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, memberId);
            
            int affectedRows = stmt.executeUpdate();
            return affectedRows > 0;
        }
    }
    
    /**
     * Check if a member is eligible to be a project member
     * 
     * @param memberId the ID of the member to check
     * @return true if the member is eligible, false otherwise
     * @throws SQLException if a database error occurs
     */
    public boolean isEligibleForProjectMembership(int memberId) throws SQLException {
        String sql = "SELECT COUNT(*) FROM EligibilityRecords WHERE MemberID = ?";
        
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, memberId);
            
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        }
        
        return false;
    }
    
    /**
     * Get all members by role
     * 
     * @param role the role to filter by
     * @return List of members with the specified role
     * @throws SQLException if a database error occurs
     */
    public List<Member> getMembersByRole(String role) throws SQLException {
        List<Member> members = new ArrayList<>();
        String sql = "SELECT * FROM Members WHERE Role = ? ORDER BY Name";
        
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, role);
            
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Member member = mapResultSetToMember(rs);
                    members.add(member);
                }
            }
        }
        
        return members;
    }
    
    /**
     * Map a ResultSet row to a Member object
     * 
     * @param rs the ResultSet containing member data
     * @return a Member object populated with data from the ResultSet
     * @throws SQLException if a database error occurs
     */
    private Member mapResultSetToMember(ResultSet rs) throws SQLException {
        Member member = new Member();
        member.setMemberId(rs.getInt("MemberID"));
        member.setName(rs.getString("Name"));
        member.setEmail(rs.getString("Email"));
        member.setPassword(rs.getString("Password"));
        member.setLinkedIn(rs.getString("LinkedIn"));
        member.setGradDate(rs.getString("GradDate"));
        member.setRole(rs.getString("Role"));
        member.setStartDate(rs.getString("StartDate"));
        member.setCreatedAt(rs.getTimestamp("CreatedAt"));
        member.setUpdatedAt(rs.getTimestamp("UpdatedAt"));
        return member;
    }
}
