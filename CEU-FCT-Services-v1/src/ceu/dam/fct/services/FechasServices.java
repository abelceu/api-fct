package ceu.dam.fct.services;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import ceu.dam.fct.dao.FechasDao;
import ceu.dam.fct.db.OpenConnection;
import ceu.dam.fct.exception.CeuFctException;
import ceu.dam.fct.modelo.Fecha;

@RestController
public class FechasServices {

	private FechasDao dao;
	private OpenConnection connProvider;

	public FechasServices() {
		dao = new FechasDao();
		connProvider = new OpenConnection();
	}

	@GetMapping("/fechasActuales")
	public List<Fecha> consultarFechasActuales() throws CeuFctException {
		Connection conn = null;
		try {
			conn = connProvider.abrirConexion();
			LocalDate hoy = LocalDate.now();
			Integer evaluacion = 1;
			if (hoy.getMonthValue() >= 9) {
				evaluacion = 1;
			}
			return dao.consultar(conn, hoy.getYear(), evaluacion);
		} catch (SQLException e) {
			System.err.println("Error al consultar fechas");
			e.printStackTrace();
			throw new CeuFctException("Error al consultar fechas en BBDD", e);
		} finally {
			try {
				conn.close();
			} catch (Exception ignore) {
			}
		}
	}

}
