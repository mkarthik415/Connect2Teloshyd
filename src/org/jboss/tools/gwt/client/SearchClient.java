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
	
	public SearchClient(){
		setHeaderVisible(false);
		setBodyBorder(false);
	}
	
	private VerticalPanel vp;

	final Logger logger = Logger.getLogger("logger");
	private FormData formData;
	private TextField<String> name = new TextField<String>();
	private TextField<String> carNumber = new TextField<String>();
	private TextField<String> serialNo = new TextField<String>();
	private TextField<String> policyNo = new TextField<String>();
	private TextField<String> telePhoneNo = new TextField<String>();
	private SimpleComboBox<String> searchFieldBox = new SimpleComboBox<String>();
	private DateField fromDate = new DateField();
	private DateField toDate = new DateField();
	private String fetchBy = null;
	private String fetchByName = "Name";
	private String fetchBySerialNo = "Serial no";
	private String fetchByCarNumber = "Car Number";
	private String fetchbyPolicyDates = "Policy Issue Date";
	private String policyCertificateNo = "Policy/Certificate No";
	private String fetchByTelephoneNo = "Telephone No";

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
							policyNo.setVisible(false);
							telePhoneNo.setVisible(false);
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
							policyNo.setVisible(false);
							telePhoneNo.setVisible(false);
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
							policyNo.setVisible(false);
							telePhoneNo.setVisible(false);
							fetchBy = fetchbyPolicyDates;
						} else if (se.getSelectedItem().getValue()
								.equals("Serial no")) {
							carNumber.setVisible(false);
							name.setVisible(false);
							toDate.setVisible(false);
							fromDate.setVisible(false);
							serialNo.setVisible(true);
							submitButton.setVisible(true);
							cancelButton.setVisible(true);
							policyNo.setVisible(false);
							telePhoneNo.setVisible(false);
							fetchBy = fetchBySerialNo;
						} else if (se.getSelectedItem().getValue()
								.equals("Policy/Certificate No")) {
							carNumber.setVisible(false);
							name.setVisible(false);
							toDate.setVisible(false);
							fromDate.setVisible(false);
							serialNo.setVisible(false);
							submitButton.setVisible(true);
							cancelButton.setVisible(true);
							policyNo.setVisible(true);
							telePhoneNo.setVisible(false);
							fetchBy = policyCertificateNo;
						}else if (se.getSelectedItem().getValue()
								.equals("Telephone No")) {
							carNumber.setVisible(false);
							name.setVisible(false);
							toDate.setVisible(false);
							fromDate.setVisible(false);
							serialNo.setVisible(false);
							submitButton.setVisible(true);
							cancelButton.setVisible(true);
							policyNo.setVisible(false);
							telePhoneNo.setVisible(true);
							fetchBy = fetchByTelephoneNo;
						}
					}
				});

		submitButton.addListener(Events.OnClick, new Listener<ButtonEvent>() {

			@Override
			public void handleEvent(ButtonEvent e) {

				submitButton.disable();
				c = new Client();
				 if(fetchBy.equals(fetchBySerialNo))
					{
					 c.setId(serialNo.getValue());
					 ((GreetingServiceAsync) GWT.create(GreetingService.class))
						.searchClientsBySrialNo(c, new AsyncCallback<List<Clients>>() {
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
				 else if (fetchBy.equals(policyCertificateNo))
					{
					 c.setPolicyNumber(policyNo.getValue());
					 ((GreetingServiceAsync) GWT.create(GreetingService.class))
						.searchClientsByPolicyNo(c, new AsyncCallback<List<Clients>>() {
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
				//Search by name server side code.
				 else if (fetchBy.equals(fetchByName)) {
					fetchBy = null;
					c.setClientName(name.getValue());
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
				//search by car number on server side
				else if(fetchBy.equals(fetchByCarNumber))
				{
					fetchBy = null;
					c.setVehicleNumber(carNumber.getValue());
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
				//search by from and to date of the policy server side
				
				else if(fetchBy.equals(fetchbyPolicyDates))
				{
					fetchBy = null;
					c.setFromDate(fromDate.getValue());
					c.setToDate(toDate.getValue());
					((GreetingServiceAsync) GWT.create(GreetingService.class))
					.searchClientsByPolicyDates(c, new AsyncCallback<List<Clients>>() {
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
				 
				else if(fetchBy.equals(fetchByTelephoneNo))
				{
					fetchBy = null;
					c.setPhoneNumber(telePhoneNo.getValue());
					((GreetingServiceAsync) GWT.create(GreetingService.class))
					.searchClientsByPhoneNum(c, new AsyncCallback<List<Clients>>() {
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
				carNumber.clear();
				toDate.clear();
				fromDate.clear();
				serialNo.clear();
				telePhoneNo.clear();
				fetchBy =null;
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
		searchFieldBox.add("Policy/Certificate No");
		searchFieldBox.add("Telephone No");

		searchFieldBox.setFieldLabel("Search By");
		searchFieldBox.setTriggerAction(TriggerAction.ALL);

		name.setFieldLabel("Name");
		name.setAllowBlank(false);
		name.setVisible(false);
		name.getFocusSupport().setPreviousId(simple.getButtonBar().getId());

		carNumber.setFieldLabel("Vehicle Number");
		carNumber.setAllowBlank(false);
		carNumber.setVisible(false);
		carNumber.getFocusSupport()
				.setPreviousId(simple.getButtonBar().getId());

		serialNo.setFieldLabel("Serial No");
		serialNo.setAllowBlank(false);
		serialNo.setVisible(false);
		serialNo.getFocusSupport().setPreviousId(simple.getButtonBar().getId());
		
		policyNo.setFieldLabel("Policy No");
		policyNo.setAllowBlank(false);
		policyNo.setVisible(false);
		policyNo.getFocusSupport().setPreviousId(simple.getButtonBar().getId());

		fromDate.setFieldLabel("From Date");
		fromDate.setVisible(false);
		toDate.setFieldLabel("To Date");
		toDate.setVisible(false);
		
		telePhoneNo.setFieldLabel("Telephone No");
		telePhoneNo.setAllowBlank(false);
		telePhoneNo.setVisible(false);
		telePhoneNo.getFocusSupport().setPreviousId(simple.getButtonBar().getId());

		simple.add(searchFieldBox, formData);
		simple.add(name, formData);
		simple.add(carNumber, formData);
		simple.add(serialNo, formData);
		simple.add(fromDate, new FormData("10%"));
		simple.add(toDate, new FormData("1%"));
		simple.add(policyNo, formData);
		simple.add(telePhoneNo, formData);

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
