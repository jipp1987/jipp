package es.ipp.springboot.core.controller.impl;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import es.ipp.springboot.core.controller.IBaseController;
import es.ipp.springboot.core.controller.PostRequestEntity;
import es.ipp.springboot.core.exception.AppException;
import es.ipp.springboot.core.service.IBaseService;
import es.ipp.springboot.entity.IBaseEntity;

/**
 * Clase abstracta de la que han de heredar los RestController de la aplicación.
 * 
 * @author ignacio
 *
 * @param <T>       Entidad base.
 * @param <PK>      Clase de la clave primaria de la entidad base.
 * @param <SERVICE> Service asociado al controller.
 */
public abstract class BaseControllerImpl<T extends IBaseEntity<PK>, PK, SERVICE extends IBaseService<T, PK>>
		implements IBaseController<T, PK> {

	// CAMPOS
	private static final Logger LOG = LogManager.getLogger(BaseControllerImpl.class);

	/**
	 * Clase de la entidad: obtengo la clase de la entidad a partir de los
	 * parámetros genéricos que se le pasan a la clase BaseDao (el primero es la
	 * entidad, por eso uso cero).
	 */
	@SuppressWarnings("unchecked")
	protected Class<T> entityClass = (Class<T>) (this.getClass().getGenericSuperclass() instanceof ParameterizedType
			? (ParameterizedType) this.getClass().getGenericSuperclass()
			: (ParameterizedType) this.getClass().getSuperclass().getGenericSuperclass()).getActualTypeArguments()[0];

	/**
	 * Servicio principal del controller.
	 */
	@Autowired
	protected SERVICE mainService;

	/**
	 * Objeto para convertir y transformar datos a/desde JSON.
	 */
	protected ObjectMapper objectMapper;

	// CONSTRUCTOR
	public BaseControllerImpl() {
		super();
		// Inicializar y configurar el objectMapper
		this.objectMapper = new ObjectMapper();
		this.objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true);
		this.objectMapper.configure(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES, true);
	}

	// FUNCIONES
	/**
	 * La URL sería la definida en cada RequestMapping de cada RestController / id a
	 * buscar. Esto es sólo un ejemplo de get, en realidad no es muy buena idea usar
	 * get para estas cosas, post es más seguro.
	 */
	@Override
	@GetMapping(value = "/{id}")
	public ResponseEntity<T> findById(@PathVariable(value = "id") PK id) {
		T entity = this.mainService.findById(id, null);
		return ResponseEntity.ok().body(entity);
	}

	@Override
	@PostMapping(path = "/find", consumes = "application/json", produces = "application/json")
	public ResponseEntity<List<T>> find(@RequestBody PostRequestEntity postRequestEntity) {
		List<T> list = this.mainService.findByFilteredQuery(postRequestEntity.getFilters(),
				postRequestEntity.getJoins(), postRequestEntity.getOrder(), postRequestEntity.getGroupBys(),
				postRequestEntity.getLimits());
		return new ResponseEntity<List<T>>(list, HttpStatus.OK);
	}

	@Override
	@PostMapping(path = "/create", consumes = "application/json", produces = "application/json")
	public ResponseEntity<T> createEntity(@RequestBody String jsonEntity) {
		T entity = null;
		try {
			// Transformar el objeto desde JSON
			entity = this.objectMapper.readValue(jsonEntity, this.entityClass);
			this.mainService.create(entity);
			return new ResponseEntity<T>(entity, HttpStatus.OK);
		} catch (IOException | AppException e) {
			LOG.error(e.getLocalizedMessage(), e);
			return new ResponseEntity<T>(entity, HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	@PostMapping(path = "/update", consumes = "application/json", produces = "application/json")
	public ResponseEntity<T> updateEntity(@RequestBody String jsonEntity) {
		T entity = null;
		try {
			// Transformar el objeto desde JSON
			entity = this.objectMapper.readValue(jsonEntity, this.entityClass);
			this.mainService.update(entity);
			return new ResponseEntity<T>(entity, HttpStatus.OK);
		} catch (IOException | AppException e) {
			LOG.error(e.getLocalizedMessage(), e);
			return new ResponseEntity<T>(entity, HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	@DeleteMapping("/{id}")
	public Map<String, Boolean> deleteEntity(@PathVariable(value = "id") PK id) {
		Map<String, Boolean> response = new HashMap<>();
		try {
			T entity = this.mainService.findById(id, null);
			this.mainService.delete(entity);
			response.put("deleted", true);
		} catch (AppException e) {
			LOG.error(e.getLocalizedMessage(), e);
			response.put("deleted", false);
		}

		return response;
	}

}
