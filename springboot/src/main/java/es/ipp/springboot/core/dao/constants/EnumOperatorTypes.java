package es.ipp.springboot.core.dao.constants;

/**
 * Enumerado para operadores de filtros HQL.
 * 
 * @author ignacio
 *
 */
public enum EnumOperatorTypes {

	UNDEFINED(""), AND("AND"), OR("OR"), XOR("XOR"), NOT("NOT");

	private String value;

	public String getValue() {
		return value;
	}

	private EnumOperatorTypes(String value) {
		this.value = value;
	}

	/**
	 * Devuelve un Enumerado a partir de un String comparando su valor.
	 * 
	 * @param value
	 * @return EnumOperatorTypes
	 */
	public static EnumOperatorTypes convert(String value) {
		EnumOperatorTypes e = UNDEFINED;
		final EnumOperatorTypes[] values = values();
		for (EnumOperatorTypes enumerado : values) {
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
	 * @return EnumOperatorTypes
	 */
	public static EnumOperatorTypes convertIgnoreCase(String value) {
		EnumOperatorTypes e = UNDEFINED;
		final EnumOperatorTypes[] values = values();
		for (EnumOperatorTypes enumerado : values) {
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
	 * @return EnumOperatorTypes
	 */
	public static EnumOperatorTypes convert(int indice) {
		final EnumOperatorTypes[] values = values();
		return indice >= 0 && indice < values.length ? values[indice] : UNDEFINED;
	}
	
}
