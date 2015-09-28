package org.jboss.tools.gwt.shared;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SendEmail implements SendEmailInterface{

    @Autowired
    private UserControllerInterface userController;

    @Autowired
    private JavaMailSenderImpl mailSender;

	Boolean sent = false;
	Logger logger = Logger.getLogger("logger");
	private SimpleMailMessage simpleMailMessage;
	Boolean filesSent = false;
	Boolean endDateStatus = false;
    SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");

	public Boolean emailSent(Client client, List<DocumentOnServerSide> files) {

		MimeMessage message = this.mailSender.createMimeMessage();

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
			helper.setFrom("do_not_reply@connect2telos.com");
			helper.setTo(client.getEmail());
			helper.setSubject("Documents(no reply accepted to this EMail ID)");
			helper.setText(messageBodyText,true);
			for (DocumentOnServerSide file : files) {
				if(! StringUtils.containsIgnoreCase(file.getName(),"MANDATE"))
				{

					InputStream in = file.getScanned().getBinaryStream();
					helper.addAttachment(file.getName(), new ByteArrayResource(
							IOUtils.toByteArray(in)));
				}
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


    public Boolean emailSentOnlyDocuments(Clients client, List<DocumentOnServerSide> files) {

        logger.log(Level.SEVERE, " @@@@@@ Inside the method to send emails @@@@@@ ");
        Boolean response = false;
		String beginingMessage = "At your request we have negotiated with the insurer and obtained policy no. ";
		String endingMessage = " with best price, terms & conditions and same is attached and hard copy being sent.";
		String conclusionMesage = "Please verify and let us know your feedback at teloshyd@gmail.com .";
		String completeMessage = beginingMessage+client.getPolicyNumber()+endingMessage;
		//String disclamerMessage = "Please do not reply to this message. Replies to this message are routed to an unmonitored mailbox. If you have any questions, please contact Telos Risk Management & Insurance Broking Services (P) Ltd at teloshyd@gmail.com .";
		try {
			MimeMessage message = this.mailSender.createMimeMessage();

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
			helper.setFrom("do_not_reply@connect2telos.com");
			helper.setTo(client.getEmail());
			helper.setSubject("Documents(no reply accepted to this EMail ID)");
			helper.setText(messageBodyText,true);
            if(files != null && files.size() >= 0 )
            {
                for (DocumentOnServerSide file : files) {
					if(! StringUtils.containsIgnoreCase(file.getName(),"MANDATE"))
					{
						InputStream in = file.getScanned().getBinaryStream();
						helper.addAttachment(file.getName(), new ByteArrayResource(
								IOUtils.toByteArray(in)));

					}

                }
            }
			mailSender.send(message);
			email = userController.logEmail(email);
			filesSent = userController.logEmailedFiles(email, files);
			endDateStatus = userController.endDate(files);
			if (email != null && filesSent != true) {
				sent = true;
                logger.log(Level.SEVERE,
                        "End dated the files mailed");
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
		}catch (Exception e) {
            e.printStackTrace();
            return sent;
        }
		response = userController.sendSMSToClient(client,"DOCUMENTS",null);
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
		helper.setFrom("do_not_reply@connect2telos.com");
		helper.setTo("teloshyd@gmail.com");
		helper.setSubject("Daily Pending,Email ID's and Phone Number's Report(no reply accepted to this EMail ID)");
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

    public Boolean sentEmailByScheduleForRenewals(Clients client, List<DocumentOnServerSide> files)
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
            String MESSAGE1 = "We wish to inform you,regarding "+client.getDepartment()+" policy, bearing Policy No.";
            String MESSAGE2 = " which stands in the name of ";
            String MESSAGE3 = " is expiring on ";
            String MESSAGE7 = "For reference attached are the documents relating to this policy." ;
            String MESSAGE4 = ". Kindly approach us for effecting renewal without break.";
            String MESSAGE5 ="For further information please contact us on our land line no.04066776677 and 04023416770 or E-mail us on teloshyd@gmail.com";
            String MESSAGE6 ="With regards,<br/>" +
                    "<br/>" +
                    "M. Narasimha Rao & M.R.G. Raju<br/>" +
                    "Telos Risk Management and Insurance Broking Services (P) Limited,<br/>" +
                    "2nd floor, Status Plaza, Opp.: Greenlands Guest House, Begumpet,<br/>" +
                    "Hyderabad - 500016.  Tel- 040-66776677  TeleFax-040-23416770 mobile 9848021211<br/>" +
                    "Website: www.telosrisk.com";
            String mainMessage = MESSAGE1+client.getPolicyNumber()+MESSAGE2+clientName+MESSAGE3+sdf.format(client.getPolicyEndDate())+MESSAGE4;
            if(client.getRenewalAmount() != null && client.getrenewalCompany() != null)
            {

                String MESSAGE8 = ". Your renewal premium works out to Rs."+client.getRenewalAmount()+", please arrange the payment in favour of "+client.getrenewalCompany()+" before expiry of the policy. If already paid please ignore.";
                mainMessage = MESSAGE1+client.getPolicyNumber()+MESSAGE2+clientName+MESSAGE3+sdf.format(client.getPolicyEndDate())+MESSAGE8;
            }
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom("do_not_reply@connect2telos.com.com");
            helper.setTo(client.getEmail());
            if(client.getSecondaryEmail() != null)
            {
                helper.setCc(client.getSecondaryEmail());
            }
            helper.setSubject("Renewal of Policy -TELOS(no reply accepted to this EMail ID)");
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
            if(files != null && files.size() > 0 )
            {

                messageBodyText = (new StringBuilder(
                        String.valueOf(messageBodyText))).append(MESSAGE7)
                        .toString();
            }
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
            if(files != null && files.size() >= 0 )
            {
                for (DocumentOnServerSide file : files) {
					if(! StringUtils.containsIgnoreCase(file.getName(),"MANDATE"))
					{

						InputStream in = file.getScanned().getBinaryStream();
						helper.addAttachment(file.getName(), new ByteArrayResource(
								IOUtils.toByteArray(in)));
					}
                }
            }
            mailSender.send(message);
        }
        catch (Exception e) {

            e.printStackTrace();
        }
        return true;

    }
}
