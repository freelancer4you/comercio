package de.goldmann.comercio.server.ui.orders;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.VerticalLayout;

import de.goldmann.comercio.domain.order.Order;
import de.goldmann.comercio.domain.service.OrderRepository;

@UIScope
@SpringView(name = OrdersView.ORDERS)
public class OrdersView extends VerticalLayout implements View
{
    private static final long  serialVersionUID = 1L;

    public static final String ORDERS           = "Orders";

    @Autowired
    private OrderRepository    orderRepo;
    private Grid               grid;

    @Override
    public void enter(ViewChangeEvent event)
    {
        if (this.grid == null)
        {
            this.grid = new Grid();

            HorizontalLayout actions = new HorizontalLayout();
            VerticalLayout mainLayout = new VerticalLayout(actions, grid);
            addComponent(mainLayout);

            // Configure layouts and components
            actions.setSpacing(true);
            mainLayout.setMargin(true);
            mainLayout.setSpacing(true);

            grid.setHeight(220, Unit.PIXELS);
            grid.setColumns("stockName", "stockPrice", "amountInput");
        }
        listOrders();
    }

    public void listOrders()
    {
        grid.getContainerDataSource().removeAllItems();
        grid.setContainerDataSource(new BeanItemContainer(Order.class, orderRepo.findAll()));

    }
}
