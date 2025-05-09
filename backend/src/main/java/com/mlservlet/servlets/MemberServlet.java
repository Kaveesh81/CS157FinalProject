package com.mlservlet.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mlservlet.util.DatabaseUtil;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class MemberServlet extends BaseServlet {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        setCorsHeaders(request, response);
        response.setContentType("application/json");
        
        List<Map<String, Object>> members = new ArrayList<>();
        try (Connection conn = DatabaseUtil.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM members")) {
            
            while (rs.next()) {
                Map<String, Object> member = new HashMap<>();
                member.put("id", rs.getInt("id"));
                member.put("name", rs.getString("name"));
                member.put("email", rs.getString("email"));
                member.put("role", rs.getString("role"));
                member.put("joinDate", rs.getDate("join_date").toString());
                members.add(member);
            }
            
            Map<String, Object> result = new HashMap<>();
            result.put("status", "success");
            result.put("members", members);
            sendJsonResponse(response, objectMapper.writeValueAsString(result));
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            Map<String, Object> result = new HashMap<>();
            result.put("status", "error");
            result.put("message", e.getMessage());
            sendJsonResponse(response, objectMapper.writeValueAsString(result));
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        setCorsHeaders(request, response);
        response.setContentType("application/json");

        try {
            Map<String, Object> memberData = objectMapper.readValue(request.getReader(), Map.class);
            
            String sql = "INSERT INTO members (name, email, role, join_date) VALUES (?, ?, ?, ?)";
            try (Connection conn = DatabaseUtil.getConnection();
                 PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                
                pstmt.setString(1, (String) memberData.get("name"));
                pstmt.setString(2, (String) memberData.get("email"));
                pstmt.setString(3, (String) memberData.get("role"));
                pstmt.setString(4, (String) memberData.get("joinDate"));
                
                int affectedRows = pstmt.executeUpdate();
                if (affectedRows > 0) {
                    Map<String, Object> result = new HashMap<>();
                    result.put("status", "success");
                    result.put("message", "Member created successfully");
                    sendJsonResponse(response, objectMapper.writeValueAsString(result));
                } else {
                    throw new Exception("Failed to create member");
                }
            }
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            Map<String, Object> result = new HashMap<>();
            result.put("status", "error");
            result.put("message", e.getMessage());
            sendJsonResponse(response, objectMapper.writeValueAsString(result));
        }
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException {
        setCorsHeaders(request, response);
        response.setContentType("application/json");

        String pathInfo = request.getPathInfo();
        if (pathInfo == null || pathInfo.equals("/")) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            sendJsonResponse(response, objectMapper.writeValueAsString(Map.of(
                "status", "error",
                "message", "Member ID is required"
            )));
            return;
        }

        String memberId = pathInfo.substring(1);
        try {
            Map<String, Object> memberData = objectMapper.readValue(request.getReader(), Map.class);
            
            String sql = "UPDATE members SET name = ?, email = ?, role = ?, join_date = ? WHERE id = ?";
            try (Connection conn = DatabaseUtil.getConnection();
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {
                
                pstmt.setString(1, (String) memberData.get("name"));
                pstmt.setString(2, (String) memberData.get("email"));
                pstmt.setString(3, (String) memberData.get("role"));
                pstmt.setString(4, (String) memberData.get("joinDate"));
                pstmt.setInt(5, Integer.parseInt(memberId));
                
                int affectedRows = pstmt.executeUpdate();
                if (affectedRows > 0) {
                    Map<String, Object> result = new HashMap<>();
                    result.put("status", "success");
                    result.put("message", "Member updated successfully");
                    sendJsonResponse(response, objectMapper.writeValueAsString(result));
                } else {
                    throw new Exception("Member not found");
                }
            }
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            Map<String, Object> result = new HashMap<>();
            result.put("status", "error");
            result.put("message", e.getMessage());
            sendJsonResponse(response, objectMapper.writeValueAsString(result));
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        setCorsHeaders(request, response);
        response.setContentType("application/json");

        String pathInfo = request.getPathInfo();
        if (pathInfo == null || pathInfo.equals("/")) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            sendJsonResponse(response, objectMapper.writeValueAsString(Map.of(
                "status", "error",
                "message", "Member ID is required"
            )));
            return;
        }

        String memberId = pathInfo.substring(1);
        try {
            String sql = "DELETE FROM members WHERE id = ?";
            try (Connection conn = DatabaseUtil.getConnection();
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {
                
                pstmt.setInt(1, Integer.parseInt(memberId));
                
                int affectedRows = pstmt.executeUpdate();
                if (affectedRows > 0) {
                    Map<String, Object> result = new HashMap<>();
                    result.put("status", "success");
                    result.put("message", "Member deleted successfully");
                    sendJsonResponse(response, objectMapper.writeValueAsString(result));
                } else {
                    throw new Exception("Member not found");
                }
            }
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            Map<String, Object> result = new HashMap<>();
            result.put("status", "error");
            result.put("message", e.getMessage());
            sendJsonResponse(response, objectMapper.writeValueAsString(result));
        }
    }
} 