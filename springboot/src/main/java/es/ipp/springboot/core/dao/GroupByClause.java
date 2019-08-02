package es.ipp.springboot.core.dao;

/**
 * Cl�usulas GROUP BY para consultas HQL.
 * 
 * @author ignacio
 *
 */
public class GroupByClause {

	// ATRIBUTOS
	/**
	 * Campo por el que se va a agrupar.
	 */
	private String campo;
	/**
	 * Alias de la tabla del campo.
	 */
	private String aliasTabla;

	// CONSTRUCTOR
	/**
	 * 
	 * @param campo
	 */
	public GroupByClause(String campo) {
		this.campo = campo;
		this.aliasTabla = null;
	}

	/**
	 * 
	 * @param campo
	 * @param aliasTabla
	 */
	public GroupByClause(String campo, String aliasTabla) {
		this.campo = campo;
		this.aliasTabla = aliasTabla;
	}

	// GETTERS Y SETTERS
	public String getCampo() {
		return campo;
	}

	public void setCampo(String campo) {
		this.campo = campo;
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
		return "GroupByClause [campo=" + campo + ", aliasTabla=" + (aliasTabla != null ? aliasTabla : "") + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((aliasTabla == null) ? 0 : aliasTabla.hashCode());
		result = prime * result + ((campo == null) ? 0 : campo.hashCode());
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
		GroupByClause other = (GroupByClause) obj;
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
		return true;
	}

}
