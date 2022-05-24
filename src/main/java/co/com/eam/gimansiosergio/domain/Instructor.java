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

import lombok.Data;

import java.util.List;

/**
 * The persistent class for the instructor database table.
 * 
 */
@Data
@Entity
//@NamedQuery(name="Instructor.findAll", query="SELECT i FROM Instructor i")
public class Instructor implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int cedula;

	@NotNull(message = "{cedulaObli.socio}")
	@Column(unique = true)
	private int numero_cedula;
	
	@NotBlank(message = "{apellidoObligatorio.socio}")
	@Size(min= 3, max=50, message="{tamanoapellido.socio}")
	@Pattern(regexp="^[a-zA-ZÃ Ã¡Ã¢Ã¤Ã£Ã¥Ä…Ä�Ä‡Ä™Ã¨Ã©ÃªÃ«Ä—Ä¯Ã¬Ã­Ã®Ã¯Å‚Å„Ã²Ã³Ã´Ã¶ÃµÃ¸Ã¹ÃºÃ»Ã¼Å³Å«Ã¿Ã½Å¼ÅºÃ±Ã§Ä�Å¡Å¾Ã€Ã�Ã‚Ã„ÃƒÃ…Ä„Ä†ÄŒÄ–Ä˜ÃˆÃ‰ÃŠÃ‹ÃŒÃ�ÃŽÃ�Ä®Å�ÅƒÃ’Ã“Ã”Ã–Ã•Ã˜Ã™ÃšÃ›ÃœÅ²ÅªÅ¸Ã�Å»Å¹Ã‘ÃŸÃ‡Å’Ã†ÄŒÅ Å½âˆ‚Ã° ,.'-]+$", message="{tipoCaracterapellido.socio}")
	private String apellido;

	@NotBlank(message = "{celularObligatorio.socio}\"")
	@Size(min= 10, max=10, message="{tamanoacelular.socio}")
	private String celular;

	@NotBlank(message = "{correoObligatorio.socio}")
	@Pattern(regexp = "^[_a-z0-9-]+(.[_a-z0-9-]+)@[a-z0-9-]+(.[a-z0-9-]+)(.[a-z]{2,4})$", message="{tipoCaractercorreo.socio}")
	@Column(unique = true)
	private String correo;
	
	@Length(min = 8, message = "{tamanoContrasena.socio}")
	@NotBlank(message = "contrasena obligatoria")
	@Column(length = 255)
	private String contrasena;
	
	@NotBlank(message = "{experienciaObligatoria.ins}")
	@Size(min= 3, max=100, message="Este campo requiere de 3 caracteres a 100 caracteres")
	//@Pattern(regexp="^[a-zA-ZÃ Ã¡Ã¢Ã¤Ã£Ã¥Ä…Ä�Ä‡Ä™Ã¨Ã©ÃªÃ«Ä—Ä¯Ã¬Ã­Ã®Ã¯Å‚Å„Ã²Ã³Ã´Ã¶ÃµÃ¸Ã¹ÃºÃ»Ã¼Å³Å«Ã¿Ã½Å¼ÅºÃ±Ã§Ä�Å¡Å¾Ã€Ã�Ã‚Ã„ÃƒÃ…Ä„Ä†ÄŒÄ–Ä˜ÃˆÃ‰ÃŠÃ‹ÃŒÃ�ÃŽÃ�Ä®Å�ÅƒÃ’Ã“Ã”Ã–Ã•Ã˜Ã™ÃšÃ›ÃœÅ²ÅªÅ¸Ã�Å»Å¹Ã‘ÃŸÃ‡Å’Ã†ÄŒÅ Å½âˆ‚Ã° ,.'-]+$", message="Verifique su experiencia")
	private String experiencia;
	
	@Lob
	private String foto;
	
	@NotBlank(message = "{nombreObligatorio.socio}")
	@Size(min= 3, max=50, message="{tamanoNombre.socio}")
	@Pattern(regexp="^[a-zA-ZÃ Ã¡Ã¢Ã¤Ã£Ã¥Ä…Ä�Ä‡Ä™Ã¨Ã©ÃªÃ«Ä—Ä¯Ã¬Ã­Ã®Ã¯Å‚Å„Ã²Ã³Ã´Ã¶ÃµÃ¸Ã¹ÃºÃ»Ã¼Å³Å«Ã¿Ã½Å¼ÅºÃ±Ã§Ä�Å¡Å¾Ã€Ã�Ã‚Ã„ÃƒÃ…Ä„Ä†ÄŒÄ–Ä˜ÃˆÃ‰ÃŠÃ‹ÃŒÃ�ÃŽÃ�Ä®Å�ÅƒÃ’Ã“Ã”Ã–Ã•Ã˜Ã™ÃšÃ›ÃœÅ²ÅªÅ¸Ã�Å»Å¹Ã‘ÃŸÃ‡Å’Ã†ÄŒÅ Å½âˆ‚Ã° ,.'-]+$", message="{tipoCaracterNombre.socio}")
	private String nombre;
	
	@NotBlank(message = "{titulacionObligatorio.ins}")
	@Size(min= 3, max=100, message="{campoobli}")
	@Pattern(regexp="^[a-zA-ZÃ Ã¡Ã¢Ã¤Ã£Ã¥Ä…Ä�Ä‡Ä™Ã¨Ã©ÃªÃ«Ä—Ä¯Ã¬Ã­Ã®Ã¯Å‚Å„Ã²Ã³Ã´Ã¶ÃµÃ¸Ã¹ÃºÃ»Ã¼Å³Å«Ã¿Ã½Å¼ÅºÃ±Ã§Ä�Å¡Å¾Ã€Ã�Ã‚Ã„ÃƒÃ…Ä„Ä†ÄŒÄ–Ä˜ÃˆÃ‰ÃŠÃ‹ÃŒÃ�ÃŽÃ�Ä®Å�ÅƒÃ’Ã“Ã”Ã–Ã•Ã˜Ã™ÃšÃ›ÃœÅ²ÅªÅ¸Ã�Å»Å¹Ã‘ÃŸÃ‡Å’Ã†ÄŒÅ Å½âˆ‚Ã° ,.'-]+$", message="{tipoCaractertitulacion.ins}")
	private String titulacion;

	// bi-directional many-to-one association to Claseinstructor
	@OneToMany(mappedBy = "instructor")
	private List<Claseinstructor> claseinstructors;

	// bi-directional many-to-one association to Rutina
	@OneToMany(mappedBy = "instructor")
	private List<Rutina> rutinas;

	public Claseinstructor addClaseinstructor(Claseinstructor claseinstructor) {
		getClaseinstructors().add(claseinstructor);
		claseinstructor.setInstructor(this);

		return claseinstructor;
	}

	public Claseinstructor removeClaseinstructor(Claseinstructor claseinstructor) {
		getClaseinstructors().remove(claseinstructor);
		claseinstructor.setInstructor(null);

		return claseinstructor;
	}

	public Rutina addRutina(Rutina rutina) {
		getRutinas().add(rutina);
		rutina.setInstructor(this);

		return rutina;
	}

	public Rutina removeRutina(Rutina rutina) {
		getRutinas().remove(rutina);
		rutina.setInstructor(null);

		return rutina;
	}

}