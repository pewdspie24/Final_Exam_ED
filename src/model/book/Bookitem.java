package model.book;

import model.employee.Employee;

public class Bookitem {

	private int ID;
	private Book book;
	private Employee employee;
	private float price;
	private float discount;
	private String uploadDate;
	private int inStock;

	public Bookitem(int iD, Book book, Employee employee, float price, float discount, String uploadDate, int inStock) {
		super();
		ID = iD;
		this.book = book;
		this.employee = employee;
		this.price = price;
		this.discount = discount;
		this.uploadDate = uploadDate;
		this.inStock = inStock;
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

	public float getPrice() {
		return this.price;
	}

	/**
	 * 
	 * @param price
	 */
	public void setPrice(float price) {
		this.price = price;
	}

	public float getDiscount() {
		return this.discount;
	}

	/**
	 * 
	 * @param discount
	 */
	public void setDiscount(float discount) {
		this.discount = discount;
	}

	public String getUploadDate() {
		return this.uploadDate;
	}

	/**
	 * 
	 * @param uploadDate
	 */
	public void setUploadDate(String uploadDate) {
		this.uploadDate = uploadDate;
	}

	public int getInStock() {
		return this.inStock;
	}

	/**
	 * 
	 * @param inStock
	 */
	public void setInStock(int inStock) {
		this.inStock = inStock;
	}

	public Book getBook() {
		return this.book;
	}

	/**
	 * 
	 * @param book
	 */
	public void setBook(Book book) {
		this.book = book;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

}