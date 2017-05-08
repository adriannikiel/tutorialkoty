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
//import pl.kobietydokodu.koty.HibernateKotDAO;
//import pl.kobietydokodu.koty.JDBCKotDAO;
//import pl.kobietydokodu.koty.KotDAO;
import pl.kobietydokodu.koty.domain.Kot;
import pl.kobietydokodu.koty.dto.KotDTO;

@Controller
public class KotyController {

	//@Autowired
	//private KotDAO kotDao;
	//@Autowired
	//private JDBCKotDAO kotDao;
	//@Autowired
	//private HibernateKotDAO kotDao;
	@Autowired
	private InterfejsDAO kotDao;
	@Autowired
	private ZabawkaDAO zabawkaDao;
	
	Long kotCount=0L;
	
	@RequestMapping("/lista")
	public String listaKotow(Model model) {
		model.addAttribute("koty", kotDao.findAll());
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
			
			kot.setId(kotCount);
			kot.setImie(kotDto.getImie());
			kot.setImieOpiekuna(kotDto.getImieOpiekuna());
			kot.setWaga(kotDto.getWaga());
			kotDao.save(kot);
			
			kotCount++;
			
			return "redirect:/lista";
		} 
			
		//form filled incorrectly
		return "dodaj";			

	}
	
	
	@RequestMapping("/kot-{id}")
	public String szczegolyKota(@PathVariable("id") Long id, Model model) {
		model.addAttribute("kot", kotDao.findById(id));
		model.addAttribute("zabawki", zabawkaDao.findByKotek_id(id));
		return "szczegoly";
	}
	

}
