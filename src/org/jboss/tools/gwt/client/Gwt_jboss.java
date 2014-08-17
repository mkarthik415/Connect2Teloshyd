package org.jboss.tools.gwt.client;

import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.jboss.tools.gwt.shared.FieldVerifier;
import org.jboss.tools.gwt.shared.User;

import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.MessageBoxEvent;
import com.extjs.gxt.ui.client.widget.MessageBox;
import com.extjs.gxt.ui.client.widget.Window;
import com.extjs.gxt.ui.client.widget.layout.CenterLayout;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.Event.NativePreviewEvent;
import com.google.gwt.user.client.Event.NativePreviewHandler;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Gwt_jboss implements EntryPoint {
	/**
	 * The message displayed to the user when the server cannot be reached or
	 * returns an error.
	 */
	private static final String SERVER_ERROR = "An error occurred while "
			+ "attempting to contact the server. Please check your network "
			+ "connection and try again.";
	
    private Timer sessionTimeoutResponseTimer;
    
    private GreetingServiceAsync service;

    
    Timer mInactivityTimer;
    HandlerRegistration mHandlerRegistration;
    int mTimeoutInMillis = 5000;
    

	/**
	 * Create a remote service proxy to talk to the server-side Greeting service.
	 */
	//private final GreetingServiceAsync greetingService = GWT.create(GreetingService.class);

	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		initSessionTimers();
		RootPanel.get().add(new LogIn());
		
	}
	
	private void initSessionTimers()
    {
//        service = (GreetingServiceAsync) GWT.create(GreetingService.class);
//        
//        ((ServiceDefTarget) service)
//            .setServiceEntryPoint(GWT.getModuleBaseURL() + "/quote.rpc");
		
	       mInactivityTimer = new Timer() {
	            @Override
	            public void run() {
	                // Logout
	                //Window.alert("Logout");
	                mHandlerRegistration.removeHandler();
	                mInactivityTimer.cancel();
	            }
	        };
	        mInactivityTimer.schedule(mTimeoutInMillis);
        
	        NativePreviewHandler handler;
	        handler = new NativePreviewHandler() {
	        	
				@Override
				public void onPreviewNativeEvent(NativePreviewEvent event) {
					mInactivityTimer.schedule(mTimeoutInMillis);
	                System.out.println("Event fired: " + new Date());
					
				}
	        };
	        mHandlerRegistration = Event.addNativePreviewHandler(handler);
	    }
	}

