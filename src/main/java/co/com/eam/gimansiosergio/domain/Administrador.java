package co.com.eam.gimansiosergio.domain;



import java.io.Serializable;
import javax.persistence.*;

import lombok.Data;


/**
 * The persistent class for the administrador database table.
 * 
 */
@Data
@Entity
//@NamedQuery(name="Administrador.findAll", query="SELECT a FROM Administrador a")
public class Administrador implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private String apellido;

	private int cedula;

	private String contrasena;

	private String correo;

	private String nombre;

	private String telefono;

	
}