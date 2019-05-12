package simple_pos;

import javax.swing.plaf.basic.BasicLabelUI;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Return extends Sale implements Transactions {

	HashMap<Item, Integer> returnSale = currentSale;

	double newTotal = 0;

	final double newAmountPaid = 0;
	final double newBalance = 0;
	double amountReturned = 0;

	Receipt newReceipt = null;

	@Override
	public void removeItem(Object item) {
		Item newItem = (Item)item;
		if(returnSale.containsKey(newItem)){
			returnSale.put(newItem, returnSale.get(newItem)-1);
			if(returnSale.get(newItem)<=0)
				returnSale.remove(newItem);
		}
	}

	@Override
	public boolean makeReturn(Object removeItem) {

		removeItem(removeItem);

		try {
			//calculate total
			for (Map.Entry<Item, Integer> singleSale : returnSale.entrySet()) {
				Item item = singleSale.getKey();
				Integer quantity = singleSale.getValue();

				newTotal = newTotal + item.getPrice() * quantity;
			}

			//returning the payment
			//TODO write the stub for return payment or get called from cashier

			//generateReceipt and return true
			generateReceipt();
			//adding the receipt generated to our list of receipts for sale
			receiptsGenerated.add(newReceipt);

		}catch (NullPointerException | IllegalArgumentException e){
			System.out.println("caught exception while making sale. Error is : " + e);
			return false;
		}

		return true;
	}

	//no amount needs to be paid while return
	@Override
	public double getPayment(double newAmountPaid, double newBalance) {
		return 0;
	}

	@Override
	public double returnPayment(double amountReturned){
		this.amountReturned = amountReturned;
		return amountReturned;
	}

	@Override
	public Receipt generateReceipt(){
		newReceipt = new Receipt(receipt.getPurchaseDate(), new Date(), newTotal, returnSale,0, 0,amountReturned);
		return newReceipt;
	}

	// This methods is not valid for return sale

	@Override
	public void addItem(Object Item) {
	}

	@Override
	public void cancel() {
		// TODO Auto-generated method stub

	}

}
