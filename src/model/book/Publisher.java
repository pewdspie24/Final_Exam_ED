package model.book;

public class Publisher {

	private int ID;
	private String address;
	private String name;
	private int operateYears;

	
	
	public Publisher(int iD, String address, String name, int operateYears) {
		super();
		ID = iD;
		this.address = address;
		this.name = name;
		this.operateYears = operateYears;
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

	public String getName() {
		return this.name;
	}

	/**
	 * 
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	public int getOperateYears() {
		return this.operateYears;
	}

	/**
	 * 
	 * @param operateYears
	 */
	public void setOperateYears(int operateYears) {
		this.operateYears = operateYears;
	}

}