package model.order;

public class SafeShipment extends Shipment {

	private int ID;
	private String insurance;

	public SafeShipment(int iD, float fees, String address, String names, int iD2, String insurance) {
		super(iD, fees, address, names);
		ID = iD2;
		this.insurance = insurance;
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

	public String getInsurance() {
		return this.insurance;
	}

	/**
	 * 
	 * @param insurance
	 */
	public void setInsurance(String insurance) {
		this.insurance = insurance;
	}

}