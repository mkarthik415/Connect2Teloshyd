package org.jboss.tools.gwt.client;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import org.jboss.tools.gwt.shared.Company;

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
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Frame;

public class ClientReport extends ContentPanel {
	
	public ClientReport(){
		setHeaderVisible(false);
		setBodyBorder(false);
	}
	
	private VerticalPanel vp;

	final Logger logger = Logger.getLogger("logger");
	@SuppressWarnings("unused")
	private FormData formData;
	TextField<String> name = new TextField<String>();
	SimpleComboBox<String> companyNameFieldBox = new SimpleComboBox<String>();
	DateField fromDate = new DateField();
	DateField toDate = new DateField();
	Map<String, Object> param = new HashMap<String, Object>();
	Frame frame = new Frame();

	@Override
	protected void onRender(Element parent, int index) {
		super.onRender(parent, index);
		formData = new FormData("100%");
		vp = new VerticalPanel();
		vp.setSpacing(10);
		createForm1();
	   // add(frame);
		add(vp);
		
		companyNameFieldBox.addListener(Events.Render, new Listener<BaseEvent>() {

			@Override
			public void handleEvent(BaseEvent be) {

				((GreetingServiceAsync) GWT.create(GreetingService.class))
						.loadComapny(new AsyncCallback<List<Company>>() {

							@Override
							public void onFailure(Throwable arg0) {
								MessageBox messageBox = new MessageBox();
								messageBox.setMessage("no compamy listed!!");
								messageBox.show();

							}

							@Override
							public void onSuccess(List<Company> result) {
								companyNameFieldBox.removeAll();
								for (Company companyInList : result) {
									companyNameFieldBox.add(companyInList.getCompnyName());
									}
							}

						});

			}
		});
		
	        pdfButton.addListener(Events.OnClick, new Listener<BaseEvent>() {
	      
				
			@Override
			public void handleEvent(BaseEvent be) {
				param.put("name",name.getValue());
				param.put("office_code",companyNameFieldBox.getSimpleValue());
				param.put("from_date", fromDate.getValue());
				param.put("to_date", toDate.getValue());
				
				PdfReportViewerForClient pdf = new PdfReportViewerForClient("/resources/Reports/client",param,"client");
				TabPanel tabPanel = Registry.get("tabPanel");
				tabPanel.getSelectedItem().close();
				TabItem item = new TabItem();
				item.setText("Client Report");
          		item.setClosable(true);
				pdf.setHeight(700);
				item.add(pdf);
                tabPanel.add(item);
                tabPanel.setSelection(item);
                

			}
		});
	        
	        excelButton.addListener(Events.OnClick, new Listener<BaseEvent>() {
	      
				
			@Override
			public void handleEvent(BaseEvent be) {
				param.put("name",name.getValue());
				param.put("office_code",companyNameFieldBox.getSimpleValue());
				param.put("from_date", fromDate.getValue());
				param.put("to_date", toDate.getValue());
				
				ExcelReportViewerForClientReport excel = new ExcelReportViewerForClientReport("/resources/Reports/client",param,"client");
				TabPanel tabPanel = Registry.get("tabPanel");
				tabPanel.getSelectedItem().close();
				Window.open(excel.getUrl(), "excel file", "");
			}
		});
		
		
		cancelButton.addListener(Events.OnClick, new Listener<ButtonEvent>() {

			@Override
			public void handleEvent(ButtonEvent e) {
				name.clear();
				companyNameFieldBox.clear();
				fromDate.clear();
				toDate.clear();
				pdfButton.enable();
			}

		});
		
		
	}

	private void createForm1() {
		FormPanel simple = new FormPanel();
		simple.setHeading("Client Report");
		simple.setFrame(true);
		simple.setWidth(550);
		simple.setBorders(false);

			
		
		name.setFieldLabel("Client Name");
		name.setEmptyText("Enter Client Name.");
		companyNameFieldBox.setFieldLabel("Company");
		companyNameFieldBox.setEmptyText("search Company Name.");
		fromDate.setFieldLabel("From Date");
		toDate.setFieldLabel("To Date");
		simple.add(name, new FormData("50%"));
		simple.add(companyNameFieldBox, new FormData("50%"));
		simple.add(fromDate, new FormData("10%"));
		simple.add(toDate, new FormData("1%"));

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

}
