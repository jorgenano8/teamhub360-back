package com.project.teamhub360.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.teamhub360.dto.AuthenticationResponseDTO;
import com.project.teamhub360.dto.SignInRequestDTO;
import com.project.teamhub360.dto.SignUpRequestDTO;
import com.project.teamhub360.entity.Rol;
import com.project.teamhub360.entity.Usuario;
import com.project.teamhub360.repository.UsuarioRepository;

@Service
public class UsuarioService {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtService jwtService;

    @Autowired
    AuthenticationManager authenticationManager;

    public AuthenticationResponseDTO signUp(SignUpRequestDTO signUpRequestDTO) {
        Usuario usuario = new Usuario(signUpRequestDTO.getUsername(), signUpRequestDTO.getEmail(),
                passwordEncoder.encode(signUpRequestDTO.getContraseña()), signUpRequestDTO.getNombreClub(),
                Rol.USUARIO);
        usuarioRepository.save(usuario);

        String jwt = jwtService.generateToken(usuario);
        return new AuthenticationResponseDTO(jwt);
    }

    public AuthenticationResponseDTO signIn(SignInRequestDTO signInRequestDTO) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                signInRequestDTO.getUsername(), signInRequestDTO.getContraseña()));

        Usuario usuario = usuarioRepository.findByUsername(signInRequestDTO.getUsername()).orElseThrow();
        String jwt = jwtService.generateToken(usuario);

        return new AuthenticationResponseDTO(jwt);
    }
}
