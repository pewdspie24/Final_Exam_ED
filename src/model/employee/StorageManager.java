package model.employee;

import model.user.User;

public class StorageManager extends Employee {

	public StorageManager(int iD, float salaries, String startedDate, User user, Area area, Branch branch,
			SalaryGrade salaryGrade, Timesheet timesheet, int iD2, String experience) {
		super(iD, salaries, startedDate, user, area, branch, salaryGrade, timesheet);
		ID = iD2;
		this.experience = experience;
	}

	private int ID;
	private String experience;

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

	public String getExperience() {
		return this.experience;
	}

	/**
	 * 
	 * @param experience
	 */
	public void setExperience(String experience) {
		this.experience = experience;
	}

}