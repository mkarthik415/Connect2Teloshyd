package org.jboss.tools.gwt.server;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadBase.SizeLimitExceededException;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.jboss.tools.gwt.shared.Client;
import org.jboss.tools.gwt.shared.UserController;

public class UploadFileHandler extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest request, HttpServletResponse response)
   throws ServletException, IOException {
            
    System.out.println("Inside doPost");  
    
    Client excelClient = new Client();
    
    UserController userController = new UserController();

       // Create a factory for disk-based file items
       FileItemFactory factory = new DiskFileItemFactory();
       // Create a new file upload handler
       ServletFileUpload fileUpload  = new ServletFileUpload(factory);
       // sizeMax - The maximum allowed size, in bytes. The default value of -1 indicates, that there is no limit.
       // 1048576 bytes = 1024 Kilobytes = 1 Megabyte
       fileUpload.setSizeMax(1048576);
       
        
       if (!ServletFileUpload.isMultipartContent(request)) {
             try {
                
               throw new FileUploadException("error multipart request not found");
           } catch (FileUploadException e) {
               // TODO Auto-generated catch block
               e.printStackTrace();
           }
       }
                        
       try {

           List<FileItem> items = fileUpload.parseRequest(request);
            
           if (items == null) {            
               response.getWriter().write("File not correctly uploaded");
               return;
         }
            
           Iterator<FileItem> iter = items.iterator();

           while (iter.hasNext()) {
               FileItem item = (FileItem) iter.next();
                
               ////////////////////////////////////////////////
               // http://commons.apache.org/fileupload/using.html                              
               ////////////////////////////////////////////////

               //if (item.isFormField()) {                                                         
                   String fileName = item.getName();
                   System.out.println("fileName is : " + fileName);    
                   String typeMime = item.getContentType();
                   System.out.println("typeMime is : " + typeMime);    
                   int sizeInBytes = (int) item.getSize();
                   System.out.println("Size in bytes is : " + sizeInBytes);    
                   //byte[] file = item.get();
                   POIFSFileSystem fs = new POIFSFileSystem(item.getInputStream());
                   HSSFWorkbook wb = new HSSFWorkbook(fs);
                   HSSFSheet sheet = wb.getSheetAt(0);
                   //get first row.
                   HSSFRow roww = sheet.getRow(0);
                   Iterator<Row> rowIterator = sheet.iterator();
                   ArrayList<String> columnsInExcel = new ArrayList<String>();
            	   Iterator<Cell> cellIterator = roww.cellIterator();
            	   while(cellIterator.hasNext()) {
            		   Cell cell = cellIterator.next();
            		   switch(cell.getCellType()) {
                       case Cell.CELL_TYPE_BOOLEAN:
                           System.out.print(cell.getBooleanCellValue() + "\t\t");
                       case Cell.CELL_TYPE_NUMERIC:
                           System.out.print(cell.getNumericCellValue() + "\t\t");
                           break;
                       case Cell.CELL_TYPE_STRING:
                           System.out.print(cell.getStringCellValue() + "\t\t");
                           columnsInExcel.add(cell.getStringCellValue());
                           break;
                           
					}
				}
                  
                   //for each row
            	   int numberOfRows = 0;
            	   
                   while(rowIterator.hasNext()) {
                	   Row row = rowIterator.next();
                	   numberOfRows ++;
					if (numberOfRows > 1) {
						Iterator<Cell> cellIteratorForRowsWithData = row
								.cellIterator();
						int numberOfCells = 0;
						while (cellIteratorForRowsWithData.hasNext()) {
							if (numberOfCells <= row.getLastCellNum()) {
								Cell eachcell = cellIteratorForRowsWithData
										.next();
								if (columnsInExcel.get(numberOfCells).equals(
										"POLICY NO")) {
									excelClient.setPolicyNumber(eachcell.getStringCellValue());
								}
								else if (columnsInExcel.get(numberOfCells).equals(
										"CLIENT NAME")) {
									excelClient.setClientName(eachcell.getStringCellValue());
								}
								else if (columnsInExcel.get(numberOfCells).equals(
										"DEPARTMENT")) {
									excelClient.setPolicyType(eachcell.getStringCellValue());
								}
								else if (columnsInExcel.get(numberOfCells).equals(
										"POLICY TYPE")) {
									excelClient.setMiscTypeOfPolicy(eachcell.getStringCellValue());
								}
								else if (columnsInExcel.get(numberOfCells).equals(
										"START DATE")) {
									excelClient.setPolicyStartdate(HSSFDateUtil.getJavaDate(eachcell.getNumericCellValue()));
								}
								else if (columnsInExcel.get(numberOfCells).equals(
										"END DATE")) {
									excelClient.setPolicyEndDate(HSSFDateUtil.getJavaDate(eachcell.getNumericCellValue()));
								}
								else if (columnsInExcel.get(numberOfCells).equals(
										"ENDRS NUMBER")) {
									excelClient.setEndrsNumber(eachcell.getStringCellValue());
								}
								else if (columnsInExcel.get(numberOfCells).equals(
										"INS COMPANYNAME")) {
									excelClient.setInsCompanyName(eachcell.getStringCellValue());
								}
								else if (columnsInExcel.get(numberOfCells).equals(
										"INS BRANCHNAME")) {
									excelClient.setInsBranchName(eachcell.getStringCellValue());
								}
								else if (columnsInExcel.get(numberOfCells).equals(
										"OFFICE CODE")) {
									excelClient.setOfficeCode(eachcell.getStringCellValue());
								}
								else if (columnsInExcel.get(numberOfCells).equals(
										"AGENT")) {
									excelClient.setAgent(eachcell.getStringCellValue());
								}
								else if (columnsInExcel.get(numberOfCells).equals(
										"PREMIUM")) {
									excelClient.setPremiumAmount(eachcell.getNumericCellValue());
								}
								else if (columnsInExcel.get(numberOfCells).equals(
										"TERRORISM")) {
									excelClient.setTerrorismPremiumAmount(eachcell.getNumericCellValue());
								}
								else if (columnsInExcel.get(numberOfCells).equals(
										"TOTAL PREMIUM")) {
									excelClient.setTotalPremiumAmount(eachcell.getNumericCellValue());
								}
								else if (columnsInExcel.get(numberOfCells).equals(
										"COMMISION RATE")) {
									excelClient.setCommionRate(eachcell.getNumericCellValue());
								}
								else if (columnsInExcel.get(numberOfCells).equals(
										"COMMISION AMOUNT")) {
									excelClient.setCommionRateAmount(eachcell.getNumericCellValue());
								}
								
								
								
								numberOfCells++;
							}
						}
						String create = userController.getCreateClientResponse(excelClient);
						System.out.println("client inserted "+create);
					}
                	   //for each cell in a row
					System.out.println("");

				}
                   PrintWriter out = response.getWriter();
                   response.getWriter().write("Hello , Welcome in My Servlet"); 
                   out.println("Upload OK");
                   out.flush();
                   out.close();
			}
		} catch (SizeLimitExceededException e) {
			System.out.println("File size exceeds the limit : 1 MB!!");
		} catch (Exception e) {
			e.printStackTrace();
			PrintWriter out = response.getWriter();
           response.setHeader("Content-Type", "text/html");
           out.println("Error");
           out.flush();
           out.close();
       }
        
   }

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

}
