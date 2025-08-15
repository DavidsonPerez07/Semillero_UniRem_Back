package com.unirem.member_service.DTO;

import com.unirem.member_service.entity.ResearchArea;
import com.unirem.member_service.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProjectDTO {
    private String tittle;
    private String description;
    private String status;
    private String year;
    private String slug;
    private byte[] image;
    private List<UserDTO> researches;
    private ResearchAreaDTO area;
}
