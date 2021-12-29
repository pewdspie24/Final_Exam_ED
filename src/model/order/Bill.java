package model.order;

import model.employee.Employee;

public class Bill {

	private int ID;
	private float totalPrice;
	private String dateCreated;
	private float totalDiscount;
	private Order order;
	private Employee employee;

	public Bill(int iD, float totalPrice, String dateCreated, float totalDiscount, Order order, Employee employee) {
		super();
		ID = iD;
		this.totalPrice = totalPrice;
		this.dateCreated = dateCreated;
		this.totalDiscount = totalDiscount;
		this.order = order;
		this.employee = employee;
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

	public float getTotalPrice() {
		return this.totalPrice;
	}

	/**
	 * 
	 * @param totalPrice
	 */
	public void setTotalPrice(float totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getDateCreated() {
		return this.dateCreated;
	}

	/**
	 * 
	 * @param dateCreated
	 */
	public void setDateCreated(String dateCreated) {
		this.dateCreated = dateCreated;
	}

	public float getTotalDiscount() {
		return this.totalDiscount;
	}

	/**
	 * 
	 * @param totalDiscount
	 */
	public void setTotalDiscount(float totalDiscount) {
		this.totalDiscount = totalDiscount;
	}

	public Order getOrder() {
		return this.order;
	}

	/**
	 * 
	 * @param order
	 */
	public void setOrder(Order order) {
		this.order = order;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

}