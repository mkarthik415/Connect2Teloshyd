package org.jboss.tools.gwt.client;

import com.extjs.gxt.ui.client.Registry;
import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.event.*;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.Dialog;
import com.extjs.gxt.ui.client.widget.Info;
import com.extjs.gxt.ui.client.widget.MessageBox;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.FileUploadField;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.FormPanel.Encoding;
import com.extjs.gxt.ui.client.widget.form.FormPanel.Method;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.FormHandler;
import com.google.gwt.user.client.ui.FormSubmitCompleteEvent;
import com.google.gwt.user.client.ui.FormSubmitEvent;

public class UploadDocuments extends ContentPanel implements FormHandler {

    MessageBox box = null;
    FileUploadField file;
    String filePath = null;
    TextField<String> name = null;
    String requestData = null;
    String fileName = null;

    public UploadDocuments() {
        setHeaderVisible(false);
        setBodyBorder(false);
    }

    @Override
    protected void onRender(Element parent, int index) {
        super.onRender(parent, index);

        final FormPanel panel = new FormPanel();
        panel.setHeading("Documents Upload");
        panel.setFrame(true);
        panel.setAction("insertImages");
        panel.setEncoding(Encoding.MULTIPART);
        panel.setMethod(Method.POST);
        panel.setButtonAlign(HorizontalAlignment.CENTER);
        panel.setWidth(350);


        name = new TextField<String>();
        name.setFieldLabel("Name");
        panel.add(name);

        file = new FileUploadField();
        file.setAllowBlank(false);
        file.setFieldLabel("Select Image");
        panel.add(file);


        file.addListener(Events.Change,
                new Listener<FieldEvent>() {

                    @Override
                    public void handleEvent(FieldEvent be) {

                        if (file.getValue() == null) {
                            return;
                        } else {
                            filePath = file.getValue();
                            fileName = filePath.substring(filePath.lastIndexOf("\\") + 1, filePath.length());
                            if (null == name.getValue()) {
                                name.setValue(fileName);
                            }
                            String user = Registry.get("user");
                            requestData = requestData + ":" + user;
                            if (file != null) {
                                file.setName(requestData);
                            }
                        }

                    }
                });

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
                box = MessageBox.wait("Progress",
                        "Saving your data, please wait...", "Saving...");
                panel.submit();
            }
        });
        panel.addButton(btn);

        panel.addListener(Events.Submit, new Listener<FormEvent>() {


            public void handleEvent(FormEvent fe) {

                box.close();
                final String url = fe.getResultHtml();
                MessageBox.info("Status", "Total processed records are " + fe.getResultHtml(), new Listener<MessageBoxEvent>() {
                    @Override
                    public void handleEvent(MessageBoxEvent be) {
                        if (be.getButtonClicked().getItemId().equals(Dialog.OK)) {
                            Announcement announcement = Registry.get("announcement");
                            System.out.println(announcement.getEmailNotes().getRawValue());
                            announcement.emailNotes.getExtendedFormatter().insertImage(url);
                        }
                    }
                });
            }
        });

        add(panel);
    }

    @Override
    @Deprecated
    public void onSubmit(FormSubmitEvent event) {
        // TODO Auto-generated method stub
        MessageBox.info("Action", "You file was uploaded", null);
    }

    @Override
    @Deprecated
    public void onSubmitComplete(FormSubmitCompleteEvent event) {
        // TODO Auto-generated method stub

        Info.display("Message", "After fake data was saved", "");

    }

}