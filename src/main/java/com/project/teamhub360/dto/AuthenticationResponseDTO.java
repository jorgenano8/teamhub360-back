package com.project.teamhub360.dto;

public class AuthenticationResponseDTO {
    private String jwt;

    public AuthenticationResponseDTO() {
    }

    public AuthenticationResponseDTO(String jwt) {
        this.jwt = jwt;
    }

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }

}
