package de.goldmann.comercio.server.ui.users;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Grid;
import com.vaadin.ui.VerticalLayout;

import de.goldmann.comercio.domain.User;
import de.goldmann.comercio.domain.order.Order;
import de.goldmann.comercio.domain.service.OrderRepository;

@SpringComponent
@UIScope
public class UsersOrderView extends VerticalLayout
{

    private static final long serialVersionUID = 1L;
    private OrderRepository   orderRepo;
    private Grid              grid;

    @Autowired
    public UsersOrderView(OrderRepository orderRepo)
    {
        this.orderRepo = orderRepo;
        this.grid = new Grid();
        grid.setHeight(300, Unit.PIXELS);
        grid.setColumns("stockName", "stockPrice", "amountInput");
        addComponent(grid);
    }

    public void listOrders(User user)
    {
        grid.getContainerDataSource().removeAllItems();
        final List<Order> orders = orderRepo.findByUserId(user.getId());
        if (!orders.isEmpty())
        {
            grid.setContainerDataSource(new BeanItemContainer(Order.class, orders));
        }
    }

}
