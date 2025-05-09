package com.mlatsjsu.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjectProposalDTO {
    private Long proposerId;
    private String title;
    private String description;
    private String topic;
    private Long semesterId;
    private String githubLink;
    private Integer spotsAvailable;
}