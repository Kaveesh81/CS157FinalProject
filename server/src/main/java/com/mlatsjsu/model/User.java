package com.mlatsjsu.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private Long userId;
    private String name;
    private String email;
    private String linkedin;
    private String gradDate;
    private String role;
    private String startDate;
    private boolean isManager;
}