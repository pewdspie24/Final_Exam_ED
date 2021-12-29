package controller.orderDAO;

import java.util.HashMap;

import model.book.Bookitem;
import model.order.Cart;

public interface CartDAO {

	public void createCart(Cart cart);
	
	public Cart getCart(int customerID);

	public void editCart(Cart cart, float price, int quantity);

	/**
	 * 
	 * @param bookitem
	 */
	public void addBookitem(Cart cart, Bookitem book, int quantity);
	
	public void updateBookitem(Cart cart, Bookitem book, int increase);
	
	public int checkBookitem(Cart cart, Bookitem book);
	
	public HashMap<Integer, Integer> getBookitemIDList(int cartID);
	
	

}