package com.ophthalmologist.clinic.security;

import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class JwtResponse {
    private String token;
    private String username;
    private Integer userId;
    private Collection<? extends GrantedAuthority> authorities;

    public JwtResponse(String accessToken, String username, Integer userId, Collection<? extends GrantedAuthority> authorities) {
        this.token = accessToken;
        this.username = username;
        this.userId = userId;
        this.authorities = authorities;
    }

    public String getToken() {
        return token;
    }


    public String getUsername() {
        return username;
    }

    public Integer getAccountId() {
        return userId;
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }
}
