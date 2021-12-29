package model.employee;

import model.user.User;

public class SystemManager extends Employee {

	public SystemManager(int iD, float salaries, String startedDate, User user, Area area, Branch branch,
			SalaryGrade salaryGrade, Timesheet timesheet, int iD2, int workDays) {
		super(iD, salaries, startedDate, user, area, branch, salaryGrade, timesheet);
		ID = iD2;
		this.workDays = workDays;
	}

	private int ID;
	private int workDays;

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

	public int getWorkDays() {
		return this.workDays;
	}

	/**
	 * 
	 * @param workDays
	 */
	public void setWorkDays(int workDays) {
		this.workDays = workDays;
	}

}