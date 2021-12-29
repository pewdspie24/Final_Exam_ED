package controller.customerDAO;

import java.util.List;

import model.customer.SearchHistory;

public interface SearchHistoryDAO {

	public boolean delSearch(int id);
	
	public List<SearchHistory> showSearch(int cusID);

}