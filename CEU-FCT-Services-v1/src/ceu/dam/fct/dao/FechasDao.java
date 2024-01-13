package ceu.dam.fct.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ceu.dam.fct.modelo.Fecha;

public class FechasDao {

	public List<Fecha> consultar(Connection conn, Integer año, Integer evaluacion) throws SQLException {
		PreparedStatement stmt = null;
		List<Fecha> resultado = new ArrayList<Fecha>();
		try {
			String sql = "select * from fechas where año = ? and evaluacion = ? ORDER BY fecha ";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, año);
			stmt.setInt(2, evaluacion);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Fecha fecha = new Fecha();
				fecha.setAño(año);
				fecha.setDisponibilidad(rs.getBoolean("disponibilidad"));
				fecha.setEvaluacion(evaluacion);
				fecha.setFecha(rs.getDate("fecha").toLocalDate());
				resultado.add(fecha);
			}
			return resultado;
		}
		finally {
			try {
				stmt.close();
			}catch(Exception ignore) {}
		}
	}

}
