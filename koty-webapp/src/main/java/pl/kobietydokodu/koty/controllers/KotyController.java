package pl.kobietydokodu.koty.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import pl.kobietydokodu.koty.KotDAO;
import pl.kobietydokodu.koty.domain.Kot;
import pl.kobietydokodu.koty.dto.KotDTO;

@Controller
public class KotyController {

	@Autowired
	private KotDAO kotDao;
	
	@RequestMapping("/lista")
	public String listaKotow(Model model) {
		model.addAttribute("koty", kotDao.getKoty());
		return "lista";
	}
	
	@RequestMapping(value="/dodaj", method=RequestMethod.GET)
	public String dodajKotaFormularz(@ModelAttribute("kotDto") @Valid KotDTO kotDto, BindingResult result) {
		return "dodaj";
	}
	
	@RequestMapping(value="/dodaj", method=RequestMethod.POST)
	public String dodajKota(@ModelAttribute("kotDto") @Valid KotDTO kotDto, BindingResult result) {
		if (!result.hasErrors()) {
			//form filled correctly
			Kot kot = new Kot();
			SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
			
			try {
				kot.setDataUrodzenia(sdf.parse(kotDto.getDataUrodzenia()));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			
			kot.setImie(kotDto.getImie());
			kot.setImieOpiekuna(kotDto.getImieOpiekuna());
			kot.setWaga(kotDto.getWaga());
			kotDao.dodajKota(kot);
			
			return "redirect:/lista";
		} 
			
		//form filled incorrectly
		return "dodaj";			

	}
	
	
	@RequestMapping("/kot-{id}")
	public String szczegolyKota(@PathVariable("id") Integer id, Model model) {
		model.addAttribute("kot", kotDao.getKotById(id));
		return "szczegoly";
	}
	

}
