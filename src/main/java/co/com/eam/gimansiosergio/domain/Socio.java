package co.com.eam.gimansiosergio.domain;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Length;

import com.sun.xml.internal.ws.dump.MessageDumping;

import lombok.Data;

import java.util.List;

/**
 * The persistent class for the socio database table.
 * 
 */
@Data
@Entity
//@NamedQuery(name="Socio.findAll", query="SELECT s FROM Socio s")
public class Socio implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int numeroSocio;

	@NotBlank(message = "{apellidoObligatorio.socio}")
	@Size(min= 3, max=50, message="{tamanoapellido.socio}")
	@Pattern(regexp="^[a-zA-ZàáâäãåąčćęèéêëėįìíîïłńòóôöõøùúûüųūÿýżźñçčšžÀÁÂÄÃÅĄĆČĖĘÈÉÊËÌÍÎÏĮŁŃÒÓÔÖÕØÙÚÛÜŲŪŸÝŻŹÑßÇŒÆČŠŽ∂ð ,.'-]+$", message="{tipoCaracterapellido.socio}")
	private String apellido;

	@NotNull(message = "Debes colocar el numero de cedula")
	@Column(unique = true)
	private int cedula;
	
	@NotBlank(message = "{celularObligatorio.socio}")
	@Size(min= 10, max=10, message="{tamanoacelular.socio}")
	@Column(unique = true)
	private String celular;
	
	@NotBlank(message = "{correoObligatorio.socio}")
	@Pattern(regexp = "^[_a-z0-9-]+(.[_a-z0-9-]+)*@[a-z0-9-]+(.[a-z0-9-]+)*(.[a-z]{2,4})$", message="{tipoCaractercorreo.socio}")
	@Column(unique = true)
	private String correo;
	
	@Length(min = 8, message = "{tamanoContrasena.socio}")
	@NotBlank(message = "{contrasenaObligatorio.socio}")
	@Column(length = 255)
	private String contrasena;

	@Lob
	private String foto;
	
	@NotBlank(message = "{nombreObligatorio.socio}")
	@Size(min= 3, max=50, message="{tamanoNombre.socio}")
	@Pattern(regexp="^[a-zA-ZàáâäãåąčćęèéêëėįìíîïłńòóôöõøùúûüųūÿýżźñçčšžÀÁÂÄÃÅĄĆČĖĘÈÉÊËÌÍÎÏĮŁŃÒÓÔÖÕØÙÚÛÜŲŪŸÝŻŹÑßÇŒÆČŠŽ∂ð ,.'-]+$", message="{tipoCaracterNombre.socio}")
	private String nombre;

	// bi-directional many-to-one association to Datosbancario
	@OneToMany(mappedBy = "socio")
	private List<Datosbancario> datosbancarios;

	// bi-directional many-to-one association to Rutina
	@OneToMany(mappedBy = "socio")
	private List<Rutina> rutinas;

	// bi-directional many-to-one association to Socioclase
	@OneToMany(mappedBy = "socio")
	private List<Socioclase> socioclases;

	public Datosbancario addDatosbancario(Datosbancario datosbancario) {
		getDatosbancarios().add(datosbancario);
		datosbancario.setSocio(this);

		return datosbancario;
	}

	public Datosbancario removeDatosbancario(Datosbancario datosbancario) {
		getDatosbancarios().remove(datosbancario);
		datosbancario.setSocio(null);

		return datosbancario;
	}

	public Rutina addRutina(Rutina rutina) {
		getRutinas().add(rutina);
		rutina.setSocio(this);

		return rutina;
	}

	public Rutina removeRutina(Rutina rutina) {
		getRutinas().remove(rutina);
		rutina.setSocio(null);

		return rutina;
	}

	public Socioclase addSocioclas(Socioclase socioclas) {
		getSocioclases().add(socioclas);
		socioclas.setSocio(this);

		return socioclas;
	}

	public Socioclase removeSocioclas(Socioclase socioclas) {
		getSocioclases().remove(socioclas);
		socioclas.setSocio(null);

		return socioclas;
	}

}