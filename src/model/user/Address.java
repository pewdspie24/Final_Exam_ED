package model.user;

public class Address {

	private int ID;
	private String city;
	private String district;
	private String houseno;

	public Address(int iD, String city, String district, String houseno) {
		super();
		this.ID = iD;
		this.city = city;
		this.district = district;
		this.houseno = houseno;
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

	public String getCity() {
		return this.city;
	}

	/**
	 * 
	 * @param city
	 */
	public void setCity(String city) {
		this.city = city;
	}

	public String getDistrict() {
		return this.district;
	}

	/**
	 * 
	 * @param district
	 */
	public void setDistrict(String district) {
		this.district = district;
	}

	public String getHouseno() {
		return this.houseno;
	}

	/**
	 * 
	 * @param houseno
	 */
	public void setHouseno(String houseno) {
		this.houseno = houseno;
	}

}