package pl.kobietydokodu.cats.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import pl.kobietydokodu.cats.dao.CatDAO;
import pl.kobietydokodu.cats.domain.Cat;

@Component
public class LogSenderRunnable implements Runnable {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private CatDAO catDao;

	@Override
	public void run() {
		List<Cat> cats = catDao.findAll();
		if (cats.size() == 1) {
			logger.info("There is 1 cat in the database.");
		} else if (cats.size() > 1) {
			logger.info("There are " + cats.size() + " cats in the database.");
		} else {
			logger.info("There is no cat in the database.");
		}

	}

}
