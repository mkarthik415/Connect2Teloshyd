package org.jboss.tools.gwt.client;

import com.extjs.gxt.ui.client.Registry;
import com.extjs.gxt.ui.client.Style;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.FormEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.Html;
import com.extjs.gxt.ui.client.widget.MessageBox;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.FileUploadField;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.HtmlEditor;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.layout.FormData;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.rpc.AsyncCallback;

import java.util.logging.Logger;

/**
 * Created by karthikmarupeddi on 9/15/15.
 */
public class Announcement extends ContentPanel {

    Announcement() {
        setHeaderVisible(false);
        setBodyBorder(false);
    }

    private VerticalPanel vp;

    public HtmlEditor getEmailNotes() {
        return emailNotes;
    }

    public HtmlEditor emailNotes = new HtmlEditor();
    private FormData formData;
    FormPanel simple = new FormPanel();
    Html htmlForBreak = new Html("<br></br>");
    TextField<String> subjectField = new TextField();
    Button eMailButton;
    Button insertImage;
    FileUploadField file;
    String requestData = null;
    MessageBox box = null;
    String url = null;
    Logger logger = Logger.getLogger("logger");

    @Override
    protected void onRender(Element parent, int index) {
        super.onRender(parent, index);
        vp = new VerticalPanel();
        vp.setSpacing(10);
        simple.setAction("insertImages");
        simple.setEncoding(FormPanel.Encoding.MULTIPART);
        simple.setMethod(FormPanel.Method.POST);

        formData = new FormData("100%");
        simple.setHeading("Email Form");
        simple.setFrame(true);
        simple.setWidth(1100);
        simple.setHeight(625);

        subjectField.setFieldLabel("Subject");
        subjectField.setAutoWidth(true);
        simple.add(subjectField);

        simple.add(htmlForBreak);

        emailNotes.setFieldLabel("Email Message");
        emailNotes.setHeight(450);
        emailNotes.setWidth(700);
        emailNotes.
                setValue("Hello Sir/Madam,<div><br></div><div>Wish Your all a Happy Deepavali.Thank you for doing business with us.<div><br></div><div>Thank you.</div></div><div><br></div><div>With Regards,</div><div>Telos.</div>");
        simple.add(emailNotes, formData);

        file = new FileUploadField();
        file.setAllowBlank(false);
        file.setFieldLabel("Select Image");
        simple.add(file);

        insertImage = new Button("Insert Image");
        simple.addButton(insertImage);

        eMailButton = new Button(" Send E-Mail");
        simple.addButton(eMailButton);

        simple.setButtonAlign(Style.HorizontalAlignment.CENTER);

        vp.add(simple);
        add(vp);




        insertImage.addListener(Events.OnClick, new Listener<ButtonEvent>() {

            @Override
            public void handleEvent(ButtonEvent e) {

                if (!simple.isValid()) {
                    return;
                }
                if (file.getValue() == null) {
                    return;
                } else {
                    String user = Registry.get("user");
                    requestData = requestData + ":" + user;
                    if (file != null) {
                        file.setName(requestData);
                    }
                }
                box = MessageBox.wait("Progress",
                        "Loading...", "Loading...");
                simple.submit();

            }

        });


        simple.addListener(Events.Submit, new Listener<FormEvent>() {


            public void handleEvent(FormEvent fe) {

                box.close();
                url = fe.getResultHtml();
                String link = new String(GWT.getHostPageBaseURL()
                        + "downloadDocuments?id="+removeHTML(url));
                if (link != null && link.length() > 0) {
                    emailNotes.getExtendedFormatter().insertImage(link);
                } else {
                    emailNotes.getExtendedFormatter().removeLink();
                }
                        }
        });



        eMailButton.addListener(Events.OnClick, new Listener<ButtonEvent>() {


            /**
             * Sent when an event that the listener has registered for occurs.
             *
             * @param be the event which occurred
             */
            @Override
            public void handleEvent(ButtonEvent be) {
                ((GreetingServiceAsync) GWT.create(GreetingService.class))
                        .sendAnnocments(subjectField.getValue(), emailNotes.getValue(), new AsyncCallback<Boolean>() {

                            @Override
                            public void onFailure(Throwable caught) {
                                return;
                            }

                            @Override
                            public void onSuccess(Boolean result) {
                                box = MessageBox.alert("Announcement","E-mail are being sent to all customer's",null);
                            }
                        });

            }
        });


    }

    public static String removeHTML(String htmlString)
    {
        // Remove HTML tag from java String
        String noHTMLString = htmlString.replaceAll("<.*?\\>", "");

        // Remove Carriage return from java String
        noHTMLString = noHTMLString.replaceAll("r", "<br/>");

        // Remove New line from java string and replace html break
        noHTMLString = noHTMLString.replaceAll("n", " ");
        noHTMLString = noHTMLString.replaceAll("'", "&#39;");
        noHTMLString = noHTMLString.replaceAll("\"", "&quot;");
        return noHTMLString;
    }




}
