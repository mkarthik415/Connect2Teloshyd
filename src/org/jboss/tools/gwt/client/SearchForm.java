package org.jboss.tools.gwt.client;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.layout.FormData;
import com.google.gwt.user.client.Element;

public class SearchForm extends LayoutContainer {
	private VerticalPanel vp;  
	  
	  private FormData formData;  
	  
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
	    FormPanel simple = new FormPanel();  
	    simple.setHeading("Simple Form");  
	    simple.setFrame(true);  
	    simple.setWidth(350);  
	  
	    TextField<String> firstName = new TextField<String>();  
	    firstName.setFieldLabel("Name");  
	    firstName.setAllowBlank(false);  
	    firstName.getFocusSupport().setPreviousId(simple.getButtonBar().getId());  
	    simple.add(firstName, formData);  
	  
	    Button b = new Button("Submit");  
	    simple.addButton(b);  
	    simple.addButton(new Button("Cancel"));  
	  
	    simple.setButtonAlign(HorizontalAlignment.CENTER);  
	  
	    /*FormButtonBinding binding = new FormButtonBinding(simple);  
	    binding.addButton(b); */ 
	  
	    vp.add(simple);  
	  }  

}
