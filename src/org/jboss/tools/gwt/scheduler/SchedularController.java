package org.jboss.tools.gwt.scheduler;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;

import org.jboss.tools.gwt.beans.TUserDAO;
import org.jboss.tools.gwt.beans.UserDAOImpl;
import org.jboss.tools.gwt.shared.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class SchedularController implements SchedularControllerInterface {
	
	ApplicationContext appContext = null;
	String report = "/resources/Reports/report";
	String renewal = "/resources/Reports/renewal";
	String pendingReport = "/resources/Reports/pending";
	public java.sql.Connection con;
    private List<Clients> renewalClient= null;
    private static String renewalSMS = "RENEWAL";
    private UserDAOImpl userDAO;
    private UserController userController;


    public String getPdfReportForIRDA(String input) throws SQLException {
		String all = "All";
		getApplicationContext();
		DataSource ds = (DataSource) appContext.getBean("dataSource");
		try {
			java.sql.Connection con = ds.getConnection();
			JasperPrint print = JasperFillManager.fillReport(input + ".jasper",
					null, con);
			String newFileName = input + ".pdf";
			JasperExportManager.exportReportToPdfFile(print, newFileName);
			if (input.contains(report)) {
				return "resources/Reports/report.pdf";
			} else if (input.contains(renewal)) {
				return "resources/Reports/renewal.pdf";
			} else if (input.contains(pendingReport)) {
				return "resources/Reports/pending.pdf";
			}
		} catch (SQLException e) {
			System.out.println("" + e.toString());
			return "SQL Exception" + e.toString();
		} catch (Exception e) {

			System.out.println("" + e.toString());
			return "Exception" + e.toString();
		}
		//con.close();
		return "/Reports/report.pdf";

	}
	
	public void getExcelReportForIRDA(String input) throws SQLException {
		getApplicationContext();
		DataSource ds;
        ds = (DataSource) appContext.getBean("dataSource");
        try {
			java.sql.Connection con = ds.getConnection();
			JasperPrint print = JasperFillManager.fillReport(input + ".jasper",
					null, con);
			String newExcelFileName = input + ".xls";
			OutputStream ouputStream = new FileOutputStream(newExcelFileName);
			
			ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

			JRXlsExporter exporterXLS = new JRXlsExporter();
			exporterXLS
					.setParameter(JRXlsExporterParameter.JASPER_PRINT, print);
			exporterXLS.setParameter(JRXlsExporterParameter.OUTPUT_STREAM,
					byteArrayOutputStream);
			exporterXLS.setParameter(
					JRXlsExporterParameter.IS_COLLAPSE_ROW_SPAN, Boolean.TRUE);
			exporterXLS
					.setParameter(
							JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_COLUMNS,
							Boolean.TRUE);
			exporterXLS.setParameter(
					JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS,
					Boolean.TRUE);
			exporterXLS
					.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET,
							Boolean.FALSE);
			exporterXLS.setParameter(
					JRXlsExporterParameter.IS_DETECT_CELL_TYPE, Boolean.FALSE);
			exporterXLS.setParameter(
					JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND,
					Boolean.FALSE);
			exporterXLS.exportReport();
			ouputStream.write(byteArrayOutputStream.toByteArray());
			ouputStream.flush();
			ouputStream.close();
			con.close();
		}catch (Exception e) {

			System.out.println("" + e.toString());
		}
		//con.close();
		
	}
	
	Boolean sentMail(Map<String, java.io.File> files)
	{
		
		SendEmail sendEmail = (SendEmail) appContext.getBean("sendEmail");
		return sendEmail.sentEmailBySchedule(files);
		
	}
	
	Boolean sentEmailAtDailyEight(Clients client, List<DocumentOnServerSide> files)
	{
		getApplicationContext();
		SendEmail sendEmail = (SendEmail) appContext.getBean("sendEmail");
		return sendEmail.emailSent(client, files);
		
	}

	/**
	 * it will get the application context
	 */
	private void getApplicationContext() {
		if (appContext == null) {
			//appContext = ApplicationContextProvider.getApplicationContext();
			appContext= new ClassPathXmlApplicationContext("applicationContext.xml");
		}

	}

    @Override
    public void sendSMSForRenewal() {
        System.out.println("Inside the controller method before Identifying the clients");
        renewalClient = userDAO.getRenewClient();
        getApplicationContext();
        SendEmail sendEmail = (SendEmail) appContext.getBean("sendEmail");
        for(Clients client : renewalClient)
         {
             System.out.println("clients are "+client.getPhoneNumber());
             Boolean smsStatus = userController.sendSMSToClient(client,renewalSMS);
             System.out.println("SMS was sent "+smsStatus.toString());
             if(client.getEmail()!= null)
             {

                 System.out.println("clients are "+client.getEmail());
                 Boolean mailStatus = sendEmail.sentEmailByScheduleForRenewals(client);
                 System.out.println("Email sent  "+mailStatus);
             }
         }
    }

    @Autowired
    public void setUserDAO(UserDAOImpl userDAO) {
        this.userDAO = userDAO;
    }

    @Autowired
    public void setUserController(UserController userController) {
        this.userController = userController;
    }
}
