package ung_wishlist;

public class Authentication {
	// DBconnection string
	// DBconnection username
	// DBconnection password
	
	
	public static boolean authenticate (String username) {
		// connect to db
		// search db for username using SQL
		// if found search for password using SQL
		// if both are found return true
		// catch errors just in case
		// else return false if
		
		
		return false; //placeholder
	}

	public static boolean checkUsernameExists (String username) {
		//When creating new account, check if username is already take
		//connect to db
		//search db for username using SQL
		//if found return true
		//if not found return false (username not already taken, can be used)
		
		return false; //placeholder
	}

	public static boolean checkEmailExists(String email){
		//When creating new account, check if there is already an account associated with an email address
		//connect to db
		//search db for email using SQL
		//if found return true
		//if not found return false (email not associated with account, can be used)


		return false; //placeholder
	}
	
	public static void main(String[] args) {
		// string username
		// string password
		
		// if ( Call authenticate method)
		// else ("too bad")
		
		
		
	}

}
