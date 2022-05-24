package co.com.eam.gimansiosergio.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import co.com.eam.gimansiosergio.domain.Instructor;

@Repository
public interface InstructorRepository extends CrudRepository<Instructor, Integer>{

	@Query("SELECT a From Instructor a WHERE a.correo=?1 and a.contrasena=?2")
	Instructor LoginInstructor(String correo, String contrasena);
	
	@Query("SELECT I FROM Instructor I WHERE I.cedula=?1")
	List<Instructor> BuscarInstructorCedula(Integer cedula);
	
	@Query("SELECT u FROM Instructor u ORDER BY u.nombre")
	public Iterable<Instructor> listarOrdenados();
	
	@Query("SELECT u FROM Instructor u  WHERE u.experiencia = :experiencia ORDER BY experiencia")
	public Iterable<Instructor> OrdenarLosInstructoresPorExperiencia(@Param("experiencia")String ordenarExperiencia);
	
	@Query("SELECT COUNT(u) FROM Instructor u ")
	public Iterable<Instructor> cuentemeCuantosInstructoresHay();
	
	Instructor findByCedula (Integer numero_cedula);
	Instructor findByCorreo (String correo);
	
	
	
	/**
	@Query("SELECT i.nombre  Instructor, s.numeroSocio, s.nombre, r.descripcion FROM "
			+ "Instructor i JOIN  Rutina r ON i.cedula = r.Instructor JOIN Socio s ON s.numeroSocio = r.socio  order by s.numeroSocio")
			public Iterable<Instructor> ListarLosInstructoresQueTieneSocios();
	

	@Query("SELECT i FROM Instructor i JOIN Rutina r"
			+ "ON i.cedula = r.instructor.cedula")
	public Iterable<Instructor> listarInstructorPorClase();
	
	@Query("SELECT c.codigo, c.nombre, c.dia, i.nombre, i.apellido FROM Instructor JOIN ClaseInstructor "
			+ "ON i.cedula = l.instructor JOIN clase "
			+ "ON c.codigo = l.clase")
	public Iterable<Instructor> listarLosInstructoresPoClase();
**/
}
