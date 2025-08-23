package com.unirem.member_service.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "news")
public class News {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long newId;
    @Column(nullable = false)
    private String tittle;
    @Column(nullable = false)
    private String excerpt;
    @Column(nullable = false)
    private String content;
    @Enumerated(EnumType.STRING)
    private NewCategory category;
    @Column(nullable = false)
    private LocalDate date;
    private String imageUrl;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "author_id", referencedColumnName = "user_id")
    private User author;
    private String slug;
    @Column(nullable = false)
    private Boolean valid;
}
