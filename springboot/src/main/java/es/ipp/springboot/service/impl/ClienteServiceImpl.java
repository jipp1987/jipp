package es.ipp.springboot.service.impl;

import java.util.Date;

import es.ipp.springboot.core.exception.AppException;
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

	@Override
	public void create(Cliente entity) throws AppException {
		entity.setFechaCreacion(new Date());
		entity.setFechaUltModificacion(new Date());

		this.dao.create(entity);
	}

	@Override
	public void update(Cliente entity) throws AppException {
		entity.setFechaUltModificacion(new Date());

		this.dao.update(entity);
	}

}
