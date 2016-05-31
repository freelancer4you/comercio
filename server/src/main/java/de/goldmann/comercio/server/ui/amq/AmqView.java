package de.goldmann.comercio.server.ui.amq;

import javax.jms.JMSException;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.apache.activemq.pool.PooledConnectionFactory;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.data.Container.Indexed;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.TabSheet.SelectedTabChangeEvent;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

import de.goldmann.comercio.server.services.amq.AmqServices;

@UIScope
@SpringView(name = AmqView.AMQ)
public class AmqView extends VerticalLayout implements View
{

    private static final String ACTIONS_TAB_HEADER = "Actions";

	private static final String     TOPICS_TAB_HEADER = "Topics";

    private static final String     QUEUES_TAB_HEADER = "Queues";

    /**
     * 
     */
    private static final long       serialVersionUID  = 1L;

    public static final String      AMQ               = "AMQ";

    private final String            brokerUrl         = "tcp://localhost:61616";

    private TabSheet                tabsheet;

    @Autowired
    private AmqServices             amqService;

    private Indexed                 queuesDataSource;

    private Indexed                 topicsDataSource;

    @Override
    public void enter(ViewChangeEvent event)
    {
        if (tabsheet == null)
        {
            tabsheet = new TabSheet();

			// Create the first tab
			VerticalLayout tabQueues = new VerticalLayout();
			Grid queuesGrid = buildQueuesGrid();
			queuesDataSource = queuesGrid.getContainerDataSource();
			tabQueues.addComponent(queuesGrid);
			tabsheet.addTab(tabQueues, QUEUES_TAB_HEADER);

			// This tab gets its caption from the component caption
			VerticalLayout tabTopics = new VerticalLayout();
			Grid topicsGrid = buildTopicsGrid();
			topicsDataSource = topicsGrid.getContainerDataSource();
			tabTopics.addComponent(topicsGrid);
			tabsheet.addTab(tabTopics, TOPICS_TAB_HEADER);

			VerticalLayout tabTests = new VerticalLayout();
			tabTests.addComponent(getActions());
			tabsheet.addTab(tabTests, ACTIONS_TAB_HEADER);

            tabsheet.addSelectedTabChangeListener(new TabSheet.SelectedTabChangeListener()
            {
				/**
				 * 
				 */
				private static final long serialVersionUID = 1L;

				@Override
                public void selectedTabChange(SelectedTabChangeEvent event)
                {

					final int selectedTabIndex = getSelectedTabIndex();
					if (selectedTabIndex == 0)
                    {
                        topicsDataSource.removeAllItems();

                        for (ActiveMQTopic topic : amqService.listTopics())
                        {
                            topicsDataSource.addItem(topic);
                        }
                    }
					else if (selectedTabIndex == 1)
                    {
                        queuesDataSource.removeAllItems();

                        for (ActiveMQQueue queue : amqService.listQueues())
                        {
                            queuesDataSource.addItem(queue);
                        }
                    }
                }
            });

            addComponent(tabsheet);
        }
    }

	private int getSelectedTabIndex() {
		int index = -1;
		if (tabsheet.getComponentCount() > 0) {
			index = tabsheet.getTabPosition(tabsheet.getTab(tabsheet.getSelectedTab()));
		}
		return index;
	}

    private Grid buildQueuesGrid()
    {
        final Grid grid = new Grid();
        grid.setHeight(220, Unit.PIXELS);
        grid.setColumns("queueName");
        grid.setContainerDataSource(new BeanItemContainer(ActiveMQQueue.class, amqService.listQueues()));
        return grid;
    }

    private Grid buildTopicsGrid()
    {
        final Grid grid = new Grid();
        grid.setHeight(220, Unit.PIXELS);
        grid.setColumns("topicName");
        grid.setContainerDataSource(new BeanItemContainer(ActiveMQTopic.class, amqService.listTopics()));
        return grid;
    }

    private Component getActions()
    {
        final VerticalLayout mainLayout = new VerticalLayout();

        HorizontalLayout testConnectionPanel = getConnectionPanel();
        VerticalLayout sendMsgPanel = getSendMsgsPanel();
        mainLayout.addComponents(testConnectionPanel, sendMsgPanel);
        return mainLayout;
    }

    private HorizontalLayout getConnectionPanel()
    {
        final TextArea errorMessage = new TextArea();
        final Button brokerSetupBtn = new Button("Connection-Test");
        brokerSetupBtn.addClickListener(new ClickListener()
        {

            /**
             * 
             */
            private static final long serialVersionUID = 1L;

            @Override
            public void buttonClick(ClickEvent event)
            {
                // BrokerService broker = null;
                // try
                // {
                // broker = new BrokerService();
                //
                // // configure the broker
                // broker.addConnector("tcp://localhost:61616");
                //
                // broker.start();
                // errorMessage.setValue("Broker setup finished");
                // }
                // catch (Exception e)
                // {
                // errorMessage.setValue(ExceptionUtils.getStackTrace(e));
                // }
                // finally
                // {}

                ActiveMQConnection connection = null;
                Session session = null;

                try
                {
                    final PooledConnectionFactory pooledConnectionFactory = new PooledConnectionFactory(
                            new ActiveMQConnectionFactory(brokerUrl));

                    connection = (ActiveMQConnection) pooledConnectionFactory.createConnection();

                    session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
                    String value = errorMessage.getValue();
                    errorMessage.setValue(value + "\n" + " Connection setup finished.");
                }
                catch (JMSException e)
                {
                    errorMessage.setValue(ExceptionUtils.getStackTrace(e));
                }
                finally
                {
                    try
                    {
                        session.close();
                        connection.close();
                    }
                    catch (JMSException e)
                    {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }

                }

            }
        });
        return new HorizontalLayout(brokerSetupBtn, errorMessage);
    }

    private VerticalLayout getSendMsgsPanel()
    {
        final Button sendMsgBtn = new Button("SendMsg");
        final TextArea msg = new TextArea();
        final TextField destTxt = new TextField();
        final ComboBox destTypCmb = new ComboBox();
        destTypCmb.addItem("Queue");
        destTypCmb.addItem("Topic");

        sendMsgBtn.addClickListener(new ClickListener()
        {

            /**
             * 
             */
            private static final long serialVersionUID = 1L;

            @Override
            public void buttonClick(ClickEvent event)
            {
                String info = amqService.sendMessage(destTxt.getValue(), destTypCmb.getValue().toString(),
                        msg.getValue());
                System.out.println(info);
            }
        });

        return new VerticalLayout(destTxt, destTypCmb, new HorizontalLayout(msg, sendMsgBtn));
    }
}
