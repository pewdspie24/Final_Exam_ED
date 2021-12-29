package model.book;

import model.employee.Employee;

public class Book {

	private int ISBN;
	private String title;
	private String summary;
	private int years;
	private Employee employee;
	private Author author;
	private Publisher publisher;

	public Book(int iSBN, String title, String summary, int years, Employee employee, Author author,
			Publisher publisher) {
		super();
		this.ISBN = iSBN;
		this.title = title;
		this.summary = summary;
		this.years = years;
		this.employee = employee;
		this.author = author;
		this.publisher = publisher;
	}

	public int getISBN() {
		return this.ISBN;
	}

	/**
	 * 
	 * @param ISBN
	 */
	public void setISBN(int ISBN) {
		this.ISBN = ISBN;
	}

	public String getTitle() {
		return this.title;
	}

	/**
	 * 
	 * @param title
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	public String getSummary() {
		return this.summary;
	}

	/**
	 * 
	 * @param summary
	 */
	public void setSummary(String summary) {
		this.summary = summary;
	}

	public int getYears() {
		return this.years;
	}

	/**
	 * 
	 * @param years
	 */
	public void setYears(int years) {
		this.years = years;
	}

	public Author getAuthor() {
		return this.author;
	}

	/**
	 * 
	 * @param author
	 */
	public void setAuthor(Author author) {
		this.author = author;
	}

	public Publisher getPublisher() {
		return this.publisher;
	}

	/**
	 * 
	 * @param publisher
	 */
	public void setPublisher(Publisher publisher) {
		this.publisher = publisher;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	
	

}