package com.hotel.controller;

import java.util.List;

import com.hotel.DAO.HuespedDAO;
import com.hotel.factory.ConnectionFactory;
import com.hotel.modelo.Huesped;

public class HuespedesController {
	
	private HuespedDAO huespedDAO;

	public HuespedesController() {
		var factory = new ConnectionFactory();
		this.huespedDAO = new HuespedDAO(factory.conectar());
	}	
	
	public void guardar(Huesped huesped) {
		huespedDAO.guardar(huesped);	
		
	}

	public List<Huesped> Listar() {
		return huespedDAO.ListarHuespedes();
	}

	public int modificar(Huesped huesped) {
		return huespedDAO.modificar(huesped);
		
	}
	
	public int Eliminar(String id) {
		
		return huespedDAO.eliminar(id);
	}

	public List<Huesped> listaPersonalizada(String buscar) {
		
		return huespedDAO.listaPersonalizada(buscar);
	}
	
	/*public List<Huesped> Listar(Hues){
		return HuespedDAO.l
	}*/

}
