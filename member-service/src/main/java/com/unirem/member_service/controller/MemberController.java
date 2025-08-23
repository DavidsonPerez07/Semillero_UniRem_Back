package com.unirem.member_service.controller;

import com.unirem.member_service.DTO.*;
import com.unirem.member_service.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/member")
public class MemberController {
    @Autowired
    private MemberService memberService;

    @PostMapping(value = "/create-project", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ProjectDTO> createProject(@ModelAttribute ProjectRequest request) {
        return ResponseEntity.ok(memberService.createProject(request));
    }

    @PutMapping("/add-user-to-project")
    public ResponseEntity<?> addUserToProject(@RequestParam Long projectId, @RequestBody UserDTO userDTO) {
        return ResponseEntity.ok().build();
    }

    @PutMapping("/approve-project")
    public ResponseEntity<?> approveProject(@RequestParam Long projectId) {
        memberService.approveProject(projectId);
        return ResponseEntity.ok("Project approved");
    }

    @PostMapping(value = "/create-news", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<NewsDTO> createNews(@ModelAttribute NewsRequest request) {
        return ResponseEntity.ok(memberService.createNews(request));
    }

    @PutMapping("/approve-news")
    public ResponseEntity<?> approveNews(@RequestParam Long newsId) {
        memberService.approveNews(newsId);
        return ResponseEntity.ok("News approved");
    }

    @PostMapping(value = "/create-gallery-image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<GalleryImageDTO> createGalleryImage(@ModelAttribute GalleryImageRequest request) {
        return ResponseEntity.ok(memberService.createGalleryImage(request));
    }

    @GetMapping("/get-all-users")
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        return ResponseEntity.ok(memberService.getAllUsers());
    }
}
