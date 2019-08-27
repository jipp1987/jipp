package es.ipp.springboot.entity;

import java.io.IOException;
import java.io.Serializable;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

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
	 * Devuelve una copia profunda del objeto. Crea un objeto ObjectMapper en la
	 * propia función.
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
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true);
		mapper.configure(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES, true);

		IBaseEntity<PK> deepCopy = mapper.readValue(mapper.writeValueAsString(this), this.getClass());
		mapper = null;
		return deepCopy;
	}

	/**
	 * Devuelve una copia profunda del objeto. Espera un objecto ObjectMapper para
	 * realizar la copia.
	 * 
	 * @param mapper
	 * @return IBaseEntity<PK>
	 * @throws IOException
	 * @throws JsonProcessingException
	 * @throws JsonMappingException
	 * @throws JsonParseException
	 */
	@SuppressWarnings("unchecked")
	default IBaseEntity<PK> deepCopy(ObjectMapper mapper) throws JsonParseException, JsonMappingException, JsonProcessingException, IOException {
		IBaseEntity<PK> deepCopy = mapper.readValue(mapper.writeValueAsString(this), this.getClass());
		return deepCopy;
	}

}
