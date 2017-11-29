package com.accn.ppes.magellan.inventory;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Id;

public class InventoryId implements Serializable {

	@Id
	@Column(name = "ProductName")
	private String productName;
	@Id
	@Column(name = "LocationName")
	private String locationName;
}
