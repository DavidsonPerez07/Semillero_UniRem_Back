package com.unirem.news_service.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "projects")
public class Project {
    @Id
    @GeneratedValue
    private Long projectId;
    @Column(nullable = false)
    private String tittle;
    @Column(nullable = false)
    private String description;
    @Column(nullable = false)
    private String status;
    @Column(nullable = false)
    private LocalDate creationDate;
    @Column(nullable = false)
    private LocalDate endDate;
    @Column(nullable = false)
    private String researchArea;
    @Column(nullable = false)
    private String researchTopic;
    @Column(nullable = false)
    private String identifierArea;
    private String slug;
    @Column(nullable = false)
    private Boolean isValid;
    private String imageUrl;
    private String documentUrl;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "leader_id", referencedColumnName = "user_id")
    private User leader;

    @ManyToMany
    @JoinTable(
            name = "project_members",
            joinColumns = @JoinColumn(name = "project_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<User> researches = new ArrayList<>();
}
