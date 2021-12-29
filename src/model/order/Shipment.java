package model.order;

public class Shipment {

	private int ID;
	private float fees;
	private String address;
	private String names;

	public Shipment(int iD, float fees, String address, String names) {
		super();
		ID = iD;
		this.fees = fees;
		this.address = address;
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

	public String getAddress() {
		return this.address;
	}

	/**
	 * 
	 * @param address
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	public String getNames() {
		return names;
	}

	public void setNames(String names) {
		this.names = names;
	}

}