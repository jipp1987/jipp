package es.ipp.springboot.core.dao;

import java.io.Serializable;

/**
 * Cláusula para seleccionar un campo en concreto de una entidad.
 * 
 * @author ignacio
 *
 */
public class FieldClause implements Serializable {

	private static final long serialVersionUID = 1029164908024175169L;
	
	// ATRIBUTOS
	/**
	 * Campo a seleccionar.
	 */
	private String campo;
	/**
	 * A�adir cl�usula DISTINCT.
	 */
	private boolean distinct;
	/**
	 * Alias de la tabla.
	 */
	private String aliasTabla;

	// CONSTRUCTOR
	/**
	 * 
	 * @param campo
	 */
	public FieldClause(String campo) {
		this.campo = campo;
		this.distinct = false;
		this.aliasTabla = null;
	}

	/**
	 * 
	 * @param campo
	 * @param aliasTabla
	 */
	public FieldClause(String campo, String aliasTabla) {
		this.campo = campo;
		this.distinct = false;
		this.aliasTabla = aliasTabla;
	}

	/**
	 * 
	 * @param campo
	 * @param distinct
	 */
	public FieldClause(String campo, boolean distinct) {
		this.campo = campo;
		this.distinct = distinct;
		this.aliasTabla = null;
	}

	/**
	 * 
	 * @param campo
	 * @param aliasTabla
	 * @param distinct
	 */
	public FieldClause(String campo, String aliasTabla, boolean distinct) {
		this.campo = campo;
		this.distinct = distinct;
		this.aliasTabla = aliasTabla;
	}

	// GETTERS Y SETTERS
	public String getCampo() {
		return campo;
	}

	public void setCampo(String campo) {
		this.campo = campo;
	}

	public boolean isDistinct() {
		return distinct;
	}

	public void setDistinct(boolean distinct) {
		this.distinct = distinct;
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
		return "FieldClause [campo=" + campo + ", distinct=" + distinct + ", aliasTabla="
				+ (aliasTabla != null ? aliasTabla : "") + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((aliasTabla == null) ? 0 : aliasTabla.hashCode());
		result = prime * result + ((campo == null) ? 0 : campo.hashCode());
		result = prime * result + (distinct ? 1231 : 1237);
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
		FieldClause other = (FieldClause) obj;
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
		if (distinct != other.distinct)
			return false;
		return true;
	}

}
