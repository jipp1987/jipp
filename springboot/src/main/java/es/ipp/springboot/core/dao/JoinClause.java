package es.ipp.springboot.core.dao;

import java.io.Serializable;

import es.ipp.springboot.core.dao.constants.EnumJoinTypes;

/**
 * Cláusulas Join Fetch para consultas HQL.
 * 
 * @author ignacio
 *
 */
public class JoinClause implements Serializable {

	private static final long serialVersionUID = 7742090964447127269L;

	// CAMPOS
	/**
	 * Campo que relaciona la tabla principal con la subordinada.
	 */
	private String campo;
	/**
	 * Tipo de join HQL.
	 */
	private EnumJoinTypes joinType;
	/**
	 * Alias HQL de la tabla padre.
	 */
	private String aliasTablaPadre;
	/**
	 * Alias HQL de la tabla.
	 */
	private String aliasTabla;

	// CONSTRUCTOR
	public JoinClause() {

	}

	/**
	 * 
	 * @param campo
	 * @param joinType
	 */
	public JoinClause(String campo, EnumJoinTypes joinType) {
		this.campo = campo;
		this.joinType = joinType;
		this.aliasTablaPadre = null;
		this.aliasTabla = null;
	}

	/**
	 * 
	 * @param campo
	 * @param joinType
	 * @param aliasTablaPadre
	 * @param aliasTabla
	 */
	public JoinClause(String campo, EnumJoinTypes joinType, String aliasTablaPadre, String aliasTabla) {
		this.campo = campo;
		this.joinType = joinType;
		this.aliasTablaPadre = aliasTablaPadre;
		this.aliasTabla = aliasTabla;
	}

	// GETTERS Y SETTERS
	public String getCampo() {
		return campo;
	}

	public void setCampo(String campo) {
		this.campo = campo;
	}

	public EnumJoinTypes getJoinType() {
		return joinType;
	}

	public void setJoinType(EnumJoinTypes joinType) {
		this.joinType = joinType;
	}

	public String getAliasTablaPadre() {
		return aliasTablaPadre;
	}

	public void setAliasTablaPadre(String aliasTablaPadre) {
		this.aliasTablaPadre = aliasTablaPadre;
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
		return "JoinClause [campo=" + campo + ", joinType=" + joinType + ", aliasTablaPadre="
				+ (aliasTablaPadre != null ? aliasTablaPadre : "") + ", aliasTabla="
				+ (aliasTabla != null ? aliasTabla : "") + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((aliasTabla == null) ? 0 : aliasTabla.hashCode());
		result = prime * result + ((aliasTablaPadre == null) ? 0 : aliasTablaPadre.hashCode());
		result = prime * result + ((campo == null) ? 0 : campo.hashCode());
		result = prime * result + ((joinType == null) ? 0 : joinType.hashCode());
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
		JoinClause other = (JoinClause) obj;
		if (aliasTabla == null) {
			if (other.aliasTabla != null)
				return false;
		} else if (!aliasTabla.equals(other.aliasTabla))
			return false;
		if (aliasTablaPadre == null) {
			if (other.aliasTablaPadre != null)
				return false;
		} else if (!aliasTablaPadre.equals(other.aliasTablaPadre))
			return false;
		if (campo == null) {
			if (other.campo != null)
				return false;
		} else if (!campo.equals(other.campo))
			return false;
		if (joinType != other.joinType)
			return false;
		return true;
	}

}
