package com.accn.ppes.magellan.inventory;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Order {

	private Integer productId;
	private Integer locationId;
	private Integer quantity;
	private String productName;
	private String locationName;
    private Long order_ID;
    private String status;


	
	public Integer getProductId() {
		return productId;
	}
	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	public Integer getLocationId() {
		return locationId;
	}
	public void setLocationId(Integer locationId) {
		this.locationId = locationId;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getLocationName() {
		return locationName;
	}
	@Override
	public String toString() {
		return "Order [productId=" + productId + ", locationId=" + locationId + ", quantity=" + quantity
				+ ", productName=" + productName + ", locationName=" + locationName + ", orderId=" + order_ID
				+ ", status=" + status + "]";
	}
	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}
	
	public String getStatus() {
	        return status;
	}
	public void setStatus(String status) {
	        this.status = status;
	}
	public Long getOrder_ID() {
		return order_ID;
	}
	public void setOrder_ID(Long order_ID) {
		this.order_ID = order_ID;
	}
	
}
