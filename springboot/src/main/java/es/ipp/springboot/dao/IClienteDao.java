package es.ipp.springboot.dao;

import es.ipp.springboot.core.dao.IBaseDao;
import es.ipp.springboot.entity.Cliente;

/**
 * IDao de cliente.
 * 
 * @author ignacio
 *
 */
public interface IClienteDao extends IBaseDao<Cliente, Long> {

	static final String CLIENTE_ID = "id";
	static final String CODIGO = "codigo";
	static final String NOMBRE = "nombre";
	static final String APELLIDO_1 = "apellido1";
	static final String APELLIDO_2 = "apellido2";
	static final String DNI = "dni";
	static final String RIESGO_CONCEDIDO = "riesgoConcedido";
	static final String FECHA_CREACION = "fechaCreacion";
	static final String FECHA_ULT_MODIFICACION = "fechaUltModificacion";

}
