package es.ipp.springboot.core.controller;

import java.io.Serializable;
import java.util.LinkedList;

import es.ipp.springboot.core.dao.FilterClause;
import es.ipp.springboot.core.dao.GroupByClause;
import es.ipp.springboot.core.dao.JoinClause;
import es.ipp.springboot.core.dao.OrderByClause;

/**
 * Objecto de petición HTTP para peticiones POST a los RestControllers.
 * 
 * @author ignacio
 *
 */
public class PostRequestEntity implements Serializable {

	private static final long serialVersionUID = 2279657839829790523L;

	// CAMPOS
	/**
	 * Filtros para la consulta de la tabla.
	 */
	private LinkedList<FilterClause> filters;
	/**
	 * Orden para la consulta sobre la tabla.
	 */
	private LinkedList<OrderByClause> order;
	/**
	 * Group bys para la consulta sobre la tabla.
	 */
	private LinkedList<GroupByClause> groupBys;
	/**
	 * Joins para la consulta sobre la tabla.
	 */
	private LinkedList<JoinClause> joins;
	/**
	 * Límites de la consulta.
	 */
	private int[] limits;

	/**
	 * Objecto genérico enviado desde el cliente, normalmente de alguna clase del
	 * modelo de datos.
	 */
	private Object data;

	// CONSTRUCTOR
	public PostRequestEntity() {
		super();
	}

	public PostRequestEntity(LinkedList<FilterClause> filters, LinkedList<OrderByClause> order,
			LinkedList<GroupByClause> groupBys, LinkedList<JoinClause> joins, int[] limits, Object data) {
		super();
		this.filters = filters;
		this.order = order;
		this.groupBys = groupBys;
		this.joins = joins;
		this.limits = limits;
		this.data = data;
	}

	// GETTERS Y SETTERS
	public LinkedList<FilterClause> getFilters() {
		return filters;
	}

	public void setFilters(LinkedList<FilterClause> filters) {
		this.filters = filters;
	}

	public LinkedList<OrderByClause> getOrder() {
		return order;
	}

	public void setOrder(LinkedList<OrderByClause> order) {
		this.order = order;
	}

	public LinkedList<JoinClause> getJoins() {
		return joins;
	}

	public void setJoins(LinkedList<JoinClause> joins) {
		this.joins = joins;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public LinkedList<GroupByClause> getGroupBys() {
		return groupBys;
	}

	public void setGroupBys(LinkedList<GroupByClause> groupBys) {
		this.groupBys = groupBys;
	}

	public int[] getLimits() {
		return limits;
	}

	public void setLimits(int[] limits) {
		this.limits = limits;
	}

}
