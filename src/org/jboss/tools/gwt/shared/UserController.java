package org.jboss.tools.gwt.shared;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
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

import org.jboss.tools.gwt.beans.TUserDAO;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

//import java.sql.Date;

public class UserController {

	@SuppressWarnings("unused")
	private String uname;
	Logger logger = Logger.getLogger("logger");
	Integer userResponse = null;
	String created = null;
	List<Clients> lClients = null;
	List<Company> lCompany = null;
	List<EmailList> lemails = null;
	CompanyDetails comapnydetails = null;
	List<org.jboss.tools.gwt.shared.File> documents = null;
	List<DocumentOnServerSide> documentsBlob = null;
	List<Agent> lAgent = null;
	List<Insurance> lInsurance = null;
	List<OfficeCode> lOfficeCode = null;
	// ApplicationContext appContext = null;
	ApplicationContext appContext = null;
	TUserDAO tUserDAO = null;
	String report = "report";
	String renewal = "renewal";
	String pendingReport = "reportForPending";
	public java.sql.Connection con;
	Email emailId;
	Boolean filesSent;
	List<EmailedFile> emailsSent = null;
	java.sql.Date mailDate = new java.sql.Date(2013, 12, 31);
	String sMSTemplateForDocuments = "DOCUMENTS";
	SmsLane smsLane = null;

	// logic to get the data for login from telos DB
	public Integer getUserResponse(final String user, final String password) {
		final TUserDAO tUserDAO = getUserDaoBean();

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
		final TUserDAO tUserDAO = getUserDaoBean();

		try {
			created = tUserDAO.createClient(client);
		} catch (Exception e) {
			logger.log(Level.SEVERE, "Inside UserController " + e.toString());
		}

		return created;

	}

	public String updateClientResponse(Client client) {
		final TUserDAO tUserDAO = getUserDaoBean();

		try {
			created = tUserDAO.updateClient(client);
		} catch (Exception e) {
			logger.log(Level.SEVERE, "Inside UserController " + e.toString());
		}

		return created;

	}

	public String createAgentResponse(Agent agent) {
		final TUserDAO tUserDAO = getUserDaoBean();

		try {
			created = tUserDAO.createAgent(agent);
		} catch (Exception e) {
			logger.log(Level.SEVERE, "Inside UserController " + e.toString());
		}

		return created;

	}

	public String createInsuranceResponse(Insurance insurance) {
		final TUserDAO tUserDAO = getUserDaoBean();

		try {
			created = tUserDAO.createInsurance(insurance);
		} catch (Exception e) {
			logger.log(Level.SEVERE, "Inside UserController " + e.toString());
		}

		return created;

	}

	public List<Clients> getSearchClient(Client client) {
		logger.log(Level.SEVERE,
				"Inside UserController before UserController execution");
		final TUserDAO tUserDAO = getUserDaoBean();
		try {
			lClients = tUserDAO.searchClient(client);
			logger.log(Level.SEVERE,
					"Inside UserController after UserController execution");
		} catch (Exception e) {
			logger.log(Level.SEVERE, "Inside UserController " + e.toString());
		}
		return lClients;

	}

	public List<Clients> getSearchClientByCarNum(Client client) {
		final TUserDAO tUserDAO = getUserDaoBean();
		try {
			lClients = tUserDAO.searchClientByCarNum(client);
			logger.log(Level.SEVERE,
					"Inside UserController after UserController execution");
		} catch (Exception e) {
			logger.log(Level.SEVERE, "Inside UserController " + e.toString());
		}
		return lClients;

	}

	public List<Clients> getSearchClientByPhoneNum(Client client) {
		final TUserDAO tUserDAO = getUserDaoBean();
		try {
			lClients = tUserDAO.searchClientByPhoneNum(client);
			logger.log(Level.SEVERE,
					"Inside UserController after UserController execution");
		} catch (Exception e) {
			logger.log(Level.SEVERE, "Inside UserController " + e.toString());
		}
		return lClients;

	}

	public List<Clients> getSearchClientByPolicyDates(Client client) {
		final TUserDAO tUserDAO = getUserDaoBean();
		try {
			lClients = tUserDAO.searchClientByPolicyDates(client);
			logger.log(Level.SEVERE,
					"Inside UserController after UserController execution");
		} catch (Exception e) {
			logger.log(Level.SEVERE, "Inside UserController " + e.toString());
		}
		return lClients;

	}

	public List<Clients> getSearchClientBySerialNo(Client client) {
		getApplicationContext();
		logger.log(Level.SEVERE,
				"Inside UserController before implementation DAO being execution");
		final TUserDAO tUserDAO = (TUserDAO) appContext.getBean("tUserDAO");
		try {
			lClients = tUserDAO.searchClientBySerialNo(client);
			logger.log(Level.SEVERE,
					"Inside UserController after UserController execution");
		} catch (Exception e) {
			logger.log(Level.SEVERE, "Inside UserController " + e.toString());
		}
		return lClients;
	}

	public List<Clients> getSearchClientByPolicyNo(Client client) {
		getApplicationContext();
		logger.log(Level.SEVERE,
				"Inside UserController before implementation DAO being execution");
		final TUserDAO tUserDAO = (TUserDAO) appContext.getBean("tUserDAO");
		try {
			lClients = tUserDAO.searchClientByPolicyNo(client);
			logger.log(Level.SEVERE,
					"Inside UserController after UserController execution");
		} catch (Exception e) {
			logger.log(Level.SEVERE, "Inside UserController " + e.toString());
		}
		return lClients;
	}

	public List<Company> getListOfCompanies() {
		getApplicationContext();
		logger.log(
				Level.SEVERE,
				"Inside UserController of list of companies before implementation DAO being execution");
		final TUserDAO tUserDAO = (TUserDAO) appContext.getBean("tUserDAO");
		try {
			lCompany = tUserDAO.getListOfComapnies();
			logger.log(Level.SEVERE,
					"Inside UserController after UserController execution");
		} catch (Exception e) {
			logger.log(Level.SEVERE, "Inside UserController " + e.toString());
		}
		return lCompany;
	}
	
	public List<EmailList> getListOfEmails(){
		getApplicationContext();
		logger.log(
				Level.SEVERE,
				"Inside UserController of list of companies before implementation DAO being execution");
		final TUserDAO tUserDAO = (TUserDAO) appContext.getBean("tUserDAO");
		try {
			lemails = tUserDAO.loadEmails();
			logger.log(Level.SEVERE,
					"Inside UserController after UserController execution");
		} catch (Exception e) {
			logger.log(Level.SEVERE, "Inside UserController " + e.toString());
		}
		return lemails;
		
	}
	
	public CompanyDetails getCompanyDetails(Company company){
		getApplicationContext();
		logger.log(
				Level.SEVERE,
				"Inside UserController of list of companies Details before implementation DAO being execution for "+company.getCompnyName());
		final TUserDAO tUserDAO = (TUserDAO) appContext.getBean("tUserDAO");
		try {
			comapnydetails = tUserDAO.getCompanyDetails(company);
			logger.log(Level.SEVERE,
					"Inside UserController after UserController execution");
		} catch (Exception e) {
			logger.log(Level.SEVERE, "Inside UserController " + e.toString());
		}
		return comapnydetails;
	}

	public List<org.jboss.tools.gwt.shared.File> getUploadedDocuments(
			Client client) {
		getApplicationContext();
		logger.log(Level.SEVERE,
				"Inside UserController before implementation DAO for documents being execution");
		final TUserDAO tUserDAO = (TUserDAO) appContext.getBean("tUserDAO");
		try {
			documents = tUserDAO.searchDocumentsByClientId(client);
			logger.log(Level.SEVERE,
					"Inside UserController after UserController execution");
		} catch (Exception e) {
			logger.log(Level.SEVERE, "Inside UserController " + e.toString());
		}
		return documents;
	}

	public Email logEmail(Email email) {
		getApplicationContext();
		final TUserDAO tUserDAO = (TUserDAO) appContext.getBean("tUserDAO");
		try {

			emailId = tUserDAO.logEmail(email);
		} catch (Exception e) {
			logger.log(Level.SEVERE, "Inside UserController " + e.toString());
		}
		return emailId;

	}

	public Boolean logEmailedFiles(Email email, List<DocumentOnServerSide> files) {
		getApplicationContext();
		final TUserDAO tUserDAO = (TUserDAO) appContext.getBean("tUserDAO");
		try {
			filesSent = tUserDAO.logEmailedFiles(email, files);
		} catch (Exception e) {
			logger.log(Level.SEVERE, "Inside UserController " + e.toString());
		}
		return filesSent;
	}

	public Boolean endDate(List<DocumentOnServerSide> files) {
		getApplicationContext();
		final TUserDAO tUserDAO = (TUserDAO) appContext.getBean("tUserDAO");
		Boolean endDateStatus = true;
		try {
			endDateStatus = tUserDAO.endDateEmailedFiles(files);

		} catch (Exception ex) {
			logger.log(Level.SEVERE, "Inside UserController " + ex.toString());
			return false;
		}
		return endDateStatus;

	}

	public List<EmailedFile> getEmails(org.jboss.tools.gwt.shared.File file) {
		final TUserDAO tUserDAO = getUserDaoBean();
		try {
			emailsSent = tUserDAO.getEmails(file);
		} catch (Exception e) {
			logger.log(Level.SEVERE, "Inside UserController " + e.toString());
		}
		return emailsSent;
	}

	public List<Agent> getSearchAgent() {
		final TUserDAO tUserDAO = getUserDaoBean();
		try {
			lAgent = tUserDAO.searchAgent();
			System.out.println(" agent found " + lAgent.get(0).getScreenName());
		} catch (Exception e) {
			logger.log(Level.SEVERE, "Inside UserController " + e.toString());
		}
		return lAgent;

	}

	public List<Insurance> getSearchInsuranceCompany() {
		final TUserDAO tUserDAO = getUserDaoBean();
		try {
			lInsurance = tUserDAO.searchInsuranceComapny();
			System.out.println(" agent found " + lAgent.get(0).getScreenName());
		} catch (Exception e) {
			logger.log(Level.SEVERE, "Inside UserController " + e.toString());
		}
		return lInsurance;

	}

	public List<OfficeCode> getSearchOfficeCode() {
		final TUserDAO tUserDAO = getUserDaoBean();
		try {
			lOfficeCode = tUserDAO.searchOfficeCode();
		} catch (Exception e) {
			logger.log(Level.SEVERE, "Inside UserController " + e.toString());
		}
		return lOfficeCode;

	}

	public Boolean getEmailClient(Client client,
			List<org.jboss.tools.gwt.shared.File> files)
			throws AddressException, MessagingException {
		getApplicationContext();
		try {
			documentsBlob = this.getUserDaoBean()
					.searchDocumentsByFileId(files);
			logger.log(Level.SEVERE,
					"Inside UserController after UserController execution");
		} catch (Exception e) {
			logger.log(Level.SEVERE, "Inside UserController " + e.toString());
		}
		SendEmail sendEmail = (SendEmail) appContext.getBean("sendEmail");
		Boolean sent = sendEmail.emailSent(client, documentsBlob);
		return sent;

	}
	
	public Boolean deleteClientDocuments(Client client,
			List<org.jboss.tools.gwt.shared.File> files)
	{
		Boolean deleted = false;
		final TUserDAO tUserDAO = getUserDaoBean();
		deleted = tUserDAO.deleteDocumentsForClients(client, files);
		return deleted;
	}

	public List<Clients> getListClientToEmail() {
		List<Clients> listClientsToEmail = null;
		final TUserDAO tUserDAO = getUserDaoBean();
		try {
			listClientsToEmail = tUserDAO.searchClientToEmail();
		} catch (Exception e) {
			logger.log(Level.SEVERE, "Inside UserController " + e.toString());
		}
		return listClientsToEmail;
	}
	
	public Clients searchInsuranceDetailsByOfficeCode(Client client){
		Clients insuranceCompanyDetails = null;
		final TUserDAO tUserDAO = getUserDaoBean();
		try {
			insuranceCompanyDetails = tUserDAO.searchInsuranceDetailsByCode(client);
			logger.log(Level.SEVERE, "Inside UserController and insurance company is  :::" + insuranceCompanyDetails.getInsCompanyName());
		} catch (Exception e) {
			logger.log(Level.SEVERE, "Inside UserController " + e.toString());
		}
		return insuranceCompanyDetails;
		
	}

	public List<DocumentOnServerSide> searchDocumentsByClient(Clients client) {
		List<DocumentOnServerSide> totalDocuments = null;
		final TUserDAO tUserDAO = getUserDaoBean();
		try {
			totalDocuments = tUserDAO.searchDocumentsByClientIdForEmail(client);
		} catch (Exception e) {
			logger.log(Level.SEVERE, "Inside UserController " + e.toString());
		}
		return totalDocuments;
	}

	public String getSMSClient(Client client) {
		smsLane = new SmsLane();
		String response = smsLane.SMSSender(client.getPhoneNumber(),
				client.getSmsLane());
		return response;

	}

	public Boolean sendSMSToClient(Clients client) {
		Boolean responseByFirstPhoneNumber = false;
		Boolean responseBySeconfPhoneNUmber = false;
		Boolean success = false;
		final TUserDAO tUserDAO = getUserDaoBean();
			SmsLane smsLane = new SmsLane();

		if (client.getPhoneNumber() != null) {

			String response = smsLane.SMSSender(client.getPhoneNumber(),
					sMSTemplateForDocuments);
			if (response.subSequence(17, 29).equals(client.getPhoneNumber())) {
				responseByFirstPhoneNumber = true;
				tUserDAO.logSms(client, "DOCUMENTS", "PRIMARY_PHONE_NUMBER");
			} else
				responseByFirstPhoneNumber = false;
		}
		if (client.getSecondaryPhoneNumber() != null) {
			String response = smsLane.SMSSender(
					client.getSecondaryPhoneNumber(), sMSTemplateForDocuments);
			if (response.subSequence(17, 29).equals(
					client.getSecondaryPhoneNumber())) {
				responseBySeconfPhoneNUmber = true;
				tUserDAO.logSms(client, "DOCUMENTS", "SECONDSRY_PHONE_NUMBER");
			} else
				responseBySeconfPhoneNUmber = false;
		}

		if (responseByFirstPhoneNumber || responseBySeconfPhoneNUmber) {
			success = true;
			return success;
		} else {

			return success;
		}

	}

	public String getPdfReport(String input,
                               Map<String, Object> parameters) throws SQLException {
		String all = "All";
		getApplicationContext();
		DataSource ds = (DataSource) appContext.getBean("dataSource");
		try {
			java.sql.Connection con = ds.getConnection();
			String officeCode = "'" + parameters.get("office_code") + "'";
			Map<String, Object> param = new HashMap<String, Object>();
			Date fromDate = (Date) parameters.get("from_date");
			Date toDate = (Date) parameters.get("to_date");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String sqlDate = sdf.format(fromDate);
			String sqlToDate = sdf.format(toDate);
			if (parameters.get("office_code") != null) {
				if (parameters.get("office_code").equals(all)) {
					param.put("office_code",
							"select distinct office_code from test_prefixTELOS");
				} else {
					param.put("office_code", officeCode);
				}
			}
			param.put("from_date", sqlDate);
			param.put("to_date", sqlToDate);
			JasperPrint print = JasperFillManager.fillReport(input + ".jasper",
					param, con);
            String newFileName = input + ".pdf";
            JasperExportManager.exportReportToPdfFile(print, newFileName);
            logger.log(Level.SEVERE,
                    "input file path before the comparistion is : "+input);
            String fileName = input.substring(input.lastIndexOf("/") + 1);
            if (fileName.equals(report)) {
                return "resources/Reports/report.pdf";
			} else if (fileName.equals(renewal)) {
				return "resources/Reports/renewal.pdf";
			} else if (fileName.equals(pendingReport)) {
				return "resources/Reports/reportForPending.pdf";
			}
		} catch (SQLException e) {
			System.out.println("" + e.toString());
			return "SQL Exception" + e.toString();
		} catch (Exception e) {

			System.out.println("" + e.toString());
			return "Exception" + e.toString();
		}
		// con.close();
		return "/Reports/report.pdf";

	}

	public String getExcelReport(String input,
                                 Map<String, Object> parameters) {
		getApplicationContext();
		String all = "All";
		DataSource ds;
        ds = (DataSource) appContext.getBean("dataSource");
        try {
            logger.log(Level.SEVERE,
                    "Inside UserController for excel report the path of the file is "+input);
			java.sql.Connection con = ds.getConnection();
			String officeCode = "'" + parameters.get("office_code") + "'";
			Map<String, Object> param = new HashMap<String, Object>();
			Date fromDate = (Date) parameters.get("from_date");
			Date toDate = (Date) parameters.get("to_date");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String sqlDate = sdf.format(fromDate);
			String sqlToDate = sdf.format(toDate);
			if (parameters.get("office_code").equals(all)) {
				param.put("office_code",
						"select distinct office_code from test_prefixTELOS");
			} else {
				param.put("office_code", officeCode);
			}
			param.put("from_date", sqlDate);
			param.put("to_date", sqlToDate);
			JasperPrint print = JasperFillManager.fillReport(input + ".jasper",
					param, con);
			String newExcelFileName = input + ".xls";
			OutputStream ouputStream = new FileOutputStream(new File(
					newExcelFileName));
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
            logger.log(Level.SEVERE,
                    "input file path before the comparistion is : "+input);
            String fileName = input.substring(input.lastIndexOf("/") + 1);
            logger.log(Level.SEVERE,
                    "After transformation : "+fileName);

			if (fileName.equals(report)) {
				return "resources/Reports/report.xls";
			} else if (fileName.equals(renewal)) {
				return "resources/Reports/renewal.xls";
			} else if (fileName.equals(pendingReport)) {
                logger.log(Level.SEVERE,
                        "Identified the output file for the report. "+fileName);
				return "resources/Reports/reportForPending.xls";
			}
			con.close();
		} catch (SQLException e) {
            logger.log(Level.SEVERE,
                    "exception for the file is  : "+e.toString());
			e.printStackTrace();
		} catch (Exception e) {
            logger.log(Level.SEVERE,
                    "exception for the file is  : "+e.toString());
			e.printStackTrace();
		}
		return "resources/Reports/reportForPending.xls";

	}

	public String getPdfReportForClient(String input,
			Map<String, Object> parameters) {
		getApplicationContext();
		DataSource ds = (DataSource) appContext.getBean("dataSource");
		try {
			java.sql.Connection con = ds.getConnection();
			String name = null;
			String officeCode = null;
			if (parameters.get("name") != null)
				name = parameters.get("name").toString();
			if (parameters.get("office_code") != null)
				officeCode = parameters.get("office_code").toString();
			Map<String, Object> param = new HashMap<String, Object>();
			Date fromDate = (Date) parameters.get("from_date");
			Date toDate = (Date) parameters.get("to_date");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String sqlDate = sdf.format(fromDate);
			String sqlToDate = sdf.format(toDate);

			String onlyName = "INSURED_NAME like '%" + name + "%'";
			String onlyCompany = "COMPANY like '%" + officeCode + "%'";
			String nameAndCompany = "AND COMPANY like '%" + officeCode + "%'";

			if (parameters.get("name") != null
					&& parameters.get("office_code") == null) {
				param.put("name", onlyName);
			} else if (parameters.get("name") != null
					&& parameters.get("office_code") != null) {
				param.put("name", onlyName + nameAndCompany);
			} else if (parameters.get("name") == null
					&& parameters.get("office_code") != null) {
				param.put("name", onlyCompany);
			}

			param.put("from_date", sqlDate);
			param.put("to_date", sqlToDate);
			JasperPrint print = JasperFillManager.fillReport(input + ".jasper",
					param, con);
			String newFileName = input + ".pdf";
			JasperExportManager.exportReportToPdfFile(print, newFileName);
			con.close();
			return "resources/Reports/client.pdf";
		} catch (SQLException e) {
			System.out.println("" + e.toString());
			return "SQL Exception" + e.toString();
		} catch (Exception e) {

			System.out.println("" + e.toString());
			return "Exception" + e.toString();
		}

	}

	public String getExcelReportForClient(String input,
			Map<String, Object> parameters) {
		getApplicationContext();
		@SuppressWarnings("unused")
		String all = "All";
		DataSource ds = (DataSource) appContext.getBean("dataSource");
		try {
			java.sql.Connection con = ds.getConnection();
			String name = null;
			String officeCode = null;
			if (parameters.get("name") != null)
				name = parameters.get("name").toString();
			if (parameters.get("office_code") != null)
				officeCode = parameters.get("office_code").toString();
			Map<String, Object> param = new HashMap<String, Object>();
			Date fromDate = (Date) parameters.get("from_date");
			Date toDate = (Date) parameters.get("to_date");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String sqlDate = sdf.format(fromDate);
			String sqlToDate = sdf.format(toDate);

			String onlyName = "INSURED_NAME like '%" + name + "%'";
			String onlyCompany = "COMPANY like '%" + officeCode + "%'";
			String nameAndCompany = "AND COMPANY like '%" + officeCode + "%'";

			if (parameters.get("name") != null
					&& parameters.get("office_code") == null) {
				param.put("name", onlyName);
			} else if (parameters.get("name") != null
					&& parameters.get("office_code") != null) {
				param.put("name", onlyName + nameAndCompany);
			} else if (parameters.get("name") == null
					&& parameters.get("office_code") != null) {
				param.put("name", onlyCompany);
			}

			param.put("from_date", sqlDate);
			param.put("to_date", sqlToDate);
			JasperPrint print = JasperFillManager.fillReport(input + ".jasper",
					param, con);
			String newExcelFileName = input + ".xls";
			OutputStream ouputStream = new FileOutputStream(new File(
					newExcelFileName));
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
			return "resources/Reports/client.xls";

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "resources/Reports/client.xls";

	}

	public String getPdfReportForPendingPolicy(String input,
			Map<String, Object> parameters) {
		getApplicationContext();
		DataSource ds = (DataSource) appContext.getBean("dataSource");
		try {
			java.sql.Connection con = ds.getConnection();
			Map<String, Object> param = new HashMap<String, Object>();
			Date fromDate = (Date) parameters.get("from_date");
			Date toDate = (Date) parameters.get("to_date");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String sqlDate = sdf.format(fromDate);
			String sqlToDate = sdf.format(toDate);
			param.put("from_date", sqlDate);
			param.put("to_date", sqlToDate);
			JasperPrint print = JasperFillManager.fillReport(input + ".jasper",
					param, con);
			String newFileName = input + ".pdf";
			JasperExportManager.exportReportToPdfFile(print, newFileName);
			con.close();
			return "resources/Reports/pendingReport.pdf";

		} catch (SQLException e) {
			System.out.println("" + e.toString());
			return "SQL Exception" + e.toString();
		} catch (Exception e) {

			System.out.println("" + e.toString());
			return "Exception" + e.toString();
		}

	}

	public String getExcelReportForPendingPolicy(String input,
			Map<String, Object> parameters) {
		getApplicationContext();
		String all = "All";
		DataSource ds = (DataSource) appContext.getBean("dataSource");
		try {
			java.sql.Connection con = ds.getConnection();
			String officeCode = "'" + parameters.get("office_code") + "'";
			Map<String, Object> param = new HashMap<String, Object>();
			Date fromDate = (Date) parameters.get("from_date");
			Date toDate = (Date) parameters.get("to_date");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String sqlDate = sdf.format(fromDate);
			String sqlToDate = sdf.format(toDate);
			if (parameters.get("office_code").equals(all)) {
				param.put("office_code",
						"select distinct office_code from test_prefixTELOS");
			} else {
				param.put("office_code", officeCode);
			}
			param.put("from_date", sqlDate);
			param.put("to_date", sqlToDate);
			JasperPrint print = JasperFillManager.fillReport(input + ".jasper",
					param, con);
			String newExcelFileName = input + ".xls";
			OutputStream ouputStream = new FileOutputStream(new File(
					newExcelFileName));
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
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "resources/Reports/policy.xls";

	}

	// methods returns boolean after uploading PDF Document
	public Boolean insertDocumentToDB(int clientId, InputStream inputStream,
			String name, String description, String scannedBy) {
		try {
			logger.log(Level.SEVERE,
					"Inside UserController for document upload");
			getApplicationContext();
			DataSource ds = (DataSource) appContext.getBean("dataSource");
			java.sql.Connection con = ds.getConnection();
			logger.log(Level.SEVERE, "Data Connection created");
			PreparedStatement psmnt = (PreparedStatement) (con)
					.prepareStatement(
							"INSERT  INTO scan(client_id,scanned,name,description,scanned_by) VALUES  (?,?,?,?,?)",
							Statement.RETURN_GENERATED_KEYS);
			psmnt.setInt(1, clientId);
			psmnt.setBinaryStream(2, inputStream);
			psmnt.setString(3, name);
			psmnt.setString(4, description);
			psmnt.setString(5, scannedBy);
			psmnt.executeUpdate();
			ResultSet generatedKeys = psmnt.getGeneratedKeys();
			if (generatedKeys.next()) {
				System.out.println("id is" + generatedKeys.getLong(1));
				PreparedStatement pSmntForScanAndMmail = (PreparedStatement) (con)
						.prepareStatement("INSERT  INTO scan_email(scan_id,client_id,mail_date) VALUES  (?,?,?)");
				pSmntForScanAndMmail.setInt(1, generatedKeys.getInt(1));
				pSmntForScanAndMmail.setInt(2, clientId);
				pSmntForScanAndMmail.setDate(3, mailDate);
				pSmntForScanAndMmail.executeUpdate();
			}
			con.close();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			logger.log(Level.SEVERE, "Inside UserController " + e.toString());
		}
		return false;
	}

	public Connection downloadDocuments() {
		PreparedStatement pst = null;
		FileOutputStream fos = null;
		Blob blob = null;
		logger.log(Level.SEVERE,
				"Inside UserController for download documents.");
		try {
			getApplicationContext();
			DataSource ds = (DataSource) appContext.getBean("dataSource");

			java.sql.Connection con = ds.getConnection();
			return con;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		logger.log(Level.SEVERE, "Data Connection created");
		return con;
	}

	/**
	 * @return TUserDAO
	 */
	private TUserDAO getUserDaoBean() {
		getApplicationContext();
		if (tUserDAO == null) {
			tUserDAO = (TUserDAO) appContext.getBean("tUserDAO");
		}
		return tUserDAO;
	}

	/**
	 * 
	 */
	private void getApplicationContext() {
		if (appContext == null) {
			// appContext = ApplicationContextProvider.getApplicationContext();
			appContext = new ClassPathXmlApplicationContext(
					"applicationContext.xml");
		}

	}
}
