package co.com.eam.gimansiosergio.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import co.com.eam.gimansiosergio.domain.Claseinstructor;
import co.com.eam.gimansiosergio.repository.ClaseInstructorRepository;
import co.com.eam.gimansiosergio.repository.ClaseRepository;
import co.com.eam.gimansiosergio.repository.InstructorRepository;

@Controller
public class ClaseinstructorController {

	@Autowired
	private  ClaseInstructorRepository claseInstructorRepo;
	@Autowired
	private  InstructorRepository instructorRepo;
	@Autowired
	private  ClaseRepository claseRepo;
	

	 @GetMapping("/addClaseinstructor")
	    public String showSignUpForm(Claseinstructor ci,Model model) {
	     	 model.addAttribute("cis", claseInstructorRepo.findAll());
	     	 model.addAttribute("clases", claseRepo.findAll());
	     	 model.addAttribute("instructores", instructorRepo.findAll());
	        return "add-claseInstructor";
	    }
	
	@PostMapping("/add_claseInstructor")
	public String addClaseInstructor(@Valid Claseinstructor ci, BindingResult result, Model model) {
		if (result.hasErrors()) {
			model.addAttribute("cis", claseInstructorRepo.findAll());
			return "add-claseInstructor";
		}

		claseInstructorRepo.save(ci);
		model.addAttribute("cis", claseInstructorRepo.findAll());
		return "listarClaseInstructor";
	}

	@GetMapping("/editClaseInstructor/{id}")
	public String showUpdateForm(@PathVariable("id") int id, Model model) {
		Claseinstructor ci = claseInstructorRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid calse instructor id:" + id));
		model.addAttribute("ci", ci);
		model.addAttribute("clases", claseRepo.findAll());
    	 model.addAttribute("instructores", instructorRepo.findAll());
		return "update-claseInstructor";
	}

	@PostMapping("/updateClaseInstructor/{id}")
	public String updateClaseInstructor(@PathVariable("id") int id, @Valid Claseinstructor ci, BindingResult result,
			Model model) {
		if (result.hasErrors()) {
			model.addAttribute("cis", claseInstructorRepo.findAll());
			ci.setId(id);
			return "update-claseInstructor";
		}

		claseInstructorRepo.save(ci);
		model.addAttribute("cis", claseInstructorRepo.findAll());
		return "listarClaseInstructor";
	}

	@GetMapping("/deleteClaseInstructor/{id}")
	    public String deleteClaseInstructor(@PathVariable("id") int id, Model model) {
		Claseinstructor ci = claseInstructorRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid  clase instructor id:" + id));
	        claseInstructorRepo.delete(ci);
	        model.addAttribute("cis", claseInstructorRepo.findAll());
	        return "listarClaseInstructor";
	    }
	
	@GetMapping("/listarClaseInstructor")
 public String ListarCodigo(Model model) {
 	model.addAttribute("cis",claseInstructorRepo.findAll());
     return "listarClaseInstructor";
 }
	
	// ver las class que estan asociadas a los instructores
	@GetMapping("/verRelacionInstructorClase")
 public String ListarclaseInstructor(Model model) {
 	model.addAttribute("cis",claseInstructorRepo.findAll());
     return "verRelacionInstructorClase";
 }
	
//listar las clases de los instructores ordenados
	@GetMapping("/listaClaseInstructorOrdenado")
	public String listarOrdenados1(Model m) {
		Iterable<Claseinstructor> listaClaseInstOrdenado=claseInstructorRepo.listarOrdenados();
		
		m.addAttribute("listaOrder", listaClaseInstOrdenado);
		
		return "claseinstructorOrdenados";
		
	}
}
