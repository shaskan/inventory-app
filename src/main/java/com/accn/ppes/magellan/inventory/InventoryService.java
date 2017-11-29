package com.accn.ppes.magellan.inventory;

import java.util.Collection;

public interface InventoryService {
	public Inventory saveInventory(Inventory inventory);
	public Collection<Inventory> getAllInventory();
	public Collection<Inventory> getByProductName(String productName);
	public Collection<Inventory> getByLocationName(String locationName);
	public Inventory getByProductNameAndLocationName(String productName,String locationName);
	public Inventory updateInventory(String productName,String locationName,int quantity);
	public Integer getQuantity(String productName,String locationName);
	public Inventory updateInventory(Order items);
}
