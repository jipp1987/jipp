package es.ipp.springboot.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Modelo de cliente.
 * 
 * @author ignacio
 *
 */
@Entity
@Table(name = "clientes")
// Spring Boot utiliza JACKSON para serializar y deserializar objetos Java desde y hacia JSON
// Usamos JsonIgnoreProperties porque no queremos que los usuarios le den valores a los campos fechaCreacion y fechaUltModificacion
@JsonIgnoreProperties(value = { "fechaCreacion", "fechaUltModificacion" }, allowGetters = true)
public class Cliente implements IBaseEntity<Long> {

	private static final long serialVersionUID = 1017419071510970547L;

	// CAMPOS
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Basic(optional = false)
	@Column(name = "codigo", nullable = false, length = 10, unique = true)
	private String codigo;

	@Basic(optional = false)
	@Column(name = "nombre", nullable = false, length = 50)
	private String nombre;

	@Basic(optional = true)
	@Column(name = "apellido1", nullable = true, length = 50)
	private String apellido1;

	@Basic(optional = true)
	@Column(name = "apellido2", nullable = true, length = 50)
	private String apellido2;

	@Basic(optional = false)
	@Column(name = "dni", nullable = false, length = 20)
	private String dni;

	@Basic(optional = true)
	@Column(name = "riesgoConcedido", nullable = true, precision = 16, scale = 6)
	private BigDecimal riesgoConcedido;

	@Basic(optional = false)
	@Column(name = "fechaCreacion", nullable = false, updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@CreatedDate
	private Date fechaCreacion;

	@Basic(optional = false)
	@Column(name = "fechaUltModificacion", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@LastModifiedDate
	private Date fechaUltModificacion;

	// CONSTRCUTOR
	public Cliente() {

	}

	public Cliente(Long id, String codigo, String nombre, String apellido1, String apellido2, String dni,
			BigDecimal riesgoConcedido) {
		super();
		this.id = id;
		this.codigo = codigo;
		this.nombre = nombre;
		this.apellido1 = apellido1;
		this.apellido2 = apellido2;
		this.dni = dni;
		this.riesgoConcedido = riesgoConcedido;
	}

	// GETTERS Y SETTERS
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido1() {
		return apellido1;
	}

	public void setApellido1(String apellido1) {
		this.apellido1 = apellido1;
	}

	public String getApellido2() {
		return apellido2;
	}

	public void setApellido2(String apellido2) {
		this.apellido2 = apellido2;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public BigDecimal getRiesgoConcedido() {
		return riesgoConcedido;
	}

	public void setRiesgoConcedido(BigDecimal riesgoConcedido) {
		this.riesgoConcedido = riesgoConcedido;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public Date getFechaUltModificacion() {
		return fechaUltModificacion;
	}

	public void setFechaUltModificacion(Date fechaUltModificacion) {
		this.fechaUltModificacion = fechaUltModificacion;
	}

	// FUNCIONES
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cliente other = (Cliente) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Cliente [id=" + id + ", nombre=" + nombre + ", apellido1=" + apellido1 + ", dni=" + dni + "]";
	}

	@Override
	public Long givePK() {
		return this.getId();
	}

	@Override
	public boolean isPKExists() {
		return (this.getId() != null && this.getId() > 0);
	}

}
