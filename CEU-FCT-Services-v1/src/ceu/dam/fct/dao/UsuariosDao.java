package ceu.dam.fct.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import ceu.dam.fct.modelo.Usuario;

public class UsuariosDao {

	public Usuario consultar(Connection conn, String email) throws SQLException {
		Statement stmt = null;
		Usuario usuario = null;
		try {
			stmt = conn.createStatement();
			String sql = "select * from usuarios where email = '" + email + "'";
			ResultSet rs = stmt.executeQuery(sql);
			if (rs.next()) {
				usuario = new Usuario();
				usuario.setIdUsuario(rs.getLong("id_usuario"));
				usuario.setActivo(rs.getBoolean("activo"));
				usuario.setApellidos(rs.getString("apellidos"));
				usuario.setCiclo(rs.getString("ciclo"));
				usuario.setNombre(rs.getString("nombre"));
				usuario.setPassword(rs.getString("password"));
				usuario.setEmail(email);
			}
			return usuario;
		}
		finally {
			try {
				stmt.close();
			}catch(Exception ignore) {}
		}
	}

	
	public void insertar(Connection conn, Usuario usuario) throws SQLException {
		PreparedStatement stmt = null;
		try {
			String sql = "insert into usuarios (email, password, nombre, apellidos, ciclo, activo) values (?,?,?,?,?,?)";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, usuario.getEmail());
			stmt.setString(2, usuario.getPassword());
			stmt.setString(3, usuario.getNombre());
			stmt.setString(4, usuario.getApellidos());
			stmt.setString(5, usuario.getCiclo());
			stmt.setBoolean(6, usuario.getActivo());
			stmt.execute();
		}
		finally {
			try {
				stmt.close();
			}catch(Exception ignore) {}
		}
	}
	
}
