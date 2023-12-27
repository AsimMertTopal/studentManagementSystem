package com.AsimMertTopal.studentManagementSystem.controller.auth;

import com.AsimMertTopal.studentManagementSystem.dto.JwtAuthenticationResponse;
import com.AsimMertTopal.studentManagementSystem.dto.SignUpRequest;
import com.AsimMertTopal.studentManagementSystem.dto.SigninRequest;
import com.AsimMertTopal.studentManagementSystem.entities.Admin;
import com.AsimMertTopal.studentManagementSystem.security.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;


    @PostMapping("/signup")
    public ResponseEntity<JwtAuthenticationResponse> signup(@RequestBody SignUpRequest request) {
        return ResponseEntity.ok(authenticationService.signup(request));
    }

    @PostMapping("/signin")
    public ResponseEntity<JwtAuthenticationResponse> signin(@RequestBody SigninRequest request) {
        return ResponseEntity.ok(authenticationService.signin(request));
    }

    @PostMapping("/signupAdmin")
    public ResponseEntity<JwtAuthenticationResponse> signupAdmın(@RequestBody Admin admin){
        return ResponseEntity.ok(authenticationService.signupAdmın(admin));
    }
    @PostMapping("/signinAdmin")
    public ResponseEntity<JwtAuthenticationResponse> loginAdmın(Admin admin){
        return ResponseEntity.ok(authenticationService.signinAdmın(admin));
    }

}
