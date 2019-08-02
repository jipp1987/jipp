package es.ipp.springboot.core.service.impl;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import es.ipp.springboot.core.dao.FieldClause;
import es.ipp.springboot.core.dao.FilterClause;
import es.ipp.springboot.core.dao.GroupByClause;
import es.ipp.springboot.core.dao.IBaseDao;
import es.ipp.springboot.core.dao.JoinClause;
import es.ipp.springboot.core.dao.OrderByClause;
import es.ipp.springboot.core.exception.AppException;
import es.ipp.springboot.core.service.IBaseService;
import es.ipp.springboot.entity.IBaseEntity;

/**
 * Clase abstracta de han de heredar los servicios de la aplicación.
 * 
 * @author ignacio
 *
 * @param <T>
 * @param <PK>
 * @param <IDAO>
 */
public abstract class BaseServiceImpl<T extends IBaseEntity<PK>, PK, IDAO extends IBaseDao<T, PK>>
		implements IBaseService<T, PK> {

	/**
	 * Acceso a datos.
	 */
	protected IDAO dao;

	/**
	 * Clase de la entidad: obtengo la clase de la entidad a partir de los
	 * parámetros genéricos que se le pasan a la clase BaseDao (el primero es la
	 * entidad, por eso uso cero).
	 */
	@SuppressWarnings("unchecked")
	protected Class<T> entityClass = (Class<T>) (this.getClass().getGenericSuperclass() instanceof ParameterizedType
			? (ParameterizedType) this.getClass().getGenericSuperclass()
			: (ParameterizedType) this.getClass().getSuperclass().getGenericSuperclass()).getActualTypeArguments()[0];

	// CONSTRUCTOR
	public BaseServiceImpl(IDAO dao) {
		this.dao = dao;
	}

	@Override
	public void create(T t) throws AppException {
		this.dao.create(t);
	}

	@Override
	public void update(T t) throws AppException {
		this.dao.update(t);
	}

	@Override
	public void delete(T t) throws AppException {
		this.dao.delete(t);
	}

	@Override
	public void createPKCompuesta(List<T> list) throws AppException {
		if (list != null && list.size() > 0) {
			for (T t : list) {
				// Crear elementos
				this.dao.create(t);
			}
		}
	}

	@Override
	public void updatePKCompuesta(List<T> listPasado, List<T> listFuturo) throws AppException {
		// Comprobar que las listas no sean null
		if (listPasado == null) {
			listPasado = new ArrayList<T>();
		}
		if (listFuturo == null) {
			listFuturo = new ArrayList<T>();
		}

		// Eliminar los registros de la lista antigua que no estén en la nueva
		if (listPasado.size() > 0) {
			for (T t : listPasado) {
				// Si no lo contiene, se elimina de la base de datos
				if (!listFuturo.contains(t)) {
					this.dao.delete(t);
				}
			}
		}

		// Comprobar lista futura
		if (listFuturo.size() > 0) {
			for (T t : listFuturo) {
				if (!listPasado.contains(t)) {
					// Si no estaba en la lista anterior, entonces se crea en la
					// base de datos
					this.dao.create(t);
				} else {
					// Si estaba en la lista anterior, entonces se actualiza en
					// la base de datos
					this.dao.update(t);
				}
			}
		}
	}

	@Override
	public void deletePKCompuesta(List<T> list) throws AppException {
		if (list != null && list.size() > 0) {
			for (T t : list) {
				// Eliminar elementos
				this.dao.delete(t);
			}
		}
	}

	@Override
	public T load(PK id) {
		return this.findById(id, null);
	}

	@Override
	public T findById(PK id, String nombreCampoId) {
		if (nombreCampoId == null) {
			nombreCampoId = "id";
		}
		return this.dao.findById(id, nombreCampoId, null);
	}

	@Override
	public T findById(PK id, String nombreCampoId, LinkedList<JoinClause> joins) {
		if (nombreCampoId == null) {
			nombreCampoId = "id";
		}
		return this.dao.findById(id, nombreCampoId, joins);
	}

	@Override
	public List<T> findByFilteredQuery(LinkedList<FilterClause> filtros, LinkedList<JoinClause> joins,
			LinkedList<OrderByClause> orden, LinkedList<GroupByClause> groupBys, int... numeroMinMaxRegistros) {
		return this.dao.findByFilteredQuery(filtros, joins, orden, groupBys, numeroMinMaxRegistros);
	}

	@Override
	public List<T> findByFilteredQuery(LinkedList<FilterClause> filtros, LinkedList<OrderByClause> orden,
			int... numeroMinMaxRegistros) {
		return this.dao.findByFilteredQuery(filtros, orden, numeroMinMaxRegistros);
	}

	@Override
	public List<T> findByFilteredQuery(LinkedList<FilterClause> filtros, int... numeroMinMaxRegistros) {
		return this.dao.findByFilteredQuery(filtros, numeroMinMaxRegistros);
	}

	@Override
	public List<T> findByFilteredQuery(LinkedList<FilterClause> filtros, LinkedList<JoinClause> joins,
			LinkedList<OrderByClause> orden, int... numeroMinMaxRegistros) {
		return this.dao.findByFilteredQuery(filtros, joins, orden, numeroMinMaxRegistros);
	}

	@Override
	public List<T> findByFilteredQuery(LinkedList<FilterClause> filtros, LinkedList<JoinClause> joins,
			LinkedList<OrderByClause> orden, LinkedList<GroupByClause> groupBys, boolean addDistinct,
			int... numeroMinMaxRegistros) {
		return this.dao.findByFilteredQuery(filtros, joins, orden, groupBys, addDistinct, numeroMinMaxRegistros);
	}

	@Override
	public List<T> findAll(LinkedList<OrderByClause> orden, int... numeroMinMaxRegistros) {
		return this.dao.findAll(orden, numeroMinMaxRegistros);
	}

	@Override
	public List<T> findAll(LinkedList<OrderByClause> orden, LinkedList<JoinClause> joins,
			int... numeroMinMaxRegistros) {
		return this.dao.findAll(orden, joins, numeroMinMaxRegistros);
	}

	@Override
	public Long countByFilteredQuery(LinkedList<FilterClause> filtros) {
		return this.dao.countByFilteredQuery(filtros);
	}

	@Override
	public Long countByFilteredQuery(LinkedList<FilterClause> filtros, LinkedList<JoinClause> joins) {
		return this.dao.countByFilteredQuery(filtros, joins);
	}

	@Override
	public Long countFieldsByFilteredQuery(LinkedList<FieldClause> fields, LinkedList<FilterClause> filtros) {
		return this.dao.countFieldsByFilteredQuery(fields, filtros);
	}

	@Override
	public Long countFieldsByFilteredQuery(LinkedList<FieldClause> fields, LinkedList<FilterClause> filtros,
			LinkedList<JoinClause> joins) {
		return this.dao.countFieldsByFilteredQuery(fields, filtros, joins);
	}

	@Override
	public List<Object[]> findFieldsByFilteredQuery(LinkedList<FieldClause> fields, LinkedList<FilterClause> filtros,
			LinkedList<OrderByClause> orden, int... numeroMinMaxRegistros) {
		return this.dao.findFieldsByFilteredQuery(fields, filtros, orden, numeroMinMaxRegistros);
	}

	@Override
	public List<Object[]> findFieldsByFilteredQuery(LinkedList<FieldClause> fields, LinkedList<FilterClause> filtros,
			LinkedList<OrderByClause> orden, LinkedList<GroupByClause> groupBys, int... numeroMinMaxRegistros) {
		return this.dao.findFieldsByFilteredQuery(fields, filtros, orden, groupBys, numeroMinMaxRegistros);
	}

	@Override
	public List<Object[]> findFieldsByFilteredQuery(LinkedList<FieldClause> fields, LinkedList<FilterClause> filtros,
			LinkedList<JoinClause> joins, LinkedList<OrderByClause> orden, LinkedList<GroupByClause> groupBys,
			int... numeroMinMaxRegistros) {
		return this.dao.findFieldsByFilteredQuery(fields, filtros, joins, orden, groupBys, numeroMinMaxRegistros);
	}

	@Override
	public T elementoDefecto() {
		T t = null;
		try {
			t = this.entityClass.newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return t;
	}

}
