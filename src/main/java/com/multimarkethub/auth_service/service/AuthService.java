package com.multimarkethub.auth_service.service;

import com.multimarkethub.auth_service.client.UserClient;
import com.multimarkethub.auth_service.dto.LoginRequest;
import com.multimarkethub.auth_service.dto.LoginResponse;
import com.multimarkethub.auth_service.dto.LoginResponseJwt;
import com.multimarkethub.auth_service.utility.JwtUtil;
import com.multimarkethub.auth_service.utility.ValidatePassword;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private static final Logger log = LoggerFactory.getLogger(AuthService.class);
    private UserClient userClient;
    private ValidatePassword validatePassword;
    private JwtUtil jwtUtil;

    public AuthService(UserClient userClient, ValidatePassword validatePassword, JwtUtil jwtUtil) {
        this.userClient = userClient;
        this.validatePassword = validatePassword;
        this.jwtUtil= jwtUtil;
    }


    public LoginResponseJwt validateUser(LoginRequest loginRequest) {
        LoginResponse loginResponse = userClient.getUserDeatilsByEmial(loginRequest.getEmail());
        LoginResponseJwt loginResponseJwt = new LoginResponseJwt();
        loginResponseJwt.setEmail(loginResponse.getEmail());
        loginResponseJwt.setRole(loginResponse.getRole());
        loginResponseJwt.setEmail(loginResponse.getEmail());
        loginResponseJwt.setFirstName(loginResponse.getFirstName());

        if (loginResponse != null) {
            log.info("Getting Details" + loginResponse);
            if (validatePassword.validateHashPassword(loginRequest.getPassword(), loginResponse.getPassword())) {
                String token = jwtUtil.generateToken(loginResponse);
                loginResponseJwt.setToken(token);
            }
        }
        return loginResponseJwt;
    }
}
