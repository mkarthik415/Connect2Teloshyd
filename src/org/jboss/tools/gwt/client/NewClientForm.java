/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jboss.tools.gwt.client;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.jboss.tools.gwt.shared.Client;

import com.extjs.gxt.ui.client.GXT;
import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.aria.FocusManager;
import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.DatePickerEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.FieldEvent;
import com.extjs.gxt.ui.client.event.FieldSetEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.MessageBox;
import com.extjs.gxt.ui.client.widget.TabItem;
import com.extjs.gxt.ui.client.widget.TabPanel;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.ComboBox;
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
import com.extjs.gxt.ui.client.widget.layout.ColumnData;
import com.extjs.gxt.ui.client.widget.layout.ColumnLayout;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.extjs.gxt.ui.client.widget.layout.FormData;
import com.extjs.gxt.ui.client.widget.layout.FormLayout;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * 
 * @author Karthik Marupeddi
 */
public class NewClientForm extends LayoutContainer {

	final Logger logger = Logger.getLogger("logger");
	private VerticalPanel vp;
	private FormLayout fol = null;
	// private FlowLayout fol= null;

	// tab #1 contents
	TextField<String> nameField = new TextField<String>();
	TextField<String> mobileField = new TextField<String>();
	TextField<String> emailField = new TextField<String>();
	TextField<String> company = new TextField<String>();
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
	TextField<String> insCompanyField = new TextField<String>();
	TextField<String> insCompanyBranchField = new TextField<String>();
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
	DateField yearOfManufacturingField = new DateField();
	TextField<String> nCBField = new TextField<String>();

	// miscellaneous fieldset
	TextField<String> misTypeOfPolicyField = new TextField<String>();
	
	//engineering fielsset
	NumberField sumInsuredField = new NumberField();

	// tab#4 contents
	NumberField premiunAmountField = new NumberField();
	NumberField terrorismPremiunAmountField = new NumberField();
	NumberField serviceTaxField = new NumberField();
	NumberField serviceTaxAmountField = new NumberField();
	NumberField totalPremiunAmountField = new NumberField();
	NumberField commisionRateField = new NumberField();
	NumberField commisionRateAmountField = new NumberField();

	DateField collectionDate = new DateField();

	//
	TextArea departmentField = new TextArea();
	// Buttons
	Button btnSubmit = null;
	
	//policy Type
	private String policyType;

	// Creating Bean
	Client c = null;

	@Override
	protected void onRender(Element parent, int index) {
		super.onRender(parent, index);
		vp = new VerticalPanel();
		vp.setSpacing(10);
		createTabForm();
		add(vp);

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
						messageBox.setMessage("Age is " + year + " year "
								+ month + " month " + day + " day");
						messageBox.show();
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
				sumInsuredField.clear();
				fieldSetEngineering.collapse();
				policyType =fieldSet.getHeading();

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
						sumInsuredField.clear();
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
				sumInsuredField.clear();
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
		
		fieldSetEngineering.addListener(Events.Expand, new Listener<FieldSetEvent>() {

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
				fieldSetMis.collapse();
				misTypeOfPolicyField.clear();
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
							commisionAmount = ((premiumAmount.doubleValue() + terrorismPremiumAmount
									.doubleValue()) * serviceTaxField
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
							commisionAmount = ((premiumAmount.doubleValue() + terrorismPremiumAmount
									.doubleValue()) * serviceTaxField
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
							commisionAmount = ((premiumAmount.doubleValue() + terrorismPremiumAmount
									.doubleValue()) * commisionRateField
									.getValue().doubleValue()) / 100;
						commisionRateAmountField.setValue(commisionAmount);

					}
				});

		btnSubmit.addListener(Events.OnClick, new Listener<ButtonEvent>() {

			@Override
			public void handleEvent(ButtonEvent e) {
				if (panel.isValid()) {
					btnSubmit.disable();
					c = new Client();
					try {
						c.setClientName(nameField.getValue());
						c.setPhoneNumber(mobileField.getValue());
						c.setDob(dateOfBirthField.getValue());
						c.setCompany(company.getValue());
						c.setEmail(emailField.getValue());
						c.setGender(genderGroup.getValue().getBoxLabel());
						c.setIndustry(industryGroup.getValue().getBoxLabel());
						c.setAddress(addressField.getValue());
						c.setPolicyNumber(policyNoField.getValue());
						c.setEndrsNumber(endrsNoField.getValue());
						c.setPolicyStartdate(policyFromDateField.getValue());
						c.setPolicyEndDate(policyToDateField.getValue());
						c.setInsCompanyName(insCompanyField.getValue());
						c.setInsBranchName(insCompanyBranchField.getValue());
						c.setOfficeCode(officeCodeField.getValue());
						c.setSource(sourceField.getValue());
						c.setPolicyDetails(policyDetailsField.getValue());
						c.setPolicyType(policyType);
						c.setAgent(agentFieldBox.getValueField());
						c.setCollectionDate(collectionDate.getValue());
						c.setFireTypeOfPolicy(typeOfPolicyField.getValue());
						c.setBasicRate((Double) basicRateField.getValue());
						c.setEarthQuakePremium((Double) earthQuakecField.getValue());
						c.setAnyAdditionalPremium((Double) anyAdditionalField.getValue());
						// motor
						c.setVehicleNumber(vehicleNoField.getValue());
						c.setiDV(iDVField.getValue());
						c.setVehicleMake(vehicleMakeField.getValue());
						c.setVehicleManufactureYear(yearOfManufacturingField
								.getValue());
						c.setnBC(nCBField.getValue());
						c.setMarineTypeOfPolicy(specificPolicyField.getValue());
						c.setMarineOpenPolicy(openPolicyField.getValue());
						c.setMarineOpenCover(openCoverField.getValue());
						c.setMarineOtherPolicies(otherPoliciesField.getValue());
						c.setMarineVoyageFrom(voyageFromField.getValue());
						c.setMarineVoyageTo(voyageToField.getValue());
						c.setPremiumAmount((Double) premiunAmountField.getValue());
						c.setTerrorismPremiumAmount((Double) terrorismPremiunAmountField
								.getValue());
						c.setServiceTax((Double) serviceTaxField.getValue());
						c.setServiceTaxAmount((Double) serviceTaxAmountField.getValue());
						c.setTotalPremiumAmount((Double) totalPremiunAmountField
								.getValue());
						c.setCommionRate((Double) commisionRateField.getValue());
						c.setCommionRateAmount(commisionRateAmountField
								.getValue().doubleValue());
						c.setDepartment(departmentField.getValue());
						c.setMiscTypeOfPolicy(misTypeOfPolicyField.getValue());
						c.setSumInsured((Double) sumInsuredField.getValue());
					} catch (Exception ee) {
						logger.log(Level.SEVERE,
								"exception at ui level" + ee.toString());
					}
					((GreetingServiceAsync) GWT.create(GreetingService.class))
							.createClient(c, new AsyncCallback<Boolean>() {
								public void onFailure(Throwable caught) {
									// Show the RPC error message to the user
									MessageBox messageBox = new MessageBox();
									messageBox
											.setMessage("Client not Submitted !!");
									messageBox.show();
								}

								public void onSuccess(Boolean result) {

									logger.log(Level.SEVERE, "inside Clent ");
									try {
										if (result) {
											logger.log(Level.SEVERE,
													"inside if block ");
											clearAll();
											btnSubmit.enable();

										} else {
											MessageBox messageBox = new MessageBox();
											messageBox
													.setMessage("Please enter the amount properly !!");
											messageBox.show();
											btnSubmit.enable();
										}

									} catch (Exception ex) {
										logger.log(
												Level.SEVERE,
												"exception at ui level"
														+ ex.toString());
										btnSubmit.enable();
									}
								}
							});
				}

			}
		});

		cancel.addListener(Events.OnClick, new Listener<ButtonEvent>() {

			@Override
			public void handleEvent(ButtonEvent e) {
				clearAll();
				btnSubmit.enable();
			}

		});

	}

	private void createTabForm() {
		FormData formData = new FormData("100%");
		panel = new FormPanel();
		panel.setBodyStyleName("example-bg");
		panel.setPadding(0);
		panel.setFrame(false);
		panel.setHeaderVisible(false);
		panel.setBodyBorder(false);
		panel.setButtonAlign(HorizontalAlignment.CENTER);
		FitLayout fl = new FitLayout();
		// FlowLayout fl= new FlowLayout();
		panel.setLayout(fl);
		final TabPanel tabs = new TabPanel();

		TabItem personal = new TabItem();
		personal.setStyleAttribute("padding", "10px");
		personal.setText("Personal Details");
		fol = new FormLayout();
		fol.setLabelAlign(LabelAlign.TOP);
		// fol = new FlowLayout();
		personal.setLayout(fol);

		// name filed
		nameField.setFieldLabel("Name of the Insured");
		nameField.setAllowBlank(false);
		personal.add(nameField, new FormData("35%"));
		nameField.setEmptyText("Enter your full name");

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
		genderGroup.setValue(maleRadio);
		personal.add(genderGroup, formData);

		// industry field
		individualRadio.setBoxLabel("Individual");
		cooporateRadio.setBoxLabel("Co-oporateRadio");
		industryGroup = new RadioGroup();
		industryGroup.setFieldLabel("Industry");
		industryGroup.add(individualRadio);
		industryGroup.add(cooporateRadio);
		industryGroup.setValue(individualRadio);
		personal.add(industryGroup, formData);

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
		right.add(insCompanyBranchField);

		// office Codefield
		officeCodeField.setFieldLabel("Office Code");
		left.add(officeCodeField);

		// source field
		sourceField.setFieldLabel("Source");
		right.add(sourceField);

		agentFieldBox.add("M.N.Rao");
		agentFieldBox.add("M.R.G.Raju");
		agentFieldBox.setFieldLabel("Agent");
		left.add(agentFieldBox);
		
		
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
		fieldSetMotor = new FieldSet();
		fieldSetMotor.setHeading("Motor");
		// fieldSet.setCheckboxToggle(false);
		fieldSetMotor.setCollapsible(true);
		fieldSetMotor.setExpanded(false);
		fieldSetMotor.setCheckboxToggle(true);

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
		fieldSetMotor.add(yearOfManufacturingField, new FormData("35%"));

		nCBField.setFieldLabel("NCB");
		fieldSetMotor.add(nCBField, new FormData("35%"));

		policyDetails.add(fieldSetMotor);

		// Marine fieldset in tab#3

		fieldSetMarine = new FieldSet();
		fieldSetMarine.setHeading("Marine");
		fieldSetMarine.setCheckboxToggle(true);
		fieldSetMarine.setExpanded(false);

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

		//Miscellaneous
		fieldSetMis = new FieldSet();
		fieldSetMis.setHeading("Miscellaneous");
		fieldSetMis.setCheckboxToggle(true);
		fieldSetMis.setExpanded(false);
		
		 FormLayout layoutMis = new FormLayout();
		 layoutMis.setLabelAlign(LabelAlign.TOP);
		 fieldSetMis.setLayout(layoutMis);
			
		 typeOfPolicyField.setFieldLabel("Type of Policy");
		fieldSetMis.add(typeOfPolicyField, new FormData("35%"));
		policyDetails.add(fieldSetMis);
		
		//Engineering
		fieldSetEngineering = new FieldSet();
		fieldSetEngineering.setHeading("Engineering");
		fieldSetEngineering.setCheckboxToggle(true);
		fieldSetEngineering.setExpanded(false);
		
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

		panel.add(tabs);
		btnSubmit = new Button("Submit");
		cancel = new Button("Cancel");
		panel.addButton(cancel);
		panel.addButton(btnSubmit);

		panel.setSize(800, 600);

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
		 sumInsuredField.clear();
		 policyDetailsField.clear();
		 agentFieldBox.clear();
		 fieldSetEngineering.collapse();
		 fieldSetMotor.collapse();
		 fieldSet.collapse();
		 fieldSetMarine.collapse();
		 fieldSetMis.collapse();
	}

	private FormPanel panel;
	private Button cancel;
	private FieldSet fieldSet;
	private FieldSet fieldSetMotor;
	private FieldSet fieldSetMarine;
	private FieldSet fieldSetMis;
	private FieldSet fieldSetEngineering;
	private List<FieldSet> list;

}
