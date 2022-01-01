
public class tester {
	private static final String INSERT_NEW_BITEM = "insert into bookitem " + "(EmployeeID, Price, Discount, UploadDate, Instock, BookID) values "+" (?, ?, ?, ?, ?, ?);";
	 private static final String UPDATE_BITEM_BY_ID = "UPDATE bookitem SET Price = ?, Discount = ?, instock= ? WHERE ID = ?;";
}