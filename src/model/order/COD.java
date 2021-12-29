package model.order;

public class COD extends Payment {

	private int ID;
	private String currency;

	public COD(int iD, float fees, String names, int iD2, String currency) {
		super(iD, fees, names);
		ID = iD2;
		this.currency = currency;
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

	public String getCurrency() {
		return this.currency;
	}

	/**
	 * 
	 * @param currency
	 */
	public void setCurrency(String currency) {
		this.currency = currency;
	}

}