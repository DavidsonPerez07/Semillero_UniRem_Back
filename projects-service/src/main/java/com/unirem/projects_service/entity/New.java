package com.unirem.projects_service.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "news")
public class New {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long newId;
    @Column(nullable = false)
    private String tittle;
    @Column(nullable = false)
    private String excerpt;
    @Column(nullable = false)
    private String content;
}
