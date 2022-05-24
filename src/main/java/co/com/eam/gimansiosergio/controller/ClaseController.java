package co.com.eam.gimansiosergio.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import co.com.eam.gimansiosergio.domain.Clase;
import co.com.eam.gimansiosergio.repository.ClaseRepository;
import co.com.eam.gimansiosergio.repository.SalaRepository;

@Controller
public class ClaseController {
	@Autowired
	private  ClaseRepository claseRepo;

	@Autowired
	private  SalaRepository salaRepo;
	
	//Metodo que nos permite acceder a la plantilla add-clase y estamos recibiendo parametros de otras clases		 

	 @GetMapping("/addClase")
	    public String showSignUpForm(Clase clase,Model model) {
	     	 model.addAttribute("clases", claseRepo.findAll());
	     	 model.addAttribute("salas", salaRepo.findAll());
	        return "add-clase";
	    }
	//Metodo que nos permite modificar el estado de esta endtidad  a nivel de la base de datos o nivel de la logica del negocio.
		 //En este caso es para agregar una nueva clase , estos metodos tienen que se publicos
		
	@PostMapping("/add_clase")
	public String addClase(@Valid Clase clase, BindingResult result, Model model) {
		
		Clase socioEncontrado = (Clase) claseRepo.findByCodigo(clase.getCodigo());

		
		
	    if (socioEncontrado != null ) {
	    	ObjectError objectError= new ObjectError("Error", "Este dato ya se encuentra registrado");
	    	result.addError(objectError );
	    	new IllegalArgumentException("nvalido codigo de la clase:" + clase.getCodigo());
	    	return "add-clase";
	} 	
		
		if (result.hasErrors()) {
			model.addAttribute("clases", claseRepo.findAll());
			return "add-clase";
		}

		claseRepo.save(clase);
		model.addAttribute("clases", claseRepo.findAll());
		return "listarClase";
	}

	//Metodo que nos permite hacer una solicitud para hacer un edit, para ver que tiene ese registro, antes de esto recibe unos parametros           
	@GetMapping("/editClase/{codigo}")
	public String showUpdateForm(@PathVariable("codigo") int codigo, Model model) {
		Clase clase = claseRepo.findById(codigo).orElseThrow(() -> new IllegalArgumentException("Invalid clase codigo:" + codigo));
		model.addAttribute("clase", clase);
		model.addAttribute("salas", salaRepo.findAll());
		return "update-clase";
	}

	 //Este es para hacer un cambio, tiene dos opciones, si hay un error podemos cambiarlo a travez de este 
	  //Pero si es verdadero llama el repository si esta lo actualiza.    
	@PostMapping("/updateClase/{codigo}")
	public String updateClase(@PathVariable("codigo") int codigo, @Valid Clase clase, BindingResult result,
			Model model) {
		if (result.hasErrors()) {
			clase.setCodigo(codigo);
			return "update-clase";
		}

		claseRepo.save(clase);
		model.addAttribute("clases", claseRepo.findAll());
		return "listarClase";
	}

    //Se esta obteniendo una informacion y se elimina, y se actualiza para pintar la lista            

	@GetMapping("/deleteClase/{codigo}")
	    public String deleteUser(@PathVariable("codigo") int codigo, Model model) {
	        Clase clase = claseRepo.findById(codigo).orElseThrow(() -> new IllegalArgumentException("Invalid clase codigo:" + codigo));
	        claseRepo.delete(clase);
	        model.addAttribute("clases", claseRepo.findAll());
	        return "listarClase";
	    }
	
	//Metodo que nos devuelve una cadena(lista)
	@GetMapping("/listarClase")
    public String ListarCodigo(Model model) {
    	model.addAttribute("clases",claseRepo.findAll());
        return "listarClase";
    }
		
	//listar  clases para socio
		@GetMapping("/listarClaseSocio")
		public String listarOrdenada(Model model) {
			model.addAttribute("clases",claseRepo.findAll());
			return "listarClaseSocio";
			
		}
		
		//listar  clases para socio
		
		@GetMapping("/listarClaseVerInstructor")
		public String listarclaseInstructor(Model model) {
			model.addAttribute("clases",claseRepo.findAll());
			return "listarClaseVerInstructor";
			
		}
}