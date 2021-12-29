package model.order;

public class CreditCard extends Payment {

	private int ID;
	private String cardNumber;
	private String type;

	public CreditCard(int iD, float fees, String names, int iD2, String cardNumber, String type) {
		super(iD, fees, names);
		ID = iD2;
		this.cardNumber = cardNumber;
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

	public String getCardNumber() {
		return this.cardNumber;
	}

	/**
	 * 
	 * @param cardNumber
	 */
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
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