package com.project.teamhub360.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.teamhub360.entity.Jugador;
import com.project.teamhub360.repository.JugadorRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class JugadorService {

    @Autowired
    JugadorRepository jugadorRepository;

    public List<Jugador> findAll(){
        return jugadorRepository.findAll();
    }

    public Optional<Jugador> findById(Long id){
        return jugadorRepository.findById(id);
    }

    public Optional<Jugador> findByDni(String dni){
        return jugadorRepository.findByDni(dni);
    }

    public boolean existsById(Long id){
        return jugadorRepository.existsById(id);
    }

    public boolean existsByDni(String dni){
        return jugadorRepository.existsByDni(dni);
    }

    public void save(Jugador jugador){
        jugadorRepository.save(jugador);
    }

    public void deleteById(Long id){
        jugadorRepository.deleteById(id);
    }
    
}
