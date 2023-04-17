package com.hotel.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.hotel.modelo.Huesped;

public class HuespedDAO {

	private Connection con;
	
	public HuespedDAO (Connection con) {
		this.con = con;
	}
	
	public void guardar(Huesped huesped) {
		try {
			final PreparedStatement statement = con.prepareStatement("INSERT INTO huespedes(nombre, apellido, "
					+ "fecha_nacimiento, nacionalidad, telefono, "
					+ "id_reserva) VALUES(?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
			try (statement){				
				statement.setString(1, huesped.getNombre());
				statement.setString(2, huesped.getApellido());
				statement.setDate(3, huesped.getFechaNacimiento());
				statement.setString(4, huesped.getNacionalidad());
				statement.setString(5, huesped.getTelefono());
				statement.setInt(6, huesped.getIdReserva());
				
				statement.execute();
				
				final ResultSet rs = statement.getGeneratedKeys();
				try(rs){
					while(rs.next()) {
						huesped.setId(rs.getString(1));
					}
				}
			}
			
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
	public List<Huesped> ListarHuespedes(){
		List<Huesped> huesped = new ArrayList<>();
		
		try {
			final PreparedStatement st = con.prepareStatement("SELECT ID, NOMBRE, APELLIDO, FECHA_NACIMIENTO,"
					+ " NACIONALIDAD, TELEFONO, ID_RESERVA FROM HUESPEDES");
			try(st){
				final ResultSet result = st.executeQuery();
				try(result){
					while(result.next()) {
						huesped.add(new Huesped(result.getString("ID"),result.getString("NOMBRE"),
								result.getString("APELLIDO"), result.getDate("FECHA_NACIMIENTO"),
								result.getString("NACIONALIDAD"), result.getString("TELEFONO"), result.getInt("ID_RESERVA")));
					}
				}
			}
		}catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return huesped;
		
	}
	public List<Huesped> listaPersonalizada(String buscar) {
		buscar = "%"+buscar+"%";
		List<Huesped> huesped = new ArrayList<>();
		try {
			final PreparedStatement st = con.prepareStatement("SELECT ID, NOMBRE, APELLIDO, FECHA_NACIMIENTO,"
					+ " NACIONALIDAD, TELEFONO, ID_RESERVA FROM HUESPEDES WHERE APELLIDO LIKE ?");
			try(st){
				st.setString(1, buscar);
				final ResultSet result = st.executeQuery();
				try(result){
					while(result.next()) {
						huesped.add(new Huesped(result.getString("ID"),result.getString("NOMBRE"),
								result.getString("APELLIDO"), result.getDate("FECHA_NACIMIENTO"),
								result.getString("NACIONALIDAD"), result.getString("TELEFONO"), result.getInt("ID_RESERVA")));
					}
				}
			}
		}catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return huesped;
	}

	public int modificar(Huesped huesped) {
		try {
			final PreparedStatement st = con.prepareStatement("UPDATE HUESPEDES SET NOMBRE = ?, "
					+ "APELLIDO = ?, FECHA_NACIMIENTO = ?, NACIONALIDAD = ?,  TELEFONO = ?,"
					+ " ID_RESERVA = ? WHERE ID = ?" );
			try(st){
							
				st.setString(1, huesped.getNombre());
				st.setString(2, huesped.getApellido());
				st.setDate(3, huesped.getFechaNacimiento());
				st.setString(4, huesped.getNacionalidad());
				st.setString(5, huesped.getTelefono());
				st.setInt(6, huesped.getIdReserva());
				st.setString(7, huesped.getId());
				st.execute();
				int updateCount = st.getUpdateCount();
				return updateCount;
			}
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}	
		
	}
	public int eliminar(String id) {
		
		try {
			final PreparedStatement st = con.prepareStatement("DELETE FROM HUESPEDES WHERE ID = ?");
			
			try(st){
				st.setString(1, id);
				st.execute();
				
				int updateCount = st.getUpdateCount();
				
				return updateCount;
			}
			
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
		
	}

	
}
