package org.jboss.tools.gwt.client;

/*
 * Ext GWT - Ext for GWT
 * Copyright(c) 2007-2009, Ext JS, LLC.
 * licensing@extjs.com
 * 
 * http://extjs.com/license
 */

import java.util.logging.Level;
import java.util.logging.Logger;

import com.extjs.gxt.ui.client.Registry;
import com.extjs.gxt.ui.client.Style.LayoutRegion;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.MenuEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.util.Margins;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.Label;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.TabItem;
import com.extjs.gxt.ui.client.widget.TabPanel;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.SimpleComboValue;
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
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.VerticalPanel;

public class HomePage extends LayoutContainer {

	// ContentPanel mainContentsPanel = new ContentPanel();
	private TabPanel tabPanel;
	LayoutContainer lc;
	private Integer userStatus = null;

	@Override
	protected void onRender(Element parent, int index) {
		super.onRender(parent, index);
		lc = new LayoutContainer();
		lc.setPosition(150, 0);
		lc.setSize(1366, 900);
		userStatus = Registry.get("team");
		// lc.setSize(1250,850);
		createHomePage();
		add(lc);
	}

	public void createHomePage() {

		BorderLayout layout = new BorderLayout();
		lc.setLayout(layout);

		BorderLayoutData menuBarToolBarLayoutData = new BorderLayoutData(
				LayoutRegion.NORTH, 190);
		menuBarToolBarLayoutData.setMargins(new Margins(5));

		BorderLayoutData leftSidebarLayoutData = new BorderLayoutData(
				LayoutRegion.WEST, 150);
		leftSidebarLayoutData.setSplit(true);
		leftSidebarLayoutData.setCollapsible(true);
		leftSidebarLayoutData.setMargins(new Margins(0, 5, 0, 5));

		BorderLayoutData mainContentsLayoutData = new BorderLayoutData(
				LayoutRegion.CENTER);
		mainContentsLayoutData.setMargins(new Margins(0));

		BorderLayoutData rightSidebarLaysoutData = new BorderLayoutData(
				LayoutRegion.EAST, 150);
		rightSidebarLaysoutData.setSplit(true);
		rightSidebarLaysoutData.setCollapsible(true);
		rightSidebarLaysoutData.setMargins(new Margins(0, 5, 0, 5));

		BorderLayoutData footerLayoutData = new BorderLayoutData(
				LayoutRegion.SOUTH, 20);
		footerLayoutData.setMargins(new Margins(5));

		lc.add(getBannerbar(), menuBarToolBarLayoutData);
		lc.add(getLeftSideBar(), leftSidebarLayoutData);

		// mainContentsPanel.setLayout(new FitLayout());

		lc.add(getMainContents(), mainContentsLayoutData);

		lc.add(getFooter(), footerLayoutData);

	}

	public ContentPanel getBannerbar() {
		ContentPanel bannerPanel = new ContentPanel();
		bannerPanel.add(getBanner());
		bannerPanel.add(getMenuBar());
		bannerPanel.setBorders(false);
		bannerPanel.setHeaderVisible(false);
		return bannerPanel;
	}

	public void addTab(String text, ContentPanel contentPanel) {
		logger.log(Level.SEVERE, "Inside tab now !! ");
		TabItem item = new TabItem();
		item.setText(text);
		item.setId(text);
		item.setClosable(true);
		contentPanel.setBodyBorder(false);
		contentPanel.setBorders(false);
		item.add(contentPanel);
		tabPanel.setBorders(false);
		tabPanel.add(item);
		tabPanel.setSelection(item);
	}

	public ContentPanel getBanner() {
		ContentPanel bannerPanel = new ContentPanel();
		bannerPanel.setHeaderVisible(false);
		bannerPanel.add(new Image("resources/images/telos.png"));
		bannerPanel.setHeight(165);
		bannerPanel.setBorders(false);

		return bannerPanel;
	}

	public ContentPanel getLeftSideBar() {
		ContentPanel leftSidebarPanel = new ContentPanel();

		leftSidebarPanel.setHeading("Navigation");
		leftSidebarPanel.setBodyBorder(true);

		leftSidebarPanel.setLayout(new AccordionLayout());

		ContentPanel setupContentPanel = new ContentPanel();
		setupContentPanel.setHeading("Client");
		setupContentPanel.setLayout(new RowLayout());

		Button clientButton = new Button("New Client",
				new SelectionListener<ButtonEvent>() {

					@Override
					public void componentSelected(ButtonEvent ce) {

						NewClientForm newClientForm = new NewClientForm();
						addTab("New Client", newClientForm);
					}
				});

		Button agentButton = new Button("New Agent",
				new SelectionListener<ButtonEvent>() {

					@Override
					public void componentSelected(ButtonEvent ce) {

						NewAgent agent = new NewAgent();
						addTab("New Agent", agent);
					}
				});

		Button insuranceButton = new Button("Add Insurance Company",
				new SelectionListener<ButtonEvent>() {

					@Override
					public void componentSelected(ButtonEvent ce) {

						NewInsurance newInsurance = new NewInsurance();
						addTab("New Agent", newInsurance);
					}
				});

		Button updateClientButton = new Button("Dispatch Client",
				new SelectionListener<ButtonEvent>() {

					@Override
					public void componentSelected(ButtonEvent ce) {

						DispatchForm dispatchForm = new DispatchForm();
						addTab("Dispatch Client", dispatchForm);
					}
				});

		Button findClientButton = new Button("Search Clients",
				new SelectionListener<ButtonEvent>() {

					@Override
					public void componentSelected(ButtonEvent ce) {

						SearchClient searchForm = new SearchClient();
						addTab("Search Client", searchForm);
						searchForm.searchFieldBox.setSimpleValue("Serial no");
						
					}
				});

		Button uploadExcelButton = new Button("Upload Excel",
				new SelectionListener<ButtonEvent>() {

					@Override
					public void componentSelected(ButtonEvent ce) {

						UploadExcel uploadExcel = new UploadExcel();
						uploadExcel.hideFields = true;
						addTab("Upload Documents", uploadExcel);
					}
				});
		
		Button uploadDocumentsButton = new Button("Upload Documents",
				new SelectionListener<ButtonEvent>() {

					@Override
					public void componentSelected(ButtonEvent ce) {

						UploadDocuments uploadDocuments = new UploadDocuments();
						addTab("Upload Documents", uploadDocuments);
					}
				});

		Button iRDAReportHtmlButton = new Button("IRDA Statement",
				new SelectionListener<ButtonEvent>() {

					@Override
					public void componentSelected(ButtonEvent ce) {

						IrdaReport irdaReport = new IrdaReport();
						addTab("IRDA Report", irdaReport);
					}
				});

		Button renewalReportPdfButton = new Button("Renewal Statement",
				new SelectionListener<ButtonEvent>() {

					@Override
					public void componentSelected(ButtonEvent ce) {

						RenewalReport renewalReport = new RenewalReport();
						addTab("Renewal Report", renewalReport);
					}
				});

		Button clientReportPdfButton = new Button("client Statement",
				new SelectionListener<ButtonEvent>() {

					@Override
					public void componentSelected(ButtonEvent ce) {

						ClientReport clientReport = new ClientReport();
						addTab("Client Report", clientReport);
					}
				});

		Button pendingPolicyPdfButton = new Button("Pending Policy",
				new SelectionListener<ButtonEvent>() {

					@Override
					public void componentSelected(ButtonEvent ce) {

						PendingPolicyReport pendingReport = new PendingPolicyReport();
						addTab("Pending Report", pendingReport);
					}
				});

		if (userStatus == 3) {

			setupContentPanel.add(updateClientButton, new RowData(1, -1,
					new Margins(5, 5, 5, 5)));

			setupContentPanel.add(findClientButton, new RowData(1, -1,
					new Margins(5, 5, 5, 5)));
		} else {

			setupContentPanel.add(clientButton, new RowData(1, -1, new Margins(
					5, 5, 5, 5)));

			setupContentPanel.add(updateClientButton, new RowData(1, -1,
					new Margins(5, 5, 5, 5)));

			setupContentPanel.add(findClientButton, new RowData(1, -1,
					new Margins(5, 5, 5, 5)));

			setupContentPanel.add(agentButton, new RowData(1, -1, new Margins(
					5, 5, 5, 5)));
			
			setupContentPanel.add(insuranceButton, new RowData(1, -1,
					new Margins(5, 5, 5, 5)));
			
			setupContentPanel.add(uploadExcelButton, new RowData(1, -1,
					new Margins(5, 5, 5, 5)));

			setupContentPanel.add(uploadDocumentsButton, new RowData(1, -1,
					new Margins(5, 5, 5, 5)));
		}
		leftSidebarPanel.add(setupContentPanel);

		ContentPanel reportsContentPanel = new ContentPanel();
		reportsContentPanel.setHeading("Reports");
		reportsContentPanel.setLayout(new RowLayout());

		if (userStatus == 3 ) {
			reportsContentPanel.add(renewalReportPdfButton, new RowData(1, -1,
					new Margins(5, 5, 5, 5)));
		} 
		else if(userStatus == 2)
		{
			reportsContentPanel.add(renewalReportPdfButton, new RowData(1, -1,
					new Margins(5, 5, 5, 5)));
			reportsContentPanel.add(pendingPolicyPdfButton, new RowData(1, -1,
					new Margins(5, 5, 5, 5)));
		}
		else {

			reportsContentPanel.add(iRDAReportHtmlButton, new RowData(1, -1,
					new Margins(5, 5, 5, 5)));
			reportsContentPanel.add(renewalReportPdfButton, new RowData(1, -1,
					new Margins(5, 5, 5, 5)));
			reportsContentPanel.add(clientReportPdfButton, new RowData(1, -1,
					new Margins(5, 5, 5, 5)));
			reportsContentPanel.add(pendingPolicyPdfButton, new RowData(1, -1,
					new Margins(5, 5, 5, 5)));
		}
		leftSidebarPanel.add(reportsContentPanel);

		return leftSidebarPanel;

	}

	public VerticalPanel getFooter() {

		VerticalPanel footerPanel = new VerticalPanel();
		footerPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		Label label = new Label("Design & Developed by Karthik Marupeddi.");
		footerPanel.add(label);

		return footerPanel;
	}

	public MenuBar getMenuBar() {
		MenuBar menuBar = new MenuBar();

		// Menus

		Menu fileMenu = new Menu();
		Menu reportsMenu = new Menu();
		Menu helpMenu = new Menu();

		// Items for File menu

		MenuItem employeeMenuItem = new MenuItem("New Client");
		fileMenu.add(employeeMenuItem);
		employeeMenuItem.addListener(Events.Select, new Listener<MenuEvent>() {

			@Override
			public void handleEvent(MenuEvent me) {

				NewClientForm newClientForm = new NewClientForm();
				addTab("New Client", newClientForm);

			}

		});

		MenuItem branchMenuItem = new MenuItem("Dispactch Client");
		fileMenu.add(branchMenuItem);
		branchMenuItem.addListener(Events.Select, new Listener<MenuEvent>() {

			@Override
			public void handleEvent(MenuEvent be) {

				DispatchForm dispatchForm = new DispatchForm();
				addTab("Dispatch Client", dispatchForm);

			}

		});

		MenuItem productMenuItem = new MenuItem("Find Client");
		fileMenu.add(productMenuItem);
		productMenuItem.addListener(Events.Select, new Listener<MenuEvent>() {

			@Override
			public void handleEvent(MenuEvent be) {

				SearchClient searchForm = new SearchClient();
				searchForm.setHeaderVisible(false);
				addTab("Search Client", searchForm);

			}

		});

		// Items for Reports menu

		MenuItem irdaStatementMenuItem = new MenuItem("IRDA Statement");
		reportsMenu.add(irdaStatementMenuItem);
		irdaStatementMenuItem.addListener(Events.Select,
				new Listener<MenuEvent>() {

					@Override
					public void handleEvent(MenuEvent be) {

						IrdaReport irdaReport = new IrdaReport();
						addTab("IRDA Report", irdaReport);

					}

				});

		MenuItem renewalStatementMenuItem = new MenuItem("Renewal statement");
		reportsMenu.add(renewalStatementMenuItem);
		renewalStatementMenuItem.addListener(Events.Select,
				new Listener<MenuEvent>() {

					@Override
					public void handleEvent(MenuEvent be) {
						RenewalReport renewalReport = new RenewalReport();
						addTab("Renewal Report", renewalReport);

					}

				});

		MenuItem clientStatementMenuItem = new MenuItem("Client statement");
		reportsMenu.add(clientStatementMenuItem);
		clientStatementMenuItem.addListener(Events.Select,
				new Listener<MenuEvent>() {

					@Override
					public void handleEvent(MenuEvent be) {
						ClientReport clientReport = new ClientReport();
						addTab("Client Report", clientReport);

					}

				});
		// Items for Help menu

		MenuItem aboutMenuItem = new MenuItem("About");
		helpMenu.add(aboutMenuItem);

		MenuBarItem menuBarItemFile = new MenuBarItem("File", fileMenu);
		MenuBarItem menuBarItemReports = new MenuBarItem("Reports", reportsMenu);
		MenuBarItem menuBarItemHelp = new MenuBarItem("Help", helpMenu);

		menuBar.add(menuBarItemFile);
		menuBar.add(menuBarItemReports);
		menuBar.add(menuBarItemHelp);
		menuBar.setBorders(false);
		return menuBar;

	}

	public TabPanel getMainContents() {

		tabPanel = new TabPanel();
		Registry.register("tabPanel", tabPanel);
		tabPanel.setMinTabWidth(115);
		tabPanel.setResizeTabs(true);
		tabPanel.setAnimScroll(true);
		tabPanel.setTabScroll(true);
		tabPanel.setCloseContextMenu(true);
		tabPanel.setBorders(false);
		return tabPanel;

	}

	public ToolBar getToolBar() {
		ToolBar toolBar = new ToolBar();

		toolBar.add(new Button("Stock"));
		toolBar.add(new Button("Sales"));
		toolBar.add(new Button("Purchase"));

		return toolBar;
	}

	final Logger logger = Logger.getLogger("logger");

}
