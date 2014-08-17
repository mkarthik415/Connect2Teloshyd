package org.jboss.tools.gwt.client;
import com.extjs.gxt.ui.client.Registry;
import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.event.*;
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
//import com.google.gwt.user.client.ui.FormPanel;
@SuppressWarnings("deprecation")
public class UploadExcel extends ContentPanel{
	
	
	public UploadExcel(){
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
    MessageBox box = null;
	  
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
    panel.setAction("renewal");
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
          box = MessageBox.wait("Progress",
                  "Processing... your data, please wait...", "Sending SMS's...Sending Email's");
          panel.submit();
      }
    });
   panel.addButton(btn);

        panel.addListener(Events.Submit, new Listener<FormEvent>() {


            public void handleEvent(FormEvent fe) {
                box.close();
                MessageBox.info("Status", "Total processed records are " + fe.getResultHtml(), null);
            }
        });

    vp.add(panel);
	}
	  
	}  