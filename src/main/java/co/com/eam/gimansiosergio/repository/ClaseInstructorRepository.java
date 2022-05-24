package co.com.eam.gimansiosergio.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import co.com.eam.gimansiosergio.domain.Claseinstructor;

@Repository
public interface ClaseInstructorRepository  extends CrudRepository<Claseinstructor, Integer>{

	@Query("SELECT C FROM Claseinstructor C WHERE C.id=?1")
	List<Claseinstructor> BuscarClaseInstructorCodigo(Integer id);

	@Query("SELECT u FROM Claseinstructor u ORDER BY u.id desc")
	public Iterable<Claseinstructor> listarOrdenados();
	
	@Query("SELECT u FROM Claseinstructor u WHERE u.instructor.cedula = :cedula")
	public Iterable<Claseinstructor> listarInstructoresDictanClases(@Param("cedula")Integer cedula);

	@Query("Select A from Claseinstructor A WHERE A.instructor.cedula=?1")
	List<Claseinstructor> listarInstructor(int cedula);
	
	@Query("Select A from Claseinstructor A WHERE A.clase.codigo=?1")
	List<Claseinstructor> listarClase(int codigo);
	
}
