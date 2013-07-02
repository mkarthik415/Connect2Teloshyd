/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jboss.tools.gwt.client;

import gwtupload.client.IFileInput.FileInputType;
import gwtupload.client.IUploadStatus.Status;
import gwtupload.client.IUploader;
import gwtupload.client.IUploader.OnFinishUploaderHandler;
import gwtupload.client.IUploader.OnStartUploaderHandler;
import gwtupload.client.IUploader.Utils;
import gwtupload.client.MultiUploader;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.jboss.tools.gwt.shared.Agent;
import org.jboss.tools.gwt.shared.Client;
import org.jboss.tools.gwt.shared.Clients;
import org.jboss.tools.gwt.shared.Company;
import org.jboss.tools.gwt.shared.Insurance;

import com.extjs.gxt.ui.client.GXT;
import com.extjs.gxt.ui.client.Registry;
import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.aria.FocusManager;
import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.DatePickerEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.FieldEvent;
import com.extjs.gxt.ui.client.event.FieldSetEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.MessageBoxEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.Info;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.MessageBox;
import com.extjs.gxt.ui.client.widget.TabItem;
import com.extjs.gxt.ui.client.widget.TabPanel;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.DateField;
import com.extjs.gxt.ui.client.widget.form.FieldSet;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.FormPanel.LabelAlign;
import com.extjs.gxt.ui.client.widget.form.NumberField;
import com.extjs.gxt.ui.client.widget.form.Radio;
import com.extjs.gxt.ui.client.widget.form.RadioGroup;
import com.extjs.gxt.ui.client.widget.form.SimpleComboBox;
import com.extjs.gxt.ui.client.widget.form.TextArea;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.grid.CheckColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.extjs.gxt.ui.client.widget.grid.Grid;
import com.extjs.gxt.ui.client.widget.grid.GridCellRenderer;
import com.extjs.gxt.ui.client.widget.layout.ColumnData;
import com.extjs.gxt.ui.client.widget.layout.ColumnLayout;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.extjs.gxt.ui.client.widget.layout.FormData;
import com.extjs.gxt.ui.client.widget.layout.FormLayout;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.xml.client.Document;
import com.google.gwt.xml.client.XMLParser;

/**
 * 
 * @author Karthik Marupeddi
 */
public class NewClientForm extends ContentPanel {
	
	public NewClientForm(){
		setHeaderVisible(false);
		setBodyBorder(false);
	}

	final Logger logger = Logger.getLogger("logger");
	private VerticalPanel vp;
	private FormLayout fol = null;
	// private FlowLayout fol= null;

	// tab #1 contents
	TextField<String> nameField = new TextField<String>();
	TextField<String> mobileField = new TextField<String>();
	TextField<String> emailField = new TextField<String>();
	//TextField<String> company = new TextField<String>();
	SimpleComboBox<String> company = new SimpleComboBox<String>();
	// NumberField number = new NumberField();

	DateField dateOfBirthField = new DateField();

	Radio maleRadio = new Radio();
	Radio femaleRadio = new Radio();
	RadioGroup genderGroup = null;
	Radio individualRadio = new Radio();
	Radio cooporateRadio = new Radio();
	RadioGroup industryGroup = null;
	TextArea addressField = new TextArea();

	// tab#2 contents
	TextField<String> policyNoField = new TextField<String>();
	TextField<String> endrsNoField = new TextField<String>();
	DateField policyFromDateField = new DateField();
	DateField policyToDateField = new DateField();
	//TextField<String> insCompanyField = new TextField<String>();
	SimpleComboBox<String> insCompanyField = new SimpleComboBox<String>();
	TextArea insCompanyBranchField = new TextArea();
	TextField<String> officeCodeField = new TextField<String>();
	TextField<String> sourceField = new TextField<String>();
	TextField<String> AgentField = new TextField<String>();
	SimpleComboBox<String> agentFieldBox = new SimpleComboBox<String>();
	TextArea policyDetailsField = new TextArea();

	// tab#3 contents
	// firefieldset
	TextField<String> typeOfPolicyField = new TextField<String>();
	NumberField basicRateField = new NumberField();
	NumberField earthQuakecField = new NumberField();
	NumberField anyAdditionalField = new NumberField();

	// marine fieldset
	TextField<String> specificPolicyField = new TextField<String>();
	TextField<String> openPolicyField = new TextField<String>();
	TextField<String> openCoverField = new TextField<String>();
	TextField<String> otherPoliciesField = new TextField<String>();
	TextField<String> voyageFromField = new TextField<String>();
	TextField<String> voyageToField = new TextField<String>();

	// motor fieldset
	TextField<String> vehicleNoField = new TextField<String>();
	TextField<String> iDVField = new TextField<String>();
	TextField<String> vehicleMakeField = new TextField<String>();
	// DateField yearOfManufacturingField = new DateField();
	TextField<String> nCBField = new TextField<String>();

	// miscellaneous fieldset
	TextField<String> misTypeOfPolicyField = new TextField<String>();
	TextField<String> misIdCardField = new TextField<String>();
	DateField dispatchDateField = new DateField();

	// engineering fielsset
	NumberField sumInsuredField = new NumberField();

	// tab#4 contents
	NumberField premiunAmountField = new NumberField();
	NumberField terrorismPremiunAmountField = new NumberField();
	NumberField serviceTaxField = new NumberField();
	NumberField serviceTaxAmountField = new NumberField();
	NumberField totalPremiunAmountField = new NumberField();
	NumberField commisionRateField = new NumberField();
	NumberField commisionRateAmountField = new NumberField();
	
	//tab#5 contents
	TabItem uploadFilesTab;
	MultiUploader uploader = new MultiUploader(FileInputType.BROWSER_INPUT);
	private ListStore<Clients> documentsList = new ListStore<Clients>();

	
	void setDocumentsList(Clients model){
		documentsList.add(model);
	}
	
	
	final DialogBox dialogBox = new DialogBox();
	
	MessageBox box;
	
	DateField collectionDate = new DateField();

	DateField yearOfManufacturingField = new DateField();

	//
	// TextArea departmentField = new TextArea();

	// Buttons
	// Button btnSubmit = null;

	Button comfirmation = null;
	
	Button uploadDocuments = null;

	// policy Type
	private String policyType;

	// Creating Bean
	Client c = null;
	private String yes = "Yes";

	// Tabitem
	TabPanel tabs;
	TabItem personal;

	public FormPanel panel;
	public Button cancel;
	public Button update;
	public FieldSet fieldSet;
	FieldSet fieldSetMotor = new FieldSet();
	public FieldSet fieldSetMarine = new FieldSet();
	public FieldSet fieldSetMis = new FieldSet();
	public FieldSet fieldSetEngineering = new FieldSet();
	public List<FieldSet> list;
	public String fieldSetFound = null;
	public String agentFound = null;
	public Boolean updateButton = false;
	public String female = "Female";
	public String fire = "Fire";
	public String mrgRaju = "M.R.G.Raju";
	public String mnrao = "M.N.Rao";
	public String genderFound = null;
	public String industryFound = null;
	public String motor = "Motor";
	public String marine = "Marine";
	public String miscellaneous = "Miscellaneous";
	public String engineering = "Engineering";
	public String fireInCaps = "FIRE";
	public String motorInCaps = "MOTOR";
	public String marineInCaps = "MARINE";
	public String miscellaneousInCaps = "MISCELLANEOUS";
	public String engineeringInCaps = "ENGINEERING";
	public String iD;
	public String className = null;
	protected String insuranceCompanyFound = null;
	protected String companyNameFound = null;

	@Override
	protected void onRender(final Element parent, int index) {
		super.onRender(parent, index);
		vp = new VerticalPanel();
		vp.setSpacing(10);
		createTabForm();
		add(vp);
		
		if (updateButton)

		{
			update.setVisible(true);
			
		} else
		{
			update.setVisible(false);
			
		}
		
		dateOfBirthField.getDatePicker().addListener(Events.Select,
				new Listener<DatePickerEvent>() {

					@Override
					public void handleEvent(DatePickerEvent be) {

						Date today = new Date();
						Date dob = dateOfBirthField.getValue();

						long difference = today.getTime() - dob.getTime();

						// emailField.setValue(""+difference);
						long second = difference / 1000;
						long minute = second / 60;
						long hour = minute / 60;
						long day = hour / 24;

						long month = day / 30;
						day = day % 30;

						long year = month / 12;
						month = month % 12;

						MessageBox messageBox = new MessageBox();
						messageBox.setButtons(MessageBox.YESNO);
						messageBox.setIcon(MessageBox.QUESTION);
						messageBox.setTitle("New policy ?");
						messageBox
								.setMessage("Do you want to create a new policy ?");
					}

				});

		fieldSet.addListener(Events.Expand, new Listener<FieldSetEvent>() {

			@Override
			public void handleEvent(FieldSetEvent be) {
				fieldSetMarine.collapse();
				specificPolicyField.clear();
				openPolicyField.clear();
				openCoverField.clear();
				otherPoliciesField.clear();
				voyageFromField.clear();
				voyageToField.clear();
				fieldSetMotor.collapse();
				vehicleNoField.clear();
				iDVField.clear();
				vehicleMakeField.clear();
				yearOfManufacturingField.clear();
				nCBField.clear();
				fieldSetMis.collapse();
				misTypeOfPolicyField.clear();
				dispatchDateField.clear();
				sumInsuredField.clear();
				misIdCardField.clear();
				fieldSetEngineering.collapse();
				policyType = fieldSet.getHeading();

			}
		});

		fieldSetMarine.addListener(Events.Expand,
				new Listener<FieldSetEvent>() {

					@Override
					public void handleEvent(FieldSetEvent be) {
						fieldSet.collapse();
						typeOfPolicyField.clear();
						basicRateField.clear();
						earthQuakecField.clear();
						anyAdditionalField.clear();
						fieldSetMotor.collapse();
						vehicleNoField.clear();
						iDVField.clear();
						vehicleMakeField.clear();
						yearOfManufacturingField.clear();
						nCBField.clear();
						fieldSetMis.collapse();
						misTypeOfPolicyField.clear();
						dispatchDateField.clear();
						sumInsuredField.clear();
						misIdCardField.clear();
						fieldSetEngineering.collapse();
						policyType = fieldSetMarine.getHeading();
					}
				});

		fieldSetMotor.addListener(Events.Expand, new Listener<FieldSetEvent>() {

			@Override
			public void handleEvent(FieldSetEvent be) {
				fieldSet.collapse();
				typeOfPolicyField.clear();
				basicRateField.clear();
				earthQuakecField.clear();
				anyAdditionalField.clear();
				fieldSetMarine.collapse();
				specificPolicyField.clear();
				openPolicyField.clear();
				openCoverField.clear();
				otherPoliciesField.clear();
				voyageFromField.clear();
				voyageToField.clear();
				fieldSetMis.collapse();
				misTypeOfPolicyField.clear();
				dispatchDateField.clear();
				sumInsuredField.clear();
				misIdCardField.clear();
				fieldSetEngineering.collapse();
				policyType = fieldSetMotor.getHeading();
			}
		});

		fieldSetMis.addListener(Events.Expand, new Listener<FieldSetEvent>() {

			@Override
			public void handleEvent(FieldSetEvent be) {
				fieldSet.collapse();
				typeOfPolicyField.clear();
				basicRateField.clear();
				earthQuakecField.clear();
				anyAdditionalField.clear();
				fieldSetMarine.collapse();
				specificPolicyField.clear();
				openPolicyField.clear();
				openCoverField.clear();
				otherPoliciesField.clear();
				voyageFromField.clear();
				voyageToField.clear();
				fieldSetMotor.collapse();
				vehicleNoField.clear();
				iDVField.clear();
				vehicleMakeField.clear();
				yearOfManufacturingField.clear();
				nCBField.clear();
				sumInsuredField.clear();
				fieldSetEngineering.collapse();
				policyType = fieldSetMis.getHeading();
			}
		});

		fieldSetEngineering.addListener(Events.Expand,
				new Listener<FieldSetEvent>() {

					@Override
					public void handleEvent(FieldSetEvent be) {
						fieldSet.collapse();
						typeOfPolicyField.clear();
						basicRateField.clear();
						earthQuakecField.clear();
						anyAdditionalField.clear();
						fieldSetMarine.collapse();
						specificPolicyField.clear();
						openPolicyField.clear();
						openCoverField.clear();
						otherPoliciesField.clear();
						voyageFromField.clear();
						voyageToField.clear();
						fieldSetMotor.collapse();
						vehicleNoField.clear();
						iDVField.clear();
						vehicleMakeField.clear();
						yearOfManufacturingField.clear();
						nCBField.clear();
						misTypeOfPolicyField.clear();
						dispatchDateField.clear();
						fieldSetMis.collapse();
						misIdCardField.clear();
						policyType = fieldSetEngineering.getHeading();

					}
				});

		serviceTaxField.addListener(Events.Change, new Listener<FieldEvent>() {

			@Override
			public void handleEvent(FieldEvent be) {
				System.out.println("changes" + serviceTaxField.getValue());
				System.out.println("changes" + premiunAmountField.getValue());

				Number premiumAmount = 0.00;
				Number terrorismPremiumAmount = 0.00;
				Number totalPremiumAmount = 0.00;
				Number precentageAmount = 0.00;
				if (premiunAmountField.getValue() == null) {
					premiumAmount = 0.00;
				} else
					premiumAmount = premiunAmountField.getValue();
				if (terrorismPremiunAmountField.getValue() == null) {
					terrorismPremiumAmount = 0.00;
				} else
					terrorismPremiumAmount = terrorismPremiunAmountField
							.getValue();
				if (serviceTaxField.getValue() == null) {
					precentageAmount = 0.00;
				} else
					precentageAmount = ((premiumAmount.doubleValue() + terrorismPremiumAmount
							.doubleValue()) * serviceTaxField.getValue()
							.doubleValue()) / 100;
				serviceTaxAmountField.setValue(precentageAmount);
				totalPremiumAmount = precentageAmount.doubleValue()
						+ premiumAmount.doubleValue()
						+ terrorismPremiumAmount.doubleValue();
				totalPremiunAmountField.setValue(totalPremiumAmount);

			}
		});

		premiunAmountField.addListener(Events.Change,
				new Listener<FieldEvent>() {

					@Override
					public void handleEvent(FieldEvent be) {
						System.out.println("changes"
								+ serviceTaxField.getValue());
						System.out.println("changes"
								+ premiunAmountField.getValue());

						Number premiumAmount = 0.00;
						Number terrorismPremiumAmount = 0.00;
						Number totalPremiumAmount = 0.00;
						Number precentageAmount = 0.00;
						Number commisionAmount = 0.00;
						if (premiunAmountField.getValue() == null) {
							premiumAmount = 0.00;
						} else
							premiumAmount = premiunAmountField.getValue();
						if (terrorismPremiunAmountField.getValue() == null) {
							terrorismPremiumAmount = 0.00;
						} else
							terrorismPremiumAmount = terrorismPremiunAmountField
									.getValue();
						if (serviceTaxField.getValue() == null) {
							precentageAmount = 0.00;
						} else
							precentageAmount = ((premiumAmount.doubleValue() + terrorismPremiumAmount
									.doubleValue()) * serviceTaxField
									.getValue().doubleValue()) / 100;
						serviceTaxAmountField.setValue(precentageAmount);
						totalPremiumAmount = precentageAmount.doubleValue()
								+ premiumAmount.doubleValue()
								+ terrorismPremiumAmount.doubleValue();
						totalPremiunAmountField.setValue(totalPremiumAmount);
						if (commisionRateField.getValue() == null) {
							commisionAmount = 0.00;
						} else
							commisionAmount = ((premiumAmount.doubleValue()) * commisionRateField
									.getValue().doubleValue()) / 100;
						commisionRateAmountField.setValue(commisionAmount);

					}
				});

		terrorismPremiunAmountField.addListener(Events.Change,
				new Listener<FieldEvent>() {

					@Override
					public void handleEvent(FieldEvent be) {
						System.out.println("changes"
								+ serviceTaxField.getValue());
						System.out.println("changes"
								+ premiunAmountField.getValue());

						Number premiumAmount = 0.00;
						Number terrorismPremiumAmount = 0.00;
						Number totalPremiumAmount = 0.00;
						Number precentageAmount = 0.00;
						Number commisionAmount = 0.00;
						if (premiunAmountField.getValue() == null) {
							premiumAmount = 0.00;
						} else
							premiumAmount = premiunAmountField.getValue();
						if (terrorismPremiunAmountField.getValue() == null) {
							terrorismPremiumAmount = 0.00;
						} else
							terrorismPremiumAmount = terrorismPremiunAmountField
									.getValue();
						if (serviceTaxField.getValue() == null) {
							precentageAmount = 0.00;
						} else
							precentageAmount = ((premiumAmount.doubleValue() + terrorismPremiumAmount
									.doubleValue()) * serviceTaxField
									.getValue().doubleValue()) / 100;
						serviceTaxAmountField.setValue(precentageAmount);
						totalPremiumAmount = precentageAmount.doubleValue()
								+ premiumAmount.doubleValue()
								+ terrorismPremiumAmount.doubleValue();
						totalPremiunAmountField.setValue(totalPremiumAmount);
						if (commisionRateField.getValue() == null) {
							commisionAmount = 0.00;
						} else
							commisionAmount = ((premiumAmount.doubleValue()) * commisionRateField
									.getValue().doubleValue()) / 100;
						commisionRateAmountField.setValue(commisionAmount);

					}
				});

		commisionRateField.addListener(Events.Change,
				new Listener<FieldEvent>() {

					@Override
					public void handleEvent(FieldEvent be) {
						Number premiumAmount = 0.00;
						Number terrorismPremiumAmount = 0.00;
						Number commisionAmount = 0.00;
						if (premiunAmountField.getValue() == null) {
							premiumAmount = 0.00;
						} else
							premiumAmount = premiunAmountField.getValue();
						if (terrorismPremiunAmountField.getValue() == null) {
							terrorismPremiumAmount = 0.00;
						} else
							terrorismPremiumAmount = terrorismPremiunAmountField
									.getValue();
						if (commisionRateField.getValue() == null) {
							commisionAmount = 0.00;
						} else
							commisionAmount = ((premiumAmount.doubleValue()) * commisionRateField
									.getValue().doubleValue()) / 100;
						commisionRateAmountField.setValue(commisionAmount);

					}
				});

		comfirmation.addListener(Events.OnClick, new Listener<ButtonEvent>() {
			String departmentField;
			final Listener<MessageBoxEvent> l = new Listener<MessageBoxEvent>() {
				public void handleEvent(MessageBoxEvent ce) {
					Button btn = ce.getButtonClicked();

					if (btn.getText().equals(yes)) {
						Info.display("MessageBox",
								"The '{0}' button was pressed", btn.getText());

						if (panel.isValid()) {
							// btnSubmit.disable();
							c = new Client();
							try {
								c.setClientName(nameField.getValue());
								c.setPhoneNumber(mobileField.getValue());
								c.setDob(dateOfBirthField.getValue());
								c.setCompany(company.getSimpleValue());
								c.setEmail(emailField.getValue());
								c.setGender(genderGroup.getValue()
										.getBoxLabel());
								c.setIndustry(industryGroup.getValue()
										.getBoxLabel());
								c.setAddress(addressField.getValue());
								c.setPolicyNumber(policyNoField.getValue());
								c.setEndrsNumber(endrsNoField.getValue());
								c.setPolicyStartdate(policyFromDateField
										.getValue());
								c.setPolicyEndDate(policyToDateField.getValue());
								c.setInsCompanyName(insCompanyField.getSimpleValue());
								c.setInsBranchName(insCompanyBranchField
										.getValue());
								c.setOfficeCode(officeCodeField.getValue());
								c.setSource(sourceField.getValue());
								c.setPolicyDetails(policyDetailsField
										.getValue());
								c.setPolicyType(policyType);
								c.setAgent(agentFieldBox.getSimpleValue());
								c.setCollectionDate(collectionDate.getValue());
								c.setFireTypeOfPolicy(typeOfPolicyField
										.getValue());
								c.setBasicRate((Double) basicRateField
										.getValue());
								c.setEarthQuakePremium((Double) earthQuakecField
										.getValue());
								c.setAnyAdditionalPremium((Double) anyAdditionalField
										.getValue());
								// motor
								c.setVehicleNumber(vehicleNoField.getValue());
								c.setiDV(iDVField.getValue());
								c.setVehicleMake(vehicleMakeField.getValue());
								c.setVehicleManufactureYear(yearOfManufacturingField
										.getValue());
								c.setnBC(nCBField.getValue());
								c.setMarineTypeOfPolicy(specificPolicyField
										.getValue());
								c.setMarineOpenPolicy(openPolicyField
										.getValue());
								c.setMarineOpenCover(openCoverField.getValue());
								c.setMarineOtherPolicies(otherPoliciesField
										.getValue());
								c.setMarineVoyageFrom(voyageFromField
										.getValue());
								c.setMarineVoyageTo(voyageToField.getValue());
								c.setPremiumAmount((Double) premiunAmountField
										.getValue());
								c.setTerrorismPremiumAmount((Double) terrorismPremiunAmountField
										.getValue());
								c.setServiceTax((Double) serviceTaxField
										.getValue());
								c.setServiceTaxAmount((Double) serviceTaxAmountField
										.getValue());
								c.setTotalPremiumAmount((Double) totalPremiunAmountField
										.getValue());
								c.setCommionRate((Double) commisionRateField
										.getValue());
								c.setCommionRateAmount((Double) commisionRateAmountField
										.getValue());
								if (fieldSet.isExpanded()) {
									c.setPolicyType(fieldSet.getHeading());
								} else if (fieldSetMotor.isExpanded()) {
									c.setPolicyType(fieldSetMotor.getHeading());
								} else if (fieldSetMarine.isExpanded()) {
									c.setPolicyType(fieldSetMarine.getHeading());
								} else if (fieldSetMis.isExpanded()) {
									c.setPolicyType(fieldSetMis.getHeading());
								} else
									c.setPolicyType(fieldSetFound);
								c.setMiscTypeOfPolicy(misTypeOfPolicyField
										.getValue());
								c.setMiscIdCard(misIdCardField.getValue());
								c.setMiscDispatchDate(dispatchDateField.getValue());
								c.setSumInsured((Double) sumInsuredField
										.getValue());
							} catch (Exception ee) {
								logger.log(Level.SEVERE,
										"exception at ui level" + ee.toString());
							}
							((GreetingServiceAsync) GWT
									.create(GreetingService.class))
									.createClient(c,
											new AsyncCallback<String>() {
												public void onFailure(
														Throwable caught) {
													// Show the RPC error
													// message to the user
													MessageBox messageBox = new MessageBox();
													messageBox
															.setMessage("Client not Submitted !!");
													messageBox.show();
												}

												public void onSuccess(
														String result) {

													logger.log(Level.SEVERE,
															"inside Clent ");
													try {
														if (result != null && !result.equals("same")) {
															logger.log(
																	Level.SEVERE,
																	"inside if block ");
															MessageBox messageBox = new MessageBox();
															messageBox
																	.setMessage("Telos Policy Number: "
																			+ result);
															messageBox.show();
															// clearAll();
															tabs.setSelection(personal);
															tabs.clearState();
															TabPanel tabPanel = Registry
																	.get("tabPanel");
															tabPanel.getSelectedItem()
																	.close();
															// btnSubmit.enable();

														}
														 if(result !=null && result.equals("same")){
															System.out
																	.println("did not execute properly..");
															MessageBox messageBox = new MessageBox();
															messageBox
																	.setMessage("Policy Number already keyed !! Search and make a update.");
															messageBox.show();
															// btnSubmit.enable();
														}
														else if(result == null) {
															System.out
																	.println("did not execute properly..");
															
															// btnSubmit.enable();
														}

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
				}
			};

			@Override
			public void handleEvent(ButtonEvent be) {
				// TODO Auto-generated method stub

				MessageBox box = new MessageBox();
				box.setButtons(MessageBox.YESNO);
				box.setIcon(MessageBox.QUESTION);
				box.setTitle("Create New Policy ?");
				box.addCallback(l);
				box.setMessage("Would you like to create a new policy?");
				box.show();

			}

		});

		update.addListener(Events.OnClick, new Listener<ButtonEvent>() {
			final Listener<MessageBoxEvent> l = new Listener<MessageBoxEvent>() {
				public void handleEvent(MessageBoxEvent ce) {
					Button btn = ce.getButtonClicked();

					if (btn.getText().equals(yes)) {
						Info.display("MessageBox",
								"The '{0}' button was pressed", btn.getText());
						logger.log(
								Level.SEVERE,
								"exception at updating ......."
										+ fieldSet.isExpanded());
						System.out.println("exception at updating ......."
								+ fieldSet.isExpanded());

						if (panel.isValid()) {
							System.out.println(" is the fire field expanded "
									+ fieldSet.isExpanded());
							c = new Client();
							try {
								c.setId(iD);
								c.setClientName(nameField.getValue());
								c.setPhoneNumber(mobileField.getValue());
								c.setDob(dateOfBirthField.getValue());
								c.setCompany(company.getSimpleValue());
								c.setEmail(emailField.getValue());
								c.setGender(genderGroup.getValue()
										.getBoxLabel());
								c.setIndustry(industryGroup.getValue()
										.getBoxLabel());
								c.setAddress(addressField.getValue());
								c.setPolicyNumber(policyNoField.getValue());
								c.setEndrsNumber(endrsNoField.getValue());
								c.setPolicyStartdate(policyFromDateField
										.getValue());
								c.setPolicyEndDate(policyToDateField.getValue());
								if(insCompanyField.getSimpleValue().isEmpty()){
									c.setInsCompanyName(insuranceCompanyFound);
								}
								else
									c.setInsCompanyName(insCompanyField.getSimpleValue());
								c.setInsBranchName(insCompanyBranchField
										.getValue());
								c.setOfficeCode(officeCodeField.getValue());
								c.setSource(sourceField.getValue());
								c.setPolicyDetails(policyDetailsField
										.getValue());
								c.setPolicyType(policyType);
								if(agentFieldBox.getSimpleValue().isEmpty())
								{
									c.setAgent(agentFound);
								}
								else
								c.setAgent(agentFieldBox.getSimpleValue());
								c.setCollectionDate(collectionDate.getValue());
								c.setFireTypeOfPolicy(typeOfPolicyField
										.getValue());
								c.setBasicRate((Double) basicRateField
										.getValue());
								c.setEarthQuakePremium((Double) earthQuakecField
										.getValue());
								c.setAnyAdditionalPremium((Double) anyAdditionalField
										.getValue());
								// motor
								c.setVehicleNumber(vehicleNoField.getValue());
								c.setiDV(iDVField.getValue());
								c.setVehicleMake(vehicleMakeField.getValue());
								c.setVehicleManufactureYear(yearOfManufacturingField
										.getValue());
								c.setnBC(nCBField.getValue());
								c.setMarineTypeOfPolicy(specificPolicyField
										.getValue());
								c.setMarineOpenPolicy(openPolicyField
										.getValue());
								c.setMarineOpenCover(openCoverField.getValue());
								c.setMarineOtherPolicies(otherPoliciesField
										.getValue());
								c.setMarineVoyageFrom(voyageFromField
										.getValue());
								c.setMarineVoyageTo(voyageToField.getValue());
								c.setPremiumAmount((Double) premiunAmountField
										.getValue());
								c.setTerrorismPremiumAmount((Double) terrorismPremiunAmountField
										.getValue());
								c.setServiceTax((Double) serviceTaxField
										.getValue());
								c.setServiceTaxAmount((Double) serviceTaxAmountField
										.getValue());
								c.setTotalPremiumAmount((Double) totalPremiunAmountField
										.getValue());
								c.setCommionRate((Double) commisionRateField
										.getValue());
								c.setCommionRateAmount((Double) commisionRateAmountField
										.getValue());
								c.setMiscTypeOfPolicy(misTypeOfPolicyField
										.getValue());
								c.setMiscIdCard(misIdCardField.getValue());
								c.setMiscDispatchDate(dispatchDateField.getValue());
								c.setDepartment(fieldSetFound);
//								System.out.println("department selected is"+fieldSetFound);
								if (fieldSet.isExpanded()
										|| fieldSetFound.equals(fieldSet.getHeading())) {
									c.setPolicyType(fieldSet.getHeading());

								} else if (fieldSetMotor.isExpanded()
										|| fieldSetFound.equals(fieldSetMotor
												.getHeading())) {
									c.setPolicyType(fieldSetMotor.getHeading());
								} else if (fieldSetMarine.isExpanded()
										|| fieldSetFound.equals(fieldSetMarine.getHeading())) {
									c.setPolicyType(fieldSetMarine.getHeading());
								} else if (fieldSetMis.isExpanded()
										|| fieldSetFound.equals(fieldSetMis
												.getHeading())) {
									c.setPolicyType(fieldSetMis.getHeading());
								} else{
									c.setPolicyType(fieldSetEngineering.getHeading());
								}
								c.setSumInsured((Double) sumInsuredField
										.getValue());
								
							} catch (Exception ee) {
								logger.log(Level.SEVERE,
										"exception at ui level" + ee.toString());
							}
							((GreetingServiceAsync) GWT
									.create(GreetingService.class))
									.upgradeClient(c,
											new AsyncCallback<String>() {
												public void onFailure(
														Throwable caught) {
													MessageBox messageBox = new MessageBox();
													messageBox
															.setMessage("Client not Submitted !!");
													messageBox.show();
												}

												public void onSuccess(
														String result) {

													logger.log(Level.SEVERE,
															"inside Clent ");
													try {
														if (result != null && ! result.equals("same")) {
															logger.log(
																	Level.SEVERE,
																	"inside if block ");
															// clearAll();
															tabs.setSelection(personal);
															tabs.clearState();
															TabPanel tabPanel = Registry
																	.get("tabPanel");
															tabPanel.getSelectedItem()
																	.close();
															// btnSubmit.enable();

														} 
														 if(result !=null && result.equals("same")){
															System.out
																	.println("did not execute properly..");
															MessageBox messageBox = new MessageBox();
															messageBox
																	.setMessage("Policy Number already keyed !! Search and make a update.");
															messageBox.show();
															// btnSubmit.enable();
														}
														else {
															MessageBox messageBox = new MessageBox();
															messageBox
																	.setMessage("You have made an update succesfully");
															messageBox.show();
															// btnSubmit.enable();
														}

													} catch (Exception ex) {
														logger.log(
																Level.SEVERE,
																"exception at ui level"
																		+ ex.toString());
														MessageBox messageBox = new MessageBox();
														messageBox
																.setMessage("You update was not succesful "+result);
														messageBox.show();
													}
												}
											});
						}

					}
				}
			};

			@Override
			public void handleEvent(ButtonEvent be) {

				MessageBox box = new MessageBox();
				box.setButtons(MessageBox.YESNO);
				box.setIcon(MessageBox.QUESTION);
				box.setTitle("Make changes to a Policy ?");
				box.addCallback(l);
				box.setMessage("Would you like to update existing policy?");
				box.show();

			}

		});

		cancel.addListener(Events.OnClick, new Listener<ButtonEvent>() {

			@Override
			public void handleEvent(ButtonEvent e) {
				clearAll();
				personal.show();
				tabs.setSelection(personal);
				// btnSubmit.enable();
			}

		});

		agentFieldBox.addListener(Events.Render, new Listener<BaseEvent>() {

			@Override
			public void handleEvent(BaseEvent be) {
				// agentFieldBox.add("Rao");

				((GreetingServiceAsync) GWT.create(GreetingService.class))
						.loadAgents(new AsyncCallback<List<Agent>>() {

							@Override
							public void onFailure(Throwable arg0) {
								MessageBox messageBox = new MessageBox();
								messageBox.setMessage("no Agents listed!!");
								messageBox.show();

							}

							@Override
							public void onSuccess(List<Agent> arg0) {
								agentFieldBox.removeAll();
								for (Agent agent : arg0) {
									agentFieldBox.add(agent.getScreenName());
									if (agentFound != null
											&& agentFound.equals(agent
													.getScreenName())) {
										agentFieldBox
												.setSimpleValue(agentFound);
									}

								}

							}

						});

			}
		});
		
		insCompanyField.addListener(Events.Render, new Listener<BaseEvent>() {

			@Override
			public void handleEvent(BaseEvent be) {
				// agentFieldBox.add("Rao");

				((GreetingServiceAsync) GWT.create(GreetingService.class))
						.loadInsurance(new AsyncCallback<List<Insurance>>() {

							@Override
							public void onFailure(Throwable arg0) {
								MessageBox messageBox = new MessageBox();
								messageBox.setMessage("no Agents listed!!");
								messageBox.show();

							}

							@Override
							public void onSuccess(List<Insurance> arg0) {

								insCompanyField.removeAll();
								for (Insurance insurance : arg0) {
									insCompanyField.add(insurance.getScreenName());
									if (insuranceCompanyFound != null
											&& insuranceCompanyFound.equals(insurance
													.getScreenName())) {
										insCompanyField
												.setSimpleValue(insuranceCompanyFound);
									}

								}
								
							}

						});

			}
		});
		
		
		
		company.addListener(Events.Render, new Listener<BaseEvent>() {

			@Override
			public void handleEvent(BaseEvent be) {
				// agentFieldBox.add("Rao");

				((GreetingServiceAsync) GWT.create(GreetingService.class))
						.loadComapny(new AsyncCallback<List<Company>>() {

							@Override
							public void onFailure(Throwable arg0) {
								MessageBox messageBox = new MessageBox();
								messageBox.setMessage("no company listed!!");
								messageBox.show();

							}

							@Override
							public void onSuccess(List<Company> result) {
								company.removeAll();
								for (Company companyInList : result) {
									company.add(companyInList.getCompnyName());
									}
								if (companyNameFound != null) {
									company.setSimpleValue(companyNameFound);
								}
							}

						});

			}
		});
				
		uploadDocuments.addListener(Events.OnClick, new Listener<ButtonEvent>() {

			@Override
			public void handleEvent(ButtonEvent be) {
				UploadExcel uploadDocumentsTab = new UploadExcel();
				TabPanel homeTabpanel =Registry.get("tabPanel");
				TabItem item = new TabItem();
				item.setText("Upload Documents");
				item.setId("Upload Documents");
				item.setClosable(true);
				item.add(uploadDocumentsTab);
				homeTabpanel.add(item);
				homeTabpanel.setSelection(item);
				homeTabpanel.setBorders(false);
				uploadDocumentsTab.clientName.setValue(nameField.getValue());
				uploadDocumentsTab.policyNumber.setValue(policyNoField.getValue());
				uploadDocumentsTab.clientId.setValue(iD);
				uploadDocumentsTab.fieldName = iD;
			}
			
		});

	}

	@SuppressWarnings("deprecation")
	private void createTabForm() {
		super.setHeaderVisible(false);
		super.setBodyBorder(false);
		FormData formData = new FormData("100%");
		panel = new FormPanel();
		panel.setBorders(false);
		panel.setBodyStyleName("example-bg");
		panel.setPadding(0);
		panel.setFrame(false);
		panel.setHeaderVisible(false);
		panel.setBodyBorder(false);
		panel.setButtonAlign(HorizontalAlignment.CENTER);
		FitLayout fl = new FitLayout();
		// FlowLayout fl= new FlowLayout();
		panel.setLayout(fl);
		tabs = new TabPanel();

		personal = new TabItem();
		personal.setStyleAttribute("padding", "10px");
		personal.setText("Personal Details");
		fol = new FormLayout();
		fol.setLabelAlign(LabelAlign.TOP);
		// fol = new FlowLayout()
		personal.setLayout(fol);

		// name filed
		nameField.setFieldLabel("Name of the Insured");
		nameField.setAllowBlank(false);
		personal.add(nameField, new FormData("35%"));
		nameField.setEmptyText("Enter clients full name");

		// mobile filed
		mobileField.setFieldLabel("Phone Number");
		personal.add(mobileField, new FormData("35%"));
		mobileField.setEmptyText("9848334455");

		// dateOfBirth
		dateOfBirthField.setFieldLabel("Date of Birth");
		dateOfBirthField.setMinValue(new Date(80, 1, 1));
		dateOfBirthField.setMaxValue(new Date());
		personal.add(dateOfBirthField, new FormData("15%"));
		dateOfBirthField.setEmptyText("YYYY-MM-DD");

		// company field
		company.setFieldLabel("Company");
		company.setSelectedStyle(".x-tool-search");
		personal.add(company, new FormData("35%"));

		// email field
		emailField.setFieldLabel("Email");
		emailField.setRegex(".+@.+\\.[a-z]+");
		emailField.getMessages().setRegexText("Bad email address!!");
		emailField.setAutoValidate(true);
		personal.add(emailField, new FormData("35%"));
		emailField.setEmptyText("example@example.com");

		// gender field
		maleRadio.setBoxLabel("Male");
		femaleRadio.setBoxLabel("Female");
		genderGroup = new RadioGroup();
		genderGroup.setFieldLabel("Gender");
		genderGroup.add(maleRadio);
		genderGroup.add(femaleRadio);
		personal.add(genderGroup, formData);
		if (genderFound != null && (genderFound.equals(female))) {
			genderGroup.setValue(femaleRadio);
		} else
			genderGroup.setValue(maleRadio);

		// industry field
		individualRadio.setBoxLabel("Individual");
		cooporateRadio.setBoxLabel("Co-oporate");
		industryGroup = new RadioGroup();
		industryGroup.setFieldLabel("Industry");
		industryGroup.add(individualRadio);
		industryGroup.add(cooporateRadio);
		personal.add(industryGroup, formData);
		if (industryFound != null && (industryFound.equals("Individual"))) {
			industryGroup.setValue(individualRadio);
		} else
			industryGroup.setValue(cooporateRadio);

		// address field
		addressField.setFieldLabel("Address");
		addressField.setHeight(70);
		personal.add(addressField, new FormData("50%"));

		tabs.add(personal);
		// tab#2 starts here

		TabItem insDetails = new TabItem();
		insDetails.setStyleAttribute("padding", "10px");
		insDetails.setText("Ins Company Details");

		// main layout for tab2
		insDetails.setLayout(new ColumnLayout());

		LayoutContainer left = new LayoutContainer();
		left.setStyleAttribute("paddingRight", "100px");

		LayoutContainer right = new LayoutContainer();
		right.setStyleAttribute("paddingLeft", "10px");

		fol = new FormLayout();
		fol.setLabelAlign(LabelAlign.TOP);
		left.setLayout(fol);

		FormLayout rightfl = new FormLayout();
		rightfl.setLabelAlign(LabelAlign.TOP);
		right.setLayout(rightfl);

		// policy no field
		policyNoField.setFieldLabel("Policy/Certificate No");
		left.add(policyNoField);

		// endrs no field
		endrsNoField.setFieldLabel("Endrs No");
		right.add(endrsNoField);

		// Policy starts On field
		policyFromDateField.setFieldLabel("Policy starts On");
		left.add(policyFromDateField, new FormData("40%"));

		// Policy ends On field
		policyToDateField.setFieldLabel("Policy Ends On");
		right.add(policyToDateField, new FormData("30%"));

		// ins Company field
		insCompanyField.setFieldLabel("Ins.Company Name");
		left.add(insCompanyField);

		// ins Company branch field
		insCompanyBranchField.setFieldLabel("Ins.Branch Name");
		insCompanyBranchField.setHeight(100);
		right.add(insCompanyBranchField, new FormData("70%"));

		// office Codefield
		officeCodeField.setFieldLabel("Office Code");
		left.add(officeCodeField);

		// source field
		sourceField.setFieldLabel("Source");
		//right.add(sourceField);

		agentFieldBox.setFieldLabel("Agent");
		left.add(agentFieldBox);
		/*
		 * if (agentFound != null && (agentFound.equals(mrgRaju))) {
		 * agentFieldBox.setSimpleValue(agentFound); } else
		 * agentFieldBox.setSimpleValue(mnrao);
		 */

		policyDetailsField.setFieldLabel("Policy Deatils");
		policyDetailsField.setHeight(100);
		right.add(policyDetailsField, new FormData("70%"));

		insDetails.add(left, new ColumnData(.5));
		insDetails.add(right, new ColumnData(.5));
		tabs.add(insDetails);

		// tab#3 starts here

		TabItem policyDetails = new TabItem();
		policyDetails.setStyleAttribute("padding", "10px");
		policyDetails.setText("Policy Details");
		fol = new FormLayout();
		fol.setLabelAlign(LabelAlign.TOP);
		// FlowLayout foll = new FlowLayout();
		policyDetails.setLayout(fol);

		// fire fieldset in tab#3
		fieldSet = new FieldSet();
		fieldSet.setHeading("Fire");
		// fieldSet.setCheckboxToggle(false);
		fieldSet.setCollapsible(true);
		fieldSet.setExpanded(false);
		fieldSet.setCheckboxToggle(true);
		if (fieldSetFound != null
				&& (fieldSetFound.equals(fire) || fieldSetFound
						.equals(fireInCaps))) {
			fieldSet.expand();
		}

		FormLayout layout = new FormLayout();
		// layout.setLabelWidth(75);
		layout.setLabelAlign(LabelAlign.TOP);
		fieldSet.setLayout(layout);

		typeOfPolicyField.setFieldLabel("Type of Policy");
		fieldSet.add(typeOfPolicyField, new FormData("35%"));

		basicRateField.setFieldLabel("Basic Rate");
		fieldSet.add(basicRateField, new FormData("35%"));

		earthQuakecField.setFieldLabel("Earth Quake Premium");
		fieldSet.add(earthQuakecField, new FormData("35%"));

		anyAdditionalField.setFieldLabel("Any Additional Premium");
		fieldSet.add(anyAdditionalField, new FormData("35%"));

		policyDetails.add(fieldSet);

		// motor fieldset in tab#3
		// fieldSetMotor = new FieldSet();
		fieldSetMotor.setHeading("Motor");
		fieldSetMotor.setId("Motor");
		// fieldSet.setCheckboxToggle(false);
		fieldSetMotor.setCollapsible(true);
		fieldSetMotor.setExpanded(false);
		fieldSetMotor.setCheckboxToggle(true);

		if (fieldSetFound != null
				&& (fieldSetFound.equals(motor) || (fieldSetFound
						.equals(motorInCaps)))) {
			fieldSetMotor.expand();
		}

		FormLayout layoutMotor = new FormLayout();
		// layout.setLabelWidth(75);
		layoutMotor.setLabelAlign(LabelAlign.TOP);
		fieldSetMotor.setLayout(layoutMotor);

		vehicleNoField.setFieldLabel("Vehicle No");
		fieldSetMotor.add(vehicleNoField, new FormData("35%"));

		iDVField.setFieldLabel("I.D.V");
		fieldSetMotor.add(iDVField, new FormData("35%"));

		vehicleMakeField.setFieldLabel("Vehicle Make");
		fieldSetMotor.add(vehicleMakeField, new FormData("35%"));


		yearOfManufacturingField.setFieldLabel("Year Of Manufacturing");
		fieldSetMotor.add(yearOfManufacturingField, new FormData("15%"));

		nCBField.setFieldLabel("NCB");
		fieldSetMotor.add(nCBField, new FormData("35%"));

		policyDetails.add(fieldSetMotor);

		// Marine fieldset in tab#3

		fieldSetMarine = new FieldSet();
		fieldSetMarine.setHeading("Marine");
		fieldSetMarine.setCheckboxToggle(true);
		fieldSetMarine.setExpanded(false);

		if (fieldSetFound != null
				&& (fieldSetFound.equals(marine) || fieldSetFound
						.equals(marineInCaps))) {
			fieldSetMarine.expand();
		}

		FormLayout layoutMarine = new FormLayout();
		// layout.setLabelWidth(75);
		layoutMarine.setLabelAlign(LabelAlign.TOP);
		fieldSetMarine.setLayout(layoutMarine);

		specificPolicyField.setFieldLabel("Type of Policy");
		fieldSetMarine.add(specificPolicyField, new FormData("35%"));

		openPolicyField.setFieldLabel("Open Policy");
		fieldSetMarine.add(openPolicyField, new FormData("35%"));

		openCoverField.setFieldLabel("Open Cover");
		fieldSetMarine.add(openCoverField, new FormData("35%"));

		otherPoliciesField.setFieldLabel("Other Policies");
		fieldSetMarine.add(otherPoliciesField, new FormData("35%"));

		voyageFromField.setFieldLabel("Voyage From");
		fieldSetMarine.add(voyageFromField, new FormData("35%"));

		voyageToField.setFieldLabel("Voyage To");
		fieldSetMarine.add(voyageToField, new FormData("35%"));

		policyDetails.add(fieldSetMarine);

		// Miscellaneous
		fieldSetMis = new FieldSet();
		fieldSetMis.setHeading("Miscellaneous");
		fieldSetMis.setCheckboxToggle(true);
		fieldSetMis.setExpanded(false);

		if (fieldSetFound != null
				&& (fieldSetFound.equals(miscellaneous) || fieldSetFound
						.equals(miscellaneousInCaps))) {
			fieldSetMis.expand();
		}

		FormLayout layoutMis = new FormLayout();
		layoutMis.setLabelAlign(LabelAlign.TOP);
		fieldSetMis.setLayout(layoutMis);

		misTypeOfPolicyField.setFieldLabel("Type of Policy");
		fieldSetMis.add(misTypeOfPolicyField, new FormData("35%"));
		policyDetails.add(fieldSetMis);
		
		misIdCardField.setFieldLabel("ID Card");
		fieldSetMis.add(misIdCardField, new FormData("35%"));
		policyDetails.add(fieldSetMis);
		
		dispatchDateField.setFieldLabel("Dispatch Date");
		fieldSetMis.add(dispatchDateField, new FormData("15%"));
		policyDetails.add(fieldSetMis);
		
		
		// Engineering
		fieldSetEngineering = new FieldSet();
		fieldSetEngineering.setHeading("Engineering");
		fieldSetEngineering.setCheckboxToggle(true);
		fieldSetEngineering.setExpanded(false);

		if (fieldSetFound != null
				&& (fieldSetFound.equals(engineering) || fieldSetFound
						.equals(engineeringInCaps))) {
			fieldSetEngineering.expand();
		}

		FormLayout layoutEnginnering = new FormLayout();
		layoutEnginnering.setLabelAlign(LabelAlign.TOP);
		fieldSetEngineering.setLayout(layoutEnginnering);

		sumInsuredField.setFieldLabel("Sum Insured");
		fieldSetEngineering.add(sumInsuredField, new FormData("35%"));
		policyDetails.add(fieldSetEngineering);

		tabs.add(policyDetails);
		list = new ArrayList<FieldSet>();
		list.add(fieldSetMarine);
		list.add(fieldSet);

		// tab#3 ends here

		// tab#4 starts here

		TabItem amountDetails = new TabItem();
		amountDetails.setStyleAttribute("padding", "10px");
		amountDetails.setText("Amount Details");
		fol = new FormLayout();
		fol.setLabelAlign(LabelAlign.TOP);
		// fol = new FlowLayout();
		amountDetails.setLayout(fol);

		// policy no field
		premiunAmountField.setFieldLabel("Premium Amount");
		amountDetails.add(premiunAmountField, new FormData("35%"));
		premiunAmountField.setEmptyText("Rs.");

		// endrs no field
		terrorismPremiunAmountField.setFieldLabel("Terrorism Premiun Amount");
		amountDetails.add(terrorismPremiunAmountField, new FormData("35%"));
		terrorismPremiunAmountField.setEmptyText("Rs.");

		// Policy starts On field
		serviceTaxField.setFieldLabel("Service Tax Percentage %");
		amountDetails.add(serviceTaxField, new FormData("10%"));
		serviceTaxField.setEmptyText("0.00%");

		// Policy ends On field
		serviceTaxAmountField.setFieldLabel("Service Tax Amount");
		amountDetails.add(serviceTaxAmountField, new FormData("35%"));
		serviceTaxAmountField.setEmptyText("Rs.");

		// ins Company field
		totalPremiunAmountField.setFieldLabel("Total Premiun Amount");
		amountDetails.add(totalPremiunAmountField, new FormData("35%"));
		totalPremiunAmountField.setEmptyText("Rs.");

		// ins Company branch field
		commisionRateField.setFieldLabel("Commision Rate Percentage %");
		amountDetails.add(commisionRateField, new FormData("10%"));
		commisionRateField.setEmptyText("0.00%");

		// office Codefield
		commisionRateAmountField.setFieldLabel("Commision Rate Amount");
		amountDetails.add(commisionRateAmountField, new FormData("35%"));
		commisionRateAmountField.setEmptyText("Rs.");

		// source field
		collectionDate.setFieldLabel("Collection Date");
		amountDetails.add(collectionDate, new FormData("15%"));
		collectionDate.setEmptyText("YYYY-MM-DD");

		tabs.add(amountDetails);
		//tab four ends here
		
		//tab five starts here
		uploadFilesTab = new TabItem();
		uploadFilesTab.setStyleAttribute("padding", "10px");
		uploadFilesTab.setText("Documents");
		//create cloumn list
		List<ColumnConfig> configs = new ArrayList<ColumnConfig>();
		
		//adding columns here
		ColumnConfig column = new ColumnConfig();  
		column.setId("name");  
		column.setHeader("Employee Name");  
		column.setWidth(200);
		
		//making the column values href
		GridCellRenderer<Clients> checkSalary = new GridCellRenderer<Clients>() { 

			@Override
			public Object render(Clients model, String property,
					com.extjs.gxt.ui.client.widget.grid.ColumnData config,
					int rowIndex, int colIndex, ListStore<Clients> store,
					Grid<Clients> grid) {
				
				Button bDispactch = new Button((String) model.get(property),
						new SelectionListener<ButtonEvent>() {
							@Override
							public void componentSelected(ButtonEvent ce) {
								Window.open("http://localhost:8080/Connect2Teloshyd/downloadDocuments", "New Window", "");
								
							}
							});
				
				bDispactch.setWidth(grid.getColumnModel().getColumnWidth(colIndex) - 20);
				bDispactch.setToolTip("Click here to Download Documents.");

				return bDispactch;
				
				}  
			};  
			column.setRenderer(checkSalary);
			
		configs.add(column);
		
		column.setAlignment(HorizontalAlignment.RIGHT);
		
		ColumnModel cm = new ColumnModel(configs);
		
		Grid<Clients> grid = new Grid<Clients>(documentsList, cm);
		grid.setStyleAttribute("borderTop", "none"); 
		grid.setAutoExpandColumn("name"); 
		grid.setBorders(true); 
		grid.setStripeRows(true);
		
		ContentPanel cp = new ContentPanel();  
		cp.setBodyBorder(false);  
		cp.setHeading("Employee List");  
		cp.setButtonAlign(HorizontalAlignment.CENTER);  
		cp.setLayout(new FitLayout());  
		cp.setSize(500, 300); 
		cp.add(grid);
		
		uploadDocuments = new Button("Upload Documents");
		uploadDocuments.setToolTip("Click here to upload documents.");
		
		cp.addButton(uploadDocuments);
	
		uploadFilesTab.add(cp);
		
		if (updateButton)
		{
			tabs.add(uploadFilesTab);	
		}


		//tab five ends here
		
		panel.add(tabs);
		comfirmation = new Button("Confirm");
		comfirmation.setToolTip("Click here to create new policy");
		cancel = new Button("Cancel");
		cancel.setToolTip("Click here to clear all fields");
		update = new Button("Update");
		update.setToolTip("Click here to update existing policy");

		
		int userStatus = Registry.get("team");
		if(userStatus  != 3)
		{
		panel.addButton(comfirmation);
		panel.addButton(cancel);
		panel.addButton(update);
		}

		panel.setSize(800, 600);
		//panel.setBorders(true);
		panel.setBodyBorder(false);
		panel.setHeaderVisible(false);
		tabs.setBorders(true);

		if (GXT.isFocusManagerEnabled()) {
			nameField.getFocusSupport().setPreviousId(
					panel.getButtonBar().getId());
			// home.getFocusSupport().setPreviousId(panel.getButtonBar().getId());

			panel.getButtonBar()
					.getFocusSupport()
					.addListener(FocusManager.TabNext,
							new Listener<BaseEvent>() {
								public void handleEvent(BaseEvent be) {
									tabs.getItem(
											tabs.getSelectedItem() == tabs
													.getItem(0) ? 0 : 1)
											.getItem(0).focus();
									be.setCancelled(true);
								}
							});
			panel.getButtonBar()
					.getFocusSupport()
					.addListener(FocusManager.TabPrevious,
							new Listener<BaseEvent>() {
								public void handleEvent(BaseEvent be) {
									TabItem item = tabs.getItem(tabs
											.getSelectedItem() == tabs
											.getItem(0) ? 0 : 1);
									item.getItem(item.getItemCount() - 1)
											.focus();
									be.setCancelled(true);
								}
							});
		}

		vp.add(panel);
	}

	private void clearAll() {
		nameField.clear();
		mobileField.clear();
		emailField.clear();
		company.clear();
		dateOfBirthField.clear();
		maleRadio.clear();
		femaleRadio.clear();
		individualRadio.clear();
		cooporateRadio.clear();
		addressField.clear();
		policyNoField.clear();
		endrsNoField.clear();
		policyFromDateField.clear();
		policyToDateField.clear();
		insCompanyField.clear();
		insCompanyBranchField.clear();
		officeCodeField.clear();
		sourceField.clear();
		typeOfPolicyField.clear();
		basicRateField.clear();
		earthQuakecField.clear();
		anyAdditionalField.clear();
		specificPolicyField.clear();
		openPolicyField.clear();
		openCoverField.clear();
		otherPoliciesField.clear();
		voyageFromField.clear();
		voyageToField.clear();
		premiunAmountField.clear();
		terrorismPremiunAmountField.clear();
		serviceTaxField.clear();
		serviceTaxAmountField.clear();
		totalPremiunAmountField.clear();
		commisionRateField.clear();
		commisionRateAmountField.clear();
		genderGroup.setValue(maleRadio);
		industryGroup.setValue(individualRadio);
		collectionDate.clear();
		vehicleNoField.clear();
		iDVField.clear();
		vehicleMakeField.clear();
		yearOfManufacturingField.clear();
		nCBField.clear();
		misTypeOfPolicyField.clear();
		misIdCardField.clear();
		dispatchDateField.clear();
		sumInsuredField.clear();
		policyDetailsField.clear();
		agentFieldBox.clear();
		fieldSetEngineering.collapse();
		fieldSetMotor.collapse();
		fieldSet.collapse();
		fieldSetMarine.collapse();
		fieldSetMis.collapse();
	}

}
