package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Persona;

@Repository
public interface PersonaRepository extends JpaRepository<Persona, Long> {

	Optional<Persona> findByNombre(String nombre);
	Optional<Persona> findByDireccion(String direccion);
}
