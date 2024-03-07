package com.project.teamhub360.controller;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<List<Jugador>> findAll(){
        return new ResponseEntity<>(jugadorService.findAll(), HttpStatus.OK);
    } 

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id){

        if(!jugadorService.existsById(id)){
            Map<String, List<String>> errors = Collections.singletonMap("errors", Collections.singletonList("El ID proporcionado no existe."));
            return new ResponseEntity<>(errors, HttpStatus.NOT_FOUND);
        }

        Jugador jugador = jugadorService.findById(id).get();
        return new ResponseEntity<>(jugador, HttpStatus.OK);

    }


    @PostMapping
    public ResponseEntity<?> save(@RequestBody @Valid JugadorDTO jugadorDTO){

        if (jugadorService.existsByDni(jugadorDTO.getDni())) {
            Map<String, List<String>> errors = Collections.singletonMap("errors", Collections.singletonList("El DNI proporcionado ya existe."));
            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        }

        Jugador jugador = new Jugador(jugadorDTO.getNombre(), jugadorDTO.getApellidos(), jugadorDTO.getDni(), jugadorDTO.getFechaNac());
        jugadorService.save(jugador);
        return new ResponseEntity<>(jugador, HttpStatus.OK);

    }

    //TODO
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody JugadorDTO jugadorDTO){
        if(jugadorService.existsById(id)){ 

            if(jugadorService.existsByDni(jugadorDTO.getDni()) && jugadorService.findByDni(jugadorDTO.getDni()).get().getId()!= id){
            Map<String, List<String>> errors = Collections.singletonMap("errors", Collections.singletonList("El DNI del jugador proporcionado no coincide."));
            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
            }

            Jugador jugador = jugadorService.findById(id).get();
            jugador.setNombre(jugadorDTO.getNombre());
            jugador.setApellidos(jugadorDTO.getApellidos());
            jugador.setDni(jugadorDTO.getDni());
            jugador.setFechaNac(jugadorDTO.getFechaNac());
            return new ResponseEntity<>(jugador, HttpStatus.OK);

        }else{
            Map<String, List<String>> errors = Collections.singletonMap("errors", Collections.singletonList("El ID del jugador proporcionado no existe."));
            return new ResponseEntity<>(errors, HttpStatus.NOT_FOUND);
        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        if(!jugadorService.existsById(id)){
            Map<String, List<String>> errors = Collections.singletonMap("errors", Collections.singletonList("El ID del jugador proporcionado no existe."));
            return new ResponseEntity<>(errors, HttpStatus.NOT_FOUND);
        }

        jugadorService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);

    }
}
