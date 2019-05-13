package simple_pos;

import java.util.ArrayList;

public class Management {
	private Inventory mainInventory;
	private ArrayList<Cashier> cashiers;
	private ArrayList<Register> registers;
	private Login login;
	
	private Cashier currentCashier;
	private Sale currentSale;
	

	public Management() {
		this.mainInventory=new Inventory();
		this.cashiers= new ArrayList<Cashier>();
		this.registers= new ArrayList<Register>();
		this.login= new Login();
	}
	
	public boolean login(String username, String password) {
		Login loginObj= new Login();
		String[] login = new String[2];
		login[0]= username;
		login[1]=password;
		
		String loginSuccess = loginObj.authenticate(login);
				
		if (loginSuccess  != "" ) {
			
			this.currentCashier = new Cashier(loginSuccess);
			return true;
		}
		else {
			return false;
		}
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
