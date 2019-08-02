package es.ipp.springboot.core.service;

import java.util.LinkedList;
import java.util.List;

import es.ipp.springboot.core.dao.FieldClause;
import es.ipp.springboot.core.dao.FilterClause;
import es.ipp.springboot.core.dao.GroupByClause;
import es.ipp.springboot.core.dao.JoinClause;
import es.ipp.springboot.core.dao.OrderByClause;
import es.ipp.springboot.core.exception.AppException;
import es.ipp.springboot.entity.IBaseEntity;

/**
 * Interfaz que han de implementar los servicios de la aplicación.
 * 
 * @author ignacio
 *
 */
public interface IBaseService<T extends IBaseEntity<PK>, PK> {

	/**
	 * Crea una entidad.
	 * 
	 * @param entity
	 * @throws AppException
	 */
	void create(T entity) throws AppException;

	/**
	 * Actualiza una entidad.
	 * 
	 * @param entity
	 * @throws AppException
	 */
	void update(T entity) throws AppException;

	/**
	 * Eliminar una entidad.
	 * 
	 * @param entity
	 * @throws AppException
	 */
	void delete(T entity) throws AppException;

	/**
	 * Crea registros de una tabla intermedia.
	 * 
	 * @param list
	 * @throws BaseException
	 */
	void createPKCompuesta(List<T> list) throws AppException;

	/**
	 * Actualiza los registros de una tabla intermedia. La idea es primero hacer
	 * una consulta para obtener los registros originales, y luego pasarle una
	 * lista con los nuevos. Las lista se compararán eliminando los registros de
	 * la lista vieja que no estén en la nueva y actualizando o creando los de
	 * la lista nueva según estén o no en la vieja.
	 * 
	 * @param listPasado
	 * @param listFuturo
	 */
	void updatePKCompuesta(List<T> listPasado, List<T> listFuturo) throws AppException;

	/**
	 * Elimina los elementos de una tabla intermedia.
	 * 
	 * @param list
	 * @throws BaseException
	 */
	void deletePKCompuesta(List<T> list) throws AppException;

	/**
	 * Carga en detalle una entidad concreta.
	 * 
	 * @param id
	 * @return T
	 */
	T load(PK id);

	/**
	 * Devuelve una entidad a partir de su id. El nombre del campo id puede ser
	 * null, en cuyo caso se le asignará "id".
	 * 
	 * @param id
	 * @param nombreCampoId
	 * @return T
	 */
	T findById(PK id, String nombreCampoId);

	/**
	 * Devuelve una entidad a partir de su id. El nombre del campo id puede ser
	 * null, en cuyo caso se le asignará "id".
	 * 
	 * @param id
	 * @param nombreCampoId
	 * @param joins
	 * @return T
	 */
	T findById(PK id, String nombreCampoId, LinkedList<JoinClause> joins);

	/**
	 * Método que devuelve una lista de elementos a partir de un orden, unos
	 * filtros y unos joins. Genera una consulta HQL.
	 * 
	 * @param filtros
	 * @param orden
	 * @param numeroMaxMinRegistros
	 * @return List<T>
	 */
	List<T> findByFilteredQuery(LinkedList<FilterClause> filtros, LinkedList<OrderByClause> orden, int... numeroMinMaxRegistros);

	/**
	 * Método que devuelve una lista de elementos a partir de un orden, unos
	 * filtros y unos joins. Genera una consulta HQL.
	 * 
	 * @param filtros
	 * @param numeroMaxMinRegistros
	 * @return List<T>
	 */
	List<T> findByFilteredQuery(LinkedList<FilterClause> filtros, int... numeroMinMaxRegistros);

	/**
	 * Método que devuelve una lista de elementos a partir de un orden, unos
	 * filtros y unos joins. Genera una consulta HQL.
	 * 
	 * @param filtros
	 * @param joins
	 * @param orden
	 * @param groupBys
	 * @param numeroMaxMinRegistros
	 * @return List<T>
	 */
	List<T> findByFilteredQuery(LinkedList<FilterClause> filtros, LinkedList<JoinClause> joins, LinkedList<OrderByClause> orden,
			LinkedList<GroupByClause> groupBys, int... numeroMinMaxRegistros);

	/**
	 * Método que devuelve una lista de elementos a partir de un orden, unos
	 * filtros y unos joins. Genera una consulta HQL.
	 * 
	 * @param filtros
	 * @param joins
	 * @param orden
	 * @param groupBys
	 * @param addDistinct
	 * @param numeroMaxMinRegistros
	 * @return List<T>
	 */
	List<T> findByFilteredQuery(LinkedList<FilterClause> filtros, LinkedList<JoinClause> joins, LinkedList<OrderByClause> orden,
			LinkedList<GroupByClause> groupBys, boolean addDistinct, int... numeroMinMaxRegistros);

	/**
	 * Método que devuelve una lista de elementos a partir de un orden, unos
	 * filtros y unos joins. Genera una consulta HQL.
	 * 
	 * @param filtros
	 * @param joins
	 * @param orden
	 * @param numeroMaxMinRegistros
	 * @return List<T>
	 */
	List<T> findByFilteredQuery(LinkedList<FilterClause> filtros, LinkedList<JoinClause> joins, LinkedList<OrderByClause> orden,
			int... numeroMinMaxRegistros);

	/**
	 * Devuelve todos los registros de una tabla, sin filtros.
	 * 
	 * @param orden
	 * @param numeroMinMaxRegistros
	 * @return List<T>
	 */
	List<T> findAll(LinkedList<OrderByClause> orden, int... numeroMinMaxRegistros);

	/**
	 * Devuelve todos los registros de una tabla, sin filtros.
	 * 
	 * @param orden
	 * @param joins
	 * @param numeroMinMaxRegistros
	 * @return List<T>
	 */
	List<T> findAll(LinkedList<OrderByClause> orden, LinkedList<JoinClause> joins, int... numeroMinMaxRegistros);

	/**
	 * Cuenta el número de registros devueltos por una consulta.
	 * 
	 * @param filtros
	 * @return Long
	 */
	Long countByFilteredQuery(LinkedList<FilterClause> filtros);

	/**
	 * Cuenta el número de registros devueltos por una consulta.
	 * 
	 * @param filtros
	 * @param joins
	 * @return Long
	 */
	Long countByFilteredQuery(LinkedList<FilterClause> filtros, LinkedList<JoinClause> joins);

	/**
	 * Cuenta el número de resultados de un campo concreto.
	 * 
	 * @param fields
	 * @param filtros
	 * @return Long
	 */
	Long countFieldsByFilteredQuery(LinkedList<FieldClause> fields, LinkedList<FilterClause> filtros);

	/**
	 * Cuenta el número de resultados de un campo concreto.
	 * 
	 * @param fields
	 * @param filtros
	 * @param joins
	 * @return Long
	 */
	Long countFieldsByFilteredQuery(LinkedList<FieldClause> fields, LinkedList<FilterClause> filtros, LinkedList<JoinClause> joins);

	/**
	 * Devuelve una lista de arrays de objetos genéricos que representarán cada
	 * campo pedido de cada fila devuelta por la consulta. Es tarea del servicio
	 * hacer una conversión de tipo para cada elemento devuelve en este array,
	 * así como comprobar si son null o no.
	 * 
	 * @param fields
	 * @param filtros
	 * @param orden
	 * @param numeroMinMaxRegistros
	 * @return Object[]
	 */
	List<Object[]> findFieldsByFilteredQuery(LinkedList<FieldClause> fields, LinkedList<FilterClause> filtros,
			LinkedList<OrderByClause> orden, int... numeroMinMaxRegistros);

	/**
	 * Devuelve una lista de arrays de objetos genéricos que representarán cada
	 * campo pedido de cada fila devuelta por la consulta. Es tarea del servicio
	 * hacer una conversión de tipo para cada elemento devuelve en este array,
	 * así como comprobar si son null o no.
	 * 
	 * @param fields
	 * @param filtros
	 * @param orden
	 * @param groupBys
	 * @param numeroMinMaxRegistros
	 * @return Object[]
	 */
	List<Object[]> findFieldsByFilteredQuery(LinkedList<FieldClause> fields, LinkedList<FilterClause> filtros,
			LinkedList<OrderByClause> orden, LinkedList<GroupByClause> groupBys, int... numeroMinMaxRegistros);

	/**
	 * Devuelve una lista de arrays de objetos genéricos que representarán cada
	 * campo pedido de cada fila devuelta por la consulta. Es tarea del servicio
	 * hacer una conversión de tipo para cada elemento devuelve en este array,
	 * así como comprobar si son null o no.
	 * 
	 * @param fields
	 * @param filtros
	 * @param joins
	 * @param orden
	 * @param groupBys
	 * @param numeroMinMaxRegistros
	 * @return Object[]
	 */
	List<Object[]> findFieldsByFilteredQuery(LinkedList<FieldClause> fields, LinkedList<FilterClause> filtros,
			LinkedList<JoinClause> joins, LinkedList<OrderByClause> orden, LinkedList<GroupByClause> groupBys,
			int... numeroMinMaxRegistros);

	/**
	 * Crea un elemento nuevo por defecto.
	 * 
	 * @return T
	 */
	T elementoDefecto();

}
