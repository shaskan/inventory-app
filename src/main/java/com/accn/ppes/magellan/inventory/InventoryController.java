package com.accn.ppes.magellan.inventory;

import java.util.Collection;


import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.exception.DataException;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.rabbit.listener.exception.ListenerExecutionFailedException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class InventoryController {

	protected InventoryService inventoryservice;

	public InventoryController(InventoryService inventoryservice) {
		this.inventoryservice = inventoryservice;
	}
	
	 @RequestMapping(method = RequestMethod.GET)
	    public @ResponseBody String index(){
	            return "Welcome to APP PES Inventory API";

	    }

	@RequestMapping(value = "/inventory", method = RequestMethod.POST)
	public Inventory insert(@RequestBody Inventory inventory) {

		inventoryservice.saveInventory(inventory);
		return inventory;

	}

	@RequestMapping(value = "/inventory", method = RequestMethod.GET)
	public Collection<Inventory> getAll() {

		Collection<Inventory> inventories = inventoryservice.getAllInventory();
		return inventories;

	}

	@RequestMapping(value = "/inventory/getByProductName/{productName}", method = RequestMethod.GET)
	public Collection<Inventory> getByProductID(@PathVariable String productName) {

		Collection<Inventory> inventories = inventoryservice.getByProductName(productName);
		return inventories;

	}

	@RequestMapping(value = "/inventory/getByLocationName/{locationName}", method = RequestMethod.GET)
	public Collection<Inventory> getByLocationID(@PathVariable String locationName) {

		Collection<Inventory> inventories = inventoryservice.getByLocationName(locationName);
		return inventories;

	}

	@RequestMapping(value = "/inventory/{productName}/{locationName}", method = RequestMethod.GET)
	public Inventory getByProductIdAndLocationId(@PathVariable String productName, @PathVariable String locationName) {

		Inventory inventory = inventoryservice.getByProductNameAndLocationName(productName, locationName);
		return inventory;
	}
	@RequestMapping(value = "/inventory/getQuantity/{productName}/{locationName}")
	public Integer getQuantity(@PathVariable String productName, @PathVariable String locationName) {

		return inventoryservice.getQuantity(productName, locationName);
	}

	@RequestMapping(value = "/inventory/updateQuantity", method = RequestMethod.PUT)
	public Inventory updateQuantity(@RequestBody Inventory inventory) {
		int quantity = inventory.getQuantity();
		return inventoryservice.updateInventory(inventory.getProductName(), inventory.getLocationName(), quantity);
		 
	}
	@ExceptionHandler(DataException.class)
	public String DataExceptionErrorMessage(DataException e) {
		return "Deficient Quantity";
	}
	
	

}
