package es.ipp.springboot.core.controller;

import java.lang.reflect.ParameterizedType;
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
	 * Devuelve el tipo de clase de la entidad principal.
	 * 
	 * @return Class<E>
	 */
	@SuppressWarnings("unchecked")
	default Class<T> getEntityClass() {
		return (Class<T>) ((ParameterizedType) this.getClass().getGenericInterfaces()[0]).getActualTypeArguments()[0];
	}

	/**
	 * Busca todas las entidades.
	 * 
	 * @return T
	 */
	List<T> findAll();

	/**
	 * Crea una entidad.
	 * 
	 * @param entity
	 * @return T
	 */
	T create(T entity);

	/**
	 * Modifica una entidad.
	 * 
	 * @param entity
	 * @return T
	 */
	T update(PK id, T entity);

	/**
	 * Busca por id.
	 * 
	 * @param id
	 * @return T
	 */
	T findById(PK id);

	/**
	 * Elimina una entidad.
	 * 
	 * @param id
	 * @return ResponseEntity<?>
	 */
	ResponseEntity<?> delete(PK id);

}
