package pl.kobietydokodu.koty.webservice;

import java.sql.Date;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import pl.kobietydokodu.koty.domain.Kot;

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
		ResponseEntity kot = restTemplate.getForEntity("http://localhost:8080/koty-webapp/rest/koty/"+id.toString(), Kot.class, Collections.EMPTY_MAP);
		logger.info(kot.toString());
	}
	
	public static void get() {
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity koty = restTemplate.getForEntity("http://localhost:8080/koty-webapp/rest/koty", List.class, Collections.EMPTY_MAP);
		//List<Kot> koty = restTemplate.getForObject("http://localhost:8080/koty-webapp/rest/koty", List.class);
		logger.info(koty.toString());
	}
	
	private static void post(Long id) {
		RestTemplate restTemplate = new RestTemplate();
		
		Kot kotek = new Kot();
		
		kotek.setImie("KociczekPostowy");
		kotek.setDataUrodzenia(Date.valueOf("2011-11-03"));
		kotek.setWaga(66.0F);
		kotek.setImieOpiekuna("Kocurek");
		
		ResponseEntity kot = restTemplate.postForEntity("http://localhost:8080/koty-webapp/rest/koty/"+id.toString(), kotek, Kot.class, Collections.EMPTY_MAP);
		logger.info(kot.toString());
	}
	
	private static void put(Long id) {
		RestTemplate restTemplate = new RestTemplate();
		
		Kot kotek = new Kot();
		
		kotek.setImie("KociczekPutowy");
		kotek.setDataUrodzenia(Date.valueOf("2011-11-03"));
		kotek.setWaga(69.0F);
		kotek.setImieOpiekuna("Kocurek");

		restTemplate.put("http://localhost:8080/koty-webapp/rest/koty/"+id.toString(), kotek, Collections.EMPTY_MAP);
		logger.info(kotek.toString());
	}
	
	public static void delete(Long id) {
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.delete("http://localhost:8080/koty-webapp/rest/koty/"+id.toString(), Collections.EMPTY_MAP);
		logger.info("Deleted cat with id={}", id);
	}
}
