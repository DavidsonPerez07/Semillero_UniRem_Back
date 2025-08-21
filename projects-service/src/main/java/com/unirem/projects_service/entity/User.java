package com.unirem.projects_service.entity;

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
@Table(name = "users")
public class User {
    @Id
    @Column(name = "user_id")
    private Long userId;
    private String name;
    private String phone;
    private String email;

    @ManyToMany(mappedBy = "researches")
    private List<Project> projects = new ArrayList<>();
}
