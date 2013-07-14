package org.jboss.tools.gwt.server;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.jboss.tools.gwt.shared.UserController;

@MultipartConfig
public class UploadFileHandler extends HttpServlet {

	private static final long serialVersionUID = 1L;
	Logger logger = Logger.getLogger("logger");
	ServletFileUpload upload = null;
	@SuppressWarnings("rawtypes")
	List items = null;
	Iterator<?> iterator = null;
	String clientId = null;
	String description = null;

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		UserController userController = new UserController();
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);

		if (isMultipart) {
			FileItemFactory factory = new DiskFileItemFactory();
			upload = new ServletFileUpload(factory);
		}

		try {
			items = upload.parseRequest(request);
			iterator = items.iterator();
			while (iterator.hasNext()) {
				FileItem item = (FileItem) iterator.next();
				String fields = item.getFieldName();
				String fileName = item.getName();
				String[] formFields = fields.split(":");
				if(formFields.length == 2)
				{
					clientId = formFields[0];
					description = formFields[1];
				}else
					clientId = formFields[0];
				
				logger.log(Level.SEVERE, "file name is " + fileName);
				int sizeInBytes = (int) item.getSize();
				logger.log(Level.SEVERE, "file size is " + sizeInBytes);
				logger.log(Level.SEVERE, "file fields are " + fields);
					InputStream inputStream = item.getInputStream();
					userController.insertDocumentToDB(
							Integer.parseInt(clientId), inputStream, fileName);
					return;
			}
		} catch (Exception e) {

		}

	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

}
