package pl.kobietydokodu.cats.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import pl.kobietydokodu.cats.dao.UserDAO;
import pl.kobietydokodu.cats.domain.User;
import pl.kobietydokodu.cats.dto.RegistrationDTO;
import pl.kobietydokodu.cats.service.EmailService;

@Controller
public class RegistrationController {

	@Autowired
	private UserDAO userDAO;

	@Autowired
	private EmailService emailService;

	/**
	 * This method displays login page. If there are errors during login process
	 * or user logout from webpage then special message appears.
	 */
	@RequestMapping(value = { "/login" }, method = RequestMethod.GET)
	public String loginPage(@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout, Model model) {

		if (error != null) {
			model.addAttribute("error", "Invalid username and password!");
		}

		if (logout != null) {
			model.addAttribute("msg", "You've been logged out successfully.");
		}

		return "login";
	}

	/**
	 * This method displays the form to register new user.
	 */
	@RequestMapping(value = "/registration", method = RequestMethod.GET)
	public String loginForm(@ModelAttribute("registrationDto") @Valid RegistrationDTO registrationDto,
			BindingResult result) {
		return "registration";
	}

	@RequestMapping(value = "/registration", method = RequestMethod.POST)
	public String login(@ModelAttribute("rejestracjaDto") @Valid RegistrationDTO registrationDto,
			BindingResult result) {
		if (!result.hasErrors()) {

			User user = new User();

			user.setUsername(registrationDto.getUser());
			user.setEmail(registrationDto.getEmail());
			user.setPassword(registrationDto.getPass());

			userDAO.save(user);

			String mail = user.getEmail();
			String content = "Witam, utworzono u¿ytkownika " + user.getUsername();

			emailService.sendEmail(mail, "Pomyœlna rejestracja kobietydokodu", content);

			return "redirect:/login";
		}
		return "registration";
	}

}
