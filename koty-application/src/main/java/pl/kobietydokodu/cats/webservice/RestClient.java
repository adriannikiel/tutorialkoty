package pl.kobietydokodu.cats.webservice;

import java.sql.Date;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import pl.kobietydokodu.cats.domain.Cat;

/**
 * Simple REST Client which tests standard CRUD operations on 'cats'
 */
public class RestClient {

	private static Logger logger = LoggerFactory.getLogger(RestClient.class);
	
	public static void main(String args[]) {

		get(Long.valueOf(args[0]));
		//get();
		//post(Long.valueOf(args[0]));
		//put(Long.valueOf(args[0]));
		//delete(Long.valueOf(args[0]));
	
	}
	public static void get(Long id) {
		RestTemplate restTemplate = new RestTemplate();
		//ResponseEntity cat = restTemplate.getForEntity("http://localhost:8080/koty-webapp/rest/koty/"+id.toString(), Cat.class, Collections.EMPTY_MAP);
		ResponseEntity cat = restTemplate.getForEntity("http://anikiel.pl/rest/koty/"+id.toString(), Cat.class, Collections.EMPTY_MAP);
		logger.info(cat.toString());
	}
	
	public static void get() {
		RestTemplate restTemplate = new RestTemplate();
		//ResponseEntity cats = restTemplate.getForEntity("http://localhost:8080/koty-webapp/rest/koty", List.class, Collections.EMPTY_MAP);
		ResponseEntity cats = restTemplate.getForEntity("http://anikiel.pl/rest/koty", List.class, Collections.EMPTY_MAP);
		//List<Cat> koty = restTemplate.getForObject("http://localhost:8080/koty-webapp/rest/koty", List.class);
		logger.info(cats.toString());
	}
	
	private static void post(Long id) {
		RestTemplate restTemplate = new RestTemplate();
		
		Cat kitten = new Cat();
		
		kitten.setName("RestPOSTkitten");
		kitten.setBirthday(Date.valueOf("2011-11-03"));
		kitten.setWeight(66.0F);
		kitten.setGuardianName("POSTKocurek");
		
		//ResponseEntity cat = restTemplate.postForEntity("http://localhost:8080/koty-webapp/rest/koty/"+id.toString(), kitten, Cat.class, Collections.EMPTY_MAP);
		ResponseEntity cat = restTemplate.postForEntity("http://anikiel.pl/rest/koty/"+id.toString(), kitten, Cat.class, Collections.EMPTY_MAP);
		logger.info(cat.toString());
	}
	
	private static void put(Long id) {
		RestTemplate restTemplate = new RestTemplate();
		
		Cat kitten = new Cat();
		
		kitten.setName("RestPUTkittenby");
		kitten.setBirthday(Date.valueOf("2016-05-11"));
		kitten.setWeight(69.0F);
		kitten.setGuardianName("PUTKocurek");

//		restTemplate.put("http://localhost:8080/koty-webapp/rest/koty/"+id.toString(), kitten, Collections.EMPTY_MAP);
		restTemplate.put("http://anikiel.pl/rest/koty/"+id.toString(), kitten, Collections.EMPTY_MAP);
		logger.info(kitten.toString());
	}
	
	public static void delete(Long id) {
		RestTemplate restTemplate = new RestTemplate();
//		restTemplate.delete("http://localhost:8080/koty-webapp/rest/koty/"+id.toString(), Collections.EMPTY_MAP);
		restTemplate.delete("http://anikiel.pl/rest/koty/"+id.toString(), Collections.EMPTY_MAP);
		logger.info("Deleted cat with id={}", id);
	}
}
