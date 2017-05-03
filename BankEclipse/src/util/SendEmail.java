/**
 *@Author: Lucien Boulet Roblin 
 *2 mai 2017
 */
package util;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import controllers.VistaNavigator;

public class SendEmail {

	/**
	 * don't work if avast is activated
	 */

	public static void SendEmails(String messageMail) {

		//String to = "lucien.bouletroblin@gmail.com";// testing
		String to = VistaNavigator.getInstance().getLoggedOwner().getEmail();// testing
		String from = "projetbank.master@gmail.com";
		String password = "projetbank"; // to hash and salt and store in DB
		String host = "smtp.gmail.com";// or IP address

		Properties mailProps = new Properties();
		mailProps.setProperty("mail.smtp.host", host);
		mailProps.put("mail.smtp.starttls.enable", "true");
		mailProps.setProperty("mail.smtp.auth", "true");
		final PasswordAuthentication usernamePassword = new PasswordAuthentication(from, password);
		Authenticator auth = new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return usernamePassword;
			}
		};
		Session session = Session.getInstance(mailProps, auth);
		


		// compose the message
		try {
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(from));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			message.setSubject("Bank Manager Admin team");
			message.setText(messageMail);

			// Send message
			Transport.send(message);
			System.out.println("message sent successfully....");

		} catch (MessagingException mex) {
			mex.printStackTrace();
		}
	}

}
