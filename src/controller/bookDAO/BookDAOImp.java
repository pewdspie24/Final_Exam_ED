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
import model.book.Comics;
import model.book.LightNovel;
import model.book.Publisher;
import model.book.TextBook;
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

public class BookDAOImp implements BookDAO {

	private String jdbcURL = "jdbc:mysql://localhost:3306/pttk_final?allowPublicKeyRetrieval=true&useSSL=false";
	private String jdbcUsername = "root";
	private String jdbcPassword = "123456";

	private static final String INSERT_NEW_LN = "insert into book " + "(EmployeeID, PublisherID, AuthorID, Title, Summary, Years, IDLN, TranslateLanguage, VolumeLN, EditionsLN, IDCom, NameSeries, Artists, IDText, NumberOfPages, VolumeText, EditionsText) values "+" (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, NULL, NULL, NULL, NULL, NULL, NULL, NULL);";
	private static final String INSERT_NEW_CM = "insert into book " + "(EmployeeID, PublisherID, AuthorID, Title, Summary, Years, IDLN, TranslateLanguage, VolumeLN, EditionsLN, IDCom, NameSeries, Artists, IDText, NumberOfPages, VolumeText, EditionsText) values "+" (?, ?, ?, ?, ?, ?, NULL, NULL, NULL, NULL, ?, ?, ?, NULL, NULL, NULL, NULL);";
	private static final String INSERT_NEW_TX = "insert into book " + "(EmployeeID, PublisherID, AuthorID, Title, Summary, Years, IDLN, TranslateLanguage, VolumeLN, EditionsLN, IDCom, NameSeries, Artists, IDText, NumberOfPages, VolumeText, EditionsText) values "+" (?, ?, ?, ?, ?, ?, NULL, NULL, NULL, NULL, NULL, NULL, NULL, ?, ?, ?, ?);";
	private static final String SELECT_BOOK_BY_ID = "select * from book where ISBN =?;";
	private static final String CHECK_TYPES_LN = "select * from book where ISBN =? and IDLN is not null;";
	private static final String CHECK_TYPES_CM = "select * from book where ISBN =? and IDCom is not null;";
	private static final String CHECK_TYPES_TX = "select * from book where ISBN =? and IDText is not null;";
	private static final String SELECT_ALL_COMICS = "select * from book where IDCom is not null;";
	private static final String SELECT_ALL_LNS = "select * from book where IDLN is not null;";
	private static final String SELECT_ALL_TEXTS = "select * from book where IDText is not null;";
	private static final String UPDATE_TEXTS = "update book set PublisherID=?, AuthorID=?, Title=?, Summary=?, Years=?, NumberOfPages=?, VolumeText=?, EditionsText=? from book where ISBN=?;";
	private static final String UPDATE_COMICS = "update book set PublisherID=?, AuthorID=?, Title=?, Summary=?, Years=?, NameSeries=?, Artists=?, from book where ISBN=?;";
	private static final String UPDATE_LNS = "update book set PublisherID=?, AuthorID=?, Title=?, Summary=?, Years=?, TranslateLanguage=?, VolumeLN=?, EditionsLN=? from book where ISBN=?;";
	private static final String DELETE_BOOK = "delete from book where ISBN  = ?;";
	private static final String SELECT_PUB_BY_ID = "select * from publisher where id =?";
	private static final String SELECT_AUT_BY_ID = "select * from author where id =?";
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
	
	public int getType(int ID) {
		int flag = 0;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(CHECK_TYPES_LN);) {
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
				PreparedStatement statement = connection.prepareStatement(CHECK_TYPES_CM);) {
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
				PreparedStatement statement = connection.prepareStatement(CHECK_TYPES_TX);) {
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
	
	public void addBookLN(LightNovel book) {
//		System.out.println(INSERT_ACC_SQL);
        // try-with-resource statement will auto close the connection.
        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(INSERT_NEW_LN)) {
        	preparedStatement.setInt(1, book.getISBN());
        	preparedStatement.setInt(2, book.getEmployee().getID());
            preparedStatement.setInt(3, book.getPublisher().getID());
            preparedStatement.setInt(4, book.getAuthor().getID());
            preparedStatement.setString(5, book.getTitle());
        	preparedStatement.setString(6, book.getSummary());
            preparedStatement.setInt(7, book.getYears());
            preparedStatement.setInt(8, book.getID());
            preparedStatement.setString(9, book.getTranslateLanguage());
            preparedStatement.setInt(10, book.getVolume());
            preparedStatement.setInt(11, book.getEditions());
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
	}
	
	public void addBookCM(Comics book) {
//		System.out.println(INSERT_ACC_SQL);
        // try-with-resource statement will auto close the connection.
        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(INSERT_NEW_CM)) {
        	preparedStatement.setInt(1, book.getISBN());
        	preparedStatement.setInt(2, book.getEmployee().getID());
            preparedStatement.setInt(3, book.getPublisher().getID());
            preparedStatement.setInt(4, book.getAuthor().getID());
            preparedStatement.setString(5, book.getTitle());
        	preparedStatement.setString(6, book.getSummary());
            preparedStatement.setInt(7, book.getYears());
            preparedStatement.setInt(8, book.getID());
            preparedStatement.setString(9, book.getNameSeries());
            preparedStatement.setString(10, book.getArtists());
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
	}
	
	public void addBookTX(TextBook book) {
//		System.out.println(INSERT_ACC_SQL);
        // try-with-resource statement will auto close the connection.
        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(INSERT_NEW_TX)) {
        	preparedStatement.setInt(1, book.getISBN());
        	preparedStatement.setInt(2, book.getEmployee().getID());
            preparedStatement.setInt(3, book.getPublisher().getID());
            preparedStatement.setInt(4, book.getAuthor().getID());
            preparedStatement.setString(5, book.getTitle());
        	preparedStatement.setString(6, book.getSummary());
            preparedStatement.setInt(7, book.getYears());
            preparedStatement.setInt(8, book.getID());
            preparedStatement.setInt(9, book.getNumberOfPages());
            preparedStatement.setInt(10, book.getVolume());
            preparedStatement.setInt(11, book.getEditions());
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
	}

	public boolean delBook(int id) {
		boolean rowDeleted = false;
        try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(DELETE_BOOK);) {
            statement.setInt(1, id);
            rowDeleted = statement.executeUpdate() > 0;
        } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return rowDeleted;
	}
	
	public List<Comics> findAllComics() {
		List<Comics> books = new ArrayList<>();
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();

				// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_COMICS);) {
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				int ISBN = rs.getInt("ISBN");
				String title = rs.getString("title");
				String summary = rs.getString("summary");
				int years = rs.getInt("years");
				int employeeID = rs.getInt("employeeID");
				Employee employee = getEmployee(employeeID);
				int authorID = rs.getInt("authorID");
				Author author = getAuthor(authorID);
				int publisherID = rs.getInt("PublisherID");
				Publisher publisher = getPublisher(publisherID);
				int IDLN = rs.getInt("IDCM");
				String nameSeries = rs.getString("nameSeries");
				String artists = rs.getString("artists");
				Comics book = new Comics(ISBN, title, summary, years, employee, author, publisher, IDLN, nameSeries, artists);
				books.add(book);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return books;
	}

	public List<LightNovel> findAllLightNovel() {
		List<LightNovel> books = new ArrayList<>();
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();

				// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_LNS);) {
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				int ISBN = rs.getInt("ISBN");
				String title = rs.getString("title");
				String summary = rs.getString("summary");
				int years = rs.getInt("years");
				int employeeID = rs.getInt("employeeID");
				Employee employee = getEmployee(employeeID);
				int authorID = rs.getInt("authorID");
				Author author = getAuthor(authorID);
				int publisherID = rs.getInt("PublisherID");
				Publisher publisher = getPublisher(publisherID);
				int iD = rs.getInt("IDLN");
				String translateLanguage = rs.getString("translateLanguage");
				int volume = rs.getInt("volumeLN");
				int editions = rs.getInt("editionsLN");
				books.add(new LightNovel(ISBN, title, summary, years, employee, author, publisher, iD, translateLanguage, volume, editions));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return books;
	}

	public List<TextBook> findAllTextBook() {
		List<TextBook> books = new ArrayList<>();
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();

				// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_TEXTS);) {
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				int ISBN = rs.getInt("ISBN");
				String title = rs.getString("title");
				String summary = rs.getString("summary");
				int years = rs.getInt("years");
				int employeeID = rs.getInt("employeeID");
				Employee employee = getEmployee(employeeID);
				int authorID = rs.getInt("authorID");
				Author author = getAuthor(authorID);
				int publisherID = rs.getInt("PublisherID");
				Publisher publisher = getPublisher(publisherID);
				int iD = rs.getInt("IDLN");
				int numberOfPages = rs.getInt("numberOfPages");
				int volume = rs.getInt("volumeText");
				int editions = rs.getInt("editionsText");
				books.add(new TextBook(ISBN, title, summary, years, employee, author, publisher, iD, numberOfPages, volume, editions));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return books;
	}

	public void editLNBook(LightNovel book) {
		try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_LNS)) {
			preparedStatement.setInt(1, book.getISBN());
        	preparedStatement.setInt(2, book.getEmployee().getID());
            preparedStatement.setInt(3, book.getPublisher().getID());
            preparedStatement.setInt(4, book.getAuthor().getID());
            preparedStatement.setString(5, book.getTitle());
        	preparedStatement.setString(6, book.getSummary());
            preparedStatement.setInt(7, book.getYears());
            preparedStatement.setInt(8, book.getID());
            preparedStatement.setString(9, book.getTranslateLanguage());
            preparedStatement.setInt(10, book.getVolume());
            preparedStatement.setInt(11, book.getEditions());
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
	}
	
	public void editCMBook(Comics book) {
		try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_COMICS)) {
			preparedStatement.setInt(1, book.getISBN());
        	preparedStatement.setInt(2, book.getEmployee().getID());
            preparedStatement.setInt(3, book.getPublisher().getID());
            preparedStatement.setInt(4, book.getAuthor().getID());
            preparedStatement.setString(5, book.getTitle());
        	preparedStatement.setString(6, book.getSummary());
            preparedStatement.setInt(7, book.getYears());
            preparedStatement.setInt(8, book.getID());
            preparedStatement.setString(9, book.getNameSeries());
            preparedStatement.setString(10, book.getArtists());
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
	}
	
	public void editTXBook(TextBook book) {
		try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_TEXTS)) {
			preparedStatement.setInt(1, book.getISBN());
        	preparedStatement.setInt(2, book.getEmployee().getID());
            preparedStatement.setInt(3, book.getPublisher().getID());
            preparedStatement.setInt(4, book.getAuthor().getID());
            preparedStatement.setString(5, book.getTitle());
        	preparedStatement.setString(6, book.getSummary());
            preparedStatement.setInt(7, book.getYears());
            preparedStatement.setInt(8, book.getID());
            preparedStatement.setInt(9, book.getNumberOfPages());
            preparedStatement.setInt(10, book.getVolume());
            preparedStatement.setInt(11, book.getEditions());
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
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

	public LightNovel getLNByID(int ISBN){
		LightNovel book = null;
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
                int iD = rs.getInt("IDLN");
                String translateLanguage = rs.getString("translateLanguage");
                int volume = rs.getInt("volume");
                int editions = rs.getInt("editions");
                book = new LightNovel(ISBN, title, summary, years, employee, author, publisher, iD, translateLanguage, volume, editions);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return book;
	}
	
	public Comics getCMByID(int ISBN){
		Comics book = null;
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
                int iD = rs.getInt("IDCom");
                String nameSeries = rs.getString("nameSeries");
                String artists = rs.getString("artists");
                book = new Comics(ISBN, title, summary, years, employee, author, publisher, iD, nameSeries, artists);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return book;
	}
	
	public TextBook getTXByID(int ISBN){
		TextBook book = null;
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
                int iD = rs.getInt("IDText");
                int volume = rs.getInt("volume");
                int editions = rs.getInt("editions");
                int numberOfPages = rs.getInt("numberOfPages");
                book = new TextBook(ISBN, title, summary, years, employee, author, publisher, iD, numberOfPages, volume, editions);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return book;
	}
	
}