package com.mlatsjsu.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private Role role;
    private Status status;
    private User user;

    public enum Role {
        MEMBER,
        LEAD
    }

    public enum Status {
        PENDING,
        APPROVED,
        REJECTED
    }
}