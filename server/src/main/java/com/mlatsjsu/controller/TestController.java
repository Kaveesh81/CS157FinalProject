// src/main/java/com/mlatsjsu/controller/TestController.java
package com.mlatsjsu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {
    
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @GetMapping("/connection")
    public String testConnection() {
        try {
            // Test database connection
            jdbcTemplate.queryForObject("SELECT 1", Integer.class);
            return "Backend and Database connection successful!";
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }
}