package model.employee;

public class Area {

	private int ID;
	private float square;
	private String location;

	public Area(int iD, float square, String location) {
		super();
		ID = iD;
		this.square = square;
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

	public float getSquare() {
		return this.square;
	}

	/**
	 * 
	 * @param square
	 */
	public void setSquare(float square) {
		this.square = square;
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