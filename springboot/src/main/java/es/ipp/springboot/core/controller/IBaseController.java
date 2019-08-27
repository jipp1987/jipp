package es.ipp.springboot.core.controller;

import java.util.List;
import java.util.Map;

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
	 * @param postRequestEntity Objeto con datos necesarios para las consultas hacia
	 *                          la base de datos. También puede contener un objeto
	 *                          genérico con un dato enviado desde el cliente.
	 * @return ResponseEntity<List<T>>
	 */
	ResponseEntity<List<T>> find(PostRequestEntity postRequestEntity);

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

	/**
	 * Elimina una entidad.
	 * 
	 * @param id
	 * @return Map<String, Boolean> Palabra "deleted" y boolean indicando si se ha
	 *         eliminado o no.
	 */
	Map<String, Boolean> deleteEntity(PK id);

}
