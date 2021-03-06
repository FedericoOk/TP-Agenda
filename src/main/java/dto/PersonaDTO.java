package dto;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Data
public class PersonaDTO {

	@Id
	@GeneratedValue
	private int idPersona;
	private String nombre;
	private String telefono;
	@OneToOne(cascade = CascadeType.ALL)
	private DomicilioDTO domicilio;
	private String email;
	private Date nacimiento;
	private String plataformaAlmacenamiento;
	private String mesNacimiento;

	@OneToOne
	private TipoContacto tipoContacto;

	public PersonaDTO(String nombre, String telefono, String email, Date nacimiento, TipoContacto tipoContacto,
			DomicilioDTO domicilio, String plataformaAlmacenamiento, String mesNacimiento) {
		this.nombre = nombre;
		this.telefono = telefono;
		this.email = email;
		this.nacimiento = nacimiento;
		this.tipoContacto = tipoContacto;
		this.domicilio = domicilio;
		this.plataformaAlmacenamiento = plataformaAlmacenamiento;
		this.mesNacimiento = mesNacimiento;
	}

	public int getIdPersona() {
		return this.idPersona;
	}

	public void setIdPersona(int idPersona) {
		this.idPersona = idPersona;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTelefono() {
		return this.telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

}
