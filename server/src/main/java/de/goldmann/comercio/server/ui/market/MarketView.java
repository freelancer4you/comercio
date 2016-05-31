package de.goldmann.comercio.server.ui.market;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.data.Property;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

import de.goldmann.comercio.domain.order.LeadingIndex;
import de.goldmann.comercio.domain.service.IndexRepository;
import de.goldmann.comercio.server.stock.poll.StockInfo;

@UIScope
@SpringView(name = MarketView.MARKET)
public class MarketView extends VerticalLayout implements View {
	private static final long serialVersionUID = 1L;

	private static final Logger logger = LoggerFactory.getLogger(MarketView.class);
	
	public static final String MARKET = "Market";

	@Autowired
	private IndexRepository indexRepo;


	private Grid indexGrid;
	private Grid stockGrid;

	private Label label;

	private Map<Long, BeanItemContainer<StockInfo>> containerMap;

	private Label dtLabel;

	public void updateView(final StockInfo info) {
		if (containerMap != null && containerMap.size() > 0) {
			if (info.isIndex()) {
				System.out.println(info);
				return;
			}
			final BeanItemContainer<StockInfo> container = containerMap.get(info.getIndexId());
			final List<StockInfo> stocks = container.getItemIds();

			if (stocks.isEmpty()) {
				container.addItem(info);
			} else {
				boolean infoFound = false;
				for (StockInfo stockInfo : stocks) {
					if (stockInfo.getKey().equals(info.getKey()) && stockInfo.getPrice() != info.getPrice()) {
						final BeanItem<StockInfo> item = container.getItem(info);
						final Property<Double> nameProperty = item.getItemProperty("price");
						nameProperty.setValue(info.getPrice());
						stockGrid.markAsDirty();
						dtLabel.setValue(new Date().toString());
						logger.info("Updated:" + item);
						infoFound = true;
						break;
					}
				}
				if (!infoFound) {
					container.addItem(info);
				}
			}
		}
	}

	@Override
	public void enter(ViewChangeEvent event) {
		if (this.indexGrid == null) {

			this.indexGrid = new Grid();
			this.stockGrid = new Grid();
			label = new Label("Date:");
			dtLabel = new Label();
			HorizontalLayout actions = new HorizontalLayout(label, dtLabel);
			VerticalLayout mainLayout = new VerticalLayout(actions, new HorizontalLayout(indexGrid, stockGrid));
			addComponent(mainLayout);

			final List<LeadingIndex> indices = indexRepo.findAll();
			containerMap = new HashMap<>();
			for (LeadingIndex leadingIndex : indices) {
				final BeanItemContainer<StockInfo> container = new BeanItemContainer<StockInfo>(StockInfo.class,
						new ArrayList<>());
				containerMap.put(leadingIndex.getId(), container);
			}

			indexGrid.addSelectionListener(e -> {

				if (!e.getSelected().isEmpty()) {
					LeadingIndex selectedRow = (LeadingIndex) indexGrid.getSelectedRow();
					listStocks(selectedRow);
				}

			});

			// Configure layouts and components
			actions.setSpacing(true);
			mainLayout.setMargin(true);
			mainLayout.setSpacing(true);

			indexGrid.setHeight(220, Unit.PIXELS);
			indexGrid.setColumns("name", "isin");
			stockGrid.setColumns("name", "price");

		}
		listOrders();
	}

	private void listStocks(LeadingIndex index) {
		stockGrid.setContainerDataSource(this.containerMap.get(index.getId()));
	}

	public void listOrders() {
		indexGrid.getContainerDataSource().removeAllItems();
		indexGrid.setContainerDataSource(new BeanItemContainer<LeadingIndex>(LeadingIndex.class, indexRepo.findAll()));
	}

}
