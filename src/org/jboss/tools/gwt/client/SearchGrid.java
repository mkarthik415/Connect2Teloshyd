package org.jboss.tools.gwt.client;

import com.extjs.gxt.ui.client.Registry;
import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.event.*;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.*;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.Radio;
import com.extjs.gxt.ui.client.widget.form.TextField;
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

import static org.jboss.tools.gwt.util.MathUtil.formatDouble;

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
            Button b;

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

                b = new Button((String) model.get(property));

                b.addListener(Events.OnClick, new Listener<ButtonEvent>() {
                    @Override
                    public void handleEvent(ButtonEvent be) {
                        logger.log(Level.SEVERE, "inside search grid");

                        //get the latest data for the client
                        Client c= new Client();
                        c.setId(model.getId().toString());
                        newClientForm = new NewClientForm();
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
                                        logger.log(Level.SEVERE, "inside Clent ");
                                        try {


                                            Info.display(model.getName(), "<ul><li>"
                                                    + "</li></ul>");
                                            Clients latestClient = result.get(0);
                                            newClientForm.serialNumberStatus = true;
                                            newClientForm.iD = latestClient.getId().toString();
                                            newClientForm.fieldSetFound = latestClient
                                                    .getDepartment();
                                            newClientForm.agentFound = latestClient.getAgent();
                                            newClientForm.insuranceCompanyFound = latestClient
                                                    .getInsCompanyName();
                                            if (latestClient.getrenewalCompany() != null) {
                                                newClientForm.renewalCompanyFound = latestClient.getrenewalCompany();
                                            }
                                            newClientForm.companyNameFound = latestClient
                                                    .getCompany();
                                            newClientForm.genderFound = latestClient.getGender();
                                            newClientForm.industryFound = latestClient
                                                    .getIndustry();
                                            newClientForm.updateButton = true;
                                            newClientForm.nameField.setValue(latestClient
                                                    .getName());
                                            newClientForm.mobileField.setValue(latestClient
                                                    .getPhoneNumber());
                                            if(latestClient.getSecondaryPhoneNumber() != null)
                                            {
                                                newClientForm.secondaryMobilefound = true;
                                            }
                                            newClientForm.secondaryMobileField.setValue(latestClient
                                                    .getSecondaryPhoneNumber());
                                            newClientForm.dateOfBirthField.setValue(latestClient
                                                    .getDob());
                                            // newClientForm.company.setValue(latestClient.getCompany());
                                            newClientForm.emailField.setValue(latestClient
                                                    .getEmail());
                                            if(latestClient.getSecondaryEmail() != null)
                                            {
                                                newClientForm.secondaryEmailfound = true;
                                            }
                                            newClientForm.secondaryEmailField.setValue(latestClient
                                                    .getSecondaryEmail());
                                            newClientForm.addressField.setValue(latestClient
                                                    .getAddress());
                                            newClientForm.agentFieldBox
                                                    .setSimpleValue(latestClient.getAgent());
                                            // Tab #1 complete
                                            newClientForm.policyNoField.setValue(latestClient
                                                    .getPolicyNumber());
                                            newClientForm.endrsNoField.setValue(latestClient
                                                    .getEndrsNumber());
                                            newClientForm.policyFromDateField
                                                    .setValue(latestClient.getPolicyStartdate());

                                            if(latestClient.getPolicyEndDate() != null)
                                            {

                                                Date tempEndDate = latestClient.getPolicyEndDate();
                                                newClientForm.policyToDateField.clear();
                                                newClientForm.policyToDateField.setValue(tempEndDate);
                                            }
                                            newClientForm.insCompanyBranchField
                                                    .setValue(latestClient.getInsBranchName());
                                            newClientForm.officeCodeField.setValue(latestClient
                                                    .getOfficeCode());
                                            if(latestClient.getSource() != null)
                                            {
                                                newClientForm.sourceFound = true;
                                            }
                                            newClientForm.sourceField.setValue(latestClient
                                                    .getSource());
                                            newClientForm.AgentField.setValue(latestClient
                                                    .getAgent());
                                            newClientForm.policyDetailsField.setValue(latestClient
                                                    .getPolicyDetails());
                                            // Tab #2 complete
                                            newClientForm.typeOfPolicyField.setValue(latestClient
                                                    .getFireTypeOfPolicy());
                                            newClientForm.basicRateField.setValue(latestClient
                                                    .getBasicRate());
                                            newClientForm.earthQuakecField.setValue(latestClient
                                                    .getEarthQuakePremium());
                                            newClientForm.anyAdditionalField.setValue(latestClient
                                                    .getAnyAdditionalPremium());
                                            // fire complete
                                            newClientForm.specificPolicyField
                                                    .setValue(latestClient.getMarineTypeOfPolicy());
                                            newClientForm.openPolicyField.setValue(latestClient
                                                    .getMarineOpenPolicy());
                                            newClientForm.openCoverField.setValue(latestClient
                                                    .getMarineOpenCover());
                                            newClientForm.otherPoliciesField.setValue(latestClient
                                                    .getMarineOtherPolicies());
                                            newClientForm.voyageFromField.setValue(latestClient
                                                    .getMarineVoyageFrom());
                                            newClientForm.voyageToField.setValue(latestClient
                                                    .getMarineVoyageTo());
                                            // Marine complete
                                            newClientForm.vehicleNoField.setValue(latestClient
                                                    .getVehicleNumber());
                                            newClientForm.iDVField.setValue(latestClient.getIDV());
                                            newClientForm.vehicleMakeField.setValue(latestClient
                                                    .getVehicleMake());
                                            if (newClientForm.manufacturingYearFound == null
                                                    && latestClient.getVehicleManufactureYear() != null) {

                                                newClientForm.manufacturingYearFound = String
                                                        .valueOf(latestClient
                                                                .getVehicleManufactureYear()
                                                                .getYear() + 1900);
                                            }
                                            newClientForm.nCBField.setValue(latestClient.getNBC());
                                            // Vehicle Complete
                                            newClientForm.misTypeOfPolicyField
                                                    .setValue(latestClient.getMiscTypeOfPolicy());
                                            // miscellaneous Complete
                                            newClientForm.sumInsuredField.setValue(latestClient
                                                    .getSumInsured());
                                            newClientForm.misIdCardField.setValue(latestClient
                                                    .getMiscIdCard());
                                            newClientForm.dispatchDateField.setValue(latestClient
                                                    .getMiscDispatchDate());
                                            // engineering fielsset
                                            newClientForm.premiunAmountField.setValue(formatDouble(latestClient
                                                    .getPremiumAmount()));

                                            newClientForm.terrorismPremiunAmountField
                                                    .setValue(latestClient
                                                            .getTerrorismPremiumAmount());
                                            newClientForm.serviceTaxField.setValue(latestClient
                                                    .getServiceTax());
                                            newClientForm.serviceTaxAmountField
                                                    .setValue(latestClient.getServiceTaxAmount());
                                            newClientForm.totalPremiunAmountField
                                                    .setValue(latestClient.getTotalPremiumAmount());
                                            newClientForm.commisionRateField.setValue(latestClient
                                                    .getCommionRate());
                                            newClientForm.commisionRateAmountField
                                                    .setValue(latestClient.getCommionRateAmount());
                                            newClientForm.collectionDate.setValue(latestClient
                                                    .getCollectionDate());
                                            if(latestClient.getPolicyEndDate() != null)
                                            {

                                                Date compareDate = latestClient.getPolicyEndDate();
                                                Date today = new Date();
                                                CalendarUtil.addMonthsToDate(today,+3);
                                                if(compareDate != null && (CalendarUtil.isSameDate(compareDate,today) || compareDate.before(today)))
                                                {
                                                    newClientForm.renewalStatus = true;
                                                    newClientForm.renewalAmountField.setValue(latestClient.getRenewalAmount());
                                                    newClientForm.renewalCompanyFound = latestClient.getrenewalCompany();
                                                }
                                                else
                                                    newClientForm.renewalStatus = false;
                                            }
                                            //newClientForm.setDocumentsList(latestClient);
                                            Registry.register("fieldset",
                                                    latestClient.getDepartment());

                                            TabPanel tabPanel = Registry.get("tabPanel");
                                            tabPanel.setBorders(false);
                                            tabPanel.setBorderStyle(false);
                                            TabItem item = new TabItem();
                                            item.setClosable(true);
                                            item.setBorders(false);
                                            item.setText("Client-"+model.getId().toString());
                                            item.add(newClientForm);
                                            tabPanel.add(item);
                                            tabPanel.setSelection(item);
                                            tabPanel.setBorders(false);


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
        TextField<String> text = new TextField<String>();
        text.setAllowBlank(false);
        column.setEditor(new CellEditor(text));
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
        TextField<String> policyNumberText = new TextField<String>();
        policyNumberText.setAllowBlank(false);
        column.setEditor(new CellEditor(policyNumberText));
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

        EditorGrid<Clients> grid = new EditorGrid<Clients>(store, cm);
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