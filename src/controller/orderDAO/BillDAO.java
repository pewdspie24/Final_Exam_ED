package controller.orderDAO;

import java.util.List;

import model.order.Bill;

public interface BillDAO {

	public Bill getBill(int orderID);

	public List <Bill> showAllBill();
	
	public void addBill(Bill bill);
	
	public int getMaxID(int customerID);

}