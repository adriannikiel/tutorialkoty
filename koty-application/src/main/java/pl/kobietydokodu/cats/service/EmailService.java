package pl.kobietydokodu.cats.service;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.stereotype.Service;

/**
 * Simple service to send e-mails
 * @see <a href="https://myaccount.google.com/lesssecureapps?pli=1">Gmail account settings to turn off additional security</a>
 */
@Service
public class EmailService {
	protected String mailSmtpAuth = "true";
	protected String mailSmtpHost = "smtp.gmail.com";
	protected String mailSmtpPort = "587";
	protected String mailSmtpStarttlsEnable = "true";
	protected String mailEmailFrom = "adiczek.koks@gmail.com";
	protected String username = "adiczek.koks@gmail.com";
	protected String password = "ovdR2fELet";

	/**
	 * Method which send e-mail
	 * @param recipientEmail Recipient of the e-mail
	 * @param subject Subject of the e-mail
	 * @param content Content of the e-mail
	 * @return Returns true when message has been sent otherwise returns false
	 */
	public boolean sendEmail(String recipientEmail, String subject, String content) {

		Properties props = new Properties();
		props.put("mail.smtp.auth", mailSmtpAuth);
		props.put("mail.smtp.starttls.enable", mailSmtpStarttlsEnable);
		props.put("mail.smtp.host", mailSmtpHost);
		props.put("mail.smtp.port", mailSmtpPort);
		props.put("mail.smtp.ssl.trust", mailSmtpHost);

		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(mailEmailFrom));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail));
			message.setSubject(subject);
			message.setText(content);

			Transport.send(message);
			return true;
		} catch (MessagingException e) {
			// TODO
		}

		return false;
	}
}
