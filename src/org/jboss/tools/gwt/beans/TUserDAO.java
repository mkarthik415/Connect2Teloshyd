package org.jboss.tools.gwt.beans;


import org.jboss.tools.gwt.shared.User;

public interface TUserDAO {
	public User selectUser(final String user,final String password);

}
