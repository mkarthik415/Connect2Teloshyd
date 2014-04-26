package org.jboss.tools.gwt.scheduler;

import org.apache.commons.lang.StringUtils;
import org.springframework.mail.javamail.JavaMailSender;

import javax.mail.MessagingException;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class PendingPolicies  {

	private JavaMailSender mailSender;
	
	public void setMailSender(JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}

	public void reports() throws SQLException, MessagingException, IOException {
		System.out.println("Starts now");
		SchedularController userController = new SchedularController();
		//String fileLocation;
		
		//file for pending policies, missing emails and missing phone number.
		File pendingPoliciesFile = new File("pending");
		File missingEmailsFile = new File("email");
		File missingPhoneNumbersFile = new File("phoneno");
		
		//path of pending policies is configured.
		String pathOfPendingPolicies = getClass().getProtectionDomain().getCodeSource()
				.getLocation().getPath();
		pathOfPendingPolicies = StringUtils.substringBefore(pathOfPendingPolicies, "/WEB-INF")+"/WEB-INF/pending";
		userController.getExcelReportForIRDA(pathOfPendingPolicies);
		pathOfPendingPolicies = pathOfPendingPolicies+".xls";
		
		//path of missing emails is configured.
		String pathOfMissingEmails = getClass().getProtectionDomain().getCodeSource()
				.getLocation().getPath();
		pathOfMissingEmails = StringUtils.substringBefore(pathOfMissingEmails, "/WEB-INF")+"/WEB-INF/email";
		userController.getExcelReportForIRDA(pathOfMissingEmails);
		pathOfMissingEmails = pathOfMissingEmails+".xls";
		
		//path of missing phone numbers is configured.
		String pathOfMissingPhoneNumbers = getClass().getProtectionDomain().getCodeSource()
				.getLocation().getPath();
		pathOfMissingPhoneNumbers = StringUtils.substringBefore(pathOfMissingPhoneNumbers, "/WEB-INF")+"/WEB-INF/phoneno";
		userController.getExcelReportForIRDA(pathOfMissingPhoneNumbers);
		pathOfMissingPhoneNumbers = pathOfMissingPhoneNumbers+".xls";
		
		
		Map<String, File> listOfFiles = new HashMap<String, File>();
		
		listOfFiles.put(pathOfPendingPolicies, pendingPoliciesFile);
		//listOfFiles.put(pathOfMissingEmails, missingEmailsFile);
		//listOfFiles.put(pathOfMissingPhoneNumbers, missingPhoneNumbersFile);

		userController.sentMail(listOfFiles);
		
		
	}
}
