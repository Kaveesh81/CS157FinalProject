package com.mlservlet.servlets;

import java.io.IOException;
import java.sql.Connection;
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
                project.put("id", rs.getInt("project_id"));
                project.put("title", rs.getString("title"));
                project.put("description", rs.getString("description"));
                project.put("topic", rs.getString("topic"));
                project.put("githubLink", rs.getString("github_link"));
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
} 