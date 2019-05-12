package simple_pos;

import java.util.ArrayList;

public class Management {
	private Inventory mainInventory;
	private ArrayList<Cashier> cashiers;
	private ArrayList<Register> registers;
	
	public Management() {
		this.mainInventory=new Inventory();
		this.cashiers= new ArrayList<Cashier>();
		this.registers= new ArrayList<Register>();
	}
	
	public void login(String username, String password) {
		//Login loginAuth = new Login(username, password);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//Getters & Setters
	protected Inventory getMainInventory() {
		return mainInventory;
	}

}
