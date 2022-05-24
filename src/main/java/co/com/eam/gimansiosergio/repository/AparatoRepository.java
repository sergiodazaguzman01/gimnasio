package co.com.eam.gimansiosergio.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import co.com.eam.gimansiosergio.domain.Aparato;

@Repository
public interface AparatoRepository extends CrudRepository<Aparato, Integer>{

	@Query("SELECT A FROM Aparato A WHERE A.codigo=?1")
	List<Aparato> BuscarAparatoCodigo(Integer codigo);

	@Query("SELECT u FROM Aparato u WHERE u.estadoConservacion = :estado")
	public Iterable<Aparato> listarOrdenado(@Param("estado") String estadoConservacion);
		
	@Query("SELECT u FROM Aparato u WHERE u.descripcion = :des")
	public Iterable<Aparato> ContarApartosPorDescripcion(@Param("des") String ContarDescripcion);

	@Query("SELECT COUNT(u) FROM Aparato u ")
	List<Aparato> code(Integer codigo);

	@Query("Select A from Aparato A WHERE A.sala.numeroSala=?1")
	List<Aparato> listarSalas(int numeroSala);
	
	Aparato findByCodigo(Integer codigo);
	
}
