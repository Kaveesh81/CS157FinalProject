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
    private Semester semester;
    private Long semesterId;
    private Long projectLeadId;
    private String githubLink;
    private Integer spotsAvailable;
    private boolean isApproved;
}