package com.unirem.member_service.DTO;

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
public class ResearchAreaDTO {
    private Long areaId;
    private String name;
    private List<ResearchTopicDTO> topics = new ArrayList<>();
    private List<ProjectDTO> projects = new ArrayList<>();
}
