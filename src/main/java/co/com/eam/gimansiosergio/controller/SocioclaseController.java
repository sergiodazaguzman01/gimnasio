package co.com.eam.gimansiosergio.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import co.com.eam.gimansiosergio.domain.Socioclase;
import co.com.eam.gimansiosergio.repository.ClaseRepository;
import co.com.eam.gimansiosergio.repository.SocioClaseRepository;
import co.com.eam.gimansiosergio.repository.SocioRepository;

@Controller
public class SocioclaseController {

	@Autowired
	private  SocioClaseRepository socioClaseRepo;
	@Autowired
	private  SocioRepository socioRepo;
	@Autowired
	private  ClaseRepository claseRepo;

	 @GetMapping("/addSocioClase")
	    public String showSignUpForm(Socioclase socioclase,Model model) {
	     	 model.addAttribute("socioclases", socioClaseRepo.findAll());
	     	model.addAttribute("socios", socioRepo.findAll());
	     	model.addAttribute("clases", claseRepo.findAll());
	        return "add-socioClase";
	    }
	
	@PostMapping("/add_socioClase")
	public String addSocioClase(@Valid Socioclase socioclase, BindingResult result, Model model) {
		if (result.hasErrors()) {
			model.addAttribute("socioClases", socioClaseRepo.findAll());
			return "add-socioClase";
		}

		socioClaseRepo.save(socioclase);
		model.addAttribute("socioClases", socioClaseRepo.findAll());
		return "listarSocioClase";
	}


	@GetMapping("/editSocioClase/{id}")
	public String showUpdateForm(@PathVariable("id") int id, Model model) {
		Socioclase socioclase = socioClaseRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid socio clase id:" + id));
		model.addAttribute("socioClase", socioclase);
		model.addAttribute("socios", socioRepo.findAll());
     	model.addAttribute("clases", claseRepo.findAll());
		return "update-socioClase";
	}

	@PostMapping("/updateSocioClase/{id}")
	public String updateClase(@PathVariable("id") int id, @Valid Socioclase socioclase, BindingResult result,
			Model model) {
		if (result.hasErrors()) {
			model.addAttribute("socioClases", socioClaseRepo.findAll());
			socioclase.setId(id);
			return "update-socioClase";
		}

		socioClaseRepo.save(socioclase);
		model.addAttribute("socioclases", socioClaseRepo.findAll());
		return "listarSocioClase";
	}

	@GetMapping("/deleteSocioClase/{id}")
	    public String deleteUser(@PathVariable("id") int id, Model model) {
	        Socioclase socioclase = socioClaseRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid socio clase id:" + id));
	        socioClaseRepo.delete(socioclase);
	        model.addAttribute("socioClases", socioClaseRepo.findAll());
	        return "listarSocioClase";
	    }
	
	@GetMapping("/listarSocioClase")
    public String ListarCodigo(Model model) {
    	model.addAttribute("socioClases",socioClaseRepo.findAll());
        return "listarSocioClase";
    }
//	///socioClase ordenados
//	@GetMapping("/listaSociosOrdenado")
//	public String listarOrdenados1(Model m) {
//		Iterable<Socioclase> listasocOrdenados=socioClaseRepo.buscarSociosPorClaseoPorCedula("1");
//		
//		m.addAttribute("listaOrder", listasocOrdenados);
//		
//		return "socioOrdenados";
//		
//	}
	
	
}
