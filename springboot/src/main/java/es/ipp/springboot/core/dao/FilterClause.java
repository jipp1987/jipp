package es.ipp.springboot.core.dao;

import java.io.Serializable;
import java.util.Arrays;

import es.ipp.springboot.core.dao.constants.EnumFilterTypes;
import es.ipp.springboot.core.dao.constants.EnumOperatorTypes;

/**
 * Clase para las cláusulas de filtros de HQL.
 * 
 * @author ignacio
 *
 */
public class FilterClause implements Serializable {

	private static final long serialVersionUID = 591682896844125650L;

	// CAMPOS
	/**
	 * Campo por el que se quiere filtrar.
	 */
	private String campo;
	/**
	 * Tipo de filtro según enumerado.
	 */
	private EnumFilterTypes tipoFiltro;
	/**
	 * Tipo de operador para enlazar varios filtros según enumerado. Por defecto
	 * será AND.
	 */
	private EnumOperatorTypes tipoOperador;
	/**
	 * Objetos a comparar. Uso un array para el caso de que sea una consulta IN,
	 * BETWEEN, etc.
	 */
	private Object[] filters;
	/**
	 * Determina el número de paréntesis que tendrá delante el filtro. Uno por
	 * defecto.
	 */
	private int numeroParentesisDelante;
	/**
	 * Determina el número de par�ntesis que tendrá detrás el filtro. Uno por
	 * defecto.
	 */
	private int numeroParentesisDetras;
	/**
	 * Alias HQL de la tabla.
	 */
	private String aliasTabla;

	// CONSTRUCTOR
	/**
	 * Constructor por defecto para los envíos desde el front-end.
	 */
	public FilterClause() {
		this.tipoOperador = EnumOperatorTypes.AND;
		this.filters = new Object[1];
		this.numeroParentesisDelante = 1;
		this.numeroParentesisDetras = 1;
		this.aliasTabla = null;
	}

	/**
	 * Se emplea para hacer consultas normales, con un sólo par�metro para comparar
	 * (LIKE, <>, =...)
	 * 
	 * @param campo
	 * @param tipoFiltro
	 * @param filter
	 */
	public FilterClause(String campo, EnumFilterTypes tipoFiltro, Object filter) {
		this.campo = campo;
		this.tipoOperador = EnumOperatorTypes.AND;
		this.tipoFiltro = tipoFiltro;
		this.filters = new Object[1];
		this.filters[0] = filter;
		this.numeroParentesisDelante = 1;
		this.numeroParentesisDetras = 1;
		this.aliasTabla = null;
	}

	/**
	 * Se emplea para hacer consultas normales, con un sólo parámetro para comparar
	 * (LIKE, <>, =...)
	 * 
	 * @param campo
	 * @param tipoFiltro
	 * @param filter
	 */
	public FilterClause(String campo, EnumFilterTypes tipoFiltro, EnumOperatorTypes tipoOperador, Object filter) {
		this.campo = campo;
		this.tipoOperador = tipoOperador;
		this.tipoFiltro = tipoFiltro;
		this.filters = new Object[1];
		this.filters[0] = filter;
		this.numeroParentesisDelante = 1;
		this.numeroParentesisDetras = 1;
		this.aliasTabla = null;
	}

	/**
	 * 
	 * @param campo
	 * @param aliasTabla
	 * @param tipoFiltro
	 * @param filter
	 */
	public FilterClause(String campo, String aliasTabla, EnumFilterTypes tipoFiltro, Object filter) {
		this.campo = campo;
		this.tipoOperador = EnumOperatorTypes.AND;
		this.tipoFiltro = tipoFiltro;
		this.filters = new Object[1];
		this.filters[0] = filter;
		this.numeroParentesisDelante = 1;
		this.numeroParentesisDetras = 1;
		this.aliasTabla = aliasTabla;
	}

	/**
	 * Se emplea para hacer consultas normales, con un sólo parámetro para comparar
	 * (LIKE, <>, =...)
	 * 
	 * @param campo
	 * @param tipoFiltro
	 * @param numeroParentesisDelante
	 * @param numeroParentesisDetras
	 * @param filter
	 */
	public FilterClause(String campo, EnumFilterTypes tipoFiltro, int numeroParentesisDelante,
			int numeroParentesisDetras, Object filter) {
		this.campo = campo;
		this.tipoOperador = EnumOperatorTypes.AND;
		this.tipoFiltro = tipoFiltro;
		this.filters = new Object[1];
		this.filters[0] = filter;
		this.numeroParentesisDelante = numeroParentesisDelante;
		this.numeroParentesisDetras = numeroParentesisDetras;
		this.aliasTabla = null;
	}

	/**
	 * 
	 * @param campo
	 * @param aliasTabla
	 * @param tipoFiltro
	 * @param numeroParentesisDelante
	 * @param numeroParentesisDetras
	 * @param filter
	 */
	public FilterClause(String campo, String aliasTabla, EnumFilterTypes tipoFiltro, int numeroParentesisDelante,
			int numeroParentesisDetras, Object filter) {
		this.campo = campo;
		this.tipoOperador = EnumOperatorTypes.AND;
		this.tipoFiltro = tipoFiltro;
		this.filters = new Object[1];
		this.filters[0] = filter;
		this.numeroParentesisDelante = numeroParentesisDelante;
		this.numeroParentesisDetras = numeroParentesisDetras;
		this.aliasTabla = aliasTabla;
	}

	/**
	 * 
	 * @param campo
	 * @param aliasTabla
	 * @param tipoFiltro
	 * @param tipoOperador
	 * @param numeroParentesisDelante
	 * @param numeroParentesisDetras
	 * @param filter
	 */
	public FilterClause(String campo, String aliasTabla, EnumFilterTypes tipoFiltro, EnumOperatorTypes tipoOperador,
			int numeroParentesisDelante, int numeroParentesisDetras, Object filter) {
		this.campo = campo;
		this.tipoOperador = tipoOperador;
		this.tipoFiltro = tipoFiltro;
		this.filters = new Object[1];
		this.filters[0] = filter;
		this.numeroParentesisDelante = numeroParentesisDelante;
		this.numeroParentesisDetras = numeroParentesisDetras;
		this.aliasTabla = aliasTabla;
	}

	/**
	 * Se emplea para hacer consultas normales, con un sólo par�metro para comparar
	 * (LIKE, <>, =...)
	 * 
	 * @param campo
	 * @param tipoOperador
	 * @param tipoFiltro
	 * @param filter
	 */
	public FilterClause(String campo, EnumOperatorTypes tipoOperador, EnumFilterTypes tipoFiltro, Object filter) {
		this.campo = campo;
		this.tipoOperador = tipoOperador;
		this.tipoFiltro = tipoFiltro;
		this.filters = new Object[1];
		this.filters[0] = filter;
		this.numeroParentesisDelante = 1;
		this.numeroParentesisDetras = 1;
		this.aliasTabla = null;
	}

	/**
	 * Se emplea para hacer consultas normales, con un sólo parámetro para comparar
	 * (LIKE, <>, =...)
	 * 
	 * @param campo
	 * @param tipoOperador
	 * @param tipoFiltro
	 * @param numeroParentesisDelante
	 * @param numeroParentesisDetras
	 * @param filter
	 */
	public FilterClause(String campo, EnumOperatorTypes tipoOperador, EnumFilterTypes tipoFiltro,
			int numeroParentesisDelante, int numeroParentesisDetras, Object filter) {
		this.campo = campo;
		this.tipoOperador = tipoOperador;
		this.tipoFiltro = tipoFiltro;
		this.filters = new Object[1];
		this.filters[0] = filter;
		this.numeroParentesisDelante = numeroParentesisDelante;
		this.numeroParentesisDetras = numeroParentesisDetras;
		this.aliasTabla = null;
	}

	/**
	 * Se emplea para hacer consultas que tienen más de un parámetro para comparar,
	 * por ejemplo IN o BETWEEN... Hay que pasarle por separado el filtro (array).
	 * 
	 * @param campo
	 * @param tipoFiltro
	 * @param numeroParentesisDelante
	 * @param numeroParentesisDetras
	 * @param filters
	 */
	public FilterClause(String campo, EnumFilterTypes tipoFiltro, EnumOperatorTypes tipoOperador,
			int numeroParentesisDelante, int numeroParentesisDetras) {
		this.campo = campo;
		this.tipoOperador = tipoOperador;
		this.tipoFiltro = tipoFiltro;
		this.numeroParentesisDelante = numeroParentesisDelante;
		this.numeroParentesisDetras = numeroParentesisDetras;
		this.aliasTabla = null;
		this.filters = null;
	}

	// GETTERS Y SETTERS
	public String getCampo() {
		return campo;
	}

	public void setCampo(String campo) {
		this.campo = campo;
	}

	public EnumFilterTypes getTipoFiltro() {
		return tipoFiltro;
	}

	public void setTipoFiltro(EnumFilterTypes tipoFiltro) {
		this.tipoFiltro = tipoFiltro;
	}

	public Object[] getFilters() {
		return filters;
	}

	public void setFilters(Object[] filters) {
		this.filters = filters;
	}

	public EnumOperatorTypes getTipoOperador() {
		return tipoOperador;
	}

	public void setTipoOperador(EnumOperatorTypes tipoOperador) {
		this.tipoOperador = tipoOperador;
	}

	public int getNumeroParentesisDelante() {
		return numeroParentesisDelante;
	}

	public void setNumeroParentesisDelante(int numeroParentesisDelante) {
		this.numeroParentesisDelante = numeroParentesisDelante;
	}

	public int getNumeroParentesisDetras() {
		return numeroParentesisDetras;
	}

	public void setNumeroParentesisDetras(int numeroParentesisDetras) {
		this.numeroParentesisDetras = numeroParentesisDetras;
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
		return "FilterClause [campo=" + campo + ", tipoFiltro=" + tipoFiltro + ", tipoOperador=" + tipoOperador
				+ ", filters=" + Arrays.toString(filters) + ", numeroParentesisDelante=" + numeroParentesisDelante
				+ ", numeroParentesisDetras=" + numeroParentesisDetras + ", aliasTabla="
				+ (aliasTabla != null ? aliasTabla : "") + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((aliasTabla == null) ? 0 : aliasTabla.hashCode());
		result = prime * result + ((campo == null) ? 0 : campo.hashCode());
		result = prime * result + Arrays.hashCode(filters);
		result = prime * result + numeroParentesisDelante;
		result = prime * result + numeroParentesisDetras;
		result = prime * result + ((tipoFiltro == null) ? 0 : tipoFiltro.hashCode());
		result = prime * result + ((tipoOperador == null) ? 0 : tipoOperador.hashCode());
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
		FilterClause other = (FilterClause) obj;
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
		if (!Arrays.equals(filters, other.filters))
			return false;
		if (numeroParentesisDelante != other.numeroParentesisDelante)
			return false;
		if (numeroParentesisDetras != other.numeroParentesisDetras)
			return false;
		if (tipoFiltro != other.tipoFiltro)
			return false;
		if (tipoOperador != other.tipoOperador)
			return false;
		return true;
	}

}
