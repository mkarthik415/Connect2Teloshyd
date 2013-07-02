package org.jboss.tools.gwt.client;

import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FileUpload;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

public class UploadFileForClient extends ContentPanel{
	
	public UploadFileForClient()
	{
		setHeaderVisible(false);
		setBodyBorder(false);
	}
	
	  @Override  
	  protected void onRender(Element parent, int index) {  
	    
	    super.onRender(parent, index);
	    
	    final FormPanel panel = new FormPanel(); 
	    
	    TextBox name = new TextBox(); 
	    panel.setWidget(name);
	    
	    add(panel);   
	}

}
