package org.jboss.tools.gwt.shared;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SendEmail {

    @Autowired
    private UserControllerInterface userController;

	Boolean sent = false;
	Logger logger = Logger.getLogger("logger");
	private JavaMailSender mailSender;
	private SimpleMailMessage simpleMailMessage;
	Boolean filesSent = false;
	Boolean endDateStatus = false;

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
			//UserController userController = new UserController();

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

    String disclamerMessage = "Please do not reply to this message. Replies to this message are routed to an unmonitored mailbox. If you have any questions, please contact Telos Risk Management & Insurance Broking Services (P) Ltd at teloshyd@gmail.com .";
	public Boolean emailSent(Clients client, List<DocumentOnServerSide> files) {

		//UserController userController = new UserController();
		Boolean response = false;
		String beginingMessage = "At your request we have negotiated with the insurer and obtained policy no. ";
		String endingMessage = " with best price, terms & conditions and same is attached and hard copy being sent.";
		String conclusionMesage = "Please verify and let us know your feedback at teloshyd@gmail.com .";
		String completeMessage = beginingMessage+client.getPolicyNumber()+endingMessage;
		//String disclamerMessage = "Please do not reply to this message. Replies to this message are routed to an unmonitored mailbox. If you have any questions, please contact Telos Risk Management & Insurance Broking Services (P) Ltd at teloshyd@gmail.com .";
		try {
			MimeMessage message = mailSender.createMimeMessage();

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
					String.valueOf(messageBodyText))).append("Dear Customer,")
					.toString();
			messageBodyText = (new StringBuilder(
					String.valueOf(messageBodyText))).append("<br/>")
					.toString();
			messageBodyText = (new StringBuilder(
					String.valueOf(messageBodyText))).append("<br/>")
					.toString();
			messageBodyText = (new StringBuilder(
					String.valueOf(messageBodyText))).append(completeMessage)
					.toString();
			messageBodyText = (new StringBuilder(
					String.valueOf(messageBodyText))).append("<br/>")
					.toString();
			messageBodyText = (new StringBuilder(
					String.valueOf(messageBodyText))).append("<br/>")
					.toString();
			messageBodyText = (new StringBuilder(
					String.valueOf(messageBodyText))).append(conclusionMesage)
					.toString();
			messageBodyText = (new StringBuilder(
					String.valueOf(messageBodyText))).append("<br/>")
					.toString();
			messageBodyText = (new StringBuilder(
					String.valueOf(messageBodyText))).append("<br/>")
					.toString();
			messageBodyText = (new StringBuilder(
					String.valueOf(messageBodyText))).append("<br/>")
					.toString();
			messageBodyText = (new StringBuilder(
					String.valueOf(messageBodyText))).append("<br/>")
					.toString();
			messageBodyText = (new StringBuilder(
					String.valueOf(messageBodyText))).append("<footer font-size:08px;font-style:italic;>")
					.toString();
			messageBodyText = (new StringBuilder(
					String.valueOf(messageBodyText))).append(disclamerMessage)
					.toString();
			messageBodyText = (new StringBuilder(
					String.valueOf(messageBodyText))).append("</footer>")
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
			endDateStatus = userController.endDate(files);
			if (email != null && filesSent != true) {
				sent = true;
                logger.log(Level.SEVERE,
                        "End dateed the files mailed");
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
		response = userController.sendSMSToClient(client,"DOCUMENTS");
		if(response)
		{
			
			return sent;
		}
		else 
			return false;
	}
	
	public Boolean sentEmailBySchedule(Map<String, java.io.File> files)
	{
		try{
			
		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, true);
		helper.setFrom("teloshyd@connect2telos.com");
		helper.setTo("teloshyd@gmail.com");
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
				String.valueOf(messageBodyText))).append("Attached is the list of pending policies, missing Email ID's and missing phone number's in one excel document, needed to be updated in the system of record immedietly.")
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

    public Boolean sentEmailByScheduleForRenewals(Clients client)
    {
        try{
            String clientName = null;
            StringBuffer clientNameBuffer= new StringBuffer(client.getName());
            if(clientNameBuffer.substring(0,4).equalsIgnoreCase("M/S.") )
            {
                clientNameBuffer.replace(0,4,"");
                clientName =clientNameBuffer.toString();
            }
            else
            {
                clientName = client.getName();
            }
            String MESSAGE1 = "We wish to inform you that the Policy No.";
            String MESSAGE2 = " stands in the name of ";
            String MESSAGE3 = " is expiring on ";
            String MESSAGE4 = ". Kindly approach us for effecting renewal without break.";
            String MESSAGE5 ="For further information please contact us on our land line no.04066776677 and 04023416770 or E-mail us on teloshyd@gmail.com";
            String MESSAGE6 ="With regards,<br/>" +
                    "<br/>" +
                    "M. Narasimha Rao & M.R.G. Raju<br/>" +
                    "Telos Risk Management and Insurance Broking Services (P) Limited,<br/>" +
                    "2nd floor, Status Plaza, Opp.: Greenlands Guest House, Begumpet,<br/>" +
                    "Hyderabad - 500016.  Tel- 040-66776677  TeleFax-040-23416770 mobile 9848021211<br/>" +
                    "Website: www.telosrisk.com";
            String mainMessage = MESSAGE1+client.getPolicyNumber()+MESSAGE2+clientName+MESSAGE3+client.getPolicyEndDate()+MESSAGE4;
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom("teloshyd@connect2telos.com");
            helper.setTo(client.getEmail());
            helper.setSubject("Renewal of Policy -TELOS");
            String messageBodyText = "<html>";
            messageBodyText = (new StringBuilder(
                    String.valueOf(messageBodyText))).append("<head>Policy Renewal.</head>")
                    .toString();
            messageBodyText = (new StringBuilder(
                    String.valueOf(messageBodyText))).append("<body>")
                    .toString();
            messageBodyText = (new StringBuilder(
                    String.valueOf(messageBodyText))).append("<br/>")
                    .toString();
            messageBodyText = (new StringBuilder(
                    String.valueOf(messageBodyText))).append(mainMessage)
                    .toString();
            messageBodyText = (new StringBuilder(
                    String.valueOf(messageBodyText))).append("<br/>")
                    .toString();
            messageBodyText = (new StringBuilder(
                    String.valueOf(messageBodyText))).append("<br/>")
                    .toString();
            messageBodyText = (new StringBuilder(
                    String.valueOf(messageBodyText))).append(MESSAGE5)
                    .toString();
            messageBodyText = (new StringBuilder(
                    String.valueOf(messageBodyText))).append("<br/>")
                    .toString();
            messageBodyText = (new StringBuilder(
                    String.valueOf(messageBodyText))).append("<br/>")
                    .toString();
            messageBodyText = (new StringBuilder(
                    String.valueOf(messageBodyText))).append(disclamerMessage)
                    .toString();
            messageBodyText = (new StringBuilder(
                    String.valueOf(messageBodyText))).append("<br/>")
                    .toString();
            messageBodyText = (new StringBuilder(
                    String.valueOf(messageBodyText))).append("<br/>")
                    .toString();
            messageBodyText = (new StringBuilder(
                    String.valueOf(messageBodyText))).append(MESSAGE6)
                    .toString();
            messageBodyText = (new StringBuilder(
                    String.valueOf(messageBodyText))).append("</body>")
                    .toString();
            messageBodyText = (new StringBuilder(
                    String.valueOf(messageBodyText))).append("</html>")
                    .toString();
            helper.setText(messageBodyText,true);
            mailSender.send(message);
        }
        catch (Exception e) {

            e.printStackTrace();
        }
        return true;

    }
}
