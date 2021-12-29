package model.book;

import model.employee.Employee;

public class LightNovel extends Book {

	private int ID;
	private String translateLanguage;
	private int volume;
	private int editions;

	public LightNovel(int iSBN, String title, String summary, int years, Employee employee, Author author,
			Publisher publisher, int iD, String translateLanguage, int volume, int editions) {
		super(iSBN, title, summary, years, employee, author, publisher);
		this.ID = iD;
		this.translateLanguage = translateLanguage;
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

	public String getTranslateLanguage() {
		return this.translateLanguage;
	}

	/**
	 * 
	 * @param translateLanguage
	 */
	public void setTranslateLanguage(String translateLanguage) {
		this.translateLanguage = translateLanguage;
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