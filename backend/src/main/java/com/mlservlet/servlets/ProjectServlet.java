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

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ProjectServlet extends BaseServlet {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        setCorsHeaders(request, response);
        response.setContentType("application/json");
        
        List<Map<String, Object>> projects = new ArrayList<>();
        try (Connection conn = DatabaseUtil.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM projects")) {
            
            while (rs.next()) {
                Map<String, Object> project = new HashMap<>();
                project.put("id", rs.getInt("id"));
                project.put("title", rs.getString("title"));
                project.put("description", rs.getString("description"));
                project.put("topic", rs.getString("topic"));
                project.put("spotsAvailable", rs.getInt("spots_available"));
                project.put("isApproved", rs.getBoolean("is_approved"));
                projects.add(project);
            }
            
            Map<String, Object> result = new HashMap<>();
            result.put("status", "success");
            result.put("projects", projects);
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        setCorsHeaders(request, response);
        response.setContentType("application/json");
        
        try {
            Map<String, Object> projectData = objectMapper.readValue(request.getReader(), Map.class);
            
            String sql = "INSERT INTO projects (title, description, topic, spots_available, is_approved) VALUES (?, ?, ?, ?, ?)";
            try (Connection conn = DatabaseUtil.getConnection();
                 PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                
                pstmt.setString(1, (String) projectData.get("title"));
                pstmt.setString(2, (String) projectData.get("description"));
                pstmt.setString(3, (String) projectData.get("topic"));
                pstmt.setInt(4, ((Number) projectData.get("spotsAvailable")).intValue());
                pstmt.setBoolean(5, (Boolean) projectData.get("isApproved"));
                
                int affectedRows = pstmt.executeUpdate();
                if (affectedRows > 0) {
                    Map<String, Object> result = new HashMap<>();
                    result.put("status", "success");
                    result.put("message", "Project created successfully");
                    sendJsonResponse(response, objectMapper.writeValueAsString(result));
                } else {
                    throw new Exception("Failed to create project");
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
                "message", "Project ID is required"
            )));
            return;
        }

        String projectId = pathInfo.substring(1);
        try {
            Map<String, Object> projectData = objectMapper.readValue(request.getReader(), Map.class);
            
            String sql = "UPDATE projects SET title = ?, description = ?, topic = ?, spots_available = ?, is_approved = ? WHERE id = ?";
            try (Connection conn = DatabaseUtil.getConnection();
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {
                
                pstmt.setString(1, (String) projectData.get("title"));
                pstmt.setString(2, (String) projectData.get("description"));
                pstmt.setString(3, (String) projectData.get("topic"));
                pstmt.setInt(4, ((Number) projectData.get("spotsAvailable")).intValue());
                pstmt.setBoolean(5, (Boolean) projectData.get("isApproved"));
                pstmt.setInt(6, Integer.parseInt(projectId));
                
                int affectedRows = pstmt.executeUpdate();
                if (affectedRows > 0) {
                    Map<String, Object> result = new HashMap<>();
                    result.put("status", "success");
                    result.put("message", "Project updated successfully");
                    sendJsonResponse(response, objectMapper.writeValueAsString(result));
                } else {
                    throw new Exception("Project not found");
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
                "message", "Project ID is required"
            )));
            return;
        }

        String projectId = pathInfo.substring(1);
        try {
            String sql = "DELETE FROM projects WHERE id = ?";
            try (Connection conn = DatabaseUtil.getConnection();
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {
                
                pstmt.setInt(1, Integer.parseInt(projectId));
                
                int affectedRows = pstmt.executeUpdate();
                if (affectedRows > 0) {
                    Map<String, Object> result = new HashMap<>();
                    result.put("status", "success");
                    result.put("message", "Project deleted successfully");
                    sendJsonResponse(response, objectMapper.writeValueAsString(result));
                } else {
                    throw new Exception("Project not found");
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