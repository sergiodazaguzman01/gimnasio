package co.com.eam.gimansiosergio.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import co.com.eam.gimansiosergio.domain.Rutina;

@Repository
public interface RutinaRepository extends CrudRepository<Rutina, Integer>{
	
	@Query("SELECT R FROM Rutina R WHERE R.instructor.cedula=?1")
	List<Rutina> rutina(int cedula);

	@Query("SELECT R FROM Rutina R WHERE R.id=?1")
	List<Rutina> BuscarRutinaId(Integer id);
	
	@Query("SELECT u FROM Rutina u ORDER BY u.id desc, u.descripcion ")
	public Iterable<Rutina> listarRutinas();
	
	@Query("SELECT COUNT(u) FROM Rutina u ")
	public Iterable<Rutina> cuenteMeCuantasRutinasHay();
	
	/**
@Query("SELECT u FROM Rutina u ORDER BY u.id, u.descripcion")
	public Iterable<Rutina> listarRutinas();
		
	@Query("SELECT s.numeroSocio, s.nombre, r.descripcion, COUNT(*) FROM Rutina r LEFT JOIN Socio s " 
	+ " ON s.numeroSocio = r.socio WHERE s.nombre = 'sergio")
	public Iterable<Rutina> CuentemeCuantasRutinasTieneUnaUnSocio();
	
	@Query(" SELECT COUNT(*) FROM Rutina")
	public Iterable<Rutina> CeunteMeLAsRutinas();
	
	@Query("SELECT i.cedula, i.nombre, r.descripcion, COUNT(*) FROM Instructor i JOIN Rutina r " + 
			"ON i.cedula = r.instructor")
	public Iterable<Rutina> cuenteMeLasRutinasDeUnInstructor();

	 * **/
}
