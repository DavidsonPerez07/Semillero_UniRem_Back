package com.unirem.member_service.entity;

import jakarta.persistence.*;
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
@Entity
@Table(name = "research_areas")
public class ResearchArea {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long areaId;
    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "research_areas", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ResearchTopic> topics = new ArrayList<>();

    @OneToMany(mappedBy = "research_areas", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Project> projects = new ArrayList<>();
}
