package co.com.eam.gimansiosergio.controller;

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
import co.com.eam.gimansiosergio.domain.Instructor;
import co.com.eam.gimansiosergio.repository.InstructorRepository;

@Controller
public class InstructorController {
	
	@Autowired
	private  InstructorRepository insRepo;

	@Autowired
	private CloudinaryConfig cloudc;

	 @GetMapping("/addInstructor")
	    public String showSignUpForm(Instructor ins,Model model) {
	     	 model.addAttribute("instructores", insRepo.findAll());
	        return "add-instructor";
	    }
	 
	@PostMapping("/add_instructor")
	public String addInstructor(@Valid Instructor ins, BindingResult result, Model model, @RequestParam("file") MultipartFile file) {
			
		Instructor socioEncontrado = (Instructor) insRepo.findByCorreo(ins.getCorreo());
		Instructor socioEncontrado1 = (Instructor) insRepo.findByCedula(ins.getNumero_cedula());
		
		 if (socioEncontrado != null || socioEncontrado1 != null) {
		    	ObjectError objectError= new ObjectError("Error", "Este dato ya se encuentra registrado");
		    	//ObjectError objectError1= new ObjectError("Cedula", "La Cedula ya se encuentra registrada");
		    	//ObjectError objectError2= new ObjectError("Celular", "El Celular ya se encuentra registrado"); 	
		    	result.addError(objectError );
		    	new IllegalArgumentException("Invalid partner cedula partner:" + ins.getNumero_cedula());
		    	//new IllegalArgumentException("Invalid partner celular partner:" + socio.getCelular());
		    	new IllegalArgumentException("Invalid partner email partner:" + ins.getCorreo());
		    	return "add-instructor";
		}
		
		
		if (result.hasErrors()) {
		//	model.addAttribute("instructores", insRepo.findAll());
			return "add-instructor";
		}	
		try {
			Map uploadResult = cloudc .upload(file.getBytes(), ObjectUtils.asMap("resourcetype", "auto"));
			System.out.println(uploadResult.get("url").toString());
			ins.setFoto(uploadResult.get("url").toString());	
		}catch(Exception e){
			System.out.println(e.getMessage());
		}		
		insRepo.save(ins);
		model.addAttribute("instructores", insRepo.findAll());
		return "listarInstructor";
	}


	@GetMapping("/editInstructor/{cedula}")
	public String showUpdateForm(@PathVariable("cedula") int cedula, Model model) {
		Instructor ins = insRepo.findById(cedula).orElseThrow(() -> new IllegalArgumentException("Invalid instructor cedula:" + cedula));
		model.addAttribute("instructor", ins);
		return "update-instructor";
	}

	@PostMapping("/updateInstructor/{cedula}")
	public String updateClase(@PathVariable("cedula") int cedula, @Valid Instructor ins, BindingResult result, Model model, @RequestParam("file") MultipartFile file, @RequestParam("cambioUrl") boolean cambioUrl) {
		if (result.hasErrors()) {
			ins.setCedula(cedula);
			return "update-instructor";
		}
		if (cambioUrl) { 
			try {
	            Map uploadResult = cloudc.upload(file.getBytes(), ObjectUtils.asMap("resourcetype", "auto"));
	            System.out.println(uploadResult.get("url").toString());
	            ins.setFoto(uploadResult.get("url").toString());	
	        } catch (Exception e) {
	        	System.out.println(e.getMessage());
	        }
		}
		insRepo.save(ins);
		model.addAttribute("instructores", insRepo.findAll());
		return "listarInstructor";
	}

	@GetMapping("/deleteInstructor/{cedula}")
	    public String deleteUser(@PathVariable("cedula") int cedula, Model model) {
		Instructor ins = insRepo.findById(cedula).orElseThrow(() -> new IllegalArgumentException("Invalid instructor cedula:" + cedula));
	        insRepo.delete(ins);
	        model.addAttribute("instructores", insRepo.findAll());
	        return "listarInstructor";
	    }
	
	@GetMapping("/listarInstructor")
    public String ListarCedula(Model model) {
    	model.addAttribute("instructores",insRepo.findAll());
        return "listarInstructor";
    }
	
	
	
	//listar clases para 
	@GetMapping("/instructor/{cedula}")
	public String listarInstructor(@PathVariable ("cedula") String cedula, Model model) throws Exception {
		Optional<Instructor> instructor = insRepo.findById(Integer.parseInt(cedula));
		if (!instructor.isPresent()) {
			throw new Exception("No existe el instructor con cedula:" + instructor);
		}
		model.addAttribute("instructores", instructor.get());
		return "listarInstructorParaInstructor";
	}
	
	
}
