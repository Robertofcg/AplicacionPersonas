package com.example.demo.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.example.aplicacion.personas.model.PersonaModel;
import com.example.demo.Exception.PersonaNotFoundException;
import com.example.demo.model.Persona;
import com.example.demo.repository.PersonaRepository;

public interface PersonaService {

	List<Persona> getPersonas();

	Optional<Persona> getPersona(Long id) throws PersonaNotFoundException;

	void agregarPersona(Persona persona) throws PersonaNotFoundException;

	void actualizarPersona(Long id, Persona personaActualizada) throws PersonaNotFoundException;

	void eliminarPersona(Long id) throws PersonaNotFoundException;
}
