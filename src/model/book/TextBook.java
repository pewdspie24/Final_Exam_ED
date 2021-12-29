package model.book;

import model.employee.Employee;

public class TextBook extends Book {

	private int ID;
	private int numberOfPages;
	private int volume;
	private int editions;

	public TextBook(int iSBN, String title, String summary, int years, Employee employee, Author author,
			Publisher publisher, int iD, int numberOfPages, int volume, int editions) {
		super(iSBN, title, summary, years, employee, author, publisher);
		ID = iD;
		this.numberOfPages = numberOfPages;
		this.volume = volume;
		this.editions = editions;
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

	public int getNumberOfPages() {
		return this.numberOfPages;
	}

	/**
	 * 
	 * @param numberOfPages
	 */
	public void setNumberOfPages(int numberOfPages) {
		this.numberOfPages = numberOfPages;
	}

	public int getVolume() {
		return this.volume;
	}

	/**
	 * 
	 * @param volume
	 */
	public void setVolume(int volume) {
		this.volume = volume;
	}

	public int getEditions() {
		return this.editions;
	}

	/**
	 * 
	 * @param editions
	 */
	public void setEditions(int editions) {
		this.editions = editions;
	}

}