package org.jboss.tools.gwt.server;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.jboss.tools.gwt.shared.Client;
import org.jboss.tools.gwt.shared.UserControllerInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.HttpRequestHandler;

import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by karthikmarupeddi on 7/10/14.
 */

@MultipartConfig
public class UploadRenewal extends HttpServlet implements HttpRequestHandler {
    Logger logger = Logger.getLogger("logger");
    ServletFileUpload upload = null;
    List items = null;
    Iterator<?> iterator = null;
    String user = null;
    final Client c = null;
    @Autowired
    UserControllerInterface userController;
    /**
     * Here the uploaded files are gathered with doPost method.
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
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
                logger.log(Level.SEVERE, "file name is " + fileName);
                int sizeInBytes = (int) item.getSize();
                logger.log(Level.SEVERE, "file size is " + sizeInBytes);
                logger.log(Level.SEVERE, "file fields are " + fields);
                InputStream inputStream = item.getInputStream();
                Integer numberOfRecords =  uploadRenewals(inputStream);
                response.getWriter().write(new String(""+(numberOfRecords-1)));
                return;
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, "exception while uploading documents",e);
        }

    }

    Integer uploadRenewals(InputStream inputStream) throws IOException {
        POIFSFileSystem fs = new POIFSFileSystem(inputStream);
        HSSFWorkbook wb = new HSSFWorkbook(fs);
        HSSFSheet sheet = wb.getSheetAt(0);
        //get first row.
        HSSFRow roww = sheet.getRow(0);
        Iterator<Row> rowIterator = sheet.iterator();
        ArrayList<String> columnsInExcel = new ArrayList<String>();
        Iterator<Cell> cellIterator = roww.cellIterator();
        while (cellIterator.hasNext()) {
            Cell cell = cellIterator.next();
            switch (cell.getCellType()) {
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

        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            numberOfRows++;
            if (numberOfRows > 1) {
                Iterator<Cell> cellIteratorForRowsWithData = row
                        .cellIterator();
                int numberOfCells = 0;
                final Client c = new Client();
                while (cellIteratorForRowsWithData.hasNext()) {
                    if (numberOfCells <= row.getLastCellNum()) {
                        Cell eachCell = cellIteratorForRowsWithData.next();

                        if (columnsInExcel.get(numberOfCells).equals(
                                "PIN")) {
                            eachCell.setCellType(Cell.CELL_TYPE_STRING);
                            logger.log(Level.SEVERE, " PIN column type being read :: " + eachCell.getStringCellValue());
                            c.setId(eachCell.getStringCellValue());
                            logger.log(Level.SEVERE, " PIN value from the client :: " + c.getId());
                        }
                        else if (columnsInExcel.get(numberOfCells).equals(
                                "POLICY NO")) {
                        }
                        else if (columnsInExcel.get(numberOfCells).equals(
                                "AMOUNT")) {
                        }
                        else if (columnsInExcel.get(numberOfCells).equals(
                                "PHONE NO")) {
                        }
                        else if (columnsInExcel.get(numberOfCells).equals(
                                "AGENT NAME")) {
                        }
                        else if (columnsInExcel.get(numberOfCells).equals(
                                "COMPANY")) {
                            eachCell.setCellType(Cell.CELL_TYPE_STRING);
                            logger.log(Level.SEVERE, " PIN column type being read :: " + eachCell.getStringCellValue());
                            c.setRenewalCompany(eachCell.getStringCellValue());
                            logger.log(Level.SEVERE, " PIN value from the client :: " + c.getRenewalCompany());
                        }
                        else if (columnsInExcel.get(numberOfCells).equals(
                                "RENEWAL PREMIUM")) {

                            eachCell.setCellType(Cell.CELL_TYPE_STRING);
                            logger.log(Level.SEVERE, " RENEWAL PREMIUM Amount is :: " + Double.parseDouble(eachCell.getStringCellValue()));
                             c.setRenewalAmount(Double.parseDouble(eachCell.getStringCellValue()));
                            logger.log(Level.SEVERE, " IDstores in the client is :: " +c.getId() );
                            logger.log(Level.SEVERE, " Renewal Company in the client is :: " +c.getRenewalCompany());
                            logger.log(Level.SEVERE, " RENEWAL PREMIUM stores in the client is :: " +c.getRenewalAmount() );
                            if(Double.parseDouble(eachCell.getStringCellValue()) > 0.0)
                            {

                                String id = userController.updateClientRenewalAmountResponse(c);
                                Boolean sentSMS = this.userController.sendSMSToClient(null,"PAYMENT",c);
                                try {
                                    Boolean sentMail = this.userController.getEmailClient(c,null,"RENEWAL");
                                } catch (MessagingException e) {
                                    logger.log(Level.SEVERE, "exception while uploading documents",e);
                                }
                            }

                        }


                        numberOfCells++;
                    }
                }

            }
        }
        return Integer.valueOf(numberOfRows);
    }


    /**
     * it is a default method.
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }


    @Override
    public void handleRequest(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
              doPost(httpServletRequest, httpServletResponse);
    }
}
