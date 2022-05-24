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
import co.com.eam.gimansiosergio.domain.Aparato;
import co.com.eam.gimansiosergio.repository.AparatoRepository;
import co.com.eam.gimansiosergio.repository.SalaRepository;

@Controller
public class AparatoController {
	private final AparatoRepository apaRepo;
	private final SalaRepository salaRepo;
		
	@Autowired
	public AparatoController(AparatoRepository apaRepo, SalaRepository salaRepo) {
		this.apaRepo = apaRepo;
		this.salaRepo = salaRepo;
	}
	@Autowired
	private CloudinaryConfig cloudc;

	 @GetMapping("/addAparato")
	    public String showSignUpForm(Aparato aparato,Model model) {
	     	model.addAttribute("aparatos", apaRepo.findAll());
	     	model.addAttribute("salas", salaRepo.findAll());
	        return "add-aparato";
	    }
	
	@PostMapping("/add_aparato")
	public String addAparato(@Valid Aparato aparato, BindingResult result, Model model, @RequestParam("file") MultipartFile file) {
		
		Aparato socioEncontrado = (Aparato) apaRepo.findByCodigo(aparato.getCodigo());

	    if (socioEncontrado != null ) {
	    	ObjectError objectError= new ObjectError("Error", "Este dato ya se encuentra registrado");
	    	result.addError(objectError );
	    	new IllegalArgumentException("Invalid codigo de aparato:" + aparato.getCodigo());
	    	return "add-aparato";
	} 	
		
		
		if (result.hasErrors()) {
			return "add-aparato";
		}
		try {
			Map uploadResult = cloudc .upload(file.getBytes(), ObjectUtils.asMap("resourcetype", "auto"));
			System.out.println(uploadResult.get("url").toString());
			aparato.setFoto(uploadResult.get("url").toString());	
		}catch(Exception e){
			System.out.println(e.getMessage());
		}	
		apaRepo.save(aparato);
		model.addAttribute("aparatos", apaRepo.findAll());
		return "listarAparato";
	}


//	@GetMapping("/editAparato/{codigo}")
//	public String showUpdateForm(@PathVariable("codigo") int codigo, Model model) {
//		Aparato aparato = apaRepo.findById(codigo).orElseThrow(() -> new IllegalArgumentException("Invalid aparato codigo:" + codigo));
//		model.addAttribute("aparato", aparato);
//		model.addAttribute("salas", salaRepo.findAll());
//		return "update-aparato";
//	}
	
	@GetMapping("/editAparato/{codigo}")
    public String showUpdateForm(@PathVariable("codigo") int codigo, Model model) {
        Aparato apa = apaRepo.findById(codigo).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + codigo));
        model.addAttribute("aparato", apa);
        model.addAttribute("salas", salaRepo.findAll());
        return "update-aparato";
    }
	
	@PostMapping("/updateAparato/{codigo}")
	public String updateAparato(@PathVariable("codigo") int codigo, @Valid Aparato aparato, BindingResult result, Model model, @RequestParam("file") MultipartFile file, @RequestParam("cambioUrl") boolean cambioUrl) {
		if (result.hasErrors()) {
			model.addAttribute("aparatos", apaRepo.findAll());
			aparato.setCodigo(codigo);
			return "update-aparato";
		}if (cambioUrl) { 
			try {
	            Map uploadResult = cloudc.upload(file.getBytes(), ObjectUtils.asMap("resourcetype", "auto"));
	            System.out.println(uploadResult.get("url").toString());
	            aparato.setFoto(uploadResult.get("url").toString());	
	        } catch (Exception e) {
	        	System.out.println(e.getMessage());
	        }
		}

		apaRepo.save(aparato);
		model.addAttribute("aparatos", apaRepo.findAll());
		return "listarAparato";
	}

	@GetMapping("/deleteAparato/{codigo}")
	    public String deleteUser(@PathVariable("codigo") int codigo, Model model) {
	        Aparato aparato = apaRepo.findById(codigo).orElseThrow(() -> new IllegalArgumentException("Invalid aparatos codigo:" + codigo));
	        apaRepo.delete(aparato);
	        model.addAttribute("aparatos", apaRepo.findAll());
	        return "listarAparato";
	    }
	
	@GetMapping("/listarAparato")
    public String ListarCodigo(Model model) {
    	model.addAttribute("aparatos",apaRepo.findAll());
        return "listarAparato";
    }
	
	
	
	
	
	
	
	
	//listar  ordenado por estado conservacion
	
	
	@GetMapping("/listarAparatoOrdenado")
	public String listarAparatoEstado(Model m) {
		Iterable<Aparato> listarOrdenado=apaRepo.listarOrdenado("Nuevo");
		
		m.addAttribute("listaEstado", listarOrdenado);
		
		return "AparatoOrdenado";
		
	}
	
}
