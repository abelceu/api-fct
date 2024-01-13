package ceu.dam.fct.modelo;

import lombok.Data;

public @Data class Usuario {

	private Long idUsuario;
	private String email;
	private String password;
	private String nombre;
	private String apellidos;
	private String ciclo;
	private Boolean activo;
	
	public Usuario() {
	}

	
	

}
