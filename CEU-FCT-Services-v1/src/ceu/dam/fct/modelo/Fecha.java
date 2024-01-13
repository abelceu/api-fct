package ceu.dam.fct.modelo;

import java.time.LocalDate;

import lombok.Data;

public @Data class Fecha {

	private LocalDate fecha;
	private Integer año;
	private Integer evaluacion;
	private Boolean disponibilidad;
	
	
	

}
