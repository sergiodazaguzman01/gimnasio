package co.com.eam.gimansiosergio.domain;



import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Data;


/**
 * The persistent class for the datosbancarios database table.
 * 
 */
@Data
@Entity
//@Table(name="datosbancarios")
//@NamedQuery(name="Datosbancario.findAll", query="SELECT d FROM Datosbancario d")
public class Datosbancario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int codigo;

	@NotBlank(message = "{nBanco}")
	@Size(min= 3, max=50, message="{tamanoNombre.socio}")
	@Pattern(regexp="^[a-zA-ZÃ Ã¡Ã¢Ã¤Ã£Ã¥Ä…Ä�Ä‡Ä™Ã¨Ã©ÃªÃ«Ä—Ä¯Ã¬Ã­Ã®Ã¯Å‚Å„Ã²Ã³Ã´Ã¶ÃµÃ¸Ã¹ÃºÃ»Ã¼Å³Å«Ã¿Ã½Å¼ÅºÃ±Ã§Ä�Å¡Å¾Ã€Ã�Ã‚Ã„ÃƒÃ…Ä„Ä†ÄŒÄ–Ä˜ÃˆÃ‰ÃŠÃ‹ÃŒÃ�ÃŽÃ�Ä®Å�ÅƒÃ’Ã“Ã”Ã–Ã•Ã˜Ã™ÃšÃ›ÃœÅ²ÅªÅ¸Ã�Å»Å¹Ã‘ÃŸÃ‡Å’Ã†ÄŒÅ Å½âˆ‚Ã° ,.'-]+$", message="{tipoCaracterNombre.banco}")
	private String nombreBanco;
	
	@NotNull(message = "numero de cuenta obligatorio")
	@Column(unique = true)
	private int numeroCuenta;

	@NotBlank(message = "{tipoCuenta}")
	@Size(min= 3, max=50, message="{tamanoNombre.socio}")
	@Pattern(regexp="^[a-zA-ZÃ Ã¡Ã¢Ã¤Ã£Ã¥Ä…Ä�Ä‡Ä™Ã¨Ã©ÃªÃ«Ä—Ä¯Ã¬Ã­Ã®Ã¯Å‚Å„Ã²Ã³Ã´Ã¶ÃµÃ¸Ã¹ÃºÃ»Ã¼Å³Å«Ã¿Ã½Å¼ÅºÃ±Ã§Ä�Å¡Å¾Ã€Ã�Ã‚Ã„ÃƒÃ…Ä„Ä†ÄŒÄ–Ä˜ÃˆÃ‰ÃŠÃ‹ÃŒÃ�ÃŽÃ�Ä®Å�ÅƒÃ’Ã“Ã”Ã–Ã•Ã˜Ã™ÃšÃ›ÃœÅ²ÅªÅ¸Ã�Å»Å¹Ã‘ÃŸÃ‡Å’Ã†ÄŒÅ Å½âˆ‚Ã° ,.'-]+$", message="{tipoCaracterNombre.cuenta}")
	private String tipoCuenta;

	//bi-directional many-to-one association to Socio
	@ManyToOne
	@JoinColumn(name="socio_fk")
	private Socio socio;

}