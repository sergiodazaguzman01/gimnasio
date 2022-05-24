package co.com.eam.gimansiosergio.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import co.com.eam.gimansiosergio.domain.Clase;

@Repository
public interface ClaseRepository extends CrudRepository<Clase, Integer>{

	@Query("SELECT C FROM Clase C WHERE C.codigo=?1")
	List<Clase> BuscarClaseCodigo(Integer codigo);
	
	@Query("SELECT u FROM Clase u ORDER BY u.nombre, u.hora")
	public Iterable<Clase> listarClaseOrdenada(); 
	
	@Query("SELECT COUNT(u) FROM Clase u WHERE u.nombre = :nombre ")
	public String ContarLasClasesPorNombre(@Param ("nombre") long contadeoPorNombre);
	
	@Query("SELECT u FROM Clase u WHERE u.dia = :dia")
	public Iterable<Clase> ListarLasClasesPorDia(@Param("dia") String ListarDia);
	
	@Query("SELECT u FROM Clase u WHERE u.hora = :hora")
	public Iterable<Clase> ListarClasesPorHoras(@Param("hora") String listarHoras);
	
	  @Query("SELECT u FROM Clase u WHERE u.dia = :dia OR u.hora = :hora")
	public List<Clase> findByLastnameOrFirstname(@Param("dia") String hora, @Param("hora") String dia);
	
	  @Query("SELECT DISTINCT u FROM Clase u WHERE u.dia = :dia OR u.hora = :hora")
		public List<Clase> findByhora(@Param("dia") String hora, @Param("hora") String dia);
	  
	  Clase findByCodigo (Integer codigo);
	  
}
