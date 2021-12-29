package controller.bookDAO;

import java.util.List;

import model.book.Bookitem;

public interface BookitemDAO {

	public void addBookitem(Bookitem book);

	public boolean delBookitem(int id);

	public void subtractBookItem(int ID, int numbers);

	public void editBookItem(int id, float price, float discount, int inStock);
	
	public List <Bookitem> getAllLNitem();
	
	public List <Bookitem> getAllCMitem();
	
	public List <Bookitem> getAllTXitem();
	
	public Bookitem getBookitem(int ID);

}