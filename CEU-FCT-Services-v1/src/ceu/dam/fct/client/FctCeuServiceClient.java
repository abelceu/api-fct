package ceu.dam.fct.client;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import ceu.dam.fct.exception.CeuFctException;
import ceu.dam.fct.exception.LoginIncorrectoException;
import ceu.dam.fct.modelo.Fecha;
import ceu.dam.fct.modelo.Registro;
import ceu.dam.fct.modelo.Usuario;

public class FctCeuServiceClient {

	private String urlBase;
	private RestTemplate restTemplate;
	
	public FctCeuServiceClient(String urlBase, RestTemplate restTemplate) {
		super();
		this.urlBase = urlBase;
		this.restTemplate = restTemplate;
	}
	
	public List<Fecha> consultarFechasDisponibles() throws CeuFctException {
		try {
			String url = urlBase + "/fechasActuales";
			Fecha[] fechas = restTemplate.getForObject(url, Fecha[].class);
			return Arrays.asList(fechas);
		}
		catch(HttpStatusCodeException e) {
			if (e.getStatusCode() == HttpStatus.INTERNAL_SERVER_ERROR) {
				throw new CeuFctException("Error consultando fechas disponibles");
			}
			throw e;
		}
	}
	
	public List<Registro> consultarRegistros(Long idUsuario) throws CeuFctException {
		try {
			String url = urlBase + "/registros/usuario/{idUsuario}";
			Registro[] registros = restTemplate.getForObject(url, Registro[].class, idUsuario);
			return Arrays.asList(registros);
		}
		catch(HttpStatusCodeException e) {
			if (e.getStatusCode() == HttpStatus.INTERNAL_SERVER_ERROR) {
				throw new CeuFctException("Error consultando registros del usuario");
			}
			throw e;
		}
	}
	
	public Usuario login(String email, String pass) throws CeuFctException, LoginIncorrectoException {
		try {
			String url = urlBase + "/login?email=" + email + "&password=" + pass;
			return restTemplate.getForObject(url, Usuario.class);
			
		}
		catch(HttpStatusCodeException e) {
			if (e.getStatusCode() == HttpStatus.INTERNAL_SERVER_ERROR) {
				throw new CeuFctException("Error consultando usuario");
			}
			if (e.getStatusCode() == HttpStatus.FORBIDDEN) {
				throw new LoginIncorrectoException("Datos incorrectos para el login");
			}
			throw e;
		}
	}
	
	
	public Usuario crearUsuario(Usuario usuario) throws CeuFctException {
		try {
			String url = urlBase + "/usuario";
			return restTemplate.postForObject(url, usuario, Usuario.class);
			
		}
		catch(HttpStatusCodeException e) {
			if (e.getStatusCode() == HttpStatus.INTERNAL_SERVER_ERROR) {
				throw new CeuFctException("Error creando usuario");
			}
			throw e;
		}
	}
	
	public Registro crearUsuario(Registro registro) throws CeuFctException {
		try {
			String url = urlBase + "/registro";
			return restTemplate.postForObject(url, registro, Registro.class);
		}
		catch(HttpStatusCodeException e) {
			if (e.getStatusCode() == HttpStatus.INTERNAL_SERVER_ERROR) {
				throw new CeuFctException("Error creando registro");
			}
			throw e;
		}
	}
	
	
	
	
}
