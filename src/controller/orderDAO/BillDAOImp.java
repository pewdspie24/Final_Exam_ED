package controller.orderDAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import controller.employeeDAO.EmployeeDAOImp;
import model.employee.Employee;
import model.order.Bill;
import model.order.Order;

public class BillDAOImp implements BillDAO {

	private String jdbcURL = "jdbc:mysql://localhost:3306/pttk_final?allowPublicKeyRetrieval=true&useSSL=false";
	private String jdbcUsername = "root";
	private String jdbcPassword = "123456";
	
	private static final String SELECT_BILL_BY_ID = "select * from bill where orderid =?";
    private static final String SELECT_ALL_BILLS = "select * from bill";
    private static final String INSERT_NEW_BILL = "INSERT INTO bill" + "  (employeeID, orderID, totalPrice, dateCreated, totalDiscount) VALUES " +" (?, ?, ?, ?, ?);";
    private static final String SELECT_MAX_ID = "SELECT MAX(id) FROM bill where orderId=?;";
	
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
	
	public int getMaxID(int customerID){
		int id = 0;
		OrderDAOImp order = new OrderDAOImp();
		CartDAOImp cart = new CartDAOImp();
		int orderID = order.getOrderByCartID(cart.getCart(customerID).getID()).getID();
		try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(SELECT_MAX_ID)) {
			preparedStatement.setInt(1, orderID);
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
	
	public void addBill(Bill bill) {
		try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(INSERT_NEW_BILL)) {
        	preparedStatement.setInt(1, bill.getEmployee().getID());
            preparedStatement.setInt(2, bill.getOrder().getID());
            preparedStatement.setFloat(3, bill.getTotalPrice());
            preparedStatement.setString(4, bill.getDateCreated());
            preparedStatement.setFloat(5, bill.getTotalDiscount());
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
	}
	
	public Bill getBill(int ID) {
		Bill bill = null;
        // Step 1: Establishing a Connection
        try (Connection connection = getConnection();
            // Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BILL_BY_ID);) {
            preparedStatement.setInt(1, ID);
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
            	int orderID = rs.getInt("orderID");
                int employeeID = rs.getInt("employeeID");
                float totalPrice = rs.getFloat("totalPrice");
                String dateCreated = rs.getString("DateCreate");
                float totalDiscount = rs.getFloat("totalDiscount");
                EmployeeDAOImp empdao = new EmployeeDAOImp();
                OrderDAOImp orderdao = new OrderDAOImp();
                Employee employee = empdao.getEmployee(employeeID);
                Order order = orderdao.getOrder(orderID);
                bill = new Bill(ID, totalPrice, dateCreated, totalDiscount, order, employee);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bill;
	}

	public List <Bill> showAllBill() {
		List <Bill> bill = new ArrayList<>();
		// Step 1: Establishing a Connection
		 try (Connection connection = getConnection();
            // Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_BILLS);) {
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
            	int ID = rs.getInt("ID");
            	int orderID = rs.getInt("orderID");
                int employeeID = rs.getInt("employeeID");
                float totalPrice = rs.getFloat("totalPrice");
                String dateCreated = rs.getString("DateCreate");
                float totalDiscount = rs.getFloat("totalDiscount");
                EmployeeDAOImp empdao = new EmployeeDAOImp();
                OrderDAOImp orderdao = new OrderDAOImp();
                Employee employee = empdao.getEmployee(employeeID);
                Order order = orderdao.getOrder(orderID);
                bill.add(new Bill(ID, totalPrice, dateCreated, totalDiscount, order, employee));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bill;
	}

}