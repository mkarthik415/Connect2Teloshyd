package org.jboss.tools.gwt.shared;

import java.io.File;
import java.util.List;
import java.util.Map;

/**
 * Created by karthikmarupeddi on 4/28/14.
 */
public interface SendEmailInterface {

    public Boolean emailSent(Client client, List<DocumentOnServerSide> files);

    public Boolean emailSentOnlyDocuments(Clients client, List<DocumentOnServerSide> files);

    public Boolean sentEmailBySchedule(Map<String, File> files);

    public Boolean sentEmailByScheduleForRenewals(Clients client, List<DocumentOnServerSide> files);


}
