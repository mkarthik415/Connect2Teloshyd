package org.jboss.tools.gwt.scheduler;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.apache.catalina.util.URL;
import org.apache.commons.io.IOUtils;
import org.jboss.tools.gwt.shared.UserController;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

public class PendingPolicies  {

	private JavaMailSender mailSender;
	
	public void setMailSender(JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}

	public void printMe() throws SQLException, MessagingException, IOException {
		System.out.println("Starts now");
		UserController userController = new UserController();
		Map<String, Object> param = new HashMap<String, Object>();
		@SuppressWarnings("deprecation")
		Date fromDate = new Date(2013,8,1);
		@SuppressWarnings("deprecation")
		Date toDate = new Date(2013,8,31);
		String fileLocation;
		param.put("from_date", fromDate);
		param.put("to_date", toDate);
		System.out.println("Before");
		
		File file= new File("pending.jasper");
		String path = file.getAbsolutePath();
		fileLocation = userController.getPdfReportForIRDA("/pending", param);
		System.out.println("After");
		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, true);
		helper.setTo("mkarthik415@gmail.com");
		helper.setSubject("Documents");
		FileInputStream fis = new FileInputStream(file);
		helper.addAttachment(file.getName(), new ByteArrayResource(
				IOUtils.toByteArray(fis)));
		mailSender.send(message);
		
		
	}
}
