package ceu.dam.fct.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class CeuFctException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2975950899430708432L;

	public CeuFctException() {
	}

	public CeuFctException(String message) {
		super(message);
	}

	public CeuFctException(Throwable cause) {
		super(cause);
	}

	public CeuFctException(String message, Throwable cause) {
		super(message, cause);
	}

	public CeuFctException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
