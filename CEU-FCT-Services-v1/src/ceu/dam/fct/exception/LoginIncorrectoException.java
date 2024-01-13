package ceu.dam.fct.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.FORBIDDEN)
public class LoginIncorrectoException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6597826605000131232L;

	public LoginIncorrectoException() {
	}

	public LoginIncorrectoException(String message) {
		super(message);
	}

	public LoginIncorrectoException(Throwable cause) {
		super(cause);
	}

	public LoginIncorrectoException(String message, Throwable cause) {
		super(message, cause);
	}

	public LoginIncorrectoException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
