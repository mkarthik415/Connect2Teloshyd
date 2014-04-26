package org.jboss.tools.gwt.shared;

import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.logging.Level;
import java.util.logging.Logger;


 @Service
public class SmsLane {
    public static String retval="";
    static Logger logger = Logger.getLogger("logger");


    private String DOCUMENTS_MESSAGE = "Dear Customer, your documents have been sent to your e-mail id. Thank you for your patronize us. With regards, TELOS Hyderabad Ph.040-66776677.";

    private String RENEWAL_MESSAGE_1 = "Policy No. ";

    private String RENEWAL_MESSAGE_2 = " stands in the name of ";

    private String RENEWAL_MESSAGE_3 = " is due for renewal on ";

    private String RENEWAL_MESSAGE_4 = " .Kindly approach us for renewal.TELOS Hyderabad Ph.040-66776677.";

    public String SMSSender(String msisdn,String template,Clients client)
    {


        String rsp="";
        String user= "teloshyd";
        String password = "hydtelos";
        String sid = "TELOSH";
        String fl = "0";
        String gwid = "2";
        String documents = "DOCUMENTS";
        String renewal = "RENEWAL";
        String msg = "";
        String clientName;
        if(template.equals(documents))
        {
            msg = DOCUMENTS_MESSAGE;
        }
        else if (template.equals(renewal)) {
            StringBuffer clientNameBuffer= new StringBuffer(client.getName());
            if(clientNameBuffer.substring(0,4).equalsIgnoreCase("M/S.") )
            {
                clientNameBuffer.replace(0,4,"");
                clientName =clientNameBuffer.toString();
            }
            else
            {
                clientName = client.getName();
            }

            msg = RENEWAL_MESSAGE_1 + client.getPolicyNumber() + RENEWAL_MESSAGE_2 + clientName + RENEWAL_MESSAGE_3 + client.getPolicyEndDate() + RENEWAL_MESSAGE_4;
            System.out.println("SMS Message is " + msg);
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