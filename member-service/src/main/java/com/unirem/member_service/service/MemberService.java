package com.unirem.member_service.service;

import com.unirem.member_service.DTO.ProjectRequest;
import com.unirem.member_service.DTO.ProjectResponse;
import com.unirem.member_service.DTO.UserDTO;
import com.unirem.member_service.entity.Project;
import com.unirem.member_service.entity.User;
import com.unirem.member_service.repository.ProjectRepository;
import com.unirem.member_service.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

//Por errores con las librerías de mapeadores se decidió crear los mapeos manualmente
@Service
public class MemberService {
    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private UserRepository userRespository;

    @Autowired
    private FileSaver fileSaver;

    public ProjectResponse createProject(ProjectRequest request) {
        Project project = new Project();
        User user = userRespository.findById(request.getLeader().getUserId())
                .orElseGet(() -> {
                    User newUser = new User();
                    newUser.setUserId(request.getLeader().getUserId());
                    newUser.setName(request.getLeader().getName());
                    newUser.setEmail(request.getLeader().getEmail());
                    newUser.setPhone(request.getLeader().getPhone());
                    return userRespository.save(newUser);
                });

        project.setTittle(request.getTittle());
        project.setDescription(request.getDescription());
        project.setStatus(request.getStatus());
        project.setCreationDate(request.getCreationDate());
        project.setEndDate(request.getEndDate());
        project.setResearchArea(request.getResearchArea());
        project.setResearchTopic(request.getResearchTopic());
        project.setIdentifierArea(request.getIdentifierArea());
        project.setSlug(request.getSlug());
        project.setIsValid(false);
        project.setImageUrl(fileSaver.save(request.getImage()));
        project.setDocumentUrl(fileSaver.save(request.getDocument()));
        project.setLeader(user);
        project.getResearches().add(user);

        user.getProjects().add(project);

        project = projectRepository.save(project);

        return mapProject(project);
    }

    public void addUserToProject(Long projectId, UserDTO userDTO) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new RuntimeException("Project not found"));

        User user = userRespository.findById(userDTO.getUserId())
                .orElseGet(() -> {
                    User newUser = new User();
                    newUser.setUserId(userDTO.getUserId());
                    newUser.setName(userDTO.getName());
                    newUser.setEmail(userDTO.getEmail());
                    newUser.setPhone(userDTO.getPhone());
                    return userRespository.save(newUser);
                });

        project.getResearches().add(user);

        user.getProjects().add(project);

        projectRepository.save(project);
    }

    public void approveProject(Long projectId) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new RuntimeException("Project not found"));

        project.setIsValid(true);

        projectRepository.save(project);
    }

    private UserDTO mapUser(User user) {
        List<ProjectResponse> projectsResponse = new ArrayList<>();
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
        for (ProjectResponse projectResponse : userDTO.getProjects()) {
            projects.add(mapProjectResponse(projectResponse));
        }

        return new User(
                userDTO.getUserId(),
                userDTO.getName(),
                userDTO.getEmail(),
                userDTO.getPhone(),
                projects
            );
    }

    private ProjectResponse mapProject(Project project) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        List<UserDTO> usersDTO = new ArrayList<>();
        for (User user : project.getResearches()) {
            usersDTO.add(mapUser(user));
        }

        return new ProjectResponse(
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

    private Project mapProjectResponse(ProjectResponse response) {
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
