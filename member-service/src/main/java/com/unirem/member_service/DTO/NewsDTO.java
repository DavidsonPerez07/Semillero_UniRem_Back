package com.unirem.member_service.DTO;

import com.unirem.member_service.entity.NewCategory;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NewsDTO {
    private Long newId;
    private String tittle;
    private String excerpt;
    private String content;
    private NewCategory category;
    private LocalDate date;
    private String imageUrl;
    private UserDTO author;
    private String slug;
    private Boolean valid;
}
