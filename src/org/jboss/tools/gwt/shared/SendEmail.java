package org.jboss.tools.gwt.shared;

import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendEmail {
	Boolean sent = false;
	Logger logger = Logger.getLogger("logger");
	
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
			System.out.println(""+client.getNote());
			String messageBodyText = "<html>";
			    messageBodyText = (new StringBuilder(String.valueOf(messageBodyText))).append("<head></head>").toString();
		        messageBodyText = (new StringBuilder(String.valueOf(messageBodyText))).append("<body>").toString();
		        messageBodyText = (new StringBuilder(String.valueOf(messageBodyText))).append("<br/>").toString();
		        messageBodyText = (new StringBuilder(String.valueOf(messageBodyText))).append(client.getNote()).toString();
		        messageBodyText = (new StringBuilder(String.valueOf(messageBodyText))).append("</body>").toString();
		        messageBodyText = (new StringBuilder(String.valueOf(messageBodyText))).append("</html>").toString();
			message.setContent(messageBodyText, "text/html");
			Transport.send(message);
 
			logger.log(Level.SEVERE,
					" sent a mail out ");
			sent = true;
 
		} catch (MessagingException e) {
			logger.log(Level.SEVERE,
					"Exception when sending a mail out " +e.toString());
			return sent;
		}
		return sent;
	}
}
