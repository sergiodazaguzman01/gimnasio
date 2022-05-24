package co.com.eam.gimansiosergio.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import co.com.eam.gimansiosergio.domain.Datosbancario;

@Repository
public interface DatosBancariosRepository extends CrudRepository<Datosbancario, Integer>{

	//List<Datosbancario> ClienteCedula(int numeroSocio);
	
	@Query("SELECT D FROM Datosbancario D WHERE D.socio.numeroSocio=?1")
	List<Datosbancario> datos(int numeroSocio);
	
//	@Query("SELECT D FROM Datosbancario D WHERE D.codigo=?1")
//	List<Datosbancario> BuscarDatosBancariosCodigo(Integer codigo);

	@Query("SELECT u FROM Datosbancario u ORDER BY u.tipoCuenta")
	public Iterable<Datosbancario> listarOrdenados();
	
	@Query("Select D from Datosbancario D WHERE D.socio.numeroSocio=?1")
	public Iterable<Datosbancario> listarDatosSocio();
	
	@Query("SELECT COUNT(u) FROM Datosbancario u WHERE u.tipoCuenta = :cuenta")
    public Iterable<Datosbancario> ContarLosTiposDeCuenta(@Param ("cuenta") String CuantaAhorros);
	
	@Query("Select A from Datosbancario A WHERE A.socio.numeroSocio=?1")
	List<Datosbancario> listarSocio(int numeroSocio);
	
	Datosbancario findBynumeroCuenta (Integer numeroCuenta);
	
	
/**
	@Query("SELECT u FROM Datosbancario u ORDER BY u.tipoCuenta")
	public Iterable<Datosbancario> listarOrdenados();

	@Query(" SELECT s.nombre, s.apellido, d.tipoCuenta FROM Socio s JOIN Datosbancario d " + 
			" ON s.numeroSocio = d.socios WHERE d.tipoCuenta = 'ahorros'")
	public Iterable<Datosbancario> ListarSociosConCuentaAhorros();
	
	@Query(" SELECT s.nombre, s.apellido, d.tipoCuenta FROM Socio s JOIN Datosbancario d " + 
			" ON s.numeroSocio = d.socios WHERE d.tipoCuenta = 'corriente'")
	public Iterable<Datosbancario> ListarSociosConCuentaCorriente();**/

}
