package com.unirem.member_service.service;

import com.unirem.member_service.DTO.*;
import com.unirem.member_service.entity.GalleryImage;
import com.unirem.member_service.entity.News;
import com.unirem.member_service.entity.Project;
import com.unirem.member_service.entity.User;
import com.unirem.member_service.repository.GalleryImageRepository;
import com.unirem.member_service.repository.NewsRepository;
import com.unirem.member_service.repository.ProjectRepository;
import com.unirem.member_service.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@Service
public class MemberService {
    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private UserRepository userRespository;

    @Autowired
    private NewsRepository newsRepository;

    @Autowired
    private GalleryImageRepository galleryImageRepository;

    @Autowired
    private ModelMapper modelMapper;

    private final String uploadProjectDir = "uploads/projects/";
    private final String uploadNewsDir = "uploads/news/";
    private final String uploadGalleryDir = "uploads/gallery/";

    public ProjectDTO createProject(ProjectRequest request) {
        Project project = new Project();
        User user = userRespository.findById(request.getLeader().getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        project.setTittle(request.getTittle());
        project.setDescription(request.getDescription());
        project.setLeader(user);
        project.getResearches().add(user);
        project.setStatus(request.getStatus());
        project.setCreationDate(request.getCreationDate());
        project.setEndDate(request.getEndDate());
        project.setResearchArea(request.getResearchArea());
        project.setResearchTopic(request.getResearchTopic());
        project.setIdentifierArea(request.getIdentifierArea());
        project.setSlug(request.getSlug());
        project.setValid(false);
        project.setImageUrl(saveFile(request.getImage(), uploadProjectDir));
        project.setDocumentUrl(saveFile(request.getDocument(), uploadProjectDir));

        project = projectRepository.save(project);

        return projectToProjectDTO(project);
    }

    public void addUserToProject(Long projectId, UserDTO userDTO) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new RuntimeException("Project not found"));

        User user = userRespository.findById(userDTO.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        project.getResearches().add(user);

        projectRepository.save(project);
    }

    public void approveProject(Long projectId) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new RuntimeException("Project not found"));

        project.setValid(true);

        projectRepository.save(project);
    }

    public NewsDTO createNews(NewsRequest newsRequest) {
        News news = new News();
        User user = userRespository.findById(newsRequest.getAuthor().getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        news.setTittle(newsRequest.getTittle());
        news.setExcerpt(newsRequest.getExcerpt());
        news.setContent(newsRequest.getContent());
        news.setCategory(newsRequest.getCategory());
        news.setDate(newsRequest.getDate());
        news.setImageUrl(saveFile(newsRequest.getImage(), uploadNewsDir));
        news.setAuthor(user);
        news.setSlug(newsRequest.getSlug());
        news.setValid(false);

        news = newsRepository.save(news);

        return newsToNewsDTO(news);
    }

    public void approveNews(Long newsId) {
        News news = newsRepository.findById(newsId)
                .orElseThrow(() -> new RuntimeException("News not found"));

        news.setValid(true);

        newsRepository.save(news);
    }

    public GalleryImageDTO createGalleryImage(GalleryImageRequest galleryImageRequest) {
        GalleryImage galleryImage = new GalleryImage();

        galleryImage.setTittle(galleryImageRequest.getTittle());
        galleryImage.setDescription(galleryImageRequest.getDescription());
        galleryImage.setImageUrl(saveFile(galleryImageRequest.getImage(), uploadGalleryDir));

        galleryImage = galleryImageRepository.save(galleryImage);

        return galleryImageToGalleryImageDTO(galleryImage);
    }

    public List<UserDTO> getAllUsers() {
        return userRespository.findAll().stream()
                .map(this::userToUserDTO)
                .toList();
    }

    private ProjectDTO projectToProjectDTO(Project project) {
        return modelMapper.map(project, ProjectDTO.class);
    }

    private NewsDTO newsToNewsDTO(News news) {
        return modelMapper.map(news, NewsDTO.class);
    }

    private GalleryImageDTO galleryImageToGalleryImageDTO(GalleryImage galleryImage) {
        return modelMapper.map(galleryImage, GalleryImageDTO.class);
    }

    private UserDTO userToUserDTO(User user) {
        return modelMapper.map(user, UserDTO.class);
    }

    private String saveFile(MultipartFile file, String specificDir) {
        if (file == null || file.isEmpty()) {
            return null;
        }

        try {
            Path uploadPath = Paths.get(specificDir);
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            String uniqueName = UUID.randomUUID() + "-" + file.getOriginalFilename();
            Path filePath = uploadPath.resolve(uniqueName);

            file.transferTo(filePath.toFile());

            return "/files/" + uniqueName;
        } catch (IOException e) {
            throw new RuntimeException("Error saving file: " + e.getMessage());
        }
    }
}
