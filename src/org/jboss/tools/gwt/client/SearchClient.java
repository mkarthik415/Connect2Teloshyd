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
import com.extjs.gxt.ui.client.event.SelectionChangedEvent;
import com.extjs.gxt.ui.client.event.SelectionChangedListener;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.MessageBox;
import com.extjs.gxt.ui.client.widget.TabItem;
import com.extjs.gxt.ui.client.widget.TabPanel;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.ComboBox.TriggerAction;
import com.extjs.gxt.ui.client.widget.form.DateField;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.SimpleComboBox;
import com.extjs.gxt.ui.client.widget.form.SimpleComboValue;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.layout.FormData;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class SearchClient extends ContentPanel {
	private VerticalPanel vp;

	final Logger logger = Logger.getLogger("logger");
	private FormData formData;
	TextField<String> name = new TextField<String>();
	TextField<String> carNumber = new TextField<String>();
	TextField<String> serialNo = new TextField<String>();
	SimpleComboBox<String> searchFieldBox = new SimpleComboBox<String>();
	DateField fromDate = new DateField();
	DateField toDate = new DateField();
	private String fetchBy = null;
	private String fetchByName = "Name";
	private String fetchBySerialNo = "serialNo";
	private String fetchByCarNumber = "serialNo";

	MessageBox messageBox = new MessageBox();

	@Override
	protected void onRender(Element parent, int index) {
		super.onRender(parent, index);
		formData = new FormData("-20");
		vp = new VerticalPanel();
		vp.setSpacing(10);
		createForm1();
		add(vp);

		searchFieldBox
				.addSelectionChangedListener(new SelectionChangedListener<SimpleComboValue<String>>() {

					@Override
					public void selectionChanged(
							SelectionChangedEvent<SimpleComboValue<String>> se) {
						if (se.getSelectedItem().getValue().equals("Name")) {

							name.setVisible(true);
							submitButton.setVisible(true);
							cancelButton.setVisible(true);
							carNumber.setVisible(false);
							toDate.setVisible(false);
							fromDate.setVisible(false);
							serialNo.setVisible(false);
							fetchBy = fetchByName;
						} else if (se.getSelectedItem().getValue()
								.equals("Car Number")) {
							carNumber.setVisible(true);
							name.setVisible(false);
							submitButton.setVisible(true);
							cancelButton.setVisible(true);
							toDate.setVisible(false);
							fromDate.setVisible(false);
							serialNo.setVisible(false);
							fetchBy = fetchByCarNumber;
						} else if (se.getSelectedItem().getValue()
								.equals("Policy Issue Date")) {
							carNumber.setVisible(false);
							name.setVisible(false);
							toDate.setVisible(true);
							fromDate.setVisible(true);
							submitButton.setVisible(true);
							cancelButton.setVisible(true);
							serialNo.setVisible(false);
						} else if (se.getSelectedItem().getValue()
								.equals("Serial no")) {
							carNumber.setVisible(false);
							name.setVisible(false);
							toDate.setVisible(false);
							fromDate.setVisible(false);
							serialNo.setVisible(true);
							submitButton.setVisible(true);
							cancelButton.setVisible(true);
						}
					}
				});

		submitButton.addListener(Events.OnClick, new Listener<ButtonEvent>() {

			@Override
			public void handleEvent(ButtonEvent e) {

				submitButton.disable();
				c = new Client();
				c.setClientName(name.getValue());
				c.setVehicleNumber(carNumber.getValue());
				if (fetchBy.equals(fetchByName)) {
					((GreetingServiceAsync) GWT.create(GreetingService.class))
							.searchClients(c,
									new AsyncCallback<List<Clients>>() {
										public void onFailure(Throwable caught) {
											// Show the RPC error message to the
											// user
											MessageBox messageBox = new MessageBox();
											messageBox
													.setMessage("Sorry we are not able to find the client right now. Please try later !!");
											messageBox.show();
										}

										public void onSuccess(
												List<Clients> result) {
											submitButton.enable();
											logger.log(Level.SEVERE,
													"inside Clent ");
											try {
												TabPanel tabPanel = Registry
														.get("tabPanel");
												tabPanel.getSelectedItem()
														.close();
												TabItem item = new TabItem();
												item.setText("Search Results");
												item.setClosable(true);
												SearchGrid searchResultGrid = new SearchGrid();
												searchResultGrid
														.setBodyBorder(false);
												searchResultGrid
														.setBorders(false);
												SearchGrid.getClients(result);
												item.add(searchResultGrid);
												tabPanel.add(item);
												tabPanel.setSelection(item);
											} catch (Exception ex) {
												logger.log(Level.SEVERE,
														"exception at ui level"
																+ ex.toString());
											}
										}
									});
				}
				else if(fetchBy.equals(fetchByCarNumber))
				{
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

		searchFieldBox.add("Name");
		searchFieldBox.add("Car Number");
		searchFieldBox.add("Serial no");
		searchFieldBox.add("Policy Issue Date");

		searchFieldBox.setFieldLabel("Search By");
		searchFieldBox.setTriggerAction(TriggerAction.ALL);

		name.setFieldLabel("Name");
		name.setAllowBlank(false);
		name.setVisible(false);
		name.getFocusSupport().setPreviousId(simple.getButtonBar().getId());

		carNumber.setFieldLabel("Car Number");
		carNumber.setAllowBlank(false);
		carNumber.setVisible(false);
		carNumber.getFocusSupport()
				.setPreviousId(simple.getButtonBar().getId());

		serialNo.setFieldLabel("Serial No");
		serialNo.setAllowBlank(false);
		serialNo.setVisible(false);
		serialNo.getFocusSupport().setPreviousId(simple.getButtonBar().getId());

		fromDate.setFieldLabel("From Date");
		fromDate.setVisible(false);
		toDate.setFieldLabel("To Date");
		toDate.setVisible(false);

		simple.add(searchFieldBox, formData);
		simple.add(name, formData);
		simple.add(carNumber, formData);
		simple.add(serialNo, formData);
		simple.add(fromDate, new FormData("10%"));
		simple.add(toDate, new FormData("1%"));

		submitButton = new Button("Submit");
		submitButton.setVisible(false);
		simple.addButton(submitButton);
		cancelButton = new Button("Cancel");
		cancelButton.setVisible(false);
		simple.addButton(cancelButton);

		simple.setButtonAlign(HorizontalAlignment.CENTER);

		vp.add(simple);
	}

	Button submitButton;
	Button cancelButton;
	Client c;

}
