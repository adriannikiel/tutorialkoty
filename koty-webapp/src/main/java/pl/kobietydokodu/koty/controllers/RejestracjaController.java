package pl.kobietydokodu.koty.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import pl.kobietydokodu.koty.UzytkownikDAO;
import pl.kobietydokodu.koty.domain.Uzytkownik;
import pl.kobietydokodu.koty.dto.RejestracjaDTO;

@Controller
public class RejestracjaController {
	
	@Autowired
	private UzytkownikDAO uzytkownikDAO;
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	public String stronaLogowowania(HttpServletRequest request) {
		
		if (request.getUserPrincipal() == null) {
			return "redirect:/spring_security_login";
		} else {
			return "redirect:/lista";
		}
	}
	
	@RequestMapping(value="/rejestracja", method=RequestMethod.GET)
	public String logujFormularz(@ModelAttribute("rejestracjaDto") @Valid RejestracjaDTO rejestracjaDto, BindingResult result) {
		return "rejestracja";
	}
	
	@RequestMapping(value="/rejestracja", method=RequestMethod.POST)
	public String loguj(@ModelAttribute("rejestracjaDto") @Valid RejestracjaDTO rejestracjaDto, BindingResult result) {
		if (!result.hasErrors()) {
			
			Uzytkownik uzytkownik = new Uzytkownik();
			
			uzytkownik.setUsername(rejestracjaDto.getUser());
			uzytkownik.setPassword(rejestracjaDto.getPass());
			
			uzytkownikDAO.save(uzytkownik);
			
			return "redirect:/lista";
		} 
		return "rejestracja";			
	}


}
