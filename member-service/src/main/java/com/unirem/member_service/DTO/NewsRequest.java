package com.unirem.member_service.DTO;

import com.unirem.member_service.entity.NewCategory;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NewsRequest {
    private String tittle;
    private String excerpt;
    private String content;
    private NewCategory category;
    private LocalDate date;
    private MultipartFile image;
    private UserDTO author;
    private String slug;
}
