package simple_pos;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Date;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Optional;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;

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
	
	private Return currentReturn;
	private int currentReturnID;
	
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
			cashiers.add(this.currentCashier);
			
			boolean openReg = false;
			for (int reg=0; reg < registers.size(); reg++) {
				if (registers.get(reg).getUser() == null) {
					registers.get(reg).setCashier(this.currentCashier);
					openReg = true;
					break;
				}
			}
			if (openReg == false) {
				this.currentRegister.setCashier(this.currentCashier);
			}
			return true;
		}
		else {
			return false;
		}
	}
	
	public void startSale() {
		this.currentSale = new Sale();		
	}
	
	public void startReturn(int currentReceiptID) {
		this.currentReturn = new Return();
		for (Receipt receipt:receipts) {
			if (receipt.getReceiptId()== currentReceiptID ) {
				this.currentReturn.setReceipt(receipt);
				return;
			}
		}
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
	
	public void addToReturn(String itemName) {
		Item returnItem=mainInventory.searchInventory(itemName);
		if (returnItem==null) {
			return;
		}
		currentReturn.addItem(returnItem);		
	}
	
	public void removeFromReturn(String itemName) {
		Item returnItem=mainInventory.searchInventory(itemName);
		if (returnItem==null) {
			return;
		}
		currentReturn.removeItem(returnItem);		
	}
	
	
	
	public String completeSale(double payment) {
		String moneyOwedString;
		DecimalFormat df = new DecimalFormat("#.##");
		if (payment < currentSale.total) {
			Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Payment is not enough!");
            alert.setHeaderText(null);
            alert.setContentText("Please ask for more payment.");
            Optional<ButtonType> result = alert.showAndWait();
           
            return "";
		} else {
			double moneyOwed = payment - currentSale.total;
			moneyOwedString =df.format(moneyOwed);
			currentReceipt = currentSale.generateReceipt(this.saleId, payment, moneyOwed);
			receipts.add(currentReceipt);
			this.saleId++;
			mainInventory.updateInventoryCSV();
			this.mainInventory = new Inventory();
			this.currentCashier.addToDrawer(this.currentSale);
			return moneyOwedString;
		}
		
	}
	
	public String completeReturn() {
		String moneyOwedString;
		DecimalFormat df = new DecimalFormat("#.##");
		double moneyOwed = currentReturn.total - currentReturn.getOldReceipt().getTotal();
		moneyOwedString =df.format(moneyOwed);
		receipts.remove(currentReturn.getOldReceipt());
		currentReceipt = currentSale.generateReceipt(this.currentReturnID, 0, moneyOwed);
		receipts.add(currentReceipt);
		mainInventory.updateInventoryCSV();
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
	
	public String createIventoryReport() { //where does this go?
		this.mainInventory=new Inventory();
		String inventoryReport = "Report generated " + getTime() + "\nInventory today:\n";
		inventoryReport = mainInventory.toString(); 
		return inventoryReport;
	}
	
	public String createCashierReport() { //where does this go?
		String cashierReport = "Report generated " + getTime() + "\nCashiers logged in today:\n";
		for(int n = 0; n < cashiers.size(); n++) {
			cashierReport += cashiers.get(n).getName() + "\n";
		}
		return cashierReport;
	}
	
	public String createRegisterReport() { //where does this go?
		String registerReport = "Report generated " + getTime() + "\nRegisters today:\n";
		for(int c = 0; c < registers.size(); c++) {
			registerReport += registers.get(c).toString();
		}
		return registerReport;
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
	protected void setCurrentReturnID(int currentReturnID) {
		this.currentReturnID=currentReturnID;
	}
	protected Return getCurrentReturn() {
		return currentReturn;
	}
	
	protected String getReceiptHeaders() {
		String headers = "";
		for (Receipt receipt:receipts) {
			headers += receipt.headerToString() + "\n";
		}
		return headers;
	}
}
