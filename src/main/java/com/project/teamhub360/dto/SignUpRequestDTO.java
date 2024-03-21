package com.project.teamhub360.dto;

public class SignUpRequestDTO {

    private String username;
    private String email;
    private String contraseña;
    private String nombreClub;

    public SignUpRequestDTO() {
    }

    public SignUpRequestDTO(String username, String email, String contraseña, String nombreClub) {
        this.username = username;
        this.email = email;
        this.contraseña = contraseña;
        this.nombreClub = nombreClub;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public String getNombreClub() {
        return nombreClub;
    }

    public void setNombreClub(String nombreClub) {
        this.nombreClub = nombreClub;
    }

}
