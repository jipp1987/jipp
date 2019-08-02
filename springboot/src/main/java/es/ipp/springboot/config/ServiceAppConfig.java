package es.ipp.springboot.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import es.ipp.springboot.dao.IClienteDao;
import es.ipp.springboot.service.IClienteService;
import es.ipp.springboot.service.impl.ClienteServiceImpl;

/**
 * Configuración de Service de Spring.
 * 
 * @author ignacio
 *
 */
@Configuration
public class ServiceAppConfig {

	@Bean(name = "clienteService")
	public IClienteService getClienteService(@Qualifier("clienteDao") IClienteDao clienteDao) {
		return new ClienteServiceImpl(clienteDao);
	}
	
}
