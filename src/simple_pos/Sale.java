package simple_pos;

import javax.swing.plaf.basic.BasicSliderUI;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Sale implements Transactions {

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


	@Override
	public void addItem(Object item) {
		Item newItem = (Item)item;
		if (currentSale.containsKey(newItem))
			currentSale.put(newItem, currentSale.get(item)+1);
		else
		currentSale.put(newItem, 1);
	}

	@Override
	public void removeItem(Object item) {
		Item newItem = (Item)item;
		if(currentSale.containsKey(newItem)){
			currentSale.put(newItem, currentSale.get(newItem)-1);
			if(currentSale.get(newItem)<=0)
				currentSale.remove(newItem);
		}
	}

	@Override
	public void cancel() {
		currentSale = null;
	}

	@Override
	public void requestCash() {
		cashRequested = true;
	}

	@Override
	public boolean makeSale() {
		try {
			//calculate total
			for (Map.Entry<Item, Integer> singleSale : currentSale.entrySet()) {
				Item item = singleSale.getKey();
				Integer quantity = singleSale.getValue();

				total = total + item.getPrice() * quantity;
			}

			//TODO write the stub for get payment or get called from cashier

			//generateReceipt and return true
			generateReceipt();
			//adding the receipt generated to our list of receipts for sale
			receiptsGenerated.add(receipt);

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
		receipt = new Receipt(new Date(), null, total, currentSale,amountPaid, balance,0);
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

	@Override
    public String toString() {
        return "" + currentSale;
	}
}
