package com.accn.ppes.magellan.inventory;


import java.util.Collection;

import org.springframework.data.repository.Repository;

public interface InventoryRepository extends Repository<Inventory,Long> {

	public Inventory save(Inventory inventory);
	public Collection<Inventory> findAll();
	public Collection<Inventory> findByProductName(String productName);
	public Collection<Inventory> findByLocationName(String locationName);
	public Inventory findByProductNameAndLocationName(String productName, String locationName);
}
