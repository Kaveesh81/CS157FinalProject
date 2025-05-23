package com.mlatsjsu.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Project {
    private Long projectId;
    private String title;
    private String description;
    private String topic;
    private Long semesterId;
    private String githubLink;
    private Integer spotsAvailable;
    private boolean isApproved;
}