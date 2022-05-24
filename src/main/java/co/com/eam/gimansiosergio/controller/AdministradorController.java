package co.com.eam.gimansiosergio.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import co.com.eam.gimansiosergio.domain.Administrador;
import co.com.eam.gimansiosergio.repository.AdministradorRepository;
@Controller
@RequestMapping("/administrador")
public class AdministradorController {
	
	 @Autowired
	 private   AdministradorRepository iAdministradorRepo;

	  
	     
	    @GetMapping("/signupAdmind")
	    public String showSignUpForm(Administrador administrador) {
	        return "index-administrador";
	    }
	    
	    @PostMapping("/addAdmind")
	    public String addUser(@Valid Administrador administrador, BindingResult result, Model model) {
	        if (result.hasErrors()) {
	            return "index-administrador";
	        }
	        
	        iAdministradorRepo.save(administrador);
	        model.addAttribute("users", iAdministradorRepo.findAll());
	        return "index";
	    }
	    
	    @GetMapping("/editAdmind/{cedula}")
	    public String showUpdateForm(@PathVariable("cedula") int cedula, Model model) {
	    	Administrador administrador = iAdministradorRepo.findById(cedula).orElseThrow(() -> new IllegalArgumentException("Invalid Administrador cedula:" + cedula));
	        model.addAttribute("user", administrador);
	        return "update-admind";
	    }
	    
	    @PostMapping("/updateAdmind/{cedula}")
	    public String updateUser(@PathVariable("cedula") int cedula, @Valid Administrador administrador, BindingResult result, Model model) {
	        if (result.hasErrors()) {
	        	administrador.setCedula(cedula);
	            return "update-admind";
	        }
	        
	        iAdministradorRepo.save(administrador);
	        model.addAttribute("administrador", iAdministradorRepo.findAll());
	        return "index";
	    }
	    
	    @GetMapping("/deleteAdmind/{cedula}")
	    public String deleteUser(@PathVariable("cedula") int cedula, Model model) {
	        Administrador administrador = iAdministradorRepo.findById(cedula).orElseThrow(() -> new IllegalArgumentException("Invalid administrador cedula:" + cedula));
	        iAdministradorRepo.delete(administrador);
	        model.addAttribute("administrador", iAdministradorRepo.findAll());
	        return "index";
	    }
	    @GetMapping("/listarAdmind")
	    public String listar(Model model){
	    	model.addAttribute("administrador", iAdministradorRepo.findAll());
	    	return "greeting";
	    	
	    }
	    
	    //listar administradores orddenados
	    
	    @GetMapping("/listaAdministradorOrdenado")
		public String listarOrdenados1(Model m) {
			Iterable<Administrador> listaAdminisOrdenado=iAdministradorRepo.listarOrdenados();
			
			m.addAttribute("listaOrder", listaAdminisOrdenado);
			
			return "administradorOrdenados";
			
		}
	
}
