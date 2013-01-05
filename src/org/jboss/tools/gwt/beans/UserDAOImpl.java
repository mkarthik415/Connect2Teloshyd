package org.jboss.tools.gwt.beans;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.jboss.tools.gwt.mapping.ClientMapper;
import org.jboss.tools.gwt.mapping.UserMapper;
import org.jboss.tools.gwt.shared.Client;
import org.jboss.tools.gwt.shared.Clients;
import org.jboss.tools.gwt.shared.User;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;

import com.extjs.gxt.ui.client.widget.MessageBox;

public class UserDAOImpl extends NamedParameterJdbcDaoSupport implements
		TUserDAO {

	MapSqlParameterSource namedParameters = null;
	MapSqlParameterSource searchClientParameters = null;

	@SuppressWarnings("unchecked")
	@Override
	public Boolean selectUser(String user, String password) {
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
					+ returnUsers.get(0).getName());
			userFound= true;
		} catch (Exception ex) {
			logger.log(Level.SEVERE, "User Not Found " + ex.toString());
			return userFound;
		}
		return userFound;


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
	boolean clientCreate = false;

	@Override
	public Boolean createClient(Client client) {
		// TODO Auto-generated method stub
		Logger logger = Logger.getLogger("logger");
		logger.log(Level.SEVERE, "inside implemntation method");
		System.out.println("inside implemntation method");
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
			namedParameters.addValue("policyDetails", client.getPolicyDetails());
			namedParameters.addValue("agent", client.getAgent());
			namedParameters.addValue("policyType", client.getPolicyType());
			namedParameters
					.addValue("premiumAmount", client.getPremiumAmount());
			namedParameters.addValue("terrorismPremiumAmount",
					client.getTerrorismPremiumAmount());
			namedParameters.addValue("serviceTax", client.getServiceTax());
			namedParameters.addValue("totalPremiumAmount",
					client.getTotalPremiumAmount());
			namedParameters.addValue("commionRate", client.getCommionRate());
			namedParameters.addValue("commionRateAmount",
					client.getCommionRateAmount());
			
			if(client.getMiscTypeOfPolicy() != null)
			{
				logger.log(Level.SEVERE, "inside implemntation method when creating a new client "+client.getMiscTypeOfPolicy());
				namedParameters.addValue("fireTypeOfPolicy",
						client.getMiscTypeOfPolicy());
			}
			else if(client.getFireTypeOfPolicy() != null)
			{
				namedParameters.addValue("fireTypeOfPolicy",
						client.getFireTypeOfPolicy());
			}
			else
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
		} catch (Exception e) {
			logger.log(Level.SEVERE, "named parameters issue " + e.toString());
/*			MessageBox messageBox = new MessageBox();
			messageBox
					.setMessage("PLease enter amount correctly ..");
			messageBox.show();*/
		}
		logger.log(Level.SEVERE, "before query being executed");
		try {

			this.getNamedParameterJdbcTemplate().update(CREATE_CLIENT,
					namedParameters);
			logger.log(Level.SEVERE, "query exceuted");
		} catch (Exception e) {
			logger.log(Level.SEVERE,
					"After query being executed exception found  " + e);
/*			MessageBox messageBox = new MessageBox();
			messageBox
					.setMessage("PLease enter amount correctly ..");
			messageBox.show();*/
			return clientCreate;
		}
		clientCreate = true;
		return clientCreate;
	}

	private static String CREATE_CLIENT = getProperty("CREATE_CLIENT_SQL");

	@Override
	public List<Clients> searchClient(Client client) {
		Logger logger = Logger.getLogger("logger");
		logger.log(Level.SEVERE, "inside search implemntation method");
		searchClientParameters = new MapSqlParameterSource();
		searchClientParameters.addValue("clientName", client.getClientName());
		logger.log(Level.INFO, "before seach query being executed");
		try {
			returnClients = this.getNamedParameterJdbcTemplate().query(
					GET_CLENTS_SQL, searchClientParameters, new ClientMapper());
			logger.log(Level.SEVERE, "After query being executed"
					+ returnClients.get(0).getName());
		} catch (Exception ex) {
			logger.log(Level.SEVERE, "User Not Found " + ex.toString());
			return null;
		}
		return returnClients;
	}

	private static String GET_CLENTS_SQL = getProperty("GET_CLIENT_SQL");
	List<Clients> returnClients = null;

}
