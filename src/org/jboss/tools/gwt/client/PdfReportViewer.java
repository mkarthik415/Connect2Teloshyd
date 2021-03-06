package org.jboss.tools.gwt.client;

import com.extjs.gxt.ui.client.widget.MessageBox;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Frame;

import java.util.Map;

/**
 *
 * @author karthik marupeddi
 */
class PdfReportViewer extends FormPanel
{

    public PdfReportViewer(String fileName, Map<String, Object> param, String title)
    {

        setLayout(new FitLayout());
        setHeading(title);
        final Frame frame = new Frame();

        add(frame);

        if(param == null)
        {

            ((GreetingServiceAsync) GWT.create(GreetingService.class))
                    .findFileToDisplay(fileName, new AsyncCallback<String>()  {
                        public void onFailure(Throwable caught) {
                            // Show the RPC error message to the user
                            MessageBox messageBox = new MessageBox();
                            messageBox
                                    .setMessage("Cannot load the file !!");
                            messageBox.show();
                        }



                        @Override
                        public void onSuccess(String arg0) {
                            if (arg0 != null)
                            {
                                frame.setUrl(arg0);

                            } else
                            {
                                MessageBox messageBox = new MessageBox();
                                messageBox.setMessage("Cannot load the file");
                                messageBox.show();

                            }
                        }

                    });
        }

        else
        {

            ((GreetingServiceAsync) GWT.create(GreetingService.class))
                    .getPdfReport(fileName, param, new AsyncCallback<String>()  {
                        public void onFailure(Throwable caught) {
                            // Show the RPC error message to the user
                            MessageBox messageBox = new MessageBox();
                            messageBox
                                    .setMessage("Cannot load the report !!");
                            messageBox.show();
                        }



                        @Override
                        public void onSuccess(String arg0) {
                            MessageBox messageBox = new MessageBox();
                            if (arg0 != null)
                            {

                                frame.setUrl(arg0);

                            } else
                            {
                                messageBox.setMessage("Cannot load the report");
                                messageBox.show();

                            }
                        }

                    });
        }


    }
}

