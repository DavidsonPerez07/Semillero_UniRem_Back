package com.unirem.projects_service.service;

import com.unirem.projects_service.DTO.ProjectDTO;
import com.unirem.projects_service.DTO.UserDTO;
import com.unirem.projects_service.entity.Project;
import com.unirem.projects_service.entity.User;
import com.unirem.projects_service.repository.ProjectRepository;
import com.unirem.projects_service.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProjectService {
    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private UserRepository userRepository;

    public List<ProjectDTO> allProjects() {
        return projectRepository.findAll().stream()
                .map(this::mapProject)
                .collect(Collectors.toList());
    }

    public ProjectDTO getProjectById(Long projectId) {
        return mapProject(projectRepository.findById(projectId)
                .orElseThrow(() -> new RuntimeException("Project not found")));
    }

    private UserDTO mapUser(User user) {
        List<ProjectDTO> projectsResponse = new ArrayList<>();
        for (Project project : user.getProjects()) {
            projectsResponse.add(mapProject(project));
        }

        return new UserDTO(
                user.getUserId(),
                user.getName(),
                user.getEmail(),
                user.getPhone(),
                projectsResponse
        );
    }

    private User mapUserDTO(UserDTO userDTO) {
        List<Project> projects = new ArrayList<>();
        for (ProjectDTO projectDTO : userDTO.getProjects()) {
            projects.add(mapProjectResponse(projectDTO));
        }

        return new User(
                userDTO.getUserId(),
                userDTO.getName(),
                userDTO.getEmail(),
                userDTO.getPhone(),
                projects
        );
    }

    private ProjectDTO mapProject(Project project) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        List<UserDTO> usersDTO = new ArrayList<>();
        for (User user : project.getResearches()) {
            usersDTO.add(mapUser(user));
        }

        return new ProjectDTO(
                project.getProjectId(),
                project.getTittle(),
                project.getDescription(),
                project.getStatus(),
                project.getCreationDate().format(formatter),
                project.getEndDate().format(formatter),
                project.getResearchArea(),
                project.getResearchTopic(),
                project.getIdentifierArea(),
                project.getSlug(),
                project.getIsValid(),
                project.getImageUrl(),
                project.getDocumentUrl(),
                mapUser(project.getLeader()),
                usersDTO
        );
    }

    private Project mapProjectResponse(ProjectDTO response) {
        List<User> users = new ArrayList<>();
        for (UserDTO userDTO : response.getResearches()) {
            users.add(mapUserDTO(userDTO));
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate creationDate = LocalDate.parse(response.getCreationDate(), formatter);
        LocalDate endDate = LocalDate.parse(response.getEndDate(), formatter);

        return new Project(
                response.getProjectId(),
                response.getTittle(),
                response.getDescription(),
                response.getStatus(),
                creationDate,
                endDate,
                response.getResearchArea(),
                response.getResearchTopic(),
                response.getIdentifierArea(),
                response.getSlug(),
                response.getIsValid(),
                response.getImageUrl(),
                response.getDocumentUrl(),
                mapUserDTO(response.getLeader()),
                users
        );
    }
}
