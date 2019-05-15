package simple_pos;

import javax.swing.plaf.basic.BasicSliderUI;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Iterator;
import java.util.Set;

public class Sale implements Transactions {

	Tracker tracker = Tracker.getInstance();

	//decalre final saleId
	final int saleId = tracker.getSaleId();

	//store current sale item and quantity
	HashMap<Item, Integer> currentSale = new HashMap<>();

	//keeping lists of currentSale receipts and any subsequent returns or exchange receipts
	ArrayList<Receipt> receiptsGenerated = new ArrayList<>();

	//receipt generated
	Receipt receipt = null;

	//calculate total price of current sale
	double total = 0.0;

	//check if cash is requested on sale by cashier
	boolean cashRequested = false;

	//check amount paid by customer
	double amountPaid = 0;

	//balance remaining to be paid
	double balance = 0;

	//getting inventory instance
	Inventory inventory = new Inventory();
	
	boolean firstRun = true;

	@Override
	public void addItem(Object item) {
		Item newItem = (Item)item;
		if (currentSale.containsKey(newItem))
			currentSale.put(newItem, currentSale.get(item)+1);
		else
			currentSale.put(newItem, 1);
		newItem.setQuantity(1);  //making sure we set item quantity to 1 to reduce inventory quantity for this item by only 1
		inventory.removeFromInventory(newItem);
		
		//calculate total
		this.total+=newItem.getPrice();
	}

	@Override
	public void removeItem(Object item) {
		Item newItem = (Item)item;
		if(currentSale.containsKey(newItem)){
			currentSale.put(newItem, currentSale.get(newItem)-1);
			if(currentSale.get(newItem)<=0)
				currentSale.remove(newItem);
			newItem.setQuantity(1); //making sure we set item quantity to 1 to increase inventory quantity for this item by only 1
			inventory.addToInventory(newItem);
		}
		//calculate total
		this.total-=newItem.getPrice();
	}

	@Override
	public void cancel() {
		Set set = currentSale.entrySet();
		Iterator iterator = set.iterator();
		
	    while(iterator.hasNext()) {
	          Map.Entry mentry = (Map.Entry)iterator.next();
	          removeItem(mentry.getKey());
	       }
	}

	@Override
	public void requestCash() {
		cashRequested = true;
	}

	@Override
	public boolean makeSale() {
		try {

			//TODO write the stub for get payment or get called from cashier

			//generateReceipt and return true
			generateReceipt();
			//adding the receipt generated to our list of receipts for sale
			receiptsGenerated.add(receipt);

			//adding the sale to tracker
			tracker.addSale(this);

		}catch (NullPointerException | IllegalArgumentException e){
			System.out.println("caught exception while making sale. Error is : " + e);
			return false;
		}

		return true;
	}

	@Override
	public double getPayment(double amountPaid, double balance) {
		this.amountPaid = amountPaid;
		this.balance = balance;
		return amountPaid;
	}

	@Override
	public Receipt generateReceipt(){
		receipt = new Receipt(saleId, new Date(), null, total, currentSale,amountPaid, balance,0);
		return receipt;
	}


	@Override
	public boolean makeReturn(Object item) {
		return false;
	}

	@Override
	public double returnPayment(double amountReturned){
		return amountReturned;
	}
	
	//Getters and setters
	public double getTotal() {
		return this.total;
	}
	
	@Override
    public String toString() {
		String toString="";
		for ( Map.Entry saleItem : currentSale.entrySet()  ) {
             toString += ((Item) saleItem.getKey()).getName() + "      " + saleItem.getValue() + "\n";
		}
		return toString;
	}

}