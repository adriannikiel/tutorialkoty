package pl.kobietydokodu.cats.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import pl.kobietydokodu.cats.dao.CatDAO;
import pl.kobietydokodu.cats.domain.Cat;

@Controller
@RequestMapping("/rest/koty")
public class RestController {

	@Autowired
	private CatDAO catDao;

	/**
	 * This method displays specific cat from database.
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Cat> get(@PathVariable("id") Long id) {
		Cat cat = catDao.findById(id);

		if (cat == null) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<Cat>(cat, new HttpHeaders(), HttpStatus.FOUND);
		}
	}

	/**
	 * This method displays all cats from database.
	 */
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Cat>> get() {
		List<Cat> cats = catDao.findAll();
		return new ResponseEntity<List<Cat>>(cats, new HttpHeaders(), HttpStatus.OK);

	}

	/**
	 * This method add new cat to database.
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.POST)
	public ResponseEntity add(@PathVariable("id") Long id, @RequestBody @Valid Cat kitten) {

		Cat cat = catDao.findById(id);

		if (cat == null) {
			cat = new Cat();

			cat.setId(id);
			cat.setName(kitten.getName());
			cat.setGuardianName(kitten.getGuardianName());
			cat.setBirthday(kitten.getBirthday());
			// cat.setPhoto(kitten.getPhoto());
			cat.setWeight(kitten.getWeight());
			// cat.setToys(kotek.getToys());

			cat = catDao.save(cat);
			return new ResponseEntity(cat, new HttpHeaders(), HttpStatus.CREATED);
		} else {
			return new ResponseEntity("Cat already exist!", HttpStatus.BAD_REQUEST);
		}

	}
	
	/**
	 * This method edit a cat.
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity edit(@PathVariable("id") Long id, @RequestBody @Valid Cat kitten) {

		Cat cat = catDao.findById(id);

		if (cat != null) {

			cat.setId(id);
			cat.setName(kitten.getName());
			cat.setGuardianName(kitten.getGuardianName());
			cat.setBirthday(kitten.getBirthday());
			// cat.setPhoto(kotek.getPhoto());
			cat.setWeight(kitten.getWeight());
			// cat.setToys(kotek.getToys());

			cat = catDao.save(cat);
			return new ResponseEntity(cat, new HttpHeaders(), HttpStatus.OK);
		} else {
			return new ResponseEntity("Cat not found!", HttpStatus.BAD_REQUEST);
		}
	}

	/**
	 * This method delete a cat.
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity delete(@PathVariable("id") Long id) {

		Cat cat = catDao.findById(id);

		if (cat != null) {
			catDao.delete(cat);
			return new ResponseEntity<Cat>(cat, new HttpHeaders(), HttpStatus.OK);
		} else {
			return new ResponseEntity("Cat not found!", HttpStatus.BAD_REQUEST);
		}
	}

}
