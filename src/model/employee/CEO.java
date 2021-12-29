package model.employee;

import model.user.User;

public class CEO extends Employee {

	public CEO(int iD, float salaries, String startedDate, User user, Area area, Branch branch, SalaryGrade salaryGrade,
			Timesheet timesheet, int iD2, String profession) {
		super(iD, salaries, startedDate, user, area, branch, salaryGrade, timesheet);
		ID = iD2;
		this.profession = profession;
	}

	private int ID;
	private String profession;

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

	public String getProfession() {
		return this.profession;
	}

	/**
	 * 
	 * @param profession
	 */
	public void setProfession(String profession) {
		this.profession = profession;
	}

}