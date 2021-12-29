package controller.employeeDAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.customer.Customer;
import model.employee.Area;
import model.employee.Branch;
import model.employee.Employee;
import model.employee.SalaryGrade;
import model.employee.Timesheet;
import model.reportNoti.Notification;
import model.reportNoti.Report;
import model.user.Account;
import model.user.Address;
import model.user.Birth;
import model.user.Fullname;
import model.user.Phone;
import model.user.User;

public class EmployeeDAOImp implements EmployeeDAO {
	
	private String jdbcURL = "jdbc:mysql://localhost:3306/pttk_final?allowPublicKeyRetrieval=true&useSSL=false";
	private String jdbcUsername = "root";
	private String jdbcPassword = "123456";
	
	private static final String INSERT_NEW_EMP = "insert into employee " + "(salaries, startedDate, userID, areaID, branchID, salaryGradeID, timesheetID) values "+" (?, ?, ?, ?, ?, ?, ?);";
	private static final String UPDATE_EMP_BY_ID = "UPDATE employee SET" + "salaries = ?, startedDate =? WHERE ID = ?;";
	private static final String INSERT_NEW_ARE = "insert into area " + "(square, location) values "+" (?, ?);";
	private static final String INSERT_NEW_BRA = "insert into branch " + "(name, numWorkers, location) values "+" (?, ?, ?);";
	private static final String INSERT_NEW_SAL = "insert into salarygrade " + "(name, multiplier) values "+" (?, ?);";
	private static final String INSERT_NEW_TMS = "insert into timesheet " + "(date, status) values "+" (?, ?);";
	private static final String INSERT_NEW_NOT = "insert into notification " + "(employeeID, customerID, context, timestamp, from) values "+" (?, ?, ?, ?, ?);";
	private static final String SELECT_EMP_BY_ID = "select * from employee where id =?";
	private static final String SELECT_BRA_BY_ID = "select * from branch where id =?";
	private static final String SELECT_ARE_BY_ID = "select * from area where id =?";
	private static final String SELECT_TMS_BY_ID = "select * from timesheet where id =?";
	private static final String SELECT_GRD_BY_ID = "select * from salarygrade where id =?";
	private static final String SELECT_USR_BY_ID = "SELECT * from user where id = ?;";
	private static final String SELECT_ACC_BY_ID = "SELECT * from account where id = ?;";
	private static final String SELECT_ADD_BY_ID = "SELECT * from address where id = ?;";
	private static final String SELECT_NAME_BY_ID = "SELECT * from fullname where id = ?;";
	private static final String SELECT_BIRTH_BY_ID = "SELECT * from birth where id = ?;";
	private static final String SELECT_PHONE_BY_ID = "SELECT * from phone where id = ?;";
	private static final String SELECT_CUS_BY_ID = "SELECT * from customer where id = ?;";
	private static final String SELECT_REP_BY_ID = "SELECT * from report;";
	
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
	
	public void addArea(Area area){
		try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(INSERT_NEW_ARE)) {
            preparedStatement.setFloat(1, area.getSquare());
            preparedStatement.setString(2, area.getLocation());
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
	}
	
	public void addBranch(Branch bra){
		try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(INSERT_NEW_BRA)) {
            preparedStatement.setString(1, bra.getName());
            preparedStatement.setInt(2, bra.getNumWorkers());
            preparedStatement.setString(3, bra.getLocation());
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
	}
	
	public void addSalaryGrade(SalaryGrade sal){
		try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(INSERT_NEW_SAL)) {
			preparedStatement.setString(1, sal.getName());
			preparedStatement.setFloat(2, sal.getMultiplier());
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
	}
	
	public void addTimesheet(Timesheet tms){
		try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(INSERT_NEW_TMS)) {
            preparedStatement.setString(1, tms.getDate());
            preparedStatement.setString(2, tms.getStatus());
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
	}
	
	public Area getArea(int ID) {
		Area area = null;
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();
				// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ARE_BY_ID);) {
			preparedStatement.setInt(1, ID);
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				int iD = rs.getInt("ID");
				float square = rs.getFloat("square");
				String location = rs.getString("location");
				area = new Area(iD, square, location);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return area;
	}
	
	public Branch getBranch(int ID) {
		Branch bra = null;
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();
				// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BRA_BY_ID);) {
			preparedStatement.setInt(1, ID);
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				int iD = rs.getInt("ID");
				String name = rs.getString("name");
				int numWorkers = rs.getInt("numWorkers");
				String location = rs.getString("location");
				bra = new  Branch(iD, name, numWorkers, location);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return bra;
	}
	
	public Timesheet getTimesheet(int ID) {
		Timesheet tms = null;
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();
				// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_TMS_BY_ID);) {
			preparedStatement.setInt(1, ID);
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				int iD = rs.getInt("ID");
				String date = rs.getString("date");
				String status = rs.getString("status");
				tms = new  Timesheet(iD, date, status);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return tms;
	}
	
	public SalaryGrade getSalaryGrade(int ID) {
		SalaryGrade slg = null;
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();
				// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_GRD_BY_ID);) {
			preparedStatement.setInt(1, ID);
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				String name = rs.getString("name");
				Float multiplier = rs.getFloat("multiplier");
				slg = new SalaryGrade(ID, name, multiplier);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return slg;
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
	
	public void addEmployee(Employee employee) {
		try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(INSERT_NEW_EMP)) {
			addSalaryGrade(employee.getSalaryGrade());
			addBranch(employee.getBranch());
			addTimesheet(employee.getTimesheet());
			addArea(employee.getArea());
            preparedStatement.setFloat(1, employee.getSalaries());
            preparedStatement.setString(2, employee.getStartedDate());
            preparedStatement.setInt(3, employee.getUser().getID());
            preparedStatement.setInt(4, employee.getArea().getID());
            preparedStatement.setInt(5, employee.getBranch().getID());
            preparedStatement.setInt(6, employee.getSalaryGrade().getID());
            preparedStatement.setInt(7, employee.getTimesheet().getID());
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
	}

	public void editEmployee(Employee employee) {
		try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_EMP_BY_ID)) {
            preparedStatement.setFloat(1, employee.getSalaries());
            preparedStatement.setString(2, employee.getStartedDate());
            preparedStatement.setInt(3, employee.getID());
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
	}
	
	public Employee getEmployee(int ID) {
		Employee emp = null;
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();
				// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_EMP_BY_ID);) {
			preparedStatement.setInt(1, ID);
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				float salaries = rs.getFloat("salaries");
				String startedDate = rs.getString("startedDate");
				int areaID = rs.getInt("areaID");
				Area area = getArea(areaID);
				int branchID = rs.getInt("branchID");
				Branch branch = getBranch(branchID);
				int userID = rs.getInt("userID");
				User user = getUser(userID);
				int salaryGradeID = rs.getInt("salaryGradeID");
				SalaryGrade salaryGrade = getSalaryGrade(salaryGradeID);
				int timesheetID = rs.getInt("timesheetID");
				Timesheet timesheet = getTimesheet(timesheetID);
				emp = new Employee(ID, salaries, startedDate, user, area, branch, salaryGrade, timesheet);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return emp;
	}

	/**
	 * 
	 * @param report
	 */
	public List<Report> checkReport() {
		List<Report> report = new ArrayList<>();
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();

				// Step 2:Create a statement using connection object
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_REP_BY_ID);) {
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				int ID = rs.getInt("ID");
				String timeStamp = rs.getString("timeStamp");
				String reasons = rs.getString("reasons");
				String nameProduct = rs.getString("nameProduct");
				int employeeID = rs.getInt("employeeID");
				Employee employee = getEmployee(employeeID);
				int customerID = rs.getInt("customerID");
				Customer customer = getCustomer(customerID);
				report.add(new Report(ID, timeStamp, reasons, nameProduct, customer, employee));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return report;
	}

	/**
	 * 
	 * @param report
	 */
	public void deleteReport(Report report) {
		// TODO - implement EmployeeDAOImp.deleteReport
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param notification
	 */
	public void sendNotification(Notification notification) {
		try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(INSERT_NEW_NOT)) {
            preparedStatement.setInt(1, notification.getEmployee().getID());
            preparedStatement.setInt(2, notification.getCustomer().getID());
            preparedStatement.setString(3, notification.getContext());
            preparedStatement.setString(4, notification.getTimeStamp());
            preparedStatement.setString(5, notification.getFrom());
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
	}

}