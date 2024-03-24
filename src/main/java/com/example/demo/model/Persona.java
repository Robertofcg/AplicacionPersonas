package com.example.demo.model;

import java.time.LocalDate;
import java.time.Period;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@Entity
@Table(name = "persona")
public class Persona {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idPersona;
	@Column(name = "nombre", length = 60, nullable = false, unique = true)
	private String nombre;
	@Column(name = "apellidos", length = 60, nullable = false)
	private String apellidos;	
	@Column(name = "año_nacimiento", nullable = false)
    @Digits(integer = 4, fraction = 0, message = "El año de nacimiento debe contener 4 dígitos")
    private int añoNacimiento;
	@Column(name = "direccion", length = 200, nullable = false, unique = true)
	private String direccion;
	@Column(name = "telefono", nullable = false)
    @Digits(integer = 10, fraction = 0, message = "El teléfono debe contener solo números enteros")
    @Pattern(regexp = "\\d{10}", message = "El teléfono debe tener 10 dígitos")
	private String telefono;
	@Column(name = "correo", length = 60, nullable = false)
	private String correo;
	@Column(name = "edad", nullable = false)
	private int edad ;
	
	public Persona() {
		
	};
	
	public Persona(String nombre, String apellidos, int añoNacimiento, String direccion,
			String telefono, String correo, int edad) {
		super();
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.añoNacimiento = añoNacimiento;
		this.direccion = direccion;
		this.telefono = telefono;
		this.correo = correo;
		calcularEdad();
	}
	
	public void calcularEdad() {
        LocalDate fechaNacimiento = LocalDate.of(añoNacimiento, 1, 1); // Suponiendo que solo se tiene el año de nacimiento 
        LocalDate fechaActual = LocalDate.now(); // Obteniendo la fecha actual
        Period periodo = Period.between(fechaNacimiento, fechaActual);
        this.edad = periodo.getYears();
    }

	public long getIdPersona() {
		return idPersona;
	}

	public void setIdPersona(long idPersona) {
		this.idPersona = idPersona;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public int getAñoNacimiento() {
		return añoNacimiento;
	}

	public void setAñoNacimiento(int añoNacimiento) {
        this.añoNacimiento = añoNacimiento;
        calcularEdad();
    }

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}
}
