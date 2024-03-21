package com.project.teamhub360.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.teamhub360.dto.JugadorDTO;
import com.project.teamhub360.entity.Jugador;
import com.project.teamhub360.service.JugadorService;

import jakarta.validation.Valid;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/jugador")
public class JugadorController {

    @Autowired
    JugadorService jugadorService;

    @GetMapping
    public ResponseEntity<List<Jugador>> findAll() {
        return jugadorService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        return jugadorService.findById(id);
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody @Valid JugadorDTO jugadorDTO) {
        return jugadorService.save(jugadorDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody JugadorDTO jugadorDTO) {
        return jugadorService.update(id, jugadorDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        return jugadorService.deleteById(id);
    }
}
