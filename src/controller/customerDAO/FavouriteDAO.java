package controller.customerDAO;

import java.util.List;

import model.customer.Favourite;

public interface FavouriteDAO {

	public void addFavourite(Favourite favourite);

	public boolean delFavourite(int id);

	public List<Favourite> showFavourite(int cusID);

}