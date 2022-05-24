package co.com.eam.gimansiosergio.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import co.com.eam.gimansiosergio.domain.Socioclase;

@Repository
public interface SocioClaseRepository extends CrudRepository<Socioclase, Integer>{

	@Query("SELECT S FROM Socioclase S WHERE S.id=?1")
	List<Socioclase> BuscarSocioClaseId(Integer id);
	
	@Query("SELECT s FROM Socioclase s WHERE s.socio.cedula = :socio")
	public Iterable<Socioclase> buscarSociosPorClaseoPorCedula(@Param("socio") String BucarPorCedula);
	
//	@Query("SELECT s FROM Socioclase s ORDER BY s.id desc ")
//	public Iterable<Socioclase> OrdenarClaseSocioPorId();
	
}
