package controller.userDAO;

import model.user.User;

public interface UserDAO {
	
	public void addUser(User user);
	
	public void editUser(User user);
	
	public boolean delUser(int id);
	
	public boolean checkValidAccount(String username, String password);
	
	public boolean checkExistAccount(String username);
	
	public boolean checkUserRole(int userID);
	
	public User getUser(int ID);
	
	
}