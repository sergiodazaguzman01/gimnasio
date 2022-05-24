package co.com.eam.gimansiosergio.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import co.com.eam.gimansiosergio.domain.Tiposala;

@Repository
public interface TipoSalaRepository extends CrudRepository<Tiposala, Integer>{
	
	@Query("SELECT T FROM Tiposala T WHERE T.id=?1")
	List<Tiposala> BuscarTipoSalaId (Integer id);
	
	@Query("SELECT u FROM Tiposala u ORDER BY u.nombre")
	public Iterable<Tiposala> listarOrdenados();
	/**
	@Query("SELECT u, e FROM Tiposala u, Sala e WHERER u.id = : id ")
	public Iterable<Tiposala> buscarTipoSalaPorSala (@Param ("id") String buscarId);
	  
	**/
	/**
	@Query("SELECT u FROM TipoSala u ORDER BY u.nombre")
	public Iterable<Tiposala> listarTipoSalaOrdenados();
	
	@Query("SELECT u.id, u.nombre, o.nombre, o.apellido,  FROM TipoSala u JOIN  Sala s "
			+ "ON u.id = s.tiposala JOIN clase l "
			+ "ON s.numeroSala = l.sala JOIN ClaseInstructor o "
			+ "ON l.codigo = o.claseinstructor JOIN Instructor q "
			+ "ON q.cedula = o.instructor")
	public Iterable<Tiposala> listarTipoSalaPorInstructor();
	
	@Query("SELECT a.codigo, a.estadoConservacion, u.id, u.nombre FROM TipoSala u JOIN Sala s "
			+ "ON u.id = s.tiposala JOIN Aparato a "
			+ "ON s.numeroSala = a.aparatos WHERE a.estadoConservacion = 'BUENO' ")
	public Iterable<Tiposala> listarAparatosPorEstadoConservacion();**/

}
