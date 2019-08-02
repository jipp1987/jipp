package es.ipp.springboot.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.ipp.springboot.core.controller.IBaseController;
import es.ipp.springboot.core.exception.AppException;
import es.ipp.springboot.dao.IClienteDao;
import es.ipp.springboot.entity.Cliente;
import es.ipp.springboot.service.IClienteService;

/**
 * Controller de Cliente.
 * 
 * RestController Es una combinación de las anotaciones Controller y
 * ResponseBody.
 * 
 * Controller Se usa para definir un controlador.
 * 
 * ResponseBody Se usa para indicar que el valor de retorno de un método debe
 * usarse como el cuerpo de respuesta de la solicitud.
 * 
 * RequestMapping("/api")declara que la url para todas las apis en este
 * controlador comenzará con /api.
 * 
 * @author ignacio
 *
 */
@RestController
@RequestMapping("/api")
public class ClienteController implements IBaseController<Cliente, Long> {

	/**
	 * Acceso a datos de clientes.
	 */
	@Autowired
	private IClienteService clienteService;

	@Override
	@GetMapping("/clientes")
	public List<Cliente> findAll() {
		return this.clienteService.findByFilteredQuery(null, null, null);
	}

	/**
	 * Crea un nuevo cliente en la base de datos.
	 * 
	 * RequestBody se utiliza para enlazar el cuerpo de la solicitud con un
	 * parámetro de método.
	 * 
	 * Valid se asegura de que el cuerpo de la solicitud es válido. Si el cuerpo de
	 * la solicitud no tiene un título o un contenido, Spring devolverá un 400
	 * BadRequesterror al cliente.
	 * 
	 * @param cliente
	 * @return Cliente
	 */
	@Override
	@PostMapping("/clientes")
	public Cliente create(@Valid @RequestBody Cliente cliente) {
		try {
			this.clienteService.create(cliente);
			return cliente;
		} catch (AppException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * PathVariable se utiliza para vincular una variable de ruta con un parámetro
	 * de método.
	 */
	@Override
	@GetMapping("/clientes/{id}")
	public Cliente findById(@PathVariable(value = "id") Long clienteId) {
		// Si no lo encuentra, lanzamos ResourceNotFoundException, la cual redirigirá
		// automáticamente a una página de error.
		return this.clienteService.findById(clienteId, IClienteDao.CLIENTE_ID);
	}

	@Override
	@PutMapping("/clientes/{id}")
	public Cliente update(@PathVariable(value = "id") Long clienteId, @Valid @RequestBody Cliente clienteAux) {
		// Si no lo encuentra, lanzamos ResourceNotFoundException, la cual redirigirá
		// automáticamente a una página de error.
		Cliente cliente = this.clienteService.findById(clienteId, IClienteDao.CLIENTE_ID);

		cliente.setCodigo(clienteAux.getCodigo());
		cliente.setNombre(clienteAux.getNombre());
		cliente.setApellido1(clienteAux.getApellido1());
		cliente.setApellido2(clienteAux.getApellido2());
		cliente.setDni(clienteAux.getDni());
		cliente.setRiesgoConcedido(clienteAux.getRiesgoConcedido());

		try {
			this.clienteService.update(cliente);
		} catch (AppException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cliente;
	}

	@Override
	@DeleteMapping("/clientes/{id}")
	public ResponseEntity<?> delete(@PathVariable(value = "id") Long clienteId) {
		// automáticamente a una página de error.
//		Cliente cliente = this.clienteService.findById(clienteId)
//				.orElseThrow(() -> new ResourceNotFoundException("Cliente", "id", clienteId));
		Cliente cliente = this.clienteService.findById(clienteId, IClienteDao.CLIENTE_ID);

		try {
			this.clienteService.delete(cliente);
		} catch (AppException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return ResponseEntity.ok().build();
	}

}
