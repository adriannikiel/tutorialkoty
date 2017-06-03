package pl.kobietydokodu.koty.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import pl.kobietydokodu.koty.InterfejsDAO;
import pl.kobietydokodu.koty.domain.Kot;
import pl.kobietydokodu.koty.domain.Zabawka;

@Controller
@RequestMapping("/rest/koty")
public class RestController {

	@Autowired
	private InterfejsDAO kotDao;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Kot> get(@PathVariable("id") Long id) {
		Kot kot = kotDao.findById(id);
		
		if (kot==null) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<Kot>(kot, new HttpHeaders(), HttpStatus.FOUND);
		}
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Kot>> get() {
		List<Kot> koty = kotDao.findAll();
		return new ResponseEntity<List<Kot>>(koty, new HttpHeaders(), HttpStatus.OK);
		
	}

	@RequestMapping(value = "/{id}", method=RequestMethod.POST)
    public ResponseEntity add(@PathVariable("id") Long id, @RequestBody @Valid Kot kotek) {
        
        Kot kot = kotDao.findById(id);
        
        if (kot==null) {
        	kot = new Kot();
        
	        kot.setId(id);
	        kot.setImie(kotek.getImie());
	        kot.setImieOpiekuna(kotek.getImieOpiekuna());
	        kot.setDataUrodzenia(kotek.getDataUrodzenia());
	        //kot.setFotka(kotek.getFotka());
	        kot.setWaga(kotek.getWaga());
	        //kot.setZabawki(kotek.getZabawki());

	        kot = kotDao.save(kot);
	        return new ResponseEntity(kot, new HttpHeaders(), HttpStatus.CREATED);
        } else {
        	return new ResponseEntity("Cat already exist!",HttpStatus.BAD_REQUEST);
        }
        
    }

	@RequestMapping(value = "/{id}", method=RequestMethod.PUT)
    public ResponseEntity edit(@PathVariable("id") Long id, @RequestBody @Valid Kot kotek) {
		
        Kot kot = kotDao.findById(id);
        
        if (kot!=null) {
	        
	        kot.setId(id);
	        kot.setImie(kotek.getImie());
	        kot.setImieOpiekuna(kotek.getImieOpiekuna());
	        kot.setDataUrodzenia(kotek.getDataUrodzenia());
	        //kot.setFotka(kotek.getFotka());
	        kot.setWaga(kotek.getWaga());
	        //kot.setZabawki(kotek.getZabawki());
	
	        kot = kotDao.save(kot);
	        return new ResponseEntity(kot, new HttpHeaders(), HttpStatus.OK);
        } else {
        	return new ResponseEntity("Cat not found!", HttpStatus.BAD_REQUEST);
        }
    }

	@RequestMapping(value = "/{id}", method=RequestMethod.DELETE)
    public ResponseEntity delete(@PathVariable("id") Long id) {
		
		Kot kot = kotDao.findById(id);
		
		if (kot!=null) {
			kotDao.delete(kot);
			return new ResponseEntity<Kot>(kot, new HttpHeaders(), HttpStatus.OK);
		} else {
			return new ResponseEntity("Cat not found!", HttpStatus.BAD_REQUEST);
		}
    }

}
