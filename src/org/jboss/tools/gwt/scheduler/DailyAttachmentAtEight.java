package org.jboss.tools.gwt.scheduler;

import java.util.List;

import org.jboss.tools.gwt.shared.Clients;
import org.jboss.tools.gwt.shared.DocumentOnServerSide;
import org.jboss.tools.gwt.shared.SendEmail;
import org.jboss.tools.gwt.shared.UserController;

public class DailyAttachmentAtEight {
	
	public void sendMail()
	{
		
	}
	
	public void sendEmails()
	{
		UserController userController = new UserController();
		List<Clients> listOfClientToEmail = userController.getListClientToEmail();
		for(Clients clients:listOfClientToEmail)
		{
			System.out.println("clients Id's are :::::::"+clients.getId());
			List<DocumentOnServerSide> totalDocuments = userController.searchDocumentsByClient(clients);

			SchedularController schedularController = new SchedularController();
			schedularController.sentEmailAtDailyEight(clients, totalDocuments);
			
		}
		
	}

}
