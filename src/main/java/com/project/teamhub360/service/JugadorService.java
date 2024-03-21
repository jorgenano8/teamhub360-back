package com.project.teamhub360.service;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.project.teamhub360.dto.JugadorDTO;
import com.project.teamhub360.entity.Jugador;
import com.project.teamhub360.repository.JugadorRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class JugadorService {

    @Autowired
    JugadorRepository jugadorRepository;

    public ResponseEntity<List<Jugador>> findAll() {
        return new ResponseEntity<>(jugadorRepository.findAll(), HttpStatus.OK);
    }

    public ResponseEntity<?> findById(Long id) {
        if (!existsById(id)) {
            Map<String, List<String>> errors = Collections.singletonMap("errors",
                    Collections.singletonList("El ID proporcionado no existe."));
            return new ResponseEntity<>(errors, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(jugadorRepository.findById(id), HttpStatus.OK);
    }

    public Optional<Jugador> findByDni(String dni) {
        return jugadorRepository.findByDni(dni);
    }

    public boolean existsById(Long id) {
        return jugadorRepository.existsById(id);
    }

    public boolean existsByDni(String dni) {
        return jugadorRepository.existsByDni(dni);
    }

    public ResponseEntity<?> save(JugadorDTO jugadorDTO) {
        if (existsByDni(jugadorDTO.getDni())) {
            Map<String, List<String>> errors = Collections.singletonMap("errors",
                    Collections.singletonList("El DNI proporcionado ya pertenece a un jugador/a."));
            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        }

        Jugador jugador = new Jugador(jugadorDTO.getNombre(), jugadorDTO.getApellidos(), jugadorDTO.getDni(),
                jugadorDTO.getFechaNac());
        jugadorRepository.save(jugador);
        return new ResponseEntity<>(jugador, HttpStatus.OK);
    }

    public ResponseEntity<?> update(Long id, JugadorDTO jugadorDTO) {
        if (existsById(id)) {

            if (existsByDni(jugadorDTO.getDni())
                    && findByDni(jugadorDTO.getDni()).get().getId() != id) {
                Map<String, List<String>> errors = Collections.singletonMap("errors",
                        Collections.singletonList("El DNI proporcionado ya pertenece a un jugador/a."));
                return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
            }

            if (!jugadorDTO.getFechaNac().before(new Date())) {
                Map<String, List<String>> errors = Collections.singletonMap("errors",
                        Collections.singletonList("La fecha de nacimiento proporcionada debe ser anterior a hoy"));
                return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
            }

            Jugador jugador = jugadorRepository.findById(id).get();
            jugador.setNombre(jugadorDTO.getNombre());
            jugador.setApellidos(jugadorDTO.getApellidos());
            jugador.setDni(jugadorDTO.getDni());
            jugador.setFechaNac(jugadorDTO.getFechaNac());
            jugadorRepository.save(jugador);
            return new ResponseEntity<>(jugador, HttpStatus.OK);

        } else {
            Map<String, List<String>> errors = Collections.singletonMap("errors",
                    Collections.singletonList("El ID proporcionado no pertenece a ningún jugador/a."));
            return new ResponseEntity<>(errors, HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<?> deleteById(Long id) {
        if (!existsById(id)) {
            Map<String, List<String>> errors = Collections.singletonMap("errors",
                    Collections.singletonList("El ID proporcionado no pertenece a ningún jugador/a."));
            return new ResponseEntity<>(errors, HttpStatus.NOT_FOUND);
        }

        jugadorRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
