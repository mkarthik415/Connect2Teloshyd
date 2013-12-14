package org.jboss.tools.gwt.shared;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.apache.commons.io.IOUtils;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

public class SendEmail {

	Boolean sent = false;
	Logger logger = Logger.getLogger("logger");
	private JavaMailSender mailSender;
	private SimpleMailMessage simpleMailMessage;
	Boolean filesSent = false;

	public void setMailSender(JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}

	public Boolean emailSent(Client client, List<DocumentOnServerSide> files) {

		MimeMessage message = mailSender.createMimeMessage();

		try {

			String messageBodyText = "<html>";
			messageBodyText = (new StringBuilder(
					String.valueOf(messageBodyText))).append("<head></head>")
					.toString();
			messageBodyText = (new StringBuilder(
					String.valueOf(messageBodyText))).append("<body>")
					.toString();
			messageBodyText = (new StringBuilder(
					String.valueOf(messageBodyText))).append("<br/>")
					.toString();
			messageBodyText = (new StringBuilder(
					String.valueOf(messageBodyText))).append(client.getNote())
					.toString();
			messageBodyText = (new StringBuilder(
					String.valueOf(messageBodyText))).append("</body>")
					.toString();
			messageBodyText = (new StringBuilder(
					String.valueOf(messageBodyText))).append("</html>")
					.toString();

			logger.log(Level.SEVERE, " sent a mail out ");
			sent = false;

			Email email = new Email();
			UserController userController = new UserController();

			email.setClientiD(client.getId());
			email.setAddress(client.getEmail());
			email.setMessage(messageBodyText);

			MimeMessageHelper helper = new MimeMessageHelper(message, true);
			helper.setFrom("teloshyd@connect2telos.com");
			helper.setTo(client.getEmail());
			helper.setSubject("Documents");
			helper.setText(messageBodyText,true);
			for (DocumentOnServerSide file : files) {
				InputStream in = file.getScanned().getBinaryStream();
				helper.addAttachment(file.getName(), new ByteArrayResource(
						IOUtils.toByteArray(in)));
			}
			mailSender.send(message);
			email = userController.logEmail(email);
			filesSent = userController.logEmailedFiles(email, files);
			if (email != null && filesSent != true) {
				sent = true;
			}

		} catch (MessagingException e) {
			logger.log(Level.SEVERE,
					"Exception when sending a mail out " + e.toString());
			return sent;
		} catch (SQLException e) {
			e.printStackTrace();
			return sent;
		} catch (IOException e) {
			e.printStackTrace();
			return sent;
		}
		return sent;
	}
	
	public Boolean sentEmailBySchedule(Map<String, java.io.File> files)
	{
		try{
			
		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, true);
		helper.setFrom("teloshyd@connect2telos.com");
		helper.setTo("mkarthik415@gmail.com");
		helper.setSubject("Daily Pending,Email ID's and Phone Number's Report");
		String messageBodyText = "<html>";
		messageBodyText = (new StringBuilder(
				String.valueOf(messageBodyText))).append("<head>Pending Policies, Missing Email ID's and Missing Phone Numbers</head>")
				.toString();
		messageBodyText = (new StringBuilder(
				String.valueOf(messageBodyText))).append("<body>")
				.toString();
		messageBodyText = (new StringBuilder(
				String.valueOf(messageBodyText))).append("<br/>")
				.toString();
		messageBodyText = (new StringBuilder(
				String.valueOf(messageBodyText))).append("Attached is the list of pending policies, missing Email ID's and missing phone number's, needed to be updated in the system of record immedietly.")
				.toString();
		messageBodyText = (new StringBuilder(
				String.valueOf(messageBodyText))).append("</body>")
				.toString();
		messageBodyText = (new StringBuilder(
				String.valueOf(messageBodyText))).append("</html>")
				.toString();
		helper.setText(messageBodyText,true);
		
		Set<String> pathOfFiles = files.keySet();
		
		for(String pathOfFile : pathOfFiles)
		{
			FileInputStream fis = new FileInputStream(pathOfFile);
			helper.addAttachment(files.get(pathOfFile).getName()+".xls", new ByteArrayResource(
					IOUtils.toByteArray(fis)));
			
		}
		mailSender.send(message);
		}
		 catch (Exception e) {
				
				e.printStackTrace();
			}
		return filesSent;
		
	}
}
