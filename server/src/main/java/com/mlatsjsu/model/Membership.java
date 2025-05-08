package com.mlatsjsu.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Membership {
    private Long membershipId;
    private Long userId;
    private Long projectId;
    private String role;
    private String status;
    private User user;
}