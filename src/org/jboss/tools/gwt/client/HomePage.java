package org.jboss.tools.gwt.client;

 




/*
* Ext GWT - Ext for GWT
* Copyright(c) 2007-2009, Ext JS, LLC.
* licensing@extjs.com
* 
* http://extjs.com/license
*/




import com.extjs.gxt.ui.client.Registry;
import com.extjs.gxt.ui.client.Style.LayoutRegion;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.MenuEvent;
import com.extjs.gxt.ui.client.event.MessageBoxEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.util.Margins;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.Label;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.MessageBox;
import com.extjs.gxt.ui.client.widget.TabItem;
import com.extjs.gxt.ui.client.widget.TabPanel;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.layout.AccordionLayout;
import com.extjs.gxt.ui.client.widget.layout.BorderLayout;
import com.extjs.gxt.ui.client.widget.layout.BorderLayoutData;
import com.extjs.gxt.ui.client.widget.layout.RowData;
import com.extjs.gxt.ui.client.widget.layout.RowLayout;
import com.extjs.gxt.ui.client.widget.menu.Menu;
import com.extjs.gxt.ui.client.widget.menu.MenuBar;
import com.extjs.gxt.ui.client.widget.menu.MenuBarItem;
import com.extjs.gxt.ui.client.widget.menu.MenuItem;
import com.extjs.gxt.ui.client.widget.toolbar.ToolBar;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.VerticalPanel;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jboss.tools.gwt.shared.Agent;

public class HomePage  extends ContentPanel
{
    
    
    private TabPanel tabPanel;


    public HomePage()
    {
    	tabPanel = new TabPanel();
    	Registry.register("tabPanel", tabPanel);
    	 logger = Logger.getLogger("logger");
    	logger.log(Level.SEVERE,"Inside homepage !! ");
        setSize(1366,900);
        //setStyleAttribute("padding", "10px"); 
        setHeaderVisible(false);
  
        BorderLayout layout = new BorderLayout();
        setLayout(layout);
        
        BorderLayoutData menuBarToolBarLayoutData = new BorderLayoutData(LayoutRegion.NORTH, 55);
        menuBarToolBarLayoutData.setMargins(new Margins(5));

        BorderLayoutData leftSidebarLayoutData = new BorderLayoutData(LayoutRegion.WEST, 150);
        leftSidebarLayoutData.setSplit(true);
        leftSidebarLayoutData.setCollapsible(true);
        leftSidebarLayoutData.setMargins(new Margins(0, 5, 0, 5));

        BorderLayoutData mainContentsLayoutData = new BorderLayoutData(LayoutRegion.CENTER);
        mainContentsLayoutData.setMargins(new Margins(0, 0, 0, 0));

        BorderLayoutData rightSidebarLayoutData = new BorderLayoutData(LayoutRegion.EAST, 150);
        rightSidebarLayoutData.setSplit(true);
        rightSidebarLayoutData.setCollapsible(true);
        rightSidebarLayoutData.setMargins(new Margins(0, 5, 0, 5));

        BorderLayoutData footerLayoutData = new BorderLayoutData(LayoutRegion.SOUTH, 20);
        footerLayoutData.setMargins(new Margins(5));

        
        setTopComponent(getBanner());

        VerticalPanel menuAndToolBarPanel=new VerticalPanel();
        menuAndToolBarPanel.add(getMenuBar());
       // menuAndToolBarPanel.add(getToolBar());

        add(menuAndToolBarPanel, menuBarToolBarLayoutData);
        add(getLeftSidebar(), leftSidebarLayoutData);

                
        tabPanel.setMinTabWidth(115);
        tabPanel.setResizeTabs(true);
        tabPanel.setAnimScroll(true);
        tabPanel.setTabScroll(true);
        tabPanel.setCloseContextMenu(true);

        add(tabPanel, mainContentsLayoutData);
        
        add(getRightSidebar(), rightSidebarLayoutData);
        add(getFooter(), footerLayoutData);

        
        
    }

    public void addTab(String text, LayoutContainer contentPanel)
    {
    	logger.log(Level.SEVERE,"Inside tab now !! ");
    	TabItem item = new TabItem();
        item.setText(text);
        item.setId(text);
        item.setClosable(true);
        item.add(contentPanel);
        tabPanel.add(item);
        tabPanel.setSelection(item);
    }

    public ContentPanel getBanner()
    {
        ContentPanel bannerPanel = new ContentPanel();
        bannerPanel.setHeaderVisible(false);
        bannerPanel.add(new Image("resources/images/banner.png"));
        
        return bannerPanel;
    }

    public ContentPanel getLeftSidebar()
    {
        ContentPanel leftSidebarPanel = new ContentPanel();

        leftSidebarPanel.setHeading("Navigation");
        leftSidebarPanel.setBodyBorder(true);

        leftSidebarPanel.setLayout(new AccordionLayout());
        

        ContentPanel setupContentPanel=new ContentPanel();
        setupContentPanel.setHeading("Client");
        setupContentPanel.setLayout(new RowLayout());

        Button clientButton=new Button("New Client", new SelectionListener<ButtonEvent>() {

            @Override
            public void componentSelected(ButtonEvent ce)
            {
            	NewClientForm newClientForm = new NewClientForm();
                addTab("New Client",newClientForm);
            }
        });
        
        Button agentButton=new Button("New Agent", new SelectionListener<ButtonEvent>() {

            @Override
            public void componentSelected(ButtonEvent ce)
            {
            	NewAgent agent = new NewAgent();
                addTab("New Agent",agent);
            }
        });
        
        Button updateClientButton=new Button("Dispatch Client",new SelectionListener<ButtonEvent>() {

            @Override
            public void componentSelected(ButtonEvent ce)
            {

            	DispatchForm dispatchForm = new DispatchForm();
                addTab("Dispatch Client",dispatchForm);
            }
        });
        
        Button findClientButton=new Button("Search Clients", new SelectionListener<ButtonEvent>() {

            @Override
            public void componentSelected(ButtonEvent ce)
            {

            	SearchForm searchForm = new SearchForm();
                addTab("Search Client",searchForm);
            }
        });


        setupContentPanel.add(clientButton,new RowData(1,-1,new Margins(5,5,5,5)));
        setupContentPanel.add(updateClientButton,new RowData(1,-1,new Margins(5,5,5,5)));
        setupContentPanel.add(findClientButton,new RowData(1,-1,new Margins(5,5,5,5)));
        setupContentPanel.add(agentButton,new RowData(1,-1,new Margins(5,5,5,5)));
        
        
        leftSidebarPanel.add(setupContentPanel);


        ContentPanel reportsContentPanel=new ContentPanel();
        reportsContentPanel.setHeading("Reports");
        reportsContentPanel.setLayout(new RowLayout());

        Button salesDetailHtmlButton=new Button("Sales Detail(HTML)");
        Button salesDetailPdfButton=new Button("Sales Detail(PDF)",  new SelectionListener<ButtonEvent>() {

            @Override
            public void componentSelected(ButtonEvent ce)
            {
                MessageBox inputBox = MessageBox.prompt("Input", "Enter the Sales No");
                inputBox.addCallback(new Listener<MessageBoxEvent>()
                {

                    public void handleEvent(MessageBoxEvent be)
                    {
                        int salesNo = Integer.parseInt(be.getValue());
                        HashMap<String, Integer> param = new HashMap<String, Integer>();
                        param.put("salesNo", salesNo);
                        //PdfReportViewer reportViewer = new PdfReportViewer("reports/Sales", param, "Sales Invoice");
                       // reportViewer.setHeight(480);
                       // addTab("Sales Details Report",reportViewer);

                    }
                });

                
                
            }
        });


        Button purchaseDetailButton=new Button("Purchase Detail");
        Button salesSummaryButton=new Button("Sales Summary");

        reportsContentPanel.add(salesDetailHtmlButton,new RowData(1,-1,new Margins(5,5,5,5)));
        reportsContentPanel.add(salesDetailPdfButton,new RowData(1,-1,new Margins(5,5,5,5)));
        reportsContentPanel.add(purchaseDetailButton,new RowData(1,-1,new Margins(5,5,5,5)));
        reportsContentPanel.add(salesSummaryButton,new RowData(1,-1,new Margins(5,5,5,5)));

        leftSidebarPanel.add(reportsContentPanel);

        return leftSidebarPanel;

    }


    public ContentPanel getRightSidebar()
    {
        ContentPanel rightSidebarPanel = new ContentPanel();
        rightSidebarPanel.setHeading("Right Sidebar");
        return rightSidebarPanel;
    }

    public VerticalPanel getFooter()
    {

        VerticalPanel footerPanel = new VerticalPanel();
        footerPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
        Label label = new Label("Developed by Karthik Marupeddi.");
        footerPanel.add(label);

        return footerPanel;
    }

    
    public MenuBar getMenuBar()
    {
       MenuBar menuBar=new MenuBar();

        //Menus

        Menu fileMenu=new Menu();
        Menu reportsMenu=new Menu();
        Menu helpMenu=new Menu();

        //Items for File menu


        MenuItem employeeMenuItem=new MenuItem("New Client");
        fileMenu.add(employeeMenuItem);
        employeeMenuItem.addListener(Events.Select,new Listener<MenuEvent>()
        {

            @Override
            public void handleEvent(MenuEvent me)
            {

            	NewClientForm newClientForm = new NewClientForm();
                addTab("New Client",newClientForm);

            }

        });

        MenuItem branchMenuItem=new MenuItem("Dispactch Client");
        fileMenu.add(branchMenuItem);
        branchMenuItem.addListener(Events.Select,new Listener<MenuEvent>()
        {

            @Override
            public void handleEvent(MenuEvent be)
            {
               
            	DispatchForm dispatchForm = new DispatchForm();
                addTab("Dispatch Client",dispatchForm);


            }

        });

        MenuItem productMenuItem=new MenuItem("Find Client");
        fileMenu.add(productMenuItem);
        productMenuItem.addListener(Events.Select,new Listener<MenuEvent>()
        {

            @Override
            public void handleEvent(MenuEvent be)
            {
               
            	SearchForm searchForm = new SearchForm();
                addTab("Search Client",searchForm);


            }

        });


        //Items for Reports menu
        
        MenuItem productListMenuItem=new MenuItem("Client List");
        reportsMenu.add(productListMenuItem);


        MenuItem salesDetailMenuItem=new MenuItem("Policy Detail");
        reportsMenu.add(salesDetailMenuItem);
        salesDetailMenuItem.addListener(Events.Select,new Listener<MenuEvent>()
        {

            @Override
            public void handleEvent(MenuEvent be)
            {
                MessageBox inputBox = MessageBox.prompt("Input", "Enter the Policy No");
                inputBox.addCallback(new Listener<MessageBoxEvent>()
                {

                    public void handleEvent(MessageBoxEvent be)
                    {
                        int salesNo = Integer.parseInt(be.getValue());
                        HashMap<String, Integer> param = new HashMap<String, Integer>();
                        param.put("salesNo", salesNo);
                       // PdfReportViewer reportViewer = new PdfReportViewer("reports/Sales", param, "Sales Invoice");
//                        mainContentsPanel.setHeading("Reports");
  //                      mainContentsPanel.add(reportViewer);
    //                    mainContentsPanel.layout();


                    }
                });
                


            }

        });
        //Items for Help menu

        MenuItem aboutMenuItem=new MenuItem("About");
        helpMenu.add(aboutMenuItem);

        MenuBarItem menuBarItemFile=new MenuBarItem("File",fileMenu);
        MenuBarItem menuBarItemReports=new MenuBarItem("Reports",reportsMenu);
        MenuBarItem menuBarItemHelp=new MenuBarItem("Help",helpMenu);

        menuBar.add(menuBarItemFile);
        menuBar.add(menuBarItemReports);
        menuBar.add(menuBarItemHelp);

        return menuBar;

    }


    public ToolBar getToolBar()
    {
        ToolBar toolBar=new ToolBar();

        toolBar.add(new Button("Stock"));
        toolBar.add(new Button("Sales"));
        toolBar.add(new Button("Purchase"));

        return toolBar;
    }
    final Logger logger;
}
