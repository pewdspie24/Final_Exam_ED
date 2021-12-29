package model.user;

public class Birth {

	private int ID;
	private String day;
	private String month;
	private String years;

	public Birth(int iD, String day, String month, String years) {
		super();
		this.ID = iD;
		this.day = day;
		this.month = month;
		this.years = years;
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

	public String getDay() {
		return this.day;
	}

	/**
	 * 
	 * @param day
	 */
	public void setDay(String day) {
		this.day = day;
	}

	public String getMonth() {
		return this.month;
	}

	/**
	 * 
	 * @param month
	 */
	public void setMonth(String month) {
		this.month = month;
	}

	public String getYears() {
		return this.years;
	}

	/**
	 * 
	 * @param years
	 */
	public void setYears(String years) {
		this.years = years;
	}

}