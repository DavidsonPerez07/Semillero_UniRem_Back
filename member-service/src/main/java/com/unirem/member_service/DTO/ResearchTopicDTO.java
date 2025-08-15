package com.unirem.member_service.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResearchTopicDTO {
    private Long topicId;
    private String name;
    private String url;
    private String identifier;
    private ResearchAreaDTO area;
}
