package org.jboss.tools.gwt.client;

import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.TextBox;

public class UploadDocuments extends ContentPanel{
	
	public UploadDocuments()
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