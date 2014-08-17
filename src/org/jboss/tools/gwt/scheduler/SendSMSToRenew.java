package org.jboss.tools.gwt.scheduler;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by karthikmarupeddi on 3/20/14.
 */

public class SendSMSToRenew {

    Logger logger = Logger.getLogger("logger");

    @Autowired
    private SchedularControllerInterface userControllerSchedularBO;


    public void sendSMSToRenewalClients()
    {
        logger.log(Level.SEVERE, "Method intilalised to send renewal for all client at 9AM in the morning. ");
        userControllerSchedularBO.sendSMSForRenewal();


    }

}
