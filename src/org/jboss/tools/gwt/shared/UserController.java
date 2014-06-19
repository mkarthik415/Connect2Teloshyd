package org.jboss.tools.gwt.shared;

import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;
import org.jboss.tools.gwt.beans.TUserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.sql.DataSource;
import java.io.*;
import java.io.File;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

//import java.sql.Date;


public class UserController implements UserControllerInterface{




    @Autowired
    private TUserDAO userDAO;

    @Autowired
    private DataSource dataSource;

    @Autowired
    private SendEmail sendEmail;

    @Autowired
    private SmsLane smsLane;

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
	ApplicationContext appContext = null;
	String report = "report";
	String renewal = "renewal";
	String pendingReport = "reportForPending";
	public java.sql.Connection con;
	Email emailId;
	Boolean filesSent;
	List<EmailedFile> emailsSent = null;
	java.sql.Date mailDate = new java.sql.Date(2013, 12, 31);
	String sMSTemplateForDocuments = null;
	//SmsLane smsLane = null;

	// logic to get the data for login from telos DB
	public Integer getUserResponse(final String user, final String password) {
		////final TUserDAO tUserDAO = screenClient();

		try {
			userResponse = this.userDAO.selectUser(user, password);
			logger.log(Level.SEVERE, "response from DB ");

		} catch (Exception e) {

			logger.log(Level.SEVERE, "Inside UserController " + e.getStackTrace().toString());
			return userResponse;
		}
		return userResponse;
	}

	// logic to put data for create new client into telos DB
	public String getCreateClientResponse(Client client) {
		//final TUserDAO tUserDAO = getUserDaoBean();

		try {
			created = userDAO.createClient(client);
		} catch (Exception e) {
			logger.log(Level.SEVERE, "Inside UserController " + e.toString());
		}

		return created;

	}

    // logic to put data for renewal of exiting client into telos DB
    public String getrenewlientResponse(Client client) {
        //final TUserDAO tUserDAO = getUserDaoBean();

        try {
            created = userDAO.renewClient(client);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Inside UserController " + e.toString());
        }

        return created;

    }

	public String updateClientResponse(Client client) {
		//final TUserDAO tUserDAO = getUserDaoBean();

		try {
			created = userDAO.updateClient(client);
		} catch (Exception e) {
			logger.log(Level.SEVERE, "Inside UserController " + e.toString());
		}

		return created;

	}

	public String createAgentResponse(Agent agent) {
		//final TUserDAO tUserDAO = getUserDaoBean();

		try {
			created = userDAO.createAgent(agent);
		} catch (Exception e) {
			logger.log(Level.SEVERE, "Inside UserController " + e.toString());
		}

		return created;

	}

	public String createInsuranceResponse(Insurance insurance) {
		//final TUserDAO tUserDAO = getUserDaoBean();

		try {
			created = userDAO.createInsurance(insurance);
		} catch (Exception e) {
			logger.log(Level.SEVERE, "Inside UserController " + e.toString());
		}

		return created;

	}

	public List<Clients> getSearchClient(Client client) {
		logger.log(Level.SEVERE,
				"Inside UserController before UserController execution");
		//final TUserDAO tUserDAO = getUserDaoBean();
		try {
			lClients = userDAO.searchClient(client);
			logger.log(Level.SEVERE,
					"Inside UserController after UserController execution");
		} catch (Exception e) {
			logger.log(Level.SEVERE, "Inside UserController " + e.toString());
		}
		return lClients;

	}

	public List<Clients> getSearchClientByCarNum(Client client) {
		//final TUserDAO tUserDAO = getUserDaoBean();
		try {
			lClients = userDAO.searchClientByCarNum(client);
			logger.log(Level.SEVERE,
					"Inside UserController after UserController execution");
		} catch (Exception e) {
			logger.log(Level.SEVERE, "Inside UserController " + e.toString());
		}
		return lClients;

	}

	public List<Clients> getSearchClientByPhoneNum(Client client) {
		//final TUserDAO tUserDAO = getUserDaoBean();
		try {
			lClients = userDAO.searchClientByPhoneNum(client);
			logger.log(Level.SEVERE,
					"Inside UserController after UserController execution");
		} catch (Exception e) {
			logger.log(Level.SEVERE, "Inside UserController " + e.toString());
		}
		return lClients;

	}

	public List<Clients> getSearchClientByPolicyDates(Client client) {
		//final TUserDAO tUserDAO = getUserDaoBean();
		try {
			lClients = userDAO.searchClientByPolicyDates(client);
			logger.log(Level.SEVERE,
					"Inside UserController after UserController execution");
		} catch (Exception e) {
			logger.log(Level.SEVERE, "Inside UserController " + e.toString());
		}
		return lClients;

	}

	public List<Clients> getSearchClientBySerialNo(Client client) {
		logger.log(Level.SEVERE,
				"Inside UserController before implementation DAO being execution");
		//final TUserDAO tUserDAO = (TUserDAO) appContext.getBean("tUserDAO");
		try {
			lClients = userDAO.searchClientBySerialNo(client);
			logger.log(Level.SEVERE,
					"Inside UserController after UserController execution");
		} catch (Exception e) {
			logger.log(Level.SEVERE, "Inside UserController " + e.toString());
		}
		return lClients;
	}

	public List<Clients> getSearchClientByPolicyNo(Client client) {
		logger.log(Level.SEVERE,
				"Inside UserController before implementation DAO being execution");
		//final TUserDAO tUserDAO = (TUserDAO) appContext.getBean("tUserDAO");
		try {
			lClients = userDAO.searchClientByPolicyNo(client);
			logger.log(Level.SEVERE,
					"Inside UserController after UserController execution");
		} catch (Exception e) {
			logger.log(Level.SEVERE, "Inside UserController " + e.toString());
		}
		return lClients;
	}

	public List<Company> getListOfCompanies() {

		logger.log(
				Level.SEVERE,
				"Inside UserController of list of companies before implementation DAO being execution");
		//final TUserDAO tUserDAO = (TUserDAO) appContext.getBean("tUserDAO");
		try {
			lCompany = userDAO.getListOfComapnies();
			logger.log(Level.SEVERE,
					"Inside UserController after UserController execution");
		} catch (Exception e) {
			logger.log(Level.SEVERE, "Inside UserController " + e.toString());
		}
		return lCompany;
	}
	
	public List<EmailList> getListOfEmails(){

		logger.log(
				Level.SEVERE,
				"Inside UserController of list of companies before implementation DAO being execution");
		//final TUserDAO tUserDAO = (TUserDAO) appContext.getBean("tUserDAO");
		try {
			lemails = userDAO.loadEmails();
			logger.log(Level.SEVERE,
					"Inside UserController after UserController execution");
		} catch (Exception e) {
			logger.log(Level.SEVERE, "Inside UserController " + e.toString());
		}
		return lemails;
		
	}
	
	public CompanyDetails getCompanyDetails(Company company){

		logger.log(
				Level.SEVERE,
				"Inside UserController of list of companies Details before implementation DAO being execution for "+company.getCompnyName());
		//final TUserDAO tUserDAO = (TUserDAO) appContext.getBean("tUserDAO");
		try {
			comapnydetails = userDAO.getCompanyDetails(company);
			logger.log(Level.SEVERE,
					"Inside UserController after UserController execution");
		} catch (Exception e) {
			logger.log(Level.SEVERE, "Inside UserController " + e.toString());
		}
		return comapnydetails;
	}

	public List<org.jboss.tools.gwt.shared.File> getUploadedDocuments(
			Client client) {

		logger.log(Level.SEVERE,
				"Inside UserController before implementation DAO for documents being execution");
		//final TUserDAO tUserDAO = (TUserDAO) appContext.getBean("tUserDAO");
		try {
			documents = userDAO.searchDocumentsByClientId(client);
			logger.log(Level.SEVERE,
					"Inside UserController after UserController execution");
		} catch (Exception e) {
			logger.log(Level.SEVERE, "Inside UserController " + e.toString());
		}
		return documents;
	}

	public Email logEmail(Email email) {

		try {
            logger.log(Level.SEVERE, "Inside UserController DAO is called for updating the database");
			emailId = this.userDAO.logEmail(email);
		} catch (Exception e) {
			logger.log(Level.SEVERE, "Inside UserController " + e.toString());
		}
		return emailId;

	}

	public Boolean logEmailedFiles(Email email, List<DocumentOnServerSide> files) {

		//final TUserDAO tUserDAO = (TUserDAO) appContext.getBean("tUserDAO");
		try {
			filesSent = userDAO.logEmailedFiles(email, files);
		} catch (Exception e) {
			logger.log(Level.SEVERE, "Inside UserController " + e.toString());
		}
		return filesSent;
	}

	public Boolean endDate(List<DocumentOnServerSide> files) {

		Boolean endDateStatus = true;
		try {
			endDateStatus = this.userDAO.endDateEmailedFiles(files);

		} catch (Exception ex) {
			logger.log(Level.SEVERE, "Inside UserController " + ex.toString());
			return false;
		}
		return endDateStatus;

	}

	public List<EmailedFile> getEmails(org.jboss.tools.gwt.shared.File file) {
		try {
			emailsSent = this.userDAO.getEmails(file);
		} catch (Exception e) {
			logger.log(Level.SEVERE, "Inside UserController " + e.toString());
		}
		return emailsSent;
	}

	public List<Agent> getSearchAgent() {
		//final TUserDAO tUserDAO = getUserDaoBean();
		try {
			lAgent = this.userDAO.searchAgent();
			System.out.println(" agent found " + lAgent.get(0).getScreenName());
		} catch (Exception e) {
			logger.log(Level.SEVERE, "Inside UserController " + e.toString());
		}
		return lAgent;

	}

	public List<Insurance> getSearchInsuranceCompany() {
		//final TUserDAO tUserDAO = getUserDaoBean();
		try {
			lInsurance = this.userDAO.searchInsuranceComapny();
			System.out.println(" agent found " + lAgent.get(0).getScreenName());
		} catch (Exception e) {
			logger.log(Level.SEVERE, "Inside UserController " + e.toString());
		}
		return lInsurance;

	}

	public List<OfficeCode> getSearchOfficeCode() {
		//final TUserDAO tUserDAO = getUserDaoBean();
		try {
			lOfficeCode = this.userDAO.searchOfficeCode();
		} catch (Exception e) {
			logger.log(Level.SEVERE, "Inside UserController " + e.toString());
		}
		return lOfficeCode;

	}

	public Boolean getEmailClient(Client client,
			List<org.jboss.tools.gwt.shared.File> files, String source)
			throws AddressException, MessagingException {
        Boolean sent =false;
		try {
			documentsBlob = userDAO.searchDocumentsByFileId(files);
			logger.log(Level.SEVERE,
					"Inside UserController after UserController execution");
		} catch (Exception e) {
			logger.log(Level.SEVERE, "Inside UserController " + e.toString());
		}

        if(source == "DOCUMENT")
        {

             sent = this.sendEmail.emailSent(client, documentsBlob);
        }
        else
        {

            lClients = userDAO.searchClientBySerialNo(client);
            List<DocumentOnServerSide> totalDocuments = searchDocumentsByClient(lClients.get(0));
             sent = this.sendEmail.sentEmailByScheduleForRenewals(lClients.get(0), totalDocuments);
        }

        return sent;
    }
	
	public Boolean deleteClientDocuments(Client client,
			List<org.jboss.tools.gwt.shared.File> files)
	{
		Boolean deleted = false;
		//final TUserDAO tUserDAO = getUserDaoBean();
		deleted = userDAO.deleteDocumentsForClients(client, files);
		return deleted;
	}

	public List<Clients> getListClientToEmail() {
		List<Clients> listClientsToEmail = null;
		//final TUserDAO tUserDAO = getUserDaoBean();
		try {
			listClientsToEmail = this.userDAO.searchClientToEmail();
		} catch (Exception e) {
			logger.log(Level.SEVERE, "Inside UserController " + e.toString());
		}
		return listClientsToEmail;
	}
	
	public Clients searchInsuranceDetailsByOfficeCode(Client client){
		Clients insuranceCompanyDetails = null;
		try {
			insuranceCompanyDetails = this.userDAO.searchInsuranceDetailsByCode(client);
			logger.log(Level.SEVERE, "Inside UserController and insurance company is  :::" + insuranceCompanyDetails.getInsCompanyName());
		} catch (Exception e) {
			logger.log(Level.SEVERE, "Inside UserController " + e.getStackTrace());
		}
		return insuranceCompanyDetails;
		
	}

	public List<DocumentOnServerSide> searchDocumentsByClient(Clients client) {
		List<DocumentOnServerSide> totalDocuments = null;
		//final TUserDAO tUserDAO = getUserDaoBean();
		try {
			totalDocuments = this.userDAO.searchDocumentsByClientIdForEmail(client);
		} catch (Exception e) {
			logger.log(Level.SEVERE, "Inside UserController " + e.toString());
		}
		return totalDocuments;
	}

	public String getSMSClient(Client client) {
		//smsLane = new SmsLane();
		String response = smsLane.SMSSender(client.getPhoneNumber(),
				client.getSmsLane(),null);
		return response;

	}


    private String templateTypeDocument = "DOCUMENTS";
    private String templateTypeRenewal = "RENEWAL";

    @Override
    public Boolean sendSMSToClient(Clients client, String templateType, Client screenClient) {

        Boolean responseByFirstPhoneNumber = false;
        Boolean responseBySecondaryPhoneNUmber = false;
        //sMSTemplateForDocuments = templateType;
        Boolean success = false;
        //SmsLane smsLane = new SmsLane();

        if(screenClient != null && client == null)
        {
            List<Clients> listOfClient=getSearchClientBySerialNo(screenClient);
            client = listOfClient.get(0);
            logger.log(Level.SEVERE, " client name is::: "+client.getName());
        }

        if (client.getPhoneNumber() != null) {

            String response = smsLane.SMSSender(client.getPhoneNumber(),
                    templateType, client);
            if (response.subSequence(17, 29).equals(client.getPhoneNumber())) {
                responseByFirstPhoneNumber = true;
                this.userDAO.logSms(client, templateType, "PRIMARY_PHONE_NUMBER");
            }
            else
                responseByFirstPhoneNumber = false;
        }
        if (client.getSecondaryPhoneNumber() != null) {
            String response = smsLane.SMSSender(
                    client.getSecondaryPhoneNumber(), templateType, client);
            if (response.subSequence(17, 29).equals(
                    client.getSecondaryPhoneNumber()) ) {
                responseBySecondaryPhoneNUmber = true;
                this.userDAO.logSms(client, templateType, "SECONDSRY_PHONE_NUMBER");
            }
            else
                responseBySecondaryPhoneNUmber = false;
        }

        if (responseByFirstPhoneNumber || responseBySecondaryPhoneNUmber) {
            success = true;
            return success;
        } else {

            return success;
        }

    }

	public String getPdfReport(String input,
                               Map<String, Object> parameters) throws SQLException {
		String all = "All";
		try {
			java.sql.Connection con = this.dataSource.getConnection();
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
		String all = "All";
        try {
            logger.log(Level.SEVERE,
                    "Inside UserController for excel report the path of the file is "+input);
			java.sql.Connection con = this.dataSource.getConnection();
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
		try {
			java.sql.Connection con = this.dataSource.getConnection();
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
		String all = "All";
		try {
			java.sql.Connection con = this.dataSource.getConnection();
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
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "resources/Reports/client.xls";

	}

	public String getPdfReportForPendingPolicy(String input,
			Map<String, Object> parameters) {
		try {
			java.sql.Connection con = this.dataSource.getConnection();
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
		String all = "All";
		try {
			java.sql.Connection con = this.dataSource.getConnection();
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

			java.sql.Connection con = downloadDocuments();
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
		logger.log(Level.SEVERE,
				"Inside UserController for download documents.");
		try {
			getApplicationContext();
			DataSource ds = (DataSource) appContext.getBean("dataSourceForStandAlone");

			java.sql.Connection con = ds.getConnection();
			return con;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		logger.log(Level.SEVERE, "Data Connection created");
		return con;
	}

    @Override
    public String getFileForDisplay(String id, String filePath) {
        documentsBlob = userDAO.searchDocumentsById(id);
        logger.log(Level.SEVERE,
                "Inside UserController after UserController execution");
        File file = new File(filePath+"/"+(documentsBlob.get(0).getName()));
        try {
            FileOutputStream fos = new FileOutputStream(file);
            InputStream in = documentsBlob.get(0).getScanned().getBinaryStream();
            int fileLength = in.available();
            int bytesRead = -1;
            byte[] buffer = new byte[fileLength];
            while ((bytesRead = in.read(buffer)) != -1) {
                fos.write(buffer, 0, bytesRead);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "resources/Reports/"+(documentsBlob.get(0).getName());
    }

    /**
     *
     */
    private void getApplicationContext() {
        if (appContext == null) {
            // appContext = ApplicationContextProvider.getApplicationContext();
            appContext = new ClassPathXmlApplicationContext("Spring-Quartz.xml");
        }

    }

}
