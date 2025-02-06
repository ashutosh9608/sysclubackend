package in.sp.main.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
	@Autowired
	private JavaMailSender mailSender;

	public void sendEmail(String to, String subject, String body) {
		try {
			SimpleMailMessage message = new SimpleMailMessage();
			message.setTo(to);
			message.setSubject(subject);
			message.setText(body);
			mailSender.send(message);
			System.out.println("Email sent successfully to: " + to);
		} catch (MailException ex) {
			// Log the exception
			System.err.println("Failed to send email to: " + to);
			System.err.println("Error: " + ex.getMessage());

			// Throw a custom exception or handle it as needed
			throw new RuntimeException("Unable to send email. Please try again later.");
		}
	}
}
