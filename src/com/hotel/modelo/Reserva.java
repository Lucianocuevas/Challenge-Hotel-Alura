package com.hotel.modelo;

import java.sql.Date;

public class Reserva {
	
	private Integer id;
	private Date fechaEntrada;
	private Date fechaSalida;	
	private float valor;
	private String formaDePago;

	public Reserva(Date fechaEntrada, Date fechaSalida, String formaDePago, float valor) {
			
		this.fechaEntrada = fechaEntrada;
		this.fechaSalida = fechaSalida;	
		this.formaDePago = formaDePago;
		this.valor = valor;
	}

	public Reserva(int id) {
		this.id = id;
	}

	public Reserva(int id, Date fechaEntrada, Date fechaSalida, float valor, String formaDePago) {
		this.id = id;
		this.fechaEntrada = fechaEntrada;
		this.fechaSalida = fechaSalida;
		this.valor = valor;
		this.formaDePago = formaDePago;
	}

	public Reserva(int id, String fechaEntrada, String fechaSalida, float valor, String formaDePago) {
		this.id = id;
		this.fechaEntrada = java.sql.Date.valueOf(fechaEntrada);
		this.fechaSalida = java.sql.Date.valueOf(fechaSalida);
		this.valor = valor;
		this.formaDePago = formaDePago;
	}

	public Integer getId() {
		return id;
	}

	public Date getFechaEntrada() {
		return fechaEntrada;
	}

	public Date getFechaSalida() {
		return fechaSalida;
	}

	public float getValor() {
		return valor;
	}

	public String getFormaDePago() {
		return formaDePago;
	}

	public void setId(int int1) {
		this.id = id;
		
	}


}
