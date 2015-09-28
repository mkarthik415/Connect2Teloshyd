package org.jboss.tools.gwt.client;

import com.extjs.gxt.ui.client.Registry;
import com.extjs.gxt.ui.client.Style;
import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.TabItem;
import com.extjs.gxt.ui.client.widget.TabPanel;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.DateField;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.SimpleComboBox;
import com.extjs.gxt.ui.client.widget.layout.FormData;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Frame;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

/**
 * Created by karthikmarupeddi on 5/10/15.
 */
public class MandateReport extends ContentPanel {

    public MandateReport(){
        setHeaderVisible(false);
        setBodyBorder(false);
    }

    private VerticalPanel vp;

    final Logger logger = Logger.getLogger("logger");
    @SuppressWarnings("unused")
    private FormData formData;
    DateField fromDate = new DateField();
    DateField toDate = new DateField();
    Map<String, Object> param = new HashMap<String, Object>();
    Frame frame = new Frame();
    SimpleComboBox<String> mandateReceived = new SimpleComboBox<String>();

    @Override
    protected void onRender(Element parent, int index) {
        super.onRender(parent, index);
        formData = new FormData("100%");
        vp = new VerticalPanel();
        vp.setSpacing(10);
        createForm1();
        // add(frame);
        add(vp);

        pdfButton.addListener(Events.OnClick, new Listener<BaseEvent>() {


            @Override
            public void handleEvent(BaseEvent be) {
                param.put("from_date", fromDate.getValue());
                param.put("to_date", toDate.getValue());
                PdfReportViewer pdf = new PdfReportViewer("/resources/Reports/mandate",param,"mandateReport");
                TabPanel tabPanel = Registry.get("tabPanel");
                tabPanel.getSelectedItem().close();
                TabItem item = new TabItem();
                item.setText("Mandate Report Pdf");
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
                System.out.println("on load fired here");
                param.put("from_date", fromDate.getValue());
                param.put("to_date", toDate.getValue());
                param.put("mandate_received_status", mandateReceived.getValueField());

                ExcelReportViewer excel = new ExcelReportViewer("/resources/Reports/mandate",param,"mandateReport");
                TabPanel tabPanel = Registry.get("tabPanel");
                tabPanel.getSelectedItem().close();
                Window.open(excel.getUrl(), "excel file", "");
            }
        });


        cancelButton.addListener(Events.OnClick, new Listener<ButtonEvent>() {

            @Override
            public void handleEvent(ButtonEvent e) {
                fromDate.clear();
                toDate.clear();
                mandateReceived.clear();
                pdfButton.enable();
            }

        });


    }

    private void createForm1() {
        FormPanel simple = new FormPanel();
        simple.setHeading("Mandate Report");
        simple.setFrame(true);
        simple.setWidth(550);
        simple.setBorders(false);

        fromDate.setFieldLabel("From Date");
        toDate.setFieldLabel("To Date");
        mandateReceived.setFieldLabel("Mandate Received");
        simple.add(fromDate, new FormData("10%"));
        simple.add(toDate, new FormData("1%"));

        pdfButton = new Button("PDF");
        simple.addButton(pdfButton);
        excelButton = new Button("Excel");
        simple.addButton(excelButton);
        cancelButton = new Button("Cancel");
        simple.addButton(cancelButton);

        simple.setButtonAlign(Style.HorizontalAlignment.CENTER);



        vp.add(simple);
    }

    Button pdfButton;
    Button cancelButton;
    Button excelButton;

}
