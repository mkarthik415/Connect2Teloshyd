package org.jboss.tools.gwt.client;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.jboss.tools.gwt.shared.Agent;
import org.jboss.tools.gwt.shared.Client;
import org.jboss.tools.gwt.shared.Clients;
import org.jboss.tools.gwt.shared.OfficeCode;

import com.extjs.gxt.ui.client.Registry;
import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.MessageBox;
import com.extjs.gxt.ui.client.widget.TabItem;
import com.extjs.gxt.ui.client.widget.TabPanel;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.DateField;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.SimpleComboBox;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.layout.FormData;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class IrdaReport extends ContentPanel {
	private VerticalPanel vp;

	final Logger logger = Logger.getLogger("logger");
	private FormData formData;
	TextField<String> name = new TextField<String>();
	SimpleComboBox<String> OfficeCodeFieldBox = new SimpleComboBox<String>();
	DateField fromDate = new DateField();
	DateField toDate = new DateField();

	@Override
	protected void onRender(Element parent, int index) {
		super.onRender(parent, index);
		formData = new FormData("-20");
		vp = new VerticalPanel();
		vp.setSpacing(10);
		createForm1();
		add(vp);
		
		OfficeCodeFieldBox.addListener(Events.Render, new Listener<BaseEvent>() {

			@Override
			public void handleEvent(BaseEvent be) {
				System.out.println("on load fired here");
				// agentFieldBox.add("Rao");

				((GreetingServiceAsync) GWT.create(GreetingService.class))
						.loadOfficeCode(new AsyncCallback<List<OfficeCode>>() {

							@Override
							public void onFailure(Throwable arg0) {
								MessageBox messageBox = new MessageBox();
								messageBox.setMessage("no Agents listed!!");
								messageBox.show();

							}

							@Override
							public void onSuccess(List<OfficeCode> arg0) {
								OfficeCodeFieldBox.removeAll();
									for (OfficeCode officeCode : arg0) {
										OfficeCodeFieldBox.add(officeCode.getCompanyOfficeCode());
									
								}

							}

						});

			}
		});
		

		pdfButton.addListener(Events.OnClick, new Listener<ButtonEvent>() {

			@Override
			public void handleEvent(ButtonEvent e) {

				pdfButton.disable();
				c = new Client();
				c.setClientName(name.getValue());
				((GreetingServiceAsync) GWT.create(GreetingService.class))
						.searchClients(c, new AsyncCallback<List<Clients>>() {
							public void onFailure(Throwable caught) {
								// Show the RPC error message to the user
								MessageBox messageBox = new MessageBox();
								messageBox
										.setMessage("Sorry we are not able to find the client right now. Please try later !!");
								messageBox.show();
							}

							public void onSuccess(List<Clients> result) {
								pdfButton.enable();
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
				pdfButton.enable();
			}

		});
	}

	private void createForm1() {
		FormPanel simple = new FormPanel();
		simple.setHeading("IRDA Report");
		simple.setFrame(true);
		simple.setWidth(350);
		simple.setBorders(false);

		name.setFieldLabel("Name");
		name.setAllowBlank(false);
		name.getFocusSupport().setPreviousId(simple.getButtonBar().getId());
		OfficeCodeFieldBox.setFieldLabel("Office Code");
		fromDate.setFieldLabel("From Date");
		toDate.setFieldLabel("To Date");
		simple.add(name, formData);
		simple.add(OfficeCodeFieldBox, formData);
		simple.add(fromDate, formData);
		simple.add(toDate, formData);

		pdfButton = new Button("PDF");
		simple.addButton(pdfButton);
		excelButton = new Button("Excel");
		simple.addButton(excelButton);
		cancelButton = new Button("Cancel");
		simple.addButton(cancelButton);

		simple.setButtonAlign(HorizontalAlignment.CENTER);



		vp.add(simple);
	}

	Button pdfButton;
	Button cancelButton;
	Button excelButton;
	Client c;

}
