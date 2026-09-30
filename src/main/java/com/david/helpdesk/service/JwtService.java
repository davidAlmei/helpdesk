package com.david.helpdesk.service;

import com.david.helpdesk.model.User;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;

@Service
public class JwtService {

    private final SecretKey secretKey;

    public JwtService() {
        this.secretKey = Jwts.SIG.HS256.key().build();
    }

    public String generateToken(User user) {

        return Jwts.builder().subject(user.getEmail()).signWith(secretKey).compact();
    }
}