package model.order;

public class Voucher {

	private int ID;
	private String names;
	private String expiresDate;

	public Voucher(int iD, String names, String expiresDate) {
		super();
		ID = iD;
		this.names = names;
		this.expiresDate = expiresDate;
	}
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public String getNames() {
		return names;
	}
	public void setNames(String names) {
		this.names = names;
	}
	public String getExpiresDate() {
		return expiresDate;
	}
	public void setExpiresDate(String expiresDate) {
		this.expiresDate = expiresDate;
	}

}