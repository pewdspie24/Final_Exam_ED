package controller.employeeDAO;

import java.util.List;

import model.employee.Employee;
import model.reportNoti.Notification;
import model.reportNoti.Report;

public interface EmployeeDAO {

	public void addEmployee(Employee employee);

	public void editEmployee(Employee employee);
	
	public Employee getEmployee(int ID);

	/**
	 * 
	 * @param report
	 */
	public List<Report> checkReport();

	/**
	 * 
	 * @param report
	 */
	void deleteReport(Report report);

	/**
	 * 
	 * @param notification
	 */
	public void sendNotification(Notification notification);

}