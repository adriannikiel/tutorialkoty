package pl.kobietydokodu.koty.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import pl.kobietydokodu.koty.UzytkownikDAO;
import pl.kobietydokodu.koty.domain.Uzytkownik;
import pl.kobietydokodu.koty.dto.RejestracjaDTO;

@Controller
public class RejestracjaController {
	
	@Autowired
	private UzytkownikDAO uzytkownikDAO;
	
	@RequestMapping(value={"/login"}, method=RequestMethod.GET)
	public String stronaLogowowania(
			@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout,
			Model model) {
		
		if (error != null) {
			model.addAttribute("error", "Invalid username and password!");
		}

		if (logout != null) {
			model.addAttribute("msg", "You've been logged out successfully.");
		}
		
		return "login";
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
			
			return "redirect:/login";
		} 
		return "rejestracja";			
	}


}
