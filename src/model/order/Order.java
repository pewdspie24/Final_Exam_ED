package model.order;

import model.employee.Employee;

public class Order {

	private int ID;
	private String date;
	private Cart cart;
	private Payment payment;
	private Shipment shipment;
	private Voucher voucher;
	private Employee employee;

	public Order(int iD, String date, Cart cart, Payment payment, Shipment shipment, Voucher voucher,
			Employee employee) {
		super();
		ID = iD;
		this.date = date;
		this.cart = cart;
		this.payment = payment;
		this.shipment = shipment;
		this.voucher = voucher;
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

	public String getDate() {
		return this.date;
	}

	/**
	 * 
	 * @param date
	 */
	public void setDate(String date) {
		this.date = date;
	}

	public Cart getCart() {
		return this.cart;
	}

	/**
	 * 
	 * @param cart
	 */
	public void setCart(Cart cart) {
		this.cart = cart;
	}

	public Payment getPayment() {
		return this.payment;
	}

	/**
	 * 
	 * @param payment
	 */
	public void setPayment(Payment payment) {
		this.payment = payment;
	}

	public Shipment getShipment() {
		return this.shipment;
	}

	/**
	 * 
	 * @param shipment
	 */
	public void setShipment(Shipment shipment) {
		this.shipment = shipment;
	}

	public Voucher getVoucher() {
		return this.voucher;
	}

	/**
	 * 
	 * @param voucher
	 */
	public void setVoucher(Voucher voucher) {
		this.voucher = voucher;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

}