package es.ipp.springboot.config;

import org.springframework.boot.autoconfigure.web.servlet.DispatcherServletAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.DispatcherServlet;

/**
 * Configuración de CrossOrigin para las peticiones a los servicios REST.
 * 
 * @author ignacio
 *
 */
@Configuration
public class CorsConfig {

	/**
	 * Filtro CrossOrigin.
	 * 
	 * @return CorsFilter.
	 */
	@Bean
	public CorsFilter corsFilter() {
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		CorsConfiguration config = new CorsConfiguration();
		config.setAllowCredentials(true);
		// Origen permitido
		config.addAllowedOrigin("*");
		// Cabeceras permitidas
		config.addAllowedHeader("*");
		// Métodos permitidos
		config.addAllowedMethod("OPTIONS");
		config.addAllowedMethod("PATCH");
		config.addAllowedMethod("GET");
		config.addAllowedMethod("POST");
		config.addAllowedMethod("PUT");
		config.addAllowedMethod("DELETE");
		source.registerCorsConfiguration("/**", config);

		return new CorsFilter(source);
	}

	/**
	 * Petición OPTIONS para evitar problemas con JavaScript.
	 * 
	 * @return DispatcherServlet
	 */
	@Bean(name = DispatcherServletAutoConfiguration.DEFAULT_DISPATCHER_SERVLET_BEAN_NAME)
	public DispatcherServlet dispatcherServlet() {
		DispatcherServlet dispatcherServlet = new DispatcherServlet();
		dispatcherServlet.setDispatchOptionsRequest(true);

		return dispatcherServlet;
	}

}
