package com.accn.ppes.magellan.inventory.api;

import com.accn.ppes.magellan.inventory.Order;

public interface MessagePublisher {
	
	void postInResponseQueue(Order order);
	
}
