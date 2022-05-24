package co.com.eam.gimansiosergio.domain;



import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Data;


/**
 * The persistent class for the aparatos database table.
 * 
 */
@Data
@Entity
//@NamedQuery(name="Aparato.findAll", query="SELECT a FROM Aparato a")
public class Aparato implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique = true)
	private int codigo;

	@NotBlank(message = "{descripcionObligatorio.apa}")
	@Size(min= 3, max=100, message="{tamanodescripcion.apa}")
	@Pattern(regexp="^[a-zA-ZàáâäãåąčćęèéêëėįìíîïłńòóôöõøùúûüųūÿýżźñçčšžÀÁÂÄÃÅĄĆČĖĘÈÉÊËÌÍÎÏĮŁŃÒÓÔÖÕØÙÚÛÜŲŪŸÝŻŹÑßÇŒÆČŠŽ∂ð ,.'-]+$", message="{tipoCaracterdescripcion.apa}")
	private String descripcion;


	@NotBlank(message = "{estadoObligatorio.apa}")
	@Size(min= 1, max=50, message="{tamanoestado.apa}")
	@Pattern(regexp="^[a-zA-ZàáâäãåąčćęèéêëėįìíîïłńòóôöõøùúûüųūÿýżźñçčšžÀÁÂÄÃÅĄĆČĖĘÈÉÊËÌÍÎÏĮŁŃÒÓÔÖÕØÙÚÛÜŲŪŸÝŻŹÑßÇŒÆČŠŽ∂ð ,.'-]+$", message="{tipoCaracterestado.apa}")
	private String estadoConservacion;
	
	@Lob
	private String foto;

	//bi-directional many-to-one association to Sala
	@ManyToOne
	@JoinColumn(name="Sala_fk")
	private Sala sala;

	
}