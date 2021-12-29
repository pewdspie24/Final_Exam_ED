package model.customer;

public class Favourite {

	private int ID;
	private Customer customer;
	private String nameProduct;
	
	public Favourite(int iD, Customer customer, String nameProduct) {
		super();
		this.ID = iD;
		this.customer = customer;
		this.nameProduct = nameProduct;
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

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public String getNameProduct() {
		return this.nameProduct;
	}

	/**
	 * 
	 * @param nameProduct
	 */
	public void setNameProduct(String nameProduct) {
		this.nameProduct = nameProduct;
	}

}