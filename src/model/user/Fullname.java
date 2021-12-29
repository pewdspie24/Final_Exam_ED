package model.user;

public class Fullname {

	private int ID;
	private String firstname;
	private String lastname;

	public Fullname(int iD, String firstname, String lastname) {
		super();
		this.ID = iD;
		this.firstname = firstname;
		this.lastname = lastname;
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

	public String getFirstname() {
		return this.firstname;
	}

	/**
	 * 
	 * @param firstname
	 */
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return this.lastname;
	}

	/**
	 * 
	 * @param lastname
	 */
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

}