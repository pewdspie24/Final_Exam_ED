package controller.userDAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.user.Account;
import model.user.Address;
import model.user.Birth;
import model.user.Fullname;
import model.user.Phone;
import model.user.User;

public class UserDAOImp implements UserDAO {
	
	private String jdbcURL = "jdbc:mysql://localhost:3306/pttk_final?allowPublicKeyRetrieval=true&useSSL=false";
	private String jdbcUsername = "root";
	private String jdbcPassword = "123456";
	
	private static final String INSERT_NEW_USR = "insert into user" + "(AccountID, FullnameID, PhoneID, BirthID, AddressID, Gender) values "+" (?, ?, ?, ?, ?, ?);";
	private static final String INSERT_NEW_ACC = "insert into account" + "(username, password) values "+" (?, ?);";
	private static final String INSERT_NEW_NAME = "insert into fullname" + "(firstname, lastname) values "+" (?, ?);";
	private static final String INSERT_NEW_PHONE = "insert into phone" + "(phonenum, type) values "+" (?, ?);";
	private static final String INSERT_NEW_BIRTH = "insert into birth" + "(day, month, years) values "+" (?, ?, ?);";
	private static final String INSERT_NEW_ADD = "insert into address" + "(city, district, houseno) values "+" (?, ?, ?);";
	private static final String UPDATE_USR_BY_ID = "UPDATE user SET" + "Gender =? WHERE ID = ?;";
	private static final String UPDATE_ACC_BY_ID = "UPDATE account SET" + "username= ?, password= ? WHERE ID = ?;";
	private static final String UPDATE_NAME_BY_ID = "UPDATE fullname SET" + "firstname = ?, lastname = ? WHERE ID = ?;";
	private static final String UPDATE_PHONE_BY_ID = "UPDATE phone SET" + "phonenum = ?, type = ? WHERE ID = ?;";
	private static final String UPDATE_BIRTH_BY_ID = "UPDATE birth SET" + "day = ?, month = ?, years = ? WHERE ID = ?;";
	private static final String UPDATE_ADD_BY_ID = "UPDATE address SET" + "city = ?, district = ?, houseno = ? WHERE ID = ?;";
	private static final String DELETE_USR_BY_ID = "delete from user where id = ?;";
	private static final String SELECT_USR_BY_AID = "SELECT * from user where accountid = ?;";
	private static final String SELECT_USR_BY_ID = "SELECT * from user where id = ?;";
	private static final String SELECT_ACC_BY_ID = "SELECT * from account where id = ?;";
	private static final String SELECT_ADD_BY_ID = "SELECT * from address where id = ?;";
	private static final String SELECT_NAME_BY_ID = "SELECT * from fullname where id = ?;";
	private static final String SELECT_BIRTH_BY_ID = "SELECT * from birth where id = ?;";
	private static final String SELECT_PHONE_BY_ID = "SELECT * from phone where id = ?;";
	private static final String VALIDATE_ACC_BY_USR_PWD = "select * from account where username = ? and password = ?;";
	private static final String CHECK_ACC_EXIST = "select * from account where username = ?";
	private static final String CHECK_ROLE = "select * from employee where userID = ?";
	private static final String SELECT_MAX_ID_ACC = "SELECT MAX(id) FROM account;";
	private static final String SELECT_MAX_ID_ADD = "SELECT MAX(id) FROM address;";
	private static final String SELECT_MAX_ID_BIR = "SELECT MAX(id) FROM birth;";
	private static final String SELECT_MAX_ID_FN = "SELECT MAX(id) FROM fullname;";
	private static final String SELECT_MAX_ID_PHO = "SELECT MAX(id) FROM phone;";
	
	
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
	
	public int getMaxIDAcc(){
		int id = 0;
		try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(SELECT_MAX_ID_ACC)) {
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
            	id = rs.getInt("MAX(id)");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
		return id;
	}
	
	public int getMaxIDAdd(){
		int id = 0;
		try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(SELECT_MAX_ID_ADD)) {
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
            	id = rs.getInt("MAX(id)");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
		return id;
	}
	
	public int getMaxIDBirth(){
		int id = 0;
		try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(SELECT_MAX_ID_BIR)) {
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
            	id = rs.getInt("MAX(id)");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
		return id;
	}
	
	public int getMaxIDFullName(){
		int id = 0;
		try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(SELECT_MAX_ID_FN)) {
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
            	id = rs.getInt("MAX(id)");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
		return id;
	}
	
	public int getMaxIDPhone(){
		int id = 0;
		try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(SELECT_MAX_ID_PHO)) {
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
            	id = rs.getInt("MAX(id)");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
		return id;
	}
	
	public void addAcc(Account acc){
		try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(INSERT_NEW_ACC)) {
            preparedStatement.setString(1, acc.getUsername());
            preparedStatement.setString(2, acc.getPassword());
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
	}
	
	public void updateAcc(Account acc, int ID){
		try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_ACC_BY_ID)) {
            preparedStatement.setString(1, acc.getUsername());
            preparedStatement.setString(2, acc.getPassword());
            preparedStatement.setInt(3, ID);
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
	}
	
	public void addFN(Fullname fn){
		try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(INSERT_NEW_NAME)) {
            preparedStatement.setString(1, fn.getFirstname());
            preparedStatement.setString(2, fn.getLastname());
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
	}
	
	public void updateFN(Fullname fn, int ID){
		try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_NAME_BY_ID)) {
            preparedStatement.setString(1, fn.getFirstname());
            preparedStatement.setString(2, fn.getLastname());
            preparedStatement.setInt(3, ID);
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
	}
	
	public void addPhone(Phone phone){
		try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(INSERT_NEW_PHONE)) {
            preparedStatement.setString(1, phone.getPhoneNum());
            preparedStatement.setString(2, phone.getType());
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
	}
	
	public void updatePhone(Phone phone, int ID){
		try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_PHONE_BY_ID)) {
            preparedStatement.setString(1, phone.getPhoneNum());
            preparedStatement.setString(2, phone.getType());
            preparedStatement.setInt(3, ID);
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
	}
	
	public void addBirth(Birth birth){
		try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(INSERT_NEW_BIRTH)) {
            preparedStatement.setString(1, birth.getDay());
            preparedStatement.setString(2, birth.getMonth());
            preparedStatement.setString(3, birth.getYears());
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
	}
	
	public void updateBirth(Birth birth, int ID){
		try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_BIRTH_BY_ID)) {
            preparedStatement.setString(1, birth.getDay());
            preparedStatement.setString(2, birth.getMonth());
            preparedStatement.setString(3, birth.getYears());
            preparedStatement.setInt(4, ID);
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
	}
	
	public void addAddress(Address address){
		try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(INSERT_NEW_ADD)) {
            preparedStatement.setString(1, address.getCity());
            preparedStatement.setString(2, address.getDistrict());
            preparedStatement.setString(3, address.getHouseno());
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
	}
	
	public void updateAddress(Address address, int ID){
		try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_ADD_BY_ID)) {
            preparedStatement.setString(1, address.getCity());
            preparedStatement.setString(2, address.getDistrict());
            preparedStatement.setString(3, address.getHouseno());
            preparedStatement.setInt(4, ID);
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
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
	
	public void addUser(User user) {
		try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(INSERT_NEW_USR)) {
			addAcc(user.getAccount());
			addFN(user.getFullname());
			addPhone(user.getPhone());
			addBirth(user.getBirth());
			addAddress(user.getAddress());
            preparedStatement.setInt(1, getMaxIDAcc());
            preparedStatement.setInt(2, getMaxIDFullName());
            preparedStatement.setInt(3, getMaxIDPhone());
            preparedStatement.setInt(4, getMaxIDBirth());
            preparedStatement.setInt(5, getMaxIDAdd());
            preparedStatement.setString(6, user.getGender());
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
	}

	public void editUser(User user) {
		try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USR_BY_ID)) {
			updateAcc(user.getAccount(), user.getAccount().getID());
			updateAddress(user.getAddress(), user.getAccount().getID());
			updateBirth(user.getBirth(), user.getAccount().getID());
			updateFN(user.getFullname(), user.getAccount().getID());
			updatePhone(user.getPhone(), user.getAccount().getID());
            preparedStatement.setString(1, user.getGender());
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
		
	}

	public boolean delUser(int id) {
		boolean rowDeleted = false;
        try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(DELETE_USR_BY_ID);) {
            statement.setInt(1, id);
            rowDeleted = statement.executeUpdate() > 0;
        } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return rowDeleted;
	}

	public boolean checkValidAccount(String username, String password) {
		boolean status = false;
		try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(VALIDATE_ACC_BY_USR_PWD)) {
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            status = rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
		return status;
	}
	
	public int getAccountID(String username, String password) {
		int status = -1;
		try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(VALIDATE_ACC_BY_USR_PWD)) {
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
            	status = rs.getInt("ID");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
		return status;
	}
	
	public boolean checkExistAccount(String username) {
		boolean status = false;
		try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(CHECK_ACC_EXIST)) {
            preparedStatement.setString(1, username);
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            status = rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
		return status;
	}
	
	public boolean checkUserRole(int userID) {
		boolean status = false;
		try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(CHECK_ROLE)) {
            preparedStatement.setInt(1, userID);
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            status = rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
		return status;
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
	
	public User getUserByAccountID(int accountID){
		User user = null;
        // Step 1: Establishing a Connection
        try (Connection connection = getConnection();

                // Step 2:Create a statement using connection object
                PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USR_BY_AID);) {
            preparedStatement.setInt(1, accountID);
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
            	int ID = rs.getInt("ID");
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
}