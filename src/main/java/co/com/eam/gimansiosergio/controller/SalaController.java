package co.com.eam.gimansiosergio.controller;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.utils.ObjectUtils;

import co.com.eam.gimansiosergio.CloudinaryConfig;
import co.com.eam.gimansiosergio.domain.Sala;
import co.com.eam.gimansiosergio.repository.SalaRepository;
import co.com.eam.gimansiosergio.repository.TipoSalaRepository;



@Controller
public class SalaController {
	@Autowired
	private  SalaRepository salaRepo;
	@Autowired
	private  TipoSalaRepository tipoRepo;
	@Autowired
	private CloudinaryConfig cloudc;

	 @GetMapping("/addSala")
	    public String showSignUpForm(Sala sala,Model model) {
	     	 model.addAttribute("salas", salaRepo.findAll());
	     	model.addAttribute("tipos", tipoRepo.findAll());
	        return "add-sala";
	    }
	
	@PostMapping("/add_sala")
	public String addSala(@Valid Sala sala, BindingResult result, Model model, @RequestParam("file") MultipartFile file) {
		

		Sala socioEncontrado = (Sala) salaRepo.findByNumeroSala(sala.getNumeroSala());

	    if (socioEncontrado != null ) {
	    	ObjectError objectError= new ObjectError("Error", "Este dato ya se encuentra registrado");
	    	result.addError(objectError );
	    	new IllegalArgumentException("Invalid partner numero sala partner:" + sala.getNumeroSala());
	    	return "add-sala";
	} 
		
		if (result.hasErrors()) {
			return "add-sala";
		}
		try {
			Map uploadResult = cloudc .upload(file.getBytes(), ObjectUtils.asMap("resourcetype", "auto"));
			System.out.println(uploadResult.get("url").toString());
			sala.setFoto(uploadResult.get("url").toString());	
		}catch(Exception e){
			System.out.println(e.getMessage());
		}	

		salaRepo.save(sala);
		model.addAttribute("salas", salaRepo.findAll());
		return "listarSala";
	}


	@GetMapping("/editSala/{numeroSala}")
	public String showUpdateForm(@PathVariable("numeroSala") int numeroSala, Model model) {
		Sala sala = salaRepo.findById(numeroSala).orElseThrow(() -> new IllegalArgumentException("Invalid sala numero:" + numeroSala));
		model.addAttribute("sala", sala);
		model.addAttribute("tipos", tipoRepo.findAll());
		return "update-sala";
	}

	@PostMapping("/updateSala/{numeroSala}")
	public String updateSala(@PathVariable("numeroSala") int numeroSala, @Valid Sala sala, BindingResult result, Model model, @RequestParam("file") MultipartFile file, @RequestParam("cambioUrl") boolean cambioUrl) {
		if (result.hasErrors()) {
			model.addAttribute("sala", sala);
			sala.setNumeroSala(numeroSala);
			return "update-sala";
		}
		if (cambioUrl) { 
			try {
	            Map uploadResult = cloudc.upload(file.getBytes(), ObjectUtils.asMap("resourcetype", "auto"));
	            System.out.println(uploadResult.get("url").toString());
	            sala.setFoto(uploadResult.get("url").toString());	
	        } catch (Exception e) {
	        	System.out.println(e.getMessage());
	        }
		}
		salaRepo.save(sala);
		model.addAttribute("salas", salaRepo.findAll());
		return "listarSala";
	}

	@GetMapping("/deleteSala/{numeroSala}")
	    public String deleteUser(@PathVariable("numeroSala") int numeroSala, Model model) {
	        Sala sala = salaRepo.findById(numeroSala).orElseThrow(() -> new IllegalArgumentException("Invalid sala numeroSala:" + numeroSala));
	        salaRepo.delete(sala);
	        model.addAttribute("salas", salaRepo.findAll());
	        return "listarSala";
	    }
	
	@GetMapping("/listarSala")
    public String ListarCodigo(Model model) {
    	model.addAttribute("salas",salaRepo.findAll());
        return "listarSala";
    }
	
	
	
	
	
	
	
	
	//salas ordenadas
	@GetMapping("/listaSalaOrdenado")
	public String listarOrdenados1(Model m) {
		Iterable<Sala> listaSalitaOrdenado=salaRepo.listarOrdenados();
		
		m.addAttribute("listaOrder", listaSalitaOrdenado);
		
		return "salaOrdenados";
		
	}
}
