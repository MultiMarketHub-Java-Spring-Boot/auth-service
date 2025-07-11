package com.multimarkethub.auth_service.controller;

import com.multimarkethub.auth_service.dto.LoginRequest;
import com.multimarkethub.auth_service.dto.LoginResponse;
import com.multimarkethub.auth_service.dto.LoginResponseJwt;
import com.multimarkethub.auth_service.service.AuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    private static final Logger log = LoggerFactory.getLogger(AuthController.class);
    AuthService authService;

    @Autowired
    public  AuthController(AuthService authService){
        this.authService = authService;
    }

    @PostMapping("/user/login")
    public ResponseEntity<LoginResponseJwt> login(@RequestBody LoginRequest loginRequest){
        log.info("Auth-Controller");
        LoginResponseJwt loginResponse = authService.validateUser(loginRequest);
       return ResponseEntity.status(HttpStatus.ACCEPTED).body(loginResponse);
    }

}
