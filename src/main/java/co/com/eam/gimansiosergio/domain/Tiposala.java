package co.com.eam.gimansiosergio.domain;



import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Data;

import java.util.List;


/**
 * The persistent class for the tiposala database table.
 * 
 */
@Entity
@Data
//@NamedQuery(name="Tiposala.findAll", query="SELECT t FROM Tiposala t")
public class Tiposala implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@NotNull(message = "Ingrese  Nombre")
//	@Size(min = 1, max = 50, message = "El Nombre  debe de tener una longitud minima de 1 y maxima de 50")
//	@Pattern(regexp = "^[ñA-Za-z _][ñA-Za-z][ñA-Za-z _]$", message = "El nombre solo puede contener letras")
	private String nombre;

	//bi-directional many-to-one association to Sala
	@OneToMany(mappedBy="tiposala")
	private List<Sala> salas;

	

	public Sala addSala(Sala sala) {
		getSalas().add(sala);
		sala.setTiposala(this);

		return sala;
	}

	public Sala removeSala(Sala sala) {
		getSalas().remove(sala);
		sala.setTiposala(null);

		return sala;
	}

}