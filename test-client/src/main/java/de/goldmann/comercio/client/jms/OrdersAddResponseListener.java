package de.goldmann.comercio.client.jms;

import static de.goldmann.comercio.client.ClientUtil.USER_CONFIG_PATH;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import de.goldmann.comercio.client.UserOrderJobImpl;
import de.goldmann.comercio.transfer.down.OrderRow;

@Component
public class OrdersAddResponseListener implements MessageListener
{

    @Override
    public void onMessage(Message message)
    {
        if (message instanceof TextMessage)
        {
            try
            {
                final String orderMessage = ((TextMessage) message).getText();
                final ObjectMapper mapper = new ObjectMapper();
                final OrderRow result = mapper.readValue(orderMessage, new TypeReference<OrderRow>()
                {});
                System.out.println("Order with id " + orderMessage + "insert");

                final Path path = Paths.get(USER_CONFIG_PATH + result.getLogin() + ".txt");
                final List<String> lines = Files.readAllLines(path);

                final String line = lines.remove(result.getLineNumber());

                if (UserOrderJobImpl.lineHasNoOrderId(line.split(";")))
                {
                    lines.add(result.getLineNumber(),
                            new StringBuffer(line).append(result.getOrderOd() + ";" + result.getTime() + ";")
                                    .toString());
                    Files.delete(path);
                    Files.write(path, lines);
                }

            }
            catch (JMSException | IOException e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

}
