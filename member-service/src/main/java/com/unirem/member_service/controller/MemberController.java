package com.unirem.member_service.controller;

import com.unirem.member_service.DTO.ProjectRequest;
import com.unirem.member_service.DTO.ProjectResponse;
import com.unirem.member_service.DTO.UserDTO;
import com.unirem.member_service.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpResponse;

@RestController
@RequestMapping("/member")
public class MemberController {
    @Autowired
    private MemberService memberService;

    @PostMapping("/create-project")
    public ResponseEntity<ProjectResponse> createProject(@RequestBody ProjectRequest request) {
        return ResponseEntity.ok(memberService.createProject(request));
    }

    @PostMapping("/add-user-to-project")
    public ResponseEntity<?> addUserToProject(@RequestParam Long projectId, @RequestBody UserDTO userDTO) {
        return ResponseEntity.ok().build();
    }

    @PostMapping("/approve-project")
    public ResponseEntity<?> approveProject(@RequestParam Long projectId) {
        return ResponseEntity.ok().build();
    }
}
