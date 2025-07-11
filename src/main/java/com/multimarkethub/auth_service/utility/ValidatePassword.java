package com.multimarkethub.auth_service.utility;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class ValidatePassword {
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public boolean validateHashPassword(String rawPassword, String hashedPassword){
        return passwordEncoder.matches(rawPassword, hashedPassword);
    }
}



