package co.com.eam.gimansiosergio.domain;



import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Data;

import java.util.List;

/**
 * The persistent class for the sala database table.
 * 
 */
@Data
@Entity
//@NamedQuery(name="Sala.findAll", query="SELECT s FROM Sala s")
public class Sala implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true)
	private int numeroSala;
	
	@NotBlank(message = "{nombreObligatorio.sala}")
	@Size(min= 3, max=50, message="{tamanoNombre.sala}")
	@Pattern(regexp="^[a-zA-ZàáâäãåąčćęèéêëėįìíîïłńòóôöõøùúûüųūÿýżźñçčšžÀÁÂÄÃÅĄĆČĖĘÈÉÊËÌÍÎÏĮŁŃÒÓÔÖÕØÙÚÛÜŲŪŸÝŻŹÑßÇŒÆČŠŽ∂ð ,.'-]+$", message="{tipoCaracterNombre.sala}")
	private String nombreSala;
	
	@Lob
	private String foto;

	private double metroCuadrado;
	
	@NotBlank(message = "{apaObligatorio.sala}")
	@Size(min= 2, max=2, message="{tamanoapa.sala}")
	@Pattern(regexp="^[a-zA-ZàáâäãåąčćęèéêëėįìíîïłńòóôöõøùúûüųūÿýżźñçčšžÀÁÂÄÃÅĄĆČĖĘÈÉÊËÌÍÎÏĮŁŃÒÓÔÖÕØÙÚÛÜŲŪŸÝŻŹÑßÇŒÆČŠŽ∂ð ,.'-]+$", message="{tipoCaracterapa.sala}")
	private String tieneAparatos;
	
	@NotBlank(message = "{ubicacionObligatorio.sala}")
	@Size(min= 3, max=50, message="{tamanoubicacion.sala}")
	@Pattern(regexp="^[a-zA-ZàáâäãåąčćęèéêëėįìíîïłńòóôöõøùúûüųūÿýżźñçčšžÀÁÂÄÃÅĄĆČĖĘÈÉÊËÌÍÎÏĮŁŃÒÓÔÖÕØÙÚÛÜŲŪŸÝŻŹÑßÇŒÆČŠŽ∂ð ,.'-]+$", message="{tipoCaracterubicacion.sala}")
	private String ubicacion;

	// bi-directional many-to-one association to Aparato
	@OneToMany(mappedBy = "sala")
	private List<Aparato> aparatos;

	// bi-directional many-to-one association to Clase
	@OneToMany(mappedBy = "sala")
	private List<Clase> clases;

	// bi-directional many-to-one association to Tiposala
	@ManyToOne
	@JoinColumn(name = "TipoSala_fk")
	private Tiposala tiposala;

	public Aparato addAparato(Aparato aparato) {
		getAparatos().add(aparato);
		aparato.setSala(this);

		return aparato;
	}

	public Aparato removeAparato(Aparato aparato) {
		getAparatos().remove(aparato);
		aparato.setSala(null);

		return aparato;
	}

	
	public Clase addClas(Clase clas) {
		getClases().add(clas);
		clas.setSala(this);

		return clas;
	}

	public Clase removeClas(Clase clas) {
		getClases().remove(clas);
		clas.setSala(null);

		return clas;
	}

}