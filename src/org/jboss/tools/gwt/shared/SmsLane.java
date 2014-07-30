package org.jboss.tools.gwt.shared;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;


 @Service
public class SmsLane implements SmsLaneInterface{
    public static String retval="";
    static Logger logger = Logger.getLogger("logger");
     SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");

     @Value( "${DOCUMENTS_MESSAGE}" )
     private String DOCUMENTS_MESSAGE;

     @Value( "${RENEWAL_MESSAGE}" )
     private String RENEWAL_MESSAGE;

     @Value( "${RENEWAL_MESSAGE_0}" )
     private String RENEWAL_MESSAGE_0;

     @Value( "${RENEWAL_MESSAGE_1}" )
     private String RENEWAL_MESSAGE_1;

     @Value( "${RENEWAL_MESSAGE_2}" )
     private String RENEWAL_MESSAGE_2;

     @Value( "${RENEWAL_MESSAGE_3}" )
     private String RENEWAL_MESSAGE_3;

     @Value( "${RENEWAL_MESSAGE_4}" )
     private String RENEWAL_MESSAGE_4;

     @Value( "${RENEWAL_MESSAGE_5}" )
     private String RENEWAL_MESSAGE_5;

     @Value( "${RENEWAL_MESSAGE_6}" )
     private String RENEWAL_MESSAGE_6;

     @Value( "${RENEWAL_MESSAGE_7}" )
     private String RENEWAL_MESSAGE_7;

     @Value( "${RENEWAL_MESSAGE_8}" )
     private String RENEWAL_MESSAGE_8;


    public String SMSSender(String msisdn,String template,Clients client)
    {

        logger.log(Level.SEVERE, " log message to be sent for documents::: "+DOCUMENTS_MESSAGE);
        String rsp="";
        String user= "teloshyd";
        String password = "hydtelos";
        String sid = "TELOSH";
        String fl = "0";
        String gwid = "2";
        String documents = "DOCUMENTS";
        String renewal = "RENEWAL";
        String payment ="PAYMENT";
        String msg = "";
        String clientName;
        DecimalFormat decim = new DecimalFormat("#.00");
        if(template.equals(documents))
        {
            msg = DOCUMENTS_MESSAGE;
        } else if (template.equals(renewal)) {
            StringBuffer clientNameBuffer = new StringBuffer(client.getName());
            if (clientNameBuffer.substring(0, 4).equalsIgnoreCase("M/S.")) {
                clientNameBuffer.replace(0, 4, "");
                clientName = clientNameBuffer.toString();
            } else {
                clientName = client.getName();
            }

            if (client.getDepartment() != null) {
                msg = RENEWAL_MESSAGE + client.getDepartment() + RENEWAL_MESSAGE_0 + RENEWAL_MESSAGE_1 + client.getPolicyNumber() + RENEWAL_MESSAGE_2 + clientName + RENEWAL_MESSAGE_3 + sdf.format(client.getPolicyEndDate()) + RENEWAL_MESSAGE_4;
                logger.log(Level.SEVERE, " log message to be sent for renewal::: "+msg);
            } else {

                msg = RENEWAL_MESSAGE_1 + client.getPolicyNumber() + RENEWAL_MESSAGE_2 + clientName + RENEWAL_MESSAGE_3 + sdf.format(client.getPolicyEndDate()) + RENEWAL_MESSAGE_4;
                logger.log(Level.SEVERE, " log message to be sent for renewal::: "+msg);
            }
        }else if (template.equals(payment)) {
            logger.log(Level.SEVERE, " log message to be sent for payment::: "+msg);

            if (client.getDepartment() != null) {

                msg = RENEWAL_MESSAGE + client.getDepartment() + RENEWAL_MESSAGE_5 + client.getPolicyNumber() + RENEWAL_MESSAGE_6 + Double.parseDouble(decim.format(client.getRenewalAmount())) + RENEWAL_MESSAGE_7 + client.getrenewalCompany() + RENEWAL_MESSAGE_8;
                logger.log(Level.SEVERE, " log message to be sent for renewal::: "+msg);
            }
        }

        try {
            // Construct The Post Data
            String data = URLEncoder.encode("user", "UTF-8") + "=" + URLEncoder.encode(user, "UTF-8");
            data += "&" + URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode(password, "UTF-8");
            data += "&" + URLEncoder.encode("msisdn", "UTF-8") + "=" + URLEncoder.encode(msisdn, "UTF-8");
            data += "&" + URLEncoder.encode("msg", "UTF-8") + "=" + URLEncoder.encode(msg, "UTF-8");
            data += "&" + URLEncoder.encode("sid", "UTF-8") + "=" + URLEncoder.encode(sid, "UTF-8");
            data += "&" + URLEncoder.encode("fl", "UTF-8") + "=" + URLEncoder.encode(fl, "UTF-8");
            data += "&" + URLEncoder.encode("gwid", "UTF-8") + "=" + URLEncoder.encode(gwid, "UTF-8");

            //Push the HTTP Request
            URL url = new URL("http://smslane.com/vendorsms/pushsms.aspx");
            URLConnection conn = url.openConnection();
            conn.setDoOutput(true);

            OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
            wr.write(data);
            wr.flush();

            //Read The Response
            BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = rd.readLine()) != null) {
                // Process line...
                retval = line;
            }
            wr.close();
            rd.close();

            System.out.println(retval);
            rsp = retval;

        } catch (Exception e) {
            logger.log(Level.SEVERE,
                    "Exception when sending a sms out " +e.toString());
        }
        return  rsp;
     }

   /*  public static void main(String[] args) {
         String response = SMSSender("919848021211",RENEWAL_MESSAGE_1+client.getPolicyNumber()+RENEWAL_MESSAGE_2+client.getPolicyEndDate()+RENEWAL_MESSAGE_3");
         System.out.println(response);
     }*/
}