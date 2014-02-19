package org.jboss.tools.gwt.client;

/**
 *
 * @author karthik marupeddi
 */
/* 
 * Ext GWT 2.2.6 - Ext for GWT 
 * Copyright(c) 2007-2010, Ext JS, LLC. 
 * licensing@extjs.com 
 *  
 * http://extjs.com/license 
 */

import java.util.logging.Level;
import java.util.logging.Logger;

import com.extjs.gxt.ui.client.Registry;
import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.layout.CenterLayout;
import com.extjs.gxt.ui.client.widget.layout.FormData;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.RootPanel;

public class LogIn extends LayoutContainer {

	final Logger logger = Logger.getLogger("logger");
	/**
	 * The message displayed to the user when the server cannot be reached or
	 * returns an error.
	 */
	private static final String SERVER_ERROR = "Please Enter yout credentials Thank you.";

	private final int INITIAL_TIMEOUT_PAD = 60000;
	private final int TIMEOUT_PAD = 15000;
	private Timer sessionTimeoutResponseTimer;
	final DialogBox dialogBox = new DialogBox();
	final Button closeButton = new Button("Close");
	private int timeoutCicle;

	private VerticalPanel vp;

	//private ContentPanel cp;

	private FormData formData;
	
	private CenterLayout cl;

	@Override
	protected void onRender(Element parent, int index) {
		super.onRender(parent, index);
		vp = new VerticalPanel(); // 2.0 container
		vp.setSize(1366, 900);
		vp.setStyleName("x-header-background");
		createForm1();
		vp.setPosition(150, 0);
		setPosition(150, 0);
		vp.setSpacing(700);
		add(vp);
	
	}

	private void initSessionTimers() {
		((GreetingServiceAsync) GWT.create(GreetingService.class))
				.getUserSessionTimeout(new AsyncCallback<Long>() {
					public void onSuccess(Long timeout) {
						timeoutCicle = (int) (long) timeout;
						sessionTimeoutResponseTimer = new Timer() {
							@Override
							public void run() {
								System.out.println("step one complete");
								checkUserSessionAlive();
								System.out.println("step two complete");
							}
						};

						sessionTimeoutResponseTimer
								.schedule((int) (timeout + INITIAL_TIMEOUT_PAD));
					}

					public void onFailure(Throwable caught) {
						displaySessionTimedOut();
					}
				});
	}

	private void checkUserSessionAlive() {
		System.out.println("step three complete");
		((GreetingServiceAsync) GWT.create(GreetingService.class))
				.isSessionStillAlive(new AsyncCallback<Boolean>() {
					public void onSuccess(Boolean result) {
						displaySessionTimedOut();
						System.out
								.println("response received back from server "
										+ result);
						if (result) {

							sessionTimeoutResponseTimer.cancel();
							sessionTimeoutResponseTimer
									.scheduleRepeating(timeoutCicle);
						} else {
							displaySessionTimedOut();
						}
					}

					public void onFailure(Throwable caught) {
						displaySessionTimedOut();
					}

				});

	}

	private void displaySessionTimedOut() {

		dialogBox.setText("Remote Procedure Call");
		dialogBox.setAnimationEnabled(true);
		VerticalPanel dialogVPanel = new VerticalPanel();
		dialogVPanel.addStyleName("dialogVPanel");
		dialogVPanel.add(new HTML("<br><b>Connect2Telos replies:</b><br>"));
		dialogVPanel.add(closeButton);
		dialogBox.setWidget(dialogVPanel);

	}

	private void createForm1() {
		// TODO Auto-generated method stub
		final FormPanel formPanel = new FormPanel();
		final TextField<String> txtUsername = new TextField<String>();
		final TextField<String> txtPassword = new TextField<String>();
		final Button btnLogin = new Button("Login");
		formPanel.setBodyBorder(true);
		formPanel.setWidth(380);
		formPanel.setLabelWidth(100);
		formPanel.setButtonAlign(HorizontalAlignment.CENTER);
		formPanel.setBodyBorder(false);
		formPanel.setHeaderVisible(false);
		formPanel.setBorders(false);

		txtUsername.setFieldLabel("Username");
		txtPassword.setFieldLabel("Password");
		txtPassword.setPassword(true);

		txtUsername.setAllowBlank(false);
		formPanel.add(txtUsername);

		txtPassword.setPassword(true);
		txtPassword.setAllowBlank(false);
		formPanel.add(txtPassword);

		formPanel.addButton(btnLogin);

		// Create the popup dialog box
		dialogBox.setText("Remote Procedure Call");
		dialogBox.setAnimationEnabled(true);
		// We can set the id of a widget by accessing its Element
		closeButton.getElement().setId("closeButton");
		final HTML serverResponseLabel = new HTML();
		VerticalPanel dialogVPanel = new VerticalPanel();
		dialogVPanel.addStyleName("dialogVPanel");
		dialogVPanel.add(new HTML("<br><b>Connect2Telos replies:</b><br>"));
		dialogVPanel.add(serverResponseLabel);
		// dialogVPanel.setHorizontalAlignment(VerticalPanel.ALIGN_RIGHT);
		dialogVPanel.add(closeButton);
		dialogBox.setWidget(dialogVPanel);

		closeButton.addListener(Events.OnClick, new Listener<ButtonEvent>() {

			@Override
			public void handleEvent(ButtonEvent be) {
				// TODO Auto-generated method stub
				logger.log(Level.SEVERE, "close button fired !! ");
				dialogBox.hide();
				btnLogin.setEnabled(true);
				// btnLogin.focus();
				txtUsername.focus();
			}

		});

		btnLogin.addListener(Events.OnClick, new Listener<ButtonEvent>() {

			@Override
			public void handleEvent(ButtonEvent e) {
				logger.log(Level.SEVERE, "submit button clicked !! ");
				btnLogin.disable();
				final String username = txtUsername.getValue();
				final String password = txtPassword.getValue();
				((GreetingServiceAsync) GWT.create(GreetingService.class))
						.greetServer(username, password,
								new AsyncCallback<Integer>() {
									public void onFailure(Throwable caught) {
										// Show the RPC error message to the
										// user
										dialogBox
												.setText("Remote Procedure Call - Failure");
										serverResponseLabel
												.addStyleName("serverResponseLabelError");
										serverResponseLabel
												.setHTML(SERVER_ERROR+caught.getStackTrace());
										dialogBox.center();
										closeButton.focus();

									}

									public void onSuccess(Integer result) {
										dialogBox.setText("Welcome");
										logger.log(Level.SEVERE,
												"inside Clent ");
										serverResponseLabel
												.removeStyleName("serverResponseLabelError");
										try {
											if (result != null) {
												logger.log(Level.SEVERE,
														"inside if block "
																+ result);
												serverResponseLabel
														.setHTML("<br>"
																+ " your logged in !");
												vp.removeAll();
												remove(vp);
												Registry.register("team",
														result);
												Registry.register("user",
														username);
												HomePage homePage = new HomePage();
												RootPanel.get().add(homePage);
												initSessionTimers();
												// add(homePage);
											} else
												serverResponseLabel
														.setHTML("<br>Check your credentials......");
											logger.log(Level.SEVERE,
													"User not in my account ");
											// vp.removeAll();
											dialogBox.center();
											closeButton.focus();
										} catch (Exception ex) {
											serverResponseLabel
													.setHTML("<br>Check your credentials.."
															+ ex.toString());
											logger.log(
													Level.INFO,
													"Exception here  "
															+ ex.toString());
											// vp.removeAll();
											dialogBox.center();
											closeButton.focus();
										}
									}
								});
			}

		});
		vp.add(formPanel);

	}

}