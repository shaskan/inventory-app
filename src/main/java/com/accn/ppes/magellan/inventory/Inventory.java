package com.accn.ppes.magellan.inventory;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table(name="inventory")
@IdClass(InventoryId.class)
public class Inventory implements Serializable{
	@Id
	@Column(name = "productName")
	private String productName;
	@Id
	@Column(name = "locationName")
	private String locationName;
	@Column(name = "Quantity")
	private int quantity;
	
	
	public String getProductName() {
		return productName;
	}


	public void setProductName(String productName) {
		this.productName = productName;
	}


	public String getLocationName() {
		return locationName;
	}


	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}


	public int getQuantity() {
		return quantity;
	}


	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}


	@Override
	public String toString() {
		return "Inventory [productName=" + productName + ", locationName=" + locationName + ", quantity=" + quantity
				+ "]";
	}
	
	

}
