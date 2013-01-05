package org.jboss.tools.gwt.client;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.jboss.tools.gwt.shared.Client;
import org.jboss.tools.gwt.shared.Clients;
import org.jboss.tools.gwt.shared.SmsLane;
import org.jboss.tools.gwt.shared.User;


import com.extjs.gxt.ui.client.Registry;
import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.MessageBox;
import com.extjs.gxt.ui.client.widget.TabItem;
import com.extjs.gxt.ui.client.widget.TabPanel;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.HtmlEditor;
import com.extjs.gxt.ui.client.widget.form.TextArea;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.layout.FormData;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class DispatchForm extends LayoutContainer {
	private VerticalPanel vp;

	final Logger logger = Logger.getLogger("logger");
	private FormData formData;
	TextField<String> name = new TextField<String>();
	TextField<String> mobileField = new TextField<String>();
	TextField<String> emailField = new TextField<String>();
	TextArea noteField = new TextArea();
	HtmlEditor a = new HtmlEditor();

	@Override
	protected void onRender(Element parent, int index) {
		super.onRender(parent, index);
		formData = new FormData("-20");
		vp = new VerticalPanel();
		vp.setSpacing(10);
		createForm1();
		add(vp);

		eMailButton.addListener(Events.OnClick, new Listener<ButtonEvent>() {

			@Override
			public void handleEvent(ButtonEvent e) {

				if(emailField.getValue() == null)
				{
					MessageBox messageBox = new MessageBox();
					messageBox
							.setMessage("please enter Email ID !!");
					messageBox.show();
					return;
				}
				eMailButton.disable();
				c = new Client();
				c.setClientName(name.getValue());
				c.setEmail(emailField.getValue());
				c.setPhoneNumber(mobileField.getValue());
				((GreetingServiceAsync) GWT.create(GreetingService.class))
						.sendEmail(c, new AsyncCallback<Boolean>() {
							public void onFailure(Throwable caught) {
								// Show the RPC error message to the user
								MessageBox messageBox = new MessageBox();
							}


							@Override
							public void onSuccess(Boolean arg0) {
								// TODO Auto-generated method stub
								MessageBox messageBox = new MessageBox();
								messageBox
										.setMessage("mail send !!");
								messageBox.show();
								eMailButton.enable();
								
							}
						});
			}

		});
		
		sMSButton.addListener(Events.OnClick, new Listener<ButtonEvent>() {

			@Override
			public void handleEvent(ButtonEvent e) {
				
				c = new Client();
				c.setClientName(name.getValue());
				c.setEmail(emailField.getValue());
				c.setPhoneNumber(mobileField.getValue());
				
				eMailButton.enable();
				((GreetingServiceAsync) GWT.create(GreetingService.class))
				.sendSms(c, new AsyncCallback<String>() {
					public void onFailure(Throwable caught) {
						// Show the RPC error message to the user
						MessageBox messageBox = new MessageBox();
						messageBox
						.setMessage("SMS not sent !!");
				messageBox.show();
					}



					@Override
					public void onSuccess(String arg0) {
						// TODO Auto-generated method stub
						if(arg0.equals("Failed#Invalid Mobile Numbers"))
						{
							MessageBox messageBox = new MessageBox();
							messageBox
									.setMessage("Invalid Mobile Numbers !! Number should always start with 91+");
							messageBox.show();
						}
						else
						{
							MessageBox messageBox = new MessageBox();
							messageBox
									.setMessage("SMS sent !!");
							messageBox.show();
						}
					}
						
					});

		}
	});
	}

	private void createForm1() {
		FormPanel simple = new FormPanel();
		simple.setHeading("Simple Form");
		simple.setFrame(true);
		simple.setWidth(350);

		name.setFieldLabel("Name");
		name.setAllowBlank(false);
		name.setEmptyText("Enter clients full name");
		name.getFocusSupport().setPreviousId(simple.getButtonBar().getId());
		simple.add(name, formData);
		
		// mobile filed
		mobileField.setFieldLabel("Phone Number");
		simple.add(mobileField, formData);
		mobileField.setEmptyText("919848334455");
		
		// email field
		emailField.setFieldLabel("Email");
		emailField.setRegex(".+@.+\\.[a-z]+");
		emailField.getMessages().setRegexText("Bad email address!!");
		emailField.setAutoValidate(true);
		simple.add(emailField, formData);
		emailField.setEmptyText("example@example.com");
		
		// address field
		noteField.setFieldLabel("Note");
		noteField.setHeight(70);
		
		simple.add(noteField, formData);
		 
	    a.setFieldLabel("Comment");  
	    a.setHeight(200);
	    simple.add(a, formData);
		
		
	

		eMailButton = new Button("E-Mail");
		simple.addButton(eMailButton);
		sMSButton = new Button("SMS");
		simple.addButton(sMSButton);

		simple.setButtonAlign(HorizontalAlignment.CENTER);

		/*
		 * FormButtonBinding binding = new FormButtonBinding(simple);
		 * binding.addButton(b);
		 */

		vp.add(simple);
	}

	Button eMailButton;
	Button sMSButton;
	Client c;

}
