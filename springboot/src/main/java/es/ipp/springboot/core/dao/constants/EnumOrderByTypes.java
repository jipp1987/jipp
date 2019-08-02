package es.ipp.springboot.core.dao.constants;

/**
 * Enumerado para cláusula ORDER BY de HQL.
 * 
 * @author ignacio
 *
 */
public enum EnumOrderByTypes {

	UNDEFINED(""), ASC("ASC"), DESC("DESC");

	private EnumOrderByTypes(String value) {
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
	 * @return EnumOrderByTypes
	 */
	public static EnumOrderByTypes convert(String value) {
		EnumOrderByTypes e = UNDEFINED;
		final EnumOrderByTypes[] values = values();
		for (EnumOrderByTypes enumerado : values) {
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
	 * @return EnumOrderByTypes
	 */
	public static EnumOrderByTypes convertIgnoreCase(String value) {
		EnumOrderByTypes e = UNDEFINED;
		final EnumOrderByTypes[] values = values();
		for (EnumOrderByTypes enumerado : values) {
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
	 * @return EnumOrderByTypes
	 */
	public static EnumOrderByTypes convert(int indice) {
		final EnumOrderByTypes[] values = values();
		return indice >= 0 && indice < values.length ? values[indice] : UNDEFINED;
	}

}
