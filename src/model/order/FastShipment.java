package model.order;

public class FastShipment extends Shipment {

	private int ID;
	private float tax;

	public FastShipment(int iD, float fees, String address, String names, int iD2, float tax) {
		super(iD, fees, address, names);
		ID = iD2;
		this.tax = tax;
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

	public float getTax() {
		return this.tax;
	}

	/**
	 * 
	 * @param tax
	 */
	public void setTax(float tax) {
		this.tax = tax;
	}

}