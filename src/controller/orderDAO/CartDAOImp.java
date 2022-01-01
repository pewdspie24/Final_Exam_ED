package controller.orderDAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import model.book.Bookitem;
import model.customer.Customer;
import model.order.Cart;
import model.user.Account;
import model.user.Address;
import model.user.Birth;
import model.user.Fullname;
import model.user.Phone;
import model.user.User;

public class CartDAOImp implements CartDAO {
	
	private String jdbcURL = "jdbc:mysql://localhost:3306/pttk_final?allowPublicKeyRetrieval=true&useSSL=false";
	private String jdbcUsername = "root";
	private String jdbcPassword = "123456";
	
	private static final String INSERT_CART_SQL = "INSERT INTO cart" + "  (customerID, createdAt, updatedAt, totalQuantity, totalPrice) VALUES " + " (?, ?, ?, ?, ?);";
    private static final String UPDATE_CART_BY_ID = "UPDATE cart SET totalPrice= ?, totalQuantity = ? WHERE ID = ?;";
    private static final String SELECT_CART_BY_ID = "select *  from cart where customerID =? and id=(select MAX(id) from cart);";
    private static final String SELECT_CART_BY_CID = "select *  from cart where ID =? ;";
    private static final String SELECT_ALL_CART_BY_ID = "select * from cart where customerID =? ;";
    private static final String INSERT_BITEM_CART = "INSERT INTO bookitem_cart" + "  (BookitemID, cartID, quantity) VALUES " + " (?, ?, ?);";
    private static final String SELECT_BITEM_CART = "SELECT * FROM bookitem_cart where cartID =?;";
    private static final String UPDATE_BITEM_CART = "UPDATE bookitem_cart SET quantity = quantity+? WHERE cartID = ? AND bookitemID = ?;";
    private static final String CHECK_BITEM_CART = "SELECT quantity FROM bookitem_cart where cartID = ? AND bookitemID = ?;";
    private static final String SELECT_CUS_BY_ID = "SELECT * from customer where id = ?;";
	private static final String SELECT_USR_BY_ID = "SELECT * from user where id = ?;";
	private static final String SELECT_ACC_BY_ID = "SELECT * from account where id = ?;";
	private static final String SELECT_ADD_BY_ID = "SELECT * from address where id = ?;";
	private static final String SELECT_NAME_BY_ID = "SELECT * from fullname where id = ?;";
	private static final String SELECT_BIRTH_BY_ID = "SELECT * from birth where id = ?;";
	private static final String SELECT_PHONE_BY_ID = "SELECT * from phone where id = ?;";
	private static final String DELETE_BITEM = "delete from bookitem_cart where Bookitemid = ? and CartId = ?;";
    
    protected Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return connection;
    }
    
    public Account getAccount(int ID) {
		Account acc = null;
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();
				// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ACC_BY_ID);) {
			preparedStatement.setInt(1, ID);
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				String username = rs.getString("username");
				String password = rs.getString("password");
				acc = new Account(ID, username, password);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return acc;
	}
	
	public Address getAddress(int ID) {
		Address add = null;
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();
				// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ADD_BY_ID);) {
			preparedStatement.setInt(1, ID);
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				String city = rs.getString("city");
				String district = rs.getString("district");
				String houseno = rs.getString("houseno");
				add = new Address(ID, city, district, houseno);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return add;
	}
	
	public Fullname getName(int ID) {
		Fullname fn = null;
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();
				// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_NAME_BY_ID);) {
			preparedStatement.setInt(1, ID);
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				String firstname = rs.getString("firstname");
				String lastname = rs.getString("lastname");
				fn = new Fullname(ID, firstname, lastname);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return fn;
	}
	
	public Birth getBirth(int ID) {
		Birth birth = null;
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();
				// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BIRTH_BY_ID);) {
			preparedStatement.setInt(1, ID);
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				String day = rs.getString("day");
				String month = rs.getString("month");
				String years = rs.getString("years");
				birth = new Birth(ID, day, month, years);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return birth;
	}
	
	public Phone getPhone(int ID) {
		Phone phone = null;
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();
				// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PHONE_BY_ID);) {
			preparedStatement.setInt(1, ID);
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				String phoneNum = rs.getString("phoneNum");
				String type = rs.getString("type");
				phone = new Phone(ID, phoneNum, type);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return phone;
	}
	
	public User getUser(int ID){
		User user = null;
        // Step 1: Establishing a Connection
        try (Connection connection = getConnection();

                // Step 2:Create a statement using connection object
                PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USR_BY_ID);) {
            preparedStatement.setInt(1, ID);
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
            	int accountID = rs.getInt("accountID");
            	Account account = getAccount(accountID);
                int phoneID = rs.getInt("phoneID");
                Phone phone = getPhone(phoneID);
				int fullnameID = rs.getInt("fullnameID");
				Fullname fullname = getName(fullnameID);
                int birthID = rs.getInt("birthID");
				Birth birth = getBirth(birthID);
				int addressID = rs.getInt("addressID");
            	Address address = getAddress(addressID);
                String gender = rs.getString("gender");
                user = new User(ID, gender, account, fullname, phone, birth, address);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
	}
	
	public Customer getCustomer(int ID){
		Customer customer = null;
        // Step 1: Establishing a Connection
        try (Connection connection = getConnection();

                // Step 2:Create a statement using connection object
                PreparedStatement preparedStatement = connection.prepareStatement(SELECT_CUS_BY_ID);) {
            preparedStatement.setInt(1, ID);
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
            	int userID = rs.getInt("userID");
            	User user = getUser(userID);
                String accountNum = rs.getString("accountNum");
                customer = new Customer(ID, accountNum, user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customer;
	}
    
    public void addBookitem(Cart cart, Bookitem book, int quantity) {
        // TODO - implement CartDAOImp.addBookitem
        try (Connection connection = getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(INSERT_BITEM_CART)) {
            preparedStatement.setInt(1, book.getID());
            preparedStatement.setInt(2, cart.getID());
            preparedStatement.setInt(3, quantity);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void updateBookitem(Cart cart, Bookitem book, int increase) {
        // TODO - implement CartDAOImp.addBookitem
        try (Connection connection = getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_BITEM_CART)) {
        	preparedStatement.setInt(1, increase);
            preparedStatement.setInt(3, book.getID());
            preparedStatement.setInt(2, cart.getID());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public int checkBookitem(Cart cart, Bookitem book) {
        // Step 1: Establishing a Connection
    	int quantity = 0;
        try (Connection connection = getConnection();
                // Step 2:Create a statement using connection object
                PreparedStatement preparedStatement = connection.prepareStatement(CHECK_BITEM_CART);) {
            preparedStatement.setInt(1, cart.getID());
            preparedStatement.setInt(2, book.getID());
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                quantity = rs.getInt("quantity");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return quantity;
    }
    
    public HashMap<Integer, Integer> getBookitemIDList(int cartID) {
        HashMap<Integer, Integer> bitem = new HashMap<Integer, Integer>();
        // Step 1: Establishing a Connection
        try (Connection connection = getConnection();
                // Step 2:Create a statement using connection object
                PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BITEM_CART);) {
            preparedStatement.setInt(1, cartID);
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                int bookitemID = rs.getInt("bookitemID");
                int quantity = rs.getInt("quantity");
                bitem.put(bookitemID, quantity);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bitem;
    }
    
    public void createCart(Cart cart) {
        System.out.println(INSERT_CART_SQL);
        // try-with-resource statement will auto close the connection.
        try (Connection connection = getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(INSERT_CART_SQL)) {
            preparedStatement.setInt(1, cart.getCustomer().getID());
            preparedStatement.setString(2, cart.getCreatedAt());
            preparedStatement.setString(3, cart.getUpdatedAt());
            preparedStatement.setInt(4, cart.getTotalQuantity());
            preparedStatement.setFloat(5, cart.getTotalPrice());
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
	public Cart getCart(int customerID) {
		Cart cart = null;
        // Step 1: Establishing a Connection
        try (Connection connection = getConnection();
                // Step 2:Create a statement using connection object
                PreparedStatement preparedStatement = connection.prepareStatement(SELECT_CART_BY_ID);) {
            preparedStatement.setInt(1, customerID);
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                int ID = rs.getInt("ID");
                 String createdAt = rs.getString("createdAt");
                 String updatedAt= rs.getString("updatedAt");
                 Float totalPrice = rs.getFloat("totalPrice");
                 int totalQuantity = rs.getInt("totalQuantity");
                Customer customer = getCustomer(customerID);
                cart = new Cart(ID, customer, createdAt, updatedAt, totalQuantity, totalPrice);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cart;
	}
	
	public Cart getCartByCartID(int ID) {
		Cart cart = null;
        // Step 1: Establishing a Connection
        try (Connection connection = getConnection();
                // Step 2:Create a statement using connection object
                PreparedStatement preparedStatement = connection.prepareStatement(SELECT_CART_BY_CID);) {
            preparedStatement.setInt(1, ID);
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                 String createdAt = rs.getString("createdAt");
                 String updatedAt= rs.getString("updatedAt");
                 Float totalPrice = rs.getFloat("totalPrice");
                 int totalQuantity = rs.getInt("totalQuantity");
                 int customerID = rs.getInt("customerID");
                Customer customer = getCustomer(customerID);
                cart = new Cart(ID, customer, createdAt, updatedAt, totalQuantity, totalPrice);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cart;
	}

	public void editCart(Cart cart, float price, int quantity) {
		System.out.println(UPDATE_CART_BY_ID);
        // try-with-resource statement will auto close the connection.
        try (Connection connection = getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_CART_BY_ID)) {
            preparedStatement.setFloat(1, price);
            preparedStatement.setInt(2, quantity);
            preparedStatement.setInt(3, cart.getID());
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
	}
	
	public List <Cart> getAllCart(int customerID) {
		List <Cart> cart = new ArrayList<>();
        // Step 1: Establishing a Connection
        try (Connection connection = getConnection();
                // Step 2:Create a statement using connection object
                PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_CART_BY_ID);) {
        	preparedStatement.setInt(1, customerID);
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
            	int ID = rs.getInt("ID");
                 String createdAt = rs.getString("createdAt");
                 String updatedAt= rs.getString("updatedAt");
                 Float totalPrice = rs.getFloat("totalPrice");
                 int totalQuantity = rs.getInt("totalQuantity");
                Customer customer = getCustomer(customerID);
                cart.add(new Cart(ID, customer, createdAt, updatedAt, totalQuantity, totalPrice));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cart;
	}
	
	public boolean delCartBitem(int bookitemID, int cartID) {
		boolean rowDeleted = false;
        try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(DELETE_BITEM);) {
            statement.setInt(1, bookitemID);
            statement.setInt(2, cartID);
            rowDeleted = statement.executeUpdate() > 0;
        } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return rowDeleted;
	}
}