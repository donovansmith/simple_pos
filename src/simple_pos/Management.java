package simple_pos;

import java.util.ArrayList;

public class Management {
	private Inventory mainInventory;
	private ArrayList<Cashier> cashiers;
	private ArrayList<Register> registers;
	//private Login login;
	
	private Cashier currentCashier;
	private Sale currentSale;
	

	public Management() {
		this.mainInventory=new Inventory();
		this.cashiers= new ArrayList<Cashier>();
		this.registers= new ArrayList<Register>();
		//this.login= new Login();
	}
	
	public void login(String username, String password) {
//		Login loginObj= new Login();
				
//		if (loginObj.authenticate(username, password)) {
			String name = "Leeroy Jenkins";
//
		this.currentCashier = new Cashier(name);
//
//		}
	}
	
	public void startSale() {
		this.currentSale = new Sale();		
	}
	
	public void addToSale(String itemName) {
		Item saleItem=mainInventory.searchInventory(itemName);
		if (saleItem==null) {
			return;
		}
		currentSale.addItem(saleItem);		
	}
	
	public void removeFromSale(String itemName) {
		Item saleItem=mainInventory.searchInventory(itemName);
		if (saleItem==null) {
			return;
		}
		currentSale.removeItem(saleItem);		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//Getters & Setters
	protected Inventory getMainInventory() {
		return mainInventory;
	}
	protected Sale getCurrentSale() {
		return currentSale;
	}

}
