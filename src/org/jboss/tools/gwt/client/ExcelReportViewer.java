package org.jboss.tools.gwt.client;

import java.util.Map;

import com.extjs.gxt.ui.client.Registry;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.widget.MessageBox;
import com.extjs.gxt.ui.client.widget.ProgressBar;
import com.extjs.gxt.ui.client.widget.TabPanel;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Frame;

class ExcelReportViewer extends FormPanel {

	public ExcelReportViewer(String fileName, Map<String, Object> param,
			String title) {

		setLayout(new FitLayout());
		setHeading(title);

		final Frame frame = new Frame();

		add(frame);

		((GreetingServiceAsync) GWT.create(GreetingService.class)).getExcelForPendingPolicy(
				fileName, param, new AsyncCallback<String>() {
					public void onFailure(Throwable caught) {
						// Show the RPC error message to the user
						MessageBox messageBox = new MessageBox();
						messageBox.setMessage("Cannot load the report !!");
						messageBox.show();
					}

					@Override
					public void onSuccess(String arg0) {
						MessageBox messageBox = new MessageBox();
						if (arg0 != null) {

							url = arg0;
							Window.open(arg0, "excel file", "");

						} else {
							messageBox.setMessage("Cannot load the report");
							messageBox.show();

						}
					}

				});

	}

	public String getUrl() {
		return url;
	}

	private String url = null;
}
