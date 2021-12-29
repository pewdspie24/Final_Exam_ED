package model.order;

public class Payment {

	private int ID;
	private float fees;
	private String names;
	
	public Payment(int iD, float fees, String names) {
		super();
		ID = iD;
		this.fees = fees;
		this.names = names;
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

	public float getFees() {
		return this.fees;
	}

	/**
	 * 
	 * @param fees
	 */
	public void setFees(float fees) {
		this.fees = fees;
	}

	public String getNames() {
		return names;
	}

	public void setNames(String names) {
		this.names = names;
	}

}