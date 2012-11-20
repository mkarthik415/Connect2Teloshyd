package org.jboss.tools.gwt.beans;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;
import org.jboss.tools.gwt.shared.User;
import org.jboss.tools.gwt.mapping.UserMapper;

public class UserDAOImpl extends NamedParameterJdbcDaoSupport implements TUserDAO{

	@SuppressWarnings("unchecked")
	@Override
	public User selectUser(String user,String password) {
		Logger logger = Logger.getLogger("logger");
		logger.log(Level.SEVERE,"inside implemntation method");
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
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

}
