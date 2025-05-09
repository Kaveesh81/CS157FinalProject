package com.mlatsjsu.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private Long userId;
    private String name;
    private String email;
    private String linkedin;
    private String gradDate;
    private String startDate;
    private String role;
    private boolean isManager;
}