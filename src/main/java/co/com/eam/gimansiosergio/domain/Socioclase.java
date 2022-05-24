package co.com.eam.gimansiosergio.domain;



import java.io.Serializable;
import javax.persistence.*;

import lombok.Data;


/**
 * The persistent class for the socioclase database table.
 * 
 */
@Data
@Entity
//@NamedQuery(name="Socioclase.findAll", query="SELECT s FROM Socioclase s")
public class Socioclase implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	//bi-directional many-to-one association to Clase
	@ManyToOne
	@JoinColumn(name="Clase_fk")
	private Clase clase;

	//bi-directional many-to-one association to Socio
	@ManyToOne
	@JoinColumn(name="Socio_fk")
	private Socio socio;

	

}