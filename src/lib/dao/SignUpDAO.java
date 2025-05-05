package dao;

import java.sql.*;
import java.util.*;

import model.SignUp;
import util.DBConnection;

public class SignUpDAO {

    // Add new sign-up (member joins a project)
    public void addSignUp(SignUp signup) throws SQLException {
        String sql = "INSERT INTO ProjectMemberships (MemberID, ProjectID, Role) VALUES (?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, signup.getMemberID());
            ps.setInt(2, signup.getProjectID());
            ps.setString(3, signup.getRole()); // e.g., "Member", "Lead"
            ps.executeUpdate();
        }
    }

    // Delete a sign-up (member leaves project)
    public void deleteSignUp(int memberId, int projectId) throws SQLException {
        String sql = "DELETE FROM ProjectMemberships WHERE MemberID = ? AND ProjectID = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, memberId);
            ps.setInt(2, projectId);
            ps.executeUpdate();
        }
    }

    // Update role of a project member (e.g., Member → Lead)
    public void updateSignUpRole(SignUp signup) throws SQLException {
        String sql = "UPDATE ProjectMemberships SET Role = ? WHERE MemberID = ? AND ProjectID = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, signup.getRole());
            ps.setInt(2, signup.getMemberID());
            ps.setInt(3, signup.getProjectID());
            ps.executeUpdate();
        }
    }

    // Retrieve a specific sign-up
    public SignUp getSignUp(int memberId, int projectId) throws SQLException {
        String sql = "SELECT * FROM ProjectMemberships WHERE MemberID = ? AND ProjectID = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, memberId);
            ps.setInt(2, projectId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new SignUp(
                    rs.getInt("MemberID"),
                    rs.getInt("ProjectID"),
                    rs.getString("Role")
                );
            }
        }
        return null;
    }

    // Retrieve all sign-ups
    public List<SignUp> getAllSignUps() throws SQLException {
        List<SignUp> signups = new ArrayList<>();
        String sql = "SELECT * FROM ProjectMemberships";
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                signups.add(new SignUp(
                    rs.getInt("MemberID"),
                    rs.getInt("ProjectID"),
                    rs.getString("Role")
                ));
            }
        }
        return signups;
    }
}
