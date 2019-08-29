package es.ipp.springboot.entity;

import java.io.IOException;
import java.io.Serializable;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import es.ipp.springboot.core.utils.JsonUtils;

/**
 * Interfaz que deben implementar los modelos de la aplicación.
 * 
 * @author ignacio
 *
 */
public interface IBaseEntity<PK> extends Serializable {

	/**
	 * Devuelve la clave primaria.
	 * 
	 * @return PK
	 */
	PK givePK();

	/**
	 * Devuelve true si la clave primaria existe.
	 * 
	 * @return
	 */
	boolean isPKExists();

	/**
	 * Devuelve una copia profunda del objeto.
	 * 
	 * @return IBaseEntity<PK>
	 * @throws IOException
	 * @throws JsonProcessingException
	 * @throws JsonMappingException
	 * @throws JsonParseException
	 */
	@SuppressWarnings("unchecked")
	default IBaseEntity<PK> deepCopy()
			throws JsonParseException, JsonMappingException, JsonProcessingException, IOException {
		return JsonUtils.getInstance().readValue(JsonUtils.getInstance().writeValueAsString(this), this.getClass());
	}

}
