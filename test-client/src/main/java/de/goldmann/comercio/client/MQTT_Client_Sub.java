package de.goldmann.comercio.client;

import java.util.Date;

import org.fusesource.mqtt.client.BlockingConnection;
import org.fusesource.mqtt.client.Callback;
import org.fusesource.mqtt.client.MQTT;
import org.fusesource.mqtt.client.Message;
import org.fusesource.mqtt.client.QoS;
import org.fusesource.mqtt.client.Topic;

import com.fasterxml.jackson.databind.ObjectMapper;

import de.goldmann.comercio.domain.order.OrderDirection;
import de.goldmann.comercio.transfer.down.OrderRow;

public class MQTT_Client_Sub {


	protected static final String topicName = "demo";

	public static void main(String[] args) {

		try {
			// new MQTT_Client_Sub().connect();
			OrderRow row = new OrderRow(1, "goldi", "DAX", new Date().getTime(), OrderDirection.SHORT, 1);
			System.out.println(new ObjectMapper().writeValueAsString(row));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

		}

	}

	private void connect() throws Exception {
		final MQTT client = createMQTTConnection();
		BlockingConnection connection = client.blockingConnection();
		connection.connect();

		Topic[] topics = { new Topic(topicName, QoS.AT_LEAST_ONCE) };

		connection.subscribe(topics);

		while (true) {
			System.out.println("Receiving .....");
			Message message = connection.receive();
			System.out.println("Found message on topic : " + message.getTopic());
			byte[] payload = message.getPayload();

			System.out.println("Found message payload : " + new String(payload));
			// process the message then:
			message.ack();
		}
	}

	protected MQTT createMQTTConnection() throws Exception {
		final MQTT mqtt = new MQTT();
		mqtt.setHost("192.168.29.1", 1883);
		return mqtt;
	}

	<T> Callback<T> onui(final Callback<T> original) {
		return new Callback<T>() {
			@Override
			public void onSuccess(final T value) {
				System.out.println("onSuccess:" + value);
			}

			@Override
			public void onFailure(final Throwable error) {
				error.printStackTrace();
			}
		};
	}


}
