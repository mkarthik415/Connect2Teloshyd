package org.jboss.tools.gwt.client;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.jboss.tools.gwt.shared.Clients;

import com.extjs.gxt.ui.client.Registry;
import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.GridEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.BoxComponent;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.Info;
import com.extjs.gxt.ui.client.widget.TabItem;
import com.extjs.gxt.ui.client.widget.TabPanel;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.Radio;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnData;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.extjs.gxt.ui.client.widget.grid.Grid;
import com.extjs.gxt.ui.client.widget.grid.GridCellRenderer;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.extjs.gxt.ui.client.widget.layout.FlowLayout;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.Element;

public class SearchGrid extends ContentPanel {
	
	NewClientForm newClientForm ;
	DateTimeFormat dformat = DateTimeFormat.getFormat("yyyy-mm-dd");
	Logger logger = Logger.getLogger("logger");
	
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
							@Override
							public void componentSelected(ButtonEvent ce) {
								logger.log(Level.SEVERE, "inside search grid");
								Info.display(model.getName(), "<ul><li>"
										+ "</li></ul>");
								TabPanel tabPanel = Registry
										.get("tabPanel");
								tabPanel.getSelectedItem()
										.close();
								tabPanel.setBorders(false);
								tabPanel.setBorderStyle(false);
								newClientForm = new NewClientForm();
								TabItem item = new TabItem();
								item.setText("Update Client");
								item.setClosable(true);
								item.setBorders(false);
								item.add(newClientForm);
								newClientForm.iD = model.getId().toString();
								newClientForm.fieldSetFound = model.getDepartment();
								newClientForm.agentFound = model.getAgent();
								newClientForm.insuranceCompanyFound = model.getInsCompanyName();
								newClientForm.companyNameFound = model.getCompany();
								newClientForm.genderFound = model.getGender();
								newClientForm.industryFound = model.getIndustry();
								newClientForm.updateButton=true;
								newClientForm.nameField.setValue(model.getName());
								newClientForm.mobileField.setValue(model.getPhoneNumber());
								newClientForm.dateOfBirthField.setValue(model.getDob());
								//newClientForm.company.setValue(model.getCompany());
								newClientForm.emailField.setValue(model.getEmail());
								newClientForm.addressField.setValue(model.getAddress());
								newClientForm.agentFieldBox.setSimpleValue(model.getAgent());
								//Tab #1 complete
								newClientForm.policyNoField.setValue(model.getPolicyNumber());
								newClientForm.endrsNoField.setValue(model.getEndrsNumber());
								newClientForm.policyFromDateField.setValue(model.getPolicyStartdate());
								newClientForm.policyToDateField.setValue(model.getPolicyEndDate());
								//newClientForm.insCompanyField.setSimpleValue(model.getInsCompanyName());
								newClientForm.insCompanyBranchField.setValue(model.getInsBranchName());
								newClientForm.officeCodeField.setValue(model.getOfficeCode());
								newClientForm.sourceField.setValue(model.getSource());
								newClientForm.AgentField.setValue(model.getAgent());
								newClientForm.policyDetailsField.setValue(model.getPolicyDetails());
								//Tab #2 complete
								newClientForm.typeOfPolicyField.setValue(model.getFireTypeOfPolicy());
								newClientForm.basicRateField.setValue(model.getBasicRate());
								newClientForm.earthQuakecField.setValue(model.getEarthQuakePremium());
								newClientForm.anyAdditionalField.setValue(model.getAnyAdditionalPremium());
								//fire complete
								newClientForm.specificPolicyField.setValue(model.getMarineTypeOfPolicy());
								newClientForm.openPolicyField.setValue(model.getMarineOpenPolicy());
								newClientForm.openCoverField.setValue(model.getMarineOpenCover());
								newClientForm.otherPoliciesField.setValue(model.getMarineOtherPolicies());
								newClientForm.voyageFromField.setValue(model.getMarineVoyageFrom());
								newClientForm.voyageToField.setValue(model.getMarineVoyageTo());
								//Marine complete
								newClientForm.vehicleNoField.setValue(model.getVehicleNumber());
								newClientForm.iDVField.setValue(model.getIDV());
								newClientForm.vehicleMakeField.setValue(model.getVehicleMake());
								newClientForm.yearOfManufacturingField.setValue(model.getVehicleManufactureYear());
								newClientForm.nCBField.setValue(model.getNBC());
								//Vehicle Complete
								newClientForm.misTypeOfPolicyField.setValue(model.getMiscTypeOfPolicy());
								// miscellaneous Complete
								newClientForm.sumInsuredField.setValue(model.getSumInsured());
								newClientForm.misIdCardField.setValue(model.getMiscIdCard());
								newClientForm.dispatchDateField.setValue(model.getMiscDispatchDate());
								// engineering fielsset
								newClientForm.premiunAmountField.setValue(model.getPremiumAmount());
								
								newClientForm.terrorismPremiunAmountField.setValue(model.getTerrorismPremiumAmount());
								newClientForm.serviceTaxField.setValue(model.getServiceTax());
								newClientForm.serviceTaxAmountField.setValue(model.getServiceTaxAmount());
								newClientForm.totalPremiunAmountField.setValue(model.getTotalPremiumAmount());
								newClientForm.commisionRateField.setValue(model.getCommionRate());
								newClientForm.commisionRateAmountField.setValue(model.getCommionRateAmount());
								newClientForm.collectionDate.setValue(model.getCollectionDate());
								Registry.register("fieldset",model.getDepartment());
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
		
		
		GridCellRenderer<Clients> buttonDispatchRenderer = new GridCellRenderer<Clients>(){

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
								tabPanel.getSelectedItem().close();
								TabItem item = new TabItem();
								DispatchForm dispatchForm = new DispatchForm();
								item.setText("Dispatch Client");
								item.setClosable(true);
								item.add(dispatchForm);
								dispatchForm.name.setValue(model.getName());
								dispatchForm.mobileField.setValue(model.getPhoneNumber());
								dispatchForm.emailField.setValue(model.getEmail());
								tabPanel.add(item);
								tabPanel.setSelection(item);
								
							}
							});
				
				bDispactch.setWidth(grid.getColumnModel().getColumnWidth(colIndex) - 20);
				bDispactch.setToolTip("Click here to send Dispatch Details");

				return bDispactch;
			}
			
		};

		List<ColumnConfig> configs = new ArrayList<ColumnConfig>();

		ColumnConfig column = new ColumnConfig();
		column.setId("id");
		column.setHeader("S.No");
		column.setWidth(70);
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
		cp.setSize(900, 600);

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