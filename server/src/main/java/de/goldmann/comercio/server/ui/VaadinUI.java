package de.goldmann.comercio.server.ui;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.annotations.Push;
import com.vaadin.annotations.Theme;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.Page;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.spring.navigator.SpringViewProvider;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Component;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.MenuBar.Command;
import com.vaadin.ui.MenuBar.MenuItem;
import com.vaadin.ui.UI;
import com.vaadin.ui.themes.ValoTheme;

import de.goldmann.comercio.server.stock.poll.StockInfo;
import de.goldmann.comercio.server.stock.poll.StockPublisher;
import de.goldmann.comercio.server.ui.amq.AmqView;
import de.goldmann.comercio.server.ui.market.MarketView;
import de.goldmann.comercio.server.ui.orders.OrdersView;
import de.goldmann.comercio.server.ui.users.UsersView;

@Push
@SpringUI
@Theme("valo")
public class VaadinUI extends UI
{
    /**
     * 
     */
    private static final long                   serialVersionUID = 1L;

    final ValoMenuLayout                        root             = new ValoMenuLayout();

    final ComponentContainer                    viewDisplay      = root.getContentContainer();
    final CssLayout                             menu             = new CssLayout();
    final CssLayout                             menuItemsLayout  = new CssLayout();
    {
        menu.setId("testMenu");
    }
    private Navigator                           navigator;
    private final LinkedHashMap<String, String> menuItems        = new LinkedHashMap<String, String>();

    @Autowired
    private SpringViewProvider                  viewProvider;

	@Autowired
	private StockPublisher stockPublisher;

    @Override
    protected void init(VaadinRequest vaadinRequest)
    {
		getPage().setTitle("Guidants");
        setContent(root);
        root.setWidth("100%");

        root.addMenu(buildMenu());
        addStyleName(ValoTheme.UI_WITH_MENU);

        navigator = new Navigator(this, viewDisplay);
        navigator.addProvider(viewProvider);
        navigator.addView(UsersView.USERS, UsersView.class);
        navigator.addView(OrdersView.ORDERS, OrdersView.class);
        navigator.addView(AmqView.AMQ, AmqView.class);
		navigator.addView(MarketView.MARKET, MarketView.class);

        final String f = Page.getCurrent().getUriFragment();
        if (f == null || f.equals(""))
        {
            navigator.navigateTo(UsersView.USERS);
        }

        navigator.addViewChangeListener(new ViewChangeListener()
        {

            /**
             * 
             */
            private static final long serialVersionUID = 1L;

            @Override
            public void afterViewChange(final ViewChangeEvent event)
            {
                for (final Iterator<Component> it = menuItemsLayout.iterator(); it.hasNext();)
                {
                    it.next().removeStyleName("selected");
                }
                for (final Entry<String, String> item : menuItems.entrySet())
                {
                    if (event.getViewName().equals(item.getKey()))
                    {
                        for (final Iterator<Component> it = menuItemsLayout.iterator(); it.hasNext();)
                        {
                            final Component c = it.next();
                            if (c.getCaption() != null && c.getCaption().startsWith(item.getValue()))
                            {
                                c.addStyleName("selected");
                                break;
                            }
                        }
                        break;
                    }
                }
                menu.removeStyleName("valo-menu-visible");
            }

            @Override
            public boolean beforeViewChange(final ViewChangeEvent event)
            {
                return true;
            }
        });

		new Thread(new QueueListener(stockPublisher)).start();
	}

	class QueueListener implements Runnable {
		private StockPublisher stockPublisher = null;

		public QueueListener(StockPublisher stockPublisher) {
			this.stockPublisher = stockPublisher;
		}

		@Override
		public void run() {
			while (true) {
				try {

					final View view = navigator.getCurrentView();
					if (view instanceof MarketView) {
						final StockInfo info = stockPublisher.take();
						access(new Runnable() {
							@Override
							public void run() {
								try {
									((MarketView) view).updateView(info);
								} catch (Exception e) {
									e.printStackTrace();
								}
							}
						});
					}

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

    CssLayout buildMenu()
    {

        final HorizontalLayout top = new HorizontalLayout();
        top.setWidth("100%");
        top.setDefaultComponentAlignment(Alignment.MIDDLE_LEFT);
        top.addStyleName(ValoTheme.MENU_TITLE);
        menu.addComponent(top);

        menuItemsLayout.setPrimaryStyleName(ValoTheme.MENU_ITEM);
        menu.addComponent(menuItemsLayout);

        addMenuEntry("../mytheme/img/pc3_white.png", UsersView.USERS, "Users");
        addMenuEntry("../mytheme/img/chart.png", OrdersView.ORDERS, "Orders");
        addMenuEntry("../mytheme/img/chart.png", AmqView.AMQ, "AMQ");
		addMenuEntry("../mytheme/img/chart.png", MarketView.MARKET, "Market");

        return menu;
    }

    private Command addMenuCommand(final String key)
    {
        return new Command()
        {
            /**
             * 
             */
            private static final long serialVersionUID = 1L;

            @Override
            public void menuSelected(MenuItem selectedItem)
            {
                navigator.navigateTo(key);
            }
        };
    }

    private void addMenuEntry(String img, String command, String description)
    {
        final MenuBar chart = new MenuBar();
        chart.addStyleName("user-menu");
        MenuItem chartItem = chart.addItem(command, null, addMenuCommand(command));
        chartItem.setDescription(description);
        menu.addComponent(chart);
    }

}
