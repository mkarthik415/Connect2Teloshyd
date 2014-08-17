package org.jboss.tools.gwt.client;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.jboss.tools.gwt.shared.Client;
import org.jboss.tools.gwt.shared.EmailedFile;
import org.jboss.tools.gwt.shared.File;

import com.extjs.gxt.ui.client.Registry;
import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.Style.SelectionMode;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.util.IconHelper;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.Html;
import com.extjs.gxt.ui.client.widget.MessageBox;
import com.extjs.gxt.ui.client.widget.TabPanel;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.WidgetComponent;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.HtmlEditor;
import com.extjs.gxt.ui.client.widget.form.TextArea;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.grid.CheckBoxSelectionModel;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnData;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.extjs.gxt.ui.client.widget.grid.Grid;
import com.extjs.gxt.ui.client.widget.grid.GridCellRenderer;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.extjs.gxt.ui.client.widget.layout.FormData;
import com.extjs.gxt.ui.client.widget.tips.QuickTip;
import com.extjs.gxt.ui.client.widget.tips.ToolTipConfig;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.LoadEvent;
import com.google.gwt.event.dom.client.LoadHandler;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Image;

public class DispatchForm extends ContentPanel {

	public DispatchForm() {
		setHeaderVisible(false);
		setBodyBorder(false);
	}

	private VerticalPanel vp;

	@Override
	protected void onRender(Element parent, int index) {
		super.onRender(parent, index);
		vp = new VerticalPanel();
		vp.setSpacing(10);

		formData = new FormData("100%");
		simple.setHeading("Email Form");
		simple.setFrame(true);
		simple.setWidth(710);
		simple.setHeight(625);

		sno.setFieldLabel("Name");
		sno.setAllowBlank(false);
		sno.setEmptyText("SNo");
		simple.add(sno, new FormData("35%"));

		name.setFieldLabel("Name");
		name.setAllowBlank(false);
		name.setEmptyText("Enter clients full name");
		name.getFocusSupport().setPreviousId(simple.getButtonBar().getId());
		simple.add(name, new FormData("50%"));

		// mobile filed
		mobileField.setFieldLabel("Phone Number");
		simple.add(mobileField, new FormData("50%"));
		mobileField.setEmptyText("919848334455");

		// email field
		emailField.setFieldLabel("Email");
		emailField.setRegex(".+@.+\\.[a-z]+");
		emailField.getMessages().setRegexText("Bad email address!!");
		emailField.setAutoValidate(true);
		simple.add(emailField, new FormData("50%"));
		emailField.setEmptyText("example@example.com");

		simple.add(html);

		// Add grid of documents uploaded.
		// create cloumn list
		List<ColumnConfig> configs = new ArrayList<ColumnConfig>();

		checkBox = new CheckBoxSelectionModel<File>();
		checkBox.setSelectionMode(SelectionMode.MULTI);
		configs.add(checkBox.getColumn());

		// adding columns here
		ColumnConfig column = new ColumnConfig();
		column.setId("name");
		column.setHeader("Document Name");
		column.setWidth(200);
		configs.add(column);

		column = new ColumnConfig();
		column.setId("description");
		column.setHeader("Description");
		column.setWidth(200);
		configs.add(column);

		column = new ColumnConfig();
		column.setId("sent");
		column.setHeader("Sent");

		sent = new GridCellRenderer<File>() {


			@Override
			public Object render(File model, String property,
					ColumnData config, int rowIndex, int colIndex,
					ListStore<File> store, Grid<File> grid) {
				
				toolTip = new ToolTipConfig();
				toolTip.setTitle("Email Records");
				toolTip.setCloseable(false);
				toolTip.setMaxWidth(1000);
				String finalTableValues ="" ;
				if(!model.getEmails().isEmpty())
				{
			
					String tableHeader = "<HTML><body><table border-collapse='collapse', border='1px solid black', bgcolor='#F5F5F5', width='100%'><tr border='1px solid black'><th border='1px solid black'>Sent By</th><th border='1px solid black'>Sent To</th><th border='1px solid black'>Sent On</th></tr>";
					for(EmailedFile email:model.getEmails())
					{
						String tableValues = "<tr border='1px solid black'><td border='1px solid black'>"+email.getUser()+"</td><td border='1px solid black'>"+email.getEmailAddress()+"</td><td border='1px solid black'>"+email.getUpdatedOn()+"</td></tr>";
								 finalTableValues += tableValues;
					}
						 tableHeader += finalTableValues;
						String endTable = "</table></body></HTML>";
					
					toolTip.setText(tableHeader.concat(endTable));
				}
				else
				{
					toolTip.setText("Document Never Mailed");
				}
				String imgSource;
				String val = model.get(property).toString();
				String zero = "0";
				if (val != null && val.compareTo(zero) > 0) {
					imgSource = "resources/images/complete_icon_small.png";
				} else
					imgSource = "resources/images/incomplete_icon_small.png";				
				helpIcon = IconHelper.createPath(imgSource).createImage();
				
				imgWidgetComponent = new WidgetComponent(helpIcon);
				imgWidgetComponent.setToolTip(toolTip);
				return imgWidgetComponent;
			}

		};

		column.setRenderer(sent);
		column.setWidth(100);
		configs.add(column);
		
		column = new ColumnConfig();
		column.setId("uploaded_by");
		column.setHeader("Uploaded By");
		column.setWidth(100);
		configs.add(column);
		
		column = new ColumnConfig();
		column.setId("updated_on");
		column.setHeader("Uploaded On");
		column.setWidth(100);
		configs.add(column);

		cm = new ColumnModel(configs);

		grid = new Grid<File>(documentsList, cm);
		grid.setStyleAttribute("borderTop", "none");
		grid.setAutoExpandColumn("name");
		grid.setBorders(true);
		grid.setStripeRows(true);
		new QuickTip(grid);

		documentsCP = new ContentPanel();
		documentsCP.setBodyBorder(false);
		documentsCP.setHeading("Documents List");
		documentsCP.setButtonAlign(HorizontalAlignment.CENTER);
		documentsCP.setLayout(new FitLayout());
		documentsCP.setSize(700, 200);
		documentsCP.add(grid);
		simple.add(documentsCP);

		simple.add(htmlForBreak);

		emailNotes.setFieldLabel("Email Message");
		emailNotes.setHeight(200);
		emailNotes
				.setValue("Hello Sir/Madam,<div><br></div><div>You documents have been dispatched. Thank you for doing business with us.<div><br></div><div>Thank you.</div></div><div><br></div><div>With Regards,</div><div>Telos.</div>");
		simple.add(emailNotes, formData);

		eMailButton = new Button("E-Mail");
		simple.addButton(eMailButton);

		simple.setButtonAlign(HorizontalAlignment.CENTER);

		vp.add(simple);

		add(vp);

		eMailButton.addListener(Events.OnClick, new Listener<ButtonEvent>() {

			@Override
			public void handleEvent(ButtonEvent e) {

				if (emailField.getValue() == null) {
					MessageBox messageBox = new MessageBox();
					messageBox.setMessage("please enter Email ID !!");
					messageBox.show();
					return;
				}
				files = grid.getSelectionModel().getSelectedItems();
				eMailButton.disable();
				c = new Client();
				c.setId(sno.getValue());
				c.setClientName(name.getValue());
				c.setEmail(emailField.getValue());
				c.setPhoneNumber(mobileField.getValue());
				c.setSmsLane(noteField.getValue());
				c.setNote(emailNotes.getValue());
				((GreetingServiceAsync) GWT.create(GreetingService.class))
						.sendEmail(c, files, new AsyncCallback<Boolean>() {
							public void onFailure(Throwable caught) {
								// Show the RPC error message to the user
								MessageBox messageBox = new MessageBox();
								messageBox.setMessage("mail not send !!");
								messageBox.show();
								eMailButton.enable();
							}

							@Override
							public void onSuccess(Boolean arg0) {
								// TODO Auto-generated method stub
								MessageBox messageBox = new MessageBox();
								messageBox.setMessage("mail send !!");
								messageBox.show();
								eMailButton.enable();
								TabPanel tabPanel = Registry.get("tabPanel");
								tabPanel.getSelectedItem().close();

							}
						});
			}

		});

	}

	Button eMailButton;
	Client c;
	List<File> files;
	final Logger logger = Logger.getLogger("logger");
	private FormData formData;
	TextField<String> sno = new TextField<String>();
	TextField<String> name = new TextField<String>();
	TextField<String> mobileField = new TextField<String>();
	TextField<String> emailField = new TextField<String>();
	Html html = new Html("<br></br>");
	Html htmlForBreak = new Html("<br></br>");
	TextArea noteField = new TextArea();
	HtmlEditor emailNotes = new HtmlEditor();
	private ListStore<File> documentsList = new ListStore<File>();
	Grid<File> grid;
	ContentPanel documentsCP ;
	ColumnModel cm;
	CheckBoxSelectionModel<File> checkBox;
	private ToolTipConfig toolTip;
	private WidgetComponent imgWidgetComponent;
	public String email;
	private Image helpIcon;
	GridCellRenderer<File> sent;
	FormPanel simple = new FormPanel();
	private File file;

}
