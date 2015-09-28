package org.jboss.tools.gwt.shared;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * Created by karthikmarupeddi on 3/21/14.
 */
public interface UserControllerInterface {

    Boolean sendSMSToClient(Clients client, String templateType, Client clients);

    Integer getUserResponse(String user, String password);

    String getCreateClientResponse(Client client);

    String getrenewlientResponse(Client client);

    String updateClientResponse(Client client);

    String createAgentResponse(Agent agent);

    String createInsuranceResponse(Insurance insurance);

    List<Clients> getSearchClient(Client client);

    List<Clients> getSearchClientByCarNum(Client client);

    List<Clients> getSearchClientByPhoneNum(Client client);

    List<Clients> getSearchClientByEmailId(Client client);

    List<Clients> getSearchClientByPolicyDates(Client client);

    List<Clients> getSearchClientBySerialNo(Client client);

    List<Clients> getSearchClientByPolicyNo(Client client);

    List<Company> getListOfCompanies();

    List<EmailList> getListOfEmails();

    CompanyDetails getCompanyDetails(Company company);

    List<org.jboss.tools.gwt.shared.File> getUploadedDocuments(
            Client client);

    Email logEmail(Email email);

    Boolean logEmailedFiles(Email email, List<DocumentOnServerSide> files);

    Boolean endDate(List<DocumentOnServerSide> files);

    List<EmailedFile> getEmails(org.jboss.tools.gwt.shared.File file);

    List<Agent> getSearchAgent();

    List<Insurance> getSearchInsuranceCompany();

    List<OfficeCode> getSearchOfficeCode();

    Boolean deleteClientDocuments(Client client,
                                  List<org.jboss.tools.gwt.shared.File> files);

    List<Clients> getListClientToEmail();

    Clients searchInsuranceDetailsByOfficeCode(Client client);

    List<DocumentOnServerSide> searchDocumentsByClient(Clients client);

    String getSMSClient(Client client);

    String getExcelReport(String input,
                          Map<String, Object> parameters);

    String getPdfReportForClient(String input,
                                 Map<String, Object> parameters);

    String getExcelReportForClient(String input,
                                   Map<String, Object> parameters);

    String getPdfReportForPendingPolicy(String input,
                                        Map<String, Object> parameters);

    String getExcelReportForPendingPolicy(String input,
                                          Map<String, Object> parameters);

    Long insertDocumentToDB(int clientId, InputStream inputStream,
                            String name, String description, String scannedBy);

    Long insertImageToDB(InputStream inputStream,
                            String name, String scannedBy);

    String getPdfReport(String input,
                        Map<String, Object> parameters) throws SQLException;

    Boolean getEmailClient(Client client,
                           List<File> files, String source)
            throws AddressException, MessagingException;

    Connection downloadDocuments();

    String getFileForDisplay(String id, String filePath);

    List<DocumentOnServerSide> searchDocumentsByClientForRenewals(Clients client);

    String updateClientRenewalAmountResponse(Client client);

    void sendAnnoncment(String subject, String data);

}
