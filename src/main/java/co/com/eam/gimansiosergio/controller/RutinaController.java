package co.com.eam.gimansiosergio.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.utils.ObjectUtils;

import co.com.eam.gimansiosergio.CloudinaryConfig;
import co.com.eam.gimansiosergio.domain.Datosbancario;
import co.com.eam.gimansiosergio.domain.Instructor;
import co.com.eam.gimansiosergio.domain.Rutina;
import co.com.eam.gimansiosergio.domain.Socio;
import co.com.eam.gimansiosergio.repository.InstructorRepository;
import co.com.eam.gimansiosergio.repository.RutinaRepository;
import co.com.eam.gimansiosergio.repository.SocioRepository;

@Controller
public class RutinaController {
	@Autowired
	private  RutinaRepository rutinaRepo;
	@Autowired
	private  InstructorRepository instructorRepo;
	@Autowired
	private  SocioRepository socioRepo;
	@Autowired
	private CloudinaryConfig cloudc;
	
	 @GetMapping("/addRutina")
	    public String showSignUpForm(Rutina rutina,Model model) {
	     	model.addAttribute("rutinas", rutinaRepo.findAll());
	     	model.addAttribute("instructores", instructorRepo.findAll());
	     	model.addAttribute("socios", socioRepo.findAll());
	        return "add-rutina";
	    }
	
	@PostMapping("/add_rutina")
	public String addRutina(@Valid Rutina rutina, BindingResult result, Model model, @RequestParam("file") MultipartFile file) {
		if (result.hasErrors()) {
			model.addAttribute("rutinas", rutinaRepo.findAll());
			model.addAttribute("instructores", instructorRepo.findAll());
	     	model.addAttribute("socios", socioRepo.findAll());
			return "add-rutina";
		}try {
			Map uploadResult = cloudc .upload(file.getBytes(), ObjectUtils.asMap("resourcetype", "auto"));
			System.out.println(uploadResult.get("url").toString());
			rutina.setFoto(uploadResult.get("url").toString());	
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		rutinaRepo.save(rutina);
		model.addAttribute("rutinas", rutinaRepo.findAll());
		return "listarRutina";
	}


	@GetMapping("/editRutina/{id}")
	public String showUpdateForm(@PathVariable("id") int id, Model model) {
		Rutina rutina = rutinaRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid rutina id:" + id));
		model.addAttribute("rutina", rutina);
		model.addAttribute("instructores", instructorRepo.findAll());
     	model.addAttribute("socios", socioRepo.findAll());
		return "update-rutina";
	}

	@PostMapping("/updateRutina/{id}")
	public String updateRutina(@PathVariable("id") int id, @Valid Rutina rutina, BindingResult result, Model model, @RequestParam("file") MultipartFile file, @RequestParam("cambioUrl") boolean cambioUrl) {
		if (result.hasErrors()) {
			rutina.setId(id);
			return "update-rutina";
		}
		if (cambioUrl) { 
			try {
	            Map uploadResult = cloudc.upload(file.getBytes(), ObjectUtils.asMap("resourcetype", "auto"));
	            System.out.println(uploadResult.get("url").toString());
	            rutina.setFoto(uploadResult.get("url").toString());	
	        } catch (Exception e) {
	        	System.out.println(e.getMessage());
	        }
		}

		rutinaRepo.save(rutina);
		model.addAttribute("rutinas", rutinaRepo.findAll());
		return "listarRutina";
	}

	@GetMapping("/deleteRutina/{id}")
	    public String deleteRutina(@PathVariable("id") int id, Model model) {
		Rutina rutina = rutinaRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid rutina id:" + id));
	        rutinaRepo.delete(rutina);
	        model.addAttribute("rutinas", rutinaRepo.findAll());
	        return "listarRutina";
	    }
	
	@GetMapping("/listarRutina")
 public String ListarCedula(Model model) {
 	model.addAttribute("rutinas",rutinaRepo.findAll());
     return "listarRutina";
 }
	
	@GetMapping("/listarRutinaSocio")
 public String Listarrutina(Model model) {
 	model.addAttribute("rutinas",rutinaRepo.findAll());
     return "listarRutinaSocio";
 }
	
	//listar  rutina por id y por descripcion
	
	@GetMapping("/rutina/{cedula}")
	public String detalleFactura(@PathVariable ("cedula") String cedula, Model model) throws Exception {
		Optional<Instructor> ins = instructorRepo.findById(Integer.parseInt(cedula));
		if (!ins.isPresent()) {
			throw new Exception("No existe el Instructor con cedula:" + ins);
		}
		List<Rutina> datos = rutinaRepo.rutina(Integer.parseInt(cedula));
		model.addAttribute("rutinas", datos);
		model.addAttribute("instructor", ins.get());
		return "listarVerRutina";
	}
	
}
