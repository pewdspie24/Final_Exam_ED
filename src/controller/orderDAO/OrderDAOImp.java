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
import model.order.COD;
import model.order.Cart;
import model.order.ConstantVoucher;
import model.order.CreditCard;
import model.order.EcoShipment;
import model.order.FastShipment;
import model.order.InternetBanking;
import model.order.Order;
import model.order.Payment;
import model.order.PercentageVoucher;
import model.order.SafeShipment;
import model.order.Shipment;
import model.order.Voucher;

public class OrderDAOImp implements OrderDAO {

	private String jdbcURL = "jdbc:mysql://localhost:3306/pttk_final?allowPublicKeyRetrieval=true&useSSL=false";
	private String jdbcUsername = "root";
	private String jdbcPassword = "123456";
	
	private static final String SELECT_MAX_ID = "SELECT MAX(id) FROM `order`;";
	private static final String INSERT_NEW_PAY_COD = "insert into payment " + "(fees, IDCOD, currency, IDInter, AccountNumber, Pwd, IDCredit, CardNumber, Type) values "+" (?, SELECT MAX(idCOD) FROM `payment`+1, ?, NULL, NULL, NULL, NULL, NULL, NULL);";
	private static final String INSERT_NEW_PAY_INT = "insert into payment " + "(fees, IDInter, AccountNumber, Pwd, IDCOD, currency, IDCredit, CardNumber, Type) values "+" (?, SELECT MAX(idInter) FROM `payment`+1, ?, ?, NULL, NULL, NULL, NULL, NULL);";
	private static final String INSERT_NEW_PAY_CRE = "insert into payment " + "(fees, IDCredit, CardNumber, Type, IDCOD, currency, IDInter, AccountNumber, Pwd) values "+" (?, SELECT MAX(idCredit) FROM `payment`+1, ?, ?, NULL, NULL, NULL, NULL, NULL);";
	private static final String INSERT_NEW_SHIP_ECO = "insert into shipment " + "(fees, address, IDEco, MaximumTime, IDSafe, Insurance, IDFast, Tax) values "+" (?, ?, SELECT MAX(idEco) FROM `shipment`+1, ?, NULL, NULL, NULL, NULL);";
	private static final String INSERT_NEW_SHIP_SAF = "insert into shipment " + "(fees, address, IDSafe, Insurance, IDEco, MaximumTime, IDFast, Tax) values "+" (?, ?, SELECT MAX(idSafe) FROM `shipment`+1, ?, NULL, NULL, NULL, NULL);";
	private static final String INSERT_NEW_SHIP_FAS = "insert into shipment " + "(fees, address, IDFast, Tax, IDEco, MaximumTime, IDSafe, Insurance) values "+" (?, ?, SELECT MAX(idFast) FROM `shipment`+1, ?, NULL, NULL, NULL, NULL);";
	private static final String INSERT_NEW_VOU_CON = "insert into voucher " + "(name, expiresdate, IDCons, Quantity) values "+" (?, ?, SELECT MAX(idCons) FROM `voucher`+1, ?, NULL, NULL);";
	private static final String INSERT_NEW_VOU_PER = "insert into voucher " + "(name, expiresdate, IDPer, Percentage) values "+" (?, ?, SELECT MAX(idPer) FROM `voucher`+1, ?, NULL, NULL);";
	private static final String INSERT_NEW_ORDER = "insert into `order` " + "(cartID, shipmentID, voucherID, paymentID, employeeID, Date) values "+" (?, ?, ?, ?, ?, ?);";
	private static final String SELECT_ORDER_BY_ID = "select * from `order` where id =?";
	private static final String SELECT_ORDER_BY_CRT_ID = "select * from `order` where Cartid =?";
	private static final String SELECT_ALL_PAY = "select * from payment;";
	private static final String SELECT_ALL_SHI= "select * from shipment;";
	private static final String SELECT_ALL_VOU = "select * from voucher;";
	private static final String SELECT_ALL_PAY_COD = "select * from payment where IDCOD is not null;";
	private static final String SELECT_ALL_PAY_INT = "select * from payment where IDInter is not null;";
	private static final String SELECT_ALL_PAY_CRE = "select * from payment where IDCredit is not null;";
	private static final String SELECT_ALL_SHIP_ECO = "select * from shipment where IDEco is not null;";
	private static final String SELECT_ALL_SHIP_SAF = "select * from shipment where IDSafe is not null;";
	private static final String SELECT_ALL_SHIP_FAS = "select * from shipment where IDFast is not null;";
	private static final String SELECT_ALL_VOU_CON = "select * from voucher where IDEco is not null;";
	private static final String SELECT_ALL_VOU_PER = "select * from voucher where IDSafe is not null;";
	private static final String CHECK_TYPES_PAY_COD = "select * from payment where ID =? and IDCOD is not null;";
	private static final String CHECK_TYPES_PAY_INT = "select * from payment where ID =? and IDInter is not null;";
	private static final String CHECK_TYPES_PAY_CRE = "select * from payment where ID =? and IDCredit is not null;";
	private static final String CHECK_TYPES_SHIP_ECO = "select * from shipment where ID =? and IDEco is not null;";
	private static final String CHECK_TYPES_SHIP_SAF = "select * from shipment where ID =? and IDSafe is not null;";
	private static final String CHECK_TYPES_SHIP_FAS = "select * from shipment where ID =? and IDFast is not null;";
	private static final String CHECK_TYPES_VOU_CON = "select * from voucher where ID =? and IDCons is not null;";
	private static final String CHECK_TYPES_VOU_PER = "select * from voucher where ID =? and IDPer is not null;";
	private static final String SELECT_PAY_BY_ID = "select * from payment where ID =?;";
	private static final String SELECT_SHIP_BY_ID = "select * from shipment where ID =?;";
	private static final String SELECT_VOU_BY_ID = "select * from voucher where ID =?;";
	
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
	
	public int getMaxID(){
		int id = 0;
		try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(SELECT_MAX_ID)) {
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
	
	public void addPayment() {
		// TODO - implement OrderDAOImp.addPayment
		throw new UnsupportedOperationException();
	}

	public void addShipment() {
		// TODO - implement OrderDAOImp.addShipment
		throw new UnsupportedOperationException();
	}

	public void addVoucher() {
		// TODO - implement OrderDAOImp.addVoucher
		throw new UnsupportedOperationException();
	}
	
	public int getPaymentType(int ID) {
		int flag = 0;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(CHECK_TYPES_PAY_COD);) {
			statement.setInt(1, ID);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				flag = 1;
				return flag;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(CHECK_TYPES_PAY_INT);) {
			statement.setInt(1, ID);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				flag = 2;
				return flag;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(CHECK_TYPES_PAY_CRE);) {
			statement.setInt(1, ID);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				flag = 3;
				return flag;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;
	}
	
	public int getShipmentType(int ID) {
		int flag = 0;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(CHECK_TYPES_SHIP_ECO);) {
			statement.setInt(1, ID);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				flag = 1;
				return flag;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(CHECK_TYPES_SHIP_SAF);) {
			statement.setInt(1, ID);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				flag = 2;
				return flag;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(CHECK_TYPES_SHIP_FAS);) {
			statement.setInt(1, ID);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				flag = 3;
				return flag;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;
	}
	
	public int getVoucherType(int ID) {
		int flag = 0;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(CHECK_TYPES_VOU_CON);) {
			statement.setInt(1, ID);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				flag = 1;
				return flag;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(CHECK_TYPES_VOU_PER);) {
			statement.setInt(1, ID);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				flag = 2;
				return flag;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;
	}
	
	public Payment getPaymentByID(int ID) {
		Payment pay = null;
        // Step 1: Establishing a Connection
        try (Connection connection = getConnection();
            // Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PAY_BY_ID);) {
            preparedStatement.setInt(1, ID);
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
            	int iD = rs.getInt("ID");
                float fees = rs.getFloat("fees");
                String names = rs.getString("names");
                pay = new Payment(iD, fees, names);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pay;
	}
	
	public COD getCODPayment(int ID){
		COD pay = null;
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();
				// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PAY_BY_ID);) {
			preparedStatement.setInt(1, ID);
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				int iD = rs.getInt("IDCOD");
				float fees = rs.getFloat("fees");
				String currency = rs.getString("currency");
				String names = rs.getString("names");
				pay = new COD(ID, fees, names, iD, currency);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pay;
	}
	
	public InternetBanking getIBPayment(int ID){
		InternetBanking pay = null;
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();
				// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PAY_BY_ID);) {
			preparedStatement.setInt(1, ID);
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				int iD = rs.getInt("IDInter");
				float fees = rs.getFloat("fees");
				String accountNumber = rs.getString("accountNumber");
				String pwd = rs.getString("pwd");
				String names = rs.getString("names");
				pay = new InternetBanking(ID, fees, names, iD, accountNumber, pwd);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pay;
	}
	
	public CreditCard getCreditPayment(int ID){
		CreditCard pay = null;
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();
				// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PAY_BY_ID);) {
			preparedStatement.setInt(1, ID);
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				int iD = rs.getInt("IDCredit");
				float fees = rs.getFloat("fees");
				String cardNumber = rs.getString("cardNumber");
				String type = rs.getString("type");
				String names = rs.getString("names");
				pay = new CreditCard(ID, fees, names, iD, cardNumber, type);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pay;
	}
	
	public Shipment getShipmentByID(int ID) {
		Shipment ship = null;
        // Step 1: Establishing a Connection
        try (Connection connection = getConnection();
            // Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_SHIP_BY_ID);) {
            preparedStatement.setInt(1, ID);
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
            	int iD = rs.getInt("ID");
                float fees = rs.getFloat("fees");
                String address = rs.getString("address");
                String names = rs.getString("names");
                ship = new Shipment(iD, fees, address, names);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ship;
	}
	
	public EcoShipment getEcoShipment(int ID){
		EcoShipment ship = null;
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();
				// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_SHIP_BY_ID);) {
			preparedStatement.setInt(1, ID);
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				int iD2 = rs.getInt("IDEco");
				float fees = rs.getFloat("fees");
				String address = rs.getString("address");
				String maximumTime = rs.getString("maximumTime");
				String names = rs.getString("names");
				ship = new EcoShipment(ID, fees, address, names, iD2, maximumTime);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ship;
	}
	
	public FastShipment getFastShipment(int ID){
		FastShipment ship = null;
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();
				// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_SHIP_BY_ID);) {
			preparedStatement.setInt(1, ID);
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				int iD2 = rs.getInt("IDFast");
				float fees = rs.getFloat("fees");
				String address = rs.getString("address");
				float tax = rs.getFloat("tax");
				String names = rs.getString("names");
				ship = new FastShipment(ID, fees, address, names, iD2, tax);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ship;
	}
	
	public SafeShipment getSafeShipment(int ID){
		SafeShipment ship = null;
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();
				// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_SHIP_BY_ID);) {
			preparedStatement.setInt(1, ID);
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				int iD2 = rs.getInt("IDSafe");
				float fees = rs.getFloat("fees");
				String address = rs.getString("address");
				String insurance = rs.getString("insurance");
				String names = rs.getString("names");
				ship = new SafeShipment(ID, fees, address, names, iD2, insurance);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ship;
	}
	
	public Voucher getVoucherByID(int ID) {
		Voucher vou = null;
        // Step 1: Establishing a Connection
        try (Connection connection = getConnection();
            // Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_VOU_BY_ID);) {
            preparedStatement.setInt(1, ID);
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery(); 

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
            	int iD = rs.getInt("ID");
            	String names = rs.getString("names");
                String expiresDate = rs.getString("expiresDate");
                vou = new Voucher(iD, names, expiresDate);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vou;
	}
	
	public ConstantVoucher getConstantVoucher(int ID){
		ConstantVoucher vou = null;
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();
				// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_VOU_BY_ID);) {
			preparedStatement.setInt(1, ID);
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				int iD2 = rs.getInt("IDCons");
				String names = rs.getString("names");
				String expiresDate = rs.getString("expiresDate");
				float quantity = rs.getFloat("quantity");
				vou = new ConstantVoucher(ID, names, expiresDate, iD2, quantity);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return vou;
	}
	
	public PercentageVoucher getPercentageVoucher(int ID){
		PercentageVoucher vou = null;
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();
				// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_VOU_BY_ID);) {
			preparedStatement.setInt(1, ID);
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				int iD2 = rs.getInt("IDPer");
				String names = rs.getString("names");
				String expiresDate = rs.getString("expiresDate");
				int percentage = rs.getInt("percentage");
				vou = new PercentageVoucher(ID, names, expiresDate, iD2, percentage);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return vou;
	}
	
	public List <COD> getAllCOD() {
		List < COD > pays = new ArrayList < > ();
        // Step 1: Establishing a Connection
        try (Connection connection = getConnection();

            // Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_PAY_COD);) {
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
            	int ID = rs.getInt("ID");
            	int iD = rs.getInt("IDCOD");
				float fees = rs.getFloat("fees");
				String currency = rs.getString("currency");
				String names = rs.getString("names");
				pays.add(new COD(ID, fees, names, iD, currency));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pays;
	}
	
	public List <InternetBanking> getAllIB() {
		List < InternetBanking > pays = new ArrayList < > ();
        // Step 1: Establishing a Connection
        try (Connection connection = getConnection();

            // Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_PAY_INT);) {
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
            	int ID = rs.getInt("ID");
            	int iD = rs.getInt("IDInter");
				float fees = rs.getFloat("fees");
				String accountNumber = rs.getString("accountNumber");
				String pwd = rs.getString("pwd");
				String names = rs.getString("names");
				pays.add(new InternetBanking(ID, fees, names, iD, accountNumber, pwd));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pays;
	}
	
	public List <CreditCard> getAllCredit() {
		List < CreditCard > pays = new ArrayList < > ();
        // Step 1: Establishing a Connection
        try (Connection connection = getConnection();

            // Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_PAY_CRE);) {
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
            	int ID = rs.getInt("ID");
            	int iD = rs.getInt("IDCredit");
				float fees = rs.getFloat("fees");
				String cardNumber = rs.getString("cardNumber");
				String type = rs.getString("type");
				String names = rs.getString("names");
				pays.add(new CreditCard(ID, fees, names, iD, cardNumber, type));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pays;
	}
	
	public List <EcoShipment> getAllEco() {
		List < EcoShipment > ships = new ArrayList < > ();
        // Step 1: Establishing a Connection
        try (Connection connection = getConnection();

            // Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_SHIP_ECO);) {
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
            	int ID = rs.getInt("ID");
            	int iD2 = rs.getInt("IDEco");
				float fees = rs.getFloat("fees");
				String address = rs.getString("address");
				String maximumTime = rs.getString("maximumTime");
				String names = rs.getString("names");
				ships.add(new EcoShipment(ID, fees, address, names, iD2, maximumTime));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ships;
	}
	
	public List <FastShipment> getAllFast() {
		List < FastShipment > ships = new ArrayList < > ();
        // Step 1: Establishing a Connection
        try (Connection connection = getConnection();

            // Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_SHIP_FAS);) {
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
            	int ID = rs.getInt("ID");
            	int iD2 = rs.getInt("IDFast");
				float fees = rs.getFloat("fees");
				String address = rs.getString("address");
				float tax = rs.getFloat("tax");
				String names = rs.getString("names");
				ships.add(new FastShipment(ID, fees, address, names, iD2, tax));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ships;
	}
	
	public List <SafeShipment> getAllSafe() {
		List < SafeShipment > ships = new ArrayList < > ();
        // Step 1: Establishing a Connection
        try (Connection connection = getConnection();

            // Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_SHIP_SAF);) {
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
            	int ID = rs.getInt("ID");
            	int iD2 = rs.getInt("IDSafe");
				float fees = rs.getFloat("fees");
				String address = rs.getString("address");
				String insurance = rs.getString("insurance");
				String names = rs.getString("names");
				ships.add(new SafeShipment(ID, fees, address, names, iD2, insurance));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ships;
	}
	
	public List <ConstantVoucher> getAllCons() {
		List < ConstantVoucher > vous = new ArrayList < > ();
        // Step 1: Establishing a Connection
        try (Connection connection = getConnection();

            // Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_VOU_CON);) {
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
            	int ID = rs.getInt("ID");
            	int iD2 = rs.getInt("IDCons");
				String names = rs.getString("names");
				String expiresDate = rs.getString("expiresDate");
				float quantity = rs.getFloat("quantity");
				vous.add(new ConstantVoucher(ID, names, expiresDate, iD2, quantity));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vous;
	}
	
	public List <PercentageVoucher> getAllPer() {
		List < PercentageVoucher > vous = new ArrayList < > ();
        // Step 1: Establishing a Connection
        try (Connection connection = getConnection();

            // Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_VOU_PER);) {
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
            	int ID = rs.getInt("ID");
            	int iD2 = rs.getInt("IDPer");
				String names = rs.getString("names");
				String expiresDate = rs.getString("expiresDate");
				int percentage = rs.getInt("percentage");
				vous.add(new PercentageVoucher(ID, names, expiresDate, iD2, percentage));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vous;
	}

	public void addOrder(Order ord) {
		try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(INSERT_NEW_ORDER)) {
        	preparedStatement.setInt(1, ord.getCart().getID());
        	preparedStatement.setInt(2, ord.getShipment().getID());
        	preparedStatement.setInt(3, ord.getVoucher().getID());
            preparedStatement.setInt(4, ord.getPayment().getID());
            preparedStatement.setInt(5, ord.getEmployee().getID());
            preparedStatement.setString(6, ord.getDate());
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
	}
	
	public Order getOrder(int ID) {
		Order order = null;
        // Step 1: Establishing a Connection
        try (Connection connection = getConnection();
            // Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ORDER_BY_ID);) {
            preparedStatement.setInt(1, ID);
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                int cartID = rs.getInt("CartID");
                int shipmentID = rs.getInt("ShipmentID");
                int voucherID = rs.getInt("VoucherID");
                int paymentID = rs.getInt("PaymentID");
                int employeeID = rs.getInt("employeeID");
                String date = rs.getString("Date");
                EmployeeDAOImp empdao = new EmployeeDAOImp();
                CartDAOImp cartdao = new CartDAOImp();
                Employee employee = empdao.getEmployee(employeeID);
                Cart cart = cartdao.getCartByCartID(cartID);
                Payment payment = getPaymentByID(paymentID);
                Shipment shipment = getShipmentByID(shipmentID);
                Voucher voucher = getVoucherByID(voucherID);
                order = new Order(ID, date, cart, payment, shipment, voucher, employee);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return order;
	}

	public void createBill() {
		// TODO - implement OrderDAOImp.createBill
		throw new UnsupportedOperationException();
	}
	
	public Order getOrderByCartID(int cartID) {
		Order order = null;
        // Step 1: Establishing a Connection
        try (Connection connection = getConnection();
            // Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ORDER_BY_CRT_ID);) {
            preparedStatement.setInt(1, cartID);
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
            	int ID = rs.getInt("ID");
                int shipmentID = rs.getInt("ShipmentID");
                int voucherID = rs.getInt("VoucherID");
                int paymentID = rs.getInt("PaymentID");
                int employeeID = rs.getInt("employeeID");
                String date = rs.getString("Date");
                EmployeeDAOImp empdao = new EmployeeDAOImp();
                CartDAOImp cartdao = new CartDAOImp();
                Employee employee = empdao.getEmployee(employeeID);
                Cart cart = cartdao.getCartByCartID(cartID);
                Payment payment = getPaymentByID(paymentID);
                Shipment shipment = getShipmentByID(shipmentID);
                Voucher voucher = getVoucherByID(voucherID);
                order = new Order(ID, date, cart, payment, shipment, voucher, employee);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return order;
	}

	public List <Order> showAllOrder(int customerID) {
		List <Order> order = new ArrayList<>();
		// Step 1: Establishing a Connection
		CartDAOImp cartDAO = new CartDAOImp();
		List <Cart> cartList = cartDAO.getAllCart(customerID);
		for (Cart cart : cartList){
			int cartID = cart.getID();
			order.add(getOrderByCartID(cartID));
		}
        return order;
	}

	public List<Payment> getAllPayment() {
		List<Payment> pay = new ArrayList<>();
        // Step 1: Establishing a Connection
        try (Connection connection = getConnection();
            // Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_PAY);) {
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
            	int iD = rs.getInt("ID");
                float fees = rs.getFloat("fees");
                String names = rs.getString("names");
                pay.add(new Payment(iD, fees, names));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pay;
	}
	
	public List<Shipment> getAllShipment() {
		List<Shipment> ship = new ArrayList<>();
        // Step 1: Establishing a Connection
        try (Connection connection = getConnection();
            // Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_SHI);) {
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
            	int iD = rs.getInt("ID");
                float fees = rs.getFloat("fees");
                String address = rs.getString("address");
                String names = rs.getString("names");
                ship.add(new Shipment(iD, fees, address, names));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ship;
	}
	
	public List<Voucher> getAllVoucher() {
		List<Voucher> vou = new ArrayList<>();
        // Step 1: Establishing a Connection
        try (Connection connection = getConnection();
            // Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_VOU);) {
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
            	int iD = rs.getInt("ID");
            	String names = rs.getString("names");
                String expiresDate = rs.getString("expiresDate");
                vou.add(new Voucher(iD, names, expiresDate));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vou;
	}
}