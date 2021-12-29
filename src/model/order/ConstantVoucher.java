package model.order;

public class ConstantVoucher extends Voucher {

	private int ID;
	private float quantity;

	public ConstantVoucher(int iD, String names, String expiresDate, int iD2, float quantity) {
		super(iD, names, expiresDate);
		ID = iD2;
		this.quantity = quantity;
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
	
	public float getQuantity() {
		return this.quantity;
	}

	/**
	 * 
	 * @param quantity
	 */
	public void setQuantity(float quantity) {
		this.quantity = quantity;
	}

}