package model.order;

public class EcoShipment extends Shipment {

	private int ID;
	private String maximumTime;

	public EcoShipment(int iD, float fees, String address, String names, int iD2, String maximumTime) {
		super(iD, fees, address, names);
		ID = iD2;
		this.maximumTime = maximumTime;
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

	public String getMaximumTime() {
		return this.maximumTime;
	}

	/**
	 * 
	 * @param maximumTime
	 */
	public void setMaximumTime(String maximumTime) {
		this.maximumTime = maximumTime;
	}

}