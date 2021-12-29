package model.customer;

import model.user.User;

public class CustomerVerified extends Customer {

	private int ID;
	private int memberID;

	public CustomerVerified(int iD, String accountNum, User user, int iD2, int memberID) {
		super(iD, accountNum, user);
		ID = iD2;
		this.memberID = memberID;
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

	public int getMemberID() {
		return this.memberID;
	}

	/**
	 * 
	 * @param memberID
	 */
	public void setMemberID(int memberID) {
		this.memberID = memberID;
	}

}