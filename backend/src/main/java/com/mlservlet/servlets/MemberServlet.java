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
} 