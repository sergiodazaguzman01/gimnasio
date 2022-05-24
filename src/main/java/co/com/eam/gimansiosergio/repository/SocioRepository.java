package co.com.eam.gimansiosergio.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import co.com.eam.gimansiosergio.domain.Socio;

@Repository
public interface SocioRepository extends CrudRepository<Socio, Integer>{

	//List<Socio> findBySocioCedula (int numeroSocio);
	

	@Query("SELECT a From Socio a WHERE a.correo=?1 and a.contrasena=?2")
	Socio LoginSocio(String correo, String contrasena);
	
//	@Query("SELECT S FROM Socio S WHERE S.numeroSocio=?1")
//	List<Socio> BuscarSocioId (Integer numeroSocio);
	
	@Query("SELECT u FROM Socio u ORDER BY u.numeroSocio, u.nombre")
	public Iterable<Socio> listarOrdenados();
	
	Socio findByCedula (Integer cedula);
	
	Socio findByCorreo (String correo);
	
	
//	@Query("SELECT COUNT(u) FROM Socio c WHERE c.nombre = :nombre" )
//	public Iterable<Socio> CuentemeLosSociosPorNombre(@Param ("nombre")String nombre);

	/**
	@Query("SELECT u FROM Socio u ORDER BY u.numeroSocio, u.nombre")
	public Iterable<Socio> listarSociosOrdenados();
	
	@Query("SELECT s.nombre, s.apellido, r.descripcion, c.codigo, c.nombre FROM Socio s JOIN Rutina r "
			+ "ON s.numeroSocio = r.socio JOIN Instructor i "
			+ "ON i.cedula = i.instructor JOIN ClaseInstructor l "
			+ "ON  i cedula = l.instructor JOIN Clase c "
			+ "ON c.codigo = l.clases")
	public Iterable<Socio> ListarSociosConRutinaEnClases();
	
		
	@Query("SELECT COUNT(u) FROM Socio c JOIN Rutina r "
			+ "ON c.numeroSocio = r.socio WHERE c.nombre = 'stiven'")
	public Iterable<Socio> listarRutinasDeUnSocio();**/

}
