package org.jboss.tools.gwt.scheduler;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.mail.MessagingException;

import org.apache.commons.lang.StringUtils;
import org.springframework.mail.javamail.JavaMailSender;

public class PendingPoliciesForMonday {

	private JavaMailSender mailSender;

	public void setMailSender(JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}

	public void printMe() throws SQLException, MessagingException, IOException {
		System.out.println("Starts now");
		SchedularController userController = new SchedularController();
		Map<String, Object> param = new HashMap<String, Object>();
		Date fromDate = new Date(new Date().getTime() - 172800000);
		//System.out.println("From Time is " + fromDate);
		Date toDate = new Date();
		String fileLocation;
		param.put("from_date", fromDate);
		param.put("to_date", toDate);
		//System.out.println("Before");

		File file = new File("pending");
		String path = getClass().getProtectionDomain().getCodeSource()
				.getLocation().getPath();
		path = StringUtils.substringBefore(path, "/WEB-INF")+"/WEB-INF/pending";
/*		System.out.println("parent directory is:::"
				+ getClass().getProtectionDomain().getCodeSource()
						.getLocation().getPath());*/
		fileLocation = userController.getPdfReportForIRDA(path);
		//System.out.println("After");
		File outPutFile = new File("pending.pdf");
		String outPutFilepath = outPutFile.getAbsolutePath();
		//userController.sentMail(path+".pdf", file);

	}

}
