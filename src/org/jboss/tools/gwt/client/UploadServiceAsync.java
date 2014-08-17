package org.jboss.tools.gwt.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface UploadServiceAsync {

	/**
	 * Method that stores a file on the server. It computes the save path
	 * depending on the case type and the case id.
	 */
	void uploadAttachement(String caseId, String id, boolean isNewCase,
			AsyncCallback callback);
	
	/**
	 * Method that deletes a file from the server. It computes the file path
	 * depending on the case type and the case id.
	 */
	void deleteAttachement(String filePath, int id, String id2,
			AsyncCallback callback);
 
	/**
	 * Update the name of the attachment
	 */
	void updateFileName(String name, AsyncCallback callback);
}
