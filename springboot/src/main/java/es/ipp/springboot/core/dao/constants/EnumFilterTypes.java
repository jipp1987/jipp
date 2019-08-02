package es.ipp.springboot.core.dao.constants;

/**
 * Enumerado para tipos de filtros de HQL.
 * 
 * @author ignacio
 *
 */
public enum EnumFilterTypes {

	UNDEFINED(""), IGUAL("="), DISTINTO("<>"), LIKE("LIKE"), MAYOR(">"), MENOR("<"), MAYOR_IGUAL(">="),
	MENOR_IGUAL("<="), IN("IN"), NOT_IN("NOT IN"), IS_NULL("IS NULL"), IS_NOT_NULL("IS NOT NULL"), BETWEEN("BETWEEN");

	private EnumFilterTypes(String value) {
		this.value = value;
	}

	private String value;

	public String getValue() {
		return value;
	}

	/**
	 * Devuelve un Enumerado a partir de un String comparando su valor.
	 * 
	 * @param value
	 * @return EnumFilterTypes
	 */
	public static EnumFilterTypes convert(String value) {
		EnumFilterTypes e = UNDEFINED;
		final EnumFilterTypes[] values = values();
		for (EnumFilterTypes enumerado : values) {
			if (enumerado.getValue().equals(value)) {
				e = enumerado;
				break;
			}
		}
		return e;
	}

	/**
	 * Devuelve un Enumerado a partir de un String comparando su valor (case
	 * indifferent).
	 * 
	 * @param value
	 * @return EnumFilterTypes
	 */
	public static EnumFilterTypes convertIgnoreCase(String value) {
		EnumFilterTypes e = UNDEFINED;
		final EnumFilterTypes[] values = values();
		for (EnumFilterTypes enumerado : values) {
			if (enumerado.getValue().equalsIgnoreCase(value)) {
				e = enumerado;
				break;
			}
		}
		return e;
	}

	/**
	 * Devuelve un Enumerado a partir de su posici�n en el array que conforman todos
	 * elementos de este enumerado.
	 * 
	 * @param indice
	 * @return EnumFilterTypes
	 */
	public static EnumFilterTypes convert(int indice) {
		final EnumFilterTypes[] values = values();
		return indice >= 0 && indice < values.length ? values[indice] : UNDEFINED;
	}

}
