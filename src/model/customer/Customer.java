package model.customer;

import model.user.User;

public class Customer {

	private int ID;
	private String accountNum;
	private User user;

	public Customer(int iD, String accountNum, User user) {
		super();
		ID = iD;
		this.accountNum = accountNum;
		this.user = user;
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

	public String getAccountNum() {
		return this.accountNum;
	}

	/**
	 * 
	 * @param accountNum
	 */
	public void setAccountNum(String accountNum) {
		this.accountNum = accountNum;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}