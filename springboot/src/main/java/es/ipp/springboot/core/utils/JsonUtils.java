package es.ipp.springboot.core.utils;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Utilidades para transformaciones JSON. Se emplea la librería Jackson.
 * 
 * @author ignacio
 *
 */
public class JsonUtils {

	/**
	 * Instancia singleton de un mapper de Jackson para transformar desde JSON a
	 * objetos Java.
	 */
	private static ObjectMapper objectMapper;

	/**
	 * Devuelve una instancia de ObjectMapper con los siguientes atributos: error en
	 * propiedades desconocidas y error en null para primitivos.
	 * 
	 * @return ObjectMapper
	 */
	public static ObjectMapper getInstance() {
		objectMapper = new ObjectMapper();
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true);
		objectMapper.configure(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES, true);

		return objectMapper;
	}

}
