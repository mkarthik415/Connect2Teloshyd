package org.jboss.tools.gwt.server;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;

import gwtupload.server.UploadAction;
import gwtupload.server.exceptions.UploadActionException;

public class UploadFile extends UploadAction {

	private static final long serialVersionUID = 1L;

	public String executeAction(HttpServletRequest request,
			List<FileItem> sessionFiles) throws UploadActionException {

		String response = "Received file:";
		for (FileItem item : sessionFiles) {
			if (!item.isFormField()) {
				try {
					response += " " + item.getName() + ", size=  "
							+ item.getSize() + ", ContentType= "
							+ item.getContentType();

					System.out.println("response is ::::::::::" + response);

				} catch (Exception e) {
					throw new UploadActionException(e.getMessage());
				}
			}
		}

		try {
			// Remove files from session
			removeSessionFileItems(request);
		} catch (Exception e) {
			throw new UploadActionException(e.getMessage());
		}
		return response;

	}
}
