package com.project.teamhub360.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.teamhub360.entity.Jugador;


@Repository
public interface JugadorRepository extends JpaRepository<Jugador, Long>{

    Optional<Jugador> findByDni(String dni);
    boolean existsByDni(String dni);

}
