package simple_pos;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class Management {
	private Inventory mainInventory;
	private ArrayList<Cashier> cashiers;
	private ArrayList<Register> registers;
	private Login login;
	
	private Cashier currentCashier;
	private Sale currentSale;
	private Register currentRegister;
	
	BufferedReader br = null;
	String line = "";
	

	public Management() {
		this.mainInventory=new Inventory();
		this.cashiers= new ArrayList<Cashier>();
		this.registers= new ArrayList<Register>();
		this.login= new Login();
		
		try{
			br = new BufferedReader(new FileReader("Registers.csv"));

			while ((line = br.readLine()) != null) {
				this.currentRegister = new Register(Integer.parseInt(line));
				registers.add(this.currentRegister);
			}
	     }catch (IOException ioe) {
	    	ioe.printStackTrace();
	     }
	}
	
	public boolean login(String username, String password) {
		Login loginObj= new Login();
		String[] login = new String[2];
		login[0]= username;
		login[1]=password;
		
		String loginSuccess = loginObj.authenticate(login);
				
		if (loginSuccess  != "" ) {
			
			this.currentCashier = new Cashier(loginSuccess);
			this.currentRegister.setCashier(this.currentCashier);
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
	
	public static String getTime() {
		String time;
		SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");  
		Date date = new Date(System.currentTimeMillis());  
		time = formatter.format(date);  
		return time;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//Getters & Setters
	protected Inventory getMainInventory() {
		return mainInventory;
	}
	protected Sale getCurrentSale() {
		return currentSale;
	}

}
