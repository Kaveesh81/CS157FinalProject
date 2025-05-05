package dao;

import db.DBConnection;
import java.sql.*;

public class SemesterDAO {
    public void add(int id, String term, int year) throws SQLException {
        String sql = "INSERT INTO Semesters VALUES (?, ?, ?)";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.setString(2, term);
            ps.setInt(3, year);
            ps.executeUpdate();
        }
    }

    public void updateTerm(int id, String newTerm) throws SQLException {
        String sql = "UPDATE Semesters SET Term = ? WHERE SemesterID = ?";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, newTerm);
            ps.setInt(2, id);
            ps.executeUpdate();
        }
    }

    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM Semesters WHERE SemesterID = ?";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    public ResultSet get
