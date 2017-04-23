package pl.kobietydokodu.koty.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import pl.kobietydokodu.koty.KotDAO;

@Controller
public class KotyController {

	@Autowired
	private KotDAO kotDao;
	
	@RequestMapping("/lista")
	public String listaKotow(Model model) {
		model.addAttribute("koty", kotDao.getKoty());
		return "lista";
	}
	
	@RequestMapping("/dodaj")
	public String dodajKota() {
		return "dodaj";
	}
	
	@RequestMapping("/kot-{id}")
	public String szczegolyKota(@PathVariable("id") Integer id, Model model) {
		model.addAttribute("kot", kotDao.getKotById(id));
		return "szczegoly";
	}
	

}
