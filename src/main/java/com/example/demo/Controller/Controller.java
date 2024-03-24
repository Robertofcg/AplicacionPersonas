package com.example.demo.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.example.demo.Service.PersonaService;
import com.example.demo.model.Persona;

import jakarta.transaction.Transactional;


@RestController
@RequestMapping(path = "api/personas")
public class Controller {
	
	@Autowired
	private PersonaService personaService;

	@Transactional
	@GetMapping
	public List<Persona> getAll(){
		return personaService.getPersonas();
	}
	
    @Transactional
	@GetMapping("/{idPersona}")
	public Optional<Persona> getPersonById(@PathVariable("idPersona") Long idPersona){
		return personaService.getPersonas(idPersona);
	}

    @Transactional	
    @PostMapping("/crear")
	public ResponseEntity<?> crearPersona(@RequestBody Persona persona) {		
		personaService.Agregar(persona);
		return ResponseEntity.ok("Persona creada exitosamente");
	}

    @Transactional
    @PutMapping("actualizar/{id}")
	public ResponseEntity<String> actualizarPersona(@PathVariable Long id, @RequestBody Persona persona) {
		personaService.Actualizar(id, persona);
		return ResponseEntity.ok("Persona actualizada exitosamente");
	}

    @Transactional
    @DeleteMapping("eliminar/{idPersona}")
    public ResponseEntity<String> delete (@PathVariable("idPersona") Long idPersona){
		personaService.Eliminar(idPersona);
		return ResponseEntity.ok("Persona eliminada exitosamente");

	}

}
