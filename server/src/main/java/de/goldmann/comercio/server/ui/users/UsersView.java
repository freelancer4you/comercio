package de.goldmann.comercio.server.ui.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

import de.goldmann.comercio.domain.User;
import de.goldmann.comercio.domain.service.UserRepository;

@UIScope
@SpringView(name = UsersView.USERS)
public class UsersView extends VerticalLayout implements View
{

    private static final long  serialVersionUID = 1L;
    public static final String USERS            = "Users";

    @Autowired
    private UserRepository     userRepo;

    private Grid               grid;

    @Autowired
    private UsersOrderView     userOrderView;

    @Override
    public void enter(ViewChangeEvent event)
    {

        if (this.grid == null)
        {
            this.grid = new Grid();

            // build layout
            final TextField filter = new TextField();

            HorizontalLayout actions = new HorizontalLayout(filter);
            VerticalLayout mainLayout = new VerticalLayout(actions, grid, userOrderView);
            addComponent(mainLayout);

            // Configure layouts and components
            actions.setSpacing(true);
            mainLayout.setMargin(true);
            mainLayout.setSpacing(true);

            grid.setHeight(220, Unit.PIXELS);
            grid.setColumns("id", "firstName", "lastName");

            filter.setInputPrompt("Filter by last name");

            // Hook logic to components

            // Replace listing with filtered content when user changes filter
            filter.addTextChangeListener(e -> listUsers(e.getText()));

            // Connect selected Customer to editor or hide if none is selected

            grid.addSelectionListener(e -> {

                if (!e.getSelected().isEmpty())
                {
                    userOrderView.listOrders((User) grid.getSelectedRow());
                }

            });
        }
        // Initialize listing
        listUsers(null);

    }

    // tag::listCustomers[]
    private void listUsers(String text)
    {

        if (StringUtils.isEmpty(text))
        {
            grid.setContainerDataSource(new BeanItemContainer(User.class, userRepo.findAll()));
        }
        else
        {
            // grid.setContainerDataSource(new BeanItemContainer(User.class, userRepo
            // .findByLastName((text)));
        }

    }
}
