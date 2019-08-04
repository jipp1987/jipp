package es.ipp.springboot.core.controller.constants;

/**
 * Enumerado de parámetros para peticiones POST para los RestController de la
 * aplicación.
 * 
 * @author ignacio
 *
 */
public enum EnumPostRequestParams {

	UNDEFINED(""), FILTER("filter"), ORDER_BY("orderBy"), JOINS("joins");

	private EnumPostRequestParams(String value) {
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
	public static EnumPostRequestParams convert(String value) {
		EnumPostRequestParams e = UNDEFINED;
		final EnumPostRequestParams[] values = values();
		for (EnumPostRequestParams enumerado : values) {
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
	public static EnumPostRequestParams convertIgnoreCase(String value) {
		EnumPostRequestParams e = UNDEFINED;
		final EnumPostRequestParams[] values = values();
		for (EnumPostRequestParams enumerado : values) {
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
	public static EnumPostRequestParams convert(int indice) {
		final EnumPostRequestParams[] values = values();
		return indice >= 0 && indice < values.length ? values[indice] : UNDEFINED;
	}
	
}
