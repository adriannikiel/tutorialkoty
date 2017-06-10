package pl.kobietydokodu.koty.service;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.Lifecycle;
import org.springframework.stereotype.Service;

import pl.kobietydokodu.koty.LogSenderRunnable;

@Service
public class LogService implements Lifecycle {

	private ScheduledExecutorService threadPool = Executors.newScheduledThreadPool(1);
	
	@Autowired
    LogSenderRunnable loglSenderRunnable;
	
	@PostConstruct 
    public void setup() {
        threadPool.scheduleAtFixedRate(loglSenderRunnable, 60, 60, TimeUnit.SECONDS);
    }
	
	@Override
	public void start() {}

	@Override
	public void stop() { 
		threadPool.shutdown();
		while (isRunning()) {
			try {
				Thread.sleep(300);
			} catch(InterruptedException e) {
				//TODO
			}
		}
	}

	@Override
	public boolean isRunning() {
		return threadPool.isTerminated();
	}

}
