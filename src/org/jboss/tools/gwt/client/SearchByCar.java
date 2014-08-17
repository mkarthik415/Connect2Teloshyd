package org.jboss.tools.gwt.client;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.jboss.tools.gwt.shared.Client;
import org.jboss.tools.gwt.shared.Clients;

import com.extjs.gxt.ui.client.Registry;
import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.MessageBox;
import com.extjs.gxt.ui.client.widget.TabItem;
import com.extjs.gxt.ui.client.widget.TabPanel;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.layout.FormData;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class SearchByCar extends ContentPanel {
	
	public SearchByCar(){
		setHeaderVisible(false);
		setBodyBorder(false);
	}
	
	private VerticalPanel vp;

	final Logger logger = Logger.getLogger("logger");
	private FormData formData;
	TextField<String> name = new TextField<String>();

	@Override
	protected void onRender(Element parent, int index) {
		super.onRender(parent, index);
		formData = new FormData("-20");
		vp = new VerticalPanel();
		vp.setSpacing(10);
		createForm1();
		add(vp);

		submitButton.addListener(Events.OnClick, new Listener<ButtonEvent>() {

			@Override
			public void handleEvent(ButtonEvent e) {

				submitButton.disable();
				c = new Client();
				c.setVehicleNumber(name.getValue());
				((GreetingServiceAsync) GWT.create(GreetingService.class))
						.searchClientsByCarNum(c, new AsyncCallback<List<Clients>>() {
							public void onFailure(Throwable caught) {
								// Show the RPC error message to the user
								MessageBox messageBox = new MessageBox();
								messageBox
										.setMessage("Sorry we are not able to find the client right now. Please try later !!");
								messageBox.show();
							}

							public void onSuccess(List<Clients> result) {
								submitButton.enable();
								logger.log(Level.SEVERE, "inside Clent ");
								try {
									TabPanel tabPanel = Registry.get("tabPanel");
									tabPanel.getSelectedItem().close();
									TabItem item = new TabItem();
				              		item.setText("Search Results");
				              		item.setClosable(true);
				              		SearchGrid searchResultGrid = new SearchGrid();
				              		searchResultGrid.setBodyBorder(false);
				              		searchResultGrid.setBorders(false);
				              		SearchGrid.getClients(result);
				              		item.add(searchResultGrid);
				                    tabPanel.add(item);
				                    tabPanel.setSelection(item);
								} catch (Exception ex) {
									logger.log(
											Level.SEVERE,
											"exception at ui level"
													+ ex.toString());
								}
							}
						});
			}

		});
		
		cancelButton.addListener(Events.OnClick, new Listener<ButtonEvent>() {

			@Override
			public void handleEvent(ButtonEvent e) {
				name.clear();
				submitButton.enable();
			}

		});
	}

	private void createForm1() {
		FormPanel simple = new FormPanel();
		simple.setHeading("Search Client");
		simple.setFrame(true);
		simple.setWidth(350);
		simple.setBorders(false);

		name.setFieldLabel("Car Number");
		name.setAllowBlank(false);
		name.getFocusSupport().setPreviousId(simple.getButtonBar().getId());
		simple.add(name, formData);

		submitButton = new Button("Submit");
		simple.addButton(submitButton);
		cancelButton = new Button("Cancel");
		simple.addButton(cancelButton);

		simple.setButtonAlign(HorizontalAlignment.CENTER);



		vp.add(simple);
	}

	Button submitButton;
	Button cancelButton;
	Client c;

}
