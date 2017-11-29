/**
 * 
 */
package com.accn.ppes.magellan.inventory;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.accn.ppes.magellan.inventory.api.MessagePublisher;

/**
 * @author jai.balaji.sukumar
 *
 */
@Component
@Profile("!rmq")
public class DefaultMessagePublisher implements MessagePublisher {

	/**
	 * 
	 */
	public DefaultMessagePublisher() {
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see com.accn.ppes.magellan.inventory.api.MessagePublisher#postInResponseQueue(com.accn.ppes.magellan.inventory.Order)
	 */
	@Override
	public void postInResponseQueue(Order order) {
		System.out.println("Message will be received but not posted" + order);
	}

}
