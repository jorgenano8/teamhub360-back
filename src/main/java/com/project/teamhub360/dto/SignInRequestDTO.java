package com.project.teamhub360.dto;

import jakarta.validation.constraints.NotBlank;

public class SignInRequestDTO {

    @NotBlank
    private String username;

    @NotBlank
    private String contraseña;

    public SignInRequestDTO() {
    }

    public SignInRequestDTO(String username, String contraseña) {
        this.username = username;
        this.contraseña = contraseña;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

}
