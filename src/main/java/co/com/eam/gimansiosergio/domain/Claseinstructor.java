package co.com.eam.gimansiosergio.domain;



import java.io.Serializable;
import javax.persistence.*;

import lombok.Data;


/**
 * The persistent class for the claseinstructor database table.
 * 
 */
@Data
@Entity
//@NamedQuery(name="Claseinstructor.findAll", query="SELECT c FROM Claseinstructor c")
public class Claseinstructor implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	//bi-directional many-to-one association to Instructor
	@ManyToOne
	@JoinColumn(name="Instructor_fk")
	private Instructor instructor;

	//bi-directional many-to-one association to Clase
	@ManyToOne
	@JoinColumn(name="clase_fk")
	private Clase clase;

	

}