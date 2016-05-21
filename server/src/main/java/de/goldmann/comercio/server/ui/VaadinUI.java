package de.goldmann.comercio.server.ui;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.annotations.Theme;
import com.vaadin.navigator.Navigator;
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

import de.goldmann.comercio.server.ui.amq.AmqView;
import de.goldmann.comercio.server.ui.orders.OrdersView;
import de.goldmann.comercio.server.ui.users.UsersView;

@SpringUI
@Theme("valo")
public class VaadinUI extends UI
{
    /**
     * 
     */
    private static final long                   serialVersionUID = 1L;

    // private final TestIcon testIcon = new TestIcon(100);
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
    }

    CssLayout buildMenu()
    {
        // Add items
        // menuItems.put(Desktop.DESKTOP, "Desktop");
        // menuItems.put(Charts.CHARTS, "Charts");

        final HorizontalLayout top = new HorizontalLayout();
        top.setWidth("100%");
        top.setDefaultComponentAlignment(Alignment.MIDDLE_LEFT);
        top.addStyleName(ValoTheme.MENU_TITLE);
        menu.addComponent(top);

        // final MenuBar settings = new MenuBar();
        // settings.addStyleName("user-menu");
        // final StringGenerator sg = new StringGenerator();
        // final MenuItem settingsItem = settings.addItem(
        // sg.nextString(true) + " " + sg.nextString(true) + sg.nextString(false), new ThemeResource(
        // "../tests-valo/img/profile-pic-300px.jpg"), null);
        // settingsItem.addItem("Edit Profile", null);
        // settingsItem.addItem("Preferences", null);
        // settingsItem.addSeparator();
        // settingsItem.addItem("Sign Out", null);
        // menu.addComponent(settings);

        // menuItemsLayout.setPrimaryStyleName("valo-menuitems");
        menuItemsLayout.setPrimaryStyleName(ValoTheme.MENU_ITEM);
        menu.addComponent(menuItemsLayout);

        addMenuEntry("../mytheme/img/pc3_white.png", UsersView.USERS, "Users");
        addMenuEntry("../mytheme/img/chart.png", OrdersView.ORDERS, "Orders");
        addMenuEntry("../mytheme/img/chart.png", AmqView.AMQ, "AMQ");
        // addMenuEntry("../mytheme/img/bell.png", Alarms.ALARMS, "Kursalarme");

        // int count = -1;
        // for (final Entry<String, String> item : menuItems.entrySet())
        // {
        // final Button b = new Button(item.getValue(), new ClickListener()
        // {
        // /**
        // *
        // */
        // private static final long serialVersionUID = 1L;
        //
        // @Override
        // public void buttonClick(final ClickEvent event)
        // {
        // navigator.navigateTo(item.getKey());
        // }
        // });
        // if (count == 2)
        // {
        // b.setCaption(b.getCaption() + " <span class=\"valo-menu-badge\">123</span>");
        // }
        // b.setHtmlContentAllowed(true);
        // b.setPrimaryStyleName("valo-menu-item");
        // b.setIcon(testIcon.get());
        // b.setId("menu-btn-id-" + (count + 1));
        // menuItemsLayout.addComponent(b);
        // count++;
        // }

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
        // MenuItem chartItem = chart.addItem("", new ThemeResource(img), addMenuCommand(command));
        MenuItem chartItem = chart.addItem(command, null, addMenuCommand(command));
        chartItem.setDescription(description);
        menu.addComponent(chart);
    }

}
