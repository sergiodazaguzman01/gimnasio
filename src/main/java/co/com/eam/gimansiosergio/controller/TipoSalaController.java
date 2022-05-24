package co.com.eam.gimansiosergio.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import co.com.eam.gimansiosergio.domain.Tiposala;
import co.com.eam.gimansiosergio.repository.TipoSalaRepository;

@Controller
public class TipoSalaController {
	@Autowired
	private final TipoSalaRepository tipoRepo;
	@Autowired
	public TipoSalaController(TipoSalaRepository tipoRepo) {
		this.tipoRepo = tipoRepo;
	}

	 @GetMapping("/addTipoSala")
	    public String showSignUpForm(Tiposala tipo,Model model) {
	     	 model.addAttribute("tipos", tipoRepo.findAll());
	        return "add-tiposala";
	    }
	
	@PostMapping("/add_tiposala")
	public String addtipo(@Valid Tiposala tipo, BindingResult result, Model model) {
		if (result.hasErrors()) {
			model.addAttribute("tipos", tipoRepo.findAll());
			return "add-tiposala";
		}

		tipoRepo.save(tipo);
		model.addAttribute("tipos", tipoRepo.findAll());
		return "listarTiposala";
	}


	@GetMapping("/editTiposala/{id}")
	public String showUpdateForm(@PathVariable("id") int id, Model model) {
		Tiposala tipo = tipoRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid tipo de sala id: " + id));
		model.addAttribute("tipo", tipo);
		return "update-tiposala";
	}

	@PostMapping("/updateTiposala/{id}")
	public String updateTipo(@PathVariable("id") int id, @Valid Tiposala tipo, BindingResult result,
			Model model) {
		if (result.hasErrors()) {
			tipo.setId(id);
			return "update-tiposala";
		}

		tipoRepo.save(tipo);
		model.addAttribute("tipos", tipoRepo.findAll());
		return "listarTipoSala";
	}

	@GetMapping("/deleteTiposala/{id}")
	    public String deleteUser(@PathVariable("id") int id, Model model) {
	        Tiposala tipo = tipoRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid tipo id:" + id));
	        tipoRepo.delete(tipo);
	        model.addAttribute("tipos", tipoRepo.findAll());
	        return "listarTipoSala";
	    }
	
	@GetMapping("/listarTipoSala")
    public String ListarCodigo(Model model) {
    	model.addAttribute("tipos",tipoRepo.findAll());
        return "listarTipoSala";
    }
	//salas ordenadas
	
	@GetMapping("/listaTipoSalaOrdenado")
	public String listarOrdenados1(Model m) {
		Iterable<Tiposala> listaTiposaOrdenado=tipoRepo.listarOrdenados();
		
		m.addAttribute("listaOrder", listaTiposaOrdenado);
		
		return "tiposalaOrdenados";
		
	}
}
