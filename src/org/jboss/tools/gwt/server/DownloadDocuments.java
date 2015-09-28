package org.jboss.tools.gwt.server;

import org.jboss.tools.gwt.shared.UserControllerInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.HttpRequestHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DownloadDocuments extends HttpServlet implements HttpRequestHandler {

    @Autowired
    private UserControllerInterface userController;

	private static final long serialVersionUID = 1L;
	PreparedStatement pst = null;
	String fileName = null;
    Blob blob = null;

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			   throws ServletException, IOException {
		//UserController userController = new UserController();
		java.sql.Connection con= userController.downloadDocuments();
		
		String query = "SELECT scanned,name from scan where id ='"+request.getParameter("id")+"'";
		//blobFile = new File("woman2.pdf");
		try {
			pst = con.prepareStatement(query);
			ResultSet result = pst.executeQuery();
			result.next();
		    blob = result.getBlob("scanned");
		    fileName = result.getString("name");
		    InputStream in = blob.getBinaryStream();
		    int fileLength = in.available();
 
             
//		    ServletContext context = getServletContext();
//		    String mimeType = context.getMimeType(fileName);
//            if (mimeType == null) {
//                mimeType = "application/octet-stream";
//				response.setContentType("application/pdf");
//            }
		    

            response.setContentLength(fileLength);
            response.addHeader("Content-Disposition", "attachment; filename="+fileName);
            
            OutputStream outStream = response.getOutputStream();
            byte[] buffer = new byte[fileLength];
            int bytesRead = -1;
            
            while ((bytesRead = in.read(buffer)) != -1) {
                outStream.write(buffer, 0, bytesRead);
            }
            
            in.close();
            outStream.close();
            con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
		
	}

	@Override
	public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doPost(request, response);
	}
}
