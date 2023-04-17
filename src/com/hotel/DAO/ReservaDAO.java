package com.hotel.DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.hotel.factory.ConnectionFactory;
import com.hotel.modelo.Huesped;
import com.hotel.modelo.Reserva;

public class ReservaDAO {

	private Connection con;
	private Integer resultado;

	public ReservaDAO(Connection con) {
		this.con = con;
	}

	public Integer Reservar(Reserva reserva) {
		final Connection con = new ConnectionFactory().conectar();

		try (con) {
			final PreparedStatement statement = con.prepareStatement("INSERT INTO reservas(fecha_entrada, "
					+ "fecha_salida, valor, forma_de_pago) " + "VALUES (?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
			try (statement) {
				Guardando(reserva, statement);

			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		return resultado;
	}

	private Integer Guardando(Reserva reserva, PreparedStatement statement) throws SQLException {

		statement.setDate(1, reserva.getFechaEntrada());
		statement.setDate(2, reserva.getFechaSalida());
		statement.setFloat(3, reserva.getValor());
		statement.setString(4, reserva.getFormaDePago());
		statement.execute();

		final ResultSet rs = statement.getGeneratedKeys();
		try (rs) {
			while (rs.next()) {
				resultado = (rs.getInt(1));
			}
		}
		return resultado;
	}

	public List<Reserva> Listar() {
		List<Reserva> reservas = new ArrayList<>();

		try {
			final PreparedStatement st = con.prepareStatement(
					"SELECT ID, FECHA_ENTRADA, FECHA_SALIDA, " + "VALOR, FORMA_DE_PAGO FROM RESERVAS",
					Statement.RETURN_GENERATED_KEYS);
			try (st) {
				final ResultSet rs = st.executeQuery();
				try (rs) {
					while (rs.next()) {
						reservas.add(new Reserva(rs.getInt(1), rs.getDate(2), rs.getDate(3), rs.getFloat(4),
								rs.getString(5)));
					}
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		return reservas;
	}

	public int Modificar(Reserva reserva) {
		try {
			final PreparedStatement st = con.prepareStatement("UPDATE RESERVAS SET FECHA_ENTRADA = ?, "
					+ "SET FECHA_SALIDA = ?, VALOR = ?, FORMA_DE_PAGO = ? WHERE ID = ?" );
			try(st){
							
				st.setDate(1, reserva.getFechaEntrada());
				st.setDate(2, reserva.getFechaSalida());
				st.setFloat(3, reserva.getValor());
				st.setString(4, reserva.getFormaDePago());				
				st.setInt(5, reserva.getId());
				
				st.execute();
				int updateCount = st.getUpdateCount();
				return updateCount;
			}
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}	
		
	}
	public int Eliminar(String id) {
		
		try {
			final PreparedStatement st = con.prepareStatement("DELETE FROM RESERVAS WHERE ID = ?");
			
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

	public List<Reserva> listaPersonalizada(int id) {
		
		List<Reserva> reservaPersonalizada = new ArrayList<>();
		
		try {
			final PreparedStatement st = con.prepareStatement(
					"SELECT ID, FECHA_ENTRADA, FECHA_SALIDA, " + 
					"VALOR, FORMA_DE_PAGO FROM RESERVAS WHERE ID LIKE ?",
					Statement.RETURN_GENERATED_KEYS);
			try (st) {
				
				st.setInt(1, id);
				
				final ResultSet rs = st.executeQuery();
				try (rs) {
					while (rs.next()) {
						reservaPersonalizada.add(new Reserva(rs.getInt(1), rs.getDate(2), rs.getDate(3), rs.getFloat(4),
								rs.getString(5)));
					}
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		return reservaPersonalizada;

	}
}
