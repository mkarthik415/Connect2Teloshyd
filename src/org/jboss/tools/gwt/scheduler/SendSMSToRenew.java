package org.jboss.tools.gwt.scheduler;

import org.jboss.tools.gwt.shared.UserControllerInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by karthikmarupeddi on 3/20/14.
 */

public class SendSMSToRenew {

    ClassPathXmlApplicationContext appContext;
    SchedularController userControllerSchedularBO;

    public void sendSMSToRenewalClients()
    {
        System.out.println("Inside the constructor");
        getUserDaoBean().sendSMSForRenewal();


    }

    private void getApplicationContext() {
        if (this.appContext == null) {
            //appContext = ApplicationContextProvider.getApplicationContext();
            this.appContext= new ClassPathXmlApplicationContext("applicationContext.xml");
        }

    }

    private SchedularController getUserDaoBean() {
        getApplicationContext();
        if (this.userControllerSchedularBO == null) {
            this.userControllerSchedularBO = (SchedularController) appContext.getBean("userControllerSchedularBO");
        }
        return userControllerSchedularBO;
    }

}
