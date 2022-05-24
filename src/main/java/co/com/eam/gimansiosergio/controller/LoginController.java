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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.utils.ObjectUtils;

import co.com.eam.gimansiosergio.CloudinaryConfig;
import co.com.eam.gimansiosergio.domain.Datosbancario;
import co.com.eam.gimansiosergio.domain.Socio;
import co.com.eam.gimansiosergio.repository.SocioRepository;

@Controller
public class LoginController {
	
	@Autowired
	private  SocioRepository socioRepo;
	

	 @GetMapping("/addSocioLogin")
	    public String showSignUpForm(Socio socio,Model model) {
	        return "add-socioLogin";
	    }
	


}
