package org.jboss.tools.gwt.server;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jboss.tools.gwt.shared.UserController;

public class DownloadDocuments extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	PreparedStatement pst = null;
	String fileName = null;
    Blob blob = null;
    File blobFile = null; 

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			   throws ServletException, IOException {
		System.out.println("here in server class for download.....");
		UserController userController = new UserController();
		java.sql.Connection con= userController.downloadDocuments();
		
		String query = "SELECT scanned,name from scan where id ='2'";
		blobFile = new File("woman2.pdf");
		try {
			pst = con.prepareStatement(query);
			ResultSet result = pst.executeQuery();
			result.next();
		    blob = result.getBlob("scanned");
		    fileName = result.getString("name");
		    InputStream in = blob.getBinaryStream();
		    int fileLength = in.available();
		    System.out.println("size of the file is....."+fileLength);  
             
		    ServletContext context = getServletContext();
		    String mimeType = context.getMimeType(fileName);
            if (mimeType == null) {         
                mimeType = "application/octet-stream";
            }  
		    
            response.setContentType("application/pdf");
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
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
		
	}

}
