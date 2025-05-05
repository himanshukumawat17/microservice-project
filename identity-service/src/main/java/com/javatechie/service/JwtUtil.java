// src/main/java/com/javatechie/service/JwtUtil.java
package com.javatechie.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

public class JwtUtil {
    private static final String SECRET = "kL9zT*7pQa!jVx#3Wr6L^t0ZsM@8eF2b"; // Store securely

    public static String generateToken(String subject, long expirySeconds) {
        return Jwts.builder()
                .setSubject(subject)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expirySeconds * 1000))
                .signWith(SignatureAlgorithm.HS256, SECRET)
                .compact();
    }
}
