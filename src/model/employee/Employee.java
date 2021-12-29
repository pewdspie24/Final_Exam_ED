package model.employee;

import model.user.User;

public class Employee {

	private int ID;
	private float salaries;
	private String startedDate;
	private User user;
	private Area area;
	private Branch branch;
	private SalaryGrade salaryGrade;
	private Timesheet timesheet;

	public Employee(int iD, float salaries, String startedDate, User user, Area area, Branch branch,
			SalaryGrade salaryGrade, Timesheet timesheet) {
		super();
		ID = iD;
		this.salaries = salaries;
		this.startedDate = startedDate;
		this.user = user;
		this.area = area;
		this.branch = branch;
		this.salaryGrade = salaryGrade;
		this.timesheet = timesheet;
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

	public float getSalaries() {
		return this.salaries;
	}

	/**
	 * 
	 * @param salaries
	 */
	public void setSalaries(float salaries) {
		this.salaries = salaries;
	}

	public String getStartedDate() {
		return this.startedDate;
	}

	/**
	 * 
	 * @param startedDate
	 */
	public void setStartedDate(String startedDate) {
		this.startedDate = startedDate;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Area getArea() {
		return this.area;
	}

	/**
	 * 
	 * @param area
	 */
	public void setArea(Area area) {
		this.area = area;
	}

	public Branch getBranch() {
		return this.branch;
	}

	/**
	 * 
	 * @param branch
	 */
	public void setBranch(Branch branch) {
		this.branch = branch;
	}

	public SalaryGrade getSalaryGrade() {
		return salaryGrade;
	}

	public void setSalaryGrade(SalaryGrade salaryGrade) {
		this.salaryGrade = salaryGrade;
	}

	public Timesheet getTimesheet() {
		return timesheet;
	}

	public void setTimesheet(Timesheet timesheet) {
		this.timesheet = timesheet;
	}

}