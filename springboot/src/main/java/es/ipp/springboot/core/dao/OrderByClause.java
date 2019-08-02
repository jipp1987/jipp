package es.ipp.springboot.core.dao;

import es.ipp.springboot.core.dao.constants.EnumOrderByTypes;

/**
 * Clase para la cláusula ORDER BY de HQL.
 * 
 * @author ignacio
 *
 */
public class OrderByClause {

	// CAMPOS
	/**
	 * Campos por el que se va a ordenar.
	 */
	private String campo;
	/**
	 * Tipo de orden seg�n enumerado.
	 */
	private EnumOrderByTypes tipoOrden;
	/**
	 * Alias de la tabla del campo.
	 */
	private String aliasTabla;

	// CONSTRUCTOR
	public OrderByClause(String campo, EnumOrderByTypes tipoOrden) {
		this.campo = campo;
		this.tipoOrden = tipoOrden;
		this.aliasTabla = null;
	}

	public OrderByClause(String campo, String aliasTabla, EnumOrderByTypes tipoOrden) {
		this.campo = campo;
		this.tipoOrden = tipoOrden;
		this.aliasTabla = aliasTabla;
	}

	// GETTERS Y SETTERS
	public String getCampo() {
		return campo;
	}

	public void setCampo(String campo) {
		this.campo = campo;
	}

	public EnumOrderByTypes getTipoOrden() {
		return tipoOrden;
	}

	public void setTipoOrden(EnumOrderByTypes tipoOrden) {
		this.tipoOrden = tipoOrden;
	}

	public String getAliasTabla() {
		return aliasTabla;
	}

	public void setAliasTabla(String aliasTabla) {
		this.aliasTabla = aliasTabla;
	}

	// M�TODOS
	@Override
	public String toString() {
		return "OrderByClause [campo=" + campo + ", tipoOrden=" + tipoOrden + ", aliasTabla="
				+ (aliasTabla != null ? aliasTabla : "") + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((aliasTabla == null) ? 0 : aliasTabla.hashCode());
		result = prime * result + ((campo == null) ? 0 : campo.hashCode());
		result = prime * result + ((tipoOrden == null) ? 0 : tipoOrden.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrderByClause other = (OrderByClause) obj;
		if (aliasTabla == null) {
			if (other.aliasTabla != null)
				return false;
		} else if (!aliasTabla.equals(other.aliasTabla))
			return false;
		if (campo == null) {
			if (other.campo != null)
				return false;
		} else if (!campo.equals(other.campo))
			return false;
		if (tipoOrden != other.tipoOrden)
			return false;
		return true;
	}

}
