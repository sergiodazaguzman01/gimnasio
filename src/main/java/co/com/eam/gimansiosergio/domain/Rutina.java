package co.com.eam.gimansiosergio.domain;


import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Data;

/**
 * The persistent class for the rutinas database table.
 * 
 */
@Entity
@Data
@Table(name = "rutinas")
//@NamedQuery(name="Rutina.findAll", query="SELECT r FROM Rutina r")
public class Rutina implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	
	@NotBlank(message = "{nombreObligatorio.socio}")
	@Size(min= 3, max=50, message="{tamanoNombre.socio}")
	@Pattern(regexp="^[a-zA-ZÃ Ã¡Ã¢Ã¤Ã£Ã¥Ä…Ä�Ä‡Ä™Ã¨Ã©ÃªÃ«Ä—Ä¯Ã¬Ã­Ã®Ã¯Å‚Å„Ã²Ã³Ã´Ã¶ÃµÃ¸Ã¹ÃºÃ»Ã¼Å³Å«Ã¿Ã½Å¼ÅºÃ±Ã§Ä�Å¡Å¾Ã€Ã�Ã‚Ã„ÃƒÃ…Ä„Ä†ÄŒÄ–Ä˜ÃˆÃ‰ÃŠÃ‹ÃŒÃ�ÃŽÃ�Ä®Å�ÅƒÃ’Ã“Ã”Ã–Ã•Ã˜Ã™ÃšÃ›ÃœÅ²ÅªÅ¸Ã�Å»Å¹Ã‘ÃŸÃ‡Å’Ã†ÄŒÅ Å½âˆ‚Ã° ,.'-]+$", message="{tipoCaracterNombre.socio}")
	private String nombre;	
	@Lob
	private String foto;
	
	@NotBlank(message = "{addescripcion}")
	@Pattern(regexp="^[a-zA-ZÃ Ã¡Ã¢Ã¤Ã£Ã¥Ä…Ä�Ä‡Ä™Ã¨Ã©ÃªÃ«Ä—Ä¯Ã¬Ã­Ã®Ã¯Å‚Å„Ã²Ã³Ã´Ã¶ÃµÃ¸Ã¹ÃºÃ»Ã¼Å³Å«Ã¿Ã½Å¼ÅºÃ±Ã§Ä�Å¡Å¾Ã€Ã�Ã‚Ã„ÃƒÃ…Ä„Ä†ÄŒÄ–Ä˜ÃˆÃ‰ÃŠÃ‹ÃŒÃ�ÃŽÃ�Ä®Å�ÅƒÃ’Ã“Ã”Ã–Ã•Ã˜Ã™ÃšÃ›ÃœÅ²ÅªÅ¸Ã�Å»Å¹Ã‘ÃŸÃ‡Å’Ã†ÄŒÅ Å½âˆ‚Ã° ,.'-]+$", message="{descripcion}")
	private String descripcion;
	
	@NotBlank(message = "{addejercicios}")
	@Pattern(regexp="^[a-zA-ZÃ Ã¡Ã¢Ã¤Ã£Ã¥Ä…Ä�Ä‡Ä™Ã¨Ã©ÃªÃ«Ä—Ä¯Ã¬Ã­Ã®Ã¯Å‚Å„Ã²Ã³Ã´Ã¶ÃµÃ¸Ã¹ÃºÃ»Ã¼Å³Å«Ã¿Ã½Å¼ÅºÃ±Ã§Ä�Å¡Å¾Ã€Ã�Ã‚Ã„ÃƒÃ…Ä„Ä†ÄŒÄ–Ä˜ÃˆÃ‰ÃŠÃ‹ÃŒÃ�ÃŽÃ�Ä®Å�ÅƒÃ’Ã“Ã”Ã–Ã•Ã˜Ã™ÃšÃ›ÃœÅ²ÅªÅ¸Ã�Å»Å¹Ã‘ÃŸÃ‡Å’Ã†ÄŒÅ Å½âˆ‚Ã° ,.'-]+$", message="{ejercicios}")
	private String ejercicios;
	
	// bi-directional many-to-one association to Instructor
	@ManyToOne
	@JoinColumn(name = "Instructor_fk")
	private Instructor instructor;

	// bi-directional many-to-one association to Socio
	@ManyToOne
	@JoinColumn(name = "Socio_fk")
	private Socio socio;

}