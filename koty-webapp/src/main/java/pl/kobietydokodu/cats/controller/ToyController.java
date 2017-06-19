package pl.kobietydokodu.cats.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import pl.kobietydokodu.cats.dao.CatDAO;
import pl.kobietydokodu.cats.dao.ToyDAO;
import pl.kobietydokodu.cats.domain.Toy;
import pl.kobietydokodu.cats.dto.ToyDTO;

@Controller
public class ToyController {

	@Autowired
	private CatDAO catDao;
	@Autowired
	private ToyDAO toyDao;
	
	/**
	 * This method displays the form to add new toy.
	 */
	@Secured("ROLE_USER")
	@RequestMapping(value="/cat-{id}/toy/add", method=RequestMethod.GET)
	public String addToyForm(@ModelAttribute("toyDto") @Valid ToyDTO toyDto, BindingResult result, @PathVariable("id") Long id, Model model) {
		model.addAttribute("cat", catDao.findById(id));
		return "addToy";
	}
	
	@Secured("ROLE_USER")
	@RequestMapping(value="/cat-{id}/toy/add", method=RequestMethod.POST)
	public String addToy(@ModelAttribute("toyDto") @Valid ToyDTO toyDto, BindingResult result, @PathVariable("id") Long id, Model model) {
		model.addAttribute("cat", catDao.findById(id));
		
		if (!result.hasErrors()) {
			//form filled correctly
			Toy toy = new Toy();
			SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
			
			try {
				toy.setProductionDate(sdf.parse(toyDto.getProductionDate()));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			
			toy.setName(toyDto.getName());
			toy.setWeight(toyDto.getWeight());
			toy.setKitten(catDao.findById(id));

			toyDao.save(toy);
			
			return "redirect:/cat-{id}";
		} 
			
		//form filled incorrectly
		return "addToy";			

	}
	
	/**
	 * This method displays details of given toy.
	 */
	@Secured("ROLE_USER")
	@RequestMapping("/cat-{id1}/toy/toy-{id2}")
	public String toyDetails(@PathVariable("id1") Long id1, @PathVariable("id2") Long id2, Model model) {
		model.addAttribute("cat", catDao.findById(id1));
		model.addAttribute("toy", toyDao.findById(id2));
		return "toyDetails";
	}
	
	/**
	 * This method delete specific toy.
	 */
	@Secured("ROLE_USER")
	@RequestMapping("/cat-{id1}/toy/delete-{id2}")
	public String deleteToy(@PathVariable("id1") Long id1, @PathVariable("id2") Long id2) {
		Toy toy = toyDao.findById(id2);
		toyDao.delete(toy);
		
		return "redirect:/cat-{id1}";
	}

}
