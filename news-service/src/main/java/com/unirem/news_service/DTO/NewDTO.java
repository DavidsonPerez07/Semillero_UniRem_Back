package com.unirem.news_service.DTO;

import com.unirem.news_service.entity.NewCategory;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NewDTO {
    private Long newId;
    private String tittle;
    private String excerpt;
    private String content;
    private NewCategory category;
    private LocalDate date;
    private String imageUrl;
    private String slug;
    private Boolean isValid;
    private UserDTO author;
}
