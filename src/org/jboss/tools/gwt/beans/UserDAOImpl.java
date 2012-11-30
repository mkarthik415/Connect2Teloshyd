package org.jboss.tools.gwt.beans;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.jboss.tools.gwt.mapping.UserMapper;
import org.jboss.tools.gwt.shared.Client;
import org.jboss.tools.gwt.shared.User;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;

public class UserDAOImpl extends NamedParameterJdbcDaoSupport implements TUserDAO{

	MapSqlParameterSource namedParameters = null;
	
	@SuppressWarnings("unchecked")
	@Override
	public User selectUser(String user,String password) {
		Logger logger = Logger.getLogger("logger");
		logger.log(Level.SEVERE,"inside implemntation method");
		namedParameters = new MapSqlParameterSource();
		namedParameters.addValue("username", user);
		namedParameters.addValue("password", password);
		
		logger.log(Level.SEVERE,"before query being executed");
		try{
			 returnUsers =  this.getNamedParameterJdbcTemplate().query(GET_BENEFITS_SQL,namedParameters, new UserMapper());
			 logger.log(Level.SEVERE,"After query being executed"+returnUsers.get(0).getName());
		}
		catch(Exception ex)
		{
			logger.log(Level.SEVERE,"User Not Found " +ex.toString());
			return null;
		}
		
		if(returnUsers.size() >=0)
		{
			return returnUsers.get(0);
					
		}
		else
			return null;

	}
    private static String GET_BENEFITS_SQL = getProperty("GET_BENEFITS_SQL");
	
    
    protected static String getProperty(String key)
	{
		String filename = "org/jboss/tools/gwt/beans/benefits.properties";
		//FileInputStream fis;
		Properties properties = new Properties();
		
		//InputStream stream = loader.getResourceAsStream("/myProp.properties");
		
	       InputStream configStream = Thread.currentThread()
                   .getContextClassLoader()
                   .getResourceAsStream(filename);

	       
		try {
			//fis = new FileInputStream(new File(filename));
			properties.load(configStream);
			
		} catch (FileNotFoundException e) {
			//logger.error(e);
		}
		catch (IOException e) {
			//logger.error(e);
		}
		return properties.getProperty(key);
		
	}
    
    List<User> returnUsers = null;
    boolean clientCreate = false;


	@Override
	public Boolean createClient(Client client) {
		// TODO Auto-generated method stub
		Logger logger = Logger.getLogger("logger");
		logger.log(Level.SEVERE,"inside implemntation method");
	     namedParameters = new MapSqlParameterSource();
	     //INSURED_NAME, PHONE_NO, DOB, COMPANY, EMAIL, GENDER, INDUSTRY, INSURED_ADDRESS
		namedParameters.addValue("clientName", client.getClientName());
		namedParameters.addValue("phoneNumber", client.getPhoneNumber());
		namedParameters.addValue("dateOfBirth", client.getDob().toString());
		namedParameters.addValue("company", client.getCompany());
		namedParameters.addValue("eMail", client.getEmail());
		namedParameters.addValue("gender", client.getGender());
		namedParameters.addValue("industry", client.getIndustry());
		namedParameters.addValue("address", client.getAddress());
		logger.log(Level.SEVERE,"before query being executed");
		try{
		
			this.getNamedParameterJdbcTemplate().update(CREATE_CLIENT, namedParameters);
			logger.log(Level.SEVERE,"query exceuted");
		}
		catch(Exception e){
		 logger.log(Level.SEVERE,"After query being executed exception found  "+e);
		 return clientCreate;
		}
		clientCreate = true;
		return clientCreate;
	}
	
	private static String CREATE_CLIENT = getProperty("CREATE_CLIENT_SQL");

}
