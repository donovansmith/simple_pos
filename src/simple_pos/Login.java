package simple_pos;

public class Login {
	private String userName;
	private String password;
	private String name;
	
	public void authenticate(String userName, String password) {
		this.userName = userName;
		this.password = password;
		
		if (password.equals("test") && userName.contentEquals("test")) {
			System.out.println("Yay");
		}
	}
	
	public void addNewUser() {
		
	}
	
	public String getName() {
		return name;
	}
}
