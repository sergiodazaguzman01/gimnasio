package co.com.eam.gimansiosergio.domain;


import java.io.Serializable;
import javax.persistence.*;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * The persistent class for the clase database table.
 * 
 */
@Data
@Entity
//@NamedQuery(name="Clase.findAll", query="SELECT c FROM Clase c")
public class Clase implements Serializable {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column (unique = true)
	private int codigo;

	@NotBlank(message = "{addescripcion}")
	@Pattern(regexp="^[a-zA-ZÃ Ã¡Ã¢Ã¤Ã£Ã¥Ä…Ä�Ä‡Ä™Ã¨Ã©ÃªÃ«Ä—Ä¯Ã¬Ã­Ã®Ã¯Å‚Å„Ã²Ã³Ã´Ã¶ÃµÃ¸Ã¹ÃºÃ»Ã¼Å³Å«Ã¿Ã½Å¼ÅºÃ±Ã§Ä�Å¡Å¾Ã€Ã�Ã‚Ã„ÃƒÃ…Ä„Ä†ÄŒÄ–Ä˜ÃˆÃ‰ÃŠÃ‹ÃŒÃ�ÃŽÃ�Ä®Å�ÅƒÃ’Ã“Ã”Ã–Ã•Ã˜Ã™ÃšÃ›ÃœÅ²ÅªÅ¸Ã�Å»Å¹Ã‘ÃŸÃ‡Å’Ã†ÄŒÅ Å½âˆ‚Ã° ,.'-]+$", message="{descripcion}")
	private String descripcion;

	@NotBlank(message = "Seleccione un dia")
	private String dia;
	
	@NotBlank(message = "seleccione una hora")
	private String hora;
	
	@NotBlank(message = "{nombreObligatorio.socio}")
	@Size(min= 3, max=50, message="{tamanoNombre.socio}")
	@Pattern(regexp="^[a-zA-ZÃ Ã¡Ã¢Ã¤Ã£Ã¥Ä…Ä�Ä‡Ä™Ã¨Ã©ÃªÃ«Ä—Ä¯Ã¬Ã­Ã®Ã¯Å‚Å„Ã²Ã³Ã´Ã¶ÃµÃ¸Ã¹ÃºÃ»Ã¼Å³Å«Ã¿Ã½Å¼ÅºÃ±Ã§Ä�Å¡Å¾Ã€Ã�Ã‚Ã„ÃƒÃ…Ä„Ä†ÄŒÄ–Ä˜ÃˆÃ‰ÃŠÃ‹ÃŒÃ�ÃŽÃ�Ä®Å�ÅƒÃ’Ã“Ã”Ã–Ã•Ã˜Ã™ÃšÃ›ÃœÅ²ÅªÅ¸Ã�Å»Å¹Ã‘ÃŸÃ‡Å’Ã†ÄŒÅ Å½âˆ‚Ã° ,.'-]+$", message="{tipoCaracterNombre.socio}")
	private String nombre;
	// bi-directional many-to-one association to Sala
	@ManyToOne
	@JoinColumn(name = "Sala_fk")
	private Sala sala;

	// bi-directional many-to-one association to Claseinstructor
	@OneToMany(mappedBy = "clase")
	private List<Claseinstructor> claseinstructors;

	// bi-directional many-to-one association to Socioclase
	@OneToMany(mappedBy = "clase")
	private List<Socioclase> socioclases;

	public Claseinstructor addClaseinstructor(Claseinstructor claseinstructor) {
		getClaseinstructors().add(claseinstructor);
		claseinstructor.setClase(this);

		return claseinstructor;
	}

	public Claseinstructor removeClaseinstructor(Claseinstructor claseinstructor) {
		getClaseinstructors().remove(claseinstructor);
		claseinstructor.setClase(null);

		return claseinstructor;
	}

	public Socioclase addSocioclas(Socioclase socioclas) {
		getSocioclases().add(socioclas);
		socioclas.setClase(this);

		return socioclas;
	}

	public Socioclase removeSocioclas(Socioclase socioclas) {
		getSocioclases().remove(socioclas);
		socioclas.setClase(null);

		return socioclas;
	}

}