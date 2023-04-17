package com.hotel.modelo;

import java.sql.Date;

public class Huesped {	
	
	private String id;
	private String nombre;
	private String apellido;
	private Date fechaNacimiento;
	private String Nacionalidad;
	private String telefono;
	private Integer idReserva;
	
	public Huesped( String nombre, String apellido, Date fechaNacimiento, String nacionalidad, String telefono, Integer idReserva) {
		
		this.nombre = nombre;
		this.apellido = apellido;
		this.fechaNacimiento = fechaNacimiento;
		this.Nacionalidad = nacionalidad;		
		this.telefono = telefono;
		this.idReserva = idReserva;
	}
	public Huesped(String id, String nombre, String apellido, Date fechaNacimiento, String nacionalidad, String telefono, Integer idReserva) {
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.fechaNacimiento = fechaNacimiento;
		this.Nacionalidad = nacionalidad;		
		this.telefono = telefono;
		this.idReserva = idReserva;
	}
	public Huesped(String id, String nombre, String apellido, String fechaNacimiento, String nacionalidad, String telefono, Integer idReserva) {
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.fechaNacimiento = java.sql.Date.valueOf(fechaNacimiento);
		this.Nacionalidad = nacionalidad;		
		this.telefono = telefono;
		this.idReserva = idReserva;
	}
	/*public Huesped(String id, String nombre, String apellido, String fechaNacimiento, String nacionalidad, String telefono, Integer idReserva) {
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.fechaNacimiento = java.sql.Date.valueOf(fechaNacimiento);
		this.Nacionalidad = nacionalidad;		
		this.telefono = telefono;
		this.idReserva = idReserva;
	}*/
	public String getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public String getNacionalidad() {
		return Nacionalidad;
	}

	public String getTelefono() {
		return telefono;
	}

	public Integer getIdReserva() {
		return idReserva;
	}

	@Override
	public String toString() {
		
		return String.format(
				"%s %s %s %s %d %d ",
				this.id, this.nombre, this.apellido,this.Nacionalidad,this.telefono,this.idReserva);
	}
	public void setId(String id) {
		this.id = id;
	}

}
