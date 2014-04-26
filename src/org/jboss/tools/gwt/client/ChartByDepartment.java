package org.jboss.tools.gwt.client;

import com.extjs.gxt.charts.client.Chart;
import com.extjs.gxt.charts.client.model.ChartModel;
import com.extjs.gxt.charts.client.model.Legend;
import com.extjs.gxt.charts.client.model.Legend.Position;
import com.extjs.gxt.charts.client.model.charts.PieChart;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.layout.FormData;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Element;
//import com.extjs.gxt.samples.client.Examples;  
import com.google.gwt.user.client.rpc.AsyncCallback;

public class ChartByDepartment extends ContentPanel {

	private VerticalPanel vp;
	private FormData formData;
	private String filePath = null;

	@Override
	protected void onRender(Element parent, int index) {
		super.onRender(parent, index);
		vp = new VerticalPanel();
		vp.setSpacing(10);
		findPath("/resources/chart/open-flash-chart.swf");
		createForm();
		add(vp);
	}

	private void findPath(String fileName)
	{
		((GreetingServiceAsync) GWT.create(GreetingService.class)).getFilePath(fileName, new AsyncCallback<String>(){

			@Override
			public void onFailure(Throwable caught) {
				filePath = null;
				
			}

			@Override
			public void onSuccess(String result) {
				filePath =  result;
				
			}
			
		});
		
	}
	private void createForm() {
		formData = new FormData("100%");
		FormPanel simple = new FormPanel();
		simple.setHeading("Simple Form");
		simple.setFrame(true);
		simple.setSize(500, 500);
		 String url = "/open-flash-chart.swf";  
	      
		    final Chart chart = new Chart(url);  
		    chart.setBorders(true);  
		    chart.setChartModel(getPieChartData()); 
		    simple.add(chart);
		vp.add(simple);
	}
	
	private ChartModel getPieChartData() {  
	    ChartModel cm = new ChartModel("Sales by Region",  
	        "font-size: 14px; font-family: Verdana; text-align: center;");  
	    cm.setBackgroundColour("#fffff5");  
	    Legend lg = new Legend(Position.RIGHT, true);  
	    lg.setPadding(10);  
	    cm.setLegend(lg);  
	      
	    PieChart pie = new PieChart();  
	    pie.setAlpha(0.5f);  
	    pie.setNoLabels(true);  
	    pie.setTooltip("#label# $#val#M<br>#percent#");  
	    pie.setColours("#ff0000", "#00aa00", "#0000ff", "#ff9900", "#ff00ff");  
	    pie.addSlices(new PieChart.Slice(100, "AU","Australia"));  
	    pie.addSlices(new PieChart.Slice(200, "US", "USA"));  
	    pie.addSlices(new PieChart.Slice(150, "JP", "Japan"));  
	    pie.addSlices(new PieChart.Slice(120, "DE", "Germany"));  
	    pie.addSlices(new PieChart.Slice(60, "UK", "United Kingdom"));  
	  
	    cm.addChartConfig(pie);  
	    return cm;  
	  }  
}