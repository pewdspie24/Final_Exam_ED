package model.customer;

public class SearchHistory {

	private int ID;
	Customer customer;
	private String timeStamp;
	private String keywords;

	public SearchHistory(int iD, Customer customer, String timeStamp, String keywords) {
		super();
		this.ID = iD;
		this.customer = customer;
		this.timeStamp = timeStamp;
		this.keywords = keywords;
	}

	public int getID() {
		return this.ID;
	}

	/**
	 * 
	 * @param ID
	 */
	public void setID(int ID) {
		this.ID = ID;
	}

	public String getTimeStamp() {
		return this.timeStamp;
	}

	/**
	 * 
	 * @param timeStamp
	 */
	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}

	public String getKeywords() {
		return this.keywords;
	}

	/**
	 * 
	 * @param keywords
	 */
	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

}