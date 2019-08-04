package es.ipp.springboot.core.controller;

import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.http.ResponseEntity;

import es.ipp.springboot.entity.IBaseEntity;

/**
 * Interfaz que han de implementar todos los controladores.
 * 
 * @author ignacio
 *
 */
public interface IBaseController<T extends IBaseEntity<PK>, PK> {

	/**
	 * Devuelve una respuesta con un listado de entidades.
	 * 
	 * @param params Mapa de parámetros con una clave identificadora y un objeto. El
	 *               mapa contendrá filtros, orderBys, la propia entidad si fuese
	 *               necesario (en formato JSON, el método ya se ocupa de
	 *               transformarla)...
	 * @return ResponseEntity<List<T>>
	 */
	ResponseEntity<List<T>> find(LinkedHashMap<String, Object> params);

	/**
	 * Devuelve una entidad por su clave principal.
	 * 
	 * @param id
	 * @return ResponseEntity<T>
	 */
	ResponseEntity<T> findById(PK id);

	/**
	 * Crea una entidad.
	 * 
	 * @param jsonEntity Entidad en formato JSON.
	 * @return ResponseEntity<T>
	 */
	ResponseEntity<T> createEntity(String jsonEntity);

	/**
	 * Actualizar una entidad.
	 * 
	 * @param jsonEntity Entidad en formato JSON.
	 * @return ResponseEntity<T>
	 */
	ResponseEntity<T> updateEntity(String jsonEntity);

}
