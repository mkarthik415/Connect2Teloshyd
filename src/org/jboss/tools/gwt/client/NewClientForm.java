/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jboss.tools.gwt.client;

import com.extjs.gxt.ui.client.GXT;
import com.extjs.gxt.ui.client.Registry;
import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.Style.SelectionMode;
import com.extjs.gxt.ui.client.aria.FocusManager;
import com.extjs.gxt.ui.client.event.*;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.util.Margins;
import com.extjs.gxt.ui.client.widget.*;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.*;
import com.extjs.gxt.ui.client.widget.form.FormPanel.LabelAlign;
import com.extjs.gxt.ui.client.widget.grid.*;
import com.extjs.gxt.ui.client.widget.layout.ColumnData;
import com.extjs.gxt.ui.client.widget.layout.*;
import com.extjs.gxt.ui.client.widget.tips.QuickTip;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.datepicker.client.CalendarUtil;
import gwtupload.client.IFileInput.FileInputType;
import gwtupload.client.MultiUploader;
import org.jboss.tools.gwt.shared.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 
 * @author Karthik Marupeddi
 */
public class NewClientForm extends ContentPanel {

	public NewClientForm() {
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
	TextField<String> secondaryMobileField = new TextField<String>();

	Label primaryEmailLabel = new Label();
	LayoutContainer primaryEmailContainer = null;
	TextField<String> emailField = new TextField<String>();
	Image emailImage = new Image("resources/images/email_add.png");
	Image secondaryEmailImage = new Image("resources/images/email_delete.png");

	Label secondaryEmailLabel = null;
	TextField<String> secondaryEmailField = new TextField<String>();
	HBoxLayoutData secondaryEmailLayoutData = new HBoxLayoutData(new Margins(
			10, 5, 10, 0));
	HBoxLayoutData secondaryEmailIconLayoutData = null;
	LayoutContainer secondaryEmailContainer = new LayoutContainer();

	// TextField<String> company = new TextField<String>();
	SimpleComboBox<String> company = new SimpleComboBox<String>();
	// NumberField number = new NumberField();
	Label primaryPhoneLabel = null;
	Image image = new Image("resources/images/telephone_add.png");
	LayoutContainer primaryPhoneContainer = null;
	Label secondaryPhoneLabel = null;
	LayoutContainer secondaryPhoneContainer = new LayoutContainer();
	HBoxLayoutData secondaryPhoneLayoutData = new HBoxLayoutData(new Margins(
			10, 5, 10, 0));
	HBoxLayoutData secondaryPhoneIconLayoutData = null;
	Image cancelImage = new Image("resources/images/telephone_delete.png");

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
	DateTimePropertyEditor dateFormat = new DateTimePropertyEditor("dd-MM-yyyy");

	// TextField<String> insCompanyField = new TextField<String>();
	SimpleComboBox<String> insCompanyField = new SimpleComboBox<String>();
	TextArea insCompanyBranchField = new TextArea();
	TextField<String> officeCodeField = new TextField<String>();
	TextField<String> AgentField = new TextField<String>();
	Label agentLabel = null;
	Label referenceLabel = null;
	LayoutContainer agentContainer = null;
	SimpleComboBox<String> agentFieldBox = new SimpleComboBox<String>();
	TextField<String> sourceField = new TextField<String>();

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
    DateField collectionDate = new DateField();
    NumberField commisionRateAmountField = new NumberField();

    // tab#5 contents
	TabItem uploadFilesTab;
    MultiUploader uploader = new MultiUploader(FileInputType.BROWSER_INPUT);
    private ListStore<File> documentsList = new ListStore<File>();
    Grid<File> grid = null;

	ColumnModel cm = null;

    //tab#6 contents
    NumberField renewalAmountField = new NumberField();
    DateField renewalSMSSentOn = new DateField();

	void setDocumentsList(File model) {
		documentsList.add(model);
	}

	final DialogBox dialogBox = new DialogBox();

	MessageBox box;

	SimpleComboBox<String>  yearOfManufacturingField = new SimpleComboBox<String>();

	Button comfirmation = null;

    Button sendSMS = null;
	
	Button deleteDocuments = null;

	Button uploadDocuments = null;

	Button reloadTable = null;

    Button policyRenewal = null;

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
	public Boolean serialNumberStatus = false;
	FieldSet fieldSetMotor = new FieldSet();
	public FieldSet fieldSetMarine = new FieldSet();
	public FieldSet fieldSetMis = new FieldSet();
	public FieldSet fieldSetEngineering = new FieldSet();
	public List<FieldSet> list;
	public Boolean secondaryMobilefound = false;
	public Boolean secondaryEmailfound = false;
	public Boolean sourceFound = false;
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
	public String manufacturingYearFound = null; 
	CheckBoxSelectionModel<File> checkBox;
	List<File> files;
    protected Boolean renewalStatus = false;

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

		} else {
			update.setVisible(false);

		}

		if (secondaryMobilefound) {
			image.setVisible(false);
			secondaryPhoneLabel.setVisible(true);
			secondaryPhoneContainer.setVisible(true);
			secondaryMobileField.setVisible(true);
			cancelImage.setVisible(true);
		} else {
			secondaryPhoneLabel.setVisible(false);
			secondaryMobileField.setVisible(false);
			cancelImage.setVisible(false);
			secondaryPhoneContainer.setVisible(false);
		}

		if (secondaryEmailfound) {
			emailImage.setVisible(false);
			secondaryEmailLabel.setVisible(true);
			secondaryEmailField.setVisible(true);
			secondaryEmailImage.setVisible(true);
			secondaryEmailContainer.setVisible(true);

		} else {
			secondaryEmailLabel.setVisible(false);
			secondaryEmailField.setVisible(false);
			secondaryEmailImage.setVisible(false);
			secondaryEmailContainer.setVisible(false);

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
				@SuppressWarnings("deprecation")
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
								c.setSecondaryPhoneNumber(secondaryMobileField
										.getValue());
								c.setDob(dateOfBirthField.getValue());
								c.setCompany(company.getSimpleValue());
								c.setEmail(emailField.getValue());
								c.setSecondaryEmail(secondaryEmailField
										.getValue());
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
								c.setInsCompanyName(insCompanyField
										.getSimpleValue());
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
								c.setMiscDispatchDate(dispatchDateField
										.getValue());
								c.setSumInsured((Double) sumInsuredField
										.getValue());
								// motor
								c.setVehicleNumber(vehicleNoField.getValue());
								c.setiDV(iDVField.getValue());
								c.setVehicleMake(vehicleMakeField.getValue());
								c.setVehicleManufactureYear(new Date(Integer.parseInt(yearOfManufacturingField
										.getSimpleValue()) -1900,0,1));
								
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
														if (result != null
																&& !result
																		.equals("same")) {
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
														if (result != null
																&& result
																		.equals("same")) {
															System.out
																	.println("did not execute properly..");
															MessageBox messageBox = new MessageBox();
															messageBox
																	.setMessage("Policy Number and Endrs No already keyed !! Search and make a update.");
															messageBox.show();
															// btnSubmit.enable();
														} else if (result == null) {
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
				@SuppressWarnings("deprecation")
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
								c.setSecondaryPhoneNumber(secondaryMobileField
										.getValue());
								c.setDob(dateOfBirthField.getValue());
								c.setCompany(company.getSimpleValue());
								c.setEmail(emailField.getValue());
								c.setSecondaryEmail(secondaryEmailField
										.getValue());
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
								c.setnBC(nCBField.getValue());
								c.setMarineTypeOfPolicy(specificPolicyField.getValue());
								c.setMarineOpenPolicy(openPolicyField.getValue());
								c.setMarineOpenCover(openCoverField.getValue());
								c.setMarineOtherPolicies(otherPoliciesField.getValue());
								c.setMarineVoyageFrom(voyageFromField.getValue());
								c.setMarineVoyageTo(voyageToField.getValue());
								c.setPremiumAmount((Double) premiunAmountField.getValue());
								c.setTerrorismPremiumAmount((Double) terrorismPremiunAmountField.getValue());
								c.setServiceTax((Double) serviceTaxField.getValue());
								c.setServiceTaxAmount((Double) serviceTaxAmountField.getValue());
								c.setTotalPremiumAmount((Double) totalPremiunAmountField.getValue());
								c.setCommionRate((Double) commisionRateField.getValue());
								c.setCommionRateAmount((Double) commisionRateAmountField.getValue());
								c.setMiscTypeOfPolicy(misTypeOfPolicyField.getValue());
								c.setMiscIdCard(misIdCardField.getValue());
								c.setMiscDispatchDate(dispatchDateField.getValue());
								c.setDepartment(fieldSetFound);
								if (insCompanyField.getSimpleValue().isEmpty()) {
									c.setInsCompanyName(insuranceCompanyFound);
								} else
								{
									c.setInsCompanyName(insCompanyField
											.getSimpleValue());
									
								}
								c.setInsBranchName(insCompanyBranchField
										.getValue());
								c.setOfficeCode(officeCodeField.getValue());
								c.setSource(sourceField.getValue());
								c.setPolicyDetails(policyDetailsField
										.getValue());
								if (policyType == null) {
									c.setPolicyType(fieldSetFound);
								} else {
									c.setPolicyType(policyType);
								}
								if (agentFieldBox.getSimpleValue().isEmpty()) {
									c.setAgent(agentFound);
								} else
								{
									c.setAgent(agentFieldBox.getSimpleValue());
									
								}
								c.setCollectionDate(collectionDate.getValue());
								c.setFireTypeOfPolicy(typeOfPolicyField
										.getValue());
								c.setBasicRate((Double) basicRateField
										.getValue());
								c.setEarthQuakePremium((Double) earthQuakecField
										.getValue());
								c.setAnyAdditionalPremium((Double) anyAdditionalField
										.getValue());
                                //reewal
                                c.setRenewalAmount((Double) renewalAmountField.getValue());
								// motor
								c.setVehicleNumber(vehicleNoField.getValue());
								c.setiDV(iDVField.getValue());
								c.setVehicleMake(vehicleMakeField.getValue());
								c.setVehicleManufactureYear(new Date(Integer
										.parseInt(yearOfManufacturingField
												.getSimpleValue()) - 1900, 0, 1));
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
														if (result != null
																&& !result
																		.equals("same")) {
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
														if (result != null
																&& result
																		.equals("same")) {
															System.out
																	.println("did not execute properly..");
															MessageBox messageBox = new MessageBox();
															messageBox
																	.setMessage("Policy Number and Endrs No already keyed !! Search and make a update.");
															messageBox.show();
															// btnSubmit.enable();
														} else {
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
																.setMessage("You update was not succesful "
																		+ result);
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



        policyRenewal.addListener(Events.OnClick, new Listener<ButtonEvent>() {
            final Listener<MessageBoxEvent> l = new Listener<MessageBoxEvent>() {
                @SuppressWarnings("deprecation")
                public void handleEvent(MessageBoxEvent ce) {
                    Button btn = ce.getButtonClicked();

                    if (btn.getText().equals(yes)) {
                        Info.display("MessageBox",
                                "The '{0}' button was pressed", btn.getText());
                        logger.log(
                                Level.SEVERE,
                                "exception at updating ......."
                                        + fieldSet.isExpanded());
                        if (panel.isValid()) {
                            System.out.println(" is the fire field expanded "
                                    + fieldSet.isExpanded());
                            c = new Client();
                            try {
                                c.setExpiredId(iD);
                                c.setClientName(nameField.getValue());
                                c.setPhoneNumber(mobileField.getValue());
                                c.setSecondaryPhoneNumber(secondaryMobileField
                                        .getValue());
                                c.setDob(dateOfBirthField.getValue());
                                c.setCompany(company.getSimpleValue());
                                c.setEmail(emailField.getValue());
                                c.setSecondaryEmail(secondaryEmailField
                                        .getValue());
                                c.setGender(genderGroup.getValue()
                                        .getBoxLabel());
                                c.setIndustry(industryGroup.getValue()
                                        .getBoxLabel());
                                c.setAddress(addressField.getValue());

                                //calculate policy from date
                                Date compareDate = policyToDateField.getValue();
                                CalendarUtil.addDaysToDate(compareDate,1);
                                c.setPolicyStartdate(compareDate);

                                c.setnBC(nCBField.getValue());
                                c.setMarineTypeOfPolicy(specificPolicyField.getValue());
                                c.setMarineOpenPolicy(openPolicyField.getValue());
                                c.setMarineOpenCover(openCoverField.getValue());
                                c.setMarineOtherPolicies(otherPoliciesField.getValue());
                                c.setMarineVoyageFrom(voyageFromField.getValue());
                                c.setMarineVoyageTo(voyageToField.getValue());
                                c.setMiscTypeOfPolicy(misTypeOfPolicyField.getValue());
                                c.setMiscIdCard(misIdCardField.getValue());
                                c.setMiscDispatchDate(dispatchDateField.getValue());
                                c.setDepartment(fieldSetFound);
                                if (insCompanyField.getSimpleValue().isEmpty()) {
                                    c.setInsCompanyName(insuranceCompanyFound);
                                } else {
                                    c.setInsCompanyName(insCompanyField
                                            .getSimpleValue());

                                }
                                c.setInsBranchName(insCompanyBranchField
                                        .getValue());
                                c.setOfficeCode(officeCodeField.getValue());
                                c.setSource(sourceField.getValue());
                                c.setPolicyDetails(policyDetailsField
                                        .getValue());
                                if (policyType == null) {
                                    c.setPolicyType(fieldSetFound);
                                } else {
                                    c.setPolicyType(policyType);
                                }
                                if (agentFieldBox.getSimpleValue().isEmpty()) {
                                    c.setAgent(agentFound);
                                } else {
                                    c.setAgent(agentFieldBox.getSimpleValue());

                                }
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
                                c.setVehicleManufactureYear(new Date(Integer
                                        .parseInt(yearOfManufacturingField
                                                .getSimpleValue()) - 1900, 0, 1));


                            } catch (Exception ee) {
                                logger.log(Level.SEVERE,
                                        "exception at ui level" + ee.toString());
                            }
                            ((GreetingServiceAsync) GWT
                                    .create(GreetingService.class))
                                    .policyRenewal(c,
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
                                                        if (result != null
                                                                && !result
                                                                .equals("same")) {
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
                                                        if (result != null
                                                                && result
                                                                .equals("same")) {
                                                            System.out
                                                                    .println("did not execute properly..");
                                                            MessageBox messageBox = new MessageBox();
                                                            messageBox
                                                                    .setMessage("Policy Number and Endrs No already keyed !! Search and make a update.");
                                                            messageBox.show();
                                                            // btnSubmit.enable();
                                                        } else if (result == null) {
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

                MessageBox box = new MessageBox();
                box.setButtons(MessageBox.YESNO);
                box.setIcon(MessageBox.QUESTION);
                box.setTitle("Do you want to Renew Policy now?");
                box.addCallback(l);
                box.setMessage("Would you like to renew existing policy?");
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
									insCompanyField.add(insurance
											.getScreenName());
									if (insuranceCompanyFound != null
											&& insuranceCompanyFound
													.equals(insurance
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

		company.addSelectionChangedListener(new SelectionChangedListener<SimpleComboValue<String>>() {

			@Override
			public void selectionChanged(
					SelectionChangedEvent<SimpleComboValue<String>> se) {

				// agentFieldBox.add("Rao");
				// mobileField.setValue(se.getSelectedItem().getValue());
				Company companyName = new Company();
				companyName.setCompanyName(se.getSelectedItem().getValue());
				((GreetingServiceAsync) GWT.create(GreetingService.class))
						.loadCompanyDetails(companyName,
								new AsyncCallback<CompanyDetails>() {

									@Override
									public void onFailure(Throwable caught) {
										MessageBox messageBox = new MessageBox();
										messageBox
												.setMessage("no company listed!!");
										messageBox.show();

									}

									@Override
									public void onSuccess(CompanyDetails result) {
										if (mobileField.getValue() == null) {

											mobileField.setValue(result
													.getPhoneNumber());
										}
										if (secondaryMobileField.getValue() == null) {
											secondaryMobileField.setValue(result
													.getSecondaryPhoneNumber());
											if (result
													.getSecondaryPhoneNumber() != null) {
												secondaryMobilefound = true;
												image.setVisible(false);
												secondaryPhoneLabel
														.setVisible(true);
												secondaryPhoneContainer
														.setVisible(true);
												secondaryMobileField
														.setVisible(true);
												cancelImage.setVisible(true);
											}
										}

										if (emailField.getValue() == null) {

											emailField.setValue(result
													.getEmail());
										}
										if (secondaryEmailField.getValue() == null) {

											secondaryEmailField.setValue(result
													.getSecondaryEmail());
											if (result.getSecondaryEmail() != null) {
												secondaryEmailfound = true;
												emailImage.setVisible(false);
												secondaryEmailLabel
														.setVisible(true);
												secondaryEmailField
														.setVisible(true);
												secondaryEmailImage
														.setVisible(true);
												secondaryEmailContainer
														.setVisible(true);
											}
										}
										if (addressField.getValue() == null) {

											addressField.setValue(result
													.getAddress());
										}

									}

								});

			}

		});

		
		policyFromDateField.getDatePicker().addListener(Events.Select, new Listener<DatePickerEvent>() {

            public void handleEvent(DatePickerEvent be) {

                if(policyToDateField.getValue() == null)
                {

                    Date fromDate = policyFromDateField.getValue();
                    CalendarUtil.addMonthsToDate(fromDate, 12);
                    CalendarUtil.addDaysToDate(fromDate, -1);
                    policyToDateField.setValue(fromDate);
                }
            }
            
        });
		
		policyFromDateField.addListener(Events.Change,
				new Listener<FieldEvent>() {

					@Override
					public void handleEvent(FieldEvent be) {

                        if(policyToDateField.getValue() == null)
                        {

                            Date fromDate = policyFromDateField.getValue();
                            CalendarUtil.addMonthsToDate(fromDate, 12);
                            CalendarUtil.addDaysToDate(fromDate, -1);
                            policyToDateField.setValue(fromDate);
                        }
						
						
					}
			
		});
		
		officeCodeField.addListener(Events.KeyUp, new Listener<BaseEvent>() {

			@Override
			public void handleEvent(BaseEvent be) {
				// agentFieldBox.add("Rao");

				c = new Client();
				c.setOfficeCode(officeCodeField.getValue());

				((GreetingServiceAsync) GWT.create(GreetingService.class))
						.searchInsuranceCompanyDetails(c,
								new AsyncCallback<Clients>() {

									@Override
									public void onFailure(Throwable arg0) {
										MessageBox messageBox = new MessageBox();
										messageBox
												.setMessage("no company found with the given office code !!");
										messageBox.show();

									}

									public void onSuccess(Clients result) {
										if (result.getInsBranchName() != null) {
											insCompanyBranchField.setValue(result
													.getInsBranchName());

										}
										if (result.getInsCompanyName() != null) {
											insCompanyField.setSimpleValue(result
													.getInsCompanyName());
										}
									}

								});

			}
		});
		
		yearOfManufacturingField.addListener(Events.Render, new Listener<BaseEvent>() {

					@Override
					public void handleEvent(BaseEvent be) {
								Date date = new Date();
									int lastYear = date.getYear() + 1901;
									int firstYear = lastYear - 30;

									for (Integer i = lastYear; i > firstYear; i--) {
										yearOfManufacturingField.add(i.toString());
									}
									
										if(manufacturingYearFound != null)
										{
											
											
							yearOfManufacturingField
									.setSimpleValue(manufacturingYearFound);
										}
									
					}
						
		});

		uploadDocuments.addListener(Events.OnClick,
				new Listener<ButtonEvent>() {

					@Override
					public void handleEvent(ButtonEvent be) {
						UploadExcel uploadDocumentsTab = new UploadExcel();
						TabPanel homeTabpanel = Registry.get("tabPanel");
						TabItem item = new TabItem();
						item.setText("Upload Documents");
						item.setId("Upload Documents");
						item.setClosable(true);
						item.add(uploadDocumentsTab);
						homeTabpanel.add(item);
						homeTabpanel.setSelection(item);
						homeTabpanel.setBorders(false);
						uploadDocumentsTab.clientName.setValue(nameField
								.getValue());
						uploadDocumentsTab.policyNumber.setValue(policyNoField
								.getValue());
						uploadDocumentsTab.clientId.setValue(iD);
						uploadDocumentsTab.fieldName = iD;
					}

				});
		

		grid.addListener(Events.ViewReady, new Listener<BaseEvent>() {

			@Override
			public void handleEvent(BaseEvent be) {

				c = new Client();
				c.setId(iD);
				((GreetingServiceAsync) GWT.create(GreetingService.class))
						.getUploadedDocumentsForClient(c,
								new AsyncCallback<List<File>>() {

									@Override
									public void onFailure(Throwable caught) {
										MessageBox messageBox = new MessageBox();
										messageBox
												.setMessage("Sorry we are not able to find the Documents right now. "
														+ "Please try later !!");
										messageBox.show();

									}

									@Override
									public void onSuccess(List<File> result) {
										ListStore<File> newStore = new ListStore<File>();
										List<File> stocks = new ArrayList<File>();
										stocks = result;
										newStore.add(stocks);
										grid.reconfigure(newStore, cm);
										reloadTable.fireEvent(Events.OnClick);

									}

								});
			}

		});

		reloadTable.addListener(Events.OnClick, new Listener<ButtonEvent>() {

			@Override
			public void handleEvent(ButtonEvent be) {

				c = new Client();
				c.setId(iD);
				((GreetingServiceAsync) GWT.create(GreetingService.class))
						.getUploadedDocumentsForClient(c,
								new AsyncCallback<List<File>>() {

									@Override
									public void onFailure(Throwable caught) {
										MessageBox messageBox = new MessageBox();
										messageBox
												.setMessage("Sorry we are not able to find the Documents right now. "
														+ "Please try later !!");
										messageBox.show();

									}

									@Override
									public void onSuccess(List<File> result) {
										ListStore<File> newStore = new ListStore<File>();
										List<File> stocks = new ArrayList<File>();
										stocks = result;
										newStore.add(stocks);
										grid.reconfigure(newStore, cm);

									}

								});
			}

		});

		deleteDocuments.addListener(Events.OnClick,
				new Listener<ButtonEvent>() {

					@Override
					public void handleEvent(ButtonEvent be) {
						files = grid.getSelectionModel().getSelectedItems();
						deleteDocuments.disable();
						c = new Client();
						c.setId(iD);
						((GreetingServiceAsync) GWT
								.create(GreetingService.class))
								.deleteDocumentsForClient(c, files, new AsyncCallback<Boolean>() {

											@Override
											public void onFailure(
													Throwable caught) {
												MessageBox messageBox = new MessageBox();
												messageBox
														.setMessage("Sorry we are not able delete documents right now. "
																+ "Please try later !!");
												messageBox.show();

											}

											@Override
											public void onSuccess(Boolean args) {
												reloadTable.fireEvent(Events.OnClick);
												deleteDocuments.enable();

											}

										});
					}

				});

		image.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				image.setVisible(false);
				secondaryPhoneLabel.setVisible(true);
				secondaryMobileField.setVisible(true);
				secondaryPhoneContainer.setVisible(true);
				cancelImage.setVisible(true);
			}
		});

		cancelImage.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				image.setVisible(true);
				secondaryPhoneLabel.setVisible(false);
				secondaryMobileField.setValue("");
				secondaryMobileField.setVisible(false);
				cancelImage.setVisible(false);
				secondaryPhoneContainer.setVisible(false);
			}
		});

		emailImage.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				emailImage.setVisible(false);
				secondaryEmailLabel.setVisible(true);
				secondaryEmailField.setVisible(true);
				secondaryEmailImage.setVisible(true);
				secondaryEmailContainer.setVisible(true);
			}
		});

		secondaryEmailImage.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				emailImage.setVisible(true);
				secondaryEmailLabel.setVisible(false);
				secondaryEmailField.setValue("");
				secondaryEmailField.setVisible(false);
				secondaryEmailImage.setVisible(false);
				secondaryEmailContainer.setVisible(false);
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
		
		// company field
		company.setFieldLabel("Company");
		company.setSelectedStyle(".x-tool-search");
		personal.add(company, new FormData("35%"));


		primaryPhoneLabel = new Label("Phone Number:");
		primaryPhoneLabel.setStyleName("x-form-item ");
		primaryPhoneLabel.setHeight(5);
		personal.add(primaryPhoneLabel);

		primaryPhoneContainer = new LayoutContainer();
		HBoxLayout primaryPhoneHlayout = new HBoxLayout();
		primaryPhoneContainer.setLayout(primaryPhoneHlayout);

		HBoxLayoutData primaryPhoneLayoutData = new HBoxLayoutData(new Margins(
				10, 5, 5, 0));
		HBoxLayoutData primaryPhoneIconLayoutData = new HBoxLayoutData(
				new Margins(10, 5, 5, 0));
		primaryPhoneContainer.add(mobileField, primaryPhoneLayoutData);
		personal.add(primaryPhoneContainer, new FormData("35%"));
		mobileField.setEmptyText("919848334455");
		image.setTitle("Add Secondary Phone no");
		primaryPhoneContainer.add(image, primaryPhoneIconLayoutData);

		// secondary phone number
		secondaryPhoneLabel = new Label("Secondary Phone Number:");
		secondaryPhoneLabel.setStyleName("x-form-item ");
		secondaryPhoneLabel.setHeight(5);
		personal.add(secondaryPhoneLabel);

		// layout horizontal
		HBoxLayout secondaryPhoneHlayout = new HBoxLayout();
		secondaryPhoneContainer.setLayout(secondaryPhoneHlayout);

		secondaryPhoneContainer.add(secondaryMobileField,
				secondaryPhoneLayoutData);
		personal.add(secondaryPhoneContainer, new FormData("35%"));
		secondaryMobileField.setEmptyText("919848334455");
		cancelImage.setTitle("Remove Secondary Phone no");
		if (secondaryMobilefound) {
			secondaryPhoneIconLayoutData = new HBoxLayoutData(new Margins(10,
					5, 10, 3));
		} else {
			secondaryPhoneIconLayoutData = new HBoxLayoutData(new Margins(10,
					5, 30, 153));
		}
		secondaryPhoneContainer.add(cancelImage, secondaryPhoneIconLayoutData);

		// dateOfBirth
		dateOfBirthField.setFieldLabel("Date of Birth");
		dateOfBirthField.setMinValue(new Date(10, 1, 1));
		dateOfBirthField.setMaxValue(new Date());
		personal.add(dateOfBirthField, new FormData("15%"));
		dateOfBirthField.setPropertyEditor(dateFormat);
		dateOfBirthField.setEmptyText("DD-MM-YYYY");

		// email label
		primaryEmailLabel = new Label("Email:");
		primaryEmailLabel.setStyleName("x-form-item ");
		primaryEmailLabel.setHeight(5);
		personal.add(primaryEmailLabel);

		// email field
		primaryEmailContainer = new LayoutContainer();
		HBoxLayout primaryEmailHlayout = new HBoxLayout();
		primaryEmailContainer.setLayout(primaryEmailHlayout);

		HBoxLayoutData primaryEmailLayoutData = new HBoxLayoutData(new Margins(
				10, 5, 5, 0));
		HBoxLayoutData primaryEmailIconLayoutData = new HBoxLayoutData(
				new Margins(10, 5, 5, 0));
		primaryEmailContainer.add(emailField, primaryEmailLayoutData);
		emailImage.setTitle("Add Secondary Email");
		primaryEmailContainer.add(emailImage, primaryEmailIconLayoutData);
		personal.add(primaryEmailContainer, new FormData("35%"));
		emailField.setRegex(".+@.+\\.[a-z]+");
		emailField.getMessages().setRegexText("Bad email address!!");
		emailField.setAutoValidate(true);
		emailField.setEmptyText("example@example.com");

		// Secondary email label
		secondaryEmailLabel = new Label("Secondary Email:");
		secondaryEmailLabel.setStyleName("x-form-item ");
		secondaryEmailLabel.setHeight(5);
		personal.add(secondaryEmailLabel);

		// Secondary email field
		HBoxLayout secondaryEmailHlayout = new HBoxLayout();
		secondaryEmailContainer.setLayout(secondaryEmailHlayout);

		secondaryEmailImage.setTitle("Remove Seondary Email");
		secondaryEmailContainer.add(secondaryEmailField,
				secondaryEmailLayoutData);
		if (secondaryEmailfound) {

			secondaryEmailIconLayoutData = new HBoxLayoutData(new Margins(10,
					5, 10, 3));
		} else {

			secondaryEmailIconLayoutData = new HBoxLayoutData(new Margins(10,
					5, 30, 153));
		}
		secondaryEmailContainer.add(secondaryEmailImage,
				secondaryEmailIconLayoutData);
		personal.add(secondaryEmailContainer, new FormData("35%"));
		secondaryEmailField.setRegex(".+@.+\\.[a-z]+");
		secondaryEmailField.getMessages().setRegexText("Bad email address!!");
		secondaryEmailField.setAutoValidate(true);
		secondaryEmailField.setEmptyText("example@example.com");

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
		policyFromDateField.setPropertyEditor(dateFormat);
		left.add(policyFromDateField, new FormData("40%"));

		// Policy ends On field
		policyToDateField.setFieldLabel("Policy Ends On");
		policyToDateField.setPropertyEditor(dateFormat);
		right.add(policyToDateField, new FormData("30%"));
		
		// office Codefield
		officeCodeField.setFieldLabel("Office Code");
		left.add(officeCodeField);

		// ins Company field
		insCompanyField.setFieldLabel("Ins.Company Name");
		left.add(insCompanyField);

		// ins Company branch field
		insCompanyBranchField.setFieldLabel("Ins.Branch Name");
		insCompanyBranchField.setHeight(100);
		right.add(insCompanyBranchField, new FormData("70%"));

		agentLabel = new Label("Agent:");
		agentLabel.setStyleName("x-form-item ");
		agentLabel.setHeight(5);
		left.add(agentLabel);

		agentContainer = new LayoutContainer();
		HBoxLayout referenceHlayout = new HBoxLayout();
		agentContainer.setLayout(referenceHlayout);

		HBoxLayoutData referenceLayoutData = new HBoxLayoutData(new Margins(10,
				5, 5, 0));

		agentFieldBox.setFieldLabel("Agent");
		agentContainer.add(agentFieldBox, referenceLayoutData);
		left.add(agentContainer);
		sourceField.setFieldLabel("Reference");
		left.add(sourceField);
		// secondary phone number

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
		yearOfManufacturingField.setSelectedStyle(".x-tool-search");
		fieldSetMotor.add(yearOfManufacturingField, new FormData("9%"));

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
		dispatchDateField.setPropertyEditor(dateFormat);
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
		collectionDate.setPropertyEditor(dateFormat);
		amountDetails.add(collectionDate, new FormData("15%"));
		collectionDate.setEmptyText("DD-MM-YY");

		tabs.add(amountDetails);
		// tab four ends here

		// tab five starts here
		uploadFilesTab = new TabItem();
		uploadFilesTab.setStyleAttribute("padding", "10px");
		uploadFilesTab.setText("Documents");

		// making the column values href
		GridCellRenderer<File> anchorRender = new GridCellRenderer<File>() {
			@Override
			public Object render(final File model, String property,
					com.extjs.gxt.ui.client.widget.grid.ColumnData config,
					int rowIndex, int colIndex, ListStore<File> store,
					Grid<File> grid) {

				Anchor bDispactch = new Anchor((String) model.get(property));
				bDispactch.setHref(GWT.getHostPageBaseURL()
						+ "downloadDocuments?id=" + model.getId());
				return bDispactch;

			}
		};

		GridCellRenderer<File> change = new GridCellRenderer<File>() {
			@Override
			public Object render(final File model, String property,
					com.extjs.gxt.ui.client.widget.grid.ColumnData config,
					int rowIndex, int colIndex, ListStore<File> store,
					Grid<File> grid) {
				String v = model.get(property);
				return "<span qtip='" + v + "'>" + v + "</span>";
			}
		};

		// create cloumn list
		List<ColumnConfig> configs = new ArrayList<ColumnConfig>();
		
		checkBox = new CheckBoxSelectionModel<File>();
		checkBox.setSelectionMode(SelectionMode.MULTI);
		configs.add(checkBox.getColumn());

		// adding columns here
		ColumnConfig column = new ColumnConfig();
		column.setId("name");
		column.setHeader("Document Name");
		column.setWidth(150);
		column.setRenderer(anchorRender);
		configs.add(column);

		column = new ColumnConfig();
		column.setId("description");
		column.setHeader("Description");
		column.setWidth(250);
		column.setRenderer(change);
		configs.add(column);

		cm = new ColumnModel(configs);

		grid = new Grid<File>(documentsList, cm);
		grid.setStyleAttribute("borderTop", "none");
		grid.setAutoExpandColumn("name");
		grid.setBorders(true);
		grid.setStripeRows(true);
		new QuickTip(grid);

		ContentPanel documentsCP = new ContentPanel();
		documentsCP.setBodyBorder(false);
		documentsCP.setHeading("Documents List");
		documentsCP.setButtonAlign(HorizontalAlignment.CENTER);
		documentsCP.setLayout(new FitLayout());
		documentsCP.setSize(400, 300);
		documentsCP.add(grid);

		uploadDocuments = new Button("Upload Documents");
		uploadDocuments.setToolTip("Click here to upload documents.");

		reloadTable = new Button("Refresh");
		reloadTable.setToolTip("Click here to refresh table.");
		
		deleteDocuments = new Button("Delete Documents");
		deleteDocuments.setToolTip("Click here to Delete Documents.");

		documentsCP.addButton(reloadTable);
		documentsCP.addButton(uploadDocuments);
		documentsCP.addButton(deleteDocuments);

		uploadFilesTab.add(documentsCP);
		uploadFilesTab.layout();

		if (updateButton) {
			tabs.add(uploadFilesTab);
		}

		uploadFilesTab.addListener(Events.Render, new Listener<BaseEvent>() {

			@Override
			public void handleEvent(BaseEvent be) {

			}
		});

		// tab five ends here

        //tab six starts here

        TabItem renewalDetails = new TabItem();
        renewalDetails.setStyleAttribute("padding", "10px");
        renewalDetails.setText("Renewal Details");
        fol = new FormLayout();
        fol.setLabelAlign(LabelAlign.TOP);
        // fol = new FlowLayout();
        renewalDetails.setLayout(fol);

        // renewal amount field
        renewalAmountField.setFieldLabel("Renewal Amount");
        renewalDetails.add(renewalAmountField, new FormData("35%"));
        renewalAmountField.setEmptyText("Rs.");

        //renewal sms last sent on
        renewalSMSSentOn.setFieldLabel("Recent SMS Date");
        renewalSMSSentOn.setPropertyEditor(dateFormat);
        renewalDetails.add(renewalSMSSentOn, new FormData("15%"));
        renewalSMSSentOn.setEmptyText("DD-MM-YY");
        renewalSMSSentOn.setEnabled(false);



        //send SMS and Email Button
        sendSMS = new Button("SMS & Email");
        sendSMS.setToolTip("Send SMS and Email to the client noticing him about his renewal");
        renewalDetails.add(sendSMS, new FormData("15%"));

        if(renewalStatus)
        {

            tabs.add(renewalDetails);
        }


        //tab six ends here
		panel.add(tabs);
		comfirmation = new Button("Confirm");
		comfirmation.setToolTip("Click here to create new policy");
		cancel = new Button("Cancel");
		cancel.setToolTip("Click here to clear all fields");
		update = new Button("Update");
		update.setToolTip("Click here to update existing policy");
        policyRenewal = new Button("Renew Policy");
        policyRenewal.setToolTip("Click here to renew policy");

		int userStatus = Registry.get("team");
		if (userStatus != 3) {
			panel.addButton(comfirmation);
			panel.addButton(cancel);
			panel.addButton(update);
            if(renewalStatus)
            {

                panel.addButton(policyRenewal);
            }
		}

		panel.setSize(800, 610);
		// panel.setBorders(true);
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
