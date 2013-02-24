package org.jboss.tools.gwt.shared;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.sql.DataSource;

import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;

import org.jboss.tools.gwt.shared.Client;

import org.jboss.tools.gwt.beans.TUserDAO;
import org.springframework.context.ApplicationContext;

public class UserController {

	private String uname;
	Logger logger = Logger.getLogger("logger");
	Boolean userResponse = false;
	String created = null;
	List<Clients> lClients = null;
	List<Agent> lAgent = null;
	List<OfficeCode> lOfficeCode = null;
	ApplicationContext appContext = null;

	// logic to get the data for login from telos DB
	public Boolean getUserResponse(final String user, final String password) {
		// User user = new User();
		appContext = ApplicationContextProvider.getApplicationContext();

		final TUserDAO tUserDAO = (TUserDAO) appContext.getBean("tUserDAO");

		try {
			userResponse = tUserDAO.selectUser(user, password);
			logger.log(Level.SEVERE, "response from DB ");

		} catch (Exception e) {

			logger.log(Level.SEVERE, "Inside UserController " + e.toString());
			return userResponse;
		}
		return userResponse;
	}

	// logic to put data for create new client into telos DB
	public String getCreateClientResponse(Client client) {
		appContext = ApplicationContextProvider.getApplicationContext();

		final TUserDAO tUserDAO = (TUserDAO) appContext.getBean("tUserDAO");

		try {
			created = tUserDAO.createClient(client);
		} catch (Exception e) {
			logger.log(Level.SEVERE, "Inside UserController " + e.toString());
		}

		return created;

	}

	public String updateClientResponse(Client client) {
		appContext = ApplicationContextProvider.getApplicationContext();

		final TUserDAO tUserDAO = (TUserDAO) appContext.getBean("tUserDAO");

		try {
			created = tUserDAO.updateClient(client);
		} catch (Exception e) {
			logger.log(Level.SEVERE, "Inside UserController " + e.toString());
		}

		return created;

	}

	public String createAgentResponse(Agent agent) {
		appContext = ApplicationContextProvider.getApplicationContext();

		final TUserDAO tUserDAO = (TUserDAO) appContext.getBean("tUserDAO");

		try {
			created = tUserDAO.createAgent(agent);
		} catch (Exception e) {
			logger.log(Level.SEVERE, "Inside UserController " + e.toString());
		}

		return created;

	}

	public List<Clients> getSearchClient(Client client) {
		appContext = ApplicationContextProvider.getApplicationContext();
		final TUserDAO tUserDAO = (TUserDAO) appContext.getBean("tUserDAO");
		try {
			lClients = tUserDAO.searchClient(client);
			logger.log(Level.SEVERE,
					"Inside UserController after UserController execution");
		} catch (Exception e) {
			logger.log(Level.SEVERE, "Inside UserController " + e.toString());
		}
		return lClients;

	}

	public List<Agent> getSearchAgent() {
		appContext = ApplicationContextProvider.getApplicationContext();
		final TUserDAO tUserDAO = (TUserDAO) appContext.getBean("tUserDAO");
		try {
			lAgent = tUserDAO.searchAgent();
			System.out.println(" agent found " + lAgent.get(0).getScreenName());
		} catch (Exception e) {
			logger.log(Level.SEVERE, "Inside UserController " + e.toString());
		}
		return lAgent;

	}
	
	public List<OfficeCode> getSearchOfficeCode() {
		appContext = ApplicationContextProvider.getApplicationContext();
		final TUserDAO tUserDAO = (TUserDAO) appContext.getBean("tUserDAO");
		try {
			lOfficeCode = tUserDAO.searchOfficeCode();
			System.out.println(" agent found " + lOfficeCode.get(0).getCompanyOfficeCode());
		} catch (Exception e) {
			logger.log(Level.SEVERE, "Inside UserController " + e.toString());
		}
		return lOfficeCode;

	}

	public Boolean getEmailClient(Client client) throws AddressException,
			MessagingException {
		SendEmail sendEmail = new SendEmail();
		Boolean sent = sendEmail.emailSent(client);
		return sent;

	}

	public String getSMSClient(Client client) {
		SmsLane smsLane = new SmsLane();
		String response = smsLane.SMSSender(client.getPhoneNumber(),
				client.getSmsLane());
		return response;

	}

	public String getPdfReportForSales(String input,
			Map<String, Object> parameters) {
		appContext = ApplicationContextProvider.getApplicationContext();
		DataSource ds = (DataSource) appContext.getBean("dataSource");
		try {
			java.sql.Connection con = ds.getConnection();
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("office_code", "611900");
			JasperPrint print = JasperFillManager.fillReport(input + ".jasper",
					parameters, con);
			String newFileName = input + ".pdf";
			JasperExportManager.exportReportToPdfFile(print, newFileName);

			// excel
			String newExcelFileName = input + ".xls";
			OutputStream ouputStream = new FileOutputStream(new File(
					newExcelFileName));
			ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

			JRXlsExporter exporterXLS = new JRXlsExporter();
			exporterXLS
					.setParameter(JRXlsExporterParameter.JASPER_PRINT, print);
			exporterXLS.setParameter(JRXlsExporterParameter.OUTPUT_STREAM,
					byteArrayOutputStream);
			exporterXLS.setParameter(JRXlsExporterParameter.IS_COLLAPSE_ROW_SPAN, Boolean.TRUE);
			exporterXLS.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_COLUMNS, Boolean.TRUE);
			exporterXLS.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS, Boolean.TRUE);
			exporterXLS.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.FALSE);
			exporterXLS.setParameter(JRXlsExporterParameter.IS_DETECT_CELL_TYPE, Boolean.FALSE);
			exporterXLS.setParameter(JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND, Boolean.FALSE);
			//exporter.setParameter(JRXlsExporterParameter.IS_IGNORE_GRAPHICS, Boolean.TRUE);
			exporterXLS.exportReport();
			ouputStream.write(byteArrayOutputStream.toByteArray());
			ouputStream.flush();
			ouputStream.close();
			return newFileName;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "/Reports/report.pdf";

	}

}
