package co.com.eam.gimansiosergio.controller;

import java.util.List;
import java.util.Map;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.utils.ObjectUtils;

import co.com.eam.gimansiosergio.CloudinaryConfig;
import co.com.eam.gimansiosergio.domain.Datosbancario;
import co.com.eam.gimansiosergio.domain.Socio;
import co.com.eam.gimansiosergio.repository.SocioRepository;

@Controller
public class SocioController {
	
	@Autowired
	private  SocioRepository socioRepo;
	
	@Autowired
	private CloudinaryConfig cloudc;

	 @GetMapping("/addSocio")
	    public String showSignUpForm(Socio socio,Model model) {
	     	 model.addAttribute("socios", socioRepo.findAll());
	        return "add-socio";
	    }
	
	@PostMapping("/add_socio")
	public String addSocio(@Valid Socio socio, BindingResult result, Model model, @RequestParam("file") MultipartFile file) {
		
		Socio socioEncontrado = (Socio) socioRepo.findByCorreo(socio.getCorreo());
		Socio socioEncontrado1 = (Socio) socioRepo.findByCedula(socio.getCedula());

		
		
	    if (socioEncontrado != null || socioEncontrado1 != null) {
	    	ObjectError objectError= new ObjectError("Error", "Este dato ya se encuentra registrado");
	    	ObjectError objectError1= new ObjectError("Cedula", "La Cedula ya se encuentra registrada");
	    	result.addError(objectError );
	    	new IllegalArgumentException("Invalid partner cedula partner:" + socio.getCedula());
	    	new IllegalArgumentException("Invalid partner email partner:" + socio.getCorreo());
	    	return "add-socio";
	} 		
	    
		if (result.hasErrors()) {
			return "add-socio";
		}
		try {
			Map uploadResult = cloudc .upload(file.getBytes(), ObjectUtils.asMap("resourcetype", "auto"));
			System.out.println(uploadResult.get("url").toString());
			socio.setFoto(uploadResult.get("url").toString());	
		}catch(Exception e){
			System.out.println(e.getMessage());
		}	

		socioRepo.save(socio);
		model.addAttribute("socios", socioRepo.findAll());
		return "login";
	}


	@GetMapping("/editSocio/{numeroSocio}")
	public String showUpdateForm(@PathVariable("numeroSocio") int numeroSocio, Model model) {
		Socio socio = socioRepo.findById(numeroSocio).orElseThrow(() -> new IllegalArgumentException("Invalid socio numeroSocio:" + numeroSocio));
		model.addAttribute("socio", socio);
		return "update-socio";
	}

	@PostMapping("/updateSocio/{numeroSocio}")
	public String updateClase(@PathVariable("numeroSocio") int numeroSocio, @Valid Socio socio, BindingResult result, Model model, @RequestParam("file") MultipartFile file, @RequestParam("cambioUrl") boolean cambioUrl) {
		if (result.hasErrors()) {
			socio.setNumeroSocio(numeroSocio);
			return "update-socio";
		}if (cambioUrl) { 
			try {
	            Map uploadResult = cloudc.upload(file.getBytes(), ObjectUtils.asMap("resourcetype", "auto"));
	            System.out.println(uploadResult.get("url").toString());
	            socio.setFoto(uploadResult.get("url").toString());	
	        } catch (Exception e) {
	        	System.out.println(e.getMessage());
	        }
		}

		socioRepo.save(socio);
		model.addAttribute("socios", socioRepo.findAll());
		return "listarSocio";
	}

	@GetMapping("/deleteSocio/{numeroSocio}")
	    public String deleteUser(@PathVariable("numeroSocio") int numeroSocio, Model model) {
	        Socio socio = socioRepo.findById(numeroSocio).orElseThrow(() -> new IllegalArgumentException("Invalid socio numeroSocio:" + numeroSocio));
	        socioRepo.delete(socio);
	        model.addAttribute("socios", socioRepo.findAll());
	        return "listarSocio";
	    }
	
	@GetMapping("/listarSocio")
    public String ListarCodigo(Model model) {
    	model.addAttribute("socios",socioRepo.findAll());
        return "listarSocio";
    }
	
	@GetMapping("/listarSocioAdministrador")
    public String ListarsocioAdmi(Model model) {
    	model.addAttribute("socios",socioRepo.findAll());
        return "listarSocioAdministrador";
    }
	
	@GetMapping("/socio/{numeroSocio}")
	public String detalleFactura(@PathVariable ("numeroSocio") String numeroSocio, Model model) throws Exception {
		Optional<Socio> socio = socioRepo.findById(Integer.parseInt(numeroSocio));
		if (!socio.isPresent()) {
			throw new Exception("No existe el socio con cedula:" + socio);
		}
		//List<Socio> socios = socioRepo.findById(Integer.parseInt(numeroSocio));
		model.addAttribute("socios", socio.get());
		return "listarSocio";
	}
	
	
	
}
