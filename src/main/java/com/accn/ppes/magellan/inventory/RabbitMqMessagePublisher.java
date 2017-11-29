/**
 * 
 */
package com.accn.ppes.magellan.inventory;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.accn.ppes.magellan.inventory.api.MessagePublisher;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author jai.balaji.sukumar
 *
 */
@Component
@Profile("rmq")
public class RabbitMqMessagePublisher implements MessagePublisher {
	
	@Value("${exchange.name}")
	private String exchangeName;

	@Value("${confirm.order.response.routingKey}")
	private String confirmOrderResponseRoutingKey;
	
	@Value("${confirm.order.response.queue}")
    private String CONFORM_ORDER_RESPONSE_QUEUE;

	@Autowired
	private RabbitTemplate rabbitTemplate;
	
	/**
	 * 
	 */
	public RabbitMqMessagePublisher() {
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see com.accn.ppes.magellan.inventory.api.MessagePublisher#postInResponseQueue(com.accn.ppes.magellan.inventory.Order)
	 */
	@Override
	public void postInResponseQueue(Order order) {
		ObjectMapper objectMapper = new ObjectMapper();
		String message;
		try {
			message = objectMapper.writeValueAsString(order);
			MessageProperties properties = new MessageProperties();
			properties.setContentType(MessageProperties.CONTENT_TYPE_TEXT_PLAIN);
			Message textMessage = new Message(message.getBytes(), properties);
			rabbitTemplate.setQueue(CONFORM_ORDER_RESPONSE_QUEUE);
			rabbitTemplate.send(exchangeName, confirmOrderResponseRoutingKey, textMessage);
			System.out.println("Message posted in Response Queue:" + textMessage);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
	}

}
