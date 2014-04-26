package org.jboss.tools.gwt.client;

import com.extjs.gxt.ui.client.Registry;
import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.event.*;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.*;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.Radio;
import com.extjs.gxt.ui.client.widget.grid.*;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.extjs.gxt.ui.client.widget.layout.FlowLayout;
import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.datepicker.client.CalendarUtil;
import org.jboss.tools.gwt.shared.Client;
import org.jboss.tools.gwt.shared.Clients;
import org.jboss.tools.gwt.shared.File;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SearchGrid extends ContentPanel {

	NewClientForm newClientForm;
	DateTimeFormat dformat = DateTimeFormat.getFormat("yyyy-mm-dd");
	Logger logger = Logger.getLogger("logger");
	Client c;

	@Override
	protected void onRender(Element parent, int index) {
		super.onRender(parent, index);
		setLayout(new FlowLayout(10));

		GridCellRenderer<Clients> buttonRenderer = new GridCellRenderer<Clients>() {

			private boolean init;
			@SuppressWarnings("unused")
			Radio radio;

			public Object render(final Clients model, String property,
					ColumnData config, final int rowIndex, final int colIndex,
					ListStore<Clients> store, Grid<Clients> grid) {
				if (!init) {
					init = true;
					grid.addListener(Events.ColumnResize,
							new Listener<GridEvent<Clients>>() {

								public void handleEvent(GridEvent<Clients> be) {
									for (int i = 0; i < be.getGrid().getStore()
											.getCount(); i++) {
										if (be.getGrid().getView()
												.getWidget(i, be.getColIndex()) != null
												&& be.getGrid()
														.getView()
														.getWidget(
																i,
																be.getColIndex()) instanceof BoxComponent) {
											((BoxComponent) be
													.getGrid()
													.getView()
													.getWidget(i,
															be.getColIndex()))
													.setWidth(be.getWidth() - 10);
										}
									}
								}
							});
				}

				Button b = new Button((String) model.get(property),
						new SelectionListener<ButtonEvent>() {
							@SuppressWarnings("deprecation")
							@Override
							public void componentSelected(ButtonEvent ce) {
								logger.log(Level.SEVERE, "inside search grid");
								Info.display(model.getName(), "<ul><li>"
										+ "</li></ul>");
								TabPanel tabPanel = Registry.get("tabPanel");
								tabPanel.getSelectedItem().close();
								tabPanel.setBorders(false);
								tabPanel.setBorderStyle(false);
								newClientForm = new NewClientForm();
								TabItem item = new TabItem();
								item.setText("Client-"+model.getId().toString());
								item.setClosable(true);
								item.setBorders(false);
								item.add(newClientForm);
								newClientForm.serialNumberStatus = true;
								newClientForm.iD = model.getId().toString();
								newClientForm.fieldSetFound = model
										.getDepartment();
								newClientForm.agentFound = model.getAgent();
								newClientForm.insuranceCompanyFound = model
										.getInsCompanyName();
								newClientForm.companyNameFound = model
										.getCompany();
								newClientForm.genderFound = model.getGender();
								newClientForm.industryFound = model
										.getIndustry();
								newClientForm.updateButton = true;
								newClientForm.nameField.setValue(model
										.getName());
								newClientForm.mobileField.setValue(model
										.getPhoneNumber());
								if(model.getSecondaryPhoneNumber() != null)
								{
									newClientForm.secondaryMobilefound = true;									
								}
								newClientForm.secondaryMobileField.setValue(model
										.getSecondaryPhoneNumber());
								newClientForm.dateOfBirthField.setValue(model
										.getDob());
								// newClientForm.company.setValue(model.getCompany());
								newClientForm.emailField.setValue(model
										.getEmail());
								if(model.getSecondaryEmail() != null)
								{
									newClientForm.secondaryEmailfound = true;
								}
								newClientForm.secondaryEmailField.setValue(model
										.getSecondaryEmail());
								newClientForm.addressField.setValue(model
										.getAddress());
								newClientForm.agentFieldBox
										.setSimpleValue(model.getAgent());
								// Tab #1 complete
								newClientForm.policyNoField.setValue(model
										.getPolicyNumber());
								newClientForm.endrsNoField.setValue(model
										.getEndrsNumber());
								newClientForm.policyFromDateField
										.setValue(model.getPolicyStartdate());

                                if(model.getPolicyEndDate() != null)
                                {

                                    Date tempEndDate = model.getPolicyEndDate();
                                    CalendarUtil.addMonthsToDate(tempEndDate,1);
                                    newClientForm.policyToDateField.setValue(tempEndDate);
                                }
								newClientForm.insCompanyBranchField
										.setValue(model.getInsBranchName());
								newClientForm.officeCodeField.setValue(model
										.getOfficeCode());
								if(model.getSource() != null)
								{
									newClientForm.sourceFound = true;
								}
								newClientForm.sourceField.setValue(model
										.getSource());
								newClientForm.AgentField.setValue(model
										.getAgent());
								newClientForm.policyDetailsField.setValue(model
										.getPolicyDetails());
								// Tab #2 complete
								newClientForm.typeOfPolicyField.setValue(model
										.getFireTypeOfPolicy());
								newClientForm.basicRateField.setValue(model
										.getBasicRate());
								newClientForm.earthQuakecField.setValue(model
										.getEarthQuakePremium());
								newClientForm.anyAdditionalField.setValue(model
										.getAnyAdditionalPremium());
								// fire complete
								newClientForm.specificPolicyField
										.setValue(model.getMarineTypeOfPolicy());
								newClientForm.openPolicyField.setValue(model
										.getMarineOpenPolicy());
								newClientForm.openCoverField.setValue(model
										.getMarineOpenCover());
								newClientForm.otherPoliciesField.setValue(model
										.getMarineOtherPolicies());
								newClientForm.voyageFromField.setValue(model
										.getMarineVoyageFrom());
								newClientForm.voyageToField.setValue(model
										.getMarineVoyageTo());
								// Marine complete
								newClientForm.vehicleNoField.setValue(model
										.getVehicleNumber());
								newClientForm.iDVField.setValue(model.getIDV());
								newClientForm.vehicleMakeField.setValue(model
										.getVehicleMake());
								if (newClientForm.manufacturingYearFound == null
										&& model.getVehicleManufactureYear() != null) {

									newClientForm.manufacturingYearFound = String
											.valueOf(model
													.getVehicleManufactureYear()
													.getYear() + 1900);
								}
								newClientForm.nCBField.setValue(model.getNBC());
								// Vehicle Complete
								newClientForm.misTypeOfPolicyField
										.setValue(model.getMiscTypeOfPolicy());
								// miscellaneous Complete
								newClientForm.sumInsuredField.setValue(model
										.getSumInsured());
								newClientForm.misIdCardField.setValue(model
										.getMiscIdCard());
								newClientForm.dispatchDateField.setValue(model
										.getMiscDispatchDate());
								// engineering fielsset
								newClientForm.premiunAmountField.setValue(model
										.getPremiumAmount());

								newClientForm.terrorismPremiunAmountField
										.setValue(model
												.getTerrorismPremiumAmount());
								newClientForm.serviceTaxField.setValue(model
										.getServiceTax());
								newClientForm.serviceTaxAmountField
										.setValue(model.getServiceTaxAmount());
								newClientForm.totalPremiunAmountField
										.setValue(model.getTotalPremiumAmount());
								newClientForm.commisionRateField.setValue(model
										.getCommionRate());
								newClientForm.commisionRateAmountField
										.setValue(model.getCommionRateAmount());
								newClientForm.collectionDate.setValue(model
										.getCollectionDate());
                                if(model.getPolicyEndDate() != null)
                                {

                                    Date compareDate = model.getPolicyEndDate();
                                    CalendarUtil.addMonthsToDate(compareDate,-1);
                                    Date today = new Date();
                                    if(compareDate != null && CalendarUtil.isSameDate(compareDate,today) || today.after(compareDate))
                                    {
                                        newClientForm.renewalStatus = true;
                                        newClientForm.renewalAmountField.setValue(model.getRenewalAmount());
                                    }
                                }
								//newClientForm.setDocumentsList(model);
								Registry.register("fieldset",
										model.getDepartment());
								tabPanel.add(item);
								tabPanel.setSelection(item);
								tabPanel.setBorders(false);
							}
						});
				b.setWidth(grid.getColumnModel().getColumnWidth(colIndex) - 10);
				b.setToolTip("Click here to update Client");

				return b;

			}
		};

		GridCellRenderer<Clients> buttonDispatchRenderer = new GridCellRenderer<Clients>() {

			private boolean init;

			@Override
			public Object render(final Clients model, String property,
					ColumnData config, int rowIndex, int colIndex,
					ListStore<Clients> store, Grid<Clients> grid) {

				if (!init) {
					init = true;
					grid.addListener(Events.ColumnResize,
							new Listener<GridEvent<Clients>>() {

								public void handleEvent(GridEvent<Clients> be) {
									for (int i = 0; i < be.getGrid().getStore()
											.getCount(); i++) {
										if (be.getGrid().getView()
												.getWidget(i, be.getColIndex()) != null
												&& be.getGrid()
														.getView()
														.getWidget(
																i,
																be.getColIndex()) instanceof BoxComponent) {
											((BoxComponent) be
													.getGrid()
													.getView()
													.getWidget(i,
															be.getColIndex()))
													.setWidth(be.getWidth() - 10);
										}
									}
								}
							});
				}

				Button bDispactch = new Button((String) model.get(property),
						new SelectionListener<ButtonEvent>() {
							@Override
							public void componentSelected(ButtonEvent ce) {
								TabPanel tabPanel = Registry.get("tabPanel");
								//tabPanel.getSelectedItem().close();
								TabItem item = new TabItem();
								final DispatchForm dispatchForm = new DispatchForm();
								item.setText("Dispatch Client");
								item.setClosable(true);
								item.add(dispatchForm);
								dispatchForm.sno.setValue(model.getId().toString());
								dispatchForm.name.setValue(model.getName());
								dispatchForm.mobileField.setValue(model
										.getPhoneNumber());
								dispatchForm.emailField.setValue(model
										.getEmail());
								
								//loading data to the grid
								
								c = new Client();
								c.setId(model.getId().toString());
								((GreetingServiceAsync) GWT.create(GreetingService.class))
								.getUploadedDocumentsForClient(c, new AsyncCallback<List<File>>() {

									@Override
									public void onFailure(Throwable caught) {
										MessageBox messageBox = new MessageBox();
										messageBox
												.setMessage("Sorry we are not able to find the Documents right now. " +
														"Please try later !!");
										messageBox.show();
										
									}

									@Override
									public void onSuccess(List<File> result) {
										ListStore<File> newStore = new ListStore<File>();
										List<File> stocks = new ArrayList<File>();
										stocks = result;
										if(stocks.isEmpty() || stocks == null)
										{
											dispatchForm.documentsCP.setVisible(false);
											dispatchForm.simple.setHeight(425);
										}
										else
										{
											newStore.add(stocks);
											dispatchForm.grid.reconfigure(newStore, dispatchForm.cm);
										}
										
										
									}
									
								});
								
								tabPanel.add(item);
								tabPanel.setSelection(item);

							}
						});

				bDispactch.setWidth(grid.getColumnModel().getColumnWidth(
						colIndex) - 20);
				bDispactch.setToolTip("Click here to send Dispatch Details");

				return bDispactch;
			}

		};

		List<ColumnConfig> configs = new ArrayList<ColumnConfig>();

		ColumnConfig column = new ColumnConfig();
		column.setId("id");
		column.setHeader("S.No");
		column.setWidth(50);
		configs.add(column);

		column = new ColumnConfig();
		column.setId("name");
		column.setHeader("Name");
		column.setWidth(200);
		configs.add(column);

		column = new ColumnConfig();
		column.setId("department");
		column.setHeader("Department");
		column.setRenderer(buttonRenderer);
		column.setWidth(125);
		configs.add(column);
		
		column = new ColumnConfig();
		column.setId("policyNumber");
		column.setHeader("Policy Number");
		column.setWidth(165);
		configs.add(column);

		column = new ColumnConfig();
		column.setId("policyStartdate");
		column.setHeader("Start Date");
		column.setWidth(75);
		configs.add(column);

		column = new ColumnConfig();
		column.setId("policyEndDate");
		column.setHeader("End Date");
		column.setWidth(75);
		configs.add(column);

		column = new ColumnConfig();
		column.setId("InsCompanyName");
		column.setHeader("Ins Company Name");
		column.setWidth(200);
		configs.add(column);

		column = new ColumnConfig();
		column.setId("phoneNumber");
		column.setHeader("Dispatch");
		column.setRenderer(buttonDispatchRenderer);
		column.setWidth(150);
		configs.add(column);

		ListStore<Clients> store = new ListStore<Clients>();
		store.add(stocks);

		ColumnModel cm = new ColumnModel(configs);

		ContentPanel cp = new ContentPanel();
		cp.setBodyBorder(false);
		// cp.setIcon(Resources.ICONS.table());
		cp.setHeading("Clients Found");
		cp.setButtonAlign(HorizontalAlignment.CENTER);
		cp.setLayout(new FitLayout());
		cp.setSize(1125, 600);

		Grid<Clients> grid = new Grid<Clients>(store, cm);
		grid.setStyleAttribute("borderTop", "none");
		// grid.setAutoExpandColumn("name");
		grid.setBorders(true);
		grid.setStripeRows(true);
		cp.add(grid);

		add(cp);
	}

	public static void getClients(List<Clients> result) {

		SearchGrid.stocks = result;

	}

	private static List<Clients> stocks = new ArrayList<Clients>();

}