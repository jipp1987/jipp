package es.ipp.springboot.entity;

import java.io.Serializable;

import com.google.gson.Gson;

/**
 * Interfaz que deben implementar los modelos de la aplicación.
 * 
 * @author ignacio
 *
 */
public interface IBaseEntity<PK> extends Serializable {

	/**
	 * Devuelve la clave primaria.
	 * 
	 * @return PK
	 */
	PK givePK();

	/**
	 * Devuelve true si la clave primaria existe.
	 * 
	 * @return
	 */
	boolean isPKExists();

	/**
	 * Devuelve una copia profunda del objeto. Crea un objeto Gson en la propia
	 * función.
	 * 
	 * @return IBaseEntity<PK>
	 */
	@SuppressWarnings("unchecked")
	default IBaseEntity<PK> deepCopy() {
		Gson gson = new Gson();
		IBaseEntity<PK> deepCopy = gson.fromJson(gson.toJson(this), this.getClass());
		gson = null;
		return deepCopy;
	}

	/**
	 * Devuelve una copia profunda del objeto. Espera un objecto Gson para realizar
	 * la copia.
	 * 
	 * @param gson
	 * @return IBaseEntity<PK>
	 */
	@SuppressWarnings("unchecked")
	default IBaseEntity<PK> deepCopy(Gson gson) {
		IBaseEntity<PK> deepCopy = gson.fromJson(gson.toJson(this), this.getClass());
		return deepCopy;
	}

}
