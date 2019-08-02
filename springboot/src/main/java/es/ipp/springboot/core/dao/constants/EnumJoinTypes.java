package es.ipp.springboot.core.dao.constants;

/**
 * Enumerado de tipos de joins para HQL.
 * 
 * @author ignacio
 *
 */
public enum EnumJoinTypes {

	UNDEFINED(""), INNER_JOIN("INNER JOIN"), LEFT_JOIN("LEFT JOIN"), RIGHT_JOIN("RIGHT JOIN"), INNER_JOIN_FETCH(
			"INNER JOIN FETCH"), LEFT_JOIN_FETCH("LEFT JOIN FETCH"), RIGHT_JOIN_FECTH("RIGHT JOIN FETCH");

	private EnumJoinTypes(String value) {
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
	 * @return EnumJoinTypes
	 */
	public static EnumJoinTypes convert(String value) {
		EnumJoinTypes e = UNDEFINED;
		final EnumJoinTypes[] values = values();
		for (EnumJoinTypes enumerado : values) {
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
	 * @return EnumJoinTypes
	 */
	public static EnumJoinTypes convertIgnoreCase(String value) {
		EnumJoinTypes e = UNDEFINED;
		final EnumJoinTypes[] values = values();
		for (EnumJoinTypes enumerado : values) {
			if (enumerado.getValue().equalsIgnoreCase(value)) {
				e = enumerado;
				break;
			}
		}
		return e;
	}

	/**
	 * Devuelve un Enumerado a partir de su posici�n en el array que conforman
	 * todos elementos de este enumerado.
	 * 
	 * @param indice
	 * @return EnumJoinTypes
	 */
	public static EnumJoinTypes convert(int indice) {
		final EnumJoinTypes[] values = values();
		return indice >= 0 && indice < values.length ? values[indice] : UNDEFINED;
	}
	
}
