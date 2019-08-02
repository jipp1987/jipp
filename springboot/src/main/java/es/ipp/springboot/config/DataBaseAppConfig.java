package es.ipp.springboot.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.Primary;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

/**
 * Fichero de configuración de la base de datos.
 * 
 * @author ignacio
 *
 */
@Configuration
@ImportResource({ "classpath*:transactionalContext.xml" }) // Transacciones definidas en XML
public class DataBaseAppConfig {

	/**
	 * Definición del Datasource de la aplicación. En él está definido el Driver,
	 * url de la base de datos y las credenciales de acceso a la misma.
	 * 
	 * @return DataSource
	 */
	@Primary
	@Bean(name = "dataSource")
	public DataSource getDataSource() {
		DataSourceBuilder<?> dataSourceBuilder = DataSourceBuilder.create();
		dataSourceBuilder.driverClassName("com.mysql.cj.jdbc.Driver");
		dataSourceBuilder.url(
				"jdbc:mysql://localhost:3306/springboot?useSSL=false&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC");
		dataSourceBuilder.username("root");
		dataSourceBuilder.password("root");

		return dataSourceBuilder.build();
	}

	/**
	 * Configuración del EntityManager, el objeto empleado para realizar las
	 * operaciones con la base de datos.
	 * 
	 * @param dataSource
	 * @return LocalContainerEntityManagerFactoryBean
	 */
	@Primary
	@Bean(name = "entityManagerFactory")
	public LocalContainerEntityManagerFactoryBean getEntityManagerFactory(
			@Qualifier("dataSource") DataSource dataSource) {
		LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		em.setDataSource(dataSource);
		em.setPackagesToScan(new String[] { "es.ipp.springboot" });
		em.setPersistenceUnitName("ORM_MySQL57");

		JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		em.setJpaVendorAdapter(vendorAdapter);

		Properties properties = new Properties();
		properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL57Dialect");
		properties.setProperty("hibernate.hbm2ddl.auto", "none");
		em.setJpaProperties(properties);

		return em;
	}

}
