package dao;

import db.DBConnection;
import java.sql.*;

public class ProjectDAO {
    public void add(int id, String title, String description, String topic, int semesterId, int leadId, String github, int spots) throws SQLException {
        String sql = "INSERT INTO Projects VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.setString(2, title);
            ps.setString(3, description);
            ps.setString(4, topic);
            ps.setInt(5, semesterId);
            ps.setInt(6, leadId);
            ps.setString(7, github);
            ps.setInt(8, spots);
            ps.executeUpdate();
        }
    }

    public void updateSpots(int id, int newSpots) throws SQLException {
        String sql = "UPDATE Projects SET SpotsAvailable = ? WHERE ProjectID = ?";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, newSpots);
            ps.setInt(2, id);
            ps.executeUpdate();
        }
    }

    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM Projects WHERE ProjectID = ?";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    public ResultSet getAll() throws SQLException {
        return DBConnection.getConnection().createStatement().executeQuery("SELECT * FROM Projects");
    }

    public ResultSet getByID(int id) throws SQLException {
        PreparedStatement ps = DBConnection.getConnection().prepareStatement("SELECT * FROM Projects WHERE ProjectID = ?");
        ps.setInt(1, id);
        return ps.executeQuery();
    }
}
