package controller.bookDAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.book.Author;
import model.book.Book;
import model.book.Bookitem;
import model.book.LightNovel;
import model.book.Publisher;
import model.employee.Area;
import model.employee.Branch;
import model.employee.Employee;
import model.employee.SalaryGrade;
import model.employee.Timesheet;
import model.user.Account;
import model.user.Address;
import model.user.Birth;
import model.user.Fullname;
import model.user.Phone;
import model.user.User;

public class BookitemDAOImp implements BookitemDAO {

	private String jdbcURL = "jdbc:mysql://localhost:3306/pttk_final?allowPublicKeyRetrieval=true&useSSL=false";
	private String jdbcUsername = "root";
	private String jdbcPassword = "123456";
	
	private static final String INSERT_NEW_BITEM = "insert into bookitem " + "(EmployeeID, BookID, Price, Discount, UploadDate, Instock) values "+" (?, ?, ?, ?, ?, ?);";
	private static final String SELECT_ALL_CMITEM_ID = "SELECT * FROM bookitem WHERE BookID IN (SELECT ID FROM book WHERE IDCom is NOT NULL); ";
    private static final String SELECT_ALL_TXITEM_ID = "SELECT * FROM bookitem WHERE BookID IN (SELECT ID FROM book WHERE IDText is NOT NULL); ";
    private static final String SELECT_ALL_LNITEM_ID = "SELECT * FROM bookitem WHERE BookID IN (SELECT ID FROM book WHERE IDLN is NOT NULL); ";
    private static final String UPDATE_BITEM_BY_ID = "UPDATE bookitem SET Price = ?, Discount = ?, instock= ? WHERE ID = ?;";
    private static final String SUBTRACT_BITEM_BY_ID = "UPDATE bookitem SET instock= ? WHERE ID = ?;";
    private static final String SELECT_BITEM_BY_ID = "select * from bookitem where id =?;";
    private static final String SELECT_BOOK_BY_ID = "select * from book where id =?;";
    private static final String SELECT_NUM_BY_ID = "select instock from bookitem where ID = ?";
    private static final String DELETE_BITEM = "delete from bookitem where id = ?;";
    private static final String SELECT_PUB_BY_ID = "select * from publisher where id =?";
	private static final String SELECT_AUT_BY_ID = "select * from author where id =?";
	private static final String SELECT_EMP_BY_ID = "select * from employee where id =?";
	private static final String SELECT_BRA_BY_ID = "select * from branch where id =?";
	private static final String SELECT_ARE_BY_ID = "select * from area where id =?";
	private static final String SELECT_USR_BY_ID = "SELECT * from user where id = ?;";
	private static final String SELECT_ACC_BY_ID = "SELECT * from account where id = ?;";
	private static final String SELECT_ADD_BY_ID = "SELECT * from address where id = ?;";
	private static final String SELECT_NAME_BY_ID = "SELECT * from fullname where id = ?;";
	private static final String SELECT_BIRTH_BY_ID = "SELECT * from birth where id = ?;";
	private static final String SELECT_PHONE_BY_ID = "SELECT * from phone where id = ?;";
	private static final String SELECT_TMS_BY_ID = "select * from timesheet where id =?";
	private static final String SELECT_GRD_BY_ID = "select * from salarygrade where id =?";
	
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
	
	public int getStock(int ID) {
        int instock = 0;
        try (Connection connection = getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(SELECT_NUM_BY_ID)) {
            preparedStatement.setInt(1, ID);
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                instock = rs.getInt("instock");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return instock;
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
	
	public Publisher getPublisher(int ID) {
		Publisher pub = null;
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();
				// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PUB_BY_ID);) {
			preparedStatement.setInt(1, ID);
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				int iD = rs.getInt("ID");
				String address = rs.getString("address");
				String name = rs.getString("name");
				int operateYears = rs.getInt("operateYears");
				pub = new Publisher(iD, address, name, operateYears);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pub;
	}
	
	public Author getAuthor(int ID) {
		Author aut = null;
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();
				// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_AUT_BY_ID);) {
			preparedStatement.setInt(1, ID);
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				int iD = rs.getInt("ID");
				String name = rs.getString("name");
				String shortBio = rs.getString("shortBio");
				String birth = rs.getString("birth");
				aut = new Author(iD, name, shortBio, birth);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return aut;
	}
	
	public Book getBookByID(int ISBN){
		Book book = null;
        // Step 1: Establishing a Connection
        try (Connection connection = getConnection();

                // Step 2:Create a statement using connection object
                PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BOOK_BY_ID);) {
            preparedStatement.setInt(1, ISBN);
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
            	int employeeID = rs.getInt("employeeID");
            	Employee employee = getEmployee(employeeID);
                int publisherID = rs.getInt("PublisherID");
                Publisher publisher = getPublisher(publisherID);
                int authorID = rs.getInt("authorID");
				Author author = getAuthor(authorID);
                String title = rs.getString("title");
                String summary = rs.getString("summary");
                int years = rs.getInt("years");
                book = new Book(ISBN, title, summary, years, employee, author, publisher);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return book;
	}
	
	public void addBookitem(Bookitem book) {
		try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(INSERT_NEW_BITEM)) {
        	preparedStatement.setInt(1, book.getID());
        	preparedStatement.setFloat(2, book.getPrice());
            preparedStatement.setFloat(3, book.getDiscount());
            preparedStatement.setString(4, book.getUploadDate());
            preparedStatement.setInt(5, book.getInStock());
            preparedStatement.setInt(6, book.getBook().getISBN());
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
	}

	public boolean delBookitem(int id) {
		boolean rowDeleted = false;
        try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(DELETE_BITEM);) {
            statement.setInt(1, id);
            rowDeleted = statement.executeUpdate() > 0;
        } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return rowDeleted;
	}
	
	public void subtractBookItem(int ID, int numbers) {
        // TODO - implement BookItemDAOImp.updateBookItem
        int prevNum = getStock(ID);
        // try-with-resource statement will auto close the connection.
        try (Connection connection = getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(SUBTRACT_BITEM_BY_ID)) {
            preparedStatement.setInt(1, prevNum - numbers);
            preparedStatement.setInt(2, ID);
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
	
	public void editBookItem(int id, float price, float discount, int inStock) {
		try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_BITEM_BY_ID)) {
			preparedStatement.setFloat(1, price);
        	preparedStatement.setFloat(2, discount);
            preparedStatement.setInt(3, inStock);
            preparedStatement.setInt(4, id);
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
	}
	
	public List <Bookitem> getAllLNitem() {
		List<Bookitem> books = new ArrayList<>();
        // Step 1: Establishing a Connection
        try (Connection connection = getConnection();

                // Step 2:Create a statement using connection object
                PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_LNITEM_ID);) {
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                int ID = rs.getInt("ID");
                int employeeID = rs.getInt("employeeID");
            	Employee employee = getEmployee(employeeID);
                int BookID = rs.getInt("BookID");
                Book book = getBookByID(BookID);
                int inStock = rs.getInt("inStock");
                float price = rs.getFloat("price");
                float discount = rs.getFloat("discount");
                String uploadDate = rs.getString("uploadDate");
                books.add(new Bookitem(ID, book, employee, price, discount, uploadDate, inStock));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
	}
	
	public List <Bookitem> getAllCMitem() {
		List<Bookitem> books = new ArrayList<>();
        // Step 1: Establishing a Connection
        try (Connection connection = getConnection();

                // Step 2:Create a statement using connection object
                PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_CMITEM_ID);) {
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                int ID = rs.getInt("ID");
                int employeeID = rs.getInt("employeeID");
            	Employee employee = getEmployee(employeeID);
                int BookID = rs.getInt("BookID");
                Book book = getBookByID(BookID);
                int inStock = rs.getInt("inStock");
                float price = rs.getFloat("price");
                float discount = rs.getFloat("discount");
                String uploadDate = rs.getString("uploadDate");
                books.add(new Bookitem(ID, book, employee, price, discount, uploadDate, inStock));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
	}

	public List <Bookitem> getAllTXitem() {
		List<Bookitem> books = new ArrayList<>();
        // Step 1: Establishing a Connection
        try (Connection connection = getConnection();

                // Step 2:Create a statement using connection object
                PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_TXITEM_ID);) {
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                int ID = rs.getInt("ID");
                int employeeID = rs.getInt("employeeID");
            	Employee employee = getEmployee(employeeID);
                int BookID = rs.getInt("BookID");
                Book book = getBookByID(BookID);
                int inStock = rs.getInt("inStock");
                float price = rs.getFloat("price");
                float discount = rs.getFloat("discount");
                String uploadDate = rs.getString("uploadDate");
                books.add(new Bookitem(ID, book, employee, price, discount, uploadDate, inStock));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
	}
	
	public Bookitem getBookitem(int ID){
		Bookitem bookitem = null;
        // Step 1: Establishing a Connection
        try (Connection connection = getConnection();

                // Step 2:Create a statement using connection object
                PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BITEM_BY_ID);) {
            preparedStatement.setInt(1, ID);
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
            	int employeeID = rs.getInt("employeeID");
            	Employee employee = getEmployee(employeeID);
                int bookID = rs.getInt("bookID");
				Book book = getBookByID(bookID);
				int inStock = rs.getInt("inStock");
                float price = rs.getFloat("price");
                float discount = rs.getFloat("discount");
                String uploadDate = rs.getString("uploadDate");
                bookitem = new Bookitem(ID, book, employee, price, discount, uploadDate, inStock);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bookitem;
	}
}