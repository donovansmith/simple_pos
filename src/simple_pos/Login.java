package simple_pos;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Login {
	private String userName;
	private String password;
	private String name;
	
	BufferedReader br = null;
	String line = "";
	
	Login() {
		try {
			br = new BufferedReader(new FileReader("Login.csv"));
			while((line = br.readLine()) != null) {
				String item[] = line.split(",");
			}
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		} catch(IOException e) {
			e.printStackTrace();
		} finally {
			if(br != null) {
				try {
					br.close();
				} catch(IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
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
