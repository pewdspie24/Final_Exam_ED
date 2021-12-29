package model.book;

public class Author {

	private int ID;
	private String name;
	private String shortBio;
	private String birth;

	public Author(int iD, String name, String shortBio, String birth) {
		super();
		this.ID = iD;
		this.name = name;
		this.shortBio = shortBio;
		this.birth = birth;
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

	public String getName() {
		return this.name;
	}

	/**
	 * 
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	public String getShortBio() {
		return this.shortBio;
	}

	/**
	 * 
	 * @param shortBio
	 */
	public void setShortBio(String shortBio) {
		this.shortBio = shortBio;
	}

	public String getBirth() {
		return this.birth;
	}

	/**
	 * 
	 * @param birth
	 */
	public void setBirth(String birth) {
		this.birth = birth;
	}

}