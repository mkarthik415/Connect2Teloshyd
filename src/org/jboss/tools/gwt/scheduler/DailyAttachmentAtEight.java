package org.jboss.tools.gwt.scheduler;

import org.jboss.tools.gwt.shared.Clients;
import org.jboss.tools.gwt.shared.DocumentOnServerSide;
import org.jboss.tools.gwt.shared.UserControllerInterface;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DailyAttachmentAtEight {

    @Autowired
    private UserControllerInterface userController;

    @Autowired
    private SchedularControllerInterface userControllerSchedularBO;

    Logger logger = Logger.getLogger("logger");
	
	public void sendEmails()
	{
        logger.log(Level.SEVERE, "Comes to method where emails and SMS are send daily at 830  ");
		List<Clients> listOfClientToEmail = userController.getListClientToEmail();

        logger.log(Level.SEVERE, "And the Email list size is ##"+listOfClientToEmail.size());
        if(!listOfClientToEmail.isEmpty())
        {

            for(Clients clients:listOfClientToEmail)
            {
                if(clients.getEmail() != null && !(clients.getEmail() .isEmpty()))
                {

                    logger.log(Level.SEVERE,"clients Id's are :::::::"+clients.getId());
                    List<DocumentOnServerSide> totalDocuments = userController.searchDocumentsByClient(clients);
                    logger.log(Level.SEVERE,"The total NUmber of Documents are ##"+totalDocuments.size());

                    userControllerSchedularBO.sentEmailAtDailyEight(clients, totalDocuments);
                }

            }
        }

	}

}
