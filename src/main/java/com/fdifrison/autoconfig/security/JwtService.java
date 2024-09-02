package com.fdifrison.autoconfig.security;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class JwtService {

    public UUID getUserId() {
        return UUID.fromString(getJwt().getSubject());
    }

    public Jwt getJwt() {
        return (Jwt) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
