package org.jboss.tools.gwt.server;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.jboss.tools.gwt.shared.UserControllerInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.HttpRequestHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by karthikmarupeddi on 9/19/15.
 */
public class UploadImagesHandler extends HttpServlet implements HttpRequestHandler {

    private static final long serialVersionUID = 1L;
    Logger logger = Logger.getLogger("logger");
    ServletFileUpload upload = null;
    List items = null;
    Iterator<?> iterator = null;
    String clientId = null;
    String description = null;
    String user = null;

    @Autowired
    UserControllerInterface userController;

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //UserController userController = new UserController();
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
                    user = formFields[1];
                }else
                    clientId = formFields[0];

                logger.log(Level.SEVERE, "file name is " + fileName);
                int sizeInBytes = (int) item.getSize();
                logger.log(Level.SEVERE, "file size is " + sizeInBytes);
                logger.log(Level.SEVERE, "file fields are " + fields);
                InputStream inputStream = item.getInputStream();
                Long value = userController.insertImageToDB(inputStream, fileName, user);
                response.getWriter().write(value.toString());
                return;
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, "exception while uploading documents" + e.toString());
        }

    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    public void handleRequest(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        doPost(httpServletRequest,httpServletResponse);
    }
}
