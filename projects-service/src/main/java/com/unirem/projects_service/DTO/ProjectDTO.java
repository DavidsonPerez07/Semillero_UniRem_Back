package com.unirem.projects_service.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProjectDTO {
    private Long projectId;
    private String tittle;
    private String description;
    private String status;
    private String creationDate;
    private String endDate;
    private String researchArea;
    private String researchTopic;
    private String identifierArea;
    private String slug;
    private Boolean isValid;
    private String imageUrl;
    private String documentUrl;
    private UserDTO leader;
    private List<UserDTO> researches = new ArrayList<>();
}
