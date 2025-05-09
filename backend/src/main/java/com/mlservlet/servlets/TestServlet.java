package com.mlservlet.servlets;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class TestServlet extends BaseServlet {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        setCorsHeaders(request, response);
        response.setContentType("application/json");
        
        Map<String, Object> result = new HashMap<>();
        result.put("status", "success");
        result.put("message", "Test endpoint is working!");
        sendJsonResponse(response, objectMapper.writeValueAsString(result));
    }
} 