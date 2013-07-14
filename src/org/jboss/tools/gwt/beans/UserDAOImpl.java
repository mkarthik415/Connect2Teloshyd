package org.jboss.tools.gwt.beans;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpSession;

import org.jboss.tools.gwt.mapping.AgentMapper;
import org.jboss.tools.gwt.mapping.ClientMapper;
import org.jboss.tools.gwt.mapping.ComapnyMapper;
import org.jboss.tools.gwt.mapping.FileMapper;
import org.jboss.tools.gwt.mapping.InsuranceMapper;
import org.jboss.tools.gwt.mapping.OfficeCodeMapper;
import org.jboss.tools.gwt.mapping.UserMapper;
import org.jboss.tools.gwt.shared.Agent;
import org.jboss.tools.gwt.shared.Client;
import org.jboss.tools.gwt.shared.Clients;
import org.jboss.tools.gwt.shared.Company;
import org.jboss.tools.gwt.shared.File;
import org.jboss.tools.gwt.shared.Insurance;
import org.jboss.tools.gwt.shared.OfficeCode;
import org.jboss.tools.gwt.shared.User;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;

import com.extjs.gxt.ui.client.Registry;

public class UserDAOImpl extends NamedParameterJdbcDaoSupport implements
		TUserDAO {

	MapSqlParameterSource namedParameters = null;
	MapSqlParameterSource searchClientParameters = null;
	Logger logger = Logger.getLogger("logger");

	@SuppressWarnings("unchecked")
	@Override
	public Integer selectUser(String user, String password) {
		Boolean userFound = false;
		Logger logger = Logger.getLogger("logger");
		logger.log(Level.SEVERE, "inside implemntation method");
		namedParameters = new MapSqlParameterSource();
		namedParameters.addValue("username", user);
		namedParameters.addValue("password", password);

		logger.log(Level.SEVERE, "before query being executed");
		try {
			returnUsers = this.getNamedParameterJdbcTemplate().query(
					GET_USER_SQL, namedParameters, new UserMapper());
			logger.log(Level.SEVERE, "After query being executed"
					+ returnUsers.get(0).getTeam());
			userFound = true;
		} catch (Exception ex) {
			logger.log(Level.SEVERE, "User Not Found " + ex.toString());
			return null;
		}
		return returnUsers.get(0).getTeam();

	}

	private static String GET_USER_SQL = getProperty("GET_BENEFITS_SQL");

	protected static String getProperty(String key) {
		String filename = "org/jboss/tools/gwt/beans/benefits.properties";
		// FileInputStream fis;
		Properties properties = new Properties();

		// InputStream stream =
		// loader.getResourceAsStream("/myProp.properties");

		InputStream configStream = Thread.currentThread()
				.getContextClassLoader().getResourceAsStream(filename);

		try {
			// fis = new FileInputStream(new File(filename));
			properties.load(configStream);

		} catch (FileNotFoundException e) {
			// logger.error(e);
		} catch (IOException e) {
			// logger.error(e);
		}
		return properties.getProperty(key);

	}

	List<User> returnUsers = null;
	String clientCreate = null;
	String clientUpdate = null;
	int i;

	@Override
	public String createClient(Client client) {
		Logger logger = Logger.getLogger("logger");
		logger.log(Level.SEVERE, "inside implemntation method");
		try {
			namedParameters = new MapSqlParameterSource();
			namedParameters.addValue("clientName", client.getClientName());
			namedParameters.addValue("phoneNumber", client.getPhoneNumber());
			namedParameters.addValue("dateOfBirth", client.getDob());
			namedParameters.addValue("company", client.getCompany());
			namedParameters.addValue("eMail", client.getEmail());
			namedParameters.addValue("gender", client.getGender());
			namedParameters.addValue("industry", client.getIndustry());
			namedParameters.addValue("address", client.getAddress());
			namedParameters.addValue("policyNumber", client.getPolicyNumber());
			namedParameters.addValue("endrsNumber", client.getEndrsNumber());
			namedParameters.addValue("insCompanyName",
					client.getInsCompanyName());
			namedParameters
					.addValue("insBranchName", client.getInsBranchName());
			namedParameters.addValue("policyStartdate",
					client.getPolicyStartdate());
			namedParameters
					.addValue("policyEndDate", client.getPolicyEndDate());
			namedParameters.addValue("officeCode", client.getOfficeCode());
			namedParameters.addValue("source", client.getSource());
			namedParameters
					.addValue("policyDetails", client.getPolicyDetails());
			namedParameters.addValue("agent", client.getAgent());
			namedParameters.addValue("policyType", client.getPolicyType());
			namedParameters
					.addValue("premiumAmount", client.getPremiumAmount());
			namedParameters.addValue("terrorismPremiumAmount",
					client.getTerrorismPremiumAmount());
			namedParameters.addValue("serviceTaxPercentage", client.getServiceTax());
			namedParameters.addValue("serviceTax", client.getServiceTaxAmount());
			namedParameters.addValue("totalPremiumAmount",
					client.getTotalPremiumAmount());
			namedParameters.addValue("commionRate", client.getCommionRate());
			namedParameters.addValue("commionRateAmount",
					client.getCommionRateAmount());

			if (client.getMiscTypeOfPolicy() != null) {
				logger.log(Level.SEVERE,
						"inside implemntation method when creating a new client "
								+ client.getMiscTypeOfPolicy());
				namedParameters.addValue("fireTypeOfPolicy",
						client.getMiscTypeOfPolicy());
			} else if (client.getFireTypeOfPolicy() != null) {
				namedParameters.addValue("fireTypeOfPolicy",
						client.getFireTypeOfPolicy());
			} else
				namedParameters.addValue("fireTypeOfPolicy",
						client.getMiscTypeOfPolicy());
			namedParameters.addValue("marineTypeOfPolicy",
					client.getMarineTypeOfPolicy());
			namedParameters.addValue("marineOpenPolicy",
					client.getMarineOpenPolicy());
			namedParameters.addValue("marineOpenCover",
					client.getMarineOpenCover());
			namedParameters.addValue("marineOtherPolicies",
					client.getMarineOtherPolicies());
			namedParameters.addValue("marineVoyageFrom",
					client.getMarineVoyageFrom());
			namedParameters.addValue("marineVoyageTo",
					client.getMarineVoyageTo());
			namedParameters.addValue("basicRate", client.getBasicRate());
			namedParameters.addValue("earthQuakePremium",
					client.getEarthQuakePremium());
			namedParameters.addValue("anyAdditionalPremium",
					client.getAnyAdditionalPremium());
			namedParameters.addValue("sumInsured", client.getSumInsured());
			namedParameters.addValue("collectionDate",
					client.getCollectionDate());
			namedParameters
					.addValue("vehicleNumber", client.getVehicleNumber());
			namedParameters.addValue("vehicleMake", client.getVehicleMake());
			namedParameters.addValue("vehicleManufactureYear",
					client.getVehicleManufactureYear());
			namedParameters.addValue("nBC", client.getnBC());
			namedParameters.addValue("department", client.getDepartment());
			namedParameters.addValue("iDV", client.getiDV());
			namedParameters.addValue("iDCard",
					client.getMiscIdCard());
			namedParameters.addValue("miscDispatchDate",
					client.getMiscDispatchDate());
		} catch (Exception e) {
			logger.log(Level.SEVERE, "named parameters issue " + e.toString());
		}
		logger.log(Level.SEVERE, "before query being executed");
		try {

			this.getNamedParameterJdbcTemplate().update(CREATE_CLIENT,
					namedParameters);
			i = this.getJdbcTemplate().queryForInt(
					"select max(id) from test_prefixTELOS");
			// String.valueOf(i);
			logger.log(Level.SEVERE, "query exceuted" + i);
		} 
		catch (DuplicateKeyException e) {
			logger.log(Level.SEVERE,
					"After query being executed exception found  " + e);
			return "same";
			}
		catch (Exception e) {
			logger.log(Level.SEVERE,
					"After query being executed exception found  " + e);
			return clientCreate;
		}

		return String.valueOf(i);
	}

	private static String CREATE_CLIENT = getProperty("CREATE_CLIENT_SQL");

	private static String UPDATE_CLIENT = getProperty("UPDATE_CLIENT_SQL");

	private static String CREATE_AGENT = getProperty("CREATE_AGENT_SQL");

	private static String GET_AGENT_SQL = getProperty("GET_AGENT_SQL");
	
	private static String CREATE_INSURANCE = getProperty("CREATE_INSURANCE_SQL");
	
	private static String GET_INSURANCE_SQL = getProperty("GET_INSURANCE_SQL");
	
	private static String GET_OFFICE_CODE_SQL = getProperty("GET_OFFICE_CODE_SQL");
	
	private static String GET_CLIENT_BY_CAR_NUM_SQL = getProperty("GET_CLIENT_BY_CAR_NUM_SQL");
	
	private static String GET_CLIENT_BY_POLICY_DATE_SQL = getProperty("GET_CLIENT_BY_POLICY_DATE_SQL");
	
	private static String GET_CLIENT_BY_SERIAL_NO_SQL = getProperty("GET_CLIENT_BY_SERIAL_NO_SQL");
	
	private static String GET_CLIENT_BY_POLICY_NO_SQL = getProperty("GET_CLIENT_BY_POLICY_NO_SQL");
	
	private static String GET_DOCUMENTS_BY_CLIENT_ID = getProperty("GET_DOCUMENTS_BY_CLIENT_ID");
	
	private static String GET_CLENTS_SQL = getProperty("GET_CLIENT_SQL");
	
	List<Clients> returnClients = null;
	
	List<File> returnFiles = null;

	@SuppressWarnings("unchecked")
	@Override
	public List<Clients> searchClient(Client client) {
		Logger logger = Logger.getLogger("logger");
		logger.log(Level.SEVERE, "inside search implemntation method by name");
		searchClientParameters = new MapSqlParameterSource();
		searchClientParameters.addValue("clientName", client.getClientName());
		logger.log(Level.INFO, "before seach query being executed by name");
		try {
			returnClients = this.getNamedParameterJdbcTemplate().query(
					GET_CLENTS_SQL, searchClientParameters, new ClientMapper());
			logger.log(Level.SEVERE, "After query being executed "
					+returnClients.get(0).getId() + " ID is:::::::"
					+ returnClients.get(0).getName() + " agent name, StartDate:::"
					+ returnClients.get(0).getPolicyStartdate());
		} catch (Exception ex) {
			logger.log(Level.SEVERE, "User Not Found " + ex.toString());
			return null;
		}
		return returnClients;
	}


	@Override
	public String updateClient(Client client) {
		// TODO Auto-generated method stub

		Logger logger = Logger.getLogger("logger");
		logger.log(Level.SEVERE, "inside implemntation method");
		try {
			namedParameters = new MapSqlParameterSource();
			namedParameters.addValue("clientName", client.getClientName());
			namedParameters.addValue("phoneNumber", client.getPhoneNumber());
			namedParameters.addValue("dateOfBirth", client.getDob());
			namedParameters.addValue("company", client.getCompany());
			namedParameters.addValue("eMail", client.getEmail());
			namedParameters.addValue("gender", client.getGender());
			namedParameters.addValue("industry", client.getIndustry());
			namedParameters.addValue("address", client.getAddress());
			namedParameters.addValue("policyNumber", client.getPolicyNumber());
			namedParameters.addValue("endrsNumber", client.getEndrsNumber());
			namedParameters.addValue("insCompanyName",
					client.getInsCompanyName());
			namedParameters
					.addValue("insBranchName", client.getInsBranchName());
			namedParameters.addValue("policyStartdate",
					client.getPolicyStartdate());
			namedParameters
					.addValue("policyEndDate", client.getPolicyEndDate());
			namedParameters.addValue("officeCode", client.getOfficeCode());
			namedParameters.addValue("source", client.getSource());
			namedParameters
					.addValue("policyDetails", client.getPolicyDetails());
			namedParameters.addValue("agent", client.getAgent());
			namedParameters.addValue("policyType", client.getPolicyType());
			namedParameters
					.addValue("premiumAmount", client.getPremiumAmount());
			namedParameters.addValue("terrorismPremiumAmount",
					client.getTerrorismPremiumAmount());
			namedParameters.addValue("serviceTaxPercentage", client.getServiceTax());
			namedParameters.addValue("serviceTax", client.getServiceTaxAmount());
			namedParameters.addValue("totalPremiumAmount",
					client.getTotalPremiumAmount());
			namedParameters.addValue("commionRate", client.getCommionRate());
			namedParameters.addValue("commionRateAmount",
					client.getCommionRateAmount());

			if (client.getMiscTypeOfPolicy() != null || client.getMiscIdCard() != null || client.getMiscDispatchDate() != null) {
				logger.log(Level.SEVERE,
						"inside implemntation method when creating a new client "
								+ client.getMiscTypeOfPolicy());
				namedParameters.addValue("fireTypeOfPolicy",
						client.getMiscTypeOfPolicy());
			} else if (client.getFireTypeOfPolicy() != null) {
				namedParameters.addValue("fireTypeOfPolicy",
						client.getFireTypeOfPolicy());
			} else
				namedParameters.addValue("fireTypeOfPolicy",
						client.getMiscTypeOfPolicy());
			namedParameters.addValue("marineTypeOfPolicy",
					client.getMarineTypeOfPolicy());
			namedParameters.addValue("marineOpenPolicy",
					client.getMarineOpenPolicy());
			namedParameters.addValue("marineOpenCover",
					client.getMarineOpenCover());
			namedParameters.addValue("marineOtherPolicies",
					client.getMarineOtherPolicies());
			namedParameters.addValue("marineVoyageFrom",
					client.getMarineVoyageFrom());
			namedParameters.addValue("marineVoyageTo",
					client.getMarineVoyageTo());
			namedParameters.addValue("basicRate", client.getBasicRate());
			namedParameters.addValue("earthQuakePremium",
					client.getEarthQuakePremium());
			namedParameters.addValue("anyAdditionalPremium",
					client.getAnyAdditionalPremium());
			namedParameters.addValue("sumInsured", client.getSumInsured());
			namedParameters.addValue("collectionDate",
					client.getCollectionDate());
			namedParameters
					.addValue("vehicleNumber", client.getVehicleNumber());
			namedParameters.addValue("vehicleMake", client.getVehicleMake());
			namedParameters.addValue("vehicleManufactureYear",
					client.getVehicleManufactureYear());
			namedParameters.addValue("nBC", client.getnBC());
			namedParameters.addValue("department", client.getDepartment());
			namedParameters.addValue("iDV", client.getiDV());
			namedParameters.addValue("id", client.getId());
			namedParameters.addValue("iDCard",
					client.getMiscIdCard());
			namedParameters.addValue("miscDispatchDate",
					client.getMiscDispatchDate());
			// logger.log(Level.SEVERE, "named parameters issue " +
			// e.toString());
		} catch (Exception e) {
			logger.log(Level.SEVERE,
					"named parameters issue for update" + e.toString());
		}
		logger.log(Level.SEVERE, "before query being executed");
		try {

			this.getNamedParameterJdbcTemplate().update(UPDATE_CLIENT,
					namedParameters);
			i = this.getJdbcTemplate().queryForInt(
					"select count(0) from test_prefixTELOS");
			// String.valueOf(i);
			logger.log(Level.SEVERE, "query exceuted" + i);
		}		
		catch (DuplicateKeyException e) {
			logger.log(Level.SEVERE,
					"After query being executed exception found  " + e);
			return "same";
			} 
		catch (Exception e) {
			logger.log(Level.SEVERE,
					"After query being executed exception found  " + e);
			return clientUpdate;
		}

		return String.valueOf(i);
	}

	@Override
	public String createAgent(Agent agent) {

		// TODO Auto-generated method stub
		Logger logger = Logger.getLogger("logger");
		logger.log(Level.SEVERE, "inside implemntation method");
		try {
			namedParameters = new MapSqlParameterSource();
			namedParameters.addValue("name", agent.getName());
			namedParameters.addValue("screenName", agent.getScreenName());
		} catch (Exception e) {
			logger.log(Level.SEVERE, "named parameters issue " + e.toString());
		}
		logger.log(Level.SEVERE, "before query being executed");
		try {

			this.getNamedParameterJdbcTemplate().update(CREATE_AGENT,
					namedParameters);
			i = this.getJdbcTemplate()
					.queryForInt("select count(0) from agent");
			// String.valueOf(i);
			logger.log(Level.SEVERE, "query exceuted" + i);
		} catch (Exception e) {
			logger.log(
					Level.SEVERE,
					"After query being executed exception found  "
							+ e.toString());
			return clientCreate;
		}

		return String.valueOf(i);

	}
	
	@Override
	public String createInsurance(Insurance insurance) {

		// TODO Auto-generated method stub
		Logger logger = Logger.getLogger("logger");
		logger.log(Level.SEVERE, "inside implemntation method");
		try {
			namedParameters = new MapSqlParameterSource();
			namedParameters.addValue("name", insurance.getName());
			namedParameters.addValue("screenName", insurance.getScreenName());
		} catch (Exception e) {
			logger.log(Level.SEVERE, "named parameters issue " + e.toString());
		}
		logger.log(Level.SEVERE, "before query being executed");
		try {

			this.getNamedParameterJdbcTemplate().update(CREATE_INSURANCE,
					namedParameters);
			i = this.getJdbcTemplate()
					.queryForInt("select count(0) from agent");
			// String.valueOf(i);
			logger.log(Level.SEVERE, "query exceuted" + i);
		} catch (Exception e) {
			logger.log(
					Level.SEVERE,
					"After query being executed exception found  "
							+ e.toString());
			return clientCreate;
		}

		return String.valueOf(i);

	} 

	List<Agent> lAgent = null;

	@SuppressWarnings("unchecked")
	@Override
	public List<Agent> searchAgent() {
		Logger logger = Logger.getLogger("logger");
		logger.log(Level.SEVERE, "inside search implemntation method");
		try {

			lAgent = this.getJdbcTemplate().query(GET_AGENT_SQL,
					new AgentMapper());
			System.out.println(" agent found " + lAgent.get(0).getScreenName());
		} catch (Exception e) {
			logger.log(Level.SEVERE,
					"After query being executed exception found  " + e);
			return lAgent;
		}
		return lAgent;
	}

	List<OfficeCode> lOfficeCode = null;
	@SuppressWarnings("unchecked")
	@Override
	public List<OfficeCode> searchOfficeCode() {
		try{
			lOfficeCode = this.getJdbcTemplate().query(GET_OFFICE_CODE_SQL, new OfficeCodeMapper());
		}catch (Exception e) {
			logger.log(Level.SEVERE,
					"After query being executed exception found  " + e);
			return lOfficeCode;
		}
		return lOfficeCode;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Clients> searchClientByCarNum(Client client) {

		logger.log(Level.SEVERE, "inside search implemntation method");
		searchClientParameters = new MapSqlParameterSource();
		searchClientParameters.addValue("clientName", client.getVehicleNumber());
		logger.log(Level.INFO, "before seach query being executed");
		try {
			returnClients = this.getNamedParameterJdbcTemplate().query(
					GET_CLIENT_BY_CAR_NUM_SQL, searchClientParameters, new ClientMapper());
			logger.log(Level.SEVERE, "After query being executed"
					+ returnClients.get(0).getName() + "agent name "
					+ returnClients.get(0).getAgent());
		} catch (Exception ex) {
			logger.log(Level.SEVERE, "User Not Found " + ex.toString());
			return null;
		}
		return returnClients;
	
	}
	
	

	@SuppressWarnings("unchecked")
	@Override
	public List<Clients> searchClientByPolicyDates(Client client) {
		logger.log(Level.SEVERE, "inside search implemntation method");
		searchClientParameters = new MapSqlParameterSource();
		searchClientParameters
				.addValue("clientFromDate", client.getFromDate());
		searchClientParameters
		.addValue("clientToDate", client.getToDate());
		logger.log(Level.INFO, "before seach query being executed");
		try {
			returnClients = this.getNamedParameterJdbcTemplate().query(
					GET_CLIENT_BY_POLICY_DATE_SQL, searchClientParameters,
					new ClientMapper());
			logger.log(Level.SEVERE, "After query being executed"
					+ returnClients.get(0).getName() + "agent name "
					+ returnClients.get(0).getAgent());
		} catch (Exception ex) {
			logger.log(Level.SEVERE, "User Not Found " + ex.toString());
			return null;
		}
		return returnClients;
	}
	
	

	@SuppressWarnings("unchecked")
	@Override
	public List<Clients> searchClientBySerialNo(Client client) {
		logger.log(Level.SEVERE, "inside search implemntation method by serial number");
		searchClientParameters = new MapSqlParameterSource();
		searchClientParameters
				.addValue("clientId", client.getId());
		logger.log(Level.INFO, "before seach query being executed for sserial number search");
		try {
			returnClients = this.getNamedParameterJdbcTemplate().query(
					GET_CLIENT_BY_SERIAL_NO_SQL, searchClientParameters,
					new ClientMapper());
			logger.log(Level.SEVERE, "After query being executed ID:::::"
					+ returnClients.get(0).getId() + "agent name "
					+ returnClients.get(0).getAgent());
		} catch (Exception ex) {
			logger.log(Level.SEVERE, "User Not Found " + ex.toString());
			return null;
		}
		return returnClients;
	}
	
	

	@SuppressWarnings("unchecked")
	@Override
	public List<Clients> searchClientByPolicyNo(Client client) {
		logger.log(Level.SEVERE, "inside search implemntation method by serial number");
		searchClientParameters = new MapSqlParameterSource();
		searchClientParameters
				.addValue("clientPolicyNo", client.getPolicyNumber());
		logger.log(Level.INFO, "before seach query being executed for policy/certificate number search");
		try {
			returnClients = this.getNamedParameterJdbcTemplate().query(
					GET_CLIENT_BY_POLICY_NO_SQL, searchClientParameters,
					new ClientMapper());
			logger.log(Level.SEVERE, "After query being executed ID:::::"
					+ returnClients.get(0).getId() + " StartDate "
					+ returnClients.get(0).getAgent());
		} catch (Exception ex) {
			logger.log(Level.SEVERE, "User Not Found " + ex.toString());
			return null;
		}
		return returnClients;
	}
	
	
	
	List<Company> lCompany = null;
	@SuppressWarnings("unchecked")
	@Override
	public List<Company> getListOfComapnies() {
		logger.log(Level.SEVERE, "inside get comapnies list  implemntation method");
		searchClientParameters = new MapSqlParameterSource();
		try {
			lCompany = this.getJdbcTemplate().query(
					GET_COMPANY_SQL,
					new ComapnyMapper());
			logger.log(Level.SEVERE, "After query being company list is :::::"+lCompany.get(0).getCompnyName());
		} catch (Exception ex) {
			logger.log(Level.SEVERE, "companies not found " + ex.toString());
			return null;
		}
		return lCompany;
	}
	
	
	private static String GET_COMPANY_SQL = getProperty("GET_COMPANY_SQL");
	
	List<Insurance> lInsurance = null;
	@SuppressWarnings("unchecked")
	@Override
	public List<Insurance> searchInsuranceComapny() {

		Logger logger = Logger.getLogger("logger");
		logger.log(Level.SEVERE, "inside search implemntation method");
		try {

			lInsurance = this.getJdbcTemplate().query(GET_INSURANCE_SQL,
					new InsuranceMapper());
			System.out.println(" agent found " + lInsurance.get(0).getScreenName());
		} catch (Exception e) {
			logger.log(Level.SEVERE,
					"After query being executed exception found  " + e);
			return lInsurance;
		}
		return lInsurance;
	
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<File> searchDocumentsByClientId(Client client) {
		logger.log(Level.SEVERE, "inside search implemntation method by serial number");
		searchClientParameters = new MapSqlParameterSource();
		searchClientParameters
				.addValue("clientId", client.getId());
		logger.log(Level.INFO, "before documents seach query being executed for client id");
		try {
			returnFiles = this.getNamedParameterJdbcTemplate().query(
					GET_DOCUMENTS_BY_CLIENT_ID, searchClientParameters,
					new FileMapper());
			logger.log(Level.SEVERE, "After query being executed ID:::::");
		} catch (Exception ex) {
			logger.log(Level.SEVERE, "User Not Found " + ex.toString());
			return null;
		}
		return returnFiles;
	}


}
