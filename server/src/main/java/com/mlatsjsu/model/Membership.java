package com.mlatsjsu.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Membership {
    private Long userId;
    private Long projectId;
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