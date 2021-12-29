package model.user;

public class User {

	private int ID;
	private String gender;
	private Account account;
	private Fullname fullname;
	private Phone phone;
	private Birth birth;
	private Address address;

	public User(int iD, String gender, Account account, Fullname fullname, Phone phone, Birth birth, Address address) {
		super();
		this.ID = iD;
		this.gender = gender;
		this.account = account;
		this.fullname = fullname;
		this.phone = phone;
		this.birth = birth;
		this.address = address;
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

	public String getGender() {
		return this.gender;
	}

	/**
	 * 
	 * @param gender
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}

	public Account getAccount() {
		return this.account;
	}

	/**
	 * 
	 * @param account
	 */
	public void setAccount(Account account) {
		this.account = account;
	}

	public Fullname getFullname() {
		return this.fullname;
	}

	/**
	 * 
	 * @param fullname
	 */
	public void setFullname(Fullname fullname) {
		this.fullname = fullname;
	}

	public Phone getPhone() {
		return this.phone;
	}

	/**
	 * 
	 * @param phone
	 */
	public void setPhone(Phone phone) {
		this.phone = phone;
	}

	public Birth getBirth() {
		return this.birth;
	}

	/**
	 * 
	 * @param birth
	 */
	public void setBirth(Birth birth) {
		this.birth = birth;
	}

	public Address getAddress() {
		return this.address;
	}

	/**
	 * 
	 * @param Address
	 */
	public void setAddress(Address address) {
		this.address = address;
	}

}