package com.accn.ppes.magellan.inventory;

import java.text.MessageFormat;
import java.util.Collection;

import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.accn.ppes.magellan.inventory.api.MessagePublisher;

@Service
public class InventoryUtility implements InventoryService {

	@Autowired
	InventoryRepository repo;
	
	@Autowired
	private MessagePublisher messagePublisher;

	public InventoryUtility() {
		
	}
	
	public Inventory saveInventory(Inventory inventory) {

		repo.save(inventory);
		return inventory;

	}

	@Override
	public Collection<Inventory> getAllInventory() {
		// TODO Auto-generated method stub
		return repo.findAll();
	}

	@Override
	public Collection<Inventory> getByProductName(String productName) {
		// TODO Auto-generated method stub
		return repo.findByProductName(productName);
	}

	@Override
	public Collection<Inventory> getByLocationName(String locationName) {
		// TODO Auto-generated method stub
		return repo.findByLocationName(locationName);
	}

	@Override
	public Inventory getByProductNameAndLocationName(String productName, String locationName) {
		// TODO Auto-generated method stub
		return repo.findByProductNameAndLocationName(productName, locationName);
	}

	@Override
	public Inventory updateInventory(String productName, String locationName, int quantity) {
		// TODO Auto-generated method stub
		Inventory inventory = repo.findByProductNameAndLocationName(productName, locationName);
		int initialQuantity = inventory.getQuantity();
		inventory.setQuantity(initialQuantity + quantity);
		repo.save(inventory);
		return inventory;
	}

	@Override
	public Integer getQuantity(String productName, String locationName) {
		// TODO Auto-generated method stub
		Inventory inventory = repo.findByProductNameAndLocationName(productName, locationName);
		if (inventory != null)
			return inventory.getQuantity();
		else
			return 0;
	}

	@Override
	public Inventory updateInventory(Order orderInfo) {
		Inventory inventory = repo.findByProductNameAndLocationName(orderInfo.getProductName(),
				orderInfo.getLocationName());
		try {
			if(inventory == null) {
				throw new IllegalStateException("Product or Location not found->"+"Product:"
					+orderInfo.getProductName()+", Location:"+orderInfo.getLocationName());
			}
			int initialQuantity = inventory.getQuantity();

			if (orderInfo.getStatus().equalsIgnoreCase("B")) { // quantity will be
																// negative when
																// order is placed,
																// condition to be
																// checked.
				if (initialQuantity - orderInfo.getQuantity() >= 0) {
					inventory.setQuantity(initialQuantity - orderInfo.getQuantity());
                    repo.save(inventory);
                    orderInfo.setStatus("S");
				} else {
                    String errorMsg = MessageFormat.format("Order is rejected as inventory is low, marking the status as \'E\'. Order id {0} .", orderInfo.getOrder_ID());
					System.out.println(errorMsg);
					orderInfo.setStatus("E");
				}
			} else { // quantity will be positive when order is cancelled and no
						// checks to be performed.
				inventory.setQuantity(initialQuantity + orderInfo.getQuantity());
				repo.save(inventory);
			}
			return inventory;
		} catch (Exception e) {
			orderInfo.setStatus("E");
			throw new AmqpRejectAndDontRequeueException(e);
		} finally {
            messagePublisher.postInResponseQueue(orderInfo);
        }

	}

	/**
	 * Post message in Confirm Order Response Queue.
	 * 
	 * @param orderDetails
	 *//*
	private void postInResponseQueue(Order orderDetails) {
		ObjectMapper objectMapper = new ObjectMapper();
		String message;
		try {
			message = objectMapper.writeValueAsString(orderDetails);
			MessageProperties properties = new MessageProperties();
			properties.setContentType(MessageProperties.CONTENT_TYPE_TEXT_PLAIN);
			Message textMessage = new Message(message.getBytes(), properties);
			rabbitTemplate.send(exchangeName, confirmOrderResponseRoutingKey, textMessage);
			System.out.println("Message posted in Response Queue:" + textMessage);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

	}*/

}
