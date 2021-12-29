package model.reportNoti;

import model.customer.Customer;
import model.employee.Employee;

public class Notification {

	private int ID;
	private String context;
	private String timeStamp;
	private String from;
	private Employee employee;
	private Customer customer;
	
	public Notification(int iD, String context, String timeStamp, String from, Employee employee, Customer customer) {
		super();
		ID = iD;
		this.context = context;
		this.timeStamp = timeStamp;
		this.from = from;
		this.employee = employee;
		this.customer = customer;
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

	public String getContext() {
		return this.context;
	}

	/**
	 * 
	 * @param context
	 */
	public void setContext(String context) {
		this.context = context;
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

	public String getFrom() {
		return this.from;
	}

	/**
	 * 
	 * @param from
	 */
	public void setFrom(String from) {
		this.from = from;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

}