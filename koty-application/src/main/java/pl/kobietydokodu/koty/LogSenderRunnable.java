package pl.kobietydokodu.koty;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import pl.kobietydokodu.koty.domain.Kot;

@Component
public class LogSenderRunnable implements Runnable {

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	public InterfejsDAO kotDao;
	
	@Override
	public void run() {
		List<Kot> koty = kotDao.findAll();
		if (koty.size()==1) {
			logger.info("There is 1 cat in the database.");
		} else if (koty.size()>1) {
			logger.info("There are " + koty.size() + " cats in the database.");
		} else {
			logger.info("There is no cat in the database.");
		}

	}

}
