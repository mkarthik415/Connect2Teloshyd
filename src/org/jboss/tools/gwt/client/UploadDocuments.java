package org.jboss.tools.gwt.client;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.Info;
import com.extjs.gxt.ui.client.widget.MessageBox;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.FileUploadField;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.FormPanel.Encoding;
import com.extjs.gxt.ui.client.widget.form.FormPanel.Method;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.FormHandler;
import com.google.gwt.user.client.ui.FormSubmitCompleteEvent;
import com.google.gwt.user.client.ui.FormSubmitEvent;

public class UploadDocuments extends ContentPanel implements FormHandler{  
	
	public UploadDocuments(){
		setHeaderVisible(false);
		setBodyBorder(false);
	}
	  
	  @Override  
	  protected void onRender(Element parent, int index) {  
	    super.onRender(parent, index);
	  
	    final FormPanel panel = new FormPanel();  
	    panel.setHeading("Documents Upload");  
	    panel.setFrame(true);  
	    panel.setAction("mydocuments");  
	    panel.setEncoding(Encoding.MULTIPART);  
	    panel.setMethod(Method.POST);  
	    panel.setButtonAlign(HorizontalAlignment.CENTER);  
	    panel.setWidth(350);  
	  
	    
	    TextField<String> name = new TextField<String>();  
	    name.setFieldLabel("Name");  
	    panel.add(name);  
	  
	    FileUploadField file = new FileUploadField();  
	    file.setAllowBlank(false);  
	    file.setName("uploadedfile");  
	    file.setFieldLabel("File");  
	    panel.add(file);   
	  
	    Button btn = new Button("Reset");  
	    btn.addSelectionListener(new SelectionListener<ButtonEvent>() {  
	      @Override  
	      public void componentSelected(ButtonEvent ce) {  
	        panel.reset();  
	      }  
	    });  
	    panel.addButton(btn);  
	  
	    btn = new Button("Submit");  
	    btn.addSelectionListener(new SelectionListener<ButtonEvent>() {  
	      @Override  
	      public void componentSelected(ButtonEvent ce) {  
	        if (!panel.isValid()) {  
	          return;  
	        }  
	        // normally would submit the form but for example no server set up to  
	        // handle the post
	         panel.submit();  
	        //MessageBox.info("Action", "You file was uploaded", null);  
	         final MessageBox box = MessageBox.wait("Progress",  
	                 "Saving your data, please wait...", "Saving...");
	         Timer t = new Timer() {

				@Override
				public void run() {
					Info.display("Message", "Please click refresh button in the update client tab, for the document.", "");  
		            box.close();  
					
				}  
	        	 
	         };
	         t.schedule(10000);  
	      }
	    });  
	   panel.addButton(btn);  
	   
	    add(panel);  
	  }

	@Override
	@Deprecated
	public
	void onSubmit(FormSubmitEvent event) {
		// TODO Auto-generated method stub
		MessageBox.info("Action", "You file was uploaded", null);  
	}

	@Override
	@Deprecated
	public
	void onSubmitComplete(FormSubmitCompleteEvent event) {
		// TODO Auto-generated method stub
		
		Info.display("Message", "After fake data was saved", "");
		
	}  
	  
	}  