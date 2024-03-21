package com.project.teamhub360.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.teamhub360.dto.AuthenticationResponseDTO;
import com.project.teamhub360.dto.SignInRequestDTO;
import com.project.teamhub360.dto.SignUpRequestDTO;
import com.project.teamhub360.service.UsuarioService;

import jakarta.validation.Valid;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    UsuarioService usuarioService;

    @PostMapping("/signup")
    public ResponseEntity<AuthenticationResponseDTO> signUp(@RequestBody @Valid SignUpRequestDTO signUpRequestDTO) {
        return new ResponseEntity<>(usuarioService.signUp(signUpRequestDTO), HttpStatus.OK);
    }

    @PostMapping("/signin")
    public ResponseEntity<AuthenticationResponseDTO> signIn(
            @RequestBody @Valid SignInRequestDTO signInRequestDTO) {
        return new ResponseEntity<>(usuarioService.signIn(signInRequestDTO), HttpStatus.OK);

    }

}
