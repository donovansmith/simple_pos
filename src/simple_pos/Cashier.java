package simple_pos;
import java.util.ArrayList; 

public class Cashier {
	private String userName;
	private	String password;
	private String name;
	private ArrayList<Sale> drawer;
	
	Cashier(String userName, String password, String name){
		this.userName = userName;
		this.password = password;
		this.name = name;
	}
	
	public void newDrawer() {
		
	}
	
	public void addToDrawer(Sale sale) {
		drawer.add(sale);
	}
	
	public void removeFromDrawer(Sale ret) {
		drawer.remove(ret);
	}
	
	//function depends on definition of being "logged in"
	public void logOut() {
		
	}
	
	//return type will depend on Inventory type
	public void checkInventory() {
		
	}
	//getters and setters
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<Sale> getDrawer() {
		return drawer;
	}

	public void setDrawer(ArrayList<Sale> drawer) {
		this.drawer = drawer;
	}


}
