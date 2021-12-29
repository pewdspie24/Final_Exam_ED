package model.employee;

public class SalaryGrade {

	private int ID;
	private String name;
	private float multiplier;

	public SalaryGrade(int iD, String name, float multiplier) {
		super();
		ID = iD;
		this.name = name;
		this.multiplier = multiplier;
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

	public float getMultiplier() {
		return this.multiplier;
	}

	/**
	 * 
	 * @param multiplier
	 */
	public void setMultiplier(float multiplier) {
		this.multiplier = multiplier;
	}

}