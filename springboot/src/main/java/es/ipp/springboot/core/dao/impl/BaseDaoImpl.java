package es.ipp.springboot.core.dao.impl;

import java.lang.reflect.ParameterizedType;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import es.ipp.springboot.core.dao.FieldClause;
import es.ipp.springboot.core.dao.FilterClause;
import es.ipp.springboot.core.dao.GroupByClause;
import es.ipp.springboot.core.dao.IBaseDao;
import es.ipp.springboot.core.dao.JoinClause;
import es.ipp.springboot.core.dao.OrderByClause;
import es.ipp.springboot.core.dao.constants.EnumFilterTypes;
import es.ipp.springboot.core.exception.AppException;
import es.ipp.springboot.entity.IBaseEntity;

/**
 * Clase abstracta de la que han de heredar los distintos DAOs de la aplicación.
 * 
 * @author ignacio
 *
 * @param <T>
 * @param <PK>
 */
public abstract class BaseDaoImpl<T extends IBaseEntity<PK>, PK> implements IBaseDao<T, PK> {

	// CAMPOS
	/**
	 * Se encarga de realizar consultas y gestionar la sesión de Hibernate. Se
	 * inyecta desde el contexto de aplicación de Spring.
	 */
	@PersistenceContext
	protected EntityManager entityManager;

	/**
	 * Clase de la entidad: obtengo la clase de la entidad a partir de los
	 * parámetros genéricos que se le pasan a la clase BaseDao (el primero es la
	 * entidad, por eso uso cero).
	 */
	@SuppressWarnings("unchecked")
	protected Class<T> entityClass = (Class<T>) (this.getClass().getGenericSuperclass() instanceof ParameterizedType
			? (ParameterizedType) this.getClass().getGenericSuperclass()
			: (ParameterizedType) this.getClass().getSuperclass().getGenericSuperclass()).getActualTypeArguments()[0];

	// MÉTODOS IMPLEMENTADOS
	@Override
	public void create(T t) throws AppException {
		try {
			entityManager.persist(t);
			entityManager.flush();
		} catch (RuntimeException re) {
			throw re;
		}
	}

	@Override
	public void update(T t) throws AppException {
		try {
			entityManager.merge(t);
			entityManager.flush();
		} catch (RuntimeException re) {
			throw re;
		}
	}

	@Override
	public void delete(T t) throws AppException {
		try {
			entityManager.remove(entityManager.contains(t) ? t : entityManager.merge(t));
			entityManager.flush();
		} catch (RuntimeException re) {
			throw re;
		}
	}

	@Override
	public T findById(PK id, String nombreCampoId) {
		return findById(id, nombreCampoId, null);
	}

	@Override
	public T findById(PK id, String nombreCampoId, List<JoinClause> joins) {
		// Creo la consulta con StringBuilder
		StringBuilder sb = new StringBuilder("");
		// Como alias uso el propio nombre de la clase
		sb.append("SELECT " + entityClass.getSimpleName() + " FROM " + entityClass.getSimpleName() + " "
				+ entityClass.getSimpleName() + " ");
		// Añado joins si hay
		if (joins != null && joins.size() > 0) {
			sb.append(this.getJoinStatement(joins));
		}
		// Filtro por el id de la entidad
		sb.append(" WHERE " + entityClass.getSimpleName() + "." + nombreCampoId + "=" + id);
		// Crear Query: sólo obtengo el primer resultado
		TypedQuery<T> query = this.getTypedQuery(sb.toString().trim(), 0, 1);
		// Obtener entidad
		T entity = query.getSingleResult();
		return entity;
	}

	@Override
	public List<T> findByFilteredQuery(LinkedList<FilterClause> filtros, LinkedList<OrderByClause> orden,
			int... numeroMinMaxRegistros) {
		return findByFilteredQuery(filtros, null, orden, null, numeroMinMaxRegistros);
	}

	@Override
	public List<T> findByFilteredQuery(LinkedList<FilterClause> filtros, int... numeroMinMaxRegistros) {
		return findByFilteredQuery(filtros, null, null, null, numeroMinMaxRegistros);
	}

	@Override
	public List<T> findByFilteredQuery(LinkedList<FilterClause> filtros, LinkedList<JoinClause> joins,
			LinkedList<OrderByClause> orden, int... numeroMinMaxRegistros) {
		return findByFilteredQuery(filtros, joins, orden, null, numeroMinMaxRegistros);
	}

	@Override
	public List<T> findByFilteredQuery(LinkedList<FilterClause> filtros, LinkedList<JoinClause> joins,
			LinkedList<OrderByClause> orden, LinkedList<GroupByClause> groupBys, int... numeroMinMaxRegistros) {
		return this.findByFilteredQuery(filtros, joins, orden, groupBys, false, numeroMinMaxRegistros);
	}

	@Override
	public List<T> findByFilteredQuery(LinkedList<FilterClause> filtros, LinkedList<JoinClause> joins,
			LinkedList<OrderByClause> orden, LinkedList<GroupByClause> groupBys, boolean addDistinct,
			int... numeroMinMaxRegistros) {
		// Creo la consulta con StringBuilder
		StringBuilder sb = new StringBuilder("");
		// En HQL es obligatorio poner alias a la tabla principal: le pongo el
		// propio nombre de la clase como alias, además del nombre de la tabla
		// En el SELECT se pone el alias cuando se quiere seleccionar la entidad
		sb.append("SELECT " + (addDistinct ? "DISTINCT " : "") + entityClass.getSimpleName() + " FROM "
				+ entityClass.getSimpleName() + " " + entityClass.getSimpleName());
		// Añado joins
		if (joins != null && joins.size() > 0) {
			sb.append(this.getJoinStatement(joins));
		}
		// Añado filtros
		if (filtros != null && filtros.size() > 0) {
			sb.append(this.getWhereStatement(filtros));
		}
		// Añado group bys
		if (groupBys != null && groupBys.size() > 0) {
			sb.append(this.getGroupByStatement(groupBys));
		}
		// Añado orden
		if (orden != null && orden.size() > 0) {
			sb.append(this.getOrderByStatement(orden));
		}
		// Crear Query
		TypedQuery<T> query = this.getTypedQuery(sb.toString().trim(), numeroMinMaxRegistros);
		// Lista de resultados
		List<T> list = query.getResultList();
		return list;
	}

	@Override
	public List<T> findAll(LinkedList<OrderByClause> orden, int... numeroMinMaxRegistros) {
		return this.findByFilteredQuery(null, orden, numeroMinMaxRegistros);
	}

	@Override
	public List<T> findAll(LinkedList<OrderByClause> orden, LinkedList<JoinClause> joins,
			int... numeroMinMaxRegistros) {
		return this.findByFilteredQuery(null, joins, orden, null, numeroMinMaxRegistros);
	}

	@Override
	public Long countByFilteredQuery(LinkedList<FilterClause> filtros) {
		return this.countByFilteredQuery(filtros, null);
	}

	@Override
	public Long countByFilteredQuery(LinkedList<FilterClause> filtros, LinkedList<JoinClause> joins) {
		StringBuilder sb = new StringBuilder("");
		sb.append("SELECT COUNT(" + entityClass.getSimpleName() + ") FROM " + entityClass.getSimpleName() + " "
				+ entityClass.getSimpleName());
		// Añadir joins
		if (joins != null && joins.size() > 0) {
			sb.append(this.getJoinStatement(joins));
		}
		// Añadir filtros
		if (filtros != null && filtros.size() > 0) {
			sb.append(this.getWhereStatement(filtros));
		}
		// Crear Query
		Query query = this.getQuery(sb.toString().trim());
		// Número de resultados
		Long count = (Long) query.getSingleResult();
		return count;
	}

	@Override
	public Long countFieldsByFilteredQuery(LinkedList<FieldClause> fields, LinkedList<FilterClause> filtros) {
		return this.countFieldsByFilteredQuery(fields, filtros, null);
	}

	@Override
	public Long countFieldsByFilteredQuery(LinkedList<FieldClause> fields, LinkedList<FilterClause> filtros,
			LinkedList<JoinClause> joins) {
		StringBuilder sb = new StringBuilder("");
		sb.append("SELECT COUNT(");
		// Añadir campos
		if (fields != null && fields.size() > 0) {
			sb.append(this.getSelectFieldStatement(fields));
		}
		sb.append(") FROM " + entityClass.getSimpleName() + " " + entityClass.getSimpleName());
		// Añadir joins
		if (joins != null && joins.size() > 0) {
			sb.append(this.getJoinStatement(joins));
		}
		// Añadir filtros
		if (filtros != null && filtros.size() > 0) {
			sb.append(this.getWhereStatement(filtros));
		}
		// Crear Query
		Query query = this.getQuery(sb.toString().trim());
		// Número de resultados
		Long count = (Long) query.getSingleResult();
		return count;
	}

	@Override
	public List<Object[]> findFieldsByFilteredQuery(LinkedList<FieldClause> fields, LinkedList<FilterClause> filtros,
			LinkedList<OrderByClause> orden, int... numeroMinMaxRegistros) {
		return this.findFieldsByFilteredQuery(fields, filtros, null, orden, null, numeroMinMaxRegistros);
	}

	@Override
	public List<Object[]> findFieldsByFilteredQuery(LinkedList<FieldClause> fields, LinkedList<FilterClause> filtros,
			LinkedList<OrderByClause> orden, LinkedList<GroupByClause> groupBys, int... numeroMinMaxRegistros) {
		return this.findFieldsByFilteredQuery(fields, filtros, null, orden, groupBys, numeroMinMaxRegistros);
	}

	@Override
	public List<Object[]> findFieldsByFilteredQuery(LinkedList<FieldClause> fields, LinkedList<FilterClause> filtros,
			LinkedList<JoinClause> joins, LinkedList<OrderByClause> orden, LinkedList<GroupByClause> groupBys,
			int... numeroMinMaxRegistros) {
		StringBuilder sb = new StringBuilder("");
		sb.append("SELECT");
		if (fields != null && fields.size() > 0) {
			sb.append(this.getSelectFieldStatement(fields));
		}
		sb.append(" FROM " + entityClass.getSimpleName() + " " + entityClass.getSimpleName());
		// Añado joins
		if (joins != null && joins.size() > 0) {
			sb.append(this.getJoinStatement(joins));
		}
		// Añado filtros
		if (filtros != null && filtros.size() > 0) {
			sb.append(this.getWhereStatement(filtros));
		}
		// Añado group bys
		if (groupBys != null && groupBys.size() > 0) {
			sb.append(this.getGroupByStatement(groupBys));
		}
		// Añado orden
		if (orden != null && orden.size() > 0) {
			sb.append(this.getOrderByStatement(orden));
		}
		// Query no tipada
		Query query = this.getQuery(sb.toString().trim(), numeroMinMaxRegistros);
		// Lista de resultados
		@SuppressWarnings("unchecked")
		List<Object[]> list = query.getResultList();
		return list;
	}

	// MÉTODOS PRIVADOS
	/**
	 * Devuelve una TypedQuery a partir de una consulta HQL.
	 * 
	 * @param hql
	 * @param numeroMinMaxRegistros
	 * @return TypedQuery<T>
	 */
	private TypedQuery<T> getTypedQuery(String hql, int... numeroMinMaxRegistros) {
		// Crear Query
		TypedQuery<T> query = entityManager.createQuery(hql, entityClass);
		// Paginación
		if (numeroMinMaxRegistros != null && numeroMinMaxRegistros.length > 0) {
			// Si le paso un único número, es el límite de registros desde el
			// primero
			// Si le paso dos, el primer número es el primer valor, y el segundo
			// el límite
			if (numeroMinMaxRegistros.length == 1) {
				query.setMaxResults(numeroMinMaxRegistros[0]);
			} else if (numeroMinMaxRegistros.length == 2) {
				query.setFirstResult(numeroMinMaxRegistros[0]);
				query.setMaxResults(numeroMinMaxRegistros[1]);
			}
		}
		return query;
	}

	/**
	 * Devuelve una query sin tipo a partir de un HQL.
	 * 
	 * @param hql
	 * @param numeroMinMaxRegistros
	 * @return Query
	 */
	private Query getQuery(String hql, int... numeroMinMaxRegistros) {
		// Crear Query
		Query query = entityManager.createQuery(hql);
		// Paginación
		if (numeroMinMaxRegistros != null && numeroMinMaxRegistros.length > 0) {
			// Si le paso un único número, es el límite de registros desde el
			// primero
			// Si le paso dos, el primer número es el primer valor, y el segundo
			// el límite
			if (numeroMinMaxRegistros.length == 1) {
				query.setMaxResults(numeroMinMaxRegistros[0]);
			} else if (numeroMinMaxRegistros.length == 2) {
				query.setFirstResult(numeroMinMaxRegistros[0]);
				query.setMaxResults(numeroMinMaxRegistros[1]);
			}
		}
		return query;
	}

	/**
	 * Devuelve una cláusula join a partir de una lista de JoinClauses.
	 * 
	 * @param join
	 * @return String
	 */
	private String getJoinStatement(List<JoinClause> joins) {
		StringBuilder sb = new StringBuilder(" ");
		// Recorro lista de joins, añadiéndolos
		for (int i = 0; i < joins.size(); i++) {
			// Tipo de join
			sb.append(" " + joins.get(i).getJoinType().getValue() + " ");
			// Uso como alias de la tabla principal el propio nombre de la clase
			sb.append((joins.get(i).getAliasTablaPadre() != null ? joins.get(i).getAliasTablaPadre()
					: entityClass.getSimpleName()) + ".");
			// Campo
			sb.append(joins.get(i).getCampo());
			// Alias de tabla del campo
			sb.append(joins.get(i).getAliasTabla() != null ? " " + joins.get(i).getAliasTabla() : "");
		}
		return sb.toString();
	}

	/**
	 * Devuelve unos filtros HQL a partir de una lista de FilterClauses.
	 * 
	 * @param filtros
	 * @return String
	 */
	private String getWhereStatement(LinkedList<FilterClause> filtros) {
		StringBuilder sb = new StringBuilder(" ");
		// Recorro lista de filtros, añadiéndolos
		for (int i = 0; i < filtros.size(); i++) {
			// Si es el primer filtro, añadir WHERE; si no lo es entonces se
			// pone el operador
			if (i == 0) {
				sb.append("WHERE ");
			} else {
				sb.append(" " + filtros.get(i).getTipoOperador().getValue() + " ");
			}
			// Paréntesis por delante
			for (int j = 0; j < filtros.get(i).getNumeroParentesisDelante(); j++) {
				sb.append("(");
			}
			// Si no tiene alias, se usa el de la clase
			sb.append((filtros.get(i).getAliasTabla() != null ? filtros.get(i).getAliasTabla()
					: entityClass.getSimpleName()) + ".");
			// Campo
			sb.append(filtros.get(i).getCampo() + " ");
			// Tipo de filtro
			sb.append(filtros.get(i).getTipoFiltro().getValue() + " ");

			// Filtros
			Object[] filters = Arrays.copyOf(filtros.get(i).getFilters(), filtros.get(i).getFilters().length);
			// Si el filtro es un String, hay que encerrarlo entre comillas
			// simples
			for (int j = 0; j < filters.length; j++) {
				if (filters[j] instanceof String) {
					filters[j] = ("'" + filters[j].toString() + "'");
				}
			}
			// Declaro este boolean para saber si tengo que añadir el filtro
			// después del switch-case
			boolean addFiltro = true;
			// Compruebo el tipo de filtro para hacer las transformaciones
			// necesarias en cada caso
			switch (EnumFilterTypes.convertIgnoreCase(filtros.get(i).getTipoFiltro().getValue())) {
			case IN:
			case NOT_IN:
				// En caso de que sea IN, encerramos los filtros entre
				// paréntesis y los separamos por comas
				sb.append("(");
				for (int j = 0; j < filters.length; j++) {
					if (j != 0) {
						sb.append(",");
					}
					sb.append(filters[j]);
				}
				sb.append(")");
				// No voy a añadir filtro, ya está añadido
				addFiltro = false;
				break;
			case BETWEEN:
				// En caso de between, debe haber obligatoriamente dos filtros
				sb.append(filters[0]);
				sb.append(" AND ");
				sb.append(filters[1]);
				// No voy a añadir filtro, ya está añadido
				addFiltro = false;
				break;
			case LIKE:
				// En caso de LIKE, le añado dos comodines a los filtros de tipo
				// String
				String aux = null;
				for (int j = 0; j < filters.length; j++) {
					if (filters[j] instanceof String) {
						// Quitamos primero las comillas simples (primer y
						// último carácter)
						aux = filters[j].toString().substring(1, filters[j].toString().length() - 1);
						// Comillas simples más comodines
						filters[j] = ("'%" + aux + "%'");
					}
				}
				// Puedo añadir este filtro después sin problema
				break;
			case IS_NULL:
			case IS_NOT_NULL:
				// No se añade filtro porque no hace falta en este caso
				addFiltro = false;
				break;
			case IGUAL:
			case DISTINTO:
			case MAYOR:
			case MENOR:
			case MAYOR_IGUAL:
			case MENOR_IGUAL:
			case UNDEFINED:
			default:
				break;
			}
			// Uso addFiltro para saber si tengo que añadir el filtro
			// En principio sólo va a haber un filtro si llega hasta aquí, el
			// array es principalmente para el caso de que sea una consulta IN,
			// BETWEEN...
			if (addFiltro) {
				// Añadir filtro
				sb.append(filters[0]);
			}

			// Paréntesis por detrás
			for (int j = 0; j < filtros.get(i).getNumeroParentesisDetras(); j++) {
				sb.append(")");
			}
		}
		return sb.toString();
	}

	/**
	 * Obtiene una cláusula GROUP BY a partir de una lista de objetos GroupByClause
	 * 
	 * @param groupBys
	 * @return String
	 */
	private String getGroupByStatement(LinkedList<GroupByClause> groupBys) {
		StringBuilder sb = new StringBuilder(" ");
		// Recorro lista de filtros, añadiéndolos
		for (int i = 0; i < groupBys.size(); i++) {
			// Encadenar las cláusulas GROUP BY con comas
			if (i != 0) {
				sb.append(",");
			} else {
				sb.append("GROUP BY ");
			}
			// Alias de la tabla
			sb.append((groupBys.get(i).getAliasTabla() != null ? groupBys.get(i).getAliasTabla()
					: entityClass.getSimpleName()) + ".");
			// Campo por el que agrupar
			sb.append(groupBys.get(i).getCampo());
		}
		return sb.toString();
	}

	/**
	 * Devuelve una cláusula ORDER BY para HQL a partir de una lista de
	 * OrderByClauses.
	 * 
	 * @param filtros
	 * @return String
	 */
	private String getOrderByStatement(LinkedList<OrderByClause> orden) {
		StringBuilder sb = new StringBuilder(" ");
		// Recorro lista de filtros, añadiéndolos
		for (int i = 0; i < orden.size(); i++) {
			// Encadenar las cláusulas ORDER BY con comas
			if (i != 0) {
				sb.append(",");
			} else {
				sb.append("ORDER BY ");
			}
			// Alias de la tabla
			sb.append(
					(orden.get(i).getAliasTabla() != null ? orden.get(i).getAliasTabla() : entityClass.getSimpleName())
							+ ".");
			// Campo por el que ordenar
			sb.append(orden.get(i).getCampo() + " ");
			// Tipo de orden
			sb.append(orden.get(i).getTipoOrden().getValue());
		}
		return sb.toString();
	}

	/**
	 * Devuelve la lista de campos del la cláusula SELECT.
	 * 
	 * @param fields
	 * @param addDistinct
	 * @return String
	 */
	private String getSelectFieldStatement(LinkedList<FieldClause> fields) {
		StringBuilder sb = new StringBuilder(" ");
		for (int i = 0; i < fields.size(); i++) {
			if (i != 0) {
				sb.append(",");
			}
			sb.append((fields.get(i).isDistinct() ? "DISTINCT " : "")
					+ (fields.get(i).getAliasTabla() != null ? fields.get(i).getAliasTabla()
							: entityClass.getSimpleName())
					+ "." + fields.get(i).getCampo());
		}
		return sb.toString();
	}

}
