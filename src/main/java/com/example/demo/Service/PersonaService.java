package com.example.demo.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.example.demo.Exception.PersonaNotFoundException;
import com.example.demo.model.Persona;
import com.example.demo.repository.PersonaRepository;

@Service
public class PersonaService {

	@Autowired
	PersonaRepository personaRepository;

	public List<Persona> getPersonas(){
		return personaRepository.findAll();
	}

	public Optional<Persona> getPersonas(Long id){
		Optional<Persona> personaOptional = personaRepository.findById(id);
		if (personaOptional.isPresent()) {
			return personaRepository.findById(id);
		} else {
			throw new PersonaNotFoundException("No se encontró la persona con el ID proporcionado: " + id);
		}

	}

	public void Agregar(Persona persona) {
		Optional<Persona> personaConMismoNombre = personaRepository.findByNombre(persona.getNombre());
		Optional<Persona> personaConMismaDireccion = personaRepository.findByDireccion(persona.getDireccion());

		if (personaConMismoNombre.isPresent()) {
			throw new PersonaNotFoundException("El nombre '" + persona.getNombre() + "' ya está en uso");
		}

		if (personaConMismaDireccion.isPresent()) {
			throw new PersonaNotFoundException("La dirección '" + persona.getDireccion() + "' ya está en uso");
		}

		personaRepository.save(persona);
	}


	public void Actualizar(Long id, Persona personaActualizada) {
		Optional<Persona> nombreExistente = personaRepository.findByNombre(personaActualizada.getNombre());
		Optional<Persona> optionalPersona = personaRepository.findById(id);
		if (nombreExistente.isPresent()) { // Comprueba si existe una persona con el nombre
			throw new PersonaNotFoundException("El nombre '" + personaActualizada.getNombre() + "' ya está en uso");
		}
		if (optionalPersona.isPresent()) {
			Persona persona = optionalPersona.get();
			persona.setNombre(personaActualizada.getNombre());
			persona.setApellidos(personaActualizada.getApellidos());
			persona.setAñoNacimiento(personaActualizada.getAñoNacimiento());
			persona.setDireccion(personaActualizada.getDireccion());
			persona.setTelefono(personaActualizada.getTelefono());
			persona.setCorreo(personaActualizada.getCorreo());
			persona.setEdad(personaActualizada.getEdad());
			try {
				personaRepository.save(persona); // Guarda la persona actualizada
			} catch (Exception e) {
				throw new RuntimeException("Error al actualizar la persona: " + e.getMessage());
			}
		}else {
			throw new PersonaNotFoundException("No se encontró la persona con el ID proporcionado: " + id);
		}
	}

	public void Eliminar(Long id) {
		try {
            personaRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new PersonaNotFoundException("No se encontró la persona con el ID proporcionado: " + id);
        }	}


}
