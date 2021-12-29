package model.employee;

import model.user.User;

public class Accountant extends Employee {

	private int ID;
	private int expYears;

	public Accountant(int iD, float salaries, String startedDate, User user, Area area, Branch branch,
			SalaryGrade salaryGrade, Timesheet timesheet, int iD2, int expYears) {
		super(iD, salaries, startedDate, user, area, branch, salaryGrade, timesheet);
		ID = iD2;
		this.expYears = expYears;
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

	public int getExpYears() {
		return this.expYears;
	}

	/**
	 * 
	 * @param expYears
	 */
	public void setExpYears(int expYears) {
		this.expYears = expYears;
	}

}