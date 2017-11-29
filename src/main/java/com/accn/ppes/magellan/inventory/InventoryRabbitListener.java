package com.accn.ppes.magellan.inventory;

import java.io.IOException;

import org.springframework.amqp.rabbit.listener.exception.ListenerExecutionFailedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class InventoryRabbitListener {
	
	@Autowired
	private InventoryUtility inventoryUtil;
	
	public void orderPlaced(Object text) throws IOException,ListenerExecutionFailedException{
		String message = (String) text;
		ObjectMapper mapper = new ObjectMapper();
		Order order = mapper.readValue(message, Order.class);
		//inventoryUtil.updateInventory(order.getProductName(), order.getLocationName(), -order.getQuantity());
		//order.setQuantity(order.getQuantity());
		System.out.println("order in inventory"+order.toString());
		inventoryUtil.updateInventory(order);
		System.out.println("message posted through inventory"+message);
	}
	/*@RabbitListener(bindings = @QueueBinding(
		    value = @Queue(value = QUEUE_NAME ,durable = "true"),
		    key = "order.confirmed.key", exchange = @Exchange(value = EXCHANGE_NAME,durable="true")))
	public void orderConfirmed(final Message message){
		System.out.println("Printing orderConfirmed :"+message);
	}*/
	
	
	public void orderCancelled(Object text) throws JsonParseException, JsonMappingException, IOException{
		String message = (String) text;
		Order order = new Order();
		ObjectMapper mapper = new ObjectMapper();
		order = mapper.readValue(message, Order.class);
		inventoryUtil.updateInventory(order);
		System.out.println(message);
	}
	
}

