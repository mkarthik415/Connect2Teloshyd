package org.jboss.tools.gwt.beans;

import org.jboss.tools.gwt.mapping.*;
import org.jboss.tools.gwt.shared.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;


public class UserDAOImpl extends NamedParameterJdbcDaoSupport implements
		TUserDAO {

    NamedParameterJdbcTemplate namedParameterJdbcTemplate  = null;
    JdbcTemplate jdbcTemplate = null;
    @Autowired
    protected UserDAOImpl(DataSource dataSource)
    {
        super();
        setDataSource(dataSource);
        namedParameterJdbcTemplate = getNamedParameterJdbcTemplate();
        jdbcTemplate = getJdbcTemplate();
    }

    MapSqlParameterSource namedParameters = null;
	MapSqlParameterSource searchClientParameters = null;
	Logger logger = Logger.getLogger("logger");
	Set<Integer> ids = null;
	private User user;
	private Integer userId = null;
	private String userName = null;
	Integer systemId = 1;

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
			returnUsers = namedParameterJdbcTemplate.query(
					GET_USER_SQL, namedParameters, new UserMapper());
			logger.log(Level.SEVERE, "After query being executed"
					+ returnUsers.get(0).getTeam());
			logger.log(Level.SEVERE, "After query being executed"
					+ returnUsers.get(0).getId());
			this.user = returnUsers.get(0);
			this.userName = user;
			this.userId = returnUsers.get(0).getId();
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
			namedParameters.addValue("secondaryPhoneNumber",
					client.getSecondaryPhoneNumber());
			namedParameters.addValue("dateOfBirth", client.getDob());
			namedParameters.addValue("company", client.getCompany());
			namedParameters.addValue("eMail", client.getEmail());
			namedParameters.addValue("secondaryEmail",
					client.getSecondaryEmail());
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
			namedParameters.addValue("serviceTaxPercentage",
					client.getServiceTax());
			namedParameters
					.addValue("serviceTax", client.getServiceTaxAmount());
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
			namedParameters.addValue("iDCard", client.getMiscIdCard());
			namedParameters.addValue("miscDispatchDate",
					client.getMiscDispatchDate());
			namedParameters.addValue("userName", this.userName);
		} catch (Exception e) {
			logger.log(Level.SEVERE, "named parameters issue " + e.toString());
		}
		logger.log(Level.SEVERE, "before query being executed");
		try {

			namedParameterJdbcTemplate.update(CREATE_CLIENT,
					namedParameters);
			i = jdbcTemplate.queryForInt(
					"select max(id) from test_prefixTELOS");
			// String.valueOf(i);
			logger.log(Level.SEVERE, "query exceuted" + i);
		} catch (DuplicateKeyException e) {
			logger.log(Level.SEVERE,
					"After query being executed exception found  " + e);
			return "same";
		} catch (Exception e) {
			logger.log(Level.SEVERE,
					"After query being executed exception found  " + e);
			return clientCreate;
		}

		return String.valueOf(i);
	}

    @Override
    public String renewClient(Client client) {
        Logger logger = Logger.getLogger("logger");
        logger.log(Level.SEVERE, "inside implemntation method");
        try {
            namedParameters = new MapSqlParameterSource();
            namedParameters.addValue("clientName", client.getClientName());
            namedParameters.addValue("phoneNumber", client.getPhoneNumber());
            namedParameters.addValue("secondaryPhoneNumber",
                    client.getSecondaryPhoneNumber());
            namedParameters.addValue("dateOfBirth", client.getDob());
            namedParameters.addValue("company", client.getCompany());
            namedParameters.addValue("eMail", client.getEmail());
            namedParameters.addValue("secondaryEmail",
                    client.getSecondaryEmail());
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
            namedParameters.addValue("serviceTaxPercentage",
                    client.getServiceTax());
            namedParameters
                    .addValue("serviceTax", client.getServiceTaxAmount());
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
            namedParameters.addValue("iDCard", client.getMiscIdCard());
            namedParameters.addValue("miscDispatchDate",
                    client.getMiscDispatchDate());
            namedParameters.addValue("userName", this.userName);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "named parameters issue " + e.toString());
        }
        logger.log(Level.SEVERE, "before query being executed");
        try {

            namedParameterJdbcTemplate.update(CREATE_CLIENT,
                    namedParameters);
            i = jdbcTemplate.queryForInt(
                    "select max(id) from test_prefixTELOS");
            // String.valueOf(i);
            logger.log(Level.SEVERE, "query exceuted" + i);

            client.setId(String.valueOf(i));

        } catch (DuplicateKeyException e) {
            logger.log(Level.SEVERE,
                    "After query being executed exception found  " + e);
            return "same";
        } catch (Exception e) {
            logger.log(Level.SEVERE,
                    "After query being executed exception found  " + e);
            return clientCreate;
        }

        Boolean recorded = recordRenewal(client);
        if(recorded)
        {

            return String.valueOf(i);
        }
        else
            return null;
    }

    protected Boolean recordRenewal(Client client)
    {
        Logger logger = Logger.getLogger("logger");
        logger.log(Level.SEVERE, "inside method for recording policy renewals");
        try {
            namedParameters = new MapSqlParameterSource();
            namedParameters.addValue("expiredId", client.getExpiredId());
            namedParameters.addValue("renewedId", client.getId());
        }
        catch (Exception e)
        {
            logger.log(Level.SEVERE, "named parameters issue " + e.toString());
        }
        logger.log(Level.SEVERE, "before query being executed");
        try {

            namedParameterJdbcTemplate.update(POLICY_RENEWAL,
                    namedParameters);
        } catch (DuplicateKeyException e) {
            logger.log(Level.SEVERE,
                    "After query being executed exception found  " + e);
            return Boolean.FALSE;
        } catch (Exception e) {
            logger.log(Level.SEVERE,
                    "After query being executed exception found  " + e);
            return Boolean.FALSE;
        }

        return Boolean.TRUE;
    }

	private static String CREATE_CLIENT = getProperty("CREATE_CLIENT_SQL");

    private static String POLICY_RENEWAL = getProperty("POLICY_RENEWAL");

	private static String UPDATE_CLIENT = getProperty("UPDATE_CLIENT_SQL");

	private static String CREATE_AGENT = getProperty("CREATE_AGENT_SQL");

	private static String GET_AGENT_SQL = getProperty("GET_AGENT_SQL");

	private static String CREATE_INSURANCE = getProperty("CREATE_INSURANCE_SQL");

	private static String GET_INSURANCE_SQL = getProperty("GET_INSURANCE_SQL");

	private static String GET_OFFICE_CODE_SQL = getProperty("GET_OFFICE_CODE_SQL");

	private static String GET_CLIENT_BY_CAR_NUM_SQL = getProperty("GET_CLIENT_BY_CAR_NUM_SQL");

	private static String GET_CLIENT_BY_PHONE_NUM_SQL = getProperty("GET_CLIENT_BY_PHONE_NUM_SQL");

	private static String GET_CLIENT_BY_POLICY_DATE_SQL = getProperty("GET_CLIENT_BY_POLICY_DATE_SQL");

	private static String GET_CLIENT_BY_SERIAL_NO_SQL = getProperty("GET_CLIENT_BY_SERIAL_NO_SQL");

	private static String GET_CLIENT_BY_POLICY_NO_SQL = getProperty("GET_CLIENT_BY_POLICY_NO_SQL");

	private static String GET_DOCUMENTS_BY_CLIENT_ID = getProperty("GET_DOCUMENTS_BY_CLIENT_ID");

	private static String GET_DOCUMENTS_BY_FILE_ID = getProperty("GET_SCANNED_FILES_SQL");

	private static String GET_EMAILED_FILE = getProperty("GET_EMAILED_FILE");

	private static String GET_CLENTS_SQL = getProperty("GET_CLIENT_SQL");

	private static String CREATE_EMAIL_LOG = getProperty("CREATE_EMAIL_LOG");

	private static String CREATE_EMAILED_FILE_LOG = getProperty("CREATE_EMAILED_FILE_LOG");

	private static String GET_EMAIL_LIST_FOR_CLIENT = getProperty("GET_EMAIL_LIST_FOR_CLIENT");

	private static String GET_FILES_TO_EMAIL_FOR_CLIENT = getProperty("GET_FILES_TO_EMAIL_FOR_CLIENT");

	private static String END_DATE_DOCUMENTS_AFTER_EMAIL = getProperty("END_DATE_DOCUMENTS_AFTER_EMAIL");

	private static String GET_INSURANCE_COMPANY_DETAILS = getProperty("GET_INSURANCE_COMPANY_DETAILS");

	private static String DELETE_DOCUMENT_FOR_CLIENT = getProperty("DELETE_DOCUMENT_FOR_CLIENT");

	private static String LOG_SMS = getProperty("LOG_SMS");

    private static String GET_RENEWAL_CLIENTS = getProperty("GET_RENEWAL_CLIENTS");

	List<Clients> returnClients = null;

	List<Clients> returnInsuranceDetails = null;

	List<File> returnFiles = null;

	List<DocumentOnServerSide> returnDocuments = null;

	List<EmailedFile> emailSent = null;

	@SuppressWarnings("unchecked")
	@Override
	public List<Clients> searchClient(Client client) {
		Logger logger = Logger.getLogger("logger");
		logger.log(Level.SEVERE, "inside search implemntation method by name");
		searchClientParameters = new MapSqlParameterSource();
		searchClientParameters.addValue("clientName", client.getClientName());
		logger.log(Level.INFO, "before seach query being executed by name");
		try {
			returnClients = namedParameterJdbcTemplate.query(
					GET_CLENTS_SQL, searchClientParameters, new ClientMapper());
			logger.log(Level.SEVERE, "After query being executed "
					+ returnClients.get(0).getId() + " ID is:::::::"
					+ returnClients.get(0).getName()
					+ " agent name, StartDate:::"
					+ returnClients.get(0).getPolicyStartdate());
		} catch (Exception ex) {
			logger.log(Level.SEVERE, "User Not Found " + ex.toString());
			return null;
		}
		return returnClients;
	}

	@Override
	public String updateClient(Client client) {

		Logger logger = Logger.getLogger("logger");
		logger.log(Level.SEVERE, "inside implemntation method");
		try {
			namedParameters = new MapSqlParameterSource();
			namedParameters.addValue("clientName", client.getClientName());
			namedParameters.addValue("phoneNumber", client.getPhoneNumber());
			namedParameters.addValue("secondaryPhoneNumber",
					client.getSecondaryPhoneNumber());
			namedParameters.addValue("dateOfBirth", client.getDob());
			namedParameters.addValue("company", client.getCompany());
			namedParameters.addValue("eMail", client.getEmail());
			namedParameters.addValue("secondaryEmail",
					client.getSecondaryEmail());
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
			logger.log(Level.SEVERE, "premium amount *************"+client.getPremiumAmount());
			namedParameters
					.addValue("premiumAmount", client.getPremiumAmount());
			logger.log(Level.SEVERE, "terrorr amount is ::::::::::"+client.getTerrorismPremiumAmount());
			namedParameters.addValue("terrorismPremiumAmount",
					client.getTerrorismPremiumAmount());
			namedParameters.addValue("serviceTaxPercentage",
					client.getServiceTax());
			namedParameters
					.addValue("serviceTax", client.getServiceTaxAmount());
			namedParameters.addValue("totalPremiumAmount",
					client.getTotalPremiumAmount());
			namedParameters.addValue("commionRate", client.getCommionRate());
			namedParameters.addValue("commionRateAmount",
					client.getCommionRateAmount());

			if (client.getMiscTypeOfPolicy() != null
					|| client.getMiscIdCard() != null
					|| client.getMiscDispatchDate() != null) {
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
			namedParameters.addValue("iDCard", client.getMiscIdCard());
			namedParameters.addValue("miscDispatchDate",
					client.getMiscDispatchDate());
            namedParameters.addValue("renewalAmount",client.getRenewalAmount());
            namedParameters.addValue("renewalcompany",client.getRenewalCompany());
			namedParameters.addValue("userName", this.userName);
			 logger.log(Level.SEVERE, "The renewal Amount is " +client.getRenewalAmount());
		} catch (Exception e) {
			logger.log(Level.SEVERE,
					"named parameters issue for update" + e.toString());
		}
		logger.log(Level.SEVERE, "before query being executed for email id :" +client.getEmail());
		try {

			namedParameterJdbcTemplate.update(UPDATE_CLIENT,
					namedParameters);
			i = jdbcTemplate.queryForInt(
					"select count(0) from test_prefixTELOS");
			// String.valueOf(i);
			logger.log(Level.SEVERE, "query exceuted" + i);
		} catch (DuplicateKeyException e) {
			logger.log(Level.SEVERE,
					"After query being executed exception found  " + e);
			return "same";
		} catch (Exception e) {
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

			namedParameterJdbcTemplate.update(CREATE_AGENT,
					namedParameters);
			i = jdbcTemplate
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

			namedParameterJdbcTemplate.update(CREATE_INSURANCE,
					namedParameters);
			i = jdbcTemplate
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

			lAgent = jdbcTemplate.query(GET_AGENT_SQL,
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
		try {
			lOfficeCode = jdbcTemplate.query(GET_OFFICE_CODE_SQL,
					new OfficeCodeMapper());
		} catch (Exception e) {
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
		searchClientParameters
				.addValue("clientName", client.getVehicleNumber());
		logger.log(Level.INFO, "before seach query being executed");
		try {
			returnClients = namedParameterJdbcTemplate.query(
					GET_CLIENT_BY_CAR_NUM_SQL, searchClientParameters,
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
	public List<Clients> searchClientByPhoneNum(Client client) {

		logger.log(Level.SEVERE, "inside search implemntation method");
		searchClientParameters = new MapSqlParameterSource();
		searchClientParameters.addValue("clientName", client.getPhoneNumber());
		logger.log(Level.INFO, "before seach query being executed");
		try {
			returnClients = namedParameterJdbcTemplate.query(
					GET_CLIENT_BY_PHONE_NUM_SQL, searchClientParameters,
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
	public List<Clients> searchClientByPolicyDates(Client client) {
		logger.log(Level.SEVERE, "inside search implemntation method");
		searchClientParameters = new MapSqlParameterSource();
		searchClientParameters.addValue("clientFromDate", client.getFromDate());
		searchClientParameters.addValue("clientToDate", client.getToDate());
		logger.log(Level.INFO, "before seach query being executed");
		try {
			returnClients = namedParameterJdbcTemplate.query(
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
		logger.log(Level.SEVERE,
				"inside search implemntation method by serial number");
		searchClientParameters = new MapSqlParameterSource();
		searchClientParameters.addValue("clientId", client.getId());
		logger.log(Level.INFO,
				"before seach query being executed for sserial number search");
		try {
			returnClients = namedParameterJdbcTemplate.query(
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
		logger.log(Level.SEVERE,
				"inside search implemntation method by serial number");
		searchClientParameters = new MapSqlParameterSource();
		searchClientParameters.addValue("clientPolicyNo",
				client.getPolicyNumber());
		logger.log(Level.INFO,
				"before seach query being executed for policy/certificate number search");
		try {
			returnClients = namedParameterJdbcTemplate.query(
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
	List<EmailList> lemails = null;
	List <CompanyDetails> companydetails= null;

	@SuppressWarnings("unchecked")
	@Override
	public List<Company> getListOfComapnies() {
		logger.log(Level.SEVERE,
				"inside get comapnies list  implemntation method");
		searchClientParameters = new MapSqlParameterSource();
		try {
			lCompany = jdbcTemplate.query(GET_COMPANY_SQL,
					new ComapnyMapper());
			logger.log(Level.SEVERE, "After query being company list is :::::"
					+ lCompany.get(0).getCompnyName());
		} catch (Exception ex) {
			logger.log(Level.SEVERE, "companies not found " + ex.toString());
			return null;
		}
		return lCompany;
	}

	private static String GET_COMPANY_SQL = getProperty("GET_COMPANY_SQL");
	private static String GET_EMAIL_ID_LIST = getProperty("GET_EMAIL_ID_LIST");
	private static String GET_COMPANY_DETAILS = getProperty("GET_COMPANY_DETAILS_SQL");

	List<Insurance> lInsurance = null;

	@SuppressWarnings("unchecked")
	@Override
	public List<Insurance> searchInsuranceComapny() {

		Logger logger = Logger.getLogger("logger");
		logger.log(Level.SEVERE, "inside search implemntation method");
		try {

			lInsurance = jdbcTemplate.query(GET_INSURANCE_SQL,
					new InsuranceMapper());
			System.out.println(" agent found "
					+ lInsurance.get(0).getScreenName());
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
		logger.log(Level.SEVERE,
				"inside search implemntation method by serial number");
		searchClientParameters = new MapSqlParameterSource();
		searchClientParameters.addValue("clientId", client.getId());
		// logger.log(Level.INFO,"before documents seach query being executed for client id");
		try {
			returnFiles = namedParameterJdbcTemplate.query(
					GET_DOCUMENTS_BY_CLIENT_ID, searchClientParameters,
					new FileMapper());
			// logger.log(Level.SEVERE, "After query being executed ID:::::");
		} catch (Exception ex) {
			logger.log(Level.SEVERE, "User Not Found " + ex.toString());
			return null;
		}
		for (File file : returnFiles) {
			try {
				namedParameters = new MapSqlParameterSource();
				namedParameters.addValue("fileId", file.getId());
				logger.log(Level.SEVERE, "before query being executed");
				emailSent = namedParameterJdbcTemplate.query(
						GET_EMAILED_FILE, namedParameters,
						new EmailedFileMapper());
			} catch (Exception e) {
				logger.log(Level.SEVERE,
						"named parameters issue " + e.toString());
			}
			file.setEmails(emailSent);
		}
		return returnFiles;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DocumentOnServerSide> searchDocumentsByFileId(List<File> files) {
		logger.log(Level.SEVERE,
				"inside search implemntation method by serial number");
		if (ids == null) {
			ids = new HashSet<Integer>();
		}
		for (File file : files) {
			ids.add(file.getId());
		}
		searchClientParameters = new MapSqlParameterSource();
		searchClientParameters.addValue("scanIds", ids);
		logger.log(Level.INFO,
				"before documents seach query being executed for client id");
		try {
			returnDocuments = namedParameterJdbcTemplate.query(
					GET_DOCUMENTS_BY_FILE_ID, searchClientParameters,
					new DocumentOnServerSideMapping());
			logger.log(Level.SEVERE, "After query being executed ID:::::");
		} catch (Exception ex) {
			logger.log(Level.SEVERE, "User Not Found " + ex.toString());
			return null;
		}
		ids.clear();
		return returnDocuments;
	}

	@Override
	public Email logEmail(Email email) {
		Logger logger = Logger.getLogger("logger");
		logger.log(Level.SEVERE, "inside implemntation method right befor updating DB when email was sent");
		try {
			namedParameters = new MapSqlParameterSource();
			namedParameters.addValue("address", email.getAddress());
			namedParameters.addValue("clientId", email.getClientiD());
			namedParameters.addValue("message", email.getMessage());
			namedParameters.addValue("userId", systemId);
			email.setUseriD(this.userId);
		} catch (Exception e) {
			logger.log(Level.SEVERE, "named parameters issue " + e.toString());
		}
		logger.log(Level.SEVERE, "before query being executed");
		try {

			namedParameterJdbcTemplate.update(CREATE_EMAIL_LOG,
					namedParameters);
			i = jdbcTemplate.queryForInt("select max(id) from email");
			email.setiD(i);
			// String.valueOf(i);
			logger.log(Level.SEVERE, "query exceuted" + i);
		} catch (DuplicateKeyException e) {
			logger.log(Level.SEVERE,
					"After query being executed exception found  " + e);

		} catch (Exception e) {
			logger.log(Level.SEVERE,
					"After query being executed exception found  " + e);

		}

		return email;
	}

	@Override
	public Boolean logEmailedFiles(Email email, List<DocumentOnServerSide> files) {
		Logger logger = Logger.getLogger("logger");
		logger.log(Level.SEVERE, "inside implemntation method");
		for (DocumentOnServerSide file : files) {
			try {
				namedParameters = new MapSqlParameterSource();
				namedParameters.addValue("emailId", email.getiD());
				namedParameters.addValue("fileId", file.getId());
				namedParameters.addValue("userId", systemId);
				logger.log(Level.SEVERE, "before query being executed");
				namedParameterJdbcTemplate.update(
						CREATE_EMAILED_FILE_LOG, namedParameters);
			} catch (Exception e) {
				logger.log(Level.SEVERE,
						"named parameters issue " + e.toString());
				return false;
			}

		}

		return true;
	}

	@Override
	public List<EmailedFile> getEmails(File file) {
		logger.log(Level.SEVERE, "inside implemntation method to get emails");
		try {
			namedParameters = new MapSqlParameterSource();
			namedParameters.addValue("fileId", file.getId());
			logger.log(Level.SEVERE, "before query being executed");
			emailSent = namedParameterJdbcTemplate.query(
					GET_EMAILED_FILE, namedParameters, new EmailedFileMapper());
		} catch (Exception e) {
			logger.log(Level.SEVERE, "named parameters issue " + e.toString());
		}
		return emailSent;
	}

	@Override
	public List<Clients> searchClientToEmail() {
		try {
			returnClients = namedParameterJdbcTemplate.query(
					GET_EMAIL_LIST_FOR_CLIENT, searchClientParameters,
					new EmailClientMapper());
			logger.log(Level.SEVERE, "After query being executed"
					+ returnClients.get(0).getId() + "agent name "
					+ returnClients.get(0).getEmail() + " Client phone number is" +returnClients.get(0).getPhoneNumber());
		}catch (java.lang.IndexOutOfBoundsException ex) {
			logger.log(Level.SEVERE, "User Not Found ");
			return null;
		}
		catch (Exception ex) {
			logger.log(Level.SEVERE, "User Not Found "+ex.getStackTrace());
			return null;
		}
		return returnClients;
	}

	@Override
	public List<DocumentOnServerSide> searchDocumentsByClientIdForEmail(
			Clients client) {
		logger.log(Level.SEVERE,
				"inside search implemntation method by serial number");
		searchClientParameters = new MapSqlParameterSource();
		searchClientParameters.addValue("clientId", client.getId());
		// logger.log(Level.INFO,"before documents seach query being executed for client id");
		try {
			returnDocuments = namedParameterJdbcTemplate.query(
					GET_FILES_TO_EMAIL_FOR_CLIENT, searchClientParameters,
					new DocumentOnServerSideMapping());
			// logger.log(Level.SEVERE, "After query being executed ID:::::");
		} catch (Exception ex) {
			logger.log(Level.SEVERE, "User Not Found " + ex.toString());
			return null;
		}
		return returnDocuments;
	}

	@Override
	public Boolean endDateEmailedFiles(List<DocumentOnServerSide> files) {
		logger.log(Level.SEVERE,
				"End date the recond which have been mailed to the respective clients");
		for(DocumentOnServerSide scanDocument :files )
		{

			namedParameters = new MapSqlParameterSource();
			namedParameters.addValue("scanId",scanDocument.getId() );
			namedParameters.addValue("date",new Date() );
			logger.log(Level.SEVERE, "before query being executed");
			try{

				namedParameterJdbcTemplate.update(
						END_DATE_DOCUMENTS_AFTER_EMAIL, namedParameters);
                logger.log(Level.SEVERE, "Sucessfully endated the records");
			}
			catch(Exception ex)
			{
				logger.log(Level.SEVERE, "Exception when trying to update DB "+ex.getStackTrace());
				return false;
			}

		}
		logger.log(Level.SEVERE, "End Dated scanned documents");
		return true;
	}

	@Override
	public Boolean logSms(Clients client,String template,String phoneNumber) {
		String first = "PRIMARY_PHONE_NUMBER";
		String second = "SECONDSRY_PHONE_NUMBER";

		logger.log(Level.SEVERE,
				"Logging sms sent to the clients");
		namedParameters = new MapSqlParameterSource();
		namedParameters.addValue("clientId",client.getId() );
		namedParameters.addValue("template",template );
		if(phoneNumber.equals(first))
		{
			namedParameters.addValue("phoneNumber", client.getPhoneNumber() );
		}
		else if(phoneNumber.equals(second))
		{
			namedParameters.addValue("phoneNumber", client.getSecondaryPhoneNumber() );
		}
		namedParameters.addValue("userId", "connect2telos" );
		logger.log(Level.SEVERE, "before logging sms  being executed");
		try{

			namedParameterJdbcTemplate.update(
					LOG_SMS, namedParameters);
		}
		catch(Exception ex)
		{
			logger.log(Level.SEVERE, "Exception when trying to update DB " + ex.toString());
			return false;
		}
		return true;
	}

	@Override
	public List<EmailList> loadEmails() {
		logger.log(Level.SEVERE,
				"inside get comapnies list  implemntation method");
		searchClientParameters = new MapSqlParameterSource();
		try {
			lemails = jdbcTemplate.query(GET_EMAIL_ID_LIST,
					new EmailIdsMapper());
			logger.log(Level.SEVERE, "After query being email id is :::::"
					+ lemails.get(0).getEmailId());
		} catch (Exception ex) {
			logger.log(Level.SEVERE, "companies not found " + ex.toString());
			return null;
		}
		return lemails;
	}

	@Override
	public CompanyDetails getCompanyDetails(Company company) {
		logger.log(Level.SEVERE,
				"inside get comapnies details implemntation method for "+company.getCompnyName());
		namedParameters = new MapSqlParameterSource();
		namedParameters.addValue("companyName",company.getCompnyName() );
		companydetails = namedParameterJdbcTemplate.query(
				GET_COMPANY_DETAILS, namedParameters, new CompanyDetailsMapper());
		logger.log(Level.SEVERE,
				"values returned from DB for get company details are  "+companydetails.get(0).getPhoneNumber());
		return companydetails.get(0);
	}

	@Override
	public Clients searchInsuranceDetailsByCode(Client client) {
		logger.log(Level.SEVERE,
				"inside get comapnies details implemntation method for "+client.getOfficeCode());
		namedParameters = new MapSqlParameterSource();
		namedParameters.addValue("officeCode",client.getOfficeCode() );
		List<Clients> returnInsuranceDetails = null;
		try {
			returnInsuranceDetails = namedParameterJdbcTemplate.query(
					GET_INSURANCE_COMPANY_DETAILS, namedParameters,new InsuranceCompanyDetailMapper());
			for(Clients clients :returnInsuranceDetails)
			{
				logger.log(Level.SEVERE, "After query being executed "
						+ returnInsuranceDetails.get(0).getInsBranchName() + "Insurance Company name "
						+ returnInsuranceDetails.get(0).getInsCompanyName());
			}
			Clients clients = returnInsuranceDetails.get(0);
		} catch (Exception ex) {
			logger.log(Level.SEVERE, "User Not Found " + ex.toString());
			return null;
		}
		return returnInsuranceDetails.get(0);
	}

	@Override
	public Boolean deleteDocumentsForClients(Client client, List<File> files) {
		logger.log(Level.SEVERE,
				"inside the method to delete files for a client "+client.getId()+" And the documents are "+files.get(0).getName());
		Boolean deleted = false;

		for(File scanDocument :files )
		{

			namedParameters = new MapSqlParameterSource();
			namedParameters.addValue("clientId",client.getId() );
			namedParameters.addValue("documentId",scanDocument.getId() );
			logger.log(Level.SEVERE, "before query being executed");
			try{

				namedParameterJdbcTemplate.update(
						DELETE_DOCUMENT_FOR_CLIENT, namedParameters);
			}
			catch(Exception ex)
			{
				logger.log(Level.SEVERE, "Exception when trying to delete Documents " + ex.toString());
				return false;
			}

		}
		deleted = true;
		logger.log(Level.SEVERE, "successfully deleted documents");
		return deleted;
	}

    @Override
    public List<Clients> getRenewClient() {
        logger.log(Level.SEVERE,
                "Get Client up for renewal in 15 and 30 days");
        try {
            returnClients = jdbcTemplate.query(GET_RENEWAL_CLIENTS, new ClientMapper());
            logger.log(Level.SEVERE, "After query being executed "
                    + returnClients.get(0).getId() + " ID is:::::::"
                    + returnClients.get(0).getName()
                    + " agent name, StartDate:::"
                    + returnClients.get(0).getPolicyStartdate());
        } catch (Exception ex) {
            logger.log(Level.SEVERE, "User Not Found " + ex.toString());
            return null;
        }
        return returnClients;
    }


}
