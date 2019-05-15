package simple_pos;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Date;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class Management {
	private Inventory mainInventory;
	private ArrayList<Cashier> cashiers;
	private ArrayList<Register> registers;
	private ArrayList<Receipt> receipts;
	private Login login;
	
	private Cashier currentCashier;
	private Sale currentSale;
	private Register currentRegister;
	private Receipt currentReceipt;
	private int saleId = 10000;
	
	BufferedReader br = null;
	String line = "";
	

	public Management() {
		this.mainInventory=new Inventory();
		this.cashiers= new ArrayList<Cashier>();
		this.registers= new ArrayList<Register>();
		this.receipts = new ArrayList<Receipt>();		
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
	
	public String completeSale(double payment) {
		String moneyOwedString;
		DecimalFormat df = new DecimalFormat("#.##");
		double moneyOwed = payment - currentSale.total;
		moneyOwedString =df.format(moneyOwed);
		currentReceipt = currentSale.generateReceipt(this.saleId, payment, moneyOwed);
		receipts.add(currentReceipt);
		this.saleId++;
		currentSale.inventory.updateInventoryCSV();
		this.mainInventory = new Inventory();
		return moneyOwedString;
	}
	
	public void cancelSale() {
		this.mainInventory = new Inventory();
	}
	
	public static String getTime() {
		String time;
		SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");  
		Date date = new Date(System.currentTimeMillis());  
		time = formatter.format(date);  
		return time;
	}
	
	public void createIventoryReport() { //where does this go?
		String inventoryReport = mainInventory.toString(); 
	}
	
	public void createCashierReport() { //where does this go?
		String cashierReport = "";
		for(int n = 0; n < cashiers.size(); n++) {
			cashierReport += cashiers.get(n).getName() + "\n";
			for(int d = 0; d < cashiers.get(n).getDrawer().size(); d++) {
				cashierReport += "Sale " + d + ":" + cashiers.get(n).getDrawer().get(d).toString() + "\n"; //or could use receipt
			}
		}
	}
	
	public void createRegisterReport() { //where does this go?
		String registerReport = "";
		for(int c = 0; c < registers.size(); c++) {
			registerReport += registers.get(c).toString();
		}
	}
	
	//Getters & Setters
	protected Inventory getMainInventory() {
		return mainInventory;
	}
	protected Sale getCurrentSale() {
		return currentSale;
	}
	protected Cashier getCurrentCashier() {
		return currentCashier;
	}
	protected Register getCurrentRegister() {
		return currentRegister;
	}
	protected Receipt getReceipts() {
		return currentReceipt;
	}
}
