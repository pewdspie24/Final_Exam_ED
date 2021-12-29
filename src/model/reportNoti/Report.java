package model.reportNoti;

import model.customer.Customer;
import model.employee.Employee;

public class Report {

	private int ID;
	private String timeStamp;
	private String reasons;
	private String nameProduct;
	private Customer customer;
	private Employee employee;

	public Report(int iD, String timeStamp, String reasons, String nameProduct, Customer customer, Employee employee) {
		super();
		ID = iD;
		this.timeStamp = timeStamp;
		this.reasons = reasons;
		this.nameProduct = nameProduct;
		this.customer = customer;
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

	public String getTimeStamp() {
		return this.timeStamp;
	}

	/**
	 * 
	 * @param timeStamp
	 */
	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}

	public String getNameProduct() {
		return this.nameProduct;
	}

	/**
	 * 
	 * @param nameProduct
	 */
	public void setNameProduct(String nameProduct) {
		this.nameProduct = nameProduct;
	}

	public String getReasons() {
		return this.reasons;
	}

	/**
	 * 
	 * @param reasons
	 */
	public void setReasons(String reasons) {
		this.reasons = reasons;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}


}