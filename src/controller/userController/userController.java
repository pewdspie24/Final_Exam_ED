package controller.userController;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.bookDAO.BookDAOImp;
import controller.bookDAO.BookitemDAOImp;
import controller.customerDAO.CustomerDAOImp;
import controller.customerDAO.FavouriteDAOImp;
import controller.customerDAO.SearchHistoryDAOImp;
import controller.employeeDAO.EmployeeDAOImp;
import controller.orderDAO.BillDAOImp;
import controller.orderDAO.CartDAOImp;
import controller.orderDAO.OrderDAOImp;
import controller.userDAO.UserDAOImp;
import model.book.Author;
import model.book.Book;
import model.book.Bookitem;
import model.book.Comics;
import model.book.LightNovel;
import model.book.Publisher;
import model.book.TextBook;
import model.customer.Customer;
import model.employee.Employee;
import model.order.Bill;
import model.order.Cart;
import model.order.ConstantVoucher;
import model.order.Order;
import model.order.Payment;
import model.order.PercentageVoucher;
import model.order.Shipment;
import model.order.Voucher;
import model.user.Account;
import model.user.Address;
import model.user.Birth;
import model.user.Fullname;
import model.user.Phone;
import model.user.User;

@WebServlet(urlPatterns = { "/", "" })
public class userController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private boolean isLogin = false;
	private BookDAOImp bookDAO;
	private BookitemDAOImp bookitemDAO;
	private CustomerDAOImp customerDAO;
	private FavouriteDAOImp favouriteDAO;
	private SearchHistoryDAOImp searchDAO;
	private BillDAOImp billDAO;
	private CartDAOImp cartDAO;
	private OrderDAOImp orderDAO;
	private UserDAOImp userDAO;
	private EmployeeDAOImp employeeDAO;
	private int cusID;
	private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	private SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy");
	
	/**
     * @see HttpServlet#HttpServlet()
     */
	
//	public void init() {
//        this.isLogin = isLogin;
//        this.cusID = cusID;
//        
//    }
	
    public userController() {
    	bookDAO = new BookDAOImp();
    	userDAO = new UserDAOImp();
    	employeeDAO = new EmployeeDAOImp();
		customerDAO = new CustomerDAOImp();
		bookitemDAO = new BookitemDAOImp();
		favouriteDAO = new FavouriteDAOImp();
		searchDAO = new SearchHistoryDAOImp();
		cartDAO = new CartDAOImp();
		orderDAO = new OrderDAOImp();
		billDAO = new BillDAOImp();
	}

    private Cookie getCookie(HttpServletRequest request, String name) {
		Cookie[] cookies = request.getCookies();
		System.out.println(cookies);
		if(cookies != null) {
			for(Cookie cookie : cookies) {
				if(cookie.getName().equals(name)) {
					return cookie;
				}
			}
		}
		return null;
	}
    
    private int getcustomerID(HttpServletRequest request) {
		Cookie cookie = getCookie(request, "customerId");
		if (cookie != null) {
			String customerIDname = cookie.getValue();

			try {
				return Integer.parseInt(customerIDname);
			} catch (Exception e) {
			}
		}
		return 0;
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action = request.getServletPath();

        try {
            switch (action.toLowerCase()) {
				case "/register":
					register(request, response);
					break;
				case "/loginform":
					loginform(request, response);
					break;
				case "/logout":
					logout(request, response);
					break;
				case "/addbook":
					addBookItem(request, response);
					break;
				case "/cart":
					showCart(request, response);
					break;
				case "/selectspv":
					selectVoucherShipPayment(request, response);
					break;
				case "/login":
					login(request, response);
					break;
				case "/checkout":
					checkoutDetail(request, response);
					break;
				case "/order":
					order(request, response);
					break;
				case "/bill":
					getBill(request, response);
					break;
				case "/addnewbook":
					addBookForm(request, response);
					 break;
				case "/editbook":
					editBookForm(request, response);
					 break;
				case "/uploadbookitem":
					addBookitemForm(request, response);
					 break;
				case "/editbookitem":
					editBookitemForm(request, response);
					 break;
				case "/home":
					listBooks(request, response);
					break;
				case "/manage":
					adminPage(request, response);
					break;
				case "/admin":
					adminPage(request, response);
					break;
				case "/processadd":
					addBookC(request, response);
					break;
				case "/processedit":
					editBookC(request, response);
					break;
				case "/processedititem":
					editBookitem(request, response);
					break;
				case "/processadditem":
					uploadBookitem(request, response);
					break;
				case "/dellbitemcart":
					dellBitemCart(request, response);
					break;
				case "/orderlists":
					orderLists(request, response);
					break;
				default:
					listBooks(request, response);
					break;
				}
            } catch (Exception ex) {
            ex.printStackTrace();
        }
	}
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	public void register(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, NoSuchAlgorithmException {
		String firstname = request.getParameter("firstName");
		String lastname = request.getParameter("lastName");
		String gender = request.getParameter("gender");
		String username = request.getParameter("username");
		String passwordTmp = request.getParameter("password");
		String phoneNum = request.getParameter("phoneNum");
		String type = request.getParameter("type");
		String accountNum = request.getParameter("accountNum");
		String city = request.getParameter("city");
		String district = request.getParameter("district");
		String houseno = request.getParameter("houseno");
		String day = request.getParameter("day");
		String month = request.getParameter("month");
		String years = request.getParameter("years");
		
		MessageDigest digest = MessageDigest.getInstance("SHA-256");
		byte[] hash = digest.digest(passwordTmp.getBytes(StandardCharsets.UTF_8));
		String password = Base64.getEncoder().encodeToString(hash);
		
		Account account = new Account(1, username, password);
		Phone phone = new Phone(1, phoneNum, type);
		Address address = new Address(1, city, district, houseno);
		Fullname fullname = new Fullname(1, firstname, lastname);
		Birth birth = new Birth(1, day, month, years);
		User user = new User(1, gender, account, fullname, phone, birth, address);
		Customer customer = new Customer(1, accountNum, user);
		
		userDAO.addAcc(account);
		userDAO.addAddress(address);
		userDAO.addPhone(phone);
		userDAO.addFN(fullname);
		userDAO.addBirth(birth);
		userDAO.addUser(user);
		customerDAO.addCustomer(customer);

		RequestDispatcher dispatcher = request.getRequestDispatcher("registersuccess.jsp");
		dispatcher.forward(request, response);
	}
	
	public void listBooks(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		boolean requireLogin = false;
		int customerID = getcustomerID(request);
		if (requireLogin && customerID <= 0) {
			response.sendRedirect("account.html");
			return;
		}
		List<Bookitem> comics = bookitemDAO.getAllCMitem();
		List<Bookitem> textBook = bookitemDAO.getAllTXitem();
		List<Bookitem> lightNovel = bookitemDAO.getAllLNitem();
		request.setAttribute("listComics", comics);
		request.setAttribute("listTextBook", textBook);
		request.setAttribute("listLightNovel", lightNovel);
		request.setAttribute("customerID", customerID > 0);
		RequestDispatcher dispatcher = request.getRequestDispatcher("booklist.jsp");
		dispatcher.forward(request, response);
	}
	
	public void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Cookie cookie = new Cookie("customerId", "");
		response.addCookie(cookie);
		response.sendRedirect("home");
	}
	
	public void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, NoSuchAlgorithmException {
		String username = request.getParameter("username");
		String passwordTmp = request.getParameter("password");
		
		MessageDigest digest = MessageDigest.getInstance("SHA-256");
		byte[] hash = digest.digest(passwordTmp.getBytes(StandardCharsets.UTF_8));
		String password = Base64.getEncoder().encodeToString(hash);
//		System.out.println(username+password);
		int accountID = userDAO.getAccountID(username, password);
		System.out.println(accountID);
		if (userDAO.checkValidAccount(username, password)) {
			int customerId = customerDAO.getCustomerByAccountID(accountID).getID();
			Cookie cookie = new Cookie("customerId", String.valueOf(customerId));
			response.addCookie(cookie);
			request.setAttribute("customerID", customerId);
			response.sendRedirect("home");
		} else {
			PrintWriter writer = response.getWriter();
			writer.write("Wrong username or password!");
			writer.close();
		}
	}

	public void loginform(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.sendRedirect("account.html");
	}
	
	public void addBookItem(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		boolean requireLogin = true;
		int customerID = getcustomerID(request);
		if (requireLogin && customerID <= 0) {
			response.sendRedirect("account.html");
			return;
		}
		String bookItemId = request.getParameter("bookItemId");
		int quantity = Integer.parseInt(request.getParameter("quantity"));
		Bookitem bookItem = bookitemDAO.getBookitemByID(Integer.parseInt(bookItemId));
		Cart cart = cartDAO.getCart(customerID);
		if (cart == null) {
			cart = new Cart(1, customerDAO.getCustomer(customerID), dateFormat.format(new Date()),
					dateFormat.format(new Date()), 0, (float) 0);
			cartDAO.createCart(cart);
		}
		if (cartDAO.checkBookitem(cart, bookItem) > 0){
			System.out.println("abc");
			cartDAO.updateBookitem(cart, bookItem, quantity);
		}
		else{
			cartDAO.addBookitem(cart, bookItem, quantity);
		}
		
		PrintWriter writer = response.getWriter();
		writer.write("Added bookID " + bookItem.getID() + " to cart");
		writer.close();
	}
	
	public void showCart(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		boolean requireLogin = true;
		int customerID = getcustomerID(request);
		if (requireLogin && customerID <= 0) {
			response.sendRedirect("account.html");
			return;
		}
		float totalPrice = 0;
		float discount = 0;
		Cart cart = cartDAO.getCart(customerID);
		if (cart == null) {
			cart = new Cart(1, customerDAO.getCustomer(customerID), dateFormat.format(new Date()),
					dateFormat.format(new Date()), 0, (float) 0);
			cartDAO.createCart(cart);
		}

		HashMap<Integer, Integer> books = cartDAO.getBookitemIDList(cart.getID());

		List<Bookitem> bookItems = new ArrayList<Bookitem>();
		List<Integer> bookQuantity = new ArrayList<Integer>();
		List<Float> bookPrice = new ArrayList<Float>();

		for (Map.Entry<Integer, Integer> entry : books.entrySet()) {
			System.out.println(entry.getKey() + " " + entry.getValue());
			Bookitem bookItem = bookitemDAO.getBookitemByID(entry.getKey());
			bookItems.add(bookItem);
			bookQuantity.add(entry.getValue());
			System.out.println("CCC"+bookItem.getBook().getTitle());
			totalPrice += bookItem.getPrice() * entry.getValue();
			bookPrice.add(bookItem.getPrice() * entry.getValue());
			discount += bookItem.getDiscount() * entry.getValue();
		}

		request.setAttribute("bookItems", bookItems);
		request.setAttribute("bookQuantity", bookQuantity);
		request.setAttribute("bookPrice", bookPrice);

		request.setAttribute("totalPrice", totalPrice);
		request.setAttribute("discount", discount);
		request.setAttribute("total", totalPrice - discount);
		request.setAttribute("customerID", customerID > 0);

		RequestDispatcher dispatcher = request.getRequestDispatcher("cart.jsp");
		dispatcher.forward(request, response);
	}
	
	public void selectVoucherShipPayment(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("voucherShipPayment.jsp");
		dispatcher.forward(request, response);
	}
	
	public void order(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		boolean requireLogin = true;
		int customerID = getcustomerID(request);
		int shipmentID = Integer.parseInt(request.getParameter("shipment"));
		int voucherID = Integer.parseInt(request.getParameter("voucher"));
		int paymentID = Integer.parseInt(request.getParameter("payment"));
		if (requireLogin && customerID <= 0) {
			response.sendRedirect("account.html");
			return;
		}
		float totalPrice = 0;
		float discount = 0;
		Cart cart = cartDAO.getCart(customerID);
		if (cart == null) {
			cart = new Cart(1, customerDAO.getCustomer(customerID), dateFormat.format(new Date()),
					dateFormat.format(new Date()), 0, (float) 0);
			cartDAO.createCart(cart);
		}

		HashMap<Integer, Integer> books = cartDAO.getBookitemIDList(cart.getID());
		Shipment shipment = orderDAO.getShipmentByID(shipmentID);
		Voucher voucher = orderDAO.getVoucherByID(voucherID);
		Payment payment = orderDAO.getPaymentByID(paymentID);
		
//		if (shipmentID == 3){
//			FastShipment shipment = shipmentDAO.findFastShipmentByID(shipmentID);
//		}else if(shipmentID == 2){
//			EconomicalShipment shipment = shipmentDAO.findEconomicalShipmentByID(shipmentID);
//		}
//		else{
//			AbroadShipment shipment = shipmentDAO.findAbroadShipmentByID(shipmentID);
//		}
		
		List<Bookitem> bookitems = new ArrayList<Bookitem>();
		List<Integer> bookQuantity = new ArrayList<Integer>();
		List<Float> bookPrice = new ArrayList<Float>();
		int totalQuantities = 0;
		
		for (Map.Entry<Integer, Integer> entryBook : books.entrySet()) {
			System.out.println(entryBook.getKey() + " " + entryBook.getValue());
			Bookitem bookItem = bookitemDAO.getBookitemByID(entryBook.getKey());
			bookitems.add(bookItem);
			bookQuantity.add(entryBook.getValue());
			totalQuantities += entryBook.getValue();
			totalPrice += bookItem.getPrice() * entryBook.getValue();
			bookPrice.add(bookItem.getPrice() * entryBook.getValue());
			discount += bookItem.getDiscount() * entryBook.getValue();
		}

		float voucherdiscount = 0;
		float shipmentPrice = shipment.getFees();
		int type = orderDAO.getVoucherType(voucherID);
		if(type == 1){
			ConstantVoucher vc = orderDAO.getConstantVoucher(voucherID);
			voucherdiscount = vc.getQuantity();
		}else{
			PercentageVoucher vc = orderDAO.getPercentageVoucher(voucherID);
			voucherdiscount = totalPrice*vc.getPercentage()/100;
		}
		
		
		Order order = new Order(0, dateFormat.format(new Date()), cart, payment, shipment, voucher, employeeDAO.getEmployee(1));
		orderDAO.addOrder(order);
		cartDAO.editCart(cart, totalPrice - discount - voucherdiscount + shipmentPrice, totalQuantities);
		billDAO.addBill(new Bill(1, cart.getTotalPrice(), dateFormat.format(new Date()), discount, order, employeeDAO.getEmployee(1)));
		cartDAO.createCart(new Cart(cart.getID()+1, customerDAO.getCustomer(getcustomerID(request)), dateFormat.format(new Date()), dateFormat.format(new Date()), 0, (float) 0.0));
		request.setAttribute("bookItems", bookitems);
		request.setAttribute("bookQuantity", bookQuantity);
		request.setAttribute("bookPrice", bookPrice);
		
		request.setAttribute("totalPrice", totalPrice);
		request.setAttribute("voucherDiscount", voucherdiscount);
		request.setAttribute("discount", discount+voucherdiscount);
		request.setAttribute("shipmentPrice", shipmentPrice);
		request.setAttribute("total", totalPrice - discount - voucherdiscount + shipmentPrice);

		RequestDispatcher dispatcher = request.getRequestDispatcher("order.jsp");
		dispatcher.forward(request, response);
	}
	
	public void checkoutDetail(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		boolean requireLogin = true;
		int customerID = getcustomerID(request);
		if (requireLogin && customerID <= 0) {
			response.sendRedirect("account.html");
			return;
		}
		float totalPrice = 0;
		float discount = 0;
		Cart cart = cartDAO.getCart(customerID);
		if (cart == null) {
			cart = new Cart(1, customerDAO.getCustomer(customerID), dateFormat.format(new Date()),
					dateFormat.format(new Date()), 0, (float) 0);
			cartDAO.createCart(cart);
		}

		HashMap<Integer, Integer> books = cartDAO.getBookitemIDList(cart.getID());

		List<Bookitem> bookitems = new ArrayList<Bookitem>();
		List<Integer> bookQuantity = new ArrayList<Integer>();
		List<Float> bookPrice = new ArrayList<Float>();
		List<Payment> payments = new ArrayList<Payment>();
		List<Shipment> shipments = new ArrayList<Shipment>();
		List<Voucher> vouchers = new ArrayList<Voucher>();
		
		
		int totalQuantities = 0;
		
		for (Payment payment: orderDAO.getAllPayment()){
			payments.add(payment);
		}
		for (Shipment shipment: orderDAO.getAllShipment()){
			shipments.add(shipment);
		}
		for (Voucher voucher: orderDAO.getAllVoucher()){
			vouchers.add(voucher);
		}
		for (Map.Entry<Integer, Integer> entryBook : books.entrySet()) {
			System.out.println(entryBook.getKey() + " " + entryBook.getValue());
			Bookitem bookitem = bookitemDAO.getBookitemByID(entryBook.getKey());
			bookitems.add(bookitem);
			bookQuantity.add(entryBook.getValue());
			totalQuantities += entryBook.getValue();
			totalPrice += bookitem.getPrice() * entryBook.getValue();
			bookPrice.add(bookitem.getPrice() * entryBook.getValue());
			discount += bookitem.getDiscount() * entryBook.getValue();
		}

		request.setAttribute("bookItems", bookitems);
		request.setAttribute("bookQuantity", bookQuantity);
		request.setAttribute("bookPrice", bookPrice);

		request.setAttribute("totalPrice", totalPrice);
		request.setAttribute("discount", discount);
		request.setAttribute("total", totalPrice - discount);
		
		request.setAttribute("payments", payments);
		request.setAttribute("shipments", shipments);
		request.setAttribute("vouchers", vouchers);
		
		request.setAttribute("customerID", customerID > 0);

		RequestDispatcher dispatcher = request.getRequestDispatcher("checkoutDetail.jsp");
		dispatcher.forward(request, response);
	}
	
	public void getBill(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int billID = billDAO.getMaxID(getcustomerID(request));
		Bill bill = billDAO.getBill(billID);
		Order order = bill.getOrder();
		orderDAO.getPaymentType(order.getPayment().getID());
		String paymentName;
		String shipmentName;
		String voucherName;
		if (orderDAO.getPaymentType(order.getPayment().getID()) == 1){
			paymentName = "COD";
		} else if (orderDAO.getPaymentType(order.getPayment().getID()) == 2){
			paymentName = "Internet Banking";
		} else {
			paymentName = "Credit Card";
		}
		
		if (orderDAO.getShipmentType(order.getShipment().getID()) == 1){
			shipmentName = "Economical";
		} else if (orderDAO.getShipmentType(order.getShipment().getID()) == 2){
			shipmentName = "Safe";
		} else {
			shipmentName = "Fast";
		}
		
		if (orderDAO.getPaymentType(order.getPayment().getID()) == 1){
			voucherName = "Constant";
		} else {
			voucherName = "Percentage";
		}
		
		request.setAttribute("totalPrice", bill.getTotalPrice());
		request.setAttribute("totalDiscount", bill.getTotalDiscount());
		request.setAttribute("orderID", order.getID());
		request.setAttribute("paymentMethod", paymentName);
		request.setAttribute("shipmentMethod", shipmentName);
		request.setAttribute("voucherMethod", voucherName);
	}
	
	public void addBookForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		boolean requireLogin = true;
		int customerID = getcustomerID(request);
		if (requireLogin && customerID != 1) {
			response.sendRedirect("account.html");
			return;
		}
		response.sendRedirect("addbookform.jsp");
	}
	
	public void addBookC(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		boolean requireLogin = true;
		int customerID = getcustomerID(request);
		if (requireLogin && customerID != 1) {
			response.sendRedirect("account.html");
			System.out.println("cc");
			return;
		}
		
		int employeeID = 1;
		Employee employee = employeeDAO.getEmployee(employeeID);
		String nameAuthor = request.getParameter("nameAuthor");
		String shortBio = request.getParameter("shortBio");
		String birth = request.getParameter("birth");
		Author authortmp = new Author(1, nameAuthor, shortBio, birth);
		bookDAO.addAuthor(authortmp);
		Author author = bookDAO.getAuthor(bookDAO.getMaxIDAut());
		String address = request.getParameter("address");
		String namePublisher = request.getParameter("namePublisher");
		int operateYears = Integer.parseInt(request.getParameter("operateYears"));
		Publisher publishertmp = new Publisher(1, address, namePublisher, operateYears);
		bookDAO.addPublisher(publishertmp);
		Publisher publisher = bookDAO.getPublisher(bookDAO.getMaxIDPub());
		String title = request.getParameter("title");
		String summary = request.getParameter("summary");
		int years = Integer.parseInt(request.getParameter("years"));
		String type = request.getParameter("type");
		System.out.println("smt");
		if(type.equals("LN")){
			String translateLanguage = request.getParameter("translateLanguage");
			int volume = Integer.parseInt(request.getParameter("volume"));
			int editions = Integer.parseInt(request.getParameter("editions"));
			LightNovel book = new LightNovel(1, title, summary, years, employee, author, publisher, 1, translateLanguage, volume, editions);
			bookDAO.addBookLN(book);
			System.out.println("Add LN done ");
			System.out.println(summary+translateLanguage);
//			PrintWriter writer = response.getWriter();
//			writer.write("Added Light Novel " + book.getID() + " to system");
//			writer.close();
		} else if(type.equals("CM")){
			String nameSeries = request.getParameter("nameSeries");
			String artists = request.getParameter("artists");
			Comics book = new Comics(1, title, summary, years, employee, author, publisher, 1, nameSeries, artists);
			bookDAO.addBookCM(book);
			System.out.println("Add CM done ");
//			PrintWriter writer = response.getWriter();
//			writer.write("Added Comic " + book.getID() + " to system");
//			writer.close();
		} else if(type.equals("TX")){
			int numberOfPages = Integer.parseInt(request.getParameter("numberOfPages"));
			int volume = Integer.parseInt(request.getParameter("volumeTX"));
			int editions = Integer.parseInt(request.getParameter("editionsTX"));
			TextBook book = new TextBook(1, title, summary, years, employee, author, publisher, 1, numberOfPages, volume, editions);
			bookDAO.addBookTX(book);
			System.out.println("Add TX done ");
//			PrintWriter writer = response.getWriter();
//			writer.write("Added Text Book " + book.getID() + " to system");
//			writer.close();
		}
//		response.sendRedirect("admin-index.jsp");  
		RequestDispatcher dispatcher = request.getRequestDispatcher("/admin");
		dispatcher.forward(request, response);
	}
	
	public void editBookForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		boolean requireLogin = true;
		int customerID = getcustomerID(request);
		if (requireLogin && customerID != 1) {
			response.sendRedirect("account.html");
			return;
		}
		int ISBN = Integer.parseInt(request.getParameter("ISBN"));
		int type = bookDAO.getType(ISBN);
		Book book = bookDAO.getBookByID(ISBN);
		request.setAttribute("ISBN",  ISBN);
		request.setAttribute("nameAuthor", book.getAuthor().getName());
		request.setAttribute("shortBio", book.getAuthor().getShortBio());
		request.setAttribute("birth", book.getAuthor().getBirth());
		request.setAttribute("address", book.getPublisher().getAddress());
		request.setAttribute("namePublisher", book.getPublisher().getName());
		request.setAttribute("operateYears", String.valueOf(book.getPublisher().getOperateYears())); //int
		request.setAttribute("title", book.getTitle());
		request.setAttribute("summary", book.getSummary());
		request.setAttribute("years", String.valueOf(book.getYears())); //int
		
		if(type == 1){
			LightNovel ln = bookDAO.getLNByID(ISBN);
			request.setAttribute("type", "LightNovel");
			request.setAttribute("ID", String.valueOf(ln.getID()));
			request.setAttribute("translateLanguage", ln.getTranslateLanguage());
			request.setAttribute("volume", String.valueOf(ln.getVolume())); //int
			request.setAttribute("editions", String.valueOf(ln.getEditions())); //int
			RequestDispatcher dispatcher = request.getRequestDispatcher("editbookform.jsp");
			dispatcher.forward(request, response);
			return;
		} else if(type == 2){
			Comics cm = bookDAO.getCMByID(ISBN);
			request.setAttribute("type", "Comics");
			request.setAttribute("ID", String.valueOf(cm.getID()));
			request.setAttribute("nameSeries", cm.getNameSeries());
			request.setAttribute("artists", cm.getArtists());
			RequestDispatcher dispatcher = request.getRequestDispatcher("editbookform.jsp");
			dispatcher.forward(request, response);
			return;
		} else if(type == 3){
			TextBook tx = bookDAO.getTXByID(ISBN);
			request.setAttribute("type", "TextBook");
			request.setAttribute("ID", String.valueOf(tx.getID()));
			request.setAttribute("numberOfPages", String.valueOf(tx.getNumberOfPages())); //int
			request.setAttribute("volumeTX", String.valueOf(tx.getVolume())); //int
			request.setAttribute("editionsTX", String.valueOf(tx.getEditions())); //int
			RequestDispatcher dispatcher = request.getRequestDispatcher("editbookform.jsp");
			dispatcher.forward(request, response);
			return;
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/manage");
		dispatcher.forward(request, response);
	}
	
	public void editBookC(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		boolean requireLogin = true;
		int customerID = getcustomerID(request);
		if (requireLogin && customerID != 1) {
			response.sendRedirect("account.html");
			return;
		}
		
		int employeeID = 1;
		int ISBN = Integer.parseInt(request.getParameter("ISBN"));
		Employee employee = employeeDAO.getEmployee(employeeID);
		String nameAuthor = request.getParameter("nameAuthor");
		String shortBio = request.getParameter("shortBio");
		String birth = request.getParameter("birth");
		Author authortmp = new Author(1, nameAuthor, shortBio, birth);
		bookDAO.addAuthor(authortmp);
		Author author = bookDAO.getAuthor(bookDAO.getMaxIDAut());
		String address = request.getParameter("address");
		String namePublisher = request.getParameter("namePublisher");
		int operateYears = Integer.parseInt(request.getParameter("operateYears"));
		Publisher publishertmp = new Publisher(1, address, namePublisher, operateYears);
		bookDAO.addPublisher(publishertmp);
		Publisher publisher = bookDAO.getPublisher(bookDAO.getMaxIDPub());
		String title = request.getParameter("title");
		String summary = request.getParameter("summary");
		int years = Integer.parseInt(request.getParameter("years"));
		String type = request.getParameter("type");
		
		if(type.equals("LightNovel")){
			int ID = Integer.parseInt(request.getParameter("ID"));
			String translateLanguage = request.getParameter("translateLanguage");
			int volume = Integer.parseInt(request.getParameter("volume"));
			int editions = Integer.parseInt(request.getParameter("editions"));
			LightNovel book = new LightNovel(ISBN, title, summary, years, employee, author, publisher, ID, translateLanguage, volume, editions);
			bookDAO.editLNBook(book);
//			PrintWriter writer = response.getWriter();
//			writer.write("Edited Light Novel " + book.getID() + " to system");
//			writer.close();
		} else if(type.equals("Comics")){
			int ID = Integer.parseInt(request.getParameter("ID"));
			String nameSeries = request.getParameter("nameSeries");
			String artists = request.getParameter("artists");
			Comics book = new Comics(ISBN, title, summary, years, employee, author, publisher, ID, nameSeries, artists);
			bookDAO.editCMBook(book);
//			PrintWriter writer = response.getWriter();
//			writer.write("Edited Comic " + book.getID() + " to system");
//			writer.close();
		} else{
			int ID = Integer.parseInt(request.getParameter("ID"));
			int numberOfPages = Integer.parseInt(request.getParameter("numberOfPages"));
			int volume = Integer.parseInt(request.getParameter("volumeTX"));
			int editions = Integer.parseInt(request.getParameter("editionsTX"));
			TextBook book = new TextBook(ISBN, title, summary, years, employee, author, publisher, ID, numberOfPages, volume, editions);
			bookDAO.editTXBook(book);
//			PrintWriter writer = response.getWriter();
//			writer.write("Edited Text Book " + book.getID() + " to system");
//			writer.close();
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/manage");
		dispatcher.forward(request, response);
	}
	
	public void addBookitemForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		boolean requireLogin = true;
		int customerID = getcustomerID(request);
		if (requireLogin && customerID != 1) {
			response.sendRedirect("account.html");
			return;
		}
		int ISBN = Integer.parseInt(request.getParameter("ISBN"));
		Bookitem tmp = bookitemDAO.getBookitem(ISBN);
		if (tmp != null){
			response.sendRedirect("/Final-Exam/admin");
		}
		request.setAttribute("ISBN",  ISBN);
		request.setAttribute("uploadDate", dateFormat.format(new Date()));
		RequestDispatcher dispatcher = request.getRequestDispatcher("addbitemform.jsp");
		dispatcher.forward(request, response);
//		response.sendRedirect("addbitemform.jsp");
	}
	
	public void uploadBookitem(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		boolean requireLogin = true;
		int customerID = getcustomerID(request);
		if (requireLogin && customerID != 1) {
			response.sendRedirect("account.html");
			return;
		}
		
		int employeeID = 1;
		Employee employee = employeeDAO.getEmployee(employeeID);
		int ISBN = Integer.parseInt(request.getParameter("ISBN"));
		float price = Float.parseFloat(request.getParameter("price"));
		float discount = Float.parseFloat(request.getParameter("discount"));
		String uploadDate = request.getParameter("uploadDate");
		int inStock = Integer.parseInt(request.getParameter("inStock"));
		Book book = bookDAO.getBookByID(ISBN);
		Bookitem bookitem = new Bookitem(1, book, employee, price, discount, uploadDate, inStock);
		bookitemDAO.addBookitem(bookitem);
		
//		PrintWriter writer = response.getWriter();
//		writer.write("Add Bookitem " + bookitem.getID() + " to system");
//		writer.close();
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/manage");
		dispatcher.forward(request, response);
	}
	
	public void editBookitemForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		boolean requireLogin = true;
		int customerID = getcustomerID(request);
		if (requireLogin && customerID != 1) {
			response.sendRedirect("account.html");
			return;
		}
		int ISBN = Integer.parseInt(request.getParameter("ISBN"));
		Bookitem bookitem = bookitemDAO.getBookitem(ISBN);
		request.setAttribute("ID", String.valueOf(bookitem.getID()));
		request.setAttribute("ISBN",  ISBN);
		request.setAttribute("uploadDate", bookitem.getUploadDate());
		request.setAttribute("price", String.valueOf(bookitem.getPrice())); //int
		request.setAttribute("discount", String.valueOf(bookitem.getDiscount())); //int
		request.setAttribute("inStock", String.valueOf(bookitem.getInStock())); //int
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("editbitemform.jsp");
		dispatcher.forward(request, response);
	}
	
	public void editBookitem(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		boolean requireLogin = true;
		int customerID = getcustomerID(request);
		if (requireLogin && customerID != 1) {
			response.sendRedirect("account.html");
			return;
		}
		
		int employeeID = 1;
		int ID = Integer.parseInt(request.getParameter("ID"));
		int ISBN = Integer.parseInt(request.getParameter("ISBN"));
		Employee employee = employeeDAO.getEmployee(employeeID);
		Book book = bookDAO.getBookByID(ISBN);
		String uploadDate = request.getParameter("uploadDate");
		int inStock = Integer.parseInt(request.getParameter("inStock"));
		float price = Float.parseFloat(request.getParameter("price"));
		float discount = Float.parseFloat(request.getParameter("discount"));
//		Bookitem bookitem = new Bookitem(ID, book, employee, price, discount, uploadDate, inStock);
		bookitemDAO.editBookItem(ID, price, discount, inStock);
		
//		PrintWriter writer = response.getWriter();
//		writer.write("Edit Bookitem " + bookitem.getID() + " to system");
//		writer.close();
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/manage");
		dispatcher.forward(request, response);
	}
	
	public void adminPage(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		boolean requireLogin = true;
		int customerID = getcustomerID(request);
		if (requireLogin && customerID != 1) {
			System.out.print("CC");
			response.sendRedirect("/Final-Exam/home");
			return;
		}
		List<Comics> comics = bookDAO.findAllComics();
		List<TextBook> textBook = bookDAO.findAllTextBook();
		List<LightNovel> lightNovel = bookDAO.findAllLightNovel();
		List<Customer> customers = customerDAO.findAllCustomers();
		List<Bookitem> bookitem = bookitemDAO.getAllCMitem();
		List<Bookitem> bookitemtmp = bookitemDAO.getAllLNitem();
		List<Bookitem> bookitemtmp2 = bookitemDAO.getAllTXitem();
		for(Bookitem bitem:bookitemtmp){
			bookitem.add(bitem);
		}
		for(Bookitem bitem:bookitemtmp2){
			bookitem.add(bitem);
		}
//		for(Bookitem bitem:bookitem){
//			System.out.println(bitem.getBook().getISBN());
//		}
		request.setAttribute("listComics", comics);
		request.setAttribute("listTextBook", textBook);
		request.setAttribute("listLightNovel", lightNovel);
		request.setAttribute("customerID", customerID > 0);
		request.setAttribute("customers", customers);
		request.setAttribute("bookitem", bookitem);
//		bookitem.get(0).getBook().get
//		TextBook txt;
//		txt.getAuthor().get
		RequestDispatcher dispatcher = request.getRequestDispatcher("admin-index.jsp");
		dispatcher.forward(request, response);
	}
	
	public void dellBitemCart(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		boolean requireLogin = true;
		int customerID = getcustomerID(request);
		if (requireLogin && customerID <= 0) {
			response.sendRedirect("account.html");
			return;
		}
		int bookitemID = Integer.parseInt(request.getParameter("bookitemID"));
		int cartID = cartDAO.getCart(customerID).getID();
		System.out.println(cartDAO.delCartBitem(bookitemID, cartID));
		response.sendRedirect("cart");
//		RequestDispatcher dispatcher = request.getRequestDispatcher("/cart");
//		dispatcher.forward(request, response);
	}
	
	public void orderLists(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		boolean requireLogin = true;
		int customerID = getcustomerID(request);
		if (requireLogin && customerID <= 0) {
			response.sendRedirect("account.html");
			return;
		}
		List<Order> orders = orderDAO.showAllOrder(customerID);
//		orders.get(0).getPayment().get
		while (orders.remove(null));
		System.out.println(orders.size());
		request.setAttribute("listOrders", orders);
		request.setAttribute("customerID", customerID > 0);
//		bookitem.get(0).getBook().get
//		TextBook txt;
//		txt.getAuthor().get
		RequestDispatcher dispatcher = request.getRequestDispatcher("orderhistory.jsp");
		dispatcher.forward(request, response);
	}
	
}
