package org.jboss.tools.gwt.client;

import org.jboss.tools.gwt.shared.Insurance;

import com.extjs.gxt.ui.client.Registry;
import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.MessageBox;
import com.extjs.gxt.ui.client.widget.TabPanel;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.layout.FormData;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class NewInsurance extends ContentPanel{
	
	public NewInsurance(){
		setHeaderVisible(false);
		setBodyBorder(false);
	}
	
	private VerticalPanel vp;
	@SuppressWarnings("unused")
	private FormData formData;
	TextField<String> name = new TextField<String>();
	TextField<String> screenName = new TextField<String>();
	Insurance insurance = null;

	@Override
	protected void onRender(Element parent, int index) {
		super.onRender(parent, index);
		vp = new VerticalPanel();
		vp.setSpacing(10);
		createForm1();
		add(vp);
		
		submitButton.addListener(Events.OnClick, new Listener<ButtonEvent>() {

			@Override
			public void handleEvent(ButtonEvent e) {

				if(name.getValue() == null)
				{
					MessageBox messageBox = new MessageBox();
					messageBox
							.setMessage("please enter Agent Name !!");
					messageBox.show();
					return;
				}
				submitButton.disable();
				insurance = new Insurance();
				insurance.setName(name.getValue());
				insurance.setScreenName(screenName.getValue());
				((GreetingServiceAsync) GWT.create(GreetingService.class))
						.createInsuranceCompony(insurance, new AsyncCallback<String>() {
							public void onFailure(Throwable caught) {
								// Show the RPC error message to the user
								MessageBox messageBox = new MessageBox();
								messageBox
								.setMessage("Insurance Company not created !!");
								messageBox.show();
								submitButton.enable();
							}

							@Override
							public void onSuccess(String arg0) {
								// TODO Auto-generated method stub
								MessageBox messageBox = new MessageBox();
								messageBox
										.setMessage("New Insurance Company in connect2telos created !!");
								messageBox.show();
								submitButton.enable();
								TabPanel tabPanel = Registry.get("tabPanel");
								tabPanel.getSelectedItem().close();
								
							}
						});
			}

		});
	}
	
	private void createForm1() {
		formData = new FormData("100%");
		FormPanel simple = new FormPanel();
		simple.setHeading("Simple Form");
		simple.setFrame(true);
		simple.setWidth(550);

		name.setFieldLabel("Name");
		name.setAllowBlank(false);
		name.setEmptyText("Enter Insurance company Name.");
		name.getFocusSupport().setPreviousId(simple.getButtonBar().getId());
		simple.add(name, new FormData("50%"));
		
		screenName.setFieldLabel("Screen Name");
		screenName.setAllowBlank(false);
		screenName.setEmptyText("Enter Insurance name.");
		screenName.getFocusSupport().setPreviousId(simple.getButtonBar().getId());
		simple.add(screenName, new FormData("50%"));
		
		submitButton = new Button("create company");
		simple.addButton(submitButton);
		
		simple.setButtonAlign(HorizontalAlignment.CENTER);

		vp.add(simple);
	}
	
	Button submitButton;
}
