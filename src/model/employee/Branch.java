package model.employee;

public class Branch {

	private int ID;
	private String name;
	private int numWorkers;
	private String location;

	public Branch(int iD, String name, int numWorkers, String location) {
		super();
		ID = iD;
		this.name = name;
		this.numWorkers = numWorkers;
		this.location = location;
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

	public int getNumWorkers() {
		return this.numWorkers;
	}

	/**
	 * 
	 * @param numWorkers
	 */
	public void setNumWorkers(int numWorkers) {
		this.numWorkers = numWorkers;
	}

	public String getLocation() {
		return this.location;
	}

	/**
	 * 
	 * @param location
	 */
	public void setLocation(String location) {
		this.location = location;
	}

}