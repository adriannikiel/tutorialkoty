package kobietydokodu.koty.test;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import junit.framework.Assert;
import pl.kobietydokodu.koty.service.EmailService;

@SuppressWarnings("deprecation")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext-test.xml")
public class EmailServiceTest {

	@Autowired
	private EmailService emailService;
	
	@Test
	public void testujEmailService() {
						
		String recipientEmail = "enjoy@buziaczek.pl";
		String subject = "Testowanie kobietydokodu";
		String content = "Testy pomyslne";

		boolean isMailSent = emailService.sendEmail(recipientEmail, subject, content);
		
		Assert.assertEquals("Nie wyslano maila", true, isMailSent);
	}
	
}
