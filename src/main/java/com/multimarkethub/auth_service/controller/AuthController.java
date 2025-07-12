package com.multimarkethub.auth_service.controller;

import com.multimarkethub.auth_service.dto.LoginRequest;
import com.multimarkethub.auth_service.dto.LoginResponse;
import com.multimarkethub.auth_service.dto.LoginResponseJwt;
import com.multimarkethub.auth_service.service.AuthService;
import com.multimarkethub.auth_service.utility.JwtUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthController {

    private static final Logger log = LoggerFactory.getLogger(AuthController.class);
    AuthService authService;

    private JwtUtil jwtUtil;


    @Autowired
    public  AuthController(AuthService authService,JwtUtil jwtUtil){
        this.authService = authService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/user/login")
    public ResponseEntity<LoginResponseJwt> login(@RequestBody LoginRequest loginRequest){
        log.info("Auth-Controller");
        LoginResponseJwt loginResponse = authService.validateUser(loginRequest);
       return ResponseEntity.status(HttpStatus.ACCEPTED).body(loginResponse);
    }

    @GetMapping("/extract-username")
    public ResponseEntity<String> extractUsername(@RequestHeader("Authorization") String authHeader) {
        try {
            System.out.println("I am in 41");
            String token = authHeader.startsWith("Bearer ") ? authHeader.substring(7) : authHeader;
            System.out.println("I am in 41"+token);
            String username = jwtUtil.extractUserName(token);
            System.out.println("I am in 45"+username);

            return ResponseEntity.ok(username);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid token");
        }
    }
}
