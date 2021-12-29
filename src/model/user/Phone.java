package model.user;

public class Phone {

	private int ID;
	private String phoneNum;
	private String type;

	public Phone(int iD, String phoneNum, String type) {
		super();
		this.ID = iD;
		this.phoneNum = phoneNum;
		this.type = type;
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

	public String getPhoneNum() {
		return this.phoneNum;
	}

	/**
	 * 
	 * @param phoneNum
	 */
	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public String getType() {
		return this.type;
	}

	/**
	 * 
	 * @param type
	 */
	public void setType(String type) {
		this.type = type;
	}

}