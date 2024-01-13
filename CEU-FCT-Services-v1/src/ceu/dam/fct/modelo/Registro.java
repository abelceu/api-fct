package ceu.dam.fct.modelo;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.Data;
public @Data class Registro {

	private Long idRegistro;
	private Long idUsuario;
	private LocalDate fecha;
	private BigDecimal numHoras;
	private String descripcion;
	
	

}
