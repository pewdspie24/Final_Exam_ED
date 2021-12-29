package controller.orderDAO;

import java.util.List;

import model.order.Order;
import model.order.Payment;
import model.order.Shipment;
import model.order.Voucher;

public interface OrderDAO {

	void addPayment();

	void addShipment();

	void addVoucher();
	
	public int getPaymentType(int ID);
	
	public int getShipmentType(int ID);
	
	public int getVoucherType(int ID);
	
	public Payment getPaymentByID(int ID);
	
	public Shipment getShipmentByID(int ID);
	
	public Voucher getVoucherByID(int ID);

	public void addOrder(Order ord);
	
	public Order getOrder(int ID);

	void createBill();
	
	public Order getOrderByCartID(int cartID);

	public List <Order> showAllOrder(int customerID);

}