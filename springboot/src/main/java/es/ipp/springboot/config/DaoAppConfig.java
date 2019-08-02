package es.ipp.springboot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import es.ipp.springboot.dao.IClienteDao;
import es.ipp.springboot.dao.impl.ClienteDaoImpl;

/**
 * Configuración de DAO de Spring.
 * 
 * @author ignacio
 *
 */
@Configuration
public class DaoAppConfig {

	@Bean(name = "clienteDao")
	public IClienteDao getClienteDao() {
		return new ClienteDaoImpl();
	}
	
}
