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

import pl.kobietydokodu.koty.InterfejsDAO;
import pl.kobietydokodu.koty.ZabawkaDAO;
import pl.kobietydokodu.koty.domain.Zabawka;
import pl.kobietydokodu.koty.dto.ZabawkaDTO;

@Controller
public class ZabawkiController {

	@Autowired
	private InterfejsDAO kotDao;
	@Autowired
	private ZabawkaDAO zabawkaDao;
		
	@RequestMapping(value="/kot-{id}/zabawka/dodaj", method=RequestMethod.GET)
	public String dodajZabawkeFormularz(@ModelAttribute("zabawkaDto") @Valid ZabawkaDTO zabawkaDto, BindingResult result, @PathVariable("id") Long id, Model model) {
		model.addAttribute("kot", kotDao.findById(id));
		return "dodajZabawke";
	}
	
	@RequestMapping(value="/kot-{id}/zabawka/dodaj", method=RequestMethod.POST)
	public String dodajZabawke(@ModelAttribute("zabawkaDto") @Valid ZabawkaDTO zabawkaDto, BindingResult result, @PathVariable("id") Long id, Model model) {
		model.addAttribute("kot", kotDao.findById(id));
		
		if (!result.hasErrors()) {
			//form filled correctly
			Zabawka zabawka = new Zabawka();
			SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
			
			try {
				zabawka.setDataProdukcji(sdf.parse(zabawkaDto.getDataProdukcji()));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			
			zabawka.setNazwa(zabawkaDto.getNazwa());
			zabawka.setWaga(zabawkaDto.getWaga());
			zabawka.setKotek(kotDao.findById(id));

			zabawkaDao.save(zabawka);
			
			return "redirect:/kot-{id}";
		} 
			
		//form filled incorrectly
		return "dodajZabawke";			

	}
	
	@RequestMapping("/kot-{id1}/zabawka/zabawka-{id2}")
	public String szczegolyZabawki(@PathVariable("id1") Long id1, @PathVariable("id2") Long id2, Model model) {
		model.addAttribute("kot", kotDao.findById(id1));
		model.addAttribute("zabawka", zabawkaDao.findById(id2));
		return "szczegolyZabawki";
	}
	
	@RequestMapping("/kot-{id1}/zabawka/usun-{id2}")
	public String usunZabawke(@PathVariable("id1") Long id1, @PathVariable("id2") Long id2) {
		Zabawka zabawka = zabawkaDao.findById(id2);
		zabawkaDao.delete(zabawka);
		
		return "redirect:/kot-{id1}";
	}

}
