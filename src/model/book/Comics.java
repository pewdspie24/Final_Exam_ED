package model.book;

import model.employee.Employee;

public class Comics extends Book {

	private int ID;
	private String nameSeries;
	private String artists;

	public Comics(int iSBN, String title, String summary, int years, Employee employee, Author author,
			Publisher publisher, int iD, String nameSeries, String artists) {
		super(iSBN, title, summary, years, employee, author, publisher);
		ID = iD;
		this.nameSeries = nameSeries;
		this.artists = artists;
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

	public String getNameSeries() {
		return this.nameSeries;
	}

	/**
	 * 
	 * @param nameSeries
	 */
	public void setNameSeries(String nameSeries) {
		this.nameSeries = nameSeries;
	}

	public String getArtists() {
		return this.artists;
	}

	/**
	 * 
	 * @param artists
	 */
	public void setArtists(String artists) {
		this.artists = artists;
	}

}