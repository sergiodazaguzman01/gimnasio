package co.com.eam.gimansiosergio.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import co.com.eam.gimansiosergio.domain.Datosbancario;
import co.com.eam.gimansiosergio.domain.Socio;
import co.com.eam.gimansiosergio.repository.DatosBancariosRepository;
import co.com.eam.gimansiosergio.repository.SocioRepository;


@Controller
public class DatosbancarioController {
	@Autowired
	private  DatosBancariosRepository dtRepo;
	@Autowired
	private  SocioRepository socioRepo;

	

	 @GetMapping("/addDatosbancario")
	    public String showSignUpForm(Datosbancario dt,Model model) {
	     	 model.addAttribute("dts", dtRepo.findAll());
	     	 model.addAttribute("socios", socioRepo.findAll());
	        return "add-datosBancarios";
	    }
	
	@PostMapping("/add_datosBancarios")
	public String addDatosBancario(@Valid Datosbancario dt, BindingResult result, Model model) {
		
		Datosbancario socioEncontrado = (Datosbancario) dtRepo.findBynumeroCuenta(dt.getNumeroCuenta());

		
		
	    if (socioEncontrado != null ) {
	    	ObjectError objectError= new ObjectError("Error", "Este dato ya se encuentra registrado");
	    	result.addError(objectError );
	    	new IllegalArgumentException("Numero de cuenta incorrecto:" + dt.getNumeroCuenta());
	    	return "add-datosBancarios";
	} 		
		
		if (result.hasErrors()) {
			model.addAttribute("dts", dtRepo.findAll());
			return "add-datosBancarios";
		}

		dtRepo.save(dt);
		model.addAttribute("dts", dtRepo.findAll());
		return "listarDatosbancario";
	}

//	@GetMapping("/editDatosbancario/{codigo}")
//	public String showUpdateForm(@PathVariable("codigo") int codigo, Model model) {
//		Datosbancario dt = dtRepo.findById(codigo).orElseThrow(() -> new IllegalArgumentException("Invalid DatosBancarios codigo:" + codigo));
//		model.addAttribute("dt", dt);
//		model.addAttribute("socios", socioRepo.findAll());
//		return "update-datosBancarios";
//	}
//
//	@PostMapping("/updateDatosbancario/{codigo}")
//	public String updateDatosBancario(@PathVariable("codigo") int codigo, @Valid Datosbancario dt, BindingResult result,
//			Model model) {
//		if (result.hasErrors()) {
//			dt.setCodigo(codigo);
//			return "update-datosBancarios";
//		}
//
//		dtRepo.save(dt);
//		model.addAttribute("dts", dtRepo.findAll());
//		return "listarDatosbancario";
//	}

    @GetMapping("/editDatosbancario/{codigo}")
    public String showUpdateForm(@PathVariable("codigo") int codigo, Model model) {
    	Datosbancario dt = dtRepo.findById(codigo).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + codigo));
        model.addAttribute("dt", dt);
        model.addAttribute("socios", socioRepo.findAll());
        return "update-datosBancarios";
    }
    
    @PostMapping("/updateDatosbancario/{codigo}")
    public String updateUser(@PathVariable("codigo") int codigo, @Valid Datosbancario dt, BindingResult result, Model model) {
        if (result.hasErrors()) {
        	dt.setCodigo(codigo);
            return "update-datosBancarios";
        }
        
        dtRepo.save(dt);
        model.addAttribute("dts", dtRepo.findAll());
        return "listarDatosbancario";
    }
	
	@GetMapping("/deleteDatosbancario/{codigo}")
	    public String deleteUser(@PathVariable("codigo") int codigo, Model model) {
		Datosbancario dt = dtRepo.findById(codigo).orElseThrow(() -> new IllegalArgumentException("Invalid  DatosBancarios codigo:" + codigo));
	        dtRepo.delete(dt);
	        model.addAttribute("dts", dtRepo.findAll());
	        return "listarDatosbancario";
	    }
	
	@GetMapping("/listarDatosbancario")
    public String ListarCodigo(Model model) {
    	model.addAttribute("dts",dtRepo.findAll());
        return "listarDatosbancario";
    }
	
	
	
	@GetMapping("/datos/{numeroSocio}")
	public String detalleFactura(@PathVariable ("numeroSocio") String numeroSocio, Model model) throws Exception {
		Optional<Socio> socio = socioRepo.findById(Integer.parseInt(numeroSocio));
		if (!socio.isPresent()) {
			throw new Exception("No existe el socio con cedula:" + socio);
		}
		List<Datosbancario> datos = dtRepo.datos(Integer.parseInt(numeroSocio));
		model.addAttribute("dts", datos);
		model.addAttribute("socio", socio.get());
		return "listarDatosbancario";
	}
}












