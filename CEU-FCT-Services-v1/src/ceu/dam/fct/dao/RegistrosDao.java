package ceu.dam.fct.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ceu.dam.fct.modelo.Registro;

public class RegistrosDao {

	public List<Registro> consultarPorUsuario(Connection conn, Long idUsuario) throws SQLException {
		PreparedStatement stmt = null;
		List<Registro> resultado = new ArrayList<Registro>();
		try {
			String sql = "select * from registros where id_usuario = ?";
			stmt = conn.prepareStatement(sql);
			stmt.setLong(1, idUsuario);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Registro registro = new Registro();
				registro.setDescripcion(rs.getString("descripcion"));
				registro.setFecha(rs.getDate("fecha").toLocalDate());
				registro.setIdRegistro(rs.getLong("id_registro"));
				registro.setIdUsuario(idUsuario);
				registro.setNumHoras(rs.getBigDecimal("num_horas"));
				resultado.add(registro);
			}
			return resultado;
		}
		finally {
			try {
				stmt.close();
			}catch(Exception ignore) {}
		}
	}
	
	public void insertar(Connection conn, Registro registro) throws SQLException {
		PreparedStatement stmt = null;
		try {
			String sql = "insert into registros (id_usuario, fecha, num_horas, descripcion) values (?,?,?,?)";
			stmt = conn.prepareStatement(sql);
			stmt.setLong(1, registro.getIdUsuario());
			stmt.setDate(2, Date.valueOf(registro.getFecha()));
			stmt.setBigDecimal(3, registro.getNumHoras());
			stmt.setString(4, registro.getDescripcion());
			stmt.execute();
		}
		finally {
			try {
				stmt.close();
			}catch(Exception ignore) {}
		}
	}

}
