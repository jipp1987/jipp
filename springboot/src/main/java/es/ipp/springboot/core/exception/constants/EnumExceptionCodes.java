package es.ipp.springboot.core.exception.constants;

/**
 * Códigos de excepción.
 * 
 * @author ignacio
 *
 */
public enum EnumExceptionCodes {

	UNDEFINED(""), ENTITY_NOT_FOUND("E"), SQL_CONSTRAINT("W");

	private EnumExceptionCodes(String value) {
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
	 * @return EnumExceptionLevels
	 */
	public static EnumExceptionCodes convert(String value) {
		EnumExceptionCodes e = UNDEFINED;
		final EnumExceptionCodes[] values = values();
		for (EnumExceptionCodes enumerado : values) {
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
	 * @return EnumExceptionLevels
	 */
	public static EnumExceptionCodes convertIgnoreCase(String value) {
		EnumExceptionCodes e = UNDEFINED;
		final EnumExceptionCodes[] values = values();
		for (EnumExceptionCodes enumerado : values) {
			if (enumerado.getValue().equalsIgnoreCase(value)) {
				e = enumerado;
				break;
			}
		}
		return e;
	}

	/**
	 * Devuelve un Enumerado a partir de su posición en el array que conforman todos
	 * elementos de este enumerado.
	 * 
	 * @param indice
	 * @return EnumExceptionLevels
	 */
	public static EnumExceptionCodes convert(int indice) {
		final EnumExceptionCodes[] values = values();
		return indice >= 0 && indice < values.length ? values[indice] : UNDEFINED;
	}

}
