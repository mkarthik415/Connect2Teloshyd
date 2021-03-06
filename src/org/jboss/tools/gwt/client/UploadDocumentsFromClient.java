package org.jboss.tools.gwt.client;
import com.extjs.gxt.ui.client.Registry;
import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.FieldEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.Info;
import com.extjs.gxt.ui.client.widget.MessageBox;
import com.extjs.gxt.ui.client.widget.TabPanel;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.FileUploadField;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.TextArea;
import com.extjs.gxt.ui.client.widget.form.FormPanel.Encoding;
import com.extjs.gxt.ui.client.widget.form.FormPanel.Method;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.layout.FormData;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.FormHandler;
import com.google.gwt.user.client.ui.FormSubmitCompleteEvent;
import com.google.gwt.user.client.ui.FormSubmitEvent;

/**
 * Created by karthikmarupeddi on 7/20/14.
 */
public class UploadDocumentsFromClient extends ContentPanel {


    public UploadDocumentsFromClient(){
        setHeaderVisible(false);
        setBodyBorder(false);
    }

    TextField<String> clientName = null;
    TextField<String> clientId = null;
    TextField<String> policyNumber = null;
    TextArea descriptionField = null;
    FileUploadField file = null;
    String descriptionFieldValue = null;
    String fieldName = null;
    Boolean hideFields = false;
    private FormData formData;
    private VerticalPanel vp;

    @Override
    protected void onRender(Element parent, int index) {
        super.onRender(parent, index);
        formData = new FormData("-20");
        vp = new VerticalPanel();
        vp.setSpacing(10);
        createForm1();
        add(vp);
    }

    private void createForm1()
    {
        final FormPanel panel = new FormPanel();
        panel.setHeading("File Upload");
        panel.setFrame(true);
        panel.setAction("myurl");
        panel.setEncoding(Encoding.MULTIPART);
        panel.setMethod(Method.POST);
        panel.setButtonAlign(HorizontalAlignment.CENTER);
        panel.setWidth(350);

        clientName = new TextField<String>();
        clientName.setFieldLabel("Client Name");
        clientName.setEnabled(false);
        clientName.setName("Client Name");
        if(!hideFields)
            panel.add(clientName);

        clientId = new TextField<String>();
        clientId.setFieldLabel("Client Id");
        clientId.setEnabled(false);
        if(!hideFields)
            panel.add(clientId);

        policyNumber = new TextField<String>();
        policyNumber.setFieldLabel("Policy Number");
        policyNumber.setEnabled(false);
        if(!hideFields)
            panel.add(policyNumber);

        descriptionField = new TextArea();
        descriptionField.setFieldLabel("Description");
        descriptionField.setAllowBlank(false);
        descriptionField.setHeight(70);
        panel.add(descriptionField);

        descriptionField.addListener(Events.Change,
                new Listener<FieldEvent>() {

                    @Override
                    public void handleEvent(FieldEvent be) {

                        if(descriptionField.getValue() == null)
                        {
                            return;
                        }
                        else
                        {
                            descriptionFieldValue = descriptionField.getValue();
                            String user = Registry.get("user");
                            fieldName = fieldName +":"+descriptionFieldValue+":"+user;
                            if(file != null)
                            {
                                file.setName(fieldName);
                            }
                        }

                    }
                });

        file = new FileUploadField();
        file.setAllowBlank(false);
        file.setFieldLabel("File");
        panel.add(file);

        Button btn = new Button("Reset");
        btn.addSelectionListener(new SelectionListener<ButtonEvent>() {
            @Override
            public void componentSelected(ButtonEvent ce) {
                panel.reset();
            }
        });
        panel.addButton(btn);

        btn = new Button("Submit");
        btn.addSelectionListener(new SelectionListener<ButtonEvent>() {
            @Override
            public void componentSelected(ButtonEvent ce) {
                if (!panel.isValid()) {
                    return;
                }
                // normally would submit the form but for example no server set up to
                // handle the post
                panel.submit();
                //MessageBox.info("Action", "You file was uploaded", null);
                final MessageBox box = MessageBox.wait("Progress",
                        "Saving your data, please wait...", "Saving...");
                Timer t = new Timer() {

                    @Override
                    public void run() {
                        Info.display("Message", "Please after few minutes, clients will be created.", "");
                        box.close();
                        TabPanel tabPanel = Registry.get("tabPanel");
                        tabPanel.getSelectedItem().close();
                    }

                };
                t.schedule(10000);
            }
        });
        panel.addButton(btn);

        vp.add(panel);
    }

}