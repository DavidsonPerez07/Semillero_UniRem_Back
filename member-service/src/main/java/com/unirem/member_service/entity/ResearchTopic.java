package com.unirem.member_service.entity;

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
@Table(name = "research_topics")
public class ResearchTopic {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long topicId;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String url;
    @Column(nullable = false)
    private String identifier;

    @ManyToOne
    @JoinColumn(name = "area_id")
    private ResearchArea area;
}
