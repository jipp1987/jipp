package es.ipp.springboot.core.exception;

import es.ipp.springboot.core.exception.constants.EnumExceptionCodes;
import es.ipp.springboot.core.exception.constants.EnumExceptionLevels;

/**
 * Excepción genérica de la aplicación.
 * 
 * @author ignacio
 *
 */
public class AppException extends Exception {

	private static final long serialVersionUID = -7045474417360902119L;
	// CAMPOS
	/**
	 * Código de excepción.
	 */
	private EnumExceptionCodes exceptionCode;
	/**
	 * Nivel de excepción.
	 */
	private EnumExceptionLevels exceptionLevel;

	// CONSTRUCTOR
	public AppException(String message) {
		super(message);
		this.exceptionCode = EnumExceptionCodes.UNDEFINED;
		this.exceptionLevel = EnumExceptionLevels.ERROR;
	}

	public AppException(String message, EnumExceptionCodes exceptionCode, EnumExceptionLevels exceptionLevel) {
		super(message);
		this.exceptionCode = exceptionCode;
		this.exceptionLevel = exceptionLevel;
	}

	// GETTERS Y SETTERS
	public EnumExceptionCodes getExceptionCode() {
		return exceptionCode;
	}

	public void setExceptionCode(EnumExceptionCodes exceptionCode) {
		this.exceptionCode = exceptionCode;
	}

	public EnumExceptionLevels getExceptionLevel() {
		return exceptionLevel;
	}

	public void setExceptionLevel(EnumExceptionLevels exceptionLevel) {
		this.exceptionLevel = exceptionLevel;
	}

}
