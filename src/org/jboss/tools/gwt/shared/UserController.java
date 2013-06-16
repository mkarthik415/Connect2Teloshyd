package org.jboss.tools.gwt.shared;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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

import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;

import org.jboss.tools.gwt.beans.TUserDAO;
import org.springframework.context.ApplicationContext;

//import java.sql.Date;

public class UserController {

	@SuppressWarnings("unused")
	private String uname;
	Logger logger = Logger.getLogger("logger");
	Integer userResponse = null;
	String created = null;
	List<Clients> lClients = null;
	List<Company> lCompany = null;
	List<Agent> lAgent = null;
	List<Insurance> lInsurance = null;
	List<OfficeCode> lOfficeCode = null;
	ApplicationContext appContext = null;
	String report = "/resources/Reports/report";
	String renewal = "/resources/Reports/renewal";
	public java.sql.Connection con;

	// logic to get the data for login from telos DB
	public Integer getUserResponse(final String user, final String password) {
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

	public String createInsuranceResponse(Insurance insurance) {
		appContext = ApplicationContextProvider.getApplicationContext();

		final TUserDAO tUserDAO = (TUserDAO) appContext.getBean("tUserDAO");

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

	public List<Clients> getSearchClientByCarNum(Client client) {
		appContext = ApplicationContextProvider.getApplicationContext();
		final TUserDAO tUserDAO = (TUserDAO) appContext.getBean("tUserDAO");
		try {
			lClients = tUserDAO.searchClientByCarNum(client);
			logger.log(Level.SEVERE,
					"Inside UserController after UserController execution");
		} catch (Exception e) {
			logger.log(Level.SEVERE, "Inside UserController " + e.toString());
		}
		return lClients;

	}

	public List<Clients> getSearchClientByPolicyDates(Client client) {
		appContext = ApplicationContextProvider.getApplicationContext();
		final TUserDAO tUserDAO = (TUserDAO) appContext.getBean("tUserDAO");
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
		appContext = ApplicationContextProvider.getApplicationContext();
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
		appContext = ApplicationContextProvider.getApplicationContext();
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
		appContext = ApplicationContextProvider.getApplicationContext();
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

	public List<Insurance> getSearchInsuranceCompany() {
		appContext = ApplicationContextProvider.getApplicationContext();
		final TUserDAO tUserDAO = (TUserDAO) appContext.getBean("tUserDAO");
		try {
			lInsurance = tUserDAO.searchInsuranceComapny();
			System.out.println(" agent found " + lAgent.get(0).getScreenName());
		} catch (Exception e) {
			logger.log(Level.SEVERE, "Inside UserController " + e.toString());
		}
		return lInsurance;

	}

	public List<OfficeCode> getSearchOfficeCode() {
		appContext = ApplicationContextProvider.getApplicationContext();
		final TUserDAO tUserDAO = (TUserDAO) appContext.getBean("tUserDAO");
		try {
			lOfficeCode = tUserDAO.searchOfficeCode();
			System.out.println(" agent found "
					+ lOfficeCode.get(0).getCompanyOfficeCode());
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

	public String getPdfReportForIRDA(String input,
			Map<String, Object> parameters) {
		String all = "All";
		appContext = ApplicationContextProvider.getApplicationContext();
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
			String newFileName = input + ".pdf";
			JasperExportManager.exportReportToPdfFile(print, newFileName);
			if (input.contains(report)) {
				return "resources/Reports/report.pdf";
			} else if (input.contains(renewal)) {
				return "resources/Reports/renewal.pdf";
			}
		} catch (SQLException e) {
			System.out.println("" + e.toString());
			return "SQL Exception" + e.toString();
		} catch (Exception e) {

			System.out.println("" + e.toString());
			return "Exception" + e.toString();
		}

		return "/Reports/report.pdf";

	}

	public String getExcelReportForIRDA(String input,
			Map<String, Object> parameters) {
		appContext = ApplicationContextProvider.getApplicationContext();
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
			if (input.contains(report)) {
				return "resources/Reports/report.xls";
			} else if (input.contains(renewal)) {
				return "resources/Reports/renewal.xls";
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "resources/Reports/report.xls";

	}

	public String getPdfReportForClient(String input,
			Map<String, Object> parameters) {
		appContext = ApplicationContextProvider.getApplicationContext();
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
		appContext = ApplicationContextProvider.getApplicationContext();
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
		String all = "All";
		appContext = ApplicationContextProvider.getApplicationContext();
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
			String newFileName = input + ".pdf";
			JasperExportManager.exportReportToPdfFile(print, newFileName);
			return "resources/Reports/policy.pdf";

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
		appContext = ApplicationContextProvider.getApplicationContext();
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

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "resources/Reports/policy.xls";

	}
	
	//methods returns boolean after uploading PDF Document
	public Boolean insertDocumentToDB(String clientId,InputStream inputStream,String name)
	{
		try {
			logger.log(Level.SEVERE, "Inside UserController ");
			appContext = ApplicationContextProvider.getApplicationContext();
			DataSource ds = (DataSource) appContext.getBean("dataSource");
			java.sql.Connection con = ds.getConnection();
			logger.log(Level.SEVERE, "Data Connection created");
			PreparedStatement psmnt = (PreparedStatement) 
			        (con).prepareStatement("INSERT  INTO scan(client_id,scanned,name) VALUES  (?,?,?)" );
			psmnt.setString(1, "1200021");
			psmnt.setBinaryStream(2, inputStream);
			psmnt.setString(3, name);
			psmnt.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			logger.log(Level.SEVERE, "Inside UserController " + e.toString());
		}
		return false;
	}
}
