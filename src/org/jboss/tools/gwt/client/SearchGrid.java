package org.jboss.tools.gwt.client;

import java.util.ArrayList;
import java.util.List;

import org.jboss.tools.gwt.shared.Clients;

import com.extjs.gxt.ui.client.Registry;
import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.GridEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.BoxComponent;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.Info;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.TabItem;
import com.extjs.gxt.ui.client.widget.TabPanel;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnData;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.extjs.gxt.ui.client.widget.grid.Grid;
import com.extjs.gxt.ui.client.widget.grid.GridCellRenderer;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.extjs.gxt.ui.client.widget.layout.FlowLayout;
import com.google.gwt.user.client.Element;

public class SearchGrid extends LayoutContainer {

	@Override
	protected void onRender(Element parent, int index) {
		super.onRender(parent, index);
		setLayout(new FlowLayout(10));

		GridCellRenderer<Clients> buttonRenderer = new GridCellRenderer<Clients>() {

			private boolean init;

			public Object render(final Clients model, String property,
					ColumnData config, final int rowIndex, final int colIndex,
					ListStore<Clients> store, Grid<Clients> grid) {
				if (!init) {
					init = true;
					grid.addListener(Events.ColumnResize,
							new Listener<GridEvent<Clients>>() {

								public void handleEvent(GridEvent<Clients> be) {
									for (int i = 0; i < be.getGrid().getStore()
											.getCount(); i++) {
										if (be.getGrid().getView()
												.getWidget(i, be.getColIndex()) != null
												&& be.getGrid()
														.getView()
														.getWidget(
																i,
																be.getColIndex()) instanceof BoxComponent) {
											((BoxComponent) be
													.getGrid()
													.getView()
													.getWidget(i,
															be.getColIndex()))
													.setWidth(be.getWidth() - 10);
										}
									}
								}
							});
				}

				Button b = new Button((String) model.get(property),
						new SelectionListener<ButtonEvent>() {
							@Override
							public void componentSelected(ButtonEvent ce) {
								Info.display(model.getName(), "<ul><li>"
										+ "</li></ul>");
								TabPanel tabPanel = Registry.get("tabPanel");
								NewClientForm newClientForm = new NewClientForm();
								TabItem item = new TabItem();
								item.setText("Update Client");
								item.setClosable(true);
								item.add(newClientForm);
								newClientForm.nameField.setValue(model.getName());
								tabPanel.add(item);
								tabPanel.setSelection(item);
							}
						});
				b.setWidth(grid.getColumnModel().getColumnWidth(colIndex) - 10);
				b.setToolTip("Click for more information");

				return b;

				/*
				 * NewClientForm newClientForm = new NewClientForm(); return
				 * newClientForm;
				 */
			}
		};

		List<ColumnConfig> configs = new ArrayList<ColumnConfig>();

		ColumnConfig column = new ColumnConfig();
		column.setId("name");
		column.setHeader("Name");
		column.setWidth(200);
		configs.add(column);

		column = new ColumnConfig();
		column.setId("company");
		column.setHeader("Company");
		column.setRenderer(buttonRenderer);
		column.setWidth(100);
		configs.add(column);

		ListStore<Clients> store = new ListStore<Clients>();
		store.add(stocks);

		ColumnModel cm = new ColumnModel(configs);

		ContentPanel cp = new ContentPanel();
		cp.setBodyBorder(false);
		// cp.setIcon(Resources.ICONS.table());
		cp.setHeading("Basic Grid");
		cp.setButtonAlign(HorizontalAlignment.CENTER);
		cp.setLayout(new FitLayout());
		cp.setSize(600, 300);

		Grid<Clients> grid = new Grid<Clients>(store, cm);
		grid.setStyleAttribute("borderTop", "none");
		// grid.setAutoExpandColumn("name");
		grid.setBorders(true);
		grid.setStripeRows(true);
		cp.add(grid);

		add(cp);
	}


	public static void getClients(List<Clients> result) {
		 
		SearchGrid.stocks = result;
		    

		 
	  
	  }
	private static List<Clients> stocks = new ArrayList<Clients>();

}