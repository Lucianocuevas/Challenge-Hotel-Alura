package com.hotel.controller;

import java.util.List;

import com.hotel.DAO.ReservaDAO;
import com.hotel.factory.ConnectionFactory;
import com.hotel.modelo.Reserva;

public class ReservaController {
	
	private ReservaDAO reservaDAO;
		

	public ReservaController() {
		
		this.reservaDAO = new ReservaDAO(new ConnectionFactory().conectar());
	}
	
	public Integer guardar(Reserva reserva) {
		return reservaDAO.Reservar(reserva);
	}

	public List<Reserva> Listar() {
		// TODO Auto-generated method stub
		return reservaDAO.Listar();
	}

	public int Eliminar(String id) {
		
		return reservaDAO.Eliminar(id);
	}
	
	public int Modificar(Reserva reserva) {
		return reservaDAO.Modificar(reserva);
	}

	public List<Reserva> ListaPersonalizada(int id) {
	
		return reservaDAO.listaPersonalizada(id);
	}

	
	

}
