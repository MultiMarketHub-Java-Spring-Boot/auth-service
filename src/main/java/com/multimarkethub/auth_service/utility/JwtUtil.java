package com.multimarkethub.auth_service.utility;

import com.multimarkethub.auth_service.dto.LoginResponse;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {
    private static final Logger log = LoggerFactory.getLogger(JwtUtil.class);
    @Value("${jwt.secret}")
    private  String secret;

    private Key key;

    @PostConstruct
    public void init() {
        key = Keys.hmacShaKeyFor(secret.getBytes());
    }
    
    @Value("${jwt.expiration}")
    private long expiry;

    public  String generateToken(LoginResponse userDeatils){
        return Jwts.builder().setSubject(userDeatils.getEmail())
                .claim("role",userDeatils.getRole())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expiry))
                .signWith(key)
                .compact();
    }


    public String extractUserName(String token){
        log.info("44 extractUserNae");
        System.out.println("Extract user Util "+token);
        Claims body = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
        System.out.println(body);
        return body.getSubject();
    }
}
