package es.ipp.springboot.core.dao;

import java.util.LinkedList;
import java.util.List;

import es.ipp.springboot.core.exception.AppException;
import es.ipp.springboot.entity.IBaseEntity;

/**
 * Interfaz de la que han de heredar todas las demás.
 * 
 * @author ignacio
 *
 * @param <T>
 * @param <PK>
 */
public interface IBaseDao<T extends IBaseEntity<PK>, PK> {

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
	 * Método que devuelve una entidad a partir de su id.
	 * 
	 * @param id
	 * @param nombreCampoId
	 * @param joins
	 * @return T
	 */
	T findById(PK id, String nombreCampoId, List<JoinClause> joins);

	/**
	 * Método que devuelve una entidad a partir de su id.
	 * 
	 * @param id
	 * @param nombreCampoId
	 * @return T
	 */
	T findById(PK id, String nombreCampoId);

	/**
	 * Devuelve una lista de elementos a partir de unos filtros, unos joins, unos
	 * group bys y un orden.
	 * 
	 * @param filtros
	 * @param joins
	 * @param orden
	 * @param groupBys
	 * @param numeroMinMaxRegistros
	 * @return List<T>
	 */
	List<T> findByFilteredQuery(LinkedList<FilterClause> filtros, LinkedList<JoinClause> joins,
			LinkedList<OrderByClause> orden, LinkedList<GroupByClause> groupBys, int... numeroMinMaxRegistros);

	/**
	 * Devuelve una lista de elementos a partir de unos filtros, unos joins y un
	 * orden.
	 * 
	 * @param filtros
	 * @param joins
	 * @param orden
	 * @param numeroMinMaxRegistros
	 * @return List<T>
	 */
	List<T> findByFilteredQuery(LinkedList<FilterClause> filtros, LinkedList<JoinClause> joins,
			LinkedList<OrderByClause> orden, int... numeroMinMaxRegistros);

	/**
	 * Devuelve una lista de elementos a partir de unos filtros, unos joins y un
	 * orden. Admite cláusula DISTINCT.
	 * 
	 * @param filtros
	 * @param joins
	 * @param orden
	 * @param groupBys
	 * @param addDistinct
	 * @param numeroMinMaxRegistros
	 * @return List<T>
	 */
	List<T> findByFilteredQuery(LinkedList<FilterClause> filtros, LinkedList<JoinClause> joins,
			LinkedList<OrderByClause> orden, LinkedList<GroupByClause> groupBys, boolean addDistinct,
			int... numeroMinMaxRegistros);

	/**
	 * Método que devuelve una lista de elementos a partir de un orden, unos filtros
	 * y unos joins. Genera una consulta HQL.
	 * 
	 * @param filtros
	 * @param orden
	 * @param numeroMaxMinRegistros
	 * @return List<T>
	 */
	List<T> findByFilteredQuery(LinkedList<FilterClause> filtros, LinkedList<OrderByClause> orden,
			int... numeroMinMaxRegistros);

	/**
	 * Método que devuelve una lista de elementos a partir de un orden, unos filtros
	 * y unos joins. Genera una consulta HQL.
	 * 
	 * @param filtros
	 * @param numeroMaxMinRegistros
	 * @return List<T>
	 */
	List<T> findByFilteredQuery(LinkedList<FilterClause> filtros, int... numeroMinMaxRegistros);

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
	Long countFieldsByFilteredQuery(LinkedList<FieldClause> fields, LinkedList<FilterClause> filtros,
			LinkedList<JoinClause> joins);

	/**
	 * Devuelve una lista de arrays de objetos genéricos que representarán cada
	 * campo pedido de cada fila devuelta por la consulta. Es tarea del servicio
	 * hacer una conversión de tipo para cada elemento devuelve en este array, así
	 * como comprobar si son null o no.
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
	 * hacer una conversión de tipo para cada elemento devuelve en este array, así
	 * como comprobar si son null o no.
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
	 * hacer una conversión de tipo para cada elemento devuelve en este array, así
	 * como comprobar si son null o no.
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

}
