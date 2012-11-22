package org.jboss.tools.gwt.client;

/**
 *
 * @author Shamsuddin Ahammad
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

import org.jboss.tools.gwt.shared.FieldVerifier;
import org.jboss.tools.gwt.shared.User;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.Viewport;
import com.extjs.gxt.ui.client.widget.Window;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.FormButtonBinding;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.layout.BorderLayout;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.extjs.gxt.ui.client.widget.layout.FormData;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.event.shared.GwtEvent.Type;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.RootPanel;
  
public class LogIn extends LayoutContainer {  
  
	
	final Logger logger = Logger.getLogger("logger");
	/**
	 * The message displayed to the user when the server cannot be reached or
	 * returns an error.
	 */
	private static final String SERVER_ERROR = "Please Enter yout credentials Thank you.";
	
	private VerticalPanel vp;  
  
  private FormData formData;  
  
  private final GreetingServiceAsync greetingService = GWT.create(GreetingService.class);
  
  @Override  
  protected void onRender(Element parent, int index) {  
    super.onRender(parent, index);  
    formData = new FormData("-20");  
    vp = new VerticalPanel();  
    vp.setSpacing(10);  
    createForm1(); 
    add(vp);  
  }  
  
  private void createForm1() {
	// TODO Auto-generated method stub

      final FormPanel formPanel = new FormPanel();
      final TextField<String> txtUsername = new TextField<String>();
      final TextField<String> txtPassword = new TextField<String>();
      final Button btnLogin = new Button("Login");

      formPanel.setBodyBorder(false);
      formPanel.setWidth(380);
      formPanel.setLabelWidth(100);
      formPanel.setButtonAlign(HorizontalAlignment.CENTER);
      
      txtUsername.setFieldLabel("Username");
      txtPassword.setFieldLabel("Password");
      txtPassword.setPassword(true);
      
      txtUsername.setAllowBlank(false);
      formPanel.add(txtUsername);

      txtPassword.setPassword(true);
      txtPassword.setAllowBlank(false);
      formPanel.add(txtPassword);

      formPanel.addButton(btnLogin);

      ContentPanel topPanel = new ContentPanel();
      Image image = new Image("resources/images/login.gif", 0, 0, 400, 101);
      topPanel.setLayout(new FitLayout());
      topPanel.add(image);
      

      
      
  	// Create the popup dialog box
		final DialogBox dialogBox = new DialogBox();
		dialogBox.setText("Remote Procedure Call");
		dialogBox.setAnimationEnabled(true);
		final Button closeButton = new Button("Close");
		// We can set the id of a widget by accessing its Element
		closeButton.getElement().setId("closeButton");
		final Label textToServerLabel = new Label();
		final HTML serverResponseLabel = new HTML();
		VerticalPanel dialogVPanel = new VerticalPanel();
		dialogVPanel.addStyleName("dialogVPanel");
		dialogVPanel.add(new HTML("<br><b>Connect2Telos replies:</b><br>"));
		dialogVPanel.add(serverResponseLabel);
		//dialogVPanel.setHorizontalAlignment(VerticalPanel.ALIGN_RIGHT);
		dialogVPanel.add(closeButton);
		dialogBox.setWidget(dialogVPanel);

		closeButton.addListener(Events.OnClick,new Listener<ButtonEvent>(){

			@Override
			public void handleEvent(ButtonEvent be) {
				// TODO Auto-generated method stub
				logger.log(Level.SEVERE,"close button fired !! ");
				dialogBox.hide();
				btnLogin.setEnabled(true);
				//btnLogin.focus();
				txtUsername.focus();
			}
			 
		 });
		
		

      btnLogin.addListener(Events.OnClick,new Listener<ButtonEvent>(){

          @Override
          public void handleEvent(ButtonEvent e)
          {
              
              btnLogin.disable();
              final String username = txtUsername.getValue();
              final String password = txtPassword.getValue();
              ((GreetingServiceAsync)GWT.create(GreetingService.class)).greetServer(username, password, new AsyncCallback<User[]>() {
					public void onFailure(Throwable caught) {
						// Show the RPC error message to the user
						dialogBox.setText("Remote Procedure Call - Failure");
						serverResponseLabel.addStyleName("serverResponseLabelError");
						serverResponseLabel.setHTML(SERVER_ERROR);
						dialogBox.center();
						closeButton.focus();
					}

					public void onSuccess(User[] result) {
						dialogBox.setText("Welcome");
						logger.log(Level.SEVERE,"inside Clent ");
						serverResponseLabel.removeStyleName("serverResponseLabelError");
						try{
						if(result[0].getName().equals(username) ){
							logger.log(Level.SEVERE,"inside if block "+result[0].getName());
						serverResponseLabel.setHTML("<br>"+result[0].getName()+" your logged in !");
						vp.removeAll();
						result[0].setName(null);
						HomePage homePage=new HomePage();
				        RootPanel.get().add(homePage);
						}
						else
						serverResponseLabel.setHTML("<br>Check your credentials..");
						dialogBox.center();
						closeButton.focus();
						}
						catch(Exception ex)
						{
							serverResponseLabel.setHTML("<br>Check your credentials..");
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