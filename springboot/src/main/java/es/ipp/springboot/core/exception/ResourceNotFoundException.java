package es.ipp.springboot.core.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Excepción personalizada para recursos no encontrados.
 * 
 * ResponseStatus hará que Spring Boot responda con el código de estado HTTP
 * especificado cada vez que se lance esta excepción desde un controlador.
 * 
 * @author ignacio
 *
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -5876062577250050343L;

	// CAMPOS
	/**
	 * Nombre del recurso.
	 */
	private String resourceName;
	/**
	 * Nombre del campo.
	 */
	private String fieldName;
	/**
	 * Valor del campo.
	 */
	private Object fieldValue;

	// CONSTRUCTOR
	public ResourceNotFoundException(String resourceName, String fieldName, Object fieldValue) {
		// Mensaje de exepción
		super(String.format("%s not found with %s : '%s'", resourceName, fieldName, fieldValue));
		this.resourceName = resourceName;
		this.fieldName = fieldName;
		this.fieldValue = fieldValue;
	}

	// GETTERS
	public String getResourceName() {
		return resourceName;
	}

	public String getFieldName() {
		return fieldName;
	}

	public Object getFieldValue() {
		return fieldValue;
	}
}
