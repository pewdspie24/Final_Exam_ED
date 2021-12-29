package model.order;

import model.customer.Customer;

public class Cart {

	private int ID;
	private Customer customer;
	private String createdAt;
	private String updatedAt;
	private int totalQuantity;
	private float totalPrice;

	public Cart(int iD, Customer customer, String createdAt, String updatedAt, int totalQuantity, Float totalPrice) {
		super();
		this.ID = iD;
		this.customer = customer;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.totalQuantity = totalQuantity;
		this.totalPrice = totalPrice;
	}

	public int getID() {
		return this.ID;
	}

	/**
	 * 
	 * @param ID
	 */
	public void setID(int ID) {
		this.ID = ID;
	}

	public String getCreatedAt() {
		return this.createdAt;
	}

	/**
	 * 
	 * @param createdAt
	 */
	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}

	public String getUpdatedAt() {
		return this.updatedAt;
	}

	/**
	 * 
	 * @param updatedAt
	 */
	public void setUpdatedAt(String updatedAt) {
		this.updatedAt = updatedAt;
	}

	public int getTotalQuantity() {
		return totalQuantity;
	}

	public void setTotalQuantity(int totalQuantity) {
		this.totalQuantity = totalQuantity;
	}

	public Float getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Float totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

}