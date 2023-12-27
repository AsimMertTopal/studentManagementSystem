package com.AsimMertTopal.studentManagementSystem.controller;

import com.AsimMertTopal.studentManagementSystem.dto.JwtAuthenticationResponse;
import com.AsimMertTopal.studentManagementSystem.entities.Admin;
import com.AsimMertTopal.studentManagementSystem.security.AuthenticationService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
@AllArgsConstructor
@NoArgsConstructor
public class AdminController {
    @Autowired
    private AuthenticationService   authenticationService;


}
