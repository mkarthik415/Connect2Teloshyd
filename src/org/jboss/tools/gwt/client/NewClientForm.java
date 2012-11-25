/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jboss.tools.gwt.client;

import com.extjs.gxt.ui.client.GXT;
import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.aria.FocusManager;
import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.DatePickerEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
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
import com.extjs.gxt.ui.client.widget.form.Radio;
import com.extjs.gxt.ui.client.widget.form.RadioGroup;
import com.extjs.gxt.ui.client.widget.form.TextArea;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.extjs.gxt.ui.client.widget.layout.FlowLayout;
import com.extjs.gxt.ui.client.widget.layout.FormData;
import com.extjs.gxt.ui.client.widget.layout.FormLayout;
import com.google.gwt.user.client.Element;
import java.util.Date;

/**
 * 
 * @author Karthik Marupeddi
 */
public class NewClientForm extends LayoutContainer {

	private VerticalPanel vp;
	private FormLayout fol = null;
	// private FlowLayout fol= null;

	// tab #1 contents
	TextField<String> nameField = new TextField<String>();
	TextField<String> mobileField = new TextField<String>();
	TextField<String> emailField = new TextField<String>();

	DateField dateOfBirthField = new DateField();

	Radio maleRadio = new Radio();
	Radio femaleRadio = new Radio();
	Radio individualRadio = new Radio();
	Radio cooporateRadio = new Radio();

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

	// tab#3 contents
	// firefieldset
	TextField<String> typeOfPolicyField = new TextField<String>();
	TextField<String> basicRateField = new TextField<String>();
	TextField<String> earthQuakecField = new TextField<String>();
	TextField<String> anyAdditionalField = new TextField<String>();
	// marine fieldset
	TextField<String> specificPolicyField = new TextField<String>();
	TextField<String> openPolicyField = new TextField<String>();
	TextField<String> openCoverField = new TextField<String>();
	TextField<String> otherPoliciesField = new TextField<String>();
	TextField<String> voyageFromField = new TextField<String>();
	TextField<String> voyageToField = new TextField<String>();
	
	// tab#4 contents
	TextField<Double> premiunAmountField = new TextField<Double>();
	TextField<Double> terrorismPremiunAmountField = new TextField<Double>();
	TextField<Double> serviceTaxField = new TextField<Double>();
	TextField<Double> serviceTaxAmountField = new TextField<Double>();
	TextField<Double> totalPremiunAmountField = new TextField<Double>();
	TextField<Double> commisionRateField = new TextField<Double>();
	TextField<Double> commisionRateAmountField = new TextField<Double>();
	DateField collectionDate = new DateField();

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

	}

	private void createTabForm() {
		FormData formData = new FormData("100%");
		FormPanel panel = new FormPanel();
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
		mobileField.setFieldLabel("Name of the Insured");
		personal.add(mobileField, new FormData("35%"));

		// mobile filed
		nameField.setFieldLabel("Phone Number");
		personal.add(nameField, new FormData("35%"));

		// dateOfBirth
		dateOfBirthField.setFieldLabel("Date of Birth");
		dateOfBirthField.setMinValue(new Date(80, 1, 1));
		dateOfBirthField.setMaxValue(new Date());
		personal.add(dateOfBirthField, new FormData("15%"));

		// company field
		TextField<String> company = new TextField<String>();
		company.setFieldLabel("Company");
		personal.add(company, new FormData("35%"));

		// email field
		TextField<String> email = new TextField<String>();
		email.setFieldLabel("Email");
		personal.add(email, new FormData("35%"));

		// gender field
		maleRadio.setBoxLabel("Male");
		femaleRadio.setBoxLabel("Female");
		RadioGroup genderGroup = new RadioGroup();
		genderGroup.setFieldLabel("Gender");
		genderGroup.add(maleRadio);
		genderGroup.add(femaleRadio);
		personal.add(genderGroup, formData);

		// industry field
		individualRadio.setBoxLabel("Individual");
		cooporateRadio.setBoxLabel("Co-oporateRadio");
		RadioGroup industryGroup = new RadioGroup();
		industryGroup.setFieldLabel("Industry");
		industryGroup.add(individualRadio);
		industryGroup.add(cooporateRadio);
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
		fol = new FormLayout();
		fol.setLabelAlign(LabelAlign.TOP);
		// fol = new FlowLayout();
		insDetails.setLayout(fol);

		// policy no field
		policyNoField.setFieldLabel("Policy/Certificate No");
		insDetails.add(policyNoField, new FormData("35%"));

		// endrs no field
		endrsNoField.setFieldLabel("Endrs No");
		insDetails.add(endrsNoField, new FormData("35%"));

		// Policy starts On field
		policyFromDateField.setFieldLabel("Policy starts On");
		insDetails.add(policyFromDateField, new FormData("15%"));

		// Policy ends On field
		policyToDateField.setFieldLabel("Policy Ends On");
		insDetails.add(policyToDateField, new FormData("15%"));

		// ins Company field
		insCompanyField.setFieldLabel("Ins.Company Name");
		insDetails.add(insCompanyField, new FormData("35%"));

		// ins Company branch field
		insCompanyBranchField.setFieldLabel("Ins.Branch Name");
		insDetails.add(insCompanyBranchField, new FormData("35%"));

		// office Codefield
		officeCodeField.setFieldLabel("Office Code");
		insDetails.add(officeCodeField, new FormData("35%"));

		// source field
		sourceField.setFieldLabel("Source");
		insDetails.add(sourceField, new FormData("35%"));

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
		FieldSet fieldSet = new FieldSet();
		fieldSet.setHeading("Fire");
		// fieldSet.setCheckboxToggle(false);
		fieldSet.setCollapsible(true);
		fieldSet.setExpanded(false);

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

		anyAdditionalField.setFieldLabel("Email");
		fieldSet.add(anyAdditionalField, new FormData("35%"));

		policyDetails.add(fieldSet);

		// Marine fieldset in tab#4

		FieldSet fieldSetMarine = new FieldSet();
		fieldSetMarine.setHeading("Marine");
		fieldSetMarine.setCheckboxToggle(false);
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

		tabs.add(policyDetails);
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

		// endrs no field
		terrorismPremiunAmountField.setFieldLabel("Terrorism Premiun Amount");
		amountDetails.add(terrorismPremiunAmountField, new FormData("35%"));

		// Policy starts On field
		serviceTaxField.setFieldLabel("Service Tax");
		amountDetails.add(serviceTaxField, new FormData("10%"));

		// Policy ends On field
		serviceTaxAmountField.setFieldLabel("Service Tax Amount");
		amountDetails.add(serviceTaxAmountField, new FormData("35%"));

		// ins Company field
		totalPremiunAmountField.setFieldLabel("Total Premiun Amount");
		amountDetails.add(totalPremiunAmountField, new FormData("35%"));

		// ins Company branch field
		commisionRateField.setFieldLabel("Commision Rate");
		amountDetails.add(commisionRateField, new FormData("10%"));

		// office Codefield
		commisionRateAmountField.setFieldLabel("Commision Rate Amount");
		amountDetails.add(commisionRateAmountField, new FormData("35%"));

		// source field
		collectionDate.setFieldLabel("Collection Date");
		insDetails.add(collectionDate, new FormData("15%"));

		tabs.add(amountDetails);

		panel.add(tabs);
		panel.addButton(new Button("Cancel"));
		panel.addButton(new Button("Submit"));

		panel.setSize(700, 500);

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

}
