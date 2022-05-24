package co.com.eam.gimansiosergio.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import co.com.eam.gimansiosergio.domain.Administrador;


// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete
@Repository
public interface AdministradorRepository extends CrudRepository<Administrador, Integer> {
	
	@Query("SELECT a From Administrador a WHERE a.correo=?1 and a.contrasena=?2")
	Administrador LoginAdmin(String correo, String contrasena);
	
	@Query("SELECT u FROM Administrador u ORDER BY u.nombre")
	public Iterable<Administrador> listarOrdenados();

}
