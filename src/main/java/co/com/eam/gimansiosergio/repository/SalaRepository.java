package co.com.eam.gimansiosergio.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import co.com.eam.gimansiosergio.domain.Sala;

@Repository
public interface SalaRepository extends CrudRepository<Sala, Integer>{

	@Query("SELECT S FROM Sala S WHERE S.numeroSala=?1")
	List<Sala> BuscarSalaNumeroSala(Integer numeroSala);
	
	@Query("Select S FROM Sala S WHERE S.tiposala.id=?1")
	List<Sala> listartiposala(Integer id);
	
	@Query("SELECT u FROM Sala u ORDER BY  u.ubicacion")
	public Iterable<Sala> listarOrdenados();
	
	@Query("SELECT COUNT(u) FROM Sala u WHERE u.ubicacion = :ubicacion")
	public Iterable<Sala> ContarCuantasSalasHayPorUbicacion(@Param ("ubicacion") String ContarUbicacion);
	
	@Query("SELECT u FROM Sala u Where u.tieneAparatos = :tieneAparatos")
	public Iterable<Sala> ListarSalaSiTieneAparatos(@Param("tieneAparatos") String ListarSiTieneAparatos);

	@Query("SELECT u FROM Sala u WHERE u.metroCuadrado = :metroCuadrado")
	public Iterable<Sala> bucarSalaPorMetroCuadrado(@Param("metroCuadrado") String buscarMetosCuadrado);
	
	Sala findByNumeroSala (Integer numeroSala);
	
	
	
	
	
	
	
	
	
	
	
	/**
	@Query("SELECT u FROM Sala u ORDER BY u.numeroSala, u.ubicacion")
	public Iterable<Sala> listarOrdenados();
	
	 @Query("SELECT u FROM Sala u ORDER BY u.numeroSala, u.ubicacion")
	public Iterable<Sala> listarOrdenados();

	@Query("SELECT COUNT(numeroSala) FROM Sala s JOIN Clase c " + "ON s.numeroSala = c.sala")
	public Iterable<Sala> cuenteMeCuantasSalasHayEnUnaClase();

	@Query("SELECT COUNT(numeroSala) FROM Sala ")
	public Iterable<Sala> cuenteMeCuantasSalasHay();

	@Query("SELECT numeroSala, tieneAparatos FROM Sala " + "WHERE tieneAparatos = 'si'")
	public Iterable<Sala> mostrarMeLasSalasQueTieneAparatos();


	**/
	
	
}
