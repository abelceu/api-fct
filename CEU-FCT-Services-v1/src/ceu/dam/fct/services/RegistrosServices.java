package ceu.dam.fct.services;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ceu.dam.fct.dao.RegistrosDao;
import ceu.dam.fct.db.OpenConnection;
import ceu.dam.fct.exception.CeuFctException;
import ceu.dam.fct.modelo.Registro;

@RestController
public class RegistrosServices {

	private RegistrosDao dao;
	private OpenConnection connProvider;

	public RegistrosServices() {
		dao = new RegistrosDao();
		connProvider = new OpenConnection();
	}

	@GetMapping("/registros/usuario/{idUsuario}")
	public List<Registro> consultarRegistrosUsuario(@PathVariable Long idUsuario) throws CeuFctException {
		Connection conn = null;
		try {
			conn = connProvider.abrirConexion();
			return dao.consultarPorUsuario(conn, idUsuario);
		} catch (SQLException e) {
			System.err.println("Error al consultar registros");
			throw new CeuFctException("Error al consultar registros en BBDD", e);
		} finally {
			try {
				conn.close();
			} catch (Exception ignore) {
			}
		}
	}
	
	@PostMapping("/registro")
	public void altaRegistro(@RequestBody Registro registro) throws CeuFctException {
		Connection conn = null;
		try {
			conn = connProvider.abrirConexion();
			List<Registro> registrosPrevios = dao.consultarPorUsuario(conn, registro.getIdUsuario());
			for (Registro previo : registrosPrevios) {
				if (previo.getFecha().equals(registro.getFecha())) {
					System.out.println("Ya existe un registro con esa fecha");
					throw new CeuFctException("Ya existe un registro para ese usuario en esa fecha");
				}
			}
			dao.insertar(conn, registro);
		} catch (SQLException e) {
			System.err.println("Error al insertar registro");
			throw new CeuFctException("Error al insertar registro en BBDD", e);
		} finally {
			try {
				conn.close();
			} catch (Exception ignore) {
			}
		}
	}

}
