package model.customer;

import model.user.User;

public class CustomerUnverified extends Customer {

	private int ID;
	private String IP;

	public CustomerUnverified(int iD, String accountNum, User user, int iD2, String iP) {
		super(iD, accountNum, user);
		ID = iD2;
		IP = iP;
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

	public String getIP() {
		return this.IP;
	}

	/**
	 * 
	 * @param ID
	 */
	public void setIP(String IP) {
		this.IP = IP;
	}

}