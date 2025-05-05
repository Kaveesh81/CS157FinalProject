package dao;

import db.DBConnection;
import java.sql.*;
import java.util.*;

public class MemberDAO {
    public void add(int id, String name, String email, String linkedIn, String gradDate, String role, String startDate) throws SQLException {
        String sql = "INSERT INTO Members VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.setString(2, name);
            ps.setString(3, email);
            ps.setString(4, linkedIn);
            ps.setString(5, gradDate);
            ps.setString(6, role);
            ps.setString(7, startDate);
            ps.executeUpdate();
        }
    }

    public void updateEmail(int id, String newEmail) throws SQLException {
        String sql = "UPDATE Members SET Email = ? WHERE MemberID = ?";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, newEmail);
            ps.setInt(2, id);
            ps.executeUpdate();
        }
    }

    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM Members WHERE MemberID = ?";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    public ResultSet getAll() throws SQLException {
        Connection conn = DBConnection.getConnection();
        String sql = "SELECT * FROM Members";
        return conn.createStatement().executeQuery(sql);
    }

    public ResultSet getByID(int id) throws SQLException {
        Connection conn = DBConnection.getConnection();
        String sql = "SELECT * FROM Members WHERE MemberID = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, id);
        return ps.executeQuery();
    }
}
