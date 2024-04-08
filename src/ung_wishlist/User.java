package ung_wishlist;


// Class for storing the currently logged in user
public class User {
	private long id;
	private String username;
	private String firstName;
	private String lastName;
	private String Email;
	

	
	public User(long id, String username, String firstName, String lastName, String Email) {
		this.id = id;
		this.username = username;
		this.firstName = firstName;
		this.lastName = lastName;
		this.Email = Email;
	}
	
	public long getId() {
		return id;
	}
	
	public String getUsername() {
		return username;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public String getEmail() {
		return Email;
	}

}
