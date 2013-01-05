package org.jboss.tools.gwt.shared;

import java.util.Properties;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendEmail {
	Boolean sent = false;
	
	public Boolean emailSent(Client client) 
	{
		final String username = "teloshyd@connect2telos.info";
		final String password = "hydtelos";
 
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "mail.connect2telos.info");
		props.put("mail.smtp.port", "26");

		Session session = Session.getInstance(props,
				  new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(username, password);
					}
				  });
		try {
			 
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(username));
			message.setRecipients(Message.RecipientType.TO,
				InternetAddress.parse(client.getEmail()));
			message.setSubject("Dispatch Details");
			message.setText("Dear "+client.getClientName()+", "
				+ "\n\n Your documents have been dispatched. Thank you for doing business with us.\n\n With Regards, \n Telos");
 
			Transport.send(message);
 
			System.out.println("Done");
			sent = true;
 
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
		return sent;
	}
}
