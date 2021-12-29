package controller.customerDAO;

import java.util.List;

import model.customer.Customer;
import model.reportNoti.Notification;
import model.reportNoti.Report;

public interface CustomerDAO {

	public void addCustomer(Customer customer);

	public void editCustomer(Customer customer, String accountNum);

	/**
	 * 
	 * @param report
	 */
	
	public Customer getCustomer(int ID);
	
	public boolean deleteCustomer(int id);
	
	public void addReport(Report report);

	/**
	 * 
	 * @param report
	 */
	public boolean deleteReport(int id);

	/**
	 * 
	 * @param notification
	 */
	
	public List<Report> showReport(int customerID);
	
	public List<Notification> showNotification(int customerID);

}