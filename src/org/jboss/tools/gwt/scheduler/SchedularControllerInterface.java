package org.jboss.tools.gwt.scheduler;

import org.jboss.tools.gwt.shared.Clients;
import org.jboss.tools.gwt.shared.DocumentOnServerSide;

import java.io.File;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * Created by karthikmarupeddi on 3/20/14.
 */
public interface SchedularControllerInterface {

    void sendSMSForRenewal();

    Boolean sentMail(Map<String, File> files);

    Boolean sentEmailAtDailyEight(Clients client, List<DocumentOnServerSide> files);

    public String getPdfReportForIRDA(String input) throws SQLException;

    public void getExcelReportForIRDA(String input) throws SQLException;

}
