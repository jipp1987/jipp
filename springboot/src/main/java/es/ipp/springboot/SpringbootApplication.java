package es.ipp.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * @SpringBootApplication es equivalente a las siguientes anotaciones:
 * 
 * @Configuration: Esta anotación se utiliza para indicar que la clase puede
 *                 contener beans que serán registrados al iniciar la
 *                 aplicación.
 * @EnableAutoConfiguration: Con esta anotación se le indica a Spring que se
 *                           encargue de configurar todas las dependencias que
 *                           tengamos en el proyecto.
 * @ComponentScan: Permite que se escaneen todos los @Component que se encuentre
 *                 dentro del paquete en el que se define, se puede configurar
 *                 para que busque en los paquetes que queramos.
 * 
 * @author ignacio
 *
 */
@SpringBootApplication
public class SpringbootApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootApplication.class, args);
	}
	
	@Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(SpringbootApplication.class);
    }

}
