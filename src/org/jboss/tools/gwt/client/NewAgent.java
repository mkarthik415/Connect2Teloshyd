package org.jboss.tools.gwt.client;

import org.jboss.tools.gwt.shared.Agent;

import com.extjs.gxt.ui.client.Registry;
import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
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

public class NewAgent extends LayoutContainer{
	private VerticalPanel vp;
	private FormData formData;
	TextField<String> name = new TextField<String>();
	TextField<String> screenName = new TextField<String>();
	Agent agent = null;

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
				agent = new Agent();
				agent.setName(name.getValue());
				agent.setScreenName(screenName.getValue());
				((GreetingServiceAsync) GWT.create(GreetingService.class))
						.createAgent(agent, new AsyncCallback<String>() {
							public void onFailure(Throwable caught) {
								// Show the RPC error message to the user
								MessageBox messageBox = new MessageBox();
								messageBox
								.setMessage("Agent not created !!");
								messageBox.show();
								submitButton.enable();
							}

							@Override
							public void onSuccess(String arg0) {
								// TODO Auto-generated method stub
								MessageBox messageBox = new MessageBox();
								messageBox
										.setMessage("New Agent in connect2telos created !!");
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
		name.setEmptyText("Enter agents full name.");
		name.getFocusSupport().setPreviousId(simple.getButtonBar().getId());
		simple.add(name, new FormData("50%"));
		
		screenName.setFieldLabel("Screen Name");
		screenName.setAllowBlank(false);
		screenName.setEmptyText("Enter agents screen name.");
		screenName.getFocusSupport().setPreviousId(simple.getButtonBar().getId());
		simple.add(screenName, new FormData("50%"));
		
		submitButton = new Button("New Agent");
		simple.addButton(submitButton);
		
		simple.setButtonAlign(HorizontalAlignment.CENTER);

		/*
		 * FormButtonBinding binding = new FormButtonBinding(simple);
		 * binding.addButton(b);
		 */

		vp.add(simple);
	}
	
	Button submitButton;
}
