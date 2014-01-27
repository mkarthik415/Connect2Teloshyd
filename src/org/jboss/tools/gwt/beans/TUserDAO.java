package org.jboss.tools.gwt.beans;


import java.util.List;

import org.jboss.tools.gwt.shared.Agent;
import org.jboss.tools.gwt.shared.Client;
import org.jboss.tools.gwt.shared.Clients;
import org.jboss.tools.gwt.shared.Company;
import org.jboss.tools.gwt.shared.CompanyDetails;
import org.jboss.tools.gwt.shared.DocumentOnServerSide;
import org.jboss.tools.gwt.shared.Email;
import org.jboss.tools.gwt.shared.EmailList;
import org.jboss.tools.gwt.shared.EmailedFile;
import org.jboss.tools.gwt.shared.File;
import org.jboss.tools.gwt.shared.Insurance;
import org.jboss.tools.gwt.shared.OfficeCode;

public interface TUserDAO {
	public Integer selectUser(final String user,final String password);
	
	public String createClient(Client client);
	
	public String updateClient(Client client);
	
	public List<Clients> searchClient(Client client);
	
	public String createAgent(Agent agent);
	
	public List<Agent> searchAgent();
	
	public List<OfficeCode> searchOfficeCode();
	
	public List<Clients> searchClientByCarNum(Client client);
	
	public List<Clients> searchClientByPhoneNum(Client client);
	
	public List<Clients> searchClientByPolicyDates(Client client);

	public List<Clients> searchClientBySerialNo(Client client);

	public List<Clients> searchClientByPolicyNo(Client client);
	
	public List<File> searchDocumentsByClientId(Client client);
	
	public List<Company> getListOfComapnies();
	
	public CompanyDetails getCompanyDetails(Company company);
	
	public List<EmailList> loadEmails();

	public String createInsurance(Insurance insurance);

	public List<Insurance> searchInsuranceComapny();

	public List<DocumentOnServerSide> searchDocumentsByFileId(List<File> files);
	
	public Email logEmail(Email email);
	
	public List<EmailedFile> getEmails(File file);
	
	public Boolean logEmailedFiles(Email email, List<DocumentOnServerSide> files);
	
	public List<Clients> searchClientToEmail();
	
	public List<DocumentOnServerSide> searchDocumentsByClientIdForEmail(Clients client);
	
	public Boolean endDateEmailedFiles(List<DocumentOnServerSide> files);
	
	public Boolean logSms(Clients client,String template,String phoneNUmber);

}
