package co.com.eam.gimansiosergio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import co.com.eam.gimansiosergio.domain.Administrador;
import co.com.eam.gimansiosergio.domain.Instructor;
import co.com.eam.gimansiosergio.domain.Socio;
import co.com.eam.gimansiosergio.repository.AdministradorRepository;
import co.com.eam.gimansiosergio.repository.InstructorRepository;
import co.com.eam.gimansiosergio.repository.SocioRepository;


@Controller
public class InicioController {
	
	@Autowired
	private AdministradorRepository AdministradorRepo;
	@Autowired
	private SocioRepository SocioRepo;
	@Autowired
	private InstructorRepository InstructorRepo;
	@Autowired
	public static Administrador admindlogeado;
	@Autowired
	public static Instructor instructorlogeado;
	@Autowired
	public static Socio sociologeado;
	
	@RequestMapping("/")
	public String inicio(Model model) {
		return "homepage";
	}
	@RequestMapping("/login")
	public String login(Model model) {
		return "login";
	}
	
	@RequestMapping("/admind")
	public String InicioAdministrador(Model model) {
		model.addAttribute("administrador", admindlogeado);
		return "index";
	}
	
	@RequestMapping("/socio")
	public String InicioSocio(Model model) {
		model.addAttribute("socios", sociologeado);
		return "index-socio";
	}
	
	@RequestMapping("/instructor")
	public String InicioInstructor(Model model) {
		model.addAttribute("instructores", instructorlogeado);
		return "index-instructor";
	}
	
	@SuppressWarnings("unused")
	@PostMapping("/ingresar")
	public String ingresar(Socio socio,  BindingResult result, Model model) {
		if(result.hasErrors()) {
		 	model.addAttribute("socios", new Socio());
		 	model.addAttribute("instructores", new Instructor());
		 	model.addAttribute("administrador", new Administrador());
		 	return "login";
		}
		Socio nuevosocio = SocioRepo.LoginSocio(socio.getCorreo(), socio.getContrasena());
		Instructor nuevoinstructor = InstructorRepo.LoginInstructor(socio.getCorreo(), socio.getContrasena());
		Administrador admind = AdministradorRepo.LoginAdmin(socio.getCorreo(), socio.getContrasena());
		if(nuevosocio!=null) {
			sociologeado = nuevosocio;
			return "redirect:/socio";
		}else if(admind!=null){
			admindlogeado = admind;
			return "redirect:/admind";			
		}else if (nuevoinstructor!=null) {
			instructorlogeado = nuevoinstructor;
			return "redirect:/instructor";
		}
	 	model.addAttribute("error", "corero o contraseña incorectos");
		return "login";	
	}

}
