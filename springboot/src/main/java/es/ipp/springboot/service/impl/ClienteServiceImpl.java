package es.ipp.springboot.service.impl;

import es.ipp.springboot.core.service.impl.BaseServiceImpl;
import es.ipp.springboot.dao.IClienteDao;
import es.ipp.springboot.entity.Cliente;
import es.ipp.springboot.service.IClienteService;

/**
 * Implementación de servicio de clientes.
 * 
 * @author ignacio
 *
 */
public class ClienteServiceImpl extends BaseServiceImpl<Cliente, Long, IClienteDao> implements IClienteService {

	// CONSTRUCTOR
	public ClienteServiceImpl(IClienteDao dao) {
		super(dao);
	}

}
