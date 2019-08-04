package es.ipp.springboot.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.ipp.springboot.core.controller.impl.BaseControllerImpl;
import es.ipp.springboot.entity.Cliente;
import es.ipp.springboot.service.IClienteService;

/**
 * Controller de Clientes.
 * 
 * RestController Es una combinación de las anotaciones Controller y
 * ResponseBody.
 * 
 * Controller Se usa para definir un controlador.
 * 
 * ResponseBody Se usa para indicar que el valor de retorno de un método debe
 * usarse como el cuerpo de respuesta de la solicitud.
 * 
 * RequestMapping("/clientes") declara que la url para todas las apis en este
 * controlador comenzará con /clientes
 * 
 * @author ignacio
 *
 */
@RestController
@RequestMapping("clientes")
public class ClienteController extends BaseControllerImpl<Cliente, Long, IClienteService> {


}
