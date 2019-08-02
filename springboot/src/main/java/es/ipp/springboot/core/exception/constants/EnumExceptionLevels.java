package es.ipp.springboot.core.exception.constants;

/**
 * Niveles de excepción.
 * 
 * @author ignacio
 *
 */
public enum EnumExceptionLevels {

	UNDEFINED(""), ERROR("E"), WARNING("W"), INFO("I");

	private EnumExceptionLevels(String value) {
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
	public static EnumExceptionLevels convert(String value) {
		EnumExceptionLevels e = UNDEFINED;
		final EnumExceptionLevels[] values = values();
		for (EnumExceptionLevels enumerado : values) {
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
	public static EnumExceptionLevels convertIgnoreCase(String value) {
		EnumExceptionLevels e = UNDEFINED;
		final EnumExceptionLevels[] values = values();
		for (EnumExceptionLevels enumerado : values) {
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
	public static EnumExceptionLevels convert(int indice) {
		final EnumExceptionLevels[] values = values();
		return indice >= 0 && indice < values.length ? values[indice] : UNDEFINED;
	}

}
